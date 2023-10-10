package com.daghlas.miniprn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CardView paymentOfTaxes;
    TextView developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paymentOfTaxes = findViewById(R.id.paymentOfTaxes);
        developer = findViewById(R.id.developer);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));

        paymentOfTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaymentOfTaxes.class);
                startActivity(intent);
            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.mipmap.ic_launcher_custom);
                builder.setTitle(R.string.app_name);
                builder.setMessage("This application is purely a test version still under development. " +
                        "This version of the app is for a proposal objective to the KRA regarding " +
                        "streamlining of tax payment procedures by adding QR Code scanning and prompting of the " +
                        "Safaricom STK Push Service as a payment procedure. STK Push from MPESA has been enabled by " +
                        "integrating Safaricom's Lipa Na Mpesa API with Daraja for completing transactions.\n\nAny " +
                        "similarities portrayed to the official " +
                        "KRA MService App are completely unintentional and only strive to accomplish the intended " +
                        "demonstrations for this app.\nThis app is not for commercial purposes and cannot be sold " +
                        "or reproduced without the developer's consent.\n\nDevelopers contact: \nPhone: 0723325631" +
                        "\nEmail: daghlaskaire58@gmail.com");
                builder.setCancelable(false);
                builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
    }
}