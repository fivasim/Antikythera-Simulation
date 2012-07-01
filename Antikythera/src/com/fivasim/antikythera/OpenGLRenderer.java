package com.fivasim.antikythera;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build;

import static com.fivasim.antikythera.Initial.*;

public class OpenGLRenderer implements Renderer {
	private Gear gears[] = new Gear[num_gears];
	private Gear axles[] = new Gear[num_axles];
	private Pointer pointers[] = new Pointer[num_pointers];
	private Gear pointerbase = new Gear( new float[]{0f, 1.5f, 1f, 50f, 0f, 0f} );
	private Plate plates[] = new Plate[2];
	
	private static int framecount = 0;
	public static float curX = 0f;
	public static float curY = 0f;
	public static float curX1 = 0f;
	public static float curY1 = 0f;
	public static float curDist = 0f;
	public static int touchmode = 0;
	
	public static float fullrotate_x = 0f;
	public static float fullrotate_y = 0f;
	public static float fullrotate_z = 0f;
	public static float position_x = 0f;
	public static float position_y = 0f;
	public static float zoomfac = 0f;
	
	public static long timestamp = System.currentTimeMillis();
	
	private Bitmap bitmap;
	
	public OpenGLRenderer() {
		int i;
		// Initialize our gears. 
		for (i=0;i<num_gears;i++) {
			gears[i] = new Gear(geardata[i]);
		}
		for (i=0;i<num_axles;i++) {
			axles[i] = new Gear(axledata[i]);
		}
		for (i=0;i<num_pointers;i++) {
			pointers[i] = new Pointer( pointer_len[i], pointer_pos[i]);
		}
		plates[0] = new Plate(60.0f, 40.0f, 25.0f);
		plates[1] = new Plate(60.0f, 40.0f,-41.0f);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
	 * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		//gl.glEnable(GL10.GL_DITHER);
	}
	
	public void onDrawFrame(GL10 gl) {
		if( Build.VERSION.SDK_INT >= 7 ){
			framecount++;
			if (framecount == 10) {
				antikytherap.fps = (float)(framecount*1000)/(float)(System.currentTimeMillis()-timestamp);
				timestamp = System.currentTimeMillis();
				framecount = 0;
			} else if (antikytherap.fps == 0f) {
				if (timestamp == 0) {
					timestamp = System.currentTimeMillis();
				} else {
					framecount = 0;
					antikytherap.fps = (float)(1000f/(float)(System.currentTimeMillis()-timestamp) );
					timestamp = System.currentTimeMillis();
				}				 
			}
		} else {
			framecount++;
			if (framecount == 10) {
				antikytherap_nomulti.fps = (float)(framecount*1000)/(float)(System.currentTimeMillis()-timestamp);
				timestamp = System.currentTimeMillis();
				framecount = 0;
			} else if (antikytherap.fps == 0f) {
				if (timestamp == 0) {
					timestamp = System.currentTimeMillis();
				} else {
					framecount = 0;
					antikytherap_nomulti.fps = (float)(1000f/(float)(System.currentTimeMillis()-timestamp) );
					timestamp = System.currentTimeMillis();
				}
			}
		} 

		gl.glDisable(GL10.GL_DITHER);
		gl.glDisable(GL10.GL_LIGHTING);
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();
		// Translates 4 units into the screen.
		if (position_x > 100f) { position_x = 100f;}
		if (position_x <-100f) { position_x =-100f;}
		if (position_y > 100f) { position_y = 100f;}
		if (position_y <-100f) { position_y =-100f;}
		gl.glTranslatef(position_x, position_y, -120f + zoomfac);
		
		gl.glRotatef( fullrotate_x, 1f, 0f, 0f);
		gl.glRotatef( fullrotate_y, 0f, 1f, 0f);
		gl.glRotatef( fullrotate_z, 0f, 0f, 1f);
		// Draw our gears
		int i;
		for (i=0;i<num_gears;i++) {
			gl.glPushMatrix();
			if(differential[i]!=0) { //Αν το γρανάζι είναι ένα από τα διαφορικά του μηχανισμού Περιστροφή διαφορικού γραναζιού
				gl.glTranslatef( gearpos[10][0], gearpos[10][1], 0.0f);	//Κέντρο κύκλου περιστροφής	
				if (Preferences.rotate_backwards) { 
					differential_angle[i] -= Preferences.rotation_speed * differential[i];
				} else {
					differential_angle[i] += Preferences.rotation_speed * differential[i];
				}	
				gl.glRotatef( differential_angle[i] , 0.0f, 0.0f, 1.0f);
			}
			gl.glTranslatef(gearpos[i][0],gearpos[i][1], gearpos[i][2]); //Κέντρο γραναζιού	
			if(i==num_gears-1) { //Αν το γρανάζι είναι η μανιβέλα να πάρει σωστή θέση (κάθετο στα υπόλοιπα)
				gl.glRotatef( 90f, 0.0f, 1.0f, 0.0f);
			}
			
			if (Preferences.rotate_backwards) { 
				startangle[i] -= Preferences.rotation_speed * gearpos[i][3];
			} else {
				startangle[i] += Preferences.rotation_speed * gearpos[i][3];
			}			
			gl.glRotatef( startangle[i], 0f, 0f, 1f); //Περιστροφή γραναζιού		
			
			gears[i].draw(gl, (int)gearpos[i][4]);
			gl.glPopMatrix();
		}
		//axles
		for (i=0;i<num_axles;i++) {
			gl.glPushMatrix();
			if(axle_differential[i]!=0) { //Αν ανήκει στα διαφορικά γρανάζια του μηχανισμού Περιστροφή διαφορικού
				if(i==num_axles-1) {//Αν είναι το χερούλι της μανιβέλας
					gl.glTranslatef( axlepos[i-1][0], axlepos[i-1][1], axlepos[i-1][2]);	//Κέντρο κύκλου περιστροφής	
					if (Preferences.rotate_backwards) { 
						axle_differential_angle[i] -= Preferences.rotation_speed * axle_differential[i];
					} else {
						axle_differential_angle[i] += Preferences.rotation_speed * axle_differential[i];
					}	
					gl.glRotatef( axle_differential_angle[i], 1.0f, 0.0f, 0.0f);
				} else { //Οποιόσδήποτε άλλος άξονας γραναζιού
					gl.glTranslatef( gearpos[10][0], gearpos[10][1], 0.0f);	   //Κέντρο κύκλου περιστροφής
					if (Preferences.rotate_backwards) { 
						axle_differential_angle[i] -= Preferences.rotation_speed * axle_differential[i];
					} else {
						axle_differential_angle[i] += Preferences.rotation_speed * axle_differential[i];
					}	
					gl.glRotatef( axle_differential_angle[i], 0.0f, 0.0f, 1.0f);
				}
			}
			gl.glTranslatef(axlepos[i][0],axlepos[i][1], axlepos[i][2]); //Κέντρο γραναζιού	
			if(i>=num_axles-3) { //Αν είναι η μανιβέλα να πάρει σωστή θέση (κάθετο στα υπόλοιπα)
				gl.glRotatef( 90f, 0.0f, 1.0f, 0.0f);
			}	
			
			if (Preferences.rotate_backwards) { 
				axle_angle[i] -= Preferences.rotation_speed * axlepos[i][3];
			} else {
				axle_angle[i] += Preferences.rotation_speed * axlepos[i][3];
			}			
			gl.glRotatef( axle_angle[i], 0f, 0f, 1f); //Περιστροφή γραναζιού		
			
			axles[i].draw(gl, (int)axlepos[i][4]);
			
			gl.glPopMatrix();
		}
		//pointers
		for (i=0;i<num_pointers;i++) {
			gl.glPushMatrix();
			gl.glTranslatef( pointer_pos[i][0], pointer_pos[i][1], pointer_pos[i][2]);	//Κέντρο δείκτη	
			//Περιστροφή δείκτη γύρω απ' τον άξονά του. Ο συντελεστής του angle είναι η ταχύτητα περιστροφής
			if (Preferences.rotate_backwards) { 
				pointer_angle[i] -= Preferences.rotation_speed * pointer_pos[i][3];
			} else {
				pointer_angle[i] += Preferences.rotation_speed * pointer_pos[i][3];
			}
			gl.glRotatef(pointer_angle[i], 0.0f, 0.0f, 1.0f);	
			
			pointers[i].draw(gl, (int)pointer_pos[i][4]);
			pointerbase.draw(gl, (int)pointer_pos[i][4]);
			gl.glPopMatrix();
		}
		//plates
		if (Preferences.plate_visibility) {
			for (i=0;i<2;i++) {
				gl.glPushMatrix();
				plates[i].draw(gl);
				gl.glPopMatrix();
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if (Preferences.plate_visibility) {
			if( Build.VERSION.SDK_INT >= 7 ){
				bitmap = antikytherap.bitmap;
			} else {
				bitmap = antikytherap_nomulti.bitmap;
			}
			
			gl.glEnable(GL10.GL_TEXTURE_2D);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
	        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
	        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	        gl.glDisable(GL10.GL_TEXTURE_2D);
		} 
        // Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 75.0f, (float) width / (float) height, 0.1f, 750.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
	}
	
}
