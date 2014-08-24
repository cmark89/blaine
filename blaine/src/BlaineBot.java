import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class BlaineBot {
	BlaineWindow window;
	ScreenScraper scraper;
	Robot bot;
	Thread thread;
	Screen screen;
	
	boolean botRunning = false;
	boolean playOnline = false;
	
	public boolean botIsRunning() {
		return botRunning;
	}
	
	private enum GameScreen {
		NONE,
		LOADING_SCREEN,
		TITLE_SCREEN,
		MODE_SELECT_SCREEN,
		GAME_SCREEN
	}
	GameScreen currentGameScreen;
	
	// Variables for playing the game
	Rectangle cardZone = new Rectangle();
	Rectangle handZone = new Rectangle();
	Point deckPosition = new Point();
	Point ticketDeckPosition = new Point();
	Point donePosition = new Point();
	
	final float DEFAULT_SCREEN_SEARCH_TIME = .3f;
	
	Rectangle wholeScreen;
	Rectangle turnArea;
	
	public BlaineBot(BlaineWindow w) {
		// Initialize logic here
		window = w;
		screen = new Screen();
		screen.setAutoWaitTimeout(DEFAULT_SCREEN_SEARCH_TIME);
		scraper = new ScreenScraper(screen);
		wholeScreen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		turnArea = new Rectangle(wholeScreen.x, (wholeScreen.height / 3) * 2, 
				wholeScreen.width, wholeScreen.height / 3);
		try {
			bot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		botRunning = true;
		thread = new Thread(new BlaineJob_MainLoop());
		thread.start();
		System.out.println("All aboard!");
	}
	
	public void stop() {
		botRunning = false;
		System.out.println("Last stop!");
	}
	
	private BufferedImage screenshot() {
		return bot.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
	}
	
	class BlaineJob_MainLoop implements Runnable {

		@Override
		public void run() {
						
			// We must determine the initial state, but for now assume that we're on the main menu
			window.forceMinimize();
			currentGameScreen = GameScreen.NONE;
			
			// Here's the main loop where we look at the current game screen and 
			// decide what to do
			while(botRunning) {
				getCurrentGameScreen();
				if(currentGameScreen != GameScreen.NONE) {
					if(currentGameScreen == GameScreen.LOADING_SCREEN) {
						System.out.println("Run load screen routine");
						BotRoutine_LoadingScreen();
					} else if(currentGameScreen == GameScreen.TITLE_SCREEN) {
						System.out.println("Run main menu routine");
						BotRoutine_MainMenu();
					} else if(currentGameScreen == GameScreen.MODE_SELECT_SCREEN) {
						System.out.println("Run mode select routine");
						BotRoutine_ModeSelect();
					} else if(currentGameScreen == GameScreen.GAME_SCREEN) {
						System.out.println("Run game routine");
						BotRoutine_PlayGame();
					}
				}
				
				sleep(250);
			}
		}
		
		// Routines to perform based on current game screen.  MUST RETURN TO THE MAIN LOOP
		
		private void BotRoutine_LoadingScreen() {
			// We're on the loading screen, so the only thing to do is wait
			sleep(1000);	// Wait one second and then return to the main loop
			return;
		}
		
		private void BotRoutine_MainMenu() {
			// We're on the main menu, so we need to navigate to the game screen
			Rectangle r = scraper.getBounds("mainmenu_key.png");
			Point p = new Point(r.x + r.width / 2, r.y + r.height / 2);
			System.out.println("Move mouse to " + p.x + "," + p.y);
			bot.mouseMove(p.x, p.y);
			sleep(25);
			click();
			
			sleep(200);
			return;
		}
		
		private void BotRoutine_ModeSelect() {
			// We're on the game mode select screen
			// Time for some real work
			Point p = new Point();
			Rectangle r = new Rectangle();
			// Online button and offline button too similar; we need to use their pressed
			// forms to make sure that they're differentiated properly
			if(playOnline) {
				Settings.MinSimilarity = .9f;
				// We want to play online, so make sure the online option is selected
				if(scraper.canFindImage("modeselect_onlineButton.png")) {
					// We found the online button, so click it
					r = scraper.getBounds("modeselect_onlineButton.png");
					p = new Point(r.x + r.width / 2, r.y + r.height / 2);
					bot.mouseMove(p.x, p.y);
					click();
				}
			} else {
				Settings.MinSimilarity = .9f;
				// We want to play offline, so make sure the offline option is selected
				if(scraper.canFindImage("modeselect_soloButton.png")) {
					// We found the online button, so click it
					r = scraper.getBounds("modeselect_soloButton.png");
					p = new Point(r.x + r.width / 2, r.y + r.height / 2);
					bot.mouseMove(p.x, p.y);
					click();
				}
				
			}
			sleep(250);
			// Finally, click the play button
			Settings.MinSimilarity = .7f;
			r = scraper.getBounds("modeselect_startButton.png");
			p = new Point(r.x + r.width / 2, r.y + r.height / 2);
			bot.mouseMove(p.x, p.y);
			click();
			
			sleep(1000);
			return;
		}
		
		private void BotRoutine_PlayGame() {
			// This is going to be a monster...
			
			cardZone = new Rectangle();
			handZone = new Rectangle();
			deckPosition = new Point();
			ticketDeckPosition = new Point();
			donePosition = new Point();
			
			// First, have to wait until the game actually starts and the board appears
			// We know the setup has begun when the grayed out Done button appears
			boolean waiting = true;
			while(!scraper.canFindImage("doneButton.png")) {
				sleep(250);
			}
			
			// Okay, we've started the game.  Time to setup some values
			Rectangle r = new Rectangle();
			r = scraper.getBounds("doneButton.png");
			donePosition = new Point(r.x + r.width / 2, r.y + r.height / 2);
			r = scraper.getBounds("ticketDeck.png");
			ticketDeckPosition = new Point(r.x + r.width / 2, r.y + r.height / 2);
			r = scraper.getBounds("deck.png");
			deckPosition = new Point(r.x + r.width / 2, r.y + r.height / 2);
			
			// Get the rectangle of the card area to speed up future searches
			int x1, x2, y1, y2 = 0;
			r = scraper.getBounds("cardzone_top.png");
			x1 = r.x;
			y1 = r.y;
			r = scraper.getBounds("cardzone_bottom.png");
			x2 = r.x + r.width;
			y2 = r.y + r.height;
			
			cardZone = new Rectangle(x1,y1, x2 - x1, y2 - y1);
			
			// Print some diagnostic information here
			System.out.println("Done button at: " + donePosition.x + "," + donePosition.y);
			System.out.println("Ticket deck at: " + ticketDeckPosition.x + "," 
					+ ticketDeckPosition.y);
			System.out.println("Deck at: " + deckPosition.x + "," + deckPosition.y);
			System.out.println("Card zone: " + cardZone.x + "," + cardZone.y + "," +
					cardZone.width + "," + cardZone.height);
			
			// Okay, we now need to take all of our starting tickets for fun
			Iterator<Match> tickets = scraper.findAll("ticket.png");
			while(tickets.hasNext()) {
				Match m = tickets.next();
				// Find the ticket and click it
				bot.mouseMove(m.getCenter().x, m.getCenter().y);
				click();
				sleep(100);
			}
			
			// click the done button
			bot.mouseMove(donePosition.x, donePosition.y);
			click();
			
			boolean gameOver = false;
			while(!gameOver) {
				// Wait until our turn
				waitUntilTurn();
				
				System.out.println("It's our turn now!");
				// It's our turn, so draw two cards
				drawCard();
				sleep(100);
				drawCard();
				sleep(100);
			}
		}
		
		private void waitUntilTurn() {
			screen.setAutoWaitTimeout(.1f);
			screen.setROI(turnArea);
			boolean ourTurn = false;
			while(!ourTurn) {
				sleep(100);
				ourTurn = scraper.canFindImage("turnStart.png");
			}
			screen.setAutoWaitTimeout(DEFAULT_SCREEN_SEARCH_TIME);
			screen.setROI(wholeScreen);
			return;
		}
		
		private void drawCard() {
			System.out.println("MOVE MOUSE TO " + deckPosition.x +"," + deckPosition.y);
			bot.mouseMove(deckPosition.x, deckPosition.y);
			System.out.println("CLICK TO DRAW");
			click();
		}
		
		private void drawTickets() {
			bot.mouseMove(deckPosition.x, deckPosition.y);
			click();
			sleep(250);
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
		
		private void getCurrentGameScreen() {
			
			if (scraper.canFindImage("mainmenu_key.png")) {
				currentGameScreen = GameScreen.TITLE_SCREEN;
				System.out.println("ON MAIN MENU SCREEN");
			} else if (scraper.canFindImage("modeselect_key.png")) {
				currentGameScreen = GameScreen.MODE_SELECT_SCREEN;
				System.out.println("ON MODE SELECT SCREEN");
			} else if (scraper.canFindImage("gamescreen_key.png")) {
				currentGameScreen = GameScreen.GAME_SCREEN;
				System.out.println("ON GAME SCREEN");
			} else if(scraper.canFindImage("loading_key.png")) {
				currentGameScreen = GameScreen.LOADING_SCREEN;
				System.out.println("ON LOADING SCREEN");
			} else {
				currentGameScreen = GameScreen.NONE;
				System.out.println("GAME SCREEN NOT FOUND");
			}
			
		}
	}
}
