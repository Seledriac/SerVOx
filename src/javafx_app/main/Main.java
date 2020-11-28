package javafx_app.main;

import game_app.Exceptions.ChasseurException;
import game_app.Exceptions.GuerrierException;
import game_app.Exceptions.MageException;
import game_app.classes.Chasseur;
import game_app.classes.Guerrier;
import game_app.classes.Mage;
import game_app.weapons.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import game_app.Exceptions.CreationException;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            // Création d'armes
            Arc bow = new Arc("Le Chasseur",10,6);
            Epee sword = new Epee("Excalibur",5);
            Bouclier shield = new Bouclier("La Montagne",0,2);

            // Création de sorts
            Sort demacia = new Sort("Démacia", 10, 3);
            Sort traque = new Sort("Traque", 15, 3);
            Sort explosion = new Sort("EXPLOSION", 15, 3);
            Sort reveil_des_arcanes = new Sort("Réveil des Arcanes", 30, 10);


            // Création de listes d'armes

            // Pour le guerrier
            ArrayList<Arme> stuff_guerrier = new ArrayList<Arme>();
            ArrayList<Sort> sorts_guerrier = new ArrayList<Sort>();
            stuff_guerrier.add(sword);
            stuff_guerrier.add(shield);
            sorts_guerrier.add(demacia);

            // Pour le chasseur
            ArrayList<Arme> stuff_chasseur = new ArrayList<Arme>();
            ArrayList<Sort> sorts_chasseur = new ArrayList<Sort>();
            stuff_chasseur.add(bow);
            sorts_chasseur.add(traque);

            // Pour le mage
            ArrayList<Arme> stuff_mage = new ArrayList<Arme>();
            ArrayList<Sort> sorts_mage = new ArrayList<Sort>();
            sorts_mage.add(explosion);
            sorts_mage.add(reveil_des_arcanes);

            // Création des personnages
            Guerrier guerrier = new Guerrier(50,10,1, stuff_guerrier, sorts_guerrier);
            Chasseur hunter = new Chasseur(34,10,1, stuff_chasseur, sorts_chasseur);
            Mage mage = new Mage(30,30,1, stuff_mage, sorts_mage);

            // Combat
            System.out.println(hunter.getHealth());
            guerrier.cac_guerrier(hunter);
            System.out.println(hunter.getHealth());

            System.out.println(guerrier.getHealth());
            hunter.sort_basique(guerrier);
            System.out.println(guerrier.getHealth());

            System.out.println(hunter.getHealth());
            mage.sort_ultime(hunter);
            System.out.println(hunter.getHealth());


        } catch (CreationException | ChasseurException | GuerrierException | MageException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}
