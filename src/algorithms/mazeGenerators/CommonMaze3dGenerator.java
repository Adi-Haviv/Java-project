package algorithms.mazeGenerators;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An abstract class that defines methods needed for a maze generation class.
 * Used to define the measureAlgorithmTime method which is used by all maze generation algorithms
 * and should therefore be inherited.
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
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
