package ViewModels;

import Application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainMenuChoose implements Initializable {

    @FXML
    protected ImageView main_menu_choose_background;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        int heure = Integer.parseInt(dtf.format(now));

        FileInputStream inputstream = null;
        try {
            if(heure < 6 || heure > 18) {
                inputstream = new FileInputStream("src\\Views\\Images\\fond_menu_nuit.png");
            } else
                inputstream = new FileInputStream("src\\Views\\Images\\fond_menu_jour.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        ImageView main_menu_background;
        main_menu_choose_background.setImage(image);
    }

    public void load_new_character_view(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNewCharacterView();
    }

    public void load_choose_character_view(MouseEvent mouseEvent) throws IOException{
        Main.gameManager.loadChooseCharacterView();
    }


}
