package ViewModels;

import Application.Main;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.weapons.Arme;
import Models.weapons.Epee;
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

public class NewGuerrier implements Initializable {

    private int num_epee;
    @FXML
    protected Button durandal;
    @FXML
    protected Button sobek;
    @FXML
    protected Button excalibur;
    @FXML
    protected TextField nom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        num_epee = 0;

        Image img_durandal = new Image("/Views/Images/durandal.png");
        ImageView view_durandal = new ImageView(img_durandal);
        view_durandal.setFitWidth(210);
        view_durandal.setFitHeight(210);
        durandal.setGraphic(view_durandal);

        Image img_sobek = new Image("/Views/Images/sobek.png");
        ImageView view_sobek = new ImageView(img_sobek);
        view_sobek.setFitWidth(210);
        view_sobek.setFitHeight(210);
        sobek.setGraphic(view_sobek);

        Image img_excalibur = new Image("/Views/Images/excalibur.png");
        ImageView view_excalibur = new ImageView(img_excalibur);
        view_excalibur.setFitWidth(210);
        view_excalibur.setFitHeight(210);
        excalibur.setGraphic(view_excalibur);
    }

    public void retour_new_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNewCharacterView();
    }

    public void selectDurandal(MouseEvent mouseEvent) {
        num_epee = 1;
    }

    public void selectSobek(MouseEvent mouseEvent) {
        num_epee = 2;
    }

    public void selectExcalibur(MouseEvent mouseEvent) {
        num_epee = 3;
    }

    public void loadGame(MouseEvent mouseEvent) throws IOException, CreationException, GuerrierException, ChasseurException, MageException {
        if(num_epee != 0 && nom.getCharacters().toString() != "") {
            ArrayList<Arme> weapons = new ArrayList<>();
            ArrayList<Sort> sorts = new ArrayList<>();
            switch (num_epee) {
                case 1:
                    weapons.add(new Epee("Durandal", 10));
                    sorts.add(new Sort("Démacia", 10, 3));
                    break;
                case 2:
                    weapons.add(new Epee("Sobek", 15));
                    sorts.add(new Sort("Démacia", 10, 3));
                    break;
                case 3:
                    weapons.add(new Epee("Excalibur", 20));
                    break;
                default:
                    break;
            }
            Main.gameManager.CreateNewCharacter(nom.getCharacters().toString(), Main.gameManager.GUERRIER, weapons, sorts);
        }
    }

}
