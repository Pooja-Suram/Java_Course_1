import java.util.Locale;

public class Part2 {

    /*This method is enhanced version of
    * findSimpleGene method in Part1*/
    //this method checks for both uppercase and lowercase letters
    public String findSimpleGene(String dna, String startCodon, String endCodon){
        String temp = dna.toUpperCase(Locale.ROOT);
        int startIndex = temp.indexOf(startCodon);
        if(startIndex < 0){
            return "";
        }
        int endIndex = temp.indexOf(endCodon, startIndex+3);
        if(endIndex <0){
            return "";
        }
        int length = endIndex+3-startIndex;
        if(length%3==0){
            return dna.substring(startIndex, endIndex+3);
        }
        return "";
    }

    public void testSimpleGene(){
        String startCodon = "ATG";
        String endCodon = "TAA";

        String dna = "CCTAATGCAGTAGG";
        System.out.println("The dna string is: "+ dna);
        String gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is = "+ gene);

        String dna2 = "CAGTTCGGAGTTACG";
        System.out.println("The dna string is: "+ dna2);
        String gene2 = findSimpleGene(dna2, startCodon, endCodon);
        System.out.println("Gene is = "+ gene2);

        String dna3 = "CCATAGTTATTGGT";
        System.out.println("The dna string is: "+ dna3);
        String gene3 = findSimpleGene(dna3, startCodon, endCodon);
        System.out.println("Gene is = "+ gene3);

        String dna4 = "CATGTGCACTTAA";
        System.out.println("The dna string is: "+ dna4);
        String gene4 = findSimpleGene(dna4, startCodon, endCodon);
        System.out.println("Gene is = "+ gene4);

        String dna6 = "catgtgcacttaa";
        System.out.println("The dna string is: "+ dna6);
        String gene6 = findSimpleGene(dna6, startCodon, endCodon);
        System.out.println("Gene is = "+ gene6);

        String dna5 = "CATGTGCACTAA";
        System.out.println("The dna string is: "+ dna5);
        String gene5 = findSimpleGene(dna5, startCodon, endCodon);
        System.out.println("Gene is = "+ gene5);
    }

    public static void main(String[] args) {
        Part2 p = new Part2();
        p.testSimpleGene();
    }
}
