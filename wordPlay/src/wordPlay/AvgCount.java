package wordPlay;

import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

import java.io.IOException;
import java.lang.Exception;
/**
 * AvgCount class that counts the metrics for the passed input.
 * @author Pranav Sahu
 *
 */
public class AvgCount {
    FileProcessor file;
    Results result;

    /**
     * Parameterized Constructor
     * @param input Takes in the input file
     * @exception IOException On any I/O errors while reading lines from input file.
     *
     */
    public AvgCount(String input) throws IOException{
        file = new FileProcessor(input);
        result = new Results();
    }

    /**
     * Calculate method which counts words per sentence, number of characters in a sentence and number of sentences to
     *  get the required metrics and writes to the metrics file.
     * @param metrics The file to which the output has to be written.
     * @exception IOException On any I/O errors while reading lines from input file.
     * @exception Exception In case input file does not meet expected criteria.
     *
     */
    public void calculate(String metrics) throws IOException, Exception{
        boolean flag1 = false;
        boolean flag2 = false;
        StringBuilder sb = new StringBuilder();
        int wordCount = 0;
        int sentences = 0;
        
        String temp = file.poll();
        if(temp == null){
            throw new Exception("Cannot calculate metrics of an empty file");
        }
        else{
            while(temp != null){
                if(temp.matches("^[a-zA-Z0-9]*$") || temp.charAt(temp.length()-1) == '.'){
                    if(temp.charAt(temp.length()-1) == '.'){
                        sentences++;
                    }
                }
                
                else{
                    flag2 = true;
                    break;
                }

                sb.append(temp);
                wordCount++;

                temp = file.poll();
            }
            
            if(flag1){
                throw new Exception("Cannot calculate metrics of file having 2 sentences on the same line!");
            }
            else if(flag2){
                throw new Exception("Cannot calculate metrics of file having special characters");
            }
            else{
                int charCount = sb.toString().replace(".","").length();
                double wordAverage = (double)charCount/wordCount;
                double res = Math.round(wordAverage * 100.0)/100.0;
                String res1 = "AVG_WORD_LENGTH - " + String.valueOf(res);

                double wordAvgLength = (double)wordCount/sentences;
                double res2 = Math.round(wordAvgLength * 100.0)/100.0;
                String res3 = "AVG_NUM_WORDS_PER_SENTENCE - " + String.valueOf(res2);

                result.storeNewResult(res3);
                result.storeNewResult(res1);
                result.writeToFile(metrics);
            }

        } 
            
    }
}
