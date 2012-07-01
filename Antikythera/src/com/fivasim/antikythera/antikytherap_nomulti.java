package com.fivasim.antikythera;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.preference.PreferenceManager;

import static com.fivasim.antikythera.OpenGLRenderer.*;

public class antikytherap_nomulti extends Activity {
	public char type = 'p';
	public static TextView tv;
	public static ImageView iv;
	public static float fps;
	public static Bitmap bitmap;
	private Handler mHandler = new Handler();
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inScaled = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boxt, opt);
        
        setContentView(R.layout.gllayout);
        GLSurfaceView view = (GLSurfaceView) findViewById(R.id.glsurfaceview1);
        view.setRenderer(new OpenGLRenderer());
   		
   		tv = (TextView) findViewById(R.id.tv);
   		iv = (ImageView) findViewById(R.id.moveresize);
    }

    @Override
    public void onPause() {
    	super.onPause();
    	mHandler.removeCallbacks(mUpdateTimeTask);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	timestamp = 0;
		fps = 0f;
    }

    @Override
    public void onStart() {
    	super.onStart();
    	loadPreferences();
    	timestamp = 0;
		fps = 0f;
		if (Preferences.hidemoverotate) {iv.setVisibility(View.INVISIBLE);}
		else {iv.setVisibility(View.VISIBLE);}
		if (Preferences.trackball_rotate) {iv.setImageResource(R.drawable.move);} 
		iv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Preferences.trackball_rotate = !Preferences.trackball_rotate;
		    	if (Preferences.trackball_rotate) {
		    		Toast.makeText( getApplicationContext(), "Touch screen to pan", Toast.LENGTH_SHORT).show();
		    		iv.setImageResource(R.drawable.move);
		    	} else {
		    		Toast.makeText( getApplicationContext(), "Touch screen to rotate", Toast.LENGTH_SHORT).show();
		    		iv.setImageResource(R.drawable.rotate);
		    	}
			}
        });
		
		mHandler.removeCallbacks(mUpdateTimeTask);
    	if (Preferences.show_fps) {
    		tv.setVisibility(View.VISIBLE);
		   	mHandler.postDelayed(mUpdateTimeTask, 100);
    	} else {
        	tv.setVisibility(View.INVISIBLE);
        }
    }
    
    private void loadPreferences() {
    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
	    Preferences.plate_visibility = sp.getBoolean("plate_visibility",false);
	    Preferences.rotation_speed = Float.parseFloat( sp.getString("rotation_speed","1") );
	    Preferences.rotate_backwards = sp.getBoolean("rotate_backwards",false);
	    Preferences.trackball_rotate = sp.getBoolean("trackball_rotate",false);
	    Preferences.show_fps = sp.getBoolean("show_fps",false);
	    Preferences.hidemoverotate = sp.getBoolean("hidemoverotate",false);
    }
    
    private Runnable mUpdateTimeTask = new Runnable() {
 	   public void run() {
 		   	if (Preferences.show_fps) {
 			   	tv.setText( String.format("%.2f fps",fps));
 	        } else {
 	        	tv.setVisibility(View.INVISIBLE);
 	        }
 		   	mHandler.postDelayed(this, 400 );
 	   }
 	};
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK ) {
			case MotionEvent.ACTION_MOVE:
				if(touchmode == 1) {
					if (Preferences.trackball_rotate) {
						position_x -= (float)( (curX-event.getX())/10 );
						position_y += (float)( (curY-event.getY())/10 );
						curX = event.getX();
						curY = event.getY();
					} else {
						fullrotate_y -= (float)( (curX-event.getX())/10 );
						fullrotate_x -= (float)( (curY-event.getY())/10 );
						curX = event.getX();
						curY = event.getY();
					}
				} 
				break;
			case MotionEvent.ACTION_DOWN:
				touchmode = 1;
				curX = event.getX();
				curY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				touchmode = 0;
				curX = event.getX();
				curY = event.getY();
			    break;
		}
		return true;
	}
    
    @Override
    public boolean onTrackballEvent(MotionEvent event) {
	    if (event.getAction() == MotionEvent.ACTION_MOVE) {
	    	if (Preferences.trackball_rotate) {
    			fullrotate_y += (float)( (6*event.getX()) );
				fullrotate_x += (float)( (6*event.getY()) );
			} else {
				position_x += (float)( (6*event.getX()) );
				position_y -= (float)( (6*event.getY()) );
			}
	    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
	    	Preferences.trackball_rotate = !Preferences.trackball_rotate;
	    	if (Preferences.trackball_rotate) {
	    		Toast.makeText( getApplicationContext(), "Touch screen to pan", Toast.LENGTH_SHORT).show();
	    	} else {
	    		Toast.makeText( getApplicationContext(), "Touch screen to rotate", Toast.LENGTH_SHORT).show();
	    	}
	    }
    	return true;
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
    	if ( keyCode == KeyEvent.KEYCODE_BACK ) {
    		bitmap.recycle();
    		mHandler.removeCallbacks(mUpdateTimeTask);
    		finish();
        } else if ( keyCode == KeyEvent.KEYCODE_MENU ) {
    		startActivity(new Intent(this, Preferences.class));
        } else if ( keyCode == KeyEvent.KEYCODE_VOLUME_UP ) {
    		zoomfac += 5f;
        } else if ( keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
    		zoomfac -= 5f;
        } else if ( keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_CAMERA ) {
        	Preferences.trackball_rotate = !Preferences.trackball_rotate;
	    	if (Preferences.trackball_rotate) {
	    		Toast.makeText( getApplicationContext(), "Touch screen to pan", Toast.LENGTH_SHORT).show();
	    	} else {
	    		Toast.makeText( getApplicationContext(), "Touch screen to rotate", Toast.LENGTH_SHORT).show();
	    	}
        }
        return true;
    }
}