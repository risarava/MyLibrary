package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.manager.MailManager;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

/**
 * Created by iabellah on 2016-10-13.
 */

public class SendEmailActivity extends Activity implements View.OnClickListener {
    private String emailTo;

    private Button sendMailButton;
    private EditText mailToEditText;
    private ProgressBar progressBar;
    private AsyncTask mAsyncTaskSendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        sendMailButton = (Button) findViewById(R.id.send_email_button);
        mailToEditText = (EditText) findViewById(R.id.email_edittext);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        sendMailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_email_button:
                emailTo = mailToEditText.getText().toString().trim();
                if (validateEmail(emailTo, mailToEditText)) {
                   beginSendMail(emailTo);
                }
                break;
        }
    }

    private void beginSendMail(final String mailTo) { // send email to customer
        if (mAsyncTaskSendMail != null) {
            mAsyncTaskSendMail.cancel(true); //cancel old request before request again
        }
        mAsyncTaskSendMail = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... params) {
                MailManager.sendEmailTo(MailManager.MAIL_FROM, mailTo,
                        "subject title", "description send from myLibrary");
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private boolean validateEmail(String emailTo, EditText mailToEditText) {
        if (!emailTo.isEmpty()) {
            if (emailTo.matches(MailManager.EMAIL_PATTERN_DOT_COM) ||
                    emailTo.matches(MailManager.EMAIL_PATTERN_DOT_CO_DOT_TH)) { //check email Pattern
                return true;
            } else {
                mailToEditText.requestFocus();
                mailToEditText.setError(getString(R.string.email_wrong));
                return false;
            }
        } else {
            mailToEditText.requestFocus();
            mailToEditText.setError(getString(R.string.email_wrong));
            return false;
        }
    }
}
