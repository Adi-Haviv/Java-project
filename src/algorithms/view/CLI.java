package algorithms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.controller.Command;

public class CLI implements Runnable{
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commands;
	String cmd;
	
	CLI(InputStream in, OutputStream out, HashMap<String, Command> commands){
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintWriter(out);
		this.commands = commands;
	}
	
	void start(){
		try {
			;
			Thread input = new Thread()
			while(cmd.equals(new String("exit"))){
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
	}
}
