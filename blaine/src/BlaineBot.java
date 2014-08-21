import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class BlaineBot {
	BlaineWindow window;
	ScreenScraper scraper;
	Robot bot;
	boolean botRunning = false;
	public boolean botIsRunning() {
		return botRunning;
	}
	
	public BlaineBot(BlaineWindow w) {
		// Initialize logic here
		window = w;
		scraper = new ScreenScraper();
		try {
			bot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		botRunning = true;
		Thread t = new Thread(new BlaineJob_MainLoop());
		t.start();
		System.out.println("All aboard!");
	}
	
	public void stop() {
		botRunning = false;
		System.out.println("Last stop!");
	}
	
	private BufferedImage screen() {
		return bot.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
	}
	
	
	
	class BlaineJob_MainLoop implements Runnable {

		@Override
		public void run() {
						
			// We must determine the initial state, but for now assume that we're on the main menu
			window.forceMinimize();
			int currentScreen = 0;
			Point p = new Point();
			while(currentScreen == 0) {
				sleep(1000);
				if(scraper.findImage(screen(), "mainMenu_eyes", p)) {
					// We found a point, but we now need to offset it by 185 to the right
					p.x += 185;
					System.out.println("MOVE MOUSE TO: " + p.x + "," + p.y);
					bot.mouseMove(p.x, p.y);
					sleep(50);
					click();
					
					currentScreen++;
				}
			}
			
			while(currentScreen == 1) {
				p = new Point(0,0);
				// Try to find the select bar to make sure we're on the right mode
				if(scraper.findImage(screen(), "modeSelect_bar", p)) {
					p.x += 57;
					bot.mouseMove(p.x, p.y);
					sleep(50);
					click();
					
					// Now start the game
					scraper.findImage(screen(), "modeSelect_play", p);
					p.y += 46;
					bot.mouseMove(p.x, p.y);
					sleep(50);
					click();
					
					currentScreen++;
				}
			}
			
			sleep(5000);
			// We should now be in the game!
			while(currentScreen == 2) {
				// Start by taking all the cards, you greedy jerk
				bot.mouseMove(570,655);
				sleep(50);
				click();
				
				bot.mouseMove(724,655);
				sleep(50);
				click();
				
				bot.mouseMove(855,655);
				sleep(50);
				click();
				
				bot.mouseMove(1222, 672);
				sleep(50);
				click();
				
				currentScreen++;
			}
			
			// We are now in the main game loop
			boolean ourTurn = false;
			while(true) {
				sleep(200);
				System.out.println("Waiting for turn...");
				if(!ourTurn && scraper.findImage(screen(), "game_turnHint", 
						new Rectangle(605, 573, 189, 41), p)) {
					ourTurn = true;
				}
				
				while(ourTurn) {
					System.out.println("Blaine's turn!");
					sleep(150);
					// Just draw cards...
					bot.mouseMove(1225, 465);
					sleep(50);
					click();
					sleep(150);
					click();
					sleep(150);
					
					// Not our turn anymore
					System.out.println("Turn over.");
					ourTurn = false;
				}
			}
		}
		
		private void sleep(int milli) {

			try {
				Thread.sleep(milli);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void click() {
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			sleep(50);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	}
}
