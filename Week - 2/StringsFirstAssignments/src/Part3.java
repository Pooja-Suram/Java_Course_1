public class Part3 {

    /*this method checks whether stringa appears
    at least twice in stringb  */
    public boolean twoOccurrences(String stringa, String stringb){
        int firstIndex = stringb.indexOf(stringa);
        if(stringb.indexOf(stringa, firstIndex+stringa.length()) < 0){
            return false;
        }
        return true;
    }

    public void testing(){
        String stringa = "by";
        String stringb = "A story by Abby Long";
        boolean value = twoOccurrences(stringa, stringb);
        System.out.println("The two strings are: "+ stringa +", "+ stringb);
        System.out.println("Result is "+ value);

        String stringa2 = "ct";
        String stringb2 = "ctgtatgta";
        boolean value2 = twoOccurrences(stringa2, stringb2);
        System.out.println("The two strings are: "+ stringa2 +", "+ stringb2);
        System.out.println("Result is "+ value2);

        String stringa3 = "an";
        String stringb3 = "banana";
        String result1 = lastPart(stringa3, stringb3);
        System.out.println("The part of string after '"+ stringa3 + "' in '"+stringb3 +"' is '"+ result1 + "'");

        String stringa4 = "zoo";
        String stringb4 = "forest";
        String result2 = lastPart(stringa4, stringb4);
        System.out.println("The part of string after '"+ stringa4 + "' in '"+stringb4 +"' is '"+ result2 + "'");


    }

    /*This method finds first occurrence of stringa in stringb
    * and returns the remaining string in stringb */
    public String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if(startIndex < 0){
            return stringb;
        }
        return stringb.substring(startIndex+ stringa.length());
    }

    public static void main(String[] args) {
        Part3 p = new Part3();
        p.testing();
    }
}
