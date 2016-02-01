package com.koko.setscreenoff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.activity_main);
        if(checkRoot()) {
        } else {
            Toast.makeText(this, "Can't root", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkRoot()
    {
        Process p;
        try {
            // Preform su to get root privledges
            p = Runtime.getRuntime().exec("su");

            // Attempt to write a file to a root-only
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes("echo \"Do I have root?\" >/system/temporary.txt\n");

            // Close the terminal
            os.writeBytes("exit\n");
            os.flush();
            try {
                p.waitFor();
                if (p.exitValue() != 255) {
                    // TODO Code to run on success
                    return true;
                }
                else {
                    // TODO Code to run on unsuccessful
                    return false;
                }
            } catch (InterruptedException e) {
                // TODO Code to run in interrupted exception
                return false;
            }
        } catch (IOException e) {
            // TODO Code to run in input/output exception
            return false;
        }
    }
    private void start() {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

}
