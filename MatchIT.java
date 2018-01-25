
/* Assignmet 2, 159.103
 * Chad Finch, 16151947
 * Sven Gerahrds, 15031719
 */

import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.awt.*;

/*
 * Images and Audio used in this project 
 * are not ours, the licenses are in same 
 * folder as the contents are.
 */

public class MatchIT extends GameEngine {
	public static void main(String agrs[]) {
		createGame(new MatchIT());
	}

	/*
	 * Global string to switch between levels/menu/winScreen, 
	 * menu = Main-menu, 
	 * lvl1= First level, 
	 * gameOver = Game Over screen;
	 */
	String currentLevel;

	/*
	 * Audio
	 */

	AudioClip backgroundMusic;
	AudioClip menuMusic;
	AudioClip goodMatch;
	AudioClip badMatch;

	boolean muted;
	boolean playPressed, mutePressed, exitPressed, backPressed, playHover, muteHover, exitHover, backHover;

	double playX, playY;
	double muteX, muteY;
	double exitX, exitY;
	double backX, backY;
	double backWidth;
	double backHeight;
	double buttonWidth;
	double buttonHeight;

	public void initAudio() {
		// Music
		backgroundMusic = loadAudio("Audio/background.wav");
		menuMusic = loadAudio("Audio/menu.wav");

		// Audio for playable Levels
		goodMatch = loadAudio("Audio/good_match.wav");
		badMatch = loadAudio("Audio/bad_match.wav");
	}

	/*
	 * Main Menu
	 */

	// All images for menu
	Image play, playHighlighted, playClicked;
	Image mute, muteHighlighted, muteClicked;
	Image exit, exitHighlighted, exitClicked;
	Image backButton, backHovered, backClicked, menuBackground, title;

	int menuOption = 0;

	public void initMenu() {
		// Menu Images
		title = loadImage("images_farm/title.png");
		menuBackground = loadImage("images_farm/background1.png");
		play = loadImage("Menu/play.png");
		playHighlighted = loadImage("Menu/playHighlighted.png");
		playClicked = loadImage("Menu/playClicked.png");
		mute = loadImage("Menu/mute.png");
		muteHighlighted = loadImage("Menu/muteHighlighted.png");
		muteClicked = loadImage("Menu/muteClicked.png");
		exit = loadImage("Menu/exit.png");
		exitHighlighted = loadImage("Menu/exitHighlighted.png");
		exitClicked = loadImage("Menu/exitClicked.png");
	}

	public void updateMenu() {
	}

	public void drawMenu() {
		drawImage(menuBackground, 0, 0, width(), height());
		drawImage(title, -10, -50, 800, 600);

		// play
		if (menuOption == 0) {
			drawImage(playHighlighted, playX, playY, buttonWidth, buttonHeight);
		} else {
			drawImage(play, playX, playY, buttonWidth, buttonHeight);
		}

		// Mute
		if (menuOption == 1) {
			drawImage(muteHighlighted, muteX, muteY, buttonWidth, buttonHeight);
		} else {
			drawImage(mute, muteX, muteY, buttonWidth, buttonHeight);
		}

		// Exit
		if (menuOption == 2) {
			drawImage(exitHighlighted, exitX, exitY, buttonWidth, buttonHeight);
		} else {
			drawImage(exit, exitX, exitY, buttonWidth, buttonHeight);
		}
		if (playHover == true) {
			drawImage(playHighlighted, playX, playY, buttonWidth, buttonHeight);
		}
		if (muteHover == true) {
			drawImage(muteHighlighted, muteX, muteY, buttonWidth, buttonHeight);
		}
		if (exitHover == true) {
			drawImage(exitHighlighted, exitX, exitY, buttonWidth, buttonHeight);
		}

		if (playPressed == true) {
			drawImage(playClicked, playX, playY, buttonWidth, buttonHeight);
		}
		if (mutePressed == true) {
			drawImage(muteClicked, muteX, muteY, buttonWidth, buttonHeight);
		}
		if (exitPressed == true) {
			drawImage(exitClicked, exitX, exitY, buttonWidth, buttonHeight);
		}
	}

	/*
	 * Level 1
	 */

	// Values for Objects
	double circle1X, circle1Y, circle2X, circle2Y, circle1Width, circle1Height;
	double square1X, square1Y, square2X, square2Y, square1Width, square1Height;
	double diam1X, diam1Y, diam2X, diam2Y, diam1Width, diam1Height;
	double rect1X, rect1Y, rect2X, rect2Y, rectWidth, rectHeight;
	double heart1X, heart2X, heart3X, heartY, heartWidth, heartHeight;

	int score; // The value which will hold the score, also be used for gameOverScreen
	int life; // Start with 3 lives; You lose 1 life if you match wrong;

	// Booleans, keep track of whether the Objects have been matched, once matched
	// they will disappear
	boolean circlesMatched, squaresMatched, diamondsMatched, rectanglesMatched;

	// All Images for Lvl1
	Image heartImage, squareImage, circleImage, diamImage, rectImage, background;

	// load all images here
	public void initLevel1() {
		// background
		background = loadImage("images_farm\\background2.png");
		// circle
		circleImage = loadImage("images_farm\\coin.png");
		// square
		squareImage = loadImage("images_farm\\box1.png");
		// diamond
		diamImage = loadImage("images_farm\\crystal.png");
		// rectangle
		rectImage = loadImage("images_farm\\box2.png");
		// heart
		heartImage = loadImage("images_farm\\heart.png");
		// back button
		backButton = loadImage("images_farm\\back.png");
		backHovered = loadImage("images_farm\\backHover.png");
		backClicked = loadImage("images_farm\\backClicked.png");
	}

	public void updateLevel1() {
		// Move on to gameOver Screen
		if (life <= 0) { // Once all Lives are gone
			currentLevel = "gameOver";
		}
		if (circlesMatched && squaresMatched && diamondsMatched && rectanglesMatched) { // Once all Objects are Matched
			currentLevel = "gameOver";
		}
	}

	public void drawLevel1() {
		// Background
		drawImage(background, 0, 0, width(), height());

		if (backPressed == false) {
			drawImage(backButton, backX, backY, backWidth, backHeight);
		} else {
			drawImage(backClicked, backX, backY, backWidth, backHeight);
		}

		if (backHover == true) {
			drawImage(backHovered, backX, backY, backWidth, backHeight);
		}

		// Circles, 2x, disappear when matched
		if (circlesMatched == false) {
			drawImage(circleImage, circle1X, circle1Y, circle1Width, circle1Height);
			drawImage(circleImage, circle2X, circle2Y, circle1Width, circle1Height);
		}

		// Squares 2x, disappear when matched
		if (squaresMatched == false) {
			drawImage(squareImage, square1X, square1Y, square1Width, square1Height);
			drawImage(squareImage, square2X, square2Y, square1Width, square1Height);
		}

		// Diamonds, 2x, disappear when matched
		if (diamondsMatched == false) {
			drawImage(diamImage, diam1X, diam1Y, diam1Width, diam1Height);
			drawImage(diamImage, diam2X, diam2Y, diam1Width, diam1Height);
		}

		// Rectangles, 2x, disappear when matched
		if (rectanglesMatched == false) {
			drawImage(rectImage, rect1X, rect1Y, rectWidth, rectHeight);
			drawImage(rectImage, rect2X, rect2Y, rectWidth, rectHeight);
		}

		// Hearts, 3x, disappear after badMatch
		if (life >= 1) {
			drawImage(heartImage, heart1X, heartY, heartWidth, heartHeight);
			if (life >= 2) {
				drawImage(heartImage, heart2X, heartY, heartWidth, heartHeight);
				if (life == 3) {
					drawImage(heartImage, heart3X, heartY, heartWidth, heartHeight);
				}
			}
		}

		// Score and Player Instructions
		changeColor(black);
		drawText(60, 40, "" + score);
		drawText(250, height() / 2, "Match the shapes!");
	}

	/*
	 * Game Over Screen
	 */

	Image gameOverBackground, homeButton;

	double homeButtonX, homeButtonY, homeButtonWidth, homeButtonHeight;

	public void initGameOverScreen() {
		gameOverBackground = loadImage("images_farm\\background3.png");
		homeButton = loadImage("images_farm\\home.png");
	}

	public void updateGameOverScreen() {
	}

	public void drawGameOverScreen() {
		drawImage(gameOverBackground, 0, 0, width(), height());

		// Draw Coins
		drawCoin();

		changeColor(black);
		drawText(width() / 2 - 165, height() / 2, "Final Score: " + score, "Arial", 50);
		if (score <= 0) {
			drawText(width() / 2 - 185, height() / 2 + 80, "Better Luck next time!", "Arial", 40);
		} else if (score >= 30) {
			drawText(width() / 2 - 95, height() / 2 + 80, "You Win!", "Arial", 40);
		} else {
			drawText(width() / 2 - 100, height() / 2 + 80, "Try again!", "Arial", 40);
		}

		// Draw Home Button
		drawImage(homeButton, homeButtonX, homeButtonY, homeButtonWidth, homeButtonHeight);
	}

	/*
	 * Coin Animation
	 */

	// Images for Coin Animation
	Image[] coinImages = new Image[100]; // currently only 30 needed

	// Coin Coordinates
	double coin1_X;
	double coin2_X;
	double coin3_X;
	double coin_Y; // All coins are on the same height

	// Coin Size
	double coinWidth;
	double coinHeight;

	// Timer and Duration
	double coinTimer;
	double coinDuration;

	// used to go through all images thus getting an animation
	int coinFrame;

	public void initCoin() {
		// String that changes automatically to load all 30 images via loop
		String imageLocation;

		// doubles
		coinTimer = 0;
		coinDuration = 1.5;

		// Load all Sprites
		for (int i = 0; i < 30; i++) {
			imageLocation = "animation_bronze_coin\\Bronze_" + (i + 1) + ".png";
			coinImages[i] = loadImage(imageLocation);
		}

	}

	public void updateCoin(double dt) {
		// Increment timer
		coinTimer += dt;

		// Check to see if explosion has finished
		if (coinTimer >= coinDuration) {
			coinTimer = 0;
		}

		// Set Coin frame
		coinFrame = getAnimationFrame(coinTimer, coinDuration, 30);
	}

	// Function to get frame of animation
	public int getAnimationFrame(double timer, double duration, int numFrames) {
		// Get frame
		int i = (int) floor(((timer % duration) / duration) * numFrames);
		// Check range
		if (i >= numFrames) {
			i = numFrames - 1;
		}
		// Return
		return i;
	}

	public void drawCoin() {
		// Draw Coins based of remaining lifes
		if (life >= 1) {
			drawImage(coinImages[coinFrame], coin1_X, coin_Y, coinWidth, coinHeight);
			if (life >= 2) {
				drawImage(coinImages[coinFrame], coin2_X, coin_Y, coinWidth, coinHeight);
				if (life == 3) {
					drawImage(coinImages[coinFrame], coin3_X, coin_Y, coinWidth, coinHeight);
				}
			}
		}
	}

	// Initialize all global values here
	@Override
	public void init() {
		// Strings
		currentLevel = "menu"; // Player starts in the menu
		selectedObject = "none"; // no object selected initially
		movedObject = "none"; // no objects moved initially

		// Doubles
		circle1X = 660;
		circle1Y = 370;
		circle2X = 70;
		circle2Y = 60;
		circle1Width = 110;
		circle1Height = 110;

		square1X = 480;
		square1Y = 380;
		square2X = 280;
		square2Y = 60;
		square1Width = 100;
		square1Height = 100;

		diam1X = 280;
		diam1Y = 380;
		diam2X = 480;
		diam2Y = 70;
		diam1Width = 90;
		diam1Height = 90;

		rect1X = 50;
		rect1Y = 380;
		rect2X = 610;
		rect2Y = 60;
		rectWidth = 150;
		rectHeight = 100;

		heart1X = 750;
		heart2X = 700;
		heart3X = 650;
		heartY = 10;
		heartWidth = 40;
		heartHeight = 40;

		homeButtonX = 350;
		homeButtonY = 340;
		homeButtonWidth = 100;
		homeButtonHeight = 100;

		coin1_X = 310;
		coin2_X = 370;
		coin3_X = 430;
		coin_Y = 150;
		coinWidth = 60;
		coinHeight = 60;

		mouseX = 0;
		mouseY = 0;

		playX = 50;
		playY = 350;
		muteX = 350;
		muteY = 350;
		exitX = 600;
		exitY = 350;
		buttonWidth = 100;
		buttonHeight = 100;
		backX = 3;
		backY = 3;
		backWidth = 40;
		backHeight = 40;

		// Integers
		score = 0;
		life = 3;
		coinFrame = 0;

		// Booleans
		circlesMatched = false;
		squaresMatched = false;
		diamondsMatched = false;
		rectanglesMatched = false;
		muted = false;
		playPressed = false;
		mutePressed = false;
		exitPressed = false;
		backPressed = false;
		playHover = false;
		muteHover = false;
		exitHover = false;
		backHover = false;

		// Audio
		initAudio();
		if (currentLevel == "menu") { // Not in Update as it doesn't work there
			startAudioLoop(menuMusic);
		}

		// Load images for menu
		initMenu();
		// Load images for lvl1
		initLevel1();
		// Load Images for gameOver
		initGameOverScreen();
		// Load Coin Animation
		initCoin();
	}

	// Main Update Function
	@Override
	public void update(double dt) {
		if (currentLevel == "menu") { // Main Menu
			updateMenu();

		} else if (currentLevel == "lvl1") { // Level 1
			updateLevel1();

		} else if (currentLevel == "gameOver") { // Game Over Screen
			updateGameOverScreen();
			updateCoin(dt);
		} else {
			/*
			 * This will only happen if currentLevel was changed to a wrong value Thus this
			 * an error
			 */
			System.out.println("Error: String 'currentLevel' has an invald name.\n");
		}
	}

	// Main Draw Function, for graphical content
	@Override
	public void paintComponent() {
		// Resize the window
		setWindowSize(800, 500);

		if (currentLevel == "menu") { // Main Menu
			drawMenu();

		} else if (currentLevel == "lvl1") { // Level 1
			drawLevel1();

		} else if (currentLevel == "gameOver") { // Game Over Screen
			drawGameOverScreen();
			drawCoin();
		} else {
			/*
			 * This will only happen if currentLevel was changed to a wrong value Thus this
			 * an error
			 */
			System.out.println("Error: String 'currentLevel' has an invald name.\n");
		}

	}

	/*
	 * Mouse Events
	 */

	double mouseX, mouseY; // Mouse coordinates

	public void mouseMoved(MouseEvent event) {
		// called when the mouse is moved over the play button - change the image to
		// playHighlighted
		if (currentLevel == "menu") {
			mouseX = event.getX();
			mouseY = event.getY();
			if ((mouseX >= playX) && (mouseX <= playX + buttonWidth)) {
				if ((mouseY >= playY) && (mouseY <= playY + buttonHeight)) {
					playHover = true;
					drawMenu();
				}
			} else {
				playHover = false;
			}
			// called when the mouse is moved over the mute button - change the image to
			// muteHighlighted
			if ((mouseX >= muteX) && (mouseX <= muteX + buttonWidth)) {
				if ((mouseY >= muteY) && (mouseY <= muteY + buttonHeight)) {
					muteHover = true;
					drawMenu();
				}
			} else {
				muteHover = false;
			}
			// called when the mouse is moved over the exit button - change the image to
			// exitHighlighted
			if ((mouseX >= exitX) && (mouseX <= exitX + buttonWidth)) {
				if ((mouseY >= exitY) && (mouseY <= exitY + buttonHeight)) {
					exitHover = true;
					drawMenu();
				}
			} else {
				exitHover = false;
			}
		}

		if (currentLevel == "lvl1") {
			mouseX = event.getX();
			mouseY = event.getY();
			if ((mouseX >= backX) && (mouseX <= backX + backWidth)) {
				if ((mouseY >= backY) && (mouseY <= backY + backHeight)) {
					backHover = true;
					drawLevel1();
				}
			} else {
				backHover = false;
			}

			if (movedObject != "none") {
				// Make movedObject follow the mouse
				// Remember to store original coordinates
			}
		}
	}

	// Called whenever a mouse button is clicked
	// (pressed and released in the same position)
	public void mouseClicked(MouseEvent event) {

	}

	// Called whenever a mouse button is pressed
	public void mousePressed(MouseEvent event) {
		if (currentLevel == "menu") {
			mouseX = event.getX();
			mouseY = event.getY();
			if ((mouseX >= playX) && (mouseX <= playX + buttonWidth)) {
				if ((mouseY >= playY) && (mouseY <= playY + buttonHeight)) {
					playPressed = true;
					drawMenu();
					return;
				}
			}
			if ((mouseX >= muteX) && (mouseX <= muteX + buttonWidth)) {
				if ((mouseY >= muteY) && (mouseY <= muteY + buttonHeight)) {
					mutePressed = true;
					drawMenu();
					return;
				}
			}
			if ((mouseX >= exitX) && (mouseX <= exitX + buttonWidth)) {
				if ((mouseY >= exitY) && (mouseY <= exitY + buttonHeight)) {
					exitPressed = true;
					drawMenu();
					return;
				}
			}
		}
		if (currentLevel == "lvl1") {
			mouseX = event.getX();
			mouseY = event.getY();
			if ((mouseX >= backX) && (mouseX <= backX + backWidth)) {
				if ((mouseY >= backY) && (mouseY <= backY + backHeight)) {
					backPressed = true;
					drawLevel1();
					return;
				}
			}
		}
	}

	// Called whenever a mouse button is released
	public void mouseReleased(MouseEvent event) {
		if (currentLevel == "menu") {
			mouseX = event.getX();
			mouseY = event.getY();
			if ((mouseX >= playX) && (mouseX <= playX + buttonWidth)) {
				if ((mouseY >= playY) && (mouseY <= playY + buttonHeight)) {
					currentLevel = "lvl1";
					playPressed = false;
					return;
				}
			}
			if ((mouseX >= muteX) && (mouseX <= muteX + buttonWidth)) {
				if ((mouseY >= muteY) && (mouseY <= muteY + buttonWidth)) {
					mutePressed = false;
					if(muted == false){
						muted = true;
						stopAudioLoop(menuMusic);
					}else{
						muted = false;
						startAudioLoop(menuMusic);
					}
					return;
				}
			}
			if ((mouseX >= exitX) && (mouseX <= exitX + buttonWidth)) {
				if ((mouseY >= exitY) && (mouseY <= exitY + buttonHeight)) {
					exitPressed = false;
					System.exit(0);
					return;
				}
			}
		}

		// Player controls; for playing the level(s)
		if (currentLevel == "lvl1") {
			// Get Mouse Coordinates
			mouseX = event.getX();
			mouseY = event.getY();

			// First Check if what was clicked, if it wasn't an object
			boolean temp = wasObjectClicked(); // use temp variable to avoid using function as argument
			if (temp) {
				if (movedObject == "none") {
					movedObject = selectedObject;
					System.out.println(movedObject + " is the moved Object\n"); // For testing purposes

				} else {
					matchObjects(movedObject, selectedObject);
					movedObject = "none";
				}
				selectedObject = "none";
			}
			// Changes back image in level 1 on mouse button release || pause
			if ((mouseX >= backX) && (mouseX <= backX + backWidth)) {
				if ((mouseY >= backY) && (mouseY <= backY + backHeight)) {
					backPressed = false;
					currentLevel = "menu";
					return;
				}
			}

			// Player controls; for playing the level(s)
			// First Check if what was clicked, if it wasn't an object
			boolean temp1 = wasObjectClicked(); // use temp variable to avoid using function as argument
			if (temp1) {
				if (movedObject == "none") {
					movedObject = selectedObject;
					System.out.println(movedObject + " is the moved Object\n"); // For testing purposes

				} else {
					matchObjects(movedObject, selectedObject);
					movedObject = "none";
				}
				selectedObject = "none";
			}
		}

	}

	// Called whenever a key is pressed
	public void keyPressed(KeyEvent e) {
		if (currentLevel == "menu") {
			keyPressedMenu(e);
		} else {
			keyPressedGame(e);
		}
	}

	// Called whenever a key is pressed in the menu
	public void keyPressedMenu(KeyEvent e) {
		// Up Arrow
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (menuOption > 0) {
				menuOption--;
			}
		}
		// Down Arrow
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (menuOption < 2) {
				menuOption++;
			}
		}
		// Enter Key
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (menuOption == 0) {
				// Start Game
				currentLevel = "lvl1";

			} else if (menuOption == 1) {
				stopAudioLoop(menuMusic);
				muted = true;

			} else {
				// Exit
				System.exit(0);
			}
		}
	}

	// Called whenever a key is pressed in the game
	public void keyPressedGame(KeyEvent e) {
		// In the game
		if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			currentLevel = "menu";
		}
	}
	/*
	 * Interacting with objects
	 */

	/*
	 * These two strings represents the Objects selected/moved none = no object
	 * selected Possible Objects are: circle1 , circle2; square1, square 2; diam1,
	 * diam2; rect1, rect2;
	 */
	String selectedObject; // What object is currently selected
	String movedObject; // What object is currently moved

	/*
	 * Game Mechanics explained in detail: One left-mouse-click makes an Object
	 * movable once an object is movable you can match it with another
	 * 
	 * The aim is to match the same looking objects; good match -> gain score, match
	 * objects disappear; bad match -> lose score, lose a life, lose movable object;
	 * returns back to origin
	 */
	public void matchObjects(String mObject, String sObject) {
		// First two if statements that check for any errors
		if (mObject == "none" || sObject == "none") {
			System.out.println("Error, Objects can't be matched with none");
			return;
		}

		if (mObject == sObject) {
			System.out.println("Error, Objects can't be matched with themselves");
			return;
		}

		// Matching
		// Circles
		if (mObject == "circle1" && sObject == "circle2") {
			goodMatch();
			circlesMatched = true;
			return;
		} else if (mObject == "circle2" && sObject == "circle1") {
			goodMatch();
			circlesMatched = true;
			return;
		}
		// Squares
		if (mObject == "square1" && sObject == "square2") {
			goodMatch();
			squaresMatched = true;
			return;
		} else if (mObject == "square2" && sObject == "square1") {
			goodMatch();
			squaresMatched = true;
			return;
		}
		// Diamonds
		if (mObject == "diam1" && sObject == "diam2") {
			goodMatch();
			diamondsMatched = true;
			return;
		} else if (mObject == "diam2" && sObject == "diam1") {
			goodMatch();
			diamondsMatched = true;
			return;
		}
		// Rectangles
		if (mObject == "rect1" && sObject == "rect2") {
			goodMatch();
			rectanglesMatched = true;
			return;
		} else if (mObject == "rect2" && sObject == "rect1") {
			goodMatch();
			rectanglesMatched = true;
			return;
		}
		badMatch();
	}

	public void goodMatch() {
		score = score + 20;
		if (muted == false) {
			playAudio(goodMatch); // Audio implementation
		}
	}

	public void badMatch() {
		score = score - 10;
		life--;
		movedObject = "none";
		if (muted == false) {
			playAudio(badMatch); // Audio implementation
		}
	}

	/*
	 * Functor that determines what's exactly been clicked on returns false if no
	 * object was clicked on else returns true
	 */
	public boolean wasObjectClicked() {
		// circles
		if (circlesMatched == false) {
			if (wasCircle1_Clicked()) {
				return true;
			}
			if (wasCircle2_Clicked()) {
				return true;
			}
		}
		// Squares
		if (squaresMatched == false) {
			if (wasSquare1_Clicked()) {
				return true;
			}
			if (wasSquare2_Clicked()) {
				return true;
			}
		}
		// Diamonds
		if (diamondsMatched == false) {
			if (wasDiam1_Clicked()) {
				return true;
			}
			if (wasDiam2_Clicked()) {
				return true;
			}
		}
		// Rectangles
		if (rectanglesMatched == false) {
			if (wasRect1_Clicked()) {
				return true;
			}
			if (wasRect2_Clicked()) {
				return true;
			}
		}

		return false; // No Object was clicked

	}

	// Check if Circle1 was clicked
	public boolean wasCircle1_Clicked() {
		if (movedObject != "circle1") {
			if (distance(circle1X + circle1Width / 2, circle1Y + circle1Height / 2, mouseX, mouseY) <= circle1Height
					/ 2) {
				selectedObject = "circle1";
				System.out.println("Circle1 is the selected Object\n"); // For testing purposes
				return true;
			}
		}
		return false;
	}

	// Check if Circle2 was clicked
	public boolean wasCircle2_Clicked() {
		if (movedObject != "circle2") {
			if (distance(circle2X + circle1Width / 2, circle2Y + circle1Height / 2, mouseX, mouseY) <= circle1Height
					/ 2) {
				selectedObject = "circle2";
				System.out.println("Circle2 is the selected Object\n"); // For testing purposes
				return true;
			}
		}
		return false;
	}

	// Check if Square1 was clicked
	public boolean wasSquare1_Clicked() {
		if (movedObject != "square1") {
			if ((mouseX >= square1X) && (mouseX <= (square1X + square1Width))) {
				if ((mouseY >= square1Y) && (mouseY <= (square1Y + square1Height))) {
					selectedObject = "square1";
					System.out.println("Square1 is the selected Object\n"); // For testing purposes
					return true;
				}
			}
		}
		return false;
	}

	// Check if Square2 was clicked
	public boolean wasSquare2_Clicked() {
		if (movedObject != "square2") {
			if ((mouseX >= square2X) && (mouseX <= (square2X + square1Width))) {
				if ((mouseY >= square2Y) && (mouseY <= (square2Y + square1Height))) {
					selectedObject = "square2";
					System.out.println("Square2 is the selected Object\n"); // For testing purposes
					return true;
				}
			}
		}
		return false;
	}

	// Check if Diam1 was clicked
	public boolean wasDiam1_Clicked() {
		if (movedObject != "diam1") {
			if ((mouseX >= diam1X) && (mouseX <= (diam1X + diam1Width))) {
				if ((mouseY >= diam1Y) && (mouseY <= (diam1Y + diam1Height))) {
					selectedObject = "diam1";
					System.out.println("Diam1 is the selected Object\n"); // For testing purposes
					return true;
				}
			}
		}
		return false;
	}

	// Check if Diam2 was clicked
	public boolean wasDiam2_Clicked() {
		if (movedObject != "diam2") {
			if ((mouseX >= diam2X) && (mouseX <= (diam2X + diam1Width))) {
				if ((mouseY >= diam2Y) && (mouseY <= (diam2Y + diam1Height))) {
					selectedObject = "diam2";
					System.out.println("Diam2 is the selected Object\n"); // For testing purposes
					return true;
				}
			}
		}
		return false;
	}

	// Check if Rect1 was clicked
	public boolean wasRect1_Clicked() {
		if (movedObject != "rect1") {
			if ((mouseX >= rect1X) && (mouseX <= (rect1X + rectWidth))) {
				if ((mouseY >= rect1Y) && (mouseY <= (rect1Y + rectHeight))) {
					selectedObject = "rect1";
					System.out.println("Rect1 is the selected Object\n"); // For testing purposes
					return true;
				}
			}
		}
		return false;
	}

	// Check if Rect2 was clicked
	public boolean wasRect2_Clicked() {
		if (movedObject != "rect2") {
			if ((mouseX >= rect2X) && (mouseX <= (rect2X + rectWidth))) {
				if ((mouseY >= rect2Y) && (mouseY <= (rect2Y + rectHeight))) {
					selectedObject = "rect2";
					System.out.println("Rect2 is the selected Object\n"); // For testing purposes
					return true;
				}
			}
		}
		return false;
	}
}
