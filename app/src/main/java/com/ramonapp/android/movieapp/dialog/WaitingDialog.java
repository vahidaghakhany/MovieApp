package com.ramonapp.android.movieapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.ramonapp.android.movieapp.R;


public class WaitingDialog extends Dialog {
    public WaitingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_waiting);
        setCanceledOnTouchOutside(false);
    }

}
