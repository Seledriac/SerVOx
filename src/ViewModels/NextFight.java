package ViewModels;

import Application.Main;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NextFight implements Initializable {

    @FXML
    protected Button btn_jouer;
    @FXML
    protected Text texte_niveau;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int niveau = Main.gameManager.getNiveau();
        texte_niveau.setText("Niveau " + String.valueOf(niveau));
        if(niveau > 1) {
            btn_jouer.setText("Continuer");
        }
    }

    @FXML
    public void lancerNiveau(MouseEvent mouseEvent) throws IOException, MageException, GuerrierException, ChasseurException, CreationException {
        Main.gameManager.loadCombat();
    }

    @FXML
    protected void retour_choose_character(MouseEvent mouseEvent) throws IOException {
        Main.gameManager.saveCharacter();
    }

}
