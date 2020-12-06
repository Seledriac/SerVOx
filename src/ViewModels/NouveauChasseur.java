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

public class NouveauChasseur implements Initializable {

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

        Image img_thoridal = new Image("/Views/Images/Thor'Idal.jpg");
        ImageView view_thoridal = new ImageView(img_thoridal);
        view_thoridal.setFitWidth(210);
        view_thoridal.setFitHeight(210);
        thoridal.setGraphic(view_thoridal);

        Image img_atiesh = new Image("/Views/Images/Atiesh.jpg");
        ImageView view_atiesh = new ImageView(img_atiesh);
        view_atiesh.setFitWidth(210);
        view_atiesh.setFitHeight(210);
        atiesh.setGraphic(view_atiesh);

        Image img_valanyr = new Image("/Views/Images/Val'Anyr.jpg");
        ImageView view_valanyr = new ImageView(img_valanyr);
        view_valanyr.setFitWidth(210);
        view_valanyr.setFitHeight(210);
        valanyr.setGraphic(view_valanyr);
    }

    @FXML
    protected void retour_nouveau_personnage(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNouveauPersonnageView();
    }

    @FXML
    protected void selectThoridal(MouseEvent mouseEvent) {
        num_arc = 1;
    }

    @FXML
    protected void selectAtiesh(MouseEvent mouseEvent) {
        num_arc = 2;
    }

    @FXML
    protected void selectValanyr(MouseEvent mouseEvent) {
        num_arc = 3;
    }

    @FXML
    protected void loadGame(MouseEvent mouseEvent) throws IOException, CreationException, GuerrierException, ChasseurException, MageException {
        String nom_perso = nom.getCharacters().toString();
        if(num_arc != 0 && nom_perso != "" && nom_perso.length() <= 10) {
            ArrayList<Item> items = new ArrayList<>();
            switch(num_arc) {
                case 1:
                    items.add(new Arc(Accessibilite.CHASSEURS, "Thor'Idal", 0, 10, 10));
                    break;
                case 2:
                    items.add(new Arc(Accessibilite.CHASSEURS, "Atiesh", 0, 15, 7));
                    break;
                case 3:
                    items.add(new Arc(Accessibilite.CHASSEURS, "Val'Anyr", 0, 20, 5));
                    break;
                default:
                    break;
            }
            items.add(new SortOffensif(Accessibilite.CHASSEURS, "Traque", 0, 20, 10));
            Main.gameManager.creerNouveauPersonnage(TypePerso.CHASSEUR, nom_perso, items);
        }
    }

}
