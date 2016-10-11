package com.supagorn.mylibrary.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

import java.io.IOException;

/**
 * Created by iabellah on 2016-10-10.
 */

public class SelectImageGallery extends Activity implements View.OnClickListener {
    private static int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;

    private Button selectImageButton;
    private ImageView selectImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image_gallery);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        selectImageButton = (Button) findViewById(R.id.select_image_button);
        selectImageView = (ImageView) findViewById(R.id.select_image_imageview);

        selectImageButton.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                selectImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_image_button:
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
        }
    }
}
