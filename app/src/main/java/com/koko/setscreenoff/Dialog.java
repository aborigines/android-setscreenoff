package com.koko.setscreenoff;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

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

    protected void setScreenTimeOff(Context context, int selectedPosition)
    {
        int time = 0;
        switch (selectedPosition) {
            case 0 :
                time = 1000;
                break;
        }
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time);
    }
}
