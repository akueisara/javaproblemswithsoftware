/**
 * Assignment 5: Finding All Genes
 * The program finds all the genes in a DNA string.
 * 
 * @version January 26, 2016
 */

package assignment5;

public class FindAllGenes
{
	// This method findStopIndex finds the first occurrence of each stop codon to the right of index.
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
    
    // This method printAll repeatedly looks for a gene, and if it finds one, it prints it and then looks for another gene.
	public static void printAll(String dna){
        String dnaLow = dna.toLowerCase();
        int start = 0;
        while (true) {
            int loc = dnaLow.indexOf( "atg", start );
            if ( loc == -1 ) {
                break;
            }
            int stop = findStopIndex( dnaLow, loc+3 );
            if ( stop != dna.length() ) {
                System.out.println( dna.substring(loc, stop+3) );
                start = stop + 3;
            } else {
                start = start + 3;
            }    
        }
    }
    
    // demo
	public static void testFinder() {
        
        String dna1 = "ATGAAATGAAAA";
        System.out.println("DNA string is: \n" +dna1);
        System.out.println("Genes found are:");
        printAll(dna1);
        System.out.print("\n");
        
        String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        System.out.println("DNA string is: \n" +dna2);
        System.out.println("Genes found are:");
        printAll(dna2);
        System.out.print("\n");
        
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        System.out.println("DNA string is: \n" +dna3);
        System.out.println("Genes found are:");
        printAll(dna3);
        System.out.print("\n");
    }
    
    public static void main(String[] args) {
    	testFinder();
    }
}
