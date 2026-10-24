package com.fariz.rotiflow;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(28, 28, 28, 28);
        root.setBackgroundColor(Color.rgb(248, 249, 251));

        TextView title = new TextView(this);
        title.setText("RotiFlow");
        title.setTextColor(Color.rgb(28, 39, 54));
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView subtitle = new TextView(this);
        subtitle.setText("Fondasi aplikasi siap\nFitur pesanan harian akan dibangun berikutnya.");
        subtitle.setTextColor(Color.rgb(75, 85, 99));
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 16, 0, 0);

        TextView demo = new TextView(this);
        demo.setText("PROTOTIPE — BELUM UNTUK DATA NYATA");
        demo.setTextColor(Color.rgb(140, 80, 20));
        demo.setTextSize(12);
        demo.setGravity(Gravity.CENTER);
        demo.setPadding(0, 24, 0, 0);

        root.addView(title, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root.addView(subtitle, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root.addView(demo, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(root);
    }
}
