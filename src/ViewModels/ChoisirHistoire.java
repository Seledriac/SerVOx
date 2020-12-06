package ViewModels;

import Application.Main;
import Models.classes.Histoire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoisirHistoire implements Initializable {

    @FXML
    protected Button guerriers;
    @FXML
    protected Button mages;
    @FXML
    protected Button chasseurs;
    @FXML
    protected Button random;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img_guerrier = new Image("/Views/Images/illustration_guerrier.jpg");
        ImageView view_guerrier = new ImageView(img_guerrier);
        view_guerrier.setFitWidth(250);
        view_guerrier.setFitHeight(360);
        guerriers.setGraphic(view_guerrier);

        Image img_mage = new Image("/Views/Images/illustration_mage.jpg");
        ImageView view_mage = new ImageView(img_mage);
        view_mage.setPreserveRatio(true);
        view_mage.setFitWidth(250);
        view_mage.setFitHeight(360);
        mages.setGraphic(view_mage);

        Image img_chasseur = new Image("/Views/Images/illustration_chasseur.jpg");
        ImageView view_chasseur = new ImageView(img_chasseur);
        view_chasseur.setFitWidth(250);
        view_chasseur.setFitHeight(360);
        chasseurs.setGraphic(view_chasseur);

        Image img_random = new Image("/Views/Images/illustration_random.jpg");
        ImageView view_random = new ImageView(img_random);
        view_random.setFitWidth(250);
        view_random.setFitHeight(360);
        random.setGraphic(view_random);
    }

    @FXML
    protected void select_guerriers(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.setHistoire(Histoire.GUERRIERS);
    }

    @FXML
    protected void select_mages(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.setHistoire(Histoire.MAGES);
    }

    @FXML
    protected void select_chasseurs(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.setHistoire(Histoire.CHASSEURS);
    }

    @FXML
    protected void select_random(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.setHistoire(Histoire.RANDOM);
    }

}
