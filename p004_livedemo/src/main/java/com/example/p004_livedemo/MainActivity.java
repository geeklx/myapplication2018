package com.example.p004_livedemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    public static String rtmpUrl = "rtmp://send1a.douyu.com/live" + "/"+
            "1314026rHKjGGNdi?wsSecret=f3790c788f85dac6f8d789614434f01b&wsTime=5aa23761&wsSeek=off&wm=0&tw=0";
    private EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edt1);
        edt1.setText(rtmpUrl);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO},
                    REQUEST_CODE_TAKE_PHOTO);
        } else {

        }
    }

    public void tv_click1(View view){
        Intent intent = new Intent("android.intent.action.LiveActivity_LandScape");
        intent.putExtra("rtmpUrl",edt1.getText().toString().trim());
        startActivity(intent);
    }

    public void tv_click2(View view){
        Intent intent = new Intent("android.intent.action.LiveActivity_Portrait");
        intent.putExtra("rtmpUrl",edt1.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }

    }


}
