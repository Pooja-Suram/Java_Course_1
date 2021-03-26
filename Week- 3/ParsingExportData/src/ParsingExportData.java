import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        String countryInformation = countryInfo(parser, "Nauru");
        System.out.println(countryInformation);

        listExportersTwoProducts(parser, "fish", "nuts");

        int countriesCount = numberOfExporters(parser, "sugar");
        System.out.println(countriesCount);

        bigExporters(parser, "$999,999,999,999");
    }

    /*This method returns country information
    such as country name, exports, export value
    if country is valid
    else returns 'NOT FOUND' */
    public String countryInfo(CSVParser parser, String country){
        String countryInformation = "";
        FileResource fr = new FileResource();
        parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            if(country.equals(record.get("Country"))){
                countryInformation = countryInformation+ country +
                                        ": "+ record.get("Exports")+
                                        ": "+record.get("Value (dollars)");
                return countryInformation;
            }
        }
        return "NOT FOUND";
    }

    /*This method prints names of countries
    * who export both export item1 and item2 */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }


    /*This method returns number of countries that export exportItem */
    public int numberOfExporters(CSVParser parser, String exportItem){
        int countryCount = 0;
        FileResource fr = new FileResource();
        parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                countryCount+=1;
            }
        }
        return countryCount;
    }

    /*This method prints country names whose value amount is
    * greater than given amount */
    public void bigExporters(CSVParser parser, String amount){
        FileResource fr = new FileResource();
        parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            String exportValue = record.get("Value (dollars)");
            if(exportValue.length() > amount.length()){
                System.out.println(record.get("Country") + " "+ exportValue);
            }
        }
    }

    public static void main(String[] args) {
        ParsingExportData p = new ParsingExportData();
        p.tester();
    }
}
