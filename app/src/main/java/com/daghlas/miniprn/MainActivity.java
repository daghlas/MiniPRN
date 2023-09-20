package com.daghlas.miniprn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView paymentOfTaxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paymentOfTaxes = findViewById(R.id.paymentOfTaxes);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));

        paymentOfTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaymentOfTaxes.class);
                startActivity(intent);
            }
        });

    }
}