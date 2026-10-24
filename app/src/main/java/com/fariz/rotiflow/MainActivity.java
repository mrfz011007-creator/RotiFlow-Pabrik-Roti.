package com.fariz.rotiflow;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/** RotiFlow: local daily orders for a bread factory. */
public class MainActivity extends Activity {
    private static final String[] BREAD_TYPES = {
            "Srikaya", "Kelapa", "Paha ayam", "Donat buat", "Moca",
            "Abon", "Keju", "Mantul", "Donat panjang", "Kacang hijau",
            "Pisang coklat", "Seres warna", "Cappucino", "Nanas", "Manis"
    };

    private static final int INK = Color.rgb(30, 41, 59);
    private static final int MUTED = Color.rgb(100, 116, 139);
    private static final int BLUE = Color.rgb(37, 99, 235);
    private static final int BG = Color.rgb(246, 248, 251);
    private static final int BORDER = Color.rgb(226, 232, 240);
    private static final int WHITE = Color.WHITE;
    private static final int GREEN = Color.rgb(22, 101, 52);
    private static final int RED = Color.rgb(185, 28, 28);

    private OrderDatabase database;
    private String selectedDate;
    private String formDate;
    private String screen = "home";
    private long activeOrderId = -1L;
    private boolean editingFromDetails = false;
    private EditText traderInput;
    private EditText[] quantityInputs;
    private TextView formDateText;
    private TextView formTotalPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new OrderDatabase(this);
        selectedDate = todayIso();
        getWindow().setStatusBarColor(WHITE);
        getWindow().setNavigationBarColor(Color.BLACK);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        renderHome();
    }

    @Override
    protected void onDestroy() {
        if (database != null) database.close();
        super.onDestroy();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBackPressed() {
        if ("form".equals(screen)) {
            if (editingFromDetails && activeOrderId >= 0) {
                renderDetails(activeOrderId);
            } else {
                renderHome();
            }
        } else if ("details".equals(screen)) {
            renderHome();
        } else {
            super.onBackPressed();
        }
    }

    private void renderHome() {
        screen = "home";
        activeOrderId = -1L;
        editingFromDetails = false;

        LinearLayout content = newPage();
        addHeader(content, "Pesanan Harian", "Catat pesanan pedagang dan lihat total kebutuhan roti.");

        LinearLayout dateRow = new LinearLayout(this);
        dateRow.setGravity(Gravity.CENTER_VERTICAL);
        dateRow.setOrientation(LinearLayout.HORIZONTAL);
        TextView dateLabel = text("Tanggal pesanan", 13, MUTED, false);
        dateRow.addView(dateLabel, new LinearLayout.LayoutParams(0, dp(44), 1f));
        Button dateButton = button(displayDate(selectedDate) + "  ▾", WHITE, INK);
        dateButton.setBackground(roundRect(WHITE, BORDER, 12));
        dateButton.setOnClickListener(v -> chooseDate(selectedDate, value -> {
            selectedDate = value;
            renderHome();
        }));
        dateRow.addView(dateButton);
        content.addView(dateRow, matchWrap());
        addGap(content, 8);

        Button addButton = button("+  Tambah Pesanan Pedagang", BLUE, WHITE);
        addButton.setOnClickListener(v -> {
            activeOrderId = -1L;
            editingFromDetails = false;
            renderForm(null);
        });
        content.addView(addButton, matchWrap());
        addGap(content, 22);

        List<OrderDatabase.OrderRecord> orders = database.getOrdersForDate(selectedDate);
        addSectionTitle(content, "PESANAN PEDAGANG", orders.size() + " catatan");
        if (orders.isEmpty()) {
            LinearLayout empty = cardColumn();
            TextView emptyTitle = text("Belum ada pesanan", 16, INK, true);
            TextView emptyHint = text("Untuk tanggal ini belum ada catatan. Tambahkan pesanan dari pedagang yang memesan hari ini.", 13, MUTED, false);
            emptyHint.setLineSpacing(dp(3), 1f);
            empty.addView(emptyTitle, matchWrap());
            addGap(empty, 6);
            empty.addView(emptyHint, matchWrap());
            content.addView(empty, matchWrap());
        } else {
            for (OrderDatabase.OrderRecord order : orders) {
                content.addView(orderCard(order), matchWrap());
                addGap(content, 8);
            }
        }

        addGap(content, 14);
        addSectionTitle(content, "TOTAL PER JENIS ROTI", "Buah");
        long[] totals = aggregate(orders);
        LinearLayout totalCard = cardColumn();
        for (int i = 0; i < BREAD_TYPES.length; i++) {
            addQuantityRow(totalCard, BREAD_TYPES[i], totals[i], i < BREAD_TYPES.length - 1);
        }
        content.addView(totalCard, matchWrap());

        addGap(content, 16);
        TextView localNote = text("Data disimpan di perangkat ini saja. Belum ada sinkronisasi atau cadangan data.", 12, MUTED, false);
        localNote.setGravity(Gravity.CENTER);
        content.addView(localNote, matchWrap());
        setContentView(wrapPage(content));
    }

    private View orderCard(OrderDatabase.OrderRecord order) {
        LinearLayout card = cardColumn();
        card.setBackground(roundRect(WHITE, BORDER, 12));
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout left = new LinearLayout(this);
        left.setOrientation(LinearLayout.VERTICAL);
        TextView trader = text(order.trader, 16, INK, true);
        TextView sub = text("Total " + formatNumber(order.total()) + " buah", 13, MUTED, false);
        left.addView(trader, matchWrap());
        addGap(left, 4);
        left.addView(sub, matchWrap());
        row.addView(left, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        TextView arrow = text("Buka rincian  ›", 12, BLUE, true);
        row.addView(arrow);
        card.addView(row, matchWrap());
        card.setOnClickListener(v -> renderDetails(order.id));
        return card;
    }

    private void renderDetails(long id) {
        OrderDatabase.OrderRecord order = database.getOrder(id);
        if (order == null) {
            toast("Pesanan tidak ditemukan.");
            renderHome();
            return;
        }
        screen = "details";
        activeOrderId = id;
        selectedDate = order.date;
        LinearLayout content = newPage();
        addBackRow(content, "Rincian Pesanan", this::renderHome);
        TextView trader = text(order.trader, 24, INK, true);
        content.addView(trader, matchWrap());
        addGap(content, 5);
        TextView date = text("Tanggal: " + displayDate(order.date), 14, MUTED, false);
        content.addView(date, matchWrap());
        addGap(content, 16);

        LinearLayout summary = cardColumn();
        addSummaryLine(summary, "Total pesanan", formatNumber(order.total()) + " buah", true);
        content.addView(summary, matchWrap());
        addGap(content, 14);
        addSectionTitle(content, "RINCIAN 15 JENIS ROTI", "");
        LinearLayout details = cardColumn();
        for (int i = 0; i < BREAD_TYPES.length; i++) {
            addQuantityRow(details, BREAD_TYPES[i], order.quantities[i], i < BREAD_TYPES.length - 1);
        }
        content.addView(details, matchWrap());
        addGap(content, 18);

        Button edit = button("Edit Pesanan", BLUE, WHITE);
        edit.setOnClickListener(v -> {
            editingFromDetails = true;
            renderForm(database.getOrder(id));
        });
        content.addView(edit, matchWrap());
        addGap(content, 9);
        Button delete = button("Hapus Pesanan", WHITE, RED);
        delete.setBackground(roundRect(WHITE, Color.rgb(254, 202, 202), 12));
        delete.setOnClickListener(v -> confirmDelete(id, order.trader));
        content.addView(delete, matchWrap());
        setContentView(wrapPage(content));
    }

    private void renderForm(OrderDatabase.OrderRecord existing) {
        screen = "form";
        if (existing != null) {
            activeOrderId = existing.id;
            formDate = existing.date;
        } else {
            activeOrderId = -1L;
            formDate = selectedDate;
        }

        LinearLayout content = newPage();
        addBackRow(content, existing == null ? "Tambah Pesanan" : "Edit Pesanan", () -> {
            if (existing != null && editingFromDetails) renderDetails(existing.id);
            else renderHome();
        });
        TextView guidance = text("Isi jumlah pesanan dalam buah. Kolom kosong akan disimpan sebagai 0.", 13, MUTED, false);
        guidance.setLineSpacing(dp(3), 1f);
        content.addView(guidance, matchWrap());
        addGap(content, 16);

        addFieldLabel(content, "Tanggal pesanan");
        formDateText = text(displayDate(formDate) + "  ▾", 15, INK, true);
        formDateText.setGravity(Gravity.CENTER_VERTICAL);
        formDateText.setPadding(dp(14), 0, dp(14), 0);
        formDateText.setBackground(roundRect(WHITE, BORDER, 10));
        formDateText.setOnClickListener(v -> chooseDate(formDate, value -> {
            formDate = value;
            formDateText.setText(displayDate(value) + "  ▾");
        }));
        content.addView(formDateText, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(48)));
        addGap(content, 14);

        addFieldLabel(content, "Nama pedagang");
        AutoCompleteTextView trader = new AutoCompleteTextView(this);
        traderInput = trader;
        trader.setSingleLine(true);
        trader.setTextSize(15);
        trader.setTextColor(INK);
        trader.setHint("Pilih atau ketik nama pedagang");
        trader.setHintTextColor(MUTED);
        trader.setPadding(dp(14), 0, dp(14), 0);
        trader.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        trader.setBackground(roundRect(WHITE, BORDER, 10));
        List<String> traders = database.getAllTraders();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, traders);
        trader.setAdapter(adapter);
        trader.setThreshold(1);
        if (existing != null) trader.setText(existing.trader, false);
        content.addView(trader, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(48)));
        addGap(content, 18);

        addSectionTitle(content, "JENIS ROTI", "JUMLAH");
        LinearLayout formCard = cardColumn();
        quantityInputs = new EditText[BREAD_TYPES.length];
        for (int i = 0; i < BREAD_TYPES.length; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setOrientation(LinearLayout.HORIZONTAL);
            TextView name = text(BREAD_TYPES[i], 14, INK, false);
            row.addView(name, new LinearLayout.LayoutParams(0, dp(48), 1f));
            EditText quantity = new EditText(this);
            quantityInputs[i] = quantity;
            quantity.setSingleLine(true);
            quantity.setTextSize(15);
            quantity.setTextColor(INK);
            quantity.setGravity(Gravity.CENTER);
            quantity.setInputType(InputType.TYPE_CLASS_NUMBER);
            quantity.setSelectAllOnFocus(true);
            quantity.setHint("0");
            quantity.setPadding(dp(6), 0, dp(6), 0);
            quantity.setBackground(roundRect(Color.rgb(248, 250, 252), BORDER, 8));
            if (existing == null) quantity.setText("");
            else if (existing.quantities[i] != 0L) quantity.setText(String.valueOf(existing.quantities[i]));
            row.addView(quantity, new LinearLayout.LayoutParams(dp(104), dp(42)));
            quantity.addTextChangedListener(new android.text.TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    updateFormTotalPreview();
                }
                @Override public void afterTextChanged(android.text.Editable editable) { }
            });
            formCard.addView(row, matchWrap());
            if (i < BREAD_TYPES.length - 1) addDivider(formCard);
        }
        content.addView(formCard, matchWrap());
        addGap(content, 14);

        formTotalPreview = text("Total pesanan: " + formatNumber(existing == null ? 0L : existing.total()) + " buah", 15, GREEN, true);
        LinearLayout previewCard = cardColumn();
        previewCard.addView(formTotalPreview, matchWrap());
        content.addView(previewCard, matchWrap());
        updateFormTotalPreview();
        addGap(content, 18);
        Button save = button("Simpan Pesanan", BLUE, WHITE);
        save.setOnClickListener(v -> saveOrder(existing));
        content.addView(save, matchWrap());
        addGap(content, 9);
        Button cancel = button("Batal", WHITE, INK);
        cancel.setBackground(roundRect(WHITE, BORDER, 12));
        cancel.setOnClickListener(v -> {
            if (existing != null && editingFromDetails) renderDetails(existing.id);
            else renderHome();
        });
        content.addView(cancel, matchWrap());
        addGap(content, 20);
        setContentView(wrapPage(content));
    }

    private void updateFormTotalPreview() {
        if (formTotalPreview == null || quantityInputs == null) return;
        long total = 0L;
        for (EditText input : quantityInputs) {
            if (input == null) continue;
            String value = input.getText().toString().trim();
            if (value.isEmpty()) continue;
            if (!value.matches("[0-9]+")) {
                formTotalPreview.setText("Total pesanan: periksa angka yang dimasukkan");
                formTotalPreview.setTextColor(RED);
                return;
            }
            try {
                long amount = Long.parseLong(value);
                if (Long.MAX_VALUE - total < amount) {
                    formTotalPreview.setText("Total pesanan terlalu besar");
                    formTotalPreview.setTextColor(RED);
                    return;
                }
                total += amount;
            } catch (NumberFormatException e) {
                formTotalPreview.setText("Jumlah terlalu besar");
                formTotalPreview.setTextColor(RED);
                return;
            }
        }
        formTotalPreview.setText("Total pesanan: " + formatNumber(total) + " buah");
        formTotalPreview.setTextColor(GREEN);
    }

    private void saveOrder(OrderDatabase.OrderRecord existing) {
        String trader = traderInput.getText().toString().trim();
        if (trader.isEmpty()) {
            traderInput.setError("Nama pedagang wajib diisi");
            traderInput.requestFocus();
            return;
        }
        if (formDate == null || !formDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            toast("Pilih tanggal pesanan yang valid.");
            return;
        }

        long[] quantities = new long[BREAD_TYPES.length];
        long total = 0L;
        for (int i = 0; i < quantityInputs.length; i++) {
            String value = quantityInputs[i].getText().toString().trim();
            if (value.isEmpty()) {
                quantities[i] = 0L;
                continue;
            }
            if (!value.matches("[0-9]+")) {
                quantityInputs[i].setError("Masukkan angka bulat nonnegatif");
                quantityInputs[i].requestFocus();
                return;
            }
            try {
                quantities[i] = Long.parseLong(value);
            } catch (NumberFormatException e) {
                quantityInputs[i].setError("Angka terlalu besar");
                quantityInputs[i].requestFocus();
                return;
            }
            if (Long.MAX_VALUE - total < quantities[i]) {
                quantityInputs[i].setError("Total pesanan terlalu besar");
                quantityInputs[i].requestFocus();
                return;
            }
            total += quantities[i];
        }

        try {
            if (existing == null) {
                database.insertOrder(formDate, trader, quantities);
            } else {
                database.updateOrder(existing.id, formDate, trader, quantities);
            }
            selectedDate = formDate;
            activeOrderId = -1L;
            editingFromDetails = false;
            toast(existing == null ? "Pesanan berhasil disimpan." : "Perubahan pesanan disimpan.");
            renderHome();
        } catch (RuntimeException e) {
            toast("Pesanan gagal disimpan. Coba lagi.");
        }
    }

    private void confirmDelete(long id, String trader) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus pesanan?")
                .setMessage("Pesanan " + trader + " pada " + displayDate(selectedDate) + " akan dihapus. Tindakan ini tidak dapat dibatalkan.")
                .setNegativeButton("Batal", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Hapus", (dialog, which) -> {
                    database.deleteOrder(id);
                    toast("Pesanan dihapus.");
                    renderHome();
                })
                .show();
    }

    private void chooseDate(String current, DateChosen callback) {
        int year, month, day;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(current);
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(java.util.Calendar.YEAR);
            month = calendar.get(java.util.Calendar.MONTH);
            day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            year = calendar.get(java.util.Calendar.YEAR);
            month = calendar.get(java.util.Calendar.MONTH);
            day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        }
        new DatePickerDialog(this, (view, chosenYear, chosenMonth, chosenDay) -> {
            String value = String.format(Locale.US, "%04d-%02d-%02d",
                    chosenYear, chosenMonth + 1, chosenDay);
            callback.onChosen(value);
        }, year, month, day).show();
    }

    private LinearLayout newPage() {
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(18), dp(18), dp(18), dp(24));
        content.setBackgroundColor(BG);
        return content;
    }

    private ScrollView wrapPage(LinearLayout content) {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setClipToPadding(false);
        scroll.setBackgroundColor(BG);
        scroll.addView(content, new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return scroll;
    }

    private void addHeader(LinearLayout root, String title, String subtitle) {
        TextView brand = text("RotiFlow", 15, BLUE, true);
        root.addView(brand, matchWrap());
        addGap(root, 7);
        TextView heading = text(title, 25, INK, true);
        root.addView(heading, matchWrap());
        addGap(root, 5);
        TextView sub = text(subtitle, 13, MUTED, false);
        sub.setLineSpacing(dp(3), 1f);
        root.addView(sub, matchWrap());
        addGap(root, 18);
    }

    private void addBackRow(LinearLayout root, String title, Runnable backAction) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.HORIZONTAL);
        Button back = button("‹  Kembali", WHITE, BLUE);
        back.setBackground(roundRect(WHITE, BORDER, 10));
        back.setOnClickListener(v -> backAction.run());
        row.addView(back);
        TextView heading = text(title, 19, INK, true);
        heading.setGravity(Gravity.CENTER_VERTICAL);
        heading.setPadding(dp(12), 0, 0, 0);
        row.addView(heading, new LinearLayout.LayoutParams(0, dp(48), 1f));
        root.addView(row, matchWrap());
        addGap(root, 16);
    }

    private void addSectionTitle(LinearLayout root, String title, String trailing) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        TextView heading = text(title, 12, MUTED, true);
        heading.setLetterSpacing(0.04f);
        row.addView(heading, new LinearLayout.LayoutParams(0, dp(34), 1f));
        if (trailing != null && !trailing.isEmpty()) {
            TextView right = text(trailing, 12, MUTED, false);
            row.addView(right);
        }
        root.addView(row, matchWrap());
    }

    private void addFieldLabel(LinearLayout root, String label) {
        TextView title = text(label, 13, INK, true);
        root.addView(title, matchWrap());
        addGap(root, 7);
    }

    private LinearLayout cardColumn() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(14), dp(8), dp(14), dp(8));
        card.setBackground(roundRect(WHITE, BORDER, 12));
        return card;
    }

    private void addQuantityRow(LinearLayout parent, String name, long quantity, boolean divider) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        TextView label = text(name, 14, INK, false);
        row.addView(label, new LinearLayout.LayoutParams(0, dp(39), 1f));
        TextView value = text(formatNumber(quantity), 14, INK, true);
        value.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        row.addView(value);
        parent.addView(row, matchWrap());
        if (divider) addDivider(parent);
    }

    private void addSummaryLine(LinearLayout parent, String label, String value, boolean strong) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        TextView left = text(label, 14, MUTED, false);
        TextView right = text(value, 16, strong ? GREEN : INK, true);
        row.addView(left, new LinearLayout.LayoutParams(0, dp(42), 1f));
        row.addView(right);
        parent.addView(row, matchWrap());
    }

    private void addDivider(LinearLayout parent) {
        View divider = new View(this);
        divider.setBackgroundColor(BORDER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(1));
        params.topMargin = dp(1);
        parent.addView(divider, params);
    }

    private TextView text(String value, int sizeSp, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(sizeSp);
        view.setTextColor(color);
        if (bold) view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return view;
    }

    private Button button(String label, int background, int foreground) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextSize(14);
        button.setTextColor(foreground);
        button.setAllCaps(false);
        button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        button.setMinHeight(dp(48));
        button.setPadding(dp(14), dp(7), dp(14), dp(7));
        button.setBackground(roundRect(background, background == WHITE ? BORDER : background, 12));
        return button;
    }

    private GradientDrawable roundRect(int color, int stroke, int radiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radiusDp));
        drawable.setStroke(dp(1), stroke);
        return drawable;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void addGap(LinearLayout parent, int heightDp) {
        View space = new View(this);
        parent.addView(space, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(heightDp)));
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private long[] aggregate(List<OrderDatabase.OrderRecord> orders) {
        long[] totals = new long[BREAD_TYPES.length];
        for (OrderDatabase.OrderRecord order : orders) {
            for (int i = 0; i < totals.length; i++) {
                long value = order.quantities[i];
                totals[i] = Long.MAX_VALUE - totals[i] < value
                        ? Long.MAX_VALUE : totals[i] + value;
            }
        }
        return totals;
    }

    private String formatNumber(long number) {
        return NumberFormat.getIntegerInstance(new Locale("id", "ID")).format(number);
    }

    private String todayIso() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    private String displayDate(String iso) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(iso);
            return new SimpleDateFormat("dd MMM yyyy", new Locale("id", "ID")).format(date);
        } catch (Exception e) {
            return iso;
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private interface DateChosen {
        void onChosen(String date);
    }
}
