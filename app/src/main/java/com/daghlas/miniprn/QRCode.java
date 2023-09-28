package com.daghlas.miniprn;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRCode extends AppCompatActivity {

    ImageView back;
    Button scanQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));

        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        scanQR = findViewById(R.id.scanQR);
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQRCode();
            }
        });
    }

    private void scanQRCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Press volume up button to on flash light");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaunch.launch(options);
    }

    ActivityResultLauncher <ScanOptions> barLaunch = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(QRCode.this);
            builder.setCancelable(false);
            builder.setTitle("Pay with QR Code");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("PAY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //capture receipt details from QR code scanned
                    String path = result.getContents();
                    String[] split = path.split(" ");
                    String amount = split[5];
                    String payBill = "572572";
                    String reference = split[11];

                    //Keep and post to next activity
                    Intent intent = new Intent(QRCode.this, QRPay.class);
                    intent.putExtra("amount", amount);
                    intent.putExtra("payBill", payBill);
                    intent.putExtra("reference", reference);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });
}