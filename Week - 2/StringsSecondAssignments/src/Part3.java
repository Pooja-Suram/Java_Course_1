import com.sun.source.tree.CompilationUnitTree;
import jdk.swing.interop.SwingInterOpUtils;

public class Part3 {

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


    public int printAllGenes(String dna){
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
        return noOfGene;
    }

    //This method returns number of genes found in given dna
    public void countAllGene(String dna){
        int noOfGene = printAllGenes(dna);
        System.out.println("No of gene is given dna is:" + noOfGene);
    }

    public void testCountGene(){
        String dna = "ATGAAGATGATGATGGGAAAGTTTTAAATGATGTAAAAGGGATTTA";
        countAllGene(dna);
    }

    public static void main(String[] args) {
        Part3 p = new Part3();
        p.testCountGene();
    }
}
