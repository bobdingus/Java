package game;

public class PerlinMap {
	
	public double values[][];
	public double range;
	public double minVal;
	public double maxVal;
	public double midVal;
	
	public PerlinMap(int width,int height){
		minVal = 0;
		maxVal = 0;
		
		int[] seed = new int[3];
		
		seed[0] = (int) (Math.random()*((double)Integer.MAX_VALUE)); 
		seed[1] = (int) (Math.random()*((double)Integer.MAX_VALUE));
		seed[2] = (int) (Math.random()*((double)Integer.MAX_VALUE));
		
		values = new double[width][height];
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				double p = PerlinNoise.getPerlin((double)x, (double)y,seed);
				values[x][y] = p;
				if(minVal >p){p = minVal;}
				if(maxVal <p){maxVal = p;}
			}
		}
		range = maxVal - minVal;
		midVal = maxVal - (range/2);
	}

}
