package ViewModels;

import Application.Main;
import Models.DB.CharacterHandler;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.weapons.Arme;
import Models.weapons.Sort;
import Models.weapons.SortUltime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewMage implements Initializable {

    private int num_sort;
    @FXML
    protected Button flamme_interdite;
    @FXML
    protected Button reveil_des_arcanes;
    @FXML
    protected Button tempete;
    @FXML
    protected TextField nom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        num_sort = 0;

        Image img_flamme_interdite = new Image("/Views/Images/flamme_interdite.png");
        ImageView view_flamme_interdite = new ImageView(img_flamme_interdite);
        view_flamme_interdite.setFitWidth(210);
        view_flamme_interdite.setFitHeight(210);
        flamme_interdite.setGraphic(view_flamme_interdite);

        Image img_reveil_des_arcanes = new Image("/Views/Images/reveil_des_arcanes.jpg");
        ImageView view_reveil_des_arcanes = new ImageView(img_reveil_des_arcanes);
        view_reveil_des_arcanes.setFitWidth(210);
        view_reveil_des_arcanes.setFitHeight(210);
        reveil_des_arcanes.setGraphic(view_reveil_des_arcanes);

        Image img_tempete = new Image("/Views/Images/tempete.jpg");
        ImageView view_tempete = new ImageView(img_tempete);
        view_tempete.setFitWidth(210);
        view_tempete.setFitHeight(210);
        tempete.setGraphic(view_tempete);
    }

    public void retour_new_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNewCharacterView();
    }

    public void selectFlammeInterdite(MouseEvent mouseEvent) {
        num_sort = 1;
    }

    public void selectReveilDesArcanes(MouseEvent mouseEvent) {
        num_sort = 2;
    }

    public void selectTempete(MouseEvent mouseEvent) {
        num_sort = 3;
    }

    public void loadGame(MouseEvent mouseEvent) throws IOException, CreationException, GuerrierException, ChasseurException, MageException {
        if(num_sort != 0 && nom.getCharacters().toString() != "") {
            ArrayList<Arme> weapons = new ArrayList<>();
            ArrayList<Sort> sorts = new ArrayList<>();
            sorts.add(new Sort("Petite Boule de Feu", 10, 5));
            switch (num_sort) {
                case 1:
                    sorts.add(new SortUltime("Flamme Interdite", 20, 15));
                    break;
                case 2:
                    sorts.add(new SortUltime("Réveil des Arcanes", 50, 40));
                    break;
                case 3:
                    sorts.add(new SortUltime("Tempête", 30, 20));
                    break;
                default:
                    break;
            }
            Main.gameManager.CreateNewCharacter(nom.getCharacters().toString(), CharacterHandler.MAGE, weapons, sorts);
        }
    }

}
