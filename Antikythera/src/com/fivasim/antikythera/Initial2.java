package com.fivasim.antikythera;

public class Initial2 {
	public static final float M_PI = (float)Math.PI;
	public static final int num_gears = 33;    //Αριθμός γραναζιών
	public static final float gear_width = 1.0f;  //Πάχος κάθε γραναζιού
	public static final float gear_tooth = 0.6f;  //Βάθος δοντιών
	public static final int num_axles = 19;	//Αριθμός αξόνων
	public static final int num_pointers = 5;	//Αριθμός δεικτών

	//Μεταβλητές και σταθερές για την χρήση του ποντικιού
	public static final int MOUSE_ROTATE_YX = 0;
	public static final int MOUSE_ZOOM = 8;
	public static final int MOUSE_MOVE = 2;
	public static float m_last_x;
	public static float m_last_y;
	public static int mouse_mode;
	
	//	    x		y		z	ταχύτητα    χρώμα
	static public float gearpos[][] = new float[][] {
									{   0.0f,    0.0f,    7.7f,   0.0748f,	 1f  }, //A
									{   0.0f,    0.0f,    2.7f,   0.0748f,	 1f  }, //B1
									{   0.0f,    0.0f,   -3.3f,  -0.0f,		 2f  }, //B2
									{ 15.72f,    0.0f,    2.7f,  -0.125984f, 4f  }, //B3
									{ 15.72f,    0.0f,    1.7f,  -0.125984f, 4f  }, //B4
									{ 23.56f,  -7.64f,    1.7f,   0.251968f, 3f  }, //C1f 
									{ 23.56f,  -7.64f,   -8.3f,   0.251968f, 3f  }, //C2
									{   0.0f,  -9.68f,   -3.3f,  -0.0f,		 7f  }, //D1
									{   0.0f,  -9.68f,   -8.3f,  -1.0f,		 6f  }, //D2
									{   0.0f,  -9.68f,  -19.3f,  -0.008418f, 5f  }, //E1
									{   0.0f,  -9.68f,  -18.3f,  -0.008418f, 14f }, //E2a
									{   0.0f,  -9.68f,  -23.8f,  -1.0f,		 6f  }, //E2b
									{   0.0f,  -9.68f,  -25.8f,  -0.0f,		 7f  }, //E3
									{ 28.01f,    0.0f,  -19.3f,   0.02986f,	 9f  },//E4
									{ 28.01f,    0.0f,  -23.8f,   0.02986f,	 9f  }, //E5
									{ 28.01f, -13.82f,  -28.8f,  -0.016589f, 10f }, //F1
									{ 28.01f, -13.82f,  -23.8f,  -0.016589f, 10f }, //F2
									{ 28.01f, -26.06f,  -28.8f,   0.00553f,	 11f },//G1
									{ 28.01f, -26.06f,  -33.8f,   0.00553f,	 11f },//G2
									{ 39.46f, -26.06f,  -33.8f,  -0.001382f, 12f },//H1
									{-14.30f,  -9.68f,  -23.8f,   1.0f,		 13f },//H2
									{-14.80f,  -9.68f,  -25.8f,  -0.0f,		  3f },//I
									{ -15.4f,    0.0f,    2.7f,  -0.125984f, 15f },//J
									{ -15.4f,    0.0f,    1.7f,  -0.125984f, 15f },//K1
									{-38.78f,    0.0f,    1.7f,   0.069553f, 16f },//K2
									{-38.78f,    0.0f,   -8.3f,   0.069553f, 16f },//L1
									{-38.78f,    0.0f,  -18.3f,   0.069553f, 16f },//L2
									{-41.70f,  -8.80f,   -8.3f,  -0.019685f, 17f },//M1
									{-41.70f,  -8.80f,  -13.3f,  -0.019685f, 17f },//M2
									{-45.06f, -20.45f,  -13.3f,   0.004921f, 18f },//O1
									{-45.06f, -20.45f,  -18.3f,   0.004921f, 18f },//O2
									{-45.06f, -11.45f,  -18.3f,  -0.000984f,  3f },//Ένδειξη Τετραετίας
									{ 36.00f,    0.0f,  13.85f,   0.5236f,	 19f } //Μανιβέλα
	};
	static public float geardata[][] = new float[][] {    //only 1f cross supported
											{28.0f, 35.56f, gear_width, 224f, gear_tooth, 1f}, //A
											{ 6.0f,  9.93f, gear_width,  64f, gear_tooth, 1f}, //B1
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //B2
											{ 0.0f,  5.79f, gear_width,  38f, gear_tooth, 1f}, //B3
											{ 0.0f,  7.38f, gear_width,  48f, gear_tooth, 1f}, //B4
											{ 0.0f,  3.56f, gear_width,  24f, gear_tooth, 1f}, //C1
											{ 8.0f, 18.81f, gear_width, 127f, gear_tooth, 1f}, //C2
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //D1
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //D2
											{10.0f, 23.21f, gear_width, 188f, gear_tooth, 1f}, //E1
											{10.0f, 35.98f, gear_width, 223f, gear_tooth, 1f}, //E2a
											{ 0.0f,  7.17f, gear_width,  50f, gear_tooth, 1f}, //E2b
											{ 0.0f,  7.40f, gear_width,  50f, gear_tooth, 1f}, //E3
											{ 0.0f,  6.34f, gear_width,  53f, gear_tooth, 1f}, //E4
											{ 0.0f,  4.51f, gear_width,  30f, gear_tooth, 1f}, //E5
											{ 0.0f,  2.92f, gear_width,  20f, gear_tooth, 1f}, //F1
											{ 5.0f,  9.29f, gear_width,  54f, gear_tooth, 1f}, //F2
											{ 5.0f,  9.29f, gear_width,  60f, gear_tooth, 1f}, //G1
											{ 0.0f,  2.18f, gear_width,  15f, gear_tooth, 1f}, //G2
											{ 5.0f,  9.29f, gear_width,  60f, gear_tooth, 1f}, //H1
											{ 0.5f,  7.17f, gear_width,  50f, gear_tooth, 0f}, //H2
											{ 0.5f,  7.40f, gear_width,  50f, gear_tooth, 0f}, //I
											{ 0.0f,  5.46f, gear_width,  38f, gear_tooth, 1f}, //J
											{ 0.0f,  8.34f, gear_width,  53f, gear_tooth, 1f}, //K1
											{ 8.0f, 15.03f, gear_width,  96f, gear_tooth, 1f}, //K2
											{ 0.0f,  2.00f, gear_width,  15f, gear_tooth, 1f}, //L1
											{ 0.0f,  3.97f, gear_width,  27f, gear_tooth, 1f}, //L2
											{ 0.0f,  7.28f, gear_width,  53f, gear_tooth, 1f}, //M1
											{ 0.0f,  2.26f, gear_width,  15f, gear_tooth, 1f}, //M2
											{ 5.0f,  9.84f, gear_width,  60f, gear_tooth, 1f}, //O1
											{ 0.0f,  1.59f, gear_width,  12f, gear_tooth, 1f}, //O2
											{ 0.0f,  7.38f, gear_width,  60f, gear_tooth, 1f}, //Ένδειξη Τετραετίας
											{ 0.0f,  5.75f, gear_width,  32f, gear_tooth, 1f}  //Μανιβέλα
	}; 
	static public float color[][] = new float[][] {
											{0.7f, 0.0f, 0.0f, 1.0f},//Κόκκινο
								  			{0.0f, 0.7f, 0.0f, 1.0f},//Πράσινο
											{0.0f, 0.0f, 0.7f, 1.0f},//Μπλε
											{0.7f, 0.7f, 0.0f, 1.0f},//Κίτρινο
								  			{0.7f, 0.0f, 0.7f, 1.0f},//Μωβ
											{0.0f, 0.7f, 0.7f, 1.0f},//Τυρκουάζ
											{0.8f, 0.4f, 0.0f, 1.0f},//Πορτοκαλί
											{0.8f, 0.0f, 0.4f, 1.0f},//Φούξια
											{1.0f, 0.5f, 0.5f, 1.0f},//Ροζ

								  			{0.4f, 0.0f, 0.0f, 1.0f},//Σκούρο Κόκκινο
											{0.3f, 0.3f, 0.3f, 1.0f},//Γκρι
								  			{0.0f, 0.4f, 0.0f, 1.0f},//Σκούρο Πράσινο									 
											{0.4f, 0.4f, 0.0f, 1.0f},//Μουσταρδί
								  			{0.4f, 0.0f, 0.4f, 1.0f},//Σκούρο Μωβ
								  			{0.1f, 0.5f, 0.5f, 1.0f},//Σκούρο Τυρκουάζ
											{0.5f, 0.3f, 0.1f, 1.0f},//Καφετί
											{0.7f, 0.5f, 0.3f, 1.0f},//Ανοιχτό Πορτοκαλί
											{0.4f, 0.7f, 0.2f, 1.0f},//Λαχανί

											{0.0f, 0.0f, 0.4f, 1.0f},//Σκούρο Μπλε
											{1.0f, 1.0f, 1.0f, 1.0f} //Άσπρο
	};
	static public boolean gear_visibility[] = new boolean[] {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,
												true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
	static public float startangle[] = new float[] {
						//  A  B1f B2f B3f B4f   C1f C2f D1f   D2f E1f  E2a E2bE3f E4f E5f F1f  F2f 
							0f ,0f ,0f ,0f ,0.6f ,5f ,0f ,9.5f ,0f ,5.6f ,5f ,0f ,3f ,0f ,0f ,4f ,0f ,
						// G1f G2f H1f H2f  I  J    K1f K2f   L1f L2f   M1f M2f   O1f O2f e4f  ,m
						9f ,0f ,0f ,12f ,0f ,2.8f ,0f ,3.6f ,5f ,3.3f ,0f ,9.1f ,9f ,0f ,1.2f ,0f};
	
	//	    x		y		z	 ταχύτητα    χρώμα
	static public float axlepos[][] = new float[][] {
		{	0.0f,    0.0f,  14.85f,	 	1.0f,		2f},//A
		{	0.0f,    0.0f,  17.35f,	 	0.0748f,	1f},//B1
		{  23.56f,  -7.64f,  -3.30f,	 0.251968f,	3f},//B2
		{    0.0f,  -9.68f, -14.55f,	-1.0f,		7f},//D
		{    0.0f,  -9.68f, -16.05f,	-1.0f,		6f},//E1
		{  28.01f,    0.0f, -21.55f,	 0.02986f,	9f},//E2
		{  28.01f, -13.82f, -33.80f,	-0.016589f,	10f},//F
		{  28.01f, -26.06f, -31.30f,	 0.00553f,	11f},//G
		{  39.46f, -26.06f, -38.80f,	-0.001382f,	12f},//H
		{ -13.55f,  -3.82f, -25.00f,	 0.0f,		19f},//I
		{ -14.30f,  -9.68f, -23.80f,	 1.0f,		13f},//J (διαφορικό)
		{ -14.80f,  -9.68f, -25.80f,	 1.0f,		14f},//K (διαφορικό)
		{ -38.78f,    0.0f,  -8.30f,	 0.069553f,	16f},//M
		{ -41.70f,  -8.80f, -10.80f,	-0.019685f,	17f},//O
		{ -45.06f, -11.45f, -31.25f,	-0.000984f,	 3f},//Ένδειξη Τετραετίας
	    { -45.06f, -20.45f, -15.80f,	 0.004921f, 18f},//Άξονας Μανιβέλας
		{  53.00f,    0.0f,  13.85f,     0.2992f,	19f},//Βάση Μανιβέλας
	    {  70.00f,    0.0f,  13.85f,     0.2992f,	19f},//Χερούλι Μανιβέλας
		{   2.50f,    0.0f,   5.50f,     0.0f,		19f} //Οι συντεταγμένες του χερουλιού απλώς προστίθενται 
		   //σε αυτές της βάσης (είναι σχετικές)
	};
	//Η μανιβέλα (το χερούλι) να είνα ΠΑΝΤΑ τελευταία
	static public float axledata[][] = new float[][] {
		{0.0f,    0.6f,   36.3f,   20f},//B1
		{0.0f,    1.0f,   29.3f,   20f},//B2
		{0.0f,    0.6f,   10.0f,   20f},//D
		{0.0f,    0.6f,   21.5f,   20f},//E1
		{0.0f,    1.0f,   15.0f,   20f},//E2
		{0.0f,    0.6f,    4.5f,   20f},//F
		{0.0f,    0.6f,   20.0f,   20f},//G
		{0.0f,    0.6f,    5.0f,   20f},//H
		{0.0f,    0.6f,   10.0f,   20f},//I 
		{0.0f,    0.6f,    4.0f,   20f},//πυρρος
		{0.0f,    0.6f,    1.0f,   20f},//K1
		{0.0f,    0.6f,    1.0f,   20f},//K2
		{0.0f,    0.6f,   20.5f,   20f},//M
	    {0.0f,    0.6f,    5.0f,   20f},//N
		{0.0f,    0.6f,   25.0f,   20f},//O
		{0.0f,    0.6f,    5.0f,   20f},//P
		{0.0f,    0.6f,   34.0f,   20f},//Άξονας Μανιβέλας
		{0.0f,   6.15f,    1.0f,   20f},//Βάση Μανιβέλας
		{0.0f,    0.5f,    5.0f,   20f} //Χερούλι Μανιβέλας (διαφορικό)
	};
	static public boolean axle_visibility[] = new boolean[] {true,true,true,true,true,true,true,true,true,
						true,true,true,true,true,true,true,true,true,true};
	static public float axle_angle[] = new float[] {0f,0f,0f,0f,0f,0f,0f,0f,0f,
													0f,0f,0f,0f,0f,0f,0f,0f,0f,0f};
	
	//Η μανιβέλα (το χερούλι) να είνα ΠΑΝΤΑ τελευταία				
	static public float axle_differential[] = new float[] {   0f,0f,0f,0f,0f,0f,0f,0f,0f, 
														gearpos[20][3],0f,0f,0f,0f,0f,0f,0f,0f,0.5f};
	static public float axle_differential_angle[] = new float[] {   0f,0f,0f,0f,0f,0f,0f,0f,0f, 
														0.01f,0f,0f,0f,0f,0f,0f,0f,0f,0.001f};
	

	static float pointer_pos[][] = new float[][] {
									{ 0.0f,   0.0f,  31.9f,  0.0748f,	10f},//Ήλιος
									{ 0.0f,   0.0f,  33.3f, -0.0f,		 2f},//Σελήνη
								    {-45.06f,-11.45f, -43.8f, -0.000984f, 3f},//Ένδειξη Τετραετίας
								    {28.01f, -13.82f, -43.8f, -0.016589f,10f},//Συνοδικός Μήνας
								    {39.46f, -26.06f, -43.8f, -0.001382f,12f}//Σεληνιακό Έτος
	};
	static public float pointer_len[] = new float[] { 30f, 20f, 8f, 7f, 8f};
	static public float pointer_angle[] = new float[] { 0f, 0f, 0f, 0f, 0f};
	
	static public boolean pointer_visibility[] = new boolean[] {true,true,true,true,true};
	
	static public boolean plate_visibility;
}
