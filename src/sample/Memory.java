package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public class Memory extends Application {

    private Tile selected = null;
    private int clickCount = 2;
    private Label labelScore = new Label();
    private BorderPane panel1 = new BorderPane();
    private BorderPane panel2 = new BorderPane();

    private Parent createContent(){


        Pane root = new Pane();
        labelScore.setText("test");
        labelScore.setContentDisplay(ContentDisplay.BOTTOM);
        root.setPadding(new Insets(10));
        root.getChildren().add(labelScore);
        root.setPrefSize(400,300);

        List<Tile> tiles = new ArrayList<>();

        for(int i=0 ; i < 10 ; i++){
            tiles.add(new Tile(new ImageView(new Image("image/"+(i+1)+".jpeg")),i+1));
            tiles.add(new Tile(new ImageView(new Image("image/"+(i+1)+".jpeg")),i+1));


        }

        Collections.shuffle(tiles);

        for (int i = 0 ; i < tiles.size() ; i++){
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 5));
            tile.setTranslateY(50 * (i / 5));
            root.getChildren().add(tile);
        }

        Collections.shuffle(tiles);

        return root ;


    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private class Tile extends StackPane{

        ImageView img = new ImageView();
        private int identifiant ;
        private int score = 0;


        public int getIdentifiant() {
            return this.identifiant;
        }

        public void setId(int id) {
            this.identifiant = id;
        }

        public Tile(ImageView image , int identif){
            Rectangle border = new Rectangle (50 , 50);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            image.setFitWidth(50);
            image.setFitHeight(50);
            img=image;


            getChildren().addAll(border,img);
            setOnMouseClicked(this::handleMouseClick);
            identifiant=identif;
            close();
        }

        public void handleMouseClick(MouseEvent event) {
            if (isOpen() || clickCount == 0)
                return;

            clickCount--;

            if (selected == null) {
                selected = this;
                open(() -> {});
            }
            else {
                open(() -> {
                    if (!hasSameValue(selected)) {
                        selected.close();
                        this.close();
                        scoreJoueur();
                    }

                    selected = null;
                    clickCount = 2;
                });
            }
        }
        public void open(Runnable action){
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),img);
            ft.setToValue(1);
            ft.setOnFinished(e->action.run());
            ft.play();
        }

        public void close(){
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),img);
            ft.setToValue(0);
            ft.play();
        }

        public boolean hasSameValue(Tile other){
            return this.getIdentifiant()==other.getIdentifiant();
        }

        public boolean isOpen(){
            return img.getOpacity()==1;
        }

        public int scoreJoueur()
        {
            labelScore.setText("" + this.score);
            return this.score += 1;

        }
        public void changeScore()
        {
        }

        public int getScore()
        {
            return this.score;
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}