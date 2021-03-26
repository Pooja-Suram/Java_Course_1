public class Part1 {

    /*This method returns index of stop codon which forms valid dna*/
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


    public void testFIndStopCodon(){
        String taaStopCodon = "TAA";
        String tgaStopCodon = "TGA";
        String tagStopCodon = "TAG";

        String stringa = "ATGCATTGATAA";
        String stringb = "ATGCATAAGGTTTAA";
        String stringc = "ATGCAATGTGTGAA";

        int taaIndex = findStopCodon(stringa, 0, taaStopCodon);
        System.out.println("taa index is: "+ taaIndex);

        int tgaIndex = findStopCodon(stringa, 0, tgaStopCodon);
        System.out.println("tga Index is: "+ tgaIndex);

        int taaIndex2 = findStopCodon(stringb, 0, taaStopCodon);
        System.out.println("taa index is: "+ taaIndex2);

        int taaIndex3 = findStopCodon(stringc, 0, taaStopCodon);
        System.out.println("taa index is: "+ taaIndex3);

    }

    /*1. Finds the first occurrence of start code(ATG)
    * 2. Finds first valid occurrences of all stop codons
    * 3. returns the gene formed from start codon and
    *    closest stop codon */
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

        System.out.println("Min Index is:" + minIndex);
        if(minIndex == -1)
        return "";

        return dna.substring(startIndex, minIndex+3);

    }

    public void testFindGene(){
        //this string doesn't has valid gene
        String string1 = "GGTTATTGGAAT";
        System.out.println("String is " + string1);
        String result1 = findGene(string1, 0);
        System.out.println("Result is: "+ result1);

        //This string has valid gene with stop codon 'TAG'
        String string2 = "ATGAGGTTAGAGTAG";
        System.out.println("String is "+ string2);
        String result2 = findGene(string2, 0);
        System.out.println("Result is " + result2);

        //this string has valid gene with stop cocon 'TAG'
        String string3 = "ATGTTATAGGGGTGAAAATAA";
        System.out.println("String is : "+ string3);
        String result3 = findGene(string3, 0);
        System.out.println("Result is "+ result3);

        //this string doesn't have valid gene
        String string4 = "ATGTTTGGGAAA";
        System.out.println("String is: "+ string4);
        String result4 = findGene(string4, 0);
        System.out.println("Result is"+ result4);

    }

    /*This method repeatedly finds genes and prints each gene */
    public void printAllGenes(String dna){
        int startingIndex = 0;
        while(true){
            String result = findGene(dna, startingIndex);
            if(result.isEmpty()){
                break;
            }
            System.out.println("Gene is " + result);
            startingIndex = startingIndex+1;
        }
    }

    public static void main(String[] args) {
        Part1 p = new Part1();
        p.testFIndStopCodon();

        p.testFindGene();
    }
}
