package com.fivasim.antikythera;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Plate {
	private float plate_v[];
	private short tplate_i[];
	private short plate_i[];
	private float ftexture_v[];
	private float texture_v[];
	private ShortBuffer tplate_indexBuffer;
	private FloatBuffer plate_vertexBuffer;
	private ShortBuffer plate_indexBuffer;
	private FloatBuffer ftextureBuffer;
	private FloatBuffer textureBuffer;
	
	public Plate( float width, float length, float z )
	{
		plate_v = new float[]{	-width,  length, z+1,
								 width,  length, z+1,
								 width, -length, z+1,
								-width, -length, z+1,
								-width,  length, z-1,
								 width,  length, z-1,
								 width, -length, z-1,
								-width, -length, z-1
						};
		if(z>0) {
			tplate_i = new short[]{1,0,3,	1,3,2};//front
			plate_i = new short[]{2,6,5,	2,5,1,//right
								7,4,5,		7,5,6,//back
								0,4,7,		0,7,3,//left
								5,4,0,		5,0,1,//top
								3,7,6,		3,6,2 //bottom
					};
			ftexture_v = new float[]{
					0.86f, 1.0f,
					0.125f,1.0f,
					0.125f,0.51f,
					0.86f, 0.51f
			};
		}else{
			tplate_i = new short[]{7,4,5,	6,7,5};//front
			plate_i = new short[]{2,6,5,	2,5,1,//right
								1,0,3,		1,3,2,//back
								0,4,7,		0,7,3,//left
								5,4,0,		5,0,1,//top
								3,7,6,		3,6,2 //bottom
					};
			ftexture_v = new float[]{
					0.0f, 0.0f,
					0.0f, 0.0f,
					0.0f, 0.0f,
					0.0f, 0.0f,
					1.0f,0.52f,
					0.0f,0.52f,
					0.0f,0.0f,
					1.0f,0.0f
			};
		}
		
		texture_v = new float[]{
						0.2f, 1.0f,
						0.0f, 1.0f,
						0.0f, 0.5f,
						0.2f, 0.5f,
						0.2f, 1.0f,
						0.0f, 1.0f,
						0.0f, 0.5f,
						0.2f, 0.5f
				};		
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer vbb1 = ByteBuffer.allocateDirect(plate_v.length * 4);
		vbb1.order(ByteOrder.nativeOrder());
		plate_vertexBuffer = vbb1.asFloatBuffer();
		plate_vertexBuffer.put(plate_v);
		plate_vertexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb1 = ByteBuffer.allocateDirect(plate_i.length * 2);
		ibb1.order(ByteOrder.nativeOrder());
		plate_indexBuffer = ibb1.asShortBuffer();
		plate_indexBuffer.put(plate_i);
		plate_indexBuffer.position(0);
		// short is 2 bytes, therefore we multiply the number of vertices with 2.
		ByteBuffer ibb2 = ByteBuffer.allocateDirect(tplate_i.length * 2);
		ibb2.order(ByteOrder.nativeOrder());
		tplate_indexBuffer = ibb2.asShortBuffer();
		tplate_indexBuffer.put(tplate_i);
		tplate_indexBuffer.position(0);
		
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer tbb2 = ByteBuffer.allocateDirect(ftexture_v.length * 4);
        tbb2.order(ByteOrder.nativeOrder());
        ftextureBuffer = tbb2.asFloatBuffer();
        ftextureBuffer.put(ftexture_v);
        ftextureBuffer.position(0);
		// a float is 4 bytes, therefore we multiply the number of vertices with 4.
		ByteBuffer tbb = ByteBuffer.allocateDirect(texture_v.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        textureBuffer = tbb.asFloatBuffer();
        textureBuffer.put(texture_v);
        textureBuffer.position(0);
	}
		
	public void draw(GL10 gl) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		// Specifies the location and data format of an array of vertex coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, plate_vertexBuffer);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, plate_i.length, GL10.GL_UNSIGNED_SHORT, plate_indexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, ftextureBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, tplate_i.length, GL10.GL_UNSIGNED_SHORT, tplate_indexBuffer);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);

		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
}

