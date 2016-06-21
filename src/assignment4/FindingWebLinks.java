/**
 * Assignment 4: Finding Web Links
 * The program reads the lines from the file at this URL location, http://www.dukelearntoprogram.com/course2/data/manylinks.html, 
 * and prints each URL on the page that is a link to youtube.com.
 * 
 * @version January 26, 2016
 */

package assignment4;


import edu.duke.*;

public class FindingWebLinks {
	public static void main(String[] args) {
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for ( String item : file.words() ) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if ( pos != -1 ) {
                int beg = item.lastIndexOf("\"", pos);
              //int end = item.indexOf("\"", pos + 1);
              //System.out.println(item.substring(beg + 1, end));
                int end = item.indexOf("v=", pos + 1);
                System.out.println(item.substring(beg + 1, end + 13));
            }
        }
    }
}
