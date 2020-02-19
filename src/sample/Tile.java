package sample;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Tile extends StackPane{

    ImageView img = new ImageView();
    private int identifiant ;
    private int score = 0;
    private Card memory = new Card();


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
        if (isOpen() || memory.getClickCount() == 0)
            return;

        //clickCount--;
        memory.setClickCount(memory.getClickCount()-1);

        if (memory.getSelected() == null) {
            memory.setSelected(this);
            open(() -> {});
        }
        else {
            open(() -> {
                if (!hasSameValue(memory.getSelected())) {
                    memory.getSelected().close();
                    this.close();
                    scoreJoueur();
                }

                memory.setSelected(null);
                memory.setClickCount(2);
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
        //labelScore.setText("" + this.score);
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