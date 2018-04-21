package com.example.harry.mycamerapra.CamerasToolKit;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Harry on 2018/4/21.
 */

public class CamerasToolKitPageNavigation {

    public static final int OPEN_SYS_CAMERAS_PAGE = 1;

    public static void OpenPage(Context mContext , int whichView){
        switch (whichView){
            case OPEN_SYS_CAMERAS_PAGE:
                mContext.startActivity(new Intent(mContext,OpenSystemCameraActivity.class));
                break;
            default:
                break;
        }
    }
}
