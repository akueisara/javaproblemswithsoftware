/**
 * Assignment 7: Find All URLs
 * The program finds all the URLs on a web page and to print the URLs
 * 
 * @version January 28, 2016
 */

package assignment7;

import edu.duke.*;

public class findAllURLs {
	
	/** The method returns a StorageResource of strings that are all the URLs on the web page at the address given by url */
	public static StorageResource findURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			if (sub.startsWith("http")) {
				store.add(sub);
			}
			start = endQuote + 1;
		}
		return store;
	}

	public static void testURL() {
		StorageResource s1 = findURLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		int countsecurity = 0;
		int countcom = 0;
		int countendcom = 0;
		int countdot = 0;
		
		for (String link : s1.data()) {
		    
			System.out.println(link);
			
			if (link.indexOf("https") != -1){
				countsecurity++;
			}
			
			if (link.indexOf(".com") != -1){
			    countcom++;
			} 
			 
			if ((link.endsWith(".com")) || (link.endsWith(".com/")) ){
			    countendcom++;
			} 
			
			int start = 0;
			while (true) {
			    int index = link.indexOf(".", start);
			    if (index == -1) {
				    break;
			    }
			    start = start + 1;
			    countdot++;
		    }
		}
		
		System.out.println("The number of URLs: " + s1.size());
		System.out.println("The number of secure links: " + countsecurity);
		System.out.println("The number of links that have \".com\" in the link: " + countcom);
		System.out.println("The number of links that end with \".com\" or \".com/\": " + countendcom);
        System.out.println("The total number of \".\"'s that appear in all the links: " + countdot);
	}
	
	// Demo
    public static void main(String[] args) {
    	testURL();
    }
}
