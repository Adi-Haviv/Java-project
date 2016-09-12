package Boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import algorithms.controller.Controller;
import algorithms.controller.MyController;
import algorithms.model.Model;
import algorithms.model.MyModel;
import algorithms.view.MyView;
import algorithms.view.View;

public class Run {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
		View view = new MyView(in, out);
		Model model = new MyModel();
		
		Controller controller = new MyController(view, model);
		view.setController(controller);
		model.setController(controller);
		
		view.start();

	}

}
