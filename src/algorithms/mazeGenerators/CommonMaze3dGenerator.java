package algorithms.mazeGenerators;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	// Runs algorithm per user specified dimensions and returns the time it took to run, in human readable format.
	public String measureAlgorithmTime(int sizeX, int sizeY, int sizeZ){
		long startTime;
		long endTime;
		long elpasedTime;
		
		startTime = System.currentTimeMillis();
		
		this.generate(sizeX, sizeY, sizeZ);
		
		endTime = System.currentTimeMillis();
		elpasedTime = endTime - startTime;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");    
		Date resultdate = new Date(elpasedTime);
		return sdf.format(resultdate);
	}
}
