package com.example.p001_fitandroid678;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AssetsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assetsmain);
    }

    public void FileAssetsActivity(View view) {
        startActivity(new Intent("hs.act.FileAssetsActivity"));
    }

    public void FitAndroidActivity(View view) {
        startActivity(new Intent("hs.act.FitAndroidActivity"));
    }

}
