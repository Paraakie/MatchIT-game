/* Assignmet 2, 159.103
 * Chad Finch,
 * Sven Gerahrds
 */
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;

public class MatchIT extends GameEngine{
	public static void main(String agrs[]) {
		createGame(new MatchIT());
	}
	
	/* Global string to switch between levels/menu/winScreen
	 * menu =  Main-menu
	 * lvl1 = First level
	 * gameOver = Game Over screen
	 */
	String currentLevel;
	
	/* 
	 * Audio
	 */
	
	AudioClip backgroundMusic;
	AudioClip menuMusic;
	AudioClip goodMatch;
	AudioClip badMatch;
	
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
	
	//All images for menu
	Image play,
	playHighlighted,
	mute,
	muteHighlighted,
	exit,
	exitHighlighted;
	
	int menuOption = 0;
	
	public void initMenu() {
		// Menu Images
		play               = loadImage("Menu/play.png");
		playHighlighted    = loadImage("Menu/playHighlighted.png");
		mute            = loadImage("Menu/mute.png");
		muteHighlighted = loadImage("Menu/muteHighlighted.png");
		exit               = loadImage("Menu/exit.png");
		exitHighlighted    = loadImage("Menu/exitHighlighted.png");
	}
	
	public void updateMenu() {
		
	}
	
	public void drawMenu() {
		drawImage(background,0,0, width(), height());
		
		//play
		if(menuOption == 0){
			drawImage(playHighlighted, 50, 150);
		}else{
			drawImage(play, 50, 150);
		}
		
		//Mute
		if(menuOption == 1) {
			drawImage(muteHighlighted, 350, 150);
		} else {
			drawImage(mute, 350, 150);
		}
		
		//Exit
		if(menuOption == 2) {
			drawImage(exitHighlighted, 600, 150);
		} else {
			drawImage(exit, 600, 150);
		}
		
	}
	
	/*
	 * Level 1
	 */
	
	double circle1X, circle1Y, circle1R;
	double rect1X, rect1Y, rect1Width, rect1Height;
	double diam1X, diam1Y, diam1Width, diam1Height;
	double rect2X, rect2Y, rect2Width, rect2Height;
	
	int score; //The value which will hold the score, also be used for gameOverScreen
	
	//All Images for Lvl1
	Image heartImage, 
	      squareImage, 
	      circleImage,
	      diamImage,
	      triImage, 
	      background; 
	
	//load all images here
	public void initLevel1() {
		//background
		background = loadImage("images_farm\\background.png"); 
		//circle
		circleImage = loadImage("images_farm\\coin.png"); 
		//square
		squareImage = loadImage("images_farm\\box1.png"); 
		//diamond
		diamImage = loadImage("images_farm\\crystal.png");
		//triangle
		triImage = loadImage("images_farm\\triangle.png");
		//heart
		heartImage = loadImage("images_farm\\heart.png"); 
	}
	
	public void updateLevel1() {
		
	}
	
	public void drawLevel1() {
		// Background
		drawImage(background, 0, 0, width(), height());
		
		// Circles, 2x
		drawImage(circleImage, circle1X, circle1Y, circle1Width, circle1Height);
		drawImage(circleImage, circle1X-590, circle1Y-310, circle1Width, circle1Height);
		
		// Squares
		drawImage(squareImage, rect1X, rect1Y, rect1Width, rect1Height);
		drawImage(squareImage, rect1X-200, rect1Y-320, rect1Width, rect1Height);
		
		// Diamonds, 2x
		drawImage(diamImage, diam1X, diam1Y, diam1Width, diam1Height);
		drawImage(diamImage, diam1X+200, diam1Y-310, diam1Width, diam1Height);
		
		// Triangles, 2x
		changeColor(purple);
		drawImage(triImage, rect2X, rect2Y, rect2Width, rect2Height);
		drawImage(triImage, rect2X+560, rect2Y-320, rect2Width, rect2Height);
		
		// Hearts, 3x
		drawImage(heartImage, 750, 10, 40, 40);
		drawImage(heartImage, 700, 10, 40, 40);
		drawImage(heartImage, 650, 10, 40, 40);
		
		//Score and Player Instructions
		changeColor(black);
		drawText(10, 40, "" + score);
		drawText(250, height()/2, "Match the shapes!");
	}
	
	/*
	 *Game Over Screen
	 */
	
	public void updateGameOverScreen() {
		
	}
	
	public void drawGameOverScreen() {
		changeColor(black);
		drawText(width()/2-165, height()/2, "Final Score: " +score, "Arial", 50);
		if(score <= 0) {
			drawText(width()/2-185, height()/2+80, "Better Luck next time", "Arial", 40);
		} else if(score >= 30) {
			drawText(width()/2-95, height()/2+80, "You Win!", "Arial", 40);
		} else {
			drawText(width()/2-100, height()/2+80, "Try again!", "Arial", 40);
		}
	}
	
	// Initialise all global values here
	@Override
	public void init() {
		// Strings
		currentLevel = "menu"; //Player starts in the menu
		
		// Doubles
		circle1X = 730; 
		circle1Y = 430; 
		circle1R = 50;
		
		rect1X = 480; 
		rect1Y = 380; 
		rect1Width = 100; 
		rect1Height = 100;
		
		diam1X = -15; 
		diam1Y = -15; 
		diam1Width = 90; 
		diam1Height = 90;
		
		rect2X = 50; 
		rect2Y = 380; 
		rect2Width = 150; 
		rect2Height = 100;
		
		// Integers
		score = 0;
		
		// Audio
		initAudio();
		if(currentLevel == "menu"){
			startAudioLoop(menuMusic);
		}
		
		//Load images for menu
		initMenu();
		//Load images for lvl1
		initLevel1(); 
	}
	
	// Main Update Function
	@Override
	public void update(double dt) {
		if(currentLevel == "menu") { // Main Menu
			updateMenu();
		
		} else if(currentLevel == "lvl1") { //Level 1
			updateLevel1();
		
		} else if(currentLevel == "gameOver") { // Game Over Screen
			updateGameOverScreen();
		
		} else {
			/* This will only happen if currentLevel was changed to a wrong value
			 * Thus this an error
			 */
			System.out.println("Error: String 'currentLevel' has an invald name.\n");
		}
	}
	
	// Main Draw Function, for graphical content
	@Override
	public void paintComponent() {
		//Resize the window
		setWindowSize(800,500);
		
		if(currentLevel == "menu") { // Main Menu
			drawMenu();
		
		} else if(currentLevel == "lvl1") { //Level 1
			drawLevel1();
		
		} else if(currentLevel == "gameOver") { // Game Over Screen
			drawGameOverScreen();
		
		} else {
			/* This will only happen if currentLevel was changed to a wrong value
			 * Thus this an error
			 */
			System.out.println("Error: String 'currentLevel' has an invald name.\n");		
		}
		
	}
	
	/*
	 * All mouse Events needed
	 */
	
	// Called whenever a mouse button is clicked
	// (pressed and released in the same position)
	public void mouseClicked(MouseEvent event) {}

	
	// Called whenever a mouse button is pressed
	public void mousePressed(MouseEvent event) {
		
	}

	// Called whenever a mouse button is released
	public void mouseReleased(MouseEvent event) {
		
	}
	
	// Called whenever the mouse is moved with the mouse button held down
	public void mouseDragged(MouseEvent event) {
		
	}
	
	//Called whenever a key is pressed
    public void keyPressed(KeyEvent e) {
    	if(currentLevel == "menu") {
    		keyPressedMenu(e);
    	} else {
    		keyPressedGame(e);
    	}
    }

    //Called whenever a key is pressed in the menu
    public void keyPressedMenu(KeyEvent e) {
		//Up Arrow
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(menuOption > 0) {
				menuOption--;
			}
		}
		//Down Arrow
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(menuOption < 2) {
				menuOption++;
			}
		}
		//Enter Key
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(menuOption == 0) {
				//Start Game
				currentLevel = "lvl1";
				stopAudioLoop(menuMusic);
				startAudioLoop(backgroundMusic);
				
			} else if(menuOption == 1) {
				//Not sure how to mute sound here...

			} else {
				//Exit
				System.exit(0);
			}
		}
	}
	



	//Called whenever a key is pressed in the game
    public void keyPressedGame(KeyEvent e) {
		//In the game
        if(e.getKeyCode() == KeyEvent.VK_Q ||
                e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			currentLevel = "menu";
			stopAudioLoop(backgroundMusic);
			startAudioLoop(menuMusic);
		}
    }
}
