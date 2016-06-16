/**
 * Write a description of class SortingAllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package assignment6;

import edu.duke.*;
import java.io.File;

public class StoringAllGenes
{
    public int findStopIndex(String dna, int index){
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
    
    public StorageResource storeAll(String dna){
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
    
    public float calCGRatio(String gene){
        gene = gene.toLowerCase();  
        int len = gene.length();
        int CGCount = 0;

        for(int i=0; i<len; i++){

            if(gene.charAt(i) == 'c' || gene.charAt(i) == 'g')
                CGCount++;

        }//end for loop

        //System.out.println("CGCount " + CGCount + " Length: " + len + " Ratio: " + (float)CGCount/len);
        return ((float)CGCount)/len;
    }
    
    public void FindCTG(StorageResource s){
        int longerthan60 = 0;
        int CGGreaterthan35 = 0;
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
    
    public void printGenes(StorageResource sr){

        int longerthan60 = 0;
        int CGGreaterthan35 = 0;
        int longestgene = 0;
        int tempgene = 0;
        
        for (String gene : sr.data()) {
			//System.out.println(gene);
			tempgene = gene.length();    
			if (longestgene<tempgene){
			    longestgene = tempgene;
			 }
			if (gene.length()>60){
			    longerthan60++;
			}
			if (calCGRatio(gene)>0.35){
			    CGGreaterthan35++;
			}
		}

        System.out.println("dnaStore.size: " + sr.size());
        System.out.println("\n There are " + sr.size() + " genes. ");
        System.out.println("There are " + longerthan60 + " genes longer than 60.");
        System.out.println("There are " + CGGreaterthan35 + " genes with CG ratio greater than 0.35.");
        System.out.println("longestgene.size: " + longestgene);
    }
    
    public void testStorageFinder() {
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
}
