package com.example.p016_glide37_quan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void TV1(View view) {
        startActivity(new Intent("hs.ac.github.DemoGlide38MainActivity"));
    }

    public void TV2(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "1*1");
        intent.putExtra("imageIndex", 1);
        startActivity(intent);
    }

    public void TV3(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "1*2");
        intent.putExtra("imageIndex", 2);
        startActivity(intent);
    }

    public void TV4(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "1*3");
        intent.putExtra("imageIndex", 3);
        startActivity(intent);
    }

    public void TV5(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "2*2");
        intent.putExtra("imageIndex", 4);
        startActivity(intent);
    }

    public void TV6(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "2*3");
        intent.putExtra("imageIndex", 5);
        startActivity(intent);
    }

    public void TV61(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "2*3");
        intent.putExtra("imageIndex", 6);
        startActivity(intent);
    }

    public void TV7(View view) {
        Intent intent = new Intent("hs.ac.github.DemoGlide38BaseMainActivity");
        intent.putExtra("title", "3*3");
        intent.putExtra("imageIndex", 0);
        startActivity(intent);
    }
}
