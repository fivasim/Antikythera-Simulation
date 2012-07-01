package com.fivasim.antikythera;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class main extends Activity  {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        loadPreferences();
        
        Button price = (Button) findViewById(R.id.Button01);
        price.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if( Build.VERSION.SDK_INT >= 7 ){
	                Intent myIntent = new Intent(view.getContext(), antikytherap.class);
	                startActivityForResult(myIntent, 0);
            	} else {
            		Intent myIntent = new Intent(view.getContext(), antikytherap_nomulti.class);
	                startActivityForResult(myIntent, 0);
            	}
            }
        });
        
        Button amrp = (Button) findViewById(R.id.Button02);
        amrp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if( Build.VERSION.SDK_INT >= 7 ){
	                Intent myIntent = new Intent(view.getContext(), antikythera2.class);
	                startActivityForResult(myIntent, 0);
            	} else {
            		Intent myIntent = new Intent(view.getContext(), antikythera2_nomulti.class);
	                startActivityForResult(myIntent, 0);
            	}
            }
        });
        
        Button tests = (Button) findViewById(R.id.Button03);
        tests.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Preferences.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button help = (Button) findViewById(R.id.Button04);
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), help.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button about = (Button) findViewById(R.id.Button05);
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), About.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
    
    private void loadPreferences() {
    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
	    Preferences.plate_visibility = sp.getBoolean("plate_visibility",false);
	    Preferences.rotation_speed = Float.parseFloat( sp.getString("rotation_speed","1") );
	    Preferences.rotate_backwards = sp.getBoolean("rotate_backwards",false);
	    Preferences.trackball_rotate = sp.getBoolean("trackball_rotate",false);
	    Preferences.show_fps = sp.getBoolean("show_fps",false);
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
