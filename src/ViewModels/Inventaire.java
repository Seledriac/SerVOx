package ViewModels;

import Application.Main;
import Models.DB.ItemsDBHandler;
import Models.classes.Chasseur;
import Models.classes.Guerrier;
import Models.classes.Personnage;
import Models.items.*;
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

public class Inventaire implements Initializable {

    private int i;
    Personnage joueur;
    @FXML
    private ImageView image_item;
    @FXML
    protected Button previous_btn;
    @FXML
    protected Button next_btn;
    @FXML
    protected Text titre;
    @FXML
    protected Text type_item;
    @FXML
    protected Text nom_item;
    @FXML
    protected Text degats_item;
    @FXML
    protected Text cout_mana_item;
    @FXML
    protected Text munitions_max_item;
    @FXML
    protected Text argent_perso;
    @FXML
    protected Button btn_equiper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joueur = Main.gameManager.getPersonnage();
        argent_perso.setText("Argent : " + joueur.getArgent() + "€");
        i = 0;
        afficherItem();
    }

    @FXML
    protected void retour_prochain_combat(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.startGame();
    }

    private void afficherItem() {
        Item item = joueur.getItems().get(i);
        image_item.setImage(new Image("/Views/Images/" + item.getNom() + ".jpg"));
        nom_item.setText(item.getNom());
        type_item.setText(item.getClass().getSimpleName());
        if((item instanceof Arme || item instanceof Bouclier) && !(item instanceof SortOffensif))
            btn_equiper.setVisible(true);
        else
            btn_equiper.setVisible(false);
        if(joueur instanceof Guerrier) {
            if(item == ((Guerrier)joueur).getArme_equipee() || item == ((Guerrier)joueur).getBouclier_equipe()) {
                btn_equiper.setText("Équipé");
                btn_equiper.setDisable(true);
            } else {
                btn_equiper.setText("Équiper");
                btn_equiper.setDisable(false);
            }
        } else if(joueur instanceof Chasseur) {
            if(item == ((Chasseur)joueur).getArme_equipee() || item == ((Chasseur)joueur).getBouclier_equipe()) {
                btn_equiper.setText("Équipé");
                btn_equiper.setDisable(true);
            } else {
                btn_equiper.setText("Équiper");
                btn_equiper.setDisable(false);
            }
        }
        if(item instanceof Bouclier) {
            degats_item.setText("Défense : " + ((Bouclier)item).getDefense());
            cout_mana_item.setText("");
            munitions_max_item.setText("");
        } else if(item instanceof Arme) {
            degats_item.setText("Dégâts : " + ((Arme)item).getDegats());
            if(item instanceof Cac) {
                cout_mana_item.setText("");
                munitions_max_item.setText("");
            } else if(item instanceof ArmeAMunitions) {
                cout_mana_item.setText("");
                if(item instanceof Arc) {
                    munitions_max_item.setText("Flèches : " + ((Arc)item).getMunitions_max());
                } else if(item instanceof Arbalete) {
                    munitions_max_item.setText("Carreaux : " + ((Arbalete)item).getMunitions_max());
                }
            } else if(item instanceof SortOffensif) {
                cout_mana_item.setText("Coût : " + ((SortOffensif)item).getCout_mana());
                munitions_max_item.setText("");
            }
        } else {
            degats_item.setText("");
            cout_mana_item.setText("Coût : " + ((Sort)item).getCout_mana());
            munitions_max_item.setText("");
        }
    }

    @FXML
    protected void previous(MouseEvent mouseEvent) {
        if(i > 0) {
            i--;
        } else {
            i = joueur.getItems().size() - 1;
        }
        afficherItem();
    }

    @FXML
    protected void next(MouseEvent mouseEvent) {
            if(i < joueur.getItems().size() - 1) {
                i++;
            } else {
                i = 0;
            }
            afficherItem();
        }

    @FXML
    protected void equiper(MouseEvent mouseEvent) {
        if(joueur instanceof Guerrier) {
            ((Guerrier)joueur).equiperArme((Arme)joueur.getItems().get(i));
        } else if(joueur instanceof Chasseur) {
            ((Chasseur)joueur).equiperArme((Arme)joueur.getItems().get(i));
        }
        afficherItem();
    }

}
