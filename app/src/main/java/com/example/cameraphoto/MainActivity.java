package com.example.cameraphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    ImageView mImage;
    Uri mUri;
    Context mContext;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImage.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mButton = (Button) findViewById(R.id.button);
        mImage = (ImageView) findViewById(R.id.imageView);

        
        ActivityManager actvityManager = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

                for(ActivityManager.RunningAppProcessInfo runningProInfo:procInfos){

                    Log.d("Running Processes", "()()"+runningProInfo.processName);
                }
                /*
                File path = new File (Environment.getExternalStorageDirectory(), "CamTest");
                if (!path.exists()) {
                    path.mkdirs();
                }
                File file = new File(path.getPath() + File.separator +
                        System.currentTimeMillis() + ".png");
                mUri = Uri.fromFile(file);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                 */
                dispatchTakePictureIntent();
            }
        });

    }
}