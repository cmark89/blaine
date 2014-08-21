import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenScraper {
	final String ASSET_PATH = "assets/";
	public static void main(String[] args) {
		ScreenScraper s = new ScreenScraper();
	}
	public ScreenScraper() {
		test();
	}
	
	// Scrape the provided image for the image of the 
	public void test() {
		BufferedImage image = loadImage("mainMenu_eyes");
		if(image == null)
			System.out.println("Failed to load image");
		else
			System.out.println("Loaded image successfully");
	}
	
	private BufferedImage loadImage(String path) {
		/*
		try {
			return ImageIO.read(new File(ASSET_PATH + path +".png"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		*/
		
		BufferedImage raw;
		try {
			raw = ImageIO.read(new File(ASSET_PATH + path +".png"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		BufferedImage converted = new BufferedImage(raw.getWidth(), raw.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		converted.getGraphics().drawImage(raw, 0, 0, null);
		converted.getGraphics().dispose();
		return converted;
		
		// Load it using the type we agreed upon
	}
	
	public boolean findImage(BufferedImage large, String smallName, Point p) {
		return findImage(large, loadImage(smallName), p);
	}
	
	public boolean findImage(BufferedImage large, String smallName, Rectangle searchArea, Point p) {
		return findImage(large.getSubimage(searchArea.x, searchArea.y, 
				searchArea.width, searchArea.height), loadImage(smallName), p);
	}
	
	public boolean findImage(BufferedImage large, BufferedImage small, Point p) {
		// Save a screenshot of what we're currently looking at		
		
		boolean found = false;
		System.out.println("Target color: " + small.getRGB(0,0));
		outerloop:
		for(int x1 = 0; x1 < large.getWidth() - small.getWidth(); x1++) {
			for(int y1 = 0; y1 < large.getHeight() - small.getHeight(); y1++) {
				
				innerloop:
				for(int x2 = 0; x2 < small.getWidth(); x2++) {
					for(int y2 = 0; y2 < small.getHeight(); y2++) {
						if(large.getRGB(x1 + x2, y1 + y2) == small.getRGB(x2,y2))
						{
							System.out.println("Matched pixel at " + (x1 + x2) + "," 
							+ (y1 + y2));

							if(x2 == 0 && y2 == 0) p.setLocation(x1,y1);
							if(x2 == small.getWidth() -1 && y2 == small.getHeight() - 1) {
								// This is the final pixel and we have a match, so set found
								// and break the loop
								found = true;
								break outerloop;
							} else {
								continue;
							}
						}
						else {
							break innerloop;
						}
					}
				} // end inner loop
			}
		} // end outer loop
		
		// debug:
		if(found) {
			System.out.println("Found image at " + p.x + "," + p.y);
		} else {
			System.out.println("Could not find image");
		}
		return found;
	}
}
