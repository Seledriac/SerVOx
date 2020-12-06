package ViewModels;

import Application.Main;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.classes.TypePerso;
import Models.items.*;
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

public class NouveauGuerrier implements Initializable {

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

        Image img_durandal = new Image("/Views/Images/Durandal.jpg");
        ImageView view_durandal = new ImageView(img_durandal);
        view_durandal.setFitWidth(210);
        view_durandal.setFitHeight(210);
        durandal.setGraphic(view_durandal);

        Image img_sobek = new Image("/Views/Images/Sobek.jpg");
        ImageView view_sobek = new ImageView(img_sobek);
        view_sobek.setFitWidth(210);
        view_sobek.setFitHeight(210);
        sobek.setGraphic(view_sobek);

        Image img_excalibur = new Image("/Views/Images/Excalibur.jpg");
        ImageView view_excalibur = new ImageView(img_excalibur);
        view_excalibur.setFitWidth(210);
        view_excalibur.setFitHeight(210);
        excalibur.setGraphic(view_excalibur);
    }

    @FXML
    protected void retour_nouveau_personnage(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNouveauPersonnageView();
    }

    @FXML
    protected void selectDurandal(MouseEvent mouseEvent) {
        num_epee = 1;
    }

    @FXML
    protected void selectSobek(MouseEvent mouseEvent) {
        num_epee = 2;
    }

    @FXML
    protected void selectExcalibur(MouseEvent mouseEvent) {
        num_epee = 3;
    }

    @FXML
    protected void loadGame(MouseEvent mouseEvent) throws IOException, CreationException, GuerrierException, ChasseurException, MageException {
        String nom_perso = nom.getCharacters().toString();
        if(num_epee != 0 && nom_perso != "" && nom_perso.length() <= 10) {
            ArrayList<Item> items = new ArrayList<>();
            items.add(new Bouclier(Accessibilite.GUERRIERS, "Bouclier en Fer", 0, 5));
            switch (num_epee) {
                case 1:
                    items.add(new Epee(Accessibilite.GUERRIERS, "Durandal", 0, 10));
                    items.add(new SortOffensif(Accessibilite.GUERRIERS, "Démacia", 0, 20, 3));
                    break;
                case 2:
                    items.add(new Epee(Accessibilite.GUERRIERS, "Sobek", 0, 15));
                    items.add(new SortOffensif(Accessibilite.GUERRIERS, "Démacia", 0, 20, 3));
                    break;
                case 3:
                    items.add(new Epee(Accessibilite.GUERRIERS, "Excalibur", 0, 20));
                    break;
                default:
                    break;
            }
            Main.gameManager.creerNouveauPersonnage(TypePerso.GUERRIER, nom_perso, items);
        }
    }

}
