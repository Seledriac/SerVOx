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

public class MainMenu implements Initializable {

    @FXML
    protected ImageView main_menu_background;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        int heure = Integer.parseInt(dtf.format(now));

        FileInputStream inputstream = null;
        try {
            if(heure < 6 || heure > 18) {
                inputstream = new FileInputStream("src\\Views\\Images\\fond_menu_nuit.jpg");
            } else
                inputstream = new FileInputStream("src\\Views\\Images\\fond_menu_jour.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        main_menu_background.setImage(image);
    }

    @FXML
    protected void loadMainMenuChoose(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadMainMenuChooseView();
    }

}
