package com.koko.setscreenoff;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import eu.chainfire.libsuperuser.Shell;

/**
 * Created by kokox on 2/2/16.
 */
public class DialogTask extends AsyncTask<String, Void , Integer> {

    private DialogActivity dialogActivity;
    private ProgressDialog dialogProgress;

    public DialogTask(DialogActivity dialogActivity) {
        this.dialogActivity = dialogActivity;
    }

    @Override
    protected Integer doInBackground(String... params) {
        int getCurrentTime = new Integer(Shell.SU.run("settings get system screen_off_timeout").get(0));
        int getCurrentPositionTime = getScreenTimeOff(getCurrentTime);
        return getCurrentPositionTime;
    }

    @Override
    protected void onPreExecute() {
        dialogProgress = new ProgressDialog(dialogActivity);
        dialogProgress.setMessage("Please wait...");
        dialogProgress.setIndeterminate(true);
        dialogProgress.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(final Integer getCurrentPositionTime) {
        dialogActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(dialogActivity)
                        .setSingleChoiceItems(R.array.dialog, getCurrentPositionTime,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        setScreenTimeOff(which);
                                        dialogActivity.finish();
                                        Toast.makeText(dialogActivity, "Set Screen Off Time " +
                                                        dialogActivity.getResources().getStringArray(R.array.dialog)[which],
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })
                        .setNegativeButton(R.string.cancel_button_label, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialogActivity.finish();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        return;
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

    protected void setScreenTimeOff(int selectedPosition) {
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
        Shell.SU.run("settings put system screen_off_timeout " + time);
    }
}
