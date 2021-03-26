import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class MiniProject {

    /*This method prints:
     total boy names & *total girls names
     total boys births  & total girls births
     total births*/
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBirthsBoys = 0;
        int totalBoysNames = 0;
        int totalBirthGirls = 0;
        int totalGirlsNames = 0;

        for(CSVRecord record: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if(record.get(1).equals("B")){
                totalBirthsBoys+= totalBirths;
                totalBoysNames+=1;
            }
            else{
                totalBirthGirls+=totalBirths;
                totalGirlsNames+=1;
            }

        }
        System.out.println("total births are :"+ totalBirths);
        System.out.println("Total girls births: "+ totalBirthGirls);
        System.out.println("Total boys Births: "+ totalBirthsBoys);
        System.out.println("Number of boys names: "+ totalBoysNames);
        System.out.println("number of girls names: "+ totalGirlsNames);
    }


    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }


    /* this method returns file name when
    * year is given*/
    public File getFileNameWithYear(int year){
        File file = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            String fileName = f.getName();
            if(fileName.contains(Integer.toString(year))){
                file = f;
            }
        }
        return file;
    }



    /*This method returns rank of name
    * for given gender, where rank 1 is the name with
    * largest number of births */
    public int getRank(int year, String name, String gender){
        int rank = 0;
        File file = getFileNameWithYear(year);
        rank = getRankInFile(file, name, gender);
        return rank;
    }


    /*This returns rank of given name in given
    * file for given gender */
    public int getRankInFile(File file, String name, String gender){
        int girlRank = 0;
        int boyRank = 0;
        FileResource fr = new FileResource(file);
        for(CSVRecord record: fr.getCSVParser()){
            String currName = record.get(0);
            if(gender.equals(record.get(1))){
                if(gender.equals("M")){
                    boyRank+=1;
                    if(name.equals(currName)){
                        return boyRank;
                    }
                }
                else{
                    girlRank+=1;
                    if(name.equals(currName)){
                        return girlRank;
                    }
                }
            }
        }
        return -1;
    }


    public void testGetRank(){
        int rank = getRank(1971, "Frank", "M");
        System.out.println("Rank is "+ rank);
    }


    /*This method returns name of person
    * in given file with given rank and gender */
    public String getName(int year, int rank, String gender){
        String name = null;
        File file = getFileNameWithYear(year);
        name = getNameInFile(file, rank, gender);
        return name;
    }

    public String getNameInFile(File f, int rank, String gender){
        FileResource fr = new FileResource(f);
        int girlRank = 0;
        int boyRank = 0;
        for(CSVRecord record: fr.getCSVParser()){
            String name = record.get(0);
            if(gender.equals(record.get(1))){
                if(gender.equals("M")){
                    boyRank+=1;
                    if(rank == boyRank){
                        return name;
                    }
                }
                else{
                    girlRank+=1;
                    if(rank == girlRank){
                       return name;
                    }
                }
            }
        }
        return "NO NAME";
    }

    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }

    /*This method determines what name that person would have
    * if that person was born in another year(newYear) */
    /*This is calculated by,
    * 1. checking rank of person in given year
    * 2. checking name with same rank in new year given */
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        File currentFile = getFileNameWithYear(year);
        int currentrank = getRankInFile(currentFile, name, gender);
        File newFile = getFileNameWithYear(newYear);
        String newName = getNameInFile(newFile, currentrank, gender);
        System.out.println(name + " born in "+ Integer.toString(year) + " would be "+ newName + " if she was born in "+ Integer.toString(newYear));

    }

    /*This method selects range of files,
    * returns an year which has highest rank for
    * given name and gender */
    public int yearOfHighestRank(String name, String gender){
        int highestYear = -1;
        Integer highestRank = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            String fileName = f.getName();
            int currentRank = getRankInFile(f, name, gender);
            if(highestRank == null){
                highestRank = currentRank;
                highestYear = Integer.parseInt(fileName.substring(3,7));
            }
            else {
                if ((currentRank < highestRank) && currentRank != -1) {
                    highestRank = currentRank;
                    highestYear = Integer.parseInt(fileName.substring(3, 7));
                }
            }
        }
        return highestYear;
    }

    public void testYearOfHighestRanks(){
        int year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }


    /*This method selects range of files,
    * returns average rank of given name with gender
    * in all selected files */
    public double getAverageRank(String name, String gender){
        int rankSum = 0;
        int noOfRanks= 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            int rank = getRankInFile(f, name, gender);
            if(rank !=-1){
                rankSum += rank;
                noOfRanks += 1;
            }
        }
        if(rankSum == 0 || noOfRanks == 0){
            return -1.0;
        }
        else{
            return (double)rankSum/noOfRanks;
        }
    }

    public void testGetAverageRank(){
        double rank = getAverageRank("Robert", "M");
        System.out.println("Average rank is " + rank);
    }


    /*This method returns total number of births
    * of those names with same gender and same year
    * who are ranked higher than name */
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int birthsRankedHigher = 0;
        File f = getFileNameWithYear(year);
        FileResource fr = new FileResource(f);
        int rank = getRankInFile(f, name, gender);
        int currentRank = 1;
        for(CSVRecord record: fr.getCSVParser()){
            if(currentRank == rank){
                return birthsRankedHigher;
            }
            if(gender.equals(record.get(1))){
                birthsRankedHigher += Integer.parseInt(record.get(2));
                currentRank+=1;
            }
        }
        return birthsRankedHigher;
    }

    public void testGetTotalBirthsRankedHigher(){
        int birthsRankedHigher = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(birthsRankedHigher);
    }


    public static void main(String[] args) {
        MiniProject project = new MiniProject();
        //project.testTotalBirths();
        project.testGetRank();
        //project.testGetName();
        //project.whatIsNameInYear("Owen", 1974, 2014, "M");
        //project.testYearOfHighestRanks();
        //project.testGetAverageRank();
        //project.testGetTotalBirthsRankedHigher();

    }
}
