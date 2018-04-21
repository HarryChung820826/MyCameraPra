package com.example.harry.mycamerapra;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.harry.mycamerapra.CamerasToolKit.CamerasToolKitPageNavigation;

public class MainActivity extends Activity {

    private Button openSysCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openSysCamera = ((Button) findViewById(R.id.openSysCamera));
        openSysCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CamerasToolKitPageNavigation.OpenPage(MainActivity.this,CamerasToolKitPageNavigation.OPEN_SYS_CAMERAS_PAGE);

            }
        });
    }
}
