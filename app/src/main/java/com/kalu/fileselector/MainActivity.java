package com.kalu.fileselector;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import lib.kalu.fileselector.Selector;
import lib.kalu.fileselector.imageload.GlideImageload;
import lib.kalu.fileselector.mimetype.SelectorMimeType;
import lib.kalu.fileselector.model.CaptureModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 选择图片
        findViewById(R.id.selector1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Selector.with(MainActivity.this)
                        .setSelectorType(SelectorMimeType.ofAll(), false)
                        .setImageOriginalEnable(true)
                        .setCaptureEnable(true)
                        .setCaptureFileProvider(
                                new CaptureModel(false, getPackageName() + ".fileprovider", "test"))
                        .setSelectMax(1)
                        .setImageOriginalMaxSizeMb(10)
                        .setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .setThumbnailScale(0.85f)
                        .setImageload(new GlideImageload())
                        .setPreviewSingleType(true)
                        .setImageOriginalEnable(true)
                        .setAutoHideToolbarOnSingleTap(true)
                        .startActivityForResult(1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {

            List<String> list = Selector.obtainPathResult(data);
            if (null == list || list.size() != 1)
                return;

            // toast
            Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}