package algorithms.mazeGenerators;

import java.util.Arrays;
import java.util.Comparator;

public class CellComparator implements Comparator<int[]>{
	@Override
	public int compare(int[] o1, int[] o2) {
		if(Arrays.equals(o1, o2)){
			return 0;
		} else {
			return 1;
		}
	}
}
