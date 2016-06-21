/**
 * Assignment 2: Image Inversion
 * The program creates new images that are photographic negatives (or inverted images) of selected images and saves these new images.
 * 1. The method named makeInversion that has one parameter, an image, and returns a new image that is the inverse of the original image.
 * 2. The another method called selectAndConvert handles the batch processing of files. This method allows the user to select several files and display the images.
 * 
 * @version January 21, 2016
 */

package assignment2;

import edu.duke.*;
import java.io.*;

public class BatchInversions {
	//I started with the image I wanted (inImage)
	public static ImageResource makeInversion(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//set pixel's red to 255- inPixel.getRed()
			pixel.setRed(255- inPixel.getRed());
			//set pixel's green
			pixel.setGreen(255- inPixel.getGreen());
			//set pixel's blue
			pixel.setBlue(255- inPixel.getBlue() );
		}
		//outImage is your answer
		return outImage;
	}
	
	public static void selectAndConvert()
	{
		DirectoryResource directory = new DirectoryResource();
		for(File file: directory.selectedFiles())
		{
			ImageResource image = new ImageResource(file);
			ImageResource invertedImage = makeInversion(image);
			String fname = image.getFileName();
			String newName = "inverted-"+ fname;
			invertedImage.setFileName("./images/" + newName);
			invertedImage.draw();
			invertedImage.save();		
		}
	}

	// demo
	public static void main(String[] args) {
		selectAndConvert();
	}   
}