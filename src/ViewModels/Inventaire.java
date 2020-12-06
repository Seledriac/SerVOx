package ViewModels;

import Application.Main;
import Models.DB.ItemsDBHandler;
import Models.classes.Personnage;
import Models.items.Item;
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
    ArrayList<Item> items;
    @FXML
    private ImageView image_item;
    @FXML
    protected Button previous_btn;
    @FXML
    protected Button next_btn;
    @FXML
    protected Text titre;
    @FXML
    protected Text type_arme;
    @FXML
    protected Text nom_arme;
    @FXML
    protected Text degats_arme;
    @FXML
    protected Text cout_mana_arme;
    @FXML
    protected Text munitions_max_arme;
    @FXML
    protected Text argent_perso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joueur = Main.gameManager.getPersonnage();
        items = ItemsDBHandler.listItems();
        for(Item item : joueur.getItems()) {
            items.removeIf(item1 -> item.getNom().equals(item1.getNom()));
        }
        i = 0;
        afficherItem();
    }

    @FXML
    protected void retour_prochain_combat(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.startGame();
    }

    private void afficherItem() {
        Item item = items.get(i);
        image_arme.setImage(new Image("/Views/Images/" + item.getNom() + ".jpg"));
        nom_arme.setText(item.getNom());
        type_arme.setText(item.getClass().getSimpleName());
        cout_arme.setText("Coût : " + item.getCout_argent());
    }

    @FXML
    protected void previous(MouseEvent mouseEvent) {
        if(i > 0) {
            i--;
        } else {
            i = items.size() - 1;
        }
        afficherItem();
    }

    @FXML
    protected void next(MouseEvent mouseEvent) {
            if(i < items.size() - 1) {
                i++;
            } else {
                i = 0;
            }
            afficherItem();
        }
}
