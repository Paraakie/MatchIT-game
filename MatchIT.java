
public class MatchIT extends GameEngine{
	public static void main(String agrs[]) {
		createGame(new MatchIT());
	}
	
	/* Global string to switch between levels/menu/winScreen
	 * Menu =  Main-menu
	 * lvl1 = First level
	 * score = Final score screen
	 */
	String currentLevel;
	
	@Override
	public void init() {
		//Player starts in the menu
		currentLevel = "menu";
	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent() {
		// TODO Auto-generated method stub
		
	}
}
