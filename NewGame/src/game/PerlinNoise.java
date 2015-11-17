package game;

public final class PerlinNoise {
	/* 
	this is a set of functions that provide perlin noise.
	the goal is to take x and y positions as well as a rng seed to provide
	an interpolated random distribution of values. 
	This means the output will be random but 
	gradiated in such a way as the values transition smoothly
	*/
	static double interpolate(double a, double b, double x) // 
	{
		double ft = x * 3.1415927;
		double f = (1.0 - Math.cos(ft))* 0.5;
		return a*(1.0 - f) + b*f;
		
	}

	static double findnoise(double x, double y)
	{
		double q = -1 + 2 * ((double)Math.random());
		double ex = -1 + 2 * ((double)(x + (y * 100)));
		double ans = -1 + 2 * (q + ex);
		return ans;
	}
	static double findnoise2(double x, double y, int[] seed)
	{
		//seed = seed %20;
		
		int n=(int)x+(int)y*57;
		 n=(n<<seed[0])^n;
		 int nn=(n*(n*n*60493+19990303)+seed[1])&0x7fffffff;
		 return 1.0-((double)nn/1073741824.0);


	}
	static double noise(double x, double y,int[] seed)
	{
		
		double floorx = (double)((int)x);//This is kinda a cheap way to floor a double integer.
		double floory = (double)((int)y);
		double s, t, u, v;//Integer declaration
		s = findnoise2(floorx, floory,seed);
		t = findnoise2(floorx + 1, floory, seed);
		u = findnoise2(floorx, floory + 1, seed);//Get the surrounding pixels to calculate the transition.
		v = findnoise2(floorx + 1, floory + 1, seed);
		double int1 = interpolate(s, t, x - floorx);//Interpolate between the values.
		double int2 = interpolate(u, v, x - floorx);//Here we use x-floorx, to get 1st dimension. Don't mind the x-floorx thingie, it's part of the cosine formula.
		return interpolate(int1, int2, y - floory);//Here we use y-floory, to get the 2nd dimension.
	}

	public static double getPerlin(double x, double y,int[] seed)
	{
		double getnoise = 0;
		double zoom = 100;
		double paa = 0.7;
		int octaves = 5;
		for (int a = 0; a<octaves - 1; a++)//This loops trough the octaves.
		{
			double frequency = Math.pow(2, a);//This increases the frequency with every loop of the octave.
			double amplitude = Math.pow(paa, a);//This decreases the amplitude with every loop of the octave.
			getnoise += noise(((double)x)*frequency / zoom, ((double)y) / zoom*frequency,seed)*amplitude;//This uses our perlin noise functions. It calculates all our zoom and frequency and amplitude
		}//											It gives a decimal value, you know, between tudethe pixels. Like 4.2 or 5.1		
		if (getnoise >= 1 || getnoise <= -1)
		{
			//std::cout << getnoise << std::endl;
		}
			
		return getnoise;
	}
}
