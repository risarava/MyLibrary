package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.model.Gitmodel;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by iabellah on 2016-10-13.
 */

public class RetrofitActivity extends Activity {

    private TextView tv;
    private ProgressBar pbar;
    private static final String API = "https://api.github.com";   //BASE URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());

        tv = (TextView) findViewById(R.id.tv);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);


        String user = "basil2style";
        pbar.setVisibility(View.VISIBLE);

        //Retrofit section start from here...
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();            //create an adapter for retrofit with base url

        gitapi git = restAdapter.create(gitapi.class); //creating a service for adapter with our GET class

        //Now ,we need to call for response
        //Retrofit using gson for JSON-POJO conversion

        git.getFeed(user, new Callback<Gitmodel>() {
            @Override
            public void success(Gitmodel Gitmodel, Response response) {
                //we get json object from github server to our POJO or model class

                tv.setText("Github Name :" + Gitmodel.getName() +
                        "\nWebsite :" + Gitmodel.getBlog() +
                        "\nCompany Name :" + Gitmodel.getCompany() +
                        "\nLocation :" + Gitmodel.getLocation() +
                        "\nEmail :" + Gitmodel.getEmail());

                pbar.setVisibility(View.INVISIBLE);  //disable progressbar
            }

            @Override
            public void failure(RetrofitError error) {
                tv.setText(error.getMessage());
                pbar.setVisibility(View.INVISIBLE);  //disable progressbar
            }
        });
    }

    public interface gitapi {
        @GET("/users/{user}")      //here is the other url part.best way is to start using /
        //string user is for passing values from edittext for eg: user=basil2style,google
        void getFeed(@Path("user") String user, Callback<Gitmodel> response);
        //response is the response from the server which is now in the POJO
    }
}
