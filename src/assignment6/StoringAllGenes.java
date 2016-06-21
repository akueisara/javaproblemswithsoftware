/**
 * Assignemnt 6: Storing All Genes / Processing DNA Strings
 * The progeam finds all the genes in a DNA string and then stores them using the
 * StorageResource class
 * 
 * @version January 28, 2016
 */

package assignment6;

import edu.duke.*;
import java.io.File;
import java.util.ArrayList;

public class StoringAllGenes
{
	/** The method finds the first occurrence of each stop codon to the right of index */
	public static int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("tga", index);
        if (stop1 == -1 || (stop1 - index) % 3 != 0) {
            stop1 = dna.length();    
        }
		int stop2 = dna.indexOf("taa", index);
		if (stop2 == -1 || (stop2 - index) % 3 != 0) {
            stop2 = dna.length();    
        }
		int stop3 = dna.indexOf("tag", index);
		if (stop3 == -1 || (stop3 - index) % 3 != 0) {
            stop3 = dna.length();    
        }
        return Math.min(stop1, Math.min(stop2,stop3));
    }  
    
    /** The method creates and returns a StorageResource containing the genes found */
	public static StorageResource storeAll(String dna){
        String dnaLow = dna.toLowerCase();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int loc = dnaLow.indexOf( "atg", start );
            if ( loc == -1 ) {
                break;
            }
            int stop = findStopIndex( dnaLow, loc+3 );
            if ( stop != dna.length() ) {
                //System.out.println( dna.substring(loc, stop+3) );
                store.add(dna.substring(loc, stop+3));
                start = stop + 3;
            } else {
                start = start + 3;
            }    
        }
        return store;
    }
    
    /** The method returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA */
	public static float cgRatio(String dna){
        dna = dna.toLowerCase();  
        int len = dna.length();
        int CGCount = 0;

        for(int i=0; i<len; i++){

            if(dna.charAt(i) == 'c' || dna.charAt(i) == 'g')
                CGCount++;

        }//end for loop

        //System.out.println("CGCount " + CGCount + " Length: " + len + " Ratio: " + (float)CGCount/len);
        return ((float)CGCount)/len;
    }
    
	/** Find how many times the codon CTG appears in a strand of DNA */
	public static void FindCTG(StorageResource s){
        int countctg = 0;
        
        for (String gene : s.data()) {
            gene = gene.toLowerCase();
            int start = 0;    
            while (true) {
                int pos = gene.indexOf("ctg", start);
                if (pos == -1) {
                    break;
                }
                countctg += 1;
                start = pos + 3;
            }
        }    
        
        System.out.println("CTG: " + countctg);
    }
    
    /** The method processes all the strings in sr */
	public static void printGenes(StorageResource sr){

        int longerthan60 = 0;
        int CGGreaterthan35 = 0;
        int longestgene = 0;
        int tempgene = 0;
        ArrayList<String> longerthansixty = new ArrayList<String>();
        ArrayList<String> CGGreaterthanthirtyfive = new ArrayList<String>();
        
        for (String gene : sr.data()) {
			tempgene = gene.length();    
			if (longestgene<tempgene){
			    longestgene = tempgene;
			 }
			if (gene.length()>60){
			    longerthan60++;
			    longerthansixty.add(gene);
			}
			if (cgRatio(gene)>0.35){
			    CGGreaterthan35++;
			    CGGreaterthanthirtyfive.add(gene);
			}
		}

        System.out.println("dnaStore.size: " + sr.size());
        System.out.println("There are " + sr.size() + " genes. ");
        System.out.println("\nThere are " + longerthan60 + " genes longer than 60.");
        for (String gene: longerthansixty){
        	System.out.println(gene);
        }
        System.out.println("\nThere are " + CGGreaterthan35 + " genes with CG ratio greater than 0.35.");
        for (String gene: CGGreaterthanthirtyfive){
        	System.out.println(gene);
        }
        
        System.out.println("\nlongestgene.size: " + longestgene);
    }
    
	public static void testStorageFinder() {
		DirectoryResource dr = new DirectoryResource();
        StorageResource dnaStore = new StorageResource(); 
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            dnaStore = storeAll(s); 
            printGenes(dnaStore);
            FindCTG(dnaStore);
        }
	}
    
    // Demo
    public static void main(String[] args) {
    	testStorageFinder();
    }
}
