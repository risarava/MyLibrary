package com.supagorn.mylibrary.model;

import android.app.Activity;
import android.net.Uri;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by iabellah on 2016-10-11.
 */

public class FacebookManager {
    /* use Profile to get profile facebook */
    public static void sharedFacebook(Activity activity, String title, String detail, String imageUrl) {
        ShareDialog shareDialog = new ShareDialog(activity);
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle(title)
                .setContentDescription(title + "\n" + detail)
                .setContentUrl(Uri.parse("http://www.google.com/"))
                .setImageUrl(Uri.parse(imageUrl))
                .build();
        shareDialog.show(linkContent);
    }

    public static CallbackManager initFacebookLogin(LoginButton facebookButton, final OnCallbackFacebook onCallbackFacebook) {
        CallbackManager callbackFacebookManager = CallbackManager.Factory.create();
        facebookButton.setReadPermissions("email");
        // Callback registration
        facebookButton.registerCallback(callbackFacebookManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                onCallbackFacebook.onSuccess(loginResult);
            }

            @Override
            public void onCancel() {
                onCallbackFacebook.onCancel();
            }

            @Override
            public void onError(FacebookException exception) {
                onCallbackFacebook.onError(exception);
            }
        });
        return callbackFacebookManager;
    }

    public static boolean isLoginFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void loginFacebook(LoginButton loginButton) {
        loginButton.performClick();
    }

    public static void logoutFacebook() {
        LoginManager.getInstance().logOut();
    }

    // listener
    public interface OnCallbackFacebook {
        void onSuccess(LoginResult loginResult);

        void onCancel();

        void onError(FacebookException exception);
    }
}
