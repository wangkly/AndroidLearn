package com.wangky.scrollleran;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {


    private static  final int  TAKEPICTUREREQUESTCODE=1;


    private static final int SELECTALBUMREQUESTCODE =2;


    private Button camera;

    private Button album;

    private ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);


        camera = findViewById(R.id.camera);

        album = findViewById(R.id.album);

        mImage = findViewById(R.id.mImage);

        camera.setOnClickListener(this);

        album.setOnClickListener(this);


        if(ContextCompat.checkSelfPermission(AlbumActivity.this,Manifest.permission.CAMERA) != PermissionChecker.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(AlbumActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }



    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.camera:

                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(takePicture.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(takePicture,TAKEPICTUREREQUESTCODE);
                }

                break;

            case R.id.album:

                Intent albumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(albumIntent,SELECTALBUMREQUESTCODE);

                break;

            default:



              break;


        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case TAKEPICTUREREQUESTCODE:

                if(resultCode == RESULT_OK){

                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");

                    mImage.setImageBitmap(bitmap);

                }
                break;


            case SELECTALBUMREQUESTCODE:

                if(resultCode == RESULT_OK){

                    Uri uri = data.getData();

                    String[] filePathColumn={MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri,filePathColumn,null,null,null);

                    cursor.moveToFirst();

                    String imgPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(imgPath);


                    mImage.setImageBitmap(bitmap);

                }


                break;




        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1 && grantResults[0] == PermissionChecker.PERMISSION_GRANTED){

            Toast.makeText(AlbumActivity.this,"permission granted",Toast.LENGTH_LONG).show();

        }


    }
}
