public class Part2 {

    /*This method returns how many times
    * stringa appears in stringb*/
    public int howMany(String stringa, String stringb){
        int count = 0;
        int currIndex = stringb.indexOf(stringa);
        while(currIndex !=-1){
            count+=1;
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
        }
        return count;
    }

    public void testHowMany(){
        int repititionCount = howMany("gaa", "atgaacgaattgaatc");
        System.out.println("Number of times:" + repititionCount);

        int repetitionCount2 = howMany("aa", "taaaa");
        System.out.println("Number of times:" + repetitionCount2);

    }

    public static void main(String[] args) {
        Part2 p = new Part2();
        p.testHowMany();
    }
}
