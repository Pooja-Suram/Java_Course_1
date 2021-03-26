/* URLResource class allows us to read the file
* at given url */


import edu.duke.*;

import java.util.Locale;

public class Part4 {

    /*1.This method reads the file from given url
    * 2. Checks if given hyperlinks are links of youtube
    * 3. prints links of youtube*/
    public void compute(){
        String basic = "youtube.com";
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String word: ur.words()){
            String caseWord = word.toLowerCase(Locale.ROOT);
            if(caseWord.contains(basic)){
                int leftIndex = caseWord.indexOf("\"");
                int rightIndex = caseWord.indexOf("\"", leftIndex+1);
                System.out.println(word.substring(leftIndex, rightIndex+1));
            }
        }
    }

    public static void main(String[] args) {
        Part4 p = new Part4();
        p.compute();
    }
}
