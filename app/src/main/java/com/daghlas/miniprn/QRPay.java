package com.daghlas.miniprn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class QRPay extends AppCompatActivity {

    ImageView back;
    EditText amount, payBill, accountNo, phone;
    Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpay);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));

        proceed = findViewById(R.id.proceed);
        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        amount = findViewById(R.id.amount);
        payBill = findViewById(R.id.payBill);
        accountNo = findViewById(R.id.accountNo);
        phone = findViewById(R.id.phone);

        amount.setEnabled(false);
        payBill.setEnabled(false);
        accountNo.setEnabled(false);

        //retrieve values
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String amountExtra = bundle.getString("amount");
            String payBillExtra = bundle.getString("payBill");
            String referenceExtra = bundle.getString("reference");

            amount.setText(amountExtra);
            payBill.setText(payBillExtra);
            accountNo.setText(referenceExtra);
        }

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePhoneNumber()){
                    Toast.makeText(QRPay.this, "Validation failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QRPay.this, "Validation Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validatePhoneNumber() {
        String val = phone.getText().toString();
        if (val.isEmpty()) {
            phone.setError("Enter phone number");
            return false;
        } else if (val.length() != 10) {
            phone.setError("Enter valid admission no.");
            return false;
        }else {
            phone.setError(null);
            return true;
        }
    }
}