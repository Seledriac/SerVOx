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

public class NouveauMage implements Initializable {

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

        Image img_flamme_interdite = new Image("/Views/Images/Flamme Interdite.jpg");
        ImageView view_flamme_interdite = new ImageView(img_flamme_interdite);
        view_flamme_interdite.setFitWidth(210);
        view_flamme_interdite.setFitHeight(210);
        flamme_interdite.setGraphic(view_flamme_interdite);

        Image img_reveil_des_arcanes = new Image("/Views/Images/Réveil des Arcanes.jpg");
        ImageView view_reveil_des_arcanes = new ImageView(img_reveil_des_arcanes);
        view_reveil_des_arcanes.setFitWidth(210);
        view_reveil_des_arcanes.setFitHeight(210);
        reveil_des_arcanes.setGraphic(view_reveil_des_arcanes);

        Image img_tempete = new Image("/Views/Images/Tempête.jpg");
        ImageView view_tempete = new ImageView(img_tempete);
        view_tempete.setFitWidth(210);
        view_tempete.setFitHeight(210);
        tempete.setGraphic(view_tempete);
    }

    @FXML
    protected void retour_nouveau_personnage(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadNouveauPersonnageView();
    }

    @FXML
    protected void selectFlammeInterdite(MouseEvent mouseEvent) {
        num_sort = 1;
    }

    @FXML
    protected void selectReveilDesArcanes(MouseEvent mouseEvent) {
        num_sort = 2;
    }

    @FXML
    protected void selectTempete(MouseEvent mouseEvent) {
        num_sort = 3;
    }

    @FXML
    protected void loadGame(MouseEvent mouseEvent) throws IOException, CreationException, GuerrierException, ChasseurException, MageException {
        String nom_perso = nom.getCharacters().toString();
        if(num_sort != 0 && nom_perso != "" && nom_perso.length() <= 10) {
            ArrayList<Item> items = new ArrayList<>();
            items.add(new SortOffensif(Accessibilite.MAGES, "Petite Boule de Feu", 0, 15, 5));
            switch (num_sort) {
                case 1:
                    items.add(new SortOffensif(Accessibilite.MAGES, "Flamme Interdite", 0, 30, 20));
                    break;
                case 2:
                    items.add(new SortOffensif(Accessibilite.MAGES, "Réveil des Arcanes", 0, 50, 40));
                    break;
                case 3:
                    items.add(new SortOffensif(Accessibilite.MAGES, "Tempête", 0, 35, 25));
                    break;
                default:
                    break;
            }
            Main.gameManager.creerNouveauPersonnage(TypePerso.MAGE, nom_perso, items);
        }
    }

}
