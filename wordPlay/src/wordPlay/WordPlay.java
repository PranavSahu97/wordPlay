package wordPlay;

import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

import java.io.IOException;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;

/**
 * WordPlay class to take each word from sentence and rotate characters based on the index in the sentence.
 * @author Pranav Sahu
 *
 */

public class WordPlay {

    FileProcessor file;
    Results result;

    /**
     * Parameterized Constructor.
     * @param input Takes in the input file.
     * @exception IOException On any I/O errors while reading lines from input file.
     *
     */
    public WordPlay(String input) throws IOException{
        file = new FileProcessor(input);
        result = new Results();
    }

    /**
     * @param output Write the output expected to the output file.
     * @exception IOException On any I/O errors while reading lines from input file.
     * @exception Exception In case input file does not meet expected criteria.
     *
     */ 
    public void wordSwap(String output) throws IOException, Exception{
    	String temp = file.poll();
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        int index=0;
        boolean flag= false;
        boolean flag2 = false;
        int count = 0;
            
      	if(temp == null){
            throw new Exception("Empty input file!");
        }
        else{
        	while(temp != null){
	            if(temp.matches("^[a-zA-Z0-9]*$") || temp.contains(".")) {
	                if(temp.charAt(temp.length()-1) == '.'){
	                    String b = temp.replace(".", "");
	                    sb.append(helper(b, index + 1)).append(".");
	                    list.add(sb.toString());
	                    sb.setLength(0);
	                    index=0;
	                }
	                else{
	                    sb.append(helper(temp, index + 1));
	                    sb.append(" ");
	                    index++;
	                }
	            }
	            else{
	            	flag = true;
	            	break;
	            }


	            temp = file.poll();
	        }

	        if(flag){
	            list.clear();
	            throw new Exception("Sentence can only contain alphanumerics and no special characters!");
	        }
	        
	        else if(flag2){
	            throw new Exception("2 words on the same line!");
	        }
	        else{
	            for(int i=0; i<list.size(); i++){
	                result.storeNewResult(list.get(i));
	                result.writeToFile(output);
	            }
	        }
        }
	                    
    }

    /**
     * Helper function which takes the word and its index and rotates it.
     * @param str Individual word from the sentence.
     * @param index Index it is present in the sentence.
     * 
     * @return rotated word.
     *
     */
    private String helper(String str, int index){
        StringBuilder sb = new StringBuilder();
        if(str.length() < index && index%2 == 0){
            sb.append(str);
        }
        else if(str.length() < index && index%2 ==1){
            while(str.length() != index){
                index--;
            }
            sb.append(str.substring(str.length()-index+1));
            sb.append(str.substring(0,str.length()-index+1));
        }
        else{
            sb.append(str.substring(str.length()-index));
            sb.append(str.substring(0,str.length()-index));
        }
        return sb.toString();

    }

}
