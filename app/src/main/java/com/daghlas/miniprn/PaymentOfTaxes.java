package com.daghlas.miniprn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PaymentOfTaxes extends AppCompatActivity {

    CardView paymentOfTaxesPRN, paymentOfTaxesQRC;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_of_taxes);

        paymentOfTaxesPRN = findViewById(R.id.paymentOfTaxesPRN);
        paymentOfTaxesQRC = findViewById(R.id.paymentOfTaxesQRC);
        back = findViewById(R.id.backButton);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));

        paymentOfTaxesPRN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentOfTaxes.this, PRNumber.class);
                startActivity(intent);

            }
        });

        paymentOfTaxesQRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentOfTaxes.this, QRCode.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}