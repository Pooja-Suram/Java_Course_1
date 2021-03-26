import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ParsingWeatherData {

    /*This method returns CSV record with coldest temperature
    * in file */
    public CSVRecord coldestHourInFile(CSVParser parser, String f){
        FileResource fr;
        if(f== null) {
            fr = new FileResource();
        }
        else{
            fr = new FileResource(f);
        }
        parser = fr.getCSVParser();
        CSVRecord coldestSoFar = null;
        for(CSVRecord record: parser){
            coldestSoFar = coldestRecord(coldestSoFar, record);
            }
        return coldestSoFar;
    }

    //This method compares temperature of both records
    // Returns record with coldest temperature
    public CSVRecord coldestRecord(CSVRecord coldestSoFar, CSVRecord current) {
        if (coldestSoFar == null) {
            return current;
        } else {
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp != -9999 && currentTemp < coldestTemp) {
                return current;
            }
            return coldestSoFar;
        }
    }

    /*This method iterates through all the selected files
    * Returns file name which has coldest temperature */
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String coldestFile = null;
        Double coldestTemp = null;

        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser(), f.getName());
            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            if(coldestFile == null){
                coldestFile = f.getName();
                coldestTemp = currentTemp;
            }
            else{
                if(currentTemp!= -9999 && currentTemp< coldestTemp){
                    coldestTemp = currentTemp;
                    coldestFile = f.getName();
                }

            }
        }
        System.out.println(coldestFile);
        return coldestFile;
    }


    public void testFileWithColdestTemperature(){
        String coldestFile = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFile);
        FileResource fr = new FileResource(coldestFile);
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser(), coldestFile);

        double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
        System.out.println("Coldest temperature on that day was " +coldestTemp);

        System.out.println("All the temperatures on the coldest day were :");
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            System.out.println(record.get("DateUTC") + ": "+ record.get("TemperatureF"));
        }

    }


    /*This method returns the CSV Record with lowest humidity */
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityRecord = null;
        Double lowestHumidity = null;
        for(CSVRecord currentRecord: parser){
            lowestHumidityRecord = getLowestHumidity(lowestHumidityRecord, currentRecord);
        }
        return lowestHumidityRecord;
    }

    /*This method compares given two records
    * and returns record with lowest humidity */
    public CSVRecord getLowestHumidity(CSVRecord lowestHumidityRecord, CSVRecord currentRecord){
        if(currentRecord.get("Humidity").equals("N/A")){
            return lowestHumidityRecord;
        }

        String humidity = currentRecord.get("Humidity");

        if(lowestHumidityRecord == null && (!humidity.equals("N/A"))){
            lowestHumidityRecord = currentRecord;
        }
        else if(!humidity.equals("N/A")){
            double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity"));
            double currentHumidity = Double.parseDouble(humidity);
            if(currentHumidity < lowestHumidity){
                lowestHumidityRecord = currentRecord;
            }
        }
        return lowestHumidityRecord;
    }



    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+ lowestHumidityRecord.get("Humidity") + " at "+ lowestHumidityRecord.get("DateUTC"));
    }


    /*This method returns CSVRecord that has the
    lowest humidity over all selected files*/
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidityRecord = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidityRecord = getLowestHumidity(lowestHumidityRecord, currentRecord);
        }
        return  lowestHumidityRecord;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was" +
                lowestHumidityRecord.get("Humidity") +
                " at " +
                lowestHumidityRecord.get("DateUTC"));
    }


    /*This method returns average temperature in selected */
    public double averageTemperatureInFile(CSVParser parser){
        //FileResource fr = new FileResource();
         //parser = fr.getCSVParser();
         double totalTemp = 0.0;
         int numOfRecords = 0;
         for(CSVRecord record: parser){
             double currTemp = Double.parseDouble(record.get("TemperatureF"));
             if(currTemp != -9999){
                 numOfRecords+=1;
                 totalTemp+= currTemp;
             }
         }
         double averageTemp = 0.0;
         averageTemp = totalTemp/numOfRecords;
         return averageTemp;
    }


    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+ avgTemp);
    }

    /*This returns average of temperatures *of those records whose
    * humidity is greater than or equal to value */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totalTemp = 0;
        int numOfRecords = 0;
        for(CSVRecord record: parser){
            String humidity = record.get("Humidity");
            if((!humidity.equals("N/A")) && Double.parseDouble(humidity)>=value ){
                totalTemp += Double.parseDouble(record.get("TemperatureF"));
                numOfRecords+=1;
            }
        }
        if(numOfRecords>0)
        return totalTemp/numOfRecords;

        return 0;
    }



    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(averageTemperature>0)
            System.out.println("Average Temperature when high Humidity is "+ averageTemperature);
        else{
            System.out.println("No temperatures with that humidity");
        }
    }


    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord parser = coldestHourInFile(fr.getCSVParser(), null);
        System.out.println("time at which coldest temperature recorded is: " +
                parser.get("TimeEST") +
                "with " +
                parser.get("TemperatureF"));
    }

    public static void main(String[] args) {
        ParsingWeatherData data = new ParsingWeatherData();
        //data.testColdestHourInFile();

        //data.testFileWithColdestTemperature();

        //data.testLowestHumidityInManyFiles();
        //data.testAverageTemperatureInFile();

        data.testAverageTemperatureWithHighHumidityInFile();
        //data.testLowestHumidityInFile();
    }


}
