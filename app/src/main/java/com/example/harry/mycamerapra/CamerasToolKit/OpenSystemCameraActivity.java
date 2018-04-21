package com.example.harry.mycamerapra.CamerasToolKit;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.harry.mycamerapra.R;

import java.io.FileNotFoundException;

public class OpenSystemCameraActivity extends Activity {

    private final int OPEN_CAMERAS_REQUEST = 100;
    private DisplayMetrics mPhone;
    private Button openSysCamera;
    private ImageView showImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_system_camera);
        init();
    }

    private void init(){

        //讀取手機解析度
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);

        
        openSysCamera = ((Button) findViewById(R.id.openSysCamera));
        showImg = ((ImageView) findViewById(R.id.showImg));

        openSysCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSysCamera();
            }
        });
    }

    private void OpenSysCamera(){
//        ContentValues value = new ContentValues();
//        value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        Uri uri= getContentResolver().insert(EXTERNAL_CONTENT_URI,
//                value);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
//        Log.d("camerasLog" ,"Before Path " + uri.getPath());
        startActivityForResult(intent, OPEN_CAMERAS_REQUEST);
    }


    private void ScalePic(Bitmap bitmap,int phone)
    {
        //縮放比例預設為1
        float mScale = 2 ;

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if(bitmap.getWidth() > phone )
        {
            //判斷縮放比例
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap,
                    0,
                    0,
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    mMat,
                    false);
            showImg.setImageBitmap(mScaleBitmap);
        }
        else showImg.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAMERAS_REQUEST && data != null) {
            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            Log.d("camerasLog" ,"After Path " + uri.getPath());
            try
            {
                //讀取照片，型態為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放
                if(bitmap.getWidth()>bitmap.getHeight())ScalePic(bitmap,
                        mPhone.heightPixels);
                else ScalePic(bitmap,mPhone.widthPixels);
            }
            catch (FileNotFoundException e)
            {
                Log.d("camerasLog" , e.getMessage());
            }
        }
    }


}
