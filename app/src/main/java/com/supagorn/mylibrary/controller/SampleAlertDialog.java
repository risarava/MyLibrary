package com.supagorn.mylibrary.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.ui.CustomDisplaySize;
import com.supagorn.mylibrary.ui.MyDialog;
import com.supagorn.mylibrary.ui.MyToast;

/**
 * Created by iabellah on 2016-10-10.
 */

public class SampleAlertDialog extends Activity implements View.OnClickListener{

    private String[] mItem = {"one", "two", "three"};
    private Context mContext;
    private Button mSampleAlert;
    private Button mAlertOkandCancel;
    private Button mDialogSingleChoice;
    private Button mDialogSelectDate;

    private MyToast myToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_alert_dialog);
        mContext = SampleAlertDialog.this;
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        mSampleAlert = (Button) findViewById(R.id.sample_alert_button);
        mAlertOkandCancel = (Button) findViewById(R.id.sample_alert_ok_cancel_button);
        mDialogSingleChoice = (Button) findViewById(R.id.dialog_single_choice_button);
        mDialogSelectDate = (Button) findViewById(R.id.dialog_select_date_button);

        myToast = new MyToast();
        mSampleAlert.setOnClickListener(this);
        mAlertOkandCancel.setOnClickListener(this);
        mDialogSingleChoice.setOnClickListener(this);
        mDialogSelectDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sample_alert_button:
                MyDialog.alertDialog(mContext, "Sample alert", new MyDialog.OnDialogClickOkListener() {
                    @Override
                    public void onClickOk(DialogInterface dialog) {

                    }
                });
                break;

            case R.id.sample_alert_ok_cancel_button:
                MyDialog.alertDialogWithOkandCancel(mContext, "Dialog ok and cancel", new MyDialog.OnDialogClickListener() {
                    @Override
                    public void onClickOk(DialogInterface dialog) {

                    }

                    @Override
                    public void onClickCancel(DialogInterface dialog) {

                    }
                });
                break;

            case R.id.dialog_single_choice_button:
                MyDialog.dialogSingleChoice(mContext, "Dialog single choice", mItem, new MyDialog.OnDialogClickItemListener() {
                    @Override
                    public void onSelectItem(DialogInterface dialog, int item) {
                        myToast.showToast(mContext, mItem[item]);
                    }
                });
                break;

            case R.id.dialog_select_date_button:
                MyDialog.dialogSelectDate(mContext, new MyDialog.OnDialogSelectDateListener() {
                    @Override
                    public void onSelectDate(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        myToast.showToast(mContext, selectedDay + "/" + selectedMonth + "/" + selectedYear);
                    }
                });
                break;

                default:
                    break;
        }
    }
}
