package com.koko.setscreenoff;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

/**
 * Created by kokox on 1/22/16.
 */
public class Dialog {

    public void showDialog(final Context context)
    {
        List<String> getCurrentTime = Shell.SU.run("settings get system screen_off_timeout");
        int getCurrentPositionTime = getScreenTimeOff(new Integer(getCurrentTime.get(0)));
        new AlertDialog.Builder(context)
                .setSingleChoiceItems(R.array.dialog, getCurrentPositionTime, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        setScreenTimeOff(context, selectedPosition);
                        dialog.cancel();
                    }
                })
                .setNegativeButton(R.string.cancel_button_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    protected int getScreenTimeOff(int time) {
        int position = 0;
        switch (time) {
            case 15000:
                position = 0;
                break;
            case 30000:
                position = 1;
                break;
            case 60000:
                position = 2;
                break;
            case 120000:
                position = 3;
                break;
            case 300000:
                position = 4;
                break;
            case 600000:
                position = 5;
                break;
            case 1800000:
                position = 6;
                break;
        }
        return position;
    }

    protected void setScreenTimeOff(Context context, int selectedPosition) {
        int time = 0;
        switch (selectedPosition) {
            case 0:
                time = 15000;
                break;
            case 1:
                time = 30000;
                break;
            case 2:
                time = 60000;
                break;
            case 3:
                time = 120000;
                break;
            case 4:
                time = 300000;
                break;
            case 5:
                time = 600000;
                break;
            case 6:
                time = 1800000;
                break;
        }
//            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time);
        Shell.SU.run("settings put system screen_off_timeout " + time);
        Toast.makeText(context, "Set Screen Time Off " + context.getResources().getStringArray(R.array.dialog)[selectedPosition]
                , Toast.LENGTH_LONG);
    }
}
