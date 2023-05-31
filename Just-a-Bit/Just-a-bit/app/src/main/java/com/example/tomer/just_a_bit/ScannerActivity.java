package com.example.tomer.just_a_bit;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scanner;

    public static final String EXTRA_RESULT = "result";

    final String DEFAULT_RETURN_DATA = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        scanner = new ZXingScannerView(this);
        setContentView(scanner);
        scanner.setResultHandler(this);
        scanner.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scanner.stopCamera();
    }



    @Override
    public void handleResult(Result result) {
        scanner.stopCamera();
        returnData(result.getText());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelAndReturn();
    }
    void returnData(String data)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_RESULT, data);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    void cancelAndReturn()
    {
        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }


}
