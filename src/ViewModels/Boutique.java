package ViewModels;

import Application.Main;
import Models.DB.ItemsDBHandler;
import Models.DB.PersonnagesDBHandler;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.classes.TypePerso;
import Models.items.Arme;
import Models.items.Bouclier;
import Models.items.Item;
import Models.items.SortOffensif;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaException;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Boutique implements Initializable {

    private int i;
    Personnage joueur;
    ArrayList<Item> items;
    @FXML
    private ImageView image_item;
    @FXML
    protected Button btn_acheter;
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
    protected Text cout_item;
    @FXML
    protected Text argent_perso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joueur = Main.gameManager.getPersonnage();
        argent_perso.setText("Argent : " + joueur.getArgent() + "€");
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

    @FXML
    protected void achat(MouseEvent mouseEvent) {
        if(joueur.acheter(items.get(i))) {
            items.removeIf(item -> item.getNom().equals(items.get(i).getNom()));
            PersonnagesDBHandler.updatePersonnage(joueur);
        }
        next(null);
        argent_perso.setText("Argent : " + joueur.getArgent() + "€");
    }

    private void afficherItem() {
        Item item = items.get(i);
        image_item.setImage(new Image("/Views/Images/" + item.getNom() + ".jpg"));
        nom_item.setText(item.getNom());
        type_item.setText(item.getClass().getSimpleName());
        cout_item.setText("Coût : " + item.getCout_argent());
        if(item.getCout_argent() > joueur.getArgent())
            btn_acheter.setDisable(true);
        else
            btn_acheter.setDisable(false);
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
