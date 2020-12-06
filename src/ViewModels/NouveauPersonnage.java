package ViewModels;

import Application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NouveauPersonnage implements Initializable {

    @FXML
    protected Button illustration_guerrier;
    @FXML
    protected Button illustration_mage;
    @FXML
    protected Button illustration_chasseur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img_guerrier = new Image("/Views/Images/illustration_guerrier.jpg");
        ImageView view_guerrier = new ImageView(img_guerrier);
        view_guerrier.setFitWidth(350);
        view_guerrier.setFitHeight(500);
        illustration_guerrier.setGraphic(view_guerrier);

        Image img_mage = new Image("/Views/Images/illustration_mage.jpg");
        ImageView view_mage = new ImageView(img_mage);
        view_mage.setPreserveRatio(true);
        view_mage.setFitWidth(350);
        view_mage.setFitHeight(500);
        illustration_mage.setGraphic(view_mage);

        Image img_chasseur = new Image("/Views/Images/illustration_chasseur.jpg");
        ImageView view_chasseur = new ImageView(img_chasseur);
        view_chasseur.setFitWidth(350);
        view_chasseur.setFitHeight(500);
        illustration_chasseur.setGraphic(view_chasseur);
    }

    @FXML
    protected void creer_guerrier_view(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNouveauGuerrierView();
    }

    @FXML
    protected void creer_mage_view(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNouveauMageView();
    }

    @FXML
    protected void creer_chasseur_view(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNouveauChasseurView();
    }

    @FXML
    protected void retour_choose_menu(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadMainMenuChooseView();
    }

}
