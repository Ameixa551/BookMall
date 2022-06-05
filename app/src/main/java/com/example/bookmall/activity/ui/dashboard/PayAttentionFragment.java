package com.example.bookmall.activity.ui.dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;



public class PayAttentionFragment extends DialogFragment {

    public interface PayAttentionDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private PayAttentionDialogListener listener;

    public PayAttentionFragment(PayAttentionDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getParentFragment().getContext());
        builder.setMessage("确定支付？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // START THE GAME!
                        listener.onDialogPositiveClick(PayAttentionFragment.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(PayAttentionFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
