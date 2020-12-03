package ViewModels;

import Application.Main;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.weapons.Arme;
import Models.weapons.Bouclier;
import Models.weapons.Sort;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class Game implements Initializable {

    @FXML
    protected Text nom_perso;
    @FXML
    protected Text classe_niveau;
    @FXML
    protected Text vie;
    @FXML
    protected Text mana;
    @FXML
    protected ImageView image_classe;
    @FXML
    protected Text nom_ennemi;
    @FXML
    protected Text classe_niveau_ennemi;
    @FXML
    protected Text vie_ennemi;
    @FXML
    protected Text mana_ennemi;
    @FXML
    protected ImageView image_classe_ennemi;
    @FXML
    protected ComboBox<String> attaques;
    @FXML
    protected Text degats_attaque;
    @FXML
    protected Text cout_attaque;
    private Personnage perso;
    private Personnage ennemi;
    private ArrayList<Arme> liste_attaques;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        perso = Main.gameManager.getPersonnage();
        ennemi = Main.gameManager.getEnnemi();
        nom_perso.setText(perso.getNom());
        String classe = "";
        if(perso instanceof Guerrier) {
            classe = "Guerrier";
            image_classe.setImage(new Image("/Views/Images/illustration_guerrier.jpg"));
        }
        else if(perso instanceof Mage) {
            classe = "Mage";
            image_classe.setImage(new Image("/Views/Images/illustration_mage.jpg"));
        }
        else {
            classe = "Chasseur";
            image_classe.setImage(new Image("/Views/Images/illustration_chasseur.jpg"));
        }
        classe_niveau.setText(classe + " : Niveau " + String.valueOf(perso.getLevel()));
        vie.setText("Vie : " + String.valueOf(perso.getHealth()));
        mana.setText("Mana : " + String.valueOf(perso.getMana()));
        nom_ennemi.setText(ennemi.getNom());
        String classe_ennemi = "";
        if(ennemi instanceof Guerrier) {
            classe_ennemi = "Guerrier";
            image_classe_ennemi.setImage(new Image("/Views/Images/illustration_guerrier.jpg"));
        }
        else if(ennemi instanceof Mage) {
            classe_ennemi = "Mage";
            image_classe_ennemi.setImage(new Image("/Views/Images/illustration_mage.jpg"));
        }
        else {
            classe_ennemi = "Chasseur";
            image_classe_ennemi.setImage(new Image("/Views/Images/illustration_chasseur.jpg"));
        }
        classe_niveau_ennemi.setText(classe_ennemi + " : Niveau " + String.valueOf(ennemi.getLevel()));
        vie_ennemi.setText("Vie : " + String.valueOf(ennemi.getHealth()));
        mana_ennemi.setText("Mana : " + String.valueOf(ennemi.getMana()));
        Set<Arme> set = new HashSet<>();
        set.addAll(perso.getWeapons());
        set.addAll(perso.getSorts());
        liste_attaques = new ArrayList<>(set);
        liste_attaques.removeIf(arme -> arme instanceof Bouclier);
        for(Arme arme : liste_attaques) {
            attaques.getItems().add(arme.getNom());
        }
        attaques.getSelectionModel().selectFirst();
        attaques.setOnAction((e) -> {
            updateAttaque(e);
        });
        updateAttaque(null);
    }

    private void updateAttaque(ActionEvent e) {
        Arme arme = null;
        if(e == null) {
             arme = liste_attaques.get(0);
        } else {
             arme = liste_attaques.get(attaques.getSelectionModel().getSelectedIndex());
        }
        degats_attaque.setText("Dégâts : " + String.valueOf(arme.getDamages()));
        cout_attaque.setText("Coût : 0");
        if(arme instanceof Sort)
            cout_attaque.setText("Coût : " + String.valueOf(((Sort)arme).getCout()));
    }

    @FXML
    protected void retour_choose_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.saveCharacter();
    }

    @FXML
    protected void attaquer(MouseEvent mouseEvent) {
    }

    @FXML
    protected void passerLeTour(MouseEvent mouseEvent) {
    }
}
