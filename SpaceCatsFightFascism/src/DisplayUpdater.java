import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DisplayUpdater extends Application {

    Rectangle[][] planetIcons;
    Planet[][] planetLayout;


    @Override
    public void start(Stage stage){

        Pane root = new Pane();
        Scene scene = new Scene(root, 1050, 600);
        ViewElements gameElements = new ViewElements();

        //Setting title to the scene 
        stage.setTitle("Space Cats Fight Fascism");
        root.getChildren().add(gameElements.getBackgroundImage("res/game_bg.jpg"));


        //Menu
        root.getChildren().addAll(drawRectangle(840,0,210,600), drawRectangle(0, 450, 1050, 150));
        
        //Draw Planets
        root.getChildren().addAll(gameElements.drawPlanets(planetLayout));

        Buttons b = new Buttons();
        root.getChildren().addAll(b.createPlayerButtons());

        stage.setScene(scene);
        stage.show();
    }

    //update cat locations
    /*public Circle drawCat(Cat cat){

    }*/

    //update token counts
    /*public void tokenDisplay(Planet[][]){
        
    }*/


    //update flags

    //update player controls/cards

    public void initDisplay(String[] args) {
        launch(args);
    }

    private Rectangle drawRectangle(int x, int y, int width, int height){

        Rectangle rect = new Rectangle(x,y,width,height);
        rect.setFill(Color.LIGHTBLUE);

        return rect;
    }

    public void setPlanetLayout(Planet[][] layout){
        planetLayout = layout;
    }
}
