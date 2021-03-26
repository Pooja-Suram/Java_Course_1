import edu.duke.*;

import java.util.Locale;

public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+1);
        while(currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex+3);
        }
        return -1;
    }


    public String findGene(String dna, int startIndex){
        if(startIndex ==-1){
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex=0;
        if(taaIndex !=-1 && tgaIndex!=-1){
            if(taaIndex<tgaIndex){
                minIndex = taaIndex;
            }
            else{
                minIndex = tgaIndex;
            }
        }
        else if(taaIndex ==-1){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }


        if(minIndex !=-1 && tagIndex!=-1){
            if(tagIndex<minIndex){
                minIndex = tagIndex;
            }
        }
        else if(minIndex== -1){
            minIndex = tagIndex;
        }

        if(minIndex == -1)
            return "";

        return dna.substring(startIndex, minIndex+3);

    }

    public void testFindGene(){
        String string1 = "GGTTATTGGAAT";
        System.out.println("String is" + string1);
        String result1 = findGene(string1, 0);
        System.out.println("Result is: "+ result1);

        String string2 = "ATGAGGTTAGAGTAG";
        System.out.println("String is"+ string2);
        String result2 = findGene(string2, 0);
        System.out.println("Result is" + result2);

        String string3 = "ATGTTATAAGGGTGAAAATAG";
        System.out.println("String is : "+ string3);
        String result3 = findGene(string3, 0);
        System.out.println("Result is "+ result3);

        String string4 = "ATGTTTGGGAAATGATAATAG";
        System.out.println("String is: "+ string4);
        String result4 = findGene(string4, 0);
        System.out.println("result is "+ result4);

    }

    public void printAllGenes(String dna){
        int startingIndex = 0;
        int noOfGene = 0;
        while(true){
            String result = findGene(dna, startingIndex);
            if(result.isEmpty()){
                break;
            }
            System.out.println(result);
            noOfGene +=1;
            startingIndex = startingIndex+result.length();
        }
        System.out.println(noOfGene);
    }

    /*This method instead of returning genes,
    * it returns StorageResource which stores all genes */
    public StorageResource getAllGenes(String dna){
        int startingIndex = dna.indexOf("ATG");
        StorageResource resource = new StorageResource();
        while(true){
            String result = findGene(dna, startingIndex);
            if(result.isEmpty()){
                break;
            }
            System.out.println("Adding " + result);
            resource.add(result);
            startingIndex = dna.indexOf("ATG", startingIndex+result.length());
            //startingIndex = startingIndex+result.length();
            //startingIndex = startingIndex + dna.indexOf("ATG", startingIndex);
        }
        return resource;
    }

    /*This method counts total number of C's and
    * G's in given dna string and divides it with dna length
    * to find cgRatio */
    public double cgRatio(String dna){
        int countCG = 0;
        for(int i=0;i<dna.length();i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                countCG+=1;
            }
        }
        return ((double)countCG)/dna.length();
    }

    public int countCTG(String dna){
        int ctgCount = 0;
        int currIndex = dna.indexOf("CTG");
        while(currIndex!=-1){
            ctgCount+=1;
            currIndex = dna.indexOf("CTG", currIndex+3);
        }
        return ctgCount;
    }

    public void testGetAllGenes(){
        String dna = "AATGCGCGAATAGATGGGATAAAGTTCGCGGTGA";
        StorageResource resource = getAllGenes(dna);
        for(String gene: resource.data()){
            System.out.println(gene);
        }
    }


    /*This method:
    * 1. Prints all strings of length greater than 9 in sr
    * 2. Prints number of strings of length >9 in sr
    * 3. Prints strings whose cg ratio is >0.35
    * 4. prints number of strings with cg ration >0.35
    * 5. prints length of longest gene in sr */
    public void processGenes(StorageResource sr){
        int stringCount9 = 0;
        System.out.println("Strings with length greater than 9 are:");
        for(String s: sr.data()){
            if(s.length() > 9){
                stringCount9+=1;
                System.out.println(s);
            }
        }
        System.out.println("there are "+ stringCount9+ " strings with length > 9");

        int cgRatioCount = 0;
        System.out.println("Strings with cg ratio >0.35 are:");
        for(String s: sr.data()){
            double ratio = cgRatio(s);
            if(ratio > 0.35){
                cgRatioCount+=1;
                System.out.println(s);
            }
        }
        System.out.println("These are "+cgRatioCount+" strings with cg ratio > 0.35");

        int longestGeneLength = 0;
        for(String s: sr.data()){
            if(s.length() > longestGeneLength){
                longestGeneLength = s.length();
            }
        }
        System.out.println("length of longest gene is:"+ longestGeneLength);

        int stringLong60 = 0;
        for(String s: sr.data()){
            if(s.length() > 60){
                stringLong60+=1;
            }
        }
        System.out.println("There are "+ stringLong60+" strings with length > 60");
        System.out.println("size of storage resource is :" + sr.size());

    }


    public void testProcessGenes(){
        FileResource fr = new FileResource("dna/brca1.fa");

        //URLResource ur = new URLResource("http://www.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase(Locale.ROOT);
        //System.out.println(dna);

        StorageResource sr =  getAllGenes(dna);
        processGenes(sr);
    }

    public static void main(String[] args) {
        Part1 p = new Part1();
        p.testProcessGenes();
        //p.testFindGene();
    }
}
