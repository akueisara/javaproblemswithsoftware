/**
 * Assignment 1: Batch Grayscale
 * The program batch processes several images, and creates and saves new images that are grayscale versions of each image.
 * 1. The program lets the user select multiple image files
 * 2. For each image, it creates a new image that is a grayscale version of the original image
 * 3. For each image, it saves the grayscale image in a new file with the same filename as the original image, 
 * but with the word ¡§gray-¡¨ in front of the filename. For example, if the original file was named lion.png, 
 * the new image would be a grayscale image and be named gray-lion.png.
 *  
 * @version January 21, 2016
 */

package assignment1;

import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
	//I started with the image I wanted (inImage)
	public static ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
		//outImage is your answer
		return outImage;
	}

	// demo
	public static void main(String[] args) {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource grayscale = makeGray(inImage);
			String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            grayscale.setFileName("./images/" + newName);
            grayscale.draw();
            grayscale.save();
		}
	}   
}