package com.example.tomer.just_a_bit;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tomer on 27/01/2017.
 */

public class Tab1Fragment extends Fragment implements View.OnClickListener{

    TextView tvAddress;
    final String CLIP_LABEL = "Bitcoin Address";
    final String TOAST_MESSAGE = "Adress Copied to Clipboard";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAddress = (TextView) getView().findViewById(R.id.tvAddress);
        tvAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tvAddress:
                onClickTvAddress(v);
        }
    }

    private void onClickTvAddress(View v) {
        ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(CLIP_LABEL, tvAddress.getText());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity().getBaseContext(), TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    


}
