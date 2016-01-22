package com.koko.setscreenoff;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by kokox on 1/22/16.
 */
public class Permission {

    private static final int PERMISSION_REQUEST_WRITE_SETTINGS = 0;

    public void check(Context context, Activity activity) {
        boolean hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS)
                == PackageManager.PERMISSION_GRANTED;
        if(!hasPermission) {
            if(!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_SETTINGS)) {
                request(context, activity);
            }
        }
    }

    public void request(Context context, final Activity activity) {
//        // Permission has not been granted and must be requested.
//        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                Manifest.permission.WRITE_SETTINGS)) {
//            // Provide an additional rationale to the user if the permission was not granted
//            // and the user would benefit from additional context for the use of the permission.
//            // Display a SnackBar with a button to request the missing permission.
//            Snackbar.make(activity, "Camera access is required to display the camera preview.",
//                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Request the permission
//                    ActivityCompat.requestPermissions(activity,
//                            new String[]{Manifest.permission.CAMERA},
//                            PERMISSION_REQUEST_WRITE_SETTINGS);
//                }
//            }).show();
//
//        } else {
//            Snackbar.make(activity,
//                    "Permission is not available. Requesting camera permission.",
//                    Snackbar.LENGTH_SHORT).show();
//            // Request the permission. The result will be received in onRequestPermissionResult().
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_SETTINGS},
//                    PERMISSION_REQUEST_WRITE_SETTINGS);
//        }
    }
}
