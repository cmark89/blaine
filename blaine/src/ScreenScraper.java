import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.sikuli.script.*;

public class ScreenScraper {
	final String ASSET_PATH = "assets/";
	
	Screen screen;
	
	public ScreenScraper(Screen s) {
		screen = s;
	}
	
	// Check if the screen contains the given image
	public boolean canFindImage(String path) {
		if(findImage(path) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Match findImage(String path) {
		Match m = null;
		try {
			m = screen.find(ASSET_PATH + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public Iterator<Match> findAll(String path) {
		Iterator<Match> m = null;
		try {
			m = screen.findAll(ASSET_PATH + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public Rectangle getBounds(String path) {
		Match m = findImage(path);
		if(m != null) {
			return new Rectangle(m.x, m.y, m.w, m.h);
		} else {
			return new Rectangle();
		}
	}
	
}
