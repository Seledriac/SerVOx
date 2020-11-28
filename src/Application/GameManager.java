package Application;


import java.io.IOException;

public class GameManager {
	
	public void loadGame() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
	}
	
}
