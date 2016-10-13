package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.manager.FacebookManager;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

/**
 * Created by iabellah on 2016-10-11.
 */

public class FacebookActivity extends Activity implements View.OnClickListener {

    private LoginButton facebookLoginButton;
    private Button loginButton;
    private Button sharedButton;

    private CallbackManager callbackFacebookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        facebookLoginButton = (LoginButton) findViewById(R.id.facebook_loginbutton);
        loginButton = (Button) findViewById(R.id.facebook_button);
        sharedButton = (Button) findViewById(R.id.shared_button);

        callbackFacebookManager = FacebookManager.initFacebookLogin(facebookLoginButton,
                new FacebookManager.OnCallbackFacebook() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });

        loginButton.setOnClickListener(this);
        sharedButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.facebook_button:
                if (FacebookManager.isLoginFacebook()) {// to logout facebook
                    FacebookManager.logoutFacebook();
                    loginButton.setText(getResources().getString(R.string.com_facebook_loginview_log_in_button_long));
                } else { // to login facebook
                    FacebookManager.loginFacebook(facebookLoginButton);
                    loginButton.setText(getResources().getString(R.string.com_facebook_loginview_log_out_button));
                }
                break;

            case R.id.shared_button:
                FacebookManager.sharedFacebook(this, getResources().getString(R.string.app_name),
                        getResources().getString(R.string.com_facebook_share_button_text),
                        "http://icons.iconarchive.com/icons/rade8/minium-2/256/Folder-Library-icon.png");
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // result facebook
        callbackFacebookManager.onActivityResult(requestCode, resultCode, data);
    }
}
