package com.example.tomer.just_a_bit;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Tomer on 27/01/2017.
 */

public class Tab0Fragment extends Fragment implements View.OnClickListener {
    Button test;
    ZXingScannerView scanner;
    final int QR_REQUEST_CODE = 13;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_0, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        test = (Button) getView().findViewById(R.id.btnTest);
        test.setOnClickListener(this);



    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnTest:
                onClickBtnTest(view);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case QR_REQUEST_CODE:
                    Toast.makeText(getContext(), data.getStringExtra(ScannerActivity.EXTRA_RESULT), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
       else  Toast.makeText(getContext(), "Scan Cancelled", Toast.LENGTH_SHORT).show();
    }

    private void onClickBtnTest(View view) {

        Intent intentToScannerActivity = new Intent(getContext(),ScannerActivity.class);
        startActivityForResult(intentToScannerActivity,QR_REQUEST_CODE);


    }

}
