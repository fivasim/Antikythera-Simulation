package com.fivasim.antikythera;

public class Initial {
	public static final float M_PI = (float)Math.PI;
	public static final int num_gears = 33;    //Αριθμός γραναζιών
	public static final float gear_width = 1.0f;  //Πάχος κάθε γραναζιού
	public static final float gear_tooth = 0.6f;  //Βάθος δοντιών
	public static final int num_axles = 18;	//Αριθμός αξόνων
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
									{          0.0f,    0.0f,   20.0f,  -0.1f,		 1f}, //A
									{		   0.0f,    0.0f,    7.7f,   0.1f,		 2f}, //B1
									{		   0.0f,    0.0f,    2.7f,   0.1f,		 2f}, //B2
									{	 	   0.0f,    0.0f,   -3.3f,   0.1f,		 2f}, //B3
									{		   0.0f,    0.0f,   -8.3f,  -1.336842f,	 3f}, //B4
									{		 15.72f,    0.0f,    2.7f,  -0.16842f,	 4f}, //C1f 
									{		 15.72f,    0.0f,    1.5f,  -0.16842f,	 4f}, //C2
									{		 23.56f,  -7.64f,    1.5f,   0.336842f,	 8f}, //D1
									{		 23.56f,  -7.64f,   -8.3f,   0.336842f,	 8f}, //D2
									{		 -9.68f,    0.0f,   -3.3f,  -0.1f,		 6f}, //E1
									{		 -9.68f,    0.0f,   -8.3f,   1.336842f,	 7f}, //E2a
									{		 -9.68f,    0.0f,  -13.3f,   1.336842f,	 7f}, //E2b
									{		 -9.68f,    0.0f,  -19.3f,   0.618421f,	 5f}, //E3
									{		 -9.68f,    0.0f,  -18.3f,   0.618421f,	 14f},//E4
									{		 -9.68f,    0.0f,  -23.8f,  -0.1f,		 6f}, //E5
									{		 28.01f,    0.0f,  -19.3f,  -2.473684f,	 4f}, //F1
									{		 28.01f,    0.0f,  -23.8f,  -2.473684f,	 4f}, //F2
									{		 28.01f, -13.82f,  -28.8f,   1.236842f,	 10f},//G1
									{		 28.01f, -13.82f,  -23.8f,   1.236842f,	 10f},//G2
									{		 28.01f, -26.06f,  -28.8f,  -0.41228f,	 11f},//H1
									{		 28.01f, -26.06f,  -33.8f,  -0.41228f,	 11f},//H2
									{		 39.46f, -26.06f,  -33.8f,   0.10307f,	 12f},//I
									{ -20.12f + 9.68f,  10.44f,  -13.3f,  -0.35921f, 13f},//J
									{ -23.95f + 9.68f,  -3.82f,  -13.3f,   0.718421f, 9f},//K1
									{ -23.95f + 9.68f,  -3.82f,  -23.8f,   0.718421f, 9f},//K2
									{		 -15.4f,    0.0f,    2.7f,  -0.1777778f, 15f},//L1
									{		 -15.4f,    0.0f,    1.5f,  -0.1777778f, 15f},//L2
									{		-38.78f,    0.0f,    1.5f,   0.1f,		 16f},//M1
									{		-38.78f,    0.0f,   -8.3f,   0.1f,		 16f},//M2
									{		-41.70f, -24.80f,   -8.3f,   0.05f,		 17f},//O1
									{		-41.70f, -24.80f,  -13.3f,   0.05f,		 17f},//O2
									{		-45.06f, -10.45f,   -8.3f,  -0.025f,	 18f},//Ένδειξη Τετραετίας
									{		 36.00f,    0.0f,  13.85f,   0.5f,		 19f} //Μανιβέλα
	};
	static public float geardata[][] = new float[][] {    //only 1 cross supported
											{28.0f, 35.56f, gear_width, 225f, gear_tooth, 2f}, //A
											{28.0f, 35.56f, gear_width, 225f, gear_tooth, 1f}, //B1
											{ 6.0f,  9.93f, gear_width,  64f, gear_tooth, 1f}, //B2
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //B3
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //B4
											{ 0.0f,  5.79f, gear_width,  38f, gear_tooth, 1f}, //C1
											{ 0.0f,  7.38f, gear_width,  48f, gear_tooth, 1f}, //C2
											{ 0.0f,  3.56f, gear_width,  24f, gear_tooth, 1f}, //D1
											{ 8.0f, 19.96f, gear_width, 127f, gear_tooth, 1f}, //D2
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //E1
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //E2a
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //E2b
											{10.0f, 30.32f, gear_width, 192f, gear_tooth, 1f}, //E3
											{10.0f, 35.08f, gear_width, 222f, gear_tooth, 0f}, //E4
											{ 0.0f,  7.38f, gear_width,  48f, gear_tooth, 1f}, //E5
											{ 0.0f,  7.38f, gear_width,  48f, gear_tooth, 1f}, //F1
											{ 0.0f,  4.51f, gear_width,  30f, gear_tooth, 1f}, //F2
											{ 0.0f,  2.92f, gear_width,  20f, gear_tooth, 1f}, //G1
											{ 7.0f,  9.29f, gear_width,  60f, gear_tooth, 1f}, //G2
											{ 7.0f,  9.29f, gear_width,  60f, gear_tooth, 1f}, //H1
											{ 0.0f,  2.18f, gear_width,  15f, gear_tooth, 1f}, //H2
											{ 6.0f,  9.29f, gear_width,  60f, gear_tooth, 1f}, //I
											{ 6.0f,  9.93f, gear_width,  64f, gear_tooth, 1f}, //J
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //K1
											{ 0.0f,  7.38f, gear_width,  48f, gear_tooth, 1f}, //K2
											{ 0.0f,  5.46f, gear_width,  36f, gear_tooth, 1f}, //L1
											{ 0.0f,  8.34f, gear_width,  54f, gear_tooth, 1f}, //L2
											{ 8.0f, 15.03f, gear_width,  96f, gear_tooth, 1f}, //M1
											{ 0.0f,  2.28f, gear_width,  16f, gear_tooth, 1f}, //M2
											{ 0.0f,  4.83f, gear_width,  32f, gear_tooth, 1f}, //O1
											{ 0.0f,  7.38f, gear_width,  48f, gear_tooth, 1f}, //O2
											{ 6.0f,  9.92f, gear_width,  64f, gear_tooth, 1f}, //Ένδειξη Τετραετίας
											{ 0.0f,  6.15f, gear_width,  45f, gear_tooth, 1f}  //Μανιβέλα
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
										 // A  B1f B2f B3f B4f   C1f C2f D1f   D2f E1f  E2a E2bE3f E4f E5f F1f  F2f 
	                                        0f ,0f ,0f ,0f ,0.6f ,5f ,0f ,9.5f ,0f ,5.6f ,5f ,0f ,0f ,0f ,0f ,4f ,0f ,
										 // G1f G2f H1f H2f  I  J    K1f K2f   L1f L2f   M1f M2f   O1f O2f e4f  ,m
											9f ,0f ,0f ,12f ,0f ,2.8f ,0f ,3.6f ,5f ,3.3f ,0f ,9.1f ,9f ,0f ,1.2f ,0f};
	static public float differential[] = new float[] { 0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
													0.618421f,0.618421f,0.618421f,0f,0f,0f,0f,0f,0f,0f,0f};
	static public float differential_angle[] = new float[] { 0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
												0.001f,0.001f,0.001f,0f,0f,0f,0f,0f,0f,0f,0f};
									//	    x		y		z	 ταχύτητα    χρώμα
	static public float axlepos[][] = new float[][] {
											{	0.0f,    0.0f,  26.0f,		-0.1f,  1f},//A
											{	0.0f,    0.0f,   5.85f,	-1.336842f, 3f},//B1
											{	0.0f,    0.0f,   2.20f,	 0.1f,		2f},//B2
											{ 23.56f,  -7.64f,  -3.30f,	 0.336842f,	8f},//D
											{ -9.68f,    0.0f, -13.55f,	-0.1f,		6f},//E1
											{ -9.68f,    0.0f, -10.80f,	 1.336842f,	7f},//E2
											{ 28.01f,    0.0f, -21.55f,	-2.473684f,	4f},//F
											{ 28.01f, -13.82f, -33.80f,	 1.236842f,	10f},//G
											{ 28.01f, -26.06f, -31.30f,	-0.41228f,	11f},//H
											{ 39.46f, -26.06f, -38.80f,	 0.10307f,	12f},//I
									 {-20.12f + 9.68f,  10.44f, -15.80f,	-0.35921f,	13f},//J (διαφορικό)
									 {-23.95f + 9.68f,  -3.82f, -18.55f,	 0.718421f,	9f },//K (διαφορικό)
											{-38.78f,    0.0f,  -3.30f,	 0.1f,		16f},//M
											{-41.70f, -24.80f, -10.80f,	 0.05f,		17f},//O
											{-45.06f, -10.45f, -25.80f,	-0.025f, 	18f},//Ένδειξη Τετραετίας
										    { 53.00f,    0.0f,  13.85f,     0.5f,		19f},//Άξονας Μανιβέλας
											{ 70.00f,    0.0f,  13.85f,     0.5f,		19f},//Βάση Μανιβέλας
										    {  2.50f,    0.0f,   5.50f,     0.0f,		19f},//Χερούλι Μανιβέλας
											   //Οι συντεταγμένες του χερουλιού απλώς προστίθενται 
											   //σε αυτές της βάσης (είναι σχετικές)
	};
	//Η μανιβέλα (το χερούλι) να είνα ΠΑΝΤΑ τελευταία
	static public float axledata[][] = new float[][] {
												{0.0f,	  1.0f,   12.0f,   20f},//A
												{0.0f,    0.6f,   28.3f,   20f},//B1
												{0.0f,    1.0f,   11.0f,   20f},//B2
												{0.0f,    0.6f,   10.0f,   20f},//D
												{0.0f,    0.6f,   20.5f,   20f},//E1
												{0.0f,    1.0f,    5.0f,   20f},//E2
												{0.0f,    0.6f,    4.5f,   20f},//F
												{0.0f,    0.6f,   20.0f,   20f},//G
												{0.0f,    0.6f,    5.0f,   20f},//H
												{0.0f,    0.6f,   10.0f,   20f},//I 
												{0.0f,    0.6f,    5.0f,   20f},//J (διαφορικό)
												{0.0f,    0.6f,   10.5f,   20f},//K (διαφορικό)
												{0.0f,    0.6f,   10.0f,   20f},//M
											    {0.0f,    0.6f,    5.0f,   20f},//O
												{0.0f,    0.6f,   35.0f,   20f},//Ένδειξη Τετραετίας
												{0.0f,    0.6f,   34.0f,   20f},//Άξονας Μανιβέλας
												{0.0f,   6.15f,    1.0f,   80f},//Βάση Μανιβέλας
												{0.0f,    0.5f,    5.0f,   20f},//Χερούλι Μανιβέλας (διαφορικό)
	};
	static public boolean axle_visibility[] = new boolean[] {true,true,true,true,true,true,true,true,
													true,true,true,true,true,true,true,true,true,true};
	static public float axle_angle[] = new float[] {0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
													0f,0f,0f,0f,0f,0f,0f,0f,0f,0f};

	//Η μανιβέλα (το χερούλι) να είνα ΠΑΝΤΑ τελευταία				
	static public float axle_differential[] = new float[] {0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
								0.618421f , 0.618421f ,0f,0f,0f,0f,0f, 0.5f};
	static public float axle_differential_angle[] = new float[] {0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
								0.001f , 0.001f ,0f,0f,0f,0f,0f, 0.001f};

	static public float pointer_pos[][] = new float[][] {
												{  0.0f ,   0.0f ,  32.00f, -0.1f,		10f},//Ήλιος
												{  0.0f ,   0.0f ,  33.00f, -1.336842f,	 3f},//Σελήνη
											    {-45.06f, -10.45f, -43.80f, -0.025f,	18f},//Ένδειξη Τετραετίας
												{ 28.01f, -13.82f, -43.80f,  1.236842f,	10f},//Συνοδικός Μήνας
												{ 39.46f, -26.06f, -43.80f,  0.10307f,	12f},//Σεληνιακό Έτος
	};
	static public float pointer_len[] = new float[] { 30f, 20f, 8f, 7f, 8f};
	static public float pointer_angle[] = new float[] { 0f, 0f, 0f, 0f, 0f};

	static public boolean pointer_visibility[] = new boolean[] {true,true,true,true,true};

	static public boolean plate_visibility;
}
