package com.fariz.rotiflow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/** Local-only storage for traders and dated bread orders. */
public final class OrderDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rotiflow.db";
    private static final int DATABASE_VERSION = 1;

    public OrderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE traders (name TEXT PRIMARY KEY COLLATE NOCASE)");
        db.execSQL("CREATE TABLE orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "order_date TEXT NOT NULL, " +
                "trader_name TEXT NOT NULL, " +
                "quantities TEXT NOT NULL)");
        db.execSQL("CREATE INDEX idx_orders_date ON orders(order_date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Schema migrations will be added when the database schema changes.
    }

    public long insertOrder(String date, String trader, long[] quantities) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            rememberTrader(db, trader);
            ContentValues values = new ContentValues();
            values.put("order_date", date);
            values.put("trader_name", trader);
            values.put("quantities", encodeQuantities(quantities));
            long id = db.insertOrThrow("orders", null, values);
            db.setTransactionSuccessful();
            return id;
        } finally {
            db.endTransaction();
        }
    }

    public boolean updateOrder(long id, String date, String trader, long[] quantities) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            rememberTrader(db, trader);
            ContentValues values = new ContentValues();
            values.put("order_date", date);
            values.put("trader_name", trader);
            values.put("quantities", encodeQuantities(quantities));
            int changed = db.update("orders", values, "id=?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            return changed > 0;
        } finally {
            db.endTransaction();
        }
    }

    public void deleteOrder(long id) {
        getWritableDatabase().delete("orders", "id=?", new String[]{String.valueOf(id)});
    }

    public OrderRecord getOrder(long id) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.query("orders",
                new String[]{"id", "order_date", "trader_name", "quantities"},
                "id=?", new String[]{String.valueOf(id)}, null, null, null)) {
            return cursor.moveToFirst() ? readOrder(cursor) : null;
        }
    }

    public List<OrderRecord> getOrdersForDate(String date) {
        List<OrderRecord> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.query("orders",
                new String[]{"id", "order_date", "trader_name", "quantities"},
                "order_date=?", new String[]{date}, null, null, "id DESC")) {
            while (cursor.moveToNext()) {
                result.add(readOrder(cursor));
            }
        }
        return result;
    }

    public List<String> getAllTraders() {
        List<String> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.query("traders", new String[]{"name"},
                null, null, null, null, "name COLLATE NOCASE ASC")) {
            while (cursor.moveToNext()) {
                result.add(cursor.getString(0));
            }
        }
        return result;
    }

    private static void rememberTrader(SQLiteDatabase db, String trader) {
        ContentValues values = new ContentValues();
        values.put("name", trader);
        db.insertWithOnConflict("traders", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    private static OrderRecord readOrder(Cursor cursor) {
        return new OrderRecord(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                decodeQuantities(cursor.getString(3)));
    }

    private static String encodeQuantities(long[] quantities) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < quantities.length; i++) {
            if (i > 0) builder.append(',');
            builder.append(quantities[i]);
        }
        return builder.toString();
    }

    private static long[] decodeQuantities(String encoded) {
        String[] parts = encoded == null ? new String[0] : encoded.split(",", -1);
        long[] quantities = new long[15];
        for (int i = 0; i < quantities.length && i < parts.length; i++) {
            try {
                quantities[i] = Math.max(0L, Long.parseLong(parts[i]));
            } catch (NumberFormatException ignored) {
                quantities[i] = 0L;
            }
        }
        return quantities;
    }

    public static final class OrderRecord {
        public final long id;
        public final String date;
        public final String trader;
        public final long[] quantities;

        OrderRecord(long id, String date, String trader, long[] quantities) {
            this.id = id;
            this.date = date;
            this.trader = trader;
            this.quantities = quantities;
        }

        public long total() {
            long sum = 0;
            for (long quantity : quantities) {
                if (Long.MAX_VALUE - sum < quantity) return Long.MAX_VALUE;
                sum += quantity;
            }
            return sum;
        }
    }
}
