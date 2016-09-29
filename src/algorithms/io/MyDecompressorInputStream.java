package algorithms.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <h1> Decompressor Input Class </h1>
 * This class defines an input stream that decompresses data written to a stream using MyCompressorOutputStream.
 * Returns a byte array or a List of Byte Objects containing all the data from the InputStream. 
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
public class MyDecompressorInputStream extends InputStream{
	InputStream in;
	
	/**
	 * C'tor
	 * @param in InputStream from which to read compressed data.
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}
	
	/**
	 * Reads a single byte from in.
	 * @return int Returns the next byte from stream as int between 0-255.
	 */
	@Override
	public int read() throws IOException {
		try{
			return in.read();
		}
		catch (Exception IOException) {
			return -1;
		}
	}
	
	/**
	 * Reads into a byte[] object until it's full or until stream is empty.
	 * @param b byte[] to read data into.
	 * @return int Number of bytes read into b.
	 * @throws IOException If input stream is not defined properly.
	 */
	public int read(byte[] b) throws IOException{
		// reps receives repetitions of specific value.
		// counter is used to count bytes already read, as well as control the loops.
		byte reps = 0;
		byte value;
		int arrIndex = 0;
		
		// If the receiving array is not defined, return 0 meaning 0 bytes read.
		if(b.length == 0){
			return 0;
		}
		
		// While b is not full and stream still has data, decompress data from stream and place in b 
		while(arrIndex < b.length){
			reps = (byte) in.read();
			value = (byte) in.read();
			
			for (int i = 0; i < reps; i++){
				b[arrIndex] = (byte) value;
				arrIndex++;
			}
		}
		
		return b.length;
	}
	
	/**
	 * Reads into a List of Byte objects until stream is empty.
	 * @param list List of Byte obejects to fill with data from stream.
	 * @return int Number of bytes read from the stream.
	 * @throws IOException When input stream isn't defined properly.
	 */
	public int read(List<Byte> list) throws IOException{
		// reps receives repetitions of specific value.
		// counter is used to count bytes already read, as well as control the loops.
		byte reps = 0;
		byte value;
		
		while(reps != -1){
			reps = (byte) in.read();
			value = (byte) in.read();
							
			for (int i = 0; i < reps; i++){
				list.add(new Byte(value));
			}
		}
		
		return list.size();
	}

}
