package ViewModels;

import Application.Main;
import Models.DB.CharacterHandler;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.weapons.Arme;
import Models.weapons.Arc;
import Models.weapons.Sort;
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

public class NewChasseur implements Initializable {

    private int num_arc;
    @FXML
    protected Button thoridal;
    @FXML
    protected Button atiesh;
    @FXML
    protected Button valanyr;
    @FXML
    protected TextField nom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        num_arc = 0;

        Image img_thoridal = new Image("/Views/Images/thoridal.png");
        ImageView view_thoridal = new ImageView(img_thoridal);
        view_thoridal.setFitWidth(210);
        view_thoridal.setFitHeight(210);
        thoridal.setGraphic(view_thoridal);

        Image img_atiesh = new Image("/Views/Images/atiesh.png");
        ImageView view_atiesh = new ImageView(img_atiesh);
        view_atiesh.setFitWidth(210);
        view_atiesh.setFitHeight(210);
        atiesh.setGraphic(view_atiesh);

        Image img_valanyr = new Image("/Views/Images/valanyr.png");
        ImageView view_valanyr = new ImageView(img_valanyr);
        view_valanyr.setFitWidth(210);
        view_valanyr.setFitHeight(210);
        valanyr.setGraphic(view_valanyr);
    }

    public void retour_new_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNewCharacterView();
    }

    public void selectThoridal(MouseEvent mouseEvent) {
        num_arc = 1;
    }

    public void selectAtiesh(MouseEvent mouseEvent) {
        num_arc = 2;
    }

    public void selectValanyr(MouseEvent mouseEvent) {
        num_arc = 3;
    }

    public void loadGame(MouseEvent mouseEvent) throws IOException, CreationException, GuerrierException, ChasseurException, MageException {
        if(num_arc != 0 && nom.getCharacters().toString() != "") {
            ArrayList<Arme> weapons = new ArrayList<>();
            ArrayList<Sort> sorts = new ArrayList<>();
            switch (num_arc) {
                case 1:
                    weapons.add(new Arc("Thor'Idal", 10, 20));
                    break;
                case 2:
                    weapons.add(new Arc("Atiesh", 15, 10));
                    break;
                case 3:
                    weapons.add(new Arc("Val'Anyr", 20, 15));
                    break;
                default:
                    break;
            }
            sorts.add(new Sort("Traque", 10, 10));
            Main.gameManager.CreateNewCharacter(nom.getCharacters().toString(), CharacterHandler.CHASSEUR, weapons, sorts);
        }
    }

}
