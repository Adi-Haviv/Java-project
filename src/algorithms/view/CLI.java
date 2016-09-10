package algorithms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.controller.Command;

public class CLI {
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commands;
	final Queue<String> userCommands = new LinkedList<String>();
	String command;
	
	CLI(InputStream in, OutputStream out, HashMap<String, Command> commands){
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintWriter(out);
		this.commands = commands;
	}
	
	// IOLoop class implements callable to be called as a separate thread, and return value to main
	public class IOLoop implements Runnable{
		ArrayList<String> commands = new ArrayList<String>();
		String command;
		BufferedReader in;
		
		// C'Tor for input stream
		IOLoop(BufferedReader in){
			this.in = in;
		}
		
		// Runs when started as a thread
		@Override
		public void run() {
			try{
				// Creates arraylist of string to return from user input to main thread.
				command = in.readLine();
				while(!command.equals(new String("exit"))){
					Thread.sleep(100);
					userCommands.add(command);
					command = in.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}	
	}
	
	public void start(){
		Runnable InputLoop = new IOLoop(in);
		Thread IOThread = new Thread(InputLoop);
		IOThread.start();
		Command current;
		String[] args;
		
		while(!command.equals(new String("exit"))){
			command = userCommands.poll();
			args = command.split(new String(" "));
			
			if (command != null){
				current = commands.get(args[0]);
				if(current != null){
					current.doCommand(Arrays.copyOfRange(args, 1, args.length));
				}
			}
		}
	}
}
