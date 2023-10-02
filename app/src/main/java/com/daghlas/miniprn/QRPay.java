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

        if (bundle != null) {
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
                if (validatePhoneNumber()) {
                    Toast.makeText(QRPay.this, "Validation Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validatePhoneNumber() {
        String val = phone.getText().toString();
        if (val.isEmpty()) {
            phone.setError("enter phone number");
            return false;
        } else if (val.length() != 10) {
            phone.setError("invalid phone no.");
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }

    /** to be honest i wasn't sure what i was doing this first time brother -- start
    /*
    private void stkPush() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, {
                "Initiator":testapi,
                "SecurityCredential":"ciJVgmrcEhWkfjagc7HsnGZ9/Gobz9tg7hIr/jxkSseatA8apVOv/xNdEATOxSquStHKCMd/VsM+Y7cTTIb0pVwSNEegWPjQjt7LYnrrXtyfrw95f5BGTInmXHR6YExRFp09++vSAoiN3n+SUrFzv6y6wHiRyr1G2aP0F/l1FUOHqwphY/31y+FedWegsbqzluMZxUj+G4rwUTjrpXEGRmwrPIErzbLu9CoivDNyDx7lB+SAatKATiW1WDY6zOhAPIN5puFslMq54beD0wb45jTxoZKodKDxClYQjRxy22XEgNSzNXOHKQMmUlbSLcvDpOr1j2wMw+bFqZ573uRiUw==",
                "CommandID":"PayTaxToKRA",
                "SenderIdentifierType":"4",
                "RecieverIdentifierType":4,
                "Amount":amount.getText(),
                "PartyA":phone.getText(),
                "PartyB":572572,
                "AccountReference":accountNo.getText(),
                "Remarks":"ok",
                "QueueTimeOutURL":"https://mydomain.com/b2b/queue/"
        "ResultURL":"https://mydomain.com/b2b/result/"
        });

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/b2b/v1/remittax")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer rS93NlmGuXKk8hVRrDsQlVWVXyZZ")
                .build();
        Response response = client.newCall(request).execute()
    }

     i wasn't sure what i was doing this first time brother -- close
     *
     */

}