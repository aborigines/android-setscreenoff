package com.koko.setscreenoff;

import android.app.Activity;
import android.os.Bundle;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Permission().check(this, this);
        new Dialog().showDialog(this);
    }
}
