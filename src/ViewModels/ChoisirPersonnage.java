package ViewModels;

import Application.Main;
import Models.DB.PersonnagesDBHandler;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChoisirPersonnage implements Initializable {

    private int i;
    private ArrayList<Personnage> persos;
    @FXML
    protected Button choose_btn;
    @FXML
    protected Button previous_btn;
    @FXML
    protected Button next_btn;
    @FXML
    protected Text titre;
    @FXML
    protected Text msg_1;
    @FXML
    protected Text msg_2;
    @FXML
    private ImageView image_classe;
    @FXML
    protected Text texte_classe;
    @FXML
    protected Text nom_perso;
    @FXML
    protected Text niveau_perso;
    @FXML
    protected Text vie_perso;
    @FXML
    protected Text mana_perso;
    @FXML
    protected Text argent_perso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persos = PersonnagesDBHandler.listPersonnages();
        if(persos.size() > 0) {
            msg_1.setVisible(false);
            msg_2.setVisible(false);
            titre.setVisible(true);
            previous_btn.setVisible(true);
            next_btn.setVisible(true);
            choose_btn.setVisible(true);
            image_classe.setVisible(true);
            nom_perso.setVisible(true);
            texte_classe.setVisible(true);
            niveau_perso.setVisible(true);
            vie_perso.setVisible(true);
            mana_perso.setVisible(true);
            argent_perso.setVisible(true);
            i = 0;
            afficherPerso();
        }
    }

    private void afficherPerso() {
        if(persos.size() > 0) {
            Personnage perso = persos.get(i);
            if (perso instanceof Guerrier) {
                image_classe.setImage(new Image("/Views/Images/illustration_guerrier.jpg"));
                texte_classe.setText("Guerrier");
            } else if (perso instanceof Mage) {
                image_classe.setImage(new Image("/Views/Images/illustration_mage.jpg"));
                texte_classe.setText("Mage");
            } else {
                image_classe.setImage(new Image("/Views/Images/illustration_chasseur.jpg"));
                texte_classe.setText("Chasseur");
            }
            nom_perso.setText(perso.getNom());
            niveau_perso.setText("Niveau " + String.valueOf(perso.getNiveau()));
            vie_perso.setText("Vie : " + String.valueOf(perso.getVie_max()));
            mana_perso.setText("Mana : " + String.valueOf(perso.getMana_max()));
            argent_perso.setText("Argent : " + String.valueOf(perso.getArgent()) + "€");
        }
    }

    @FXML
    protected void previous(MouseEvent mouseEvent) {
        if(i > 0) {
            i--;
        } else {
            i = persos.size() - 1;
        }
        afficherPerso();
    }

    @FXML
    protected void next(MouseEvent mouseEvent) {
        if(i < persos.size() - 1) {
            i++;
        } else {
            i = 0;
        }
        afficherPerso();
    }

    @FXML
    protected void loadGame(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.choisirPersonnage(persos.get(i));
    }

    @FXML
    protected void retour_choose_menu(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadMainMenuChooseView();
    }

}
