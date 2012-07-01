package com.fivasim.antikythera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class help extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
    	if ( keyCode == KeyEvent.KEYCODE_BACK ) {
    		finish();
        }
    	if ( keyCode == KeyEvent.KEYCODE_MENU ) {
    		startActivity(new Intent(this, Preferences.class));
        }
        return true;
    }
}
