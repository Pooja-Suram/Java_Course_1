import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Part1 {

    /*This method returns dna string by finding
    * start and stop codons */
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex < 0){
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex+3);
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
        //this string doesn't has stop codon
        //hence dna is not found
        String dna = "CCTAATGCAGTAGG";
        System.out.println("The dna string is: "+ dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is = "+ gene);

        //this string has no start and stop codons
        String dna2 = "CAGTTCGGAGTTACG";
        System.out.println("The dna string is: "+ dna2);
        String gene2 = findSimpleGene(dna2);
        System.out.println("Gene is = "+ gene2);

        //this string doesn't has start codon
        String dna3 = "CCATAGTTATTGGT";
        System.out.println("The dna string is: "+ dna3);
        String gene3 = findSimpleGene(dna3);
        System.out.println("Gene is = "+ gene3);

        //this string has dna string
        String dna4 = "CATGTGCACTTAA";
        System.out.println("The dna string is: "+ dna4);
        String gene4 = findSimpleGene(dna4);
        System.out.println("Gene is = "+ gene4);

        /*this string doesn't has dna string
        with multiple 3 gene*/
        String dna5 = "CATGTGCACTAA";
        System.out.println("The dna string is: "+ dna5);
        String gene5 = findSimpleGene(dna5);
        System.out.println("Gene is = "+ gene5);
    }

    public static void main(String[] args) {
        Part1 p = new Part1();
        p.testSimpleGene();
    }

}
