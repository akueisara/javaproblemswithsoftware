/**
 * Assignment 3: Finding a Gene
 * The program finds and prints a gene in a strand of DNA
 * and also prints the stop codon that identifies the gene.
 * 
 * @version January 26, 2016
 */

package assignment3;

import edu.duke.*;
import java.io.*;

public class FindAGene {
	
	// It returns a gene for a protein from the String dna 
	public static String findProtein(String dna) {
		int start = dna.toLowerCase().indexOf("atg");
		if (start == -1) {
			return ""; // no start codon found
		}
		int stop1 = dna.toLowerCase().indexOf("tag", start+3);
		int stop2 = dna.toLowerCase().indexOf("tga", start+3);
		int stop3 = dna.toLowerCase().indexOf("taa", start+3);
		if ((stop1 - start) % 3 == 0) {
			return dna.substring(start, stop1+3);
		}
		else if ((stop2 - start) % 3 == 0){
		    return dna.substring(start, stop2+3);
		}
		else if ((stop3 - start) % 3 == 0){
		    return dna.substring(start, stop3+3);
		}
		else{    
			return "";
		}
	}
	
	// The method prints the stop codon found in the gene
	public static void testing() {
		//String a = "cccatggggtttaaataataataggagagagagagagagttt";
		//String ap = "atggggtttaaataataatag";
		//String a = "atgcctag";
		//String ap = "";
		String a = "ATGCCCTAG";
	    String ap = "ATGCCCTAG";
		//String a = "acatgataacctaag";
		//String ap = "";
		//String a = "AATGCTAGTTTAAATCTGA";
		//String ap = "ATGCTAGTTTAAATCTGA";
		//String a = "ataaactatgttttaaatgt";
		//String ap = "atgttttaa";
		String result = findProtein(a);
		String StopCodon = stopCodon(a);
		if (ap.equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
			System.out.println("Stop codon is " + StopCodon);
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}
	
	//  This method returns the stop codon of the gene. 
	//  It returns the empty string if the parameter is not a gene.
	public static String stopCodon(String dna) {
        String answer = findProtein(dna);
        int size = answer.length();
        if ( size > 6 ) {
            return answer.substring(size - 3, size);
        }
        else {
            return "";
        }
    }
    
	public static void printAllStarts(String dna){
        int start = 0;
        while (true){
            int loc = dna.indexOf("atg",start);
            if (loc == -1){
                break;
            }
            System.out.println("starts at" + loc);
            start = loc + 3;
        }
    }

    // demo
 	public static void main(String[] args) {
 		testing();
 		
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
			String result = findProtein(s);
			System.out.println("found " + result);
		}
	}
}
