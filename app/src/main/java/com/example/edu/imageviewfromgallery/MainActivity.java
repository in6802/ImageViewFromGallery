package com.example.edu.imageviewfromgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int LOAD_IMAGE = 101;
    Button buttonImageGallery;
    ImageView imageViewFromGallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // design 연결
        buttonImageGallery = findViewById(R.id.buttonImageGallery);
        buttonImageGallery.setOnClickListener(this);
        imageViewFromGallery = findViewById(R.id.imageViewFromGallery);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        //ContentProvider 호출
        startActivityForResult(intent, LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(data != null){
            Uri selectedImage = data.getData();
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(selectedImage);// 받은 정보를 담음

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//파일 형식을 맞춰줌

                imageViewFromGallery.setImageBitmap(bitmap);//파일 뿌려줌
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
