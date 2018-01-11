/* Assignmet 2, 159.103
 * Chad Finch,  test
 * Sven Gerahrds
 */
import java.awt.event.MouseEvent;

public class MatchIT extends GameEngine{
	public static void main(String agrs[]) {
		createGame(new MatchIT());
	}
	
	/* Global string to switch between levels/menu/winScreen
	 * menu =  Main-menu
	 * lvl1 = First level
	 * score = Final score screen
	 */
	String currentLevel;
	
	/*
	 * Main Menu
	 */
	
	public void updateMenu() {
		
	}
	
	public void drawMenu() {
		
	}
	
	/*
	 * Level 1
	 */
	
	int score; //The value which will hold the score, also be used for ScoreScreen
	
	public void updateLevel1() {
		
	}
	
	public void drawLevel1() {
		
	}
	
	/*
	 * Final Score Screen
	 */
	
	public void updateScoreScreen() {
		
	}
	
	public void drawScoreScreen() {
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
		//Player starts in the menu
		currentLevel = "menu";
	}
	
	// Main Update Function
	@Override
	public void update(double dt) {
		if(currentLevel == "menu") { // Main Menu
			updateMenu();
		
		} else if(currentLevel == "lvl1") { //Level 1
			updateLevel1();
		
		} else if(currentLevel == "score") { // Final Score Screen
			updateScoreScreen();
		
		} else {
			/* This will only happen if currentLevel was changed to a wrong value
			 * Thus this an error
			 */
			
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
		
		} else if(currentLevel == "score") { // Final Score Screen
			drawScoreScreen();
		
		} else {
			/* This will only happen if currentLevel was changed to a wrong value
			 * Thus this an error
			 */
			
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
	
}
