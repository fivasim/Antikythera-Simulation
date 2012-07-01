package com.fivasim.antikythera;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.KeyEvent;

public class Preferences extends PreferenceActivity {
	public static float rotation_speed;
	public static boolean plate_visibility,rotate_backwards,hidemoverotate,trackball_rotate,show_fps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storePreferences();
        addPreferencesFromResource(R.xml.preferences);
    }
    
    public void storePreferences() { 
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
	    SharedPreferences.Editor editor = sp.edit();
	    editor.putBoolean("trackball_rotate", trackball_rotate);
	    editor.commit();
	}
    
    public void updatePreferences() { 
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
	    plate_visibility = sp.getBoolean("plate_visibility",false);
	    rotation_speed = Float.parseFloat( sp.getString("rotation_speed","1") );
	    rotate_backwards = sp.getBoolean("rotate_backwards",false);
	    trackball_rotate = sp.getBoolean("trackball_rotate",false);
	    hidemoverotate = sp.getBoolean("hidemoverotate",false);
	    show_fps = sp.getBoolean("show_fps",false);
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
    	if ( keyCode == KeyEvent.KEYCODE_BACK ) {
    		updatePreferences();
    		finish();
        }
    	return true;
    }
}
