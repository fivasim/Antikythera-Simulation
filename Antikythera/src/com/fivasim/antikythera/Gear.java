package com.fivasim.antikythera;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.fivasim.antikythera.Initial.color;
import static com.fivasim.antikythera.Initial.M_PI;

public class Gear {
	private float frontface_v[];
	private short frontface_i[];
	private float rearface_v[];
	private short rearface_i[];
	private float frontgear_v[];
	private short frontgear_i[];
	private float reargear_v[];
	private short reargear_i[];
	private float uppergear_v[];
	private short uppergear_i[];
	private float innergear_v[];
	private short innergear_i[];
	private float cross_v[];
	private short cross_i[];
	private FloatBuffer frontface_vertexBuffer;
	private ShortBuffer frontface_indexBuffer;
	private FloatBuffer rearface_vertexBuffer;
	private ShortBuffer rearface_indexBuffer;
	private FloatBuffer frontgear_vertexBuffer;
	private ShortBuffer frontgear_indexBuffer;
	private FloatBuffer reargear_vertexBuffer;
	private ShortBuffer reargear_indexBuffer;
	private FloatBuffer uppergear_vertexBuffer;
	private ShortBuffer uppergear_indexBuffer;
	private FloatBuffer innergear_vertexBuffer;
	private ShortBuffer innergear_indexBuffer;
	private FloatBuffer cross_vertexBuffer;
	private ShortBuffer cross_indexBuffer;
	
	public Gear( float[] geardata )
	{
		float inner_radius = geardata[0];
		float outer_radius = geardata[1];
		float width = geardata[2];
		int teeth = (int)geardata[3];
		float tooth_depth = 0f;
		int cross = 0;
		if (geardata.length > 4) {
			tooth_depth = geardata[4];
			cross = (int)geardata[5];
		}
		
		int i;
		float r0, r1, r2;
		float angle, da;
		//float u, v;
		
		frontface_i = new short[teeth+3];
		frontface_v = new float[3*(teeth+3)];
		rearface_i = new short[teeth+3];
		rearface_v = new float[3*(teeth+3)];
		frontgear_i = new short[3*teeth];
		frontgear_v = new float[9*teeth];
		reargear_i = new short[3*teeth];
		reargear_v = new float[9*teeth];
		uppergear_i = new short[4*(teeth+1)];
		uppergear_v = new float[12*(teeth+1)];
		innergear_i = new short[2*(teeth+1)];
		innergear_v = new float[6*(teeth+1)];
		cross_i = new short[11*cross];
		cross_v = new float[33*cross];
		
		r0 = inner_radius;
		r1 = outer_radius - tooth_depth / 1.8f;  //ακτίνα δίσκου (χωρίς τα δόντια)
		r2 = outer_radius + tooth_depth / 2.2f;  //ακτίνα μαζί με τα δόντια
		
		da = 2.0f * M_PI / teeth / 4.0f;
		short count = 0;
//κυκλική μπροστινή όψη γραναζιού
		for (i = 0; i <= teeth+2; i++) {
			angle = i * 2.0f * M_PI / teeth;
			if(i%2==0) {
				frontface_v[count] = (float)(r0 * (float)Math.cos(angle)); count++;
				frontface_v[count] = (float)(r0 * (float)Math.sin(angle)); count++;
				frontface_v[count] = (float)(width * 0.5f); count++;
			}else{
				frontface_v[count] = (float)(r1 * (float)Math.cos(angle)); count++;
				frontface_v[count] = (float)(r1 * (float)Math.sin(angle)); count++;
				frontface_v[count] = (float)(width * 0.5f); count++;
			}
			frontface_i[i]=(short)i;
		}
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb1 = ByteBuffer.allocateDirect(frontface_v.length * 4);
		vbb1.order(ByteOrder.nativeOrder());
		frontface_vertexBuffer = vbb1.asFloatBuffer();
		frontface_vertexBuffer.put(frontface_v);
		frontface_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb1 = ByteBuffer.allocateDirect(frontface_i.length * 2);
		ibb1.order(ByteOrder.nativeOrder());
		frontface_indexBuffer = ibb1.asShortBuffer();
		frontface_indexBuffer.put(frontface_i);
		frontface_indexBuffer.position(0);
		
//μπροστινή όψη δοντιών
		count = 0;
		short count_i=0;
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * M_PI / teeth;
			frontgear_v[count] = (float)( (r1-0.1) * Math.cos(angle)); count++;
			frontgear_v[count] = (float)( (r1-0.1) * Math.sin(angle)); count++;
			frontgear_v[count] = (float)(width * 0.5f); count++;
			frontgear_i[count_i]=count_i;count_i++;
			frontgear_v[count] = (float)(r2 * Math.cos(angle + 2 * da)); count++;
			frontgear_v[count] = (float)(r2 * Math.sin(angle + 2 * da)); count++;
			frontgear_v[count] = (float)(width * 0.5f); count++;
			frontgear_i[count_i]=count_i;count_i++;
			frontgear_v[count] = (float)( (r1-0.1) * Math.cos(angle + 4 * da)); count++;
			frontgear_v[count] = (float)( (r1-0.1) * Math.sin(angle + 4 * da)); count++;
			frontgear_v[count] = (float)(width * 0.5f); count++;
			frontgear_i[count_i]=count_i;count_i++;
		}	
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb2 = ByteBuffer.allocateDirect(frontgear_v.length * 4);
		vbb2.order(ByteOrder.nativeOrder());
		frontgear_vertexBuffer = vbb2.asFloatBuffer();
		frontgear_vertexBuffer.put(frontgear_v);
		frontgear_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb2 = ByteBuffer.allocateDirect(frontgear_i.length * 2);
		ibb2.order(ByteOrder.nativeOrder());
		frontgear_indexBuffer = ibb2.asShortBuffer();
		frontgear_indexBuffer.put(frontgear_i);
		frontgear_indexBuffer.position(0);
		
//κυκλική πίσω όψη γραναζιού
		if (teeth == 222 ) {r0 = 30f;}//stupid small depth buffer
		if (teeth == 223 ) {r0 = 22.9f;}//stupid small depth buffer
		count = 0;
		for (i = 0; i <= teeth+2; i++) {
			angle = i * 2.0f * M_PI / teeth;
			if(i%2==0) {
				rearface_v[count] = (float)(r0 * Math.cos(angle)); count++;
				rearface_v[count] = (float)(r0 * Math.sin(angle)); count++;
				rearface_v[count] = (float)(-width * 0.5f); count++;
			}else{
				rearface_v[count] = (float)(r1 * Math.cos(angle)); count++;
				rearface_v[count] = (float)(r1 * Math.sin(angle)); count++;
				rearface_v[count] = (float)(-width * 0.5f); count++;
			}
			rearface_i[i]=(short)i;
		}
		if (teeth == 222 ) {r0 = 12f;}//stupid small depth buffer
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb3 = ByteBuffer.allocateDirect(rearface_v.length * 4);
		vbb3.order(ByteOrder.nativeOrder());
		rearface_vertexBuffer = vbb3.asFloatBuffer();
		rearface_vertexBuffer.put(rearface_v);
		rearface_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb3 = ByteBuffer.allocateDirect(rearface_i.length * 2);
		ibb3.order(ByteOrder.nativeOrder());
		rearface_indexBuffer = ibb3.asShortBuffer();
		rearface_indexBuffer.put(rearface_i);
		rearface_indexBuffer.position(0);
		
//πίσω όψη δοντιών
		count = 0;
		count_i=0;
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * M_PI / teeth;
			reargear_v[count] = (float)( (r1-0.1) * Math.cos(angle)); count++;
			reargear_v[count] = (float)( (r1-0.1) * Math.sin(angle)); count++;
			reargear_v[count] = (float)(-width * 0.5f); count++;
			reargear_i[count_i]=count_i;count_i++;
			reargear_v[count] = (float)(r2 * Math.cos(angle + 2 * da)); count++;
			reargear_v[count] = (float)(r2 * Math.sin(angle + 2 * da)); count++;
			reargear_v[count] = (float)(-width * 0.5f); count++;
			reargear_i[count_i]=count_i;count_i++;
			reargear_v[count] = (float)( (r1-0.1) * Math.cos(angle + 4 * da)); count++;
			reargear_v[count] = (float)( (r1-0.1) * Math.sin(angle + 4 * da)); count++;
			reargear_v[count] = (float)(-width * 0.5f); count++;
			reargear_i[count_i]=count_i;count_i++;
		}	
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb4 = ByteBuffer.allocateDirect(reargear_v.length * 4);
		vbb4.order(ByteOrder.nativeOrder());
		reargear_vertexBuffer = vbb4.asFloatBuffer();
		reargear_vertexBuffer.put(reargear_v);
		reargear_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb4 = ByteBuffer.allocateDirect(reargear_i.length * 2);
		ibb4.order(ByteOrder.nativeOrder());
		reargear_indexBuffer = ibb4.asShortBuffer();
		reargear_indexBuffer.put(reargear_i);
		reargear_indexBuffer.position(0);
	
//εξωτερική όψη δοντιών
		count = 0;
		count_i=0;
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * M_PI / teeth;
			uppergear_v[count] = (float)( (r1-0.1) * Math.cos(angle)); count++;
			uppergear_v[count] = (float)( (r1-0.1) * Math.sin(angle)); count++;
			uppergear_v[count] = (float)(width * 0.5f); count++;
			uppergear_i[count_i]=count_i;count_i++;
			uppergear_v[count] = (float)( (r1-0.1) * Math.cos(angle)); count++;
			uppergear_v[count] = (float)( (r1-0.1) * Math.sin(angle)); count++;
			uppergear_v[count] = (float)(-width * 0.5f); count++;
			uppergear_i[count_i]=count_i;count_i++;
			uppergear_v[count] = (float)(r2 * Math.cos(angle + 2 * da)); count++;
			uppergear_v[count] = (float)(r2 * Math.sin(angle + 2 * da)); count++;
			uppergear_v[count] = (float)(width * 0.5f); count++;
			uppergear_i[count_i]=count_i;count_i++;
			uppergear_v[count] = (float)(r2 * Math.cos(angle + 2 * da)); count++;
			uppergear_v[count] = (float)(r2 * Math.sin(angle + 2 * da)); count++;
			uppergear_v[count] = (float)(-width * 0.5f); count++;
			uppergear_i[count_i]=count_i;count_i++;			
		}
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb5 = ByteBuffer.allocateDirect(uppergear_v.length * 4);
		vbb5.order(ByteOrder.nativeOrder());
		uppergear_vertexBuffer = vbb5.asFloatBuffer();
		uppergear_vertexBuffer.put(uppergear_v);
		uppergear_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb5 = ByteBuffer.allocateDirect(uppergear_i.length * 2);
		ibb5.order(ByteOrder.nativeOrder());
		uppergear_indexBuffer = ibb5.asShortBuffer();
		uppergear_indexBuffer.put(uppergear_i);
		uppergear_indexBuffer.position(0);
		
//εσωτερικός κύλινδρος (τρύπα στο κέντρο)
		count = 0;
		count_i=0;
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * M_PI / teeth;
			innergear_v[count] = (float)(r0 * Math.cos(angle)); count++;
			innergear_v[count] = (float)(r0 * Math.sin(angle)); count++;
			innergear_v[count] = (float)(-width * 0.5f); count++;
			innergear_i[count_i]=count_i;count_i++;
			innergear_v[count] = (float)(r0 * Math.cos(angle)); count++;
			innergear_v[count] = (float)(r0 * Math.sin(angle)); count++;
			innergear_v[count] = (float)(width * 0.5f); count++;
			innergear_i[count_i]=count_i;count_i++;
		}
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb6 = ByteBuffer.allocateDirect(innergear_v.length * 4);
		vbb6.order(ByteOrder.nativeOrder());
		innergear_vertexBuffer = vbb6.asFloatBuffer();
		innergear_vertexBuffer.put(innergear_v);
		innergear_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb6 = ByteBuffer.allocateDirect(innergear_i.length * 2);
		ibb6.order(ByteOrder.nativeOrder());
		innergear_indexBuffer = ibb6.asShortBuffer();
		innergear_indexBuffer.put(innergear_i);
		innergear_indexBuffer.position(0);
		
//Βραχίονες γραναζιού
		count = 0;
		count_i=0;
		if ( (cross>0) & (r0>0.0f) ) {
		 	float length = (r0 + r1)/2;		//Μήκος βραχίονα
			float thicknessy = r0 * 0.2f;		//Πάχος βραχίονα κατά την διάμετρο του γραναζιού
			float thicknessz = width * 0.8f;	//Πάχος βραχίονα κατά το πάχος του γραναζιού
		
			//Βραχίονας δείκτη
			if(cross>=1) {
				//front1
				cross_v[count] = length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				cross_v[count] = -length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				cross_v[count] = length; count++;
				cross_v[count] = (float)( thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//front2
				cross_v[count] = -length; count++;
				cross_v[count] = (float)( thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//up1
				cross_v[count] = length; count++;
				cross_v[count] = (float)( thicknessy/2); count++;
				cross_v[count] = (float)( thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//up2
				cross_v[count] = -length; count++;
				cross_v[count] = (float)( thicknessy/2); count++;
				cross_v[count] = (float)( thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//back1
				cross_v[count] = length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)( thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//back2
				cross_v[count] = -length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)( thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//down1
				cross_v[count] = length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//down2
				cross_v[count] = -length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
				//final bound with beginning
				cross_v[count] = length; count++;
				cross_v[count] = (float)(-thicknessy/2); count++;
				cross_v[count] = (float)(-thicknessz/2); count++;
				cross_i[count_i]=count_i;count_i++;
			}
		}
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb7 = ByteBuffer.allocateDirect(cross_v.length * 4);
		vbb7.order(ByteOrder.nativeOrder());
		cross_vertexBuffer = vbb7.asFloatBuffer();
		cross_vertexBuffer.put(cross_v);
		cross_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb7 = ByteBuffer.allocateDirect(cross_i.length * 2);
		ibb7.order(ByteOrder.nativeOrder());
		cross_indexBuffer = ibb7.asShortBuffer();
		cross_indexBuffer.put(cross_i);
		cross_indexBuffer.position(0);
				
	}
	
	public void draw(GL10 gl, int color_pointer ) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		// Specifies the location and data format of an array of vertex coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, frontface_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, frontface_i.length, GL10.GL_UNSIGNED_SHORT, frontface_indexBuffer);

		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, frontgear_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, frontgear_i.length, GL10.GL_UNSIGNED_SHORT, frontgear_indexBuffer);

		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, uppergear_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, uppergear_i.length, GL10.GL_UNSIGNED_SHORT, uppergear_indexBuffer);
		
		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, innergear_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, innergear_i.length, GL10.GL_UNSIGNED_SHORT, innergear_indexBuffer);

		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cross_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, cross_i.length, GL10.GL_UNSIGNED_SHORT, cross_indexBuffer);
		
		gl.glDisable(GL10.GL_CULL_FACE);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_FRONT);
	
		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rearface_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, rearface_i.length, GL10.GL_UNSIGNED_SHORT, rearface_indexBuffer);

		gl.glColor4f( color[color_pointer][0], color[color_pointer][1], color[color_pointer][2], color[color_pointer][3] );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, reargear_vertexBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, reargear_i.length, GL10.GL_UNSIGNED_SHORT, reargear_indexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
		
	}
	
}
