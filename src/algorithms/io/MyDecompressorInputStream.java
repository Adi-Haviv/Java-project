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
		byte reps = 0;
		byte value;
		int counter = 0;
		if(b.length == 0){
			return 0;
		}
		
		while(counter < b.length){
			reps = (byte) (reps + in.read());
			value = (byte) in.read();
			
			while(counter < reps){
				b[counter] = (byte) value;
			}
		}
		
		return counter;
	}
	

}
