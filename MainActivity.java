package com.example.ramesh.camera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE=1;
    ImageView buckysImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buckyButton =(Button) findViewById(R.id.buckysButton);

        buckysImageView = (ImageView) findViewById(R.id.buckysImageView);

        //Disable the Button user has no Camera

        if(!hasCamera())
            buckyButton.setEnabled(false);
    }

    //Check if user has a Camera

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // Launching the Camera

    public void launchCamera(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Take & Picture and pass results along to onActivityResult

        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    // if you want to return the image Taken


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            buckysImageView.setImageBitmap(photo);

        }
    }
}





