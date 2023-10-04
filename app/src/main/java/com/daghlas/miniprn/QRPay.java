package com.daghlas.miniprn;

import static com.daghlas.miniprn.Constants.BUSINESS_SHORT_CODE;
import static com.daghlas.miniprn.Constants.CALLBACKURL;
import static com.daghlas.miniprn.Constants.PARTYB;
import static com.daghlas.miniprn.Constants.PASSKEY;
import static com.daghlas.miniprn.Constants.TRANSACTION_TYPE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.daghlas.miniprn.Model.AccessToken;
import com.daghlas.miniprn.Model.STKPush;
import com.daghlas.miniprn.Services.DarajaApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class QRPay extends AppCompatActivity {

    ImageView back;
    EditText amount, payBill, accountNo, phone;
    Button proceed, done;
    //mpesa STKPush
    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpay);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));

        //mpesa STKPush
        mProgressDialog = new ProgressDialog(this);
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(true); //Set True to enable logging, false to disable.

        proceed = findViewById(R.id.proceed);
        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(QRPay.this, QRCode.class);
                startActivity(intent);
                finish();
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
                    //Toast.makeText(QRPay.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    String phone_number = phone.getText().toString();
                    String pay_amount = amount.getText().toString();
                    performSTKPush(phone_number,pay_amount);
                    proceed.setText(R.string.resend);
                    done.setText(R.string.finish);
                }
            }
        });

        //token method call
        getAccessToken();
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

    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }
            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }

    public void performSTKPush(String phone_number,String pay_amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(pay_amount),
                Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                accountNo.getText().toString(), //Payment Reference Number as Account reference
                "PRN Payment STK PUSH by Daghlas Kenyatta" //Transaction description
        );

        mApiClient.setGetAccessToken(false);
        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d("post submitted to API. %s", response.body());
                    } else {
                        Timber.e("Response %s", response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}