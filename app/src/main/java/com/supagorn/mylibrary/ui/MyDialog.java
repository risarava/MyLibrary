package com.supagorn.mylibrary.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by iabellah on 2016-10-10.
 */

public class MyDialog {
    public static void alertDialog(Context context, String title, final OnDialogClickOkListener onDialogClickOkListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onDialogClickOkListener.onClickOk(dialog);
            }
        });
        builder.show();
    }

    public static void alertDialogWithOkandCancel(Context context, String title, final OnDialogClickListener onDialogClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title);
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onDialogClickListener.onClickOk(dialog);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onDialogClickListener.onClickCancel(dialog);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void dialogSingleChoice(Context context, String title, String[] item,
                                     final OnDialogClickItemListener onDialogClickItemListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(item, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                onDialogClickItemListener.onSelectItem(dialog, item);
                //genderEditText.setText(gender[item]);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void dialogSelectDate(Context context, final OnDialogSelectDateListener onDialogSelectDateListener) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault()); // Get current date
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                onDialogSelectDateListener.onSelectDate(datePicker, selectedYear, selectedMonth,
                        selectedDay);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // Ok and Cancel button listener
    public interface OnDialogClickListener {
        void onClickOk(DialogInterface dialog);

        void onClickCancel(DialogInterface dialog);
    }

    // Ok button listener
    public interface OnDialogClickOkListener {
        void onClickOk(DialogInterface dialog);
    }

    // select item listener
    public interface OnDialogClickItemListener {
        void onSelectItem(DialogInterface dialog, int item);
    }

    // select date listener
    public interface OnDialogSelectDateListener {
        void onSelectDate(DatePicker datePicker, int selectedYear,
                          int selectedMonth, int selectedDay);
    }

}
