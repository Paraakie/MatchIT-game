
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
	
	public void updateLevel1() {
		
	}
	
	public void drawLevel1() {
		
	}
	
	/*
	 * Final Score Screen
	 */
	
	public void updateScoreScreen() {
		//Usesful?
	}
	
	public void drawScoreScreen() {
		
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
		// TODO Auto-generated method stub
		
	}
	
	// Main Draw Function, for graphical content
	@Override
	public void paintComponent() {
		if(currentLevel == "menu") {
			updateMenu();
		
		} else if(currentLevel == "lvl1") {
			updateLevel1();
		
		} else if(currentLevel == "score") {
			updateScoreScreen();
		
		} else {
			/* This will only happen if currentLevel was changed to a wrong value
			 * Thus this an error
			 */
			
		}
		
		
	}
}
