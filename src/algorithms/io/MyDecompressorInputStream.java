package algorithms.io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{
	InputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		try{
			return in.read();
		}
		catch (Exception IOException) {
			return -1;
		}
	}
	
	public int read(byte[] b) throws IOException{
		// reps receives repetitions of specific value.
		// counter is used to count bytes already read, as well as control the loops.
		byte reps = 0;
		byte value;
		int counter = 0;
		
		// If the receiving array is not defined, return 0 meaning 0 bytes read.
		if(b.length == 0){
			return 0;
		}
		
		// While b is not full and stream still has data, decompress data from stream and place in b 
		while(counter < b.length){
			reps = (byte) (reps + in.read());
			
			value = (byte) in.read();
			
			while(counter < reps){
				b[counter] = (byte) value;
				counter++;
			}
		}
		
		return counter;
	}
	

}