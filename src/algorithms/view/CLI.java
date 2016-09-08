package algorithms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import algorithms.controller.Command;

public class CLI {
	
	// IOLoop class implements callable to be called as a separate thread, and return value to main
	public static class IOLoop implements Callable<ArrayList<String>>{
		ArrayList<String> commands = new ArrayList<String>();
		String command;
		BufferedReader in;
		
		// C'Tor for input stream
		IOLoop(BufferedReader in){
			this.in = in;
		}
		
		// Runs when started as a thread via ExecutorService
		@Override
		public ArrayList<String> call() throws IOException {
			try{
				// Creates arraylist of string to return from user input to main thread.
				command = in.readLine();
				while(!command.equals("exit")){
					commands.add(command);
					command = in.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return commands;
			
		}
		
	}
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commands;
	ArrayList<String> userCommands = new ArrayList<String>();
	Future<ArrayList<String>> cmdFuture;
	ExecutorService executor = Executors.newFixedThreadPool(3);
	
	CLI(InputStream in, OutputStream out, HashMap<String, Command> commands){
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintWriter(out);
		this.commands = commands;
	}
	
	void start(){
		Callable<ArrayList<String>> InputLoop = new IOLoop(in);
		cmdFuture = executor.submit(InputLoop);
		try {
			userCommands = cmdFuture.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		for(String cmd: userCommands){
			Command current = commands.get(cmd);
			if(current != null){
				current.doCommand();
			}
		}
	}
}
