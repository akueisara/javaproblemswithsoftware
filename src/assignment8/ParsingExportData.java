/**
 * Assignment 8: Parsing Export Data
 * 
 * @version February 8, 2016 
 */

package assignment8;

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
	
	/** The method prints the number of countries that export exportItem */
	public static void numberOfExporters(CSVParser parser, String exportItem) {
		//for each row in the CSV File
		int count = 0; 
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			//Check if it contains exportOfInterest
			if (export.contains(exportItem)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
				count++;
			}
		}
		System.out.println("How many countries export " + exportItem + " : " + count);
	}
	
	/** The method prints the names of all the countries that have both exportItem1 and exportItem2 as export items. */
	public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			//Check if it contains exportOfInterest
			if (export.contains(exportItem1) && export.contains(exportItem2)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}
	
	/** This method prints a string of information about the country or prints “NOT FOUND” if there is no information about the country */
	public static void countryInfo(CSVParser parser, String country) {		
		boolean found = false;
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String getCountry = record.get("Country");
			//Check if it contains exportOfInterest
			if (getCountry.contains(country)) {
				//If so, write down the "Country" from that row
				String export = record.get("Exports");
				System.out.println(getCountry + " : " + export);
				found = true;
			}
		}
		if (!found) System.out.println("NOT FOUND");
	}
	
	/** This method prints the names of countries and their Value amount for all countries whose Value (dollars) string is longer than the amount string. */
	public static void bigExporters(CSVParser parser, String amount) {
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String value = record.get("Value (dollars)");
			//Check if it contains exportOfInterest
			if (value.length() >("$"+amount).length() ) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country + " : " + value);

			}
		}
	}
	
	// test
	public static void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		countryInfo(parser, "Germany");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "gold", "diamonds");
		parser = fr.getCSVParser();
		numberOfExporters(parser, "gold");
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999");
	}
	
	// demo
	public static void main(String[] args) {
		tester();
	}
}
