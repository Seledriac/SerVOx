package ViewModels;

import Application.Main;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.weapons.Arc;
import Models.weapons.Arme;
import Models.weapons.Bouclier;
import Models.weapons.Sort;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
    protected TextArea logs;
    @FXML
    protected Button btn_attaquer;
    @FXML
    protected Button btn_passer;
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
        liste_attaques = new ArrayList<>();
        if(perso.getArme_equipee() != null)
            liste_attaques.add(perso.getArme_equipee());
        liste_attaques.addAll(perso.getSorts());

        liste_attaques.removeIf(arme -> arme instanceof Bouclier);
        for(Arme arme : liste_attaques) {
            if(arme instanceof Sort)
                attaques.getItems().add(arme.getNom());
            else
                attaques.getItems().add("Attaque basique");
        }
        attaques.getSelectionModel().selectFirst();
        attaques.setOnAction((e) -> {
            updateAttaque(e);
        });
        updateAttaque(null);
        addLogs("Début du combat");
    }

    public void updateAttaque(ActionEvent e) {
        Arme arme = liste_attaques.get(attaques.getSelectionModel().getSelectedIndex());
        degats_attaque.setText("Dégâts : " + String.valueOf(arme.getDamages()));
        cout_attaque.setText("");
        if(arme instanceof Sort) {
            cout_attaque.setText("Coût : " + String.valueOf(((Sort) arme).getCout()));
        }
        else if(arme instanceof Arc) {
            cout_attaque.setText("Flèches : " + String.valueOf(((Arc) arme).getFleches()));
        }
    }

    @FXML
    protected void retour_choose_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.saveCharacter();
    }

    @FXML
    protected void attaquer(MouseEvent mouseEvent) throws IOException, InterruptedException {
        btn_attaquer.setDisable(true);
        btn_passer.setDisable(true);
        Main.gameManager.attaqueJoueur(this, liste_attaques.get(attaques.getSelectionModel().getSelectedIndex()));
        updateAttaque(null);
    }

    @FXML
    protected void passerLeTour(MouseEvent mouseEvent) throws IOException, InterruptedException {
        btn_attaquer.setDisable(true);
        btn_passer.setDisable(true);
        Main.gameManager.finDeTourJoueur(this);
    }

    public void updateStats() {
        vie.setText("Vie : " + String.valueOf(perso.getHealth()));
        mana.setText("Mana : " + String.valueOf(perso.getMana()));
        vie_ennemi.setText("Vie : " + String.valueOf(ennemi.getHealth()));
        mana_ennemi.setText("Mana : " + String.valueOf(ennemi.getMana()));
    }

    public void reprendreCombat() {
        updateStats();
        btn_attaquer.setDisable(false);
        btn_passer.setDisable(false);
    }

    public void addLogs(String texte) {
        logs.setText(texte + "\n" + logs.getText());
    }

}
