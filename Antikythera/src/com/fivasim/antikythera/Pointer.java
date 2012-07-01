package com.fivasim.antikythera;

import static com.fivasim.antikythera.Initial.color;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Pointer {
	private float pointer_v[];
	private short pointer_i[];
	private short pointerpin_i[];
	private FloatBuffer pointer_vertexBuffer;
	private ShortBuffer pointer_indexBuffer;
	private ShortBuffer pointerpin_indexBuffer;
	
	public Pointer( float length, float[] pointerpos )
	{
		pointerpin_i = new short[9];
		pointer_i = new short[10];
		pointer_v = new float[33];
		
		short count = 0;
		short count_i = 0;
		if(length>=3f) {
			float width = 2.0f;		//Πλάτος δείκτη
			float thickness = 0.9f;	//Πάχος δείκτη
//Βραχίονας δείκτη
			//front1
			pointer_v[count] = (float)(length-3); count++;
			pointer_v[count] = (float)(-width/2); count++;
			pointer_v[count] = (float)(-thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			pointer_v[count] = 0.0f; count++;
			pointer_v[count] = (float)(-width/2); count++;
			pointer_v[count] = (float)(-thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			pointer_v[count] = (float)(length-3); count++;
			pointer_v[count] = (float)( width/2); count++;
			pointer_v[count] = (float)(-thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//front2
			pointer_v[count] = 0.0f; count++;
			pointer_v[count] = (float)( width/2); count++;
			pointer_v[count] = (float)(-thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//up1
			pointer_v[count] = (float)(length-3); count++;
			pointer_v[count] = (float)( width/2); count++;
			pointer_v[count] = (float)( thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//up2
			pointer_v[count] = 0.0f; count++;
			pointer_v[count] = (float)( width/2); count++;
			pointer_v[count] = (float)( thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//back1
			pointer_v[count] = (float)(length-3); count++;
			pointer_v[count] = (float)(-width/2); count++;			
			pointer_v[count] = (float)( thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//back2
			pointer_v[count] = 0.0f; count++;
			pointer_v[count] = (float)(-width/2); count++;
			pointer_v[count] = (float)( thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//down1
			pointer_v[count] = (float)(length-3); count++;
			pointer_v[count] = (float)(-width/2); count++;
			pointer_v[count] = (float)(-thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//down2
			pointer_v[count] = 0.0f; count++;
			pointer_v[count] = (float)(-width/2); count++;
			pointer_v[count] = (float)(-thickness/2); count++;
			pointer_i[count_i]=count_i;count_i++;
			//final bound to start pointer pin
			short arrow_point = count_i;
			pointer_v[count] = length; count++;
			pointer_v[count] = 0f; count++;
			pointer_v[count] = 0f; count++;	
//Βέλος δείκτη
			count_i = 0;
			pointerpin_i[count_i]=0;count_i++;
			pointerpin_i[count_i]=2;count_i++;
			pointerpin_i[count_i]=arrow_point;count_i++;
			pointerpin_i[count_i]=4;count_i++;	
			pointerpin_i[count_i]=arrow_point;count_i++;	
			pointerpin_i[count_i]=6;count_i++;	
			pointerpin_i[count_i]=arrow_point;count_i++;
			pointerpin_i[count_i]=0;count_i++;	
			pointerpin_i[count_i]=arrow_point;count_i++;
		}
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb1 = ByteBuffer.allocateDirect(pointer_v.length * 4);
		vbb1.order(ByteOrder.nativeOrder());
		pointer_vertexBuffer = vbb1.asFloatBuffer();
		pointer_vertexBuffer.put(pointer_v);
		pointer_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb1 = ByteBuffer.allocateDirect(pointer_i.length * 2);
		ibb1.order(ByteOrder.nativeOrder());
		pointer_indexBuffer = ibb1.asShortBuffer();
		pointer_indexBuffer.put(pointer_i);
		pointer_indexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb2 = ByteBuffer.allocateDirect(pointerpin_i.length * 2);
		ibb2.order(ByteOrder.nativeOrder());
		pointerpin_indexBuffer = ibb2.asShortBuffer();
		pointerpin_indexBuffer.put(pointerpin_i);
		pointerpin_indexBuffer.position(0);
				
	}
	
	public void draw(GL10 gl, int color_pointer ) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glColor4f( color[color_pointer][0], color[color_pointer][1],
				color[color_pointer][2], color[color_pointer][3] );
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, pointer_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, pointer_i.length, GL10.GL_UNSIGNED_SHORT, pointer_indexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, pointerpin_i.length, GL10.GL_UNSIGNED_SHORT, pointerpin_indexBuffer);


		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
		
	}
	
}

