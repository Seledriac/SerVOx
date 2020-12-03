package ViewModels;

import Application.Main;
import Application.SceneLoader;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class FinStory {
    public void retourMenu(MouseEvent mouseEvent) throws IOException {
        Main.sceneLoader.switchTo(SceneLoader.SCENE_MAIN_MENU);
    }

}
