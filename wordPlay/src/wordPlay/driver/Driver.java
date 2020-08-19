package wordPlay.driver;

import wordPlay.AvgCount;
import wordPlay.WordPlay;
import java.io.IOException;
/**
 * @author Pranav Sahu
 *
 */
public class Driver {
	/**
	 * The first method to be executed in the program.
	 * @exception IOException On any I/O errors while reading lines from input file.
     * @exception Exception In case input file does not meet expected criteria.
	 *
	 */
	public static void main(String[] args) throws IOException, Exception {

		/**
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used.
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		
		String input = args[0];
		String output = args[1];
		String metrics = args[2];

		WordPlay swap = new WordPlay(input);
		swap.wordSwap(output);

		AvgCount avg = new AvgCount(input);
		avg.calculate(metrics);


	}
}
