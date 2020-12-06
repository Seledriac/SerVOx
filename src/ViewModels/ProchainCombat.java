package ViewModels;

import Application.Main;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProchainCombat implements Initializable {

    @FXML
    protected Text texte_niveau;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int niveau = Main.gameManager.getNiveau();
        texte_niveau.setText("Niveau " + String.valueOf(niveau));
    }

    @FXML
    public void lancerNiveau(MouseEvent mouseEvent) throws IOException, MageException, GuerrierException, ChasseurException, CreationException {
        Main.gameManager.loadCombat();
    }

    @FXML
    protected void retour_choisir_personnage(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.savePersonnage();
    }

    @FXML
    protected void loadBoutique(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadBoutiqueView();
    }

    @FXML
    protected void loadInventaire(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.loadInventaireView();
    }
}
