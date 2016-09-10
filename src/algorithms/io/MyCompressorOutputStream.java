package algorithms.io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	OutputStream out;	
	
	//Constructor
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}

	@Override
	public void write(int maze) throws IOException {
		out.write(maze);
	}
	
	public void write(byte[] arr) throws IOException {
		byte currByte = arr[0];
		int count = 1;
		
		//run over the array and nu-merate the zeros and ones in the order of appearance
		for (int i=1; i < arr.length; i++){
			//checks if the cell is the same as cell[0]
			//if it is add one to `count`
			//if not - sum take the count of the number (0/1) and Writes the specified byte to this output stream. and divide it by 255
			if (arr[i] != currByte) {
				while (count >= 127) {
					out.write(127);
					out.write(currByte);
					count -= 127;
				}
				out.write(count);
				out.write(currByte);
				currByte = arr[i];
				count = 1;
			}
			else {
				count++;
			}
		}
		out.write(count);
		out.write(currByte);
		}
		
		
	}


