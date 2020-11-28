package javafx_app.main;

import game_app.classes.Chasseur;
import game_app.classes.Guerrier;
import game_app.weapons.Arc;
import game_app.weapons.Arme;
import game_app.weapons.Bouclier;
import game_app.weapons.Epee;
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
        launch(args);

        try {
            Arc bow = new Arc("Le Chasseur",10,6);
            Epee sword = new Epee("Excalibur",21);
            Bouclier shield = new Bouclier("La Montagne",0,14);
            ArrayList<Arme> liste = new ArrayList<Arme>();
            ArrayList<Arme> liste2 = new ArrayList<Arme>();
            liste.add(sword);
            liste.add(shield);
            liste2.add(bow);
            Guerrier guerrier = new Guerrier(50,5,1,liste);
            Chasseur hunter = new Chasseur(34,17,1,liste2);
            System.out.println(guerrier.getHealth());
            System.out.println(((Arc) liste2.get(0)).getFleches());

        } catch (CreationException e) {
            e.printStackTrace();
        }


    }
}
