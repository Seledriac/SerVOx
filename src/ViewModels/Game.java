package ViewModels;

import Application.Main;
import Models.classes.Chasseur;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.items.*;
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
import java.util.ResourceBundle;

public class Game implements Initializable {

    @FXML
    protected TextArea logs;
    @FXML
    protected Button btn_action;
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
    protected ComboBox<String> actions;
    @FXML
    protected Text degats_action;
    @FXML
    protected Text cout_action;
    private Personnage perso;
    private Personnage ennemi;
    private ArrayList<Item> liste_actions;

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
        classe_niveau.setText(classe + " : Niveau " + perso.getNiveau());
        vie.setText("Vie : " + perso.getVie_max());
        mana.setText("Mana : " + perso.getMana_max());
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
        classe_niveau_ennemi.setText(classe_ennemi + " : Niveau " + ennemi.getNiveau());
        vie_ennemi.setText("Vie : " + ennemi.getVie_max());
        mana_ennemi.setText("Mana : " + ennemi.getMana_max());
        liste_actions = new ArrayList<>();
        if(perso instanceof Guerrier) {
            if(((Guerrier)perso).getArme_equipee() != null) {
                liste_actions.add(((Guerrier)perso).getArme_equipee());
            }
        } else if(perso instanceof Chasseur) {
            if(((Chasseur)perso).getArme_equipee() != null)
                liste_actions.add(((Chasseur)perso).getArme_equipee());
        }
        for(Item item : perso.getItems()) {
            if(item instanceof SortOffensif || item instanceof Sort)
                liste_actions.add(item);        
        }
        for(Item item : liste_actions) {
            if(item instanceof SortOffensif || item instanceof Sort)
                actions.getItems().add(item.getNom());
            else
                actions.getItems().add("action basique");
        }
        actions.getSelectionModel().selectFirst();
        actions.setOnAction((e) -> {
            updateAction(e);
        });
        updateAction(null);
        addLogs("Début du combat");
    }

    public void updateAction(ActionEvent e) {
        Item item = liste_actions.get(actions.getSelectionModel().getSelectedIndex());
        degats_action.setText("");
        if(item instanceof Arme) {
            degats_action.setText("Dégâts : " + ((Arme)item).getDegats());
        }
        cout_action.setText("");
        if(item instanceof SortOffensif) {
            cout_action.setText("Coût : " + ((SortOffensif)item).getCout_mana());
        } else if(item instanceof Sort) {
            cout_action.setText("Coût : " + ((Sort)item).getCout_mana());
        } else if(item instanceof ArmeAMunitions) {
            cout_action.setText("Flèches : " + ((ArmeAMunitions)item).getMunitions());
        }
    }

    @FXML
    protected void retour_choose_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.savePersonnage();
    }

    @FXML
    protected void action(MouseEvent mouseEvent) throws IOException, InterruptedException {
        btn_action.setDisable(true);
        btn_passer.setDisable(true);
        Main.gameManager.actionJoueur(this, liste_actions.get(actions.getSelectionModel().getSelectedIndex()));
        updateAction(null);
    }

    @FXML
    protected void passerLeTour(MouseEvent mouseEvent) throws IOException, InterruptedException {
        btn_action.setDisable(true);
        btn_passer.setDisable(true);
        Main.gameManager.finDeTourJoueur(this);
    }

    public void updateStats() {
        vie.setText("Vie : " + perso.getVie());
        mana.setText("Mana : " + perso.getMana());
        vie_ennemi.setText("Vie : " + ennemi.getVie());
        mana_ennemi.setText("Mana : " + ennemi.getMana());
    }

    public void reprendreCombat() {
        updateStats();
        btn_action.setDisable(false);
        btn_passer.setDisable(false);
    }

    public void addLogs(String texte) {
        logs.setText(texte + "\n" + logs.getText());
    }

}
