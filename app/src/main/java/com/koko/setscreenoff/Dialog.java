package com.koko.setscreenoff;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.TabHost;
import android.widget.Toast;

import eu.chainfire.libsuperuser.Shell;

/**
 * Created by kokox on 1/22/16.
 */
public class Dialog {

    public void showDialog(final Context context)
    {
        new AlertDialog.Builder(context)
                .setSingleChoiceItems(R.array.dialog, 0, null)
                .setPositiveButton(R.string.ok_button_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button
                        setScreenTimeOff(context, selectedPosition);
                    }
                })
                .show();
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
