package algorithms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import algorithms.controller.Command;

/**
 * <h1> CLI Class </h1>
 * This class is a basic CLI - Command Line Interface.
 * Receives commands by input from a BufferedReader and capable of writing to a PrintWriter.
 * Commands are held in a HashMap<String,Command> object.
 * 
 * @author Adi Haviv & Bar Genish
 *
 */
public class CLI {
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commands;
	final Queue<String> userCommands = new LinkedList<String>();
	String command;
	
	/**
	 * C'Tor
	 * @param in BufferedReader to be used for input.
	 * @param out PrintWriter to be used for output.
	 */
	CLI(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
	}

	/**
	 * Sets the commands data member to commands hashmap received as parameter.
	 * @param commands HashMap that maps Command objects to user String objects.
	 */
	public void setCommands(HashMap<String, Command> commands){
		this.commands = commands;
	}
	
	/**
	 * The main method for the CLI class.
	 * Starts the IOLoop in charge of user input in a seperate thread.
	 * The loop then inputs command lines from user, fetches respective Command object from HashMap,
	 * and calls the Command's doCommand method.
	 */
	public void start(){
		Thread IOThread = new Thread(new Runnable() {
			Command current;
			String[] args;
			String command;
			BufferedReader in;
			
			// Runs when started as a thread
			@Override
			public void run() {
				try{
					// Creates arraylist of string to return from user input to main thread.
					command = in.readLine();
					args = command.split(" ");
					
					while(!command.equals(new String("exit"))){
						if (command != null){
							current = commands.get(args[0]);
							if(current != null){
								current.doCommand(Arrays.copyOfRange(args, 1, args.length));
							}
						}						
						Thread.sleep(100);
						command = in.readLine();
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}
		});
		IOThread.start();
	}	
	
	/**
	 * Prints string to PrintWriter data member of CLI.
	 * @param string String to be written to PrintWriter.
	 */
	public void write(String string){
		this.out.write(string);	
	}

}

