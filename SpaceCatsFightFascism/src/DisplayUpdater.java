import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayUpdater extends Application {

    GameController gc = new GameController();
    ViewElements gameElements = new ViewElements();
    boolean firstRun = true;
    Rectangle[][] planetIcons;
    Planet[][] planetLayout = new Planet[3][4];
    Text[][] tokenCounts;

    Pane root;
    Scene scene;

    //DEV TESTING DELETE
    Cat cat1 = new Cat("Ophelia", 3, null);
    Cat cat2 = new Cat("Alias:SC", 6, null);
    Planet plan1 = new Planet(3, 0, 2);
    Planet plan2 = new Planet(10, 2, 1);
    Player plyr1 = new Player(0,cat1);
    Player plyr2 = new Player(1,cat2);
    Player[] players = {plyr1,plyr2};
    //DEV TESTING DELETE


    @Override
    public void start(Stage stage){

        root = new Pane();
        scene = new Scene(root, 1050, 600);

        //Setting title to the scene 
        stage.setTitle("Space Cats Fight Fascism");
        root.getChildren().add(gameElements.getBackgroundImage("res/game_bg.jpg"));

        //Menu bar
        root.getChildren().addAll(drawRectangle(840,0,210,600), drawRectangle(0, 450, 1050, 150));
        Text resistMenu = new Text(885, 50, "RESIST CARDS");
        resistMenu.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        root.getChildren().add(resistMenu);
        
        //Draw Planets
        root.getChildren().addAll(gameElements.drawPlanets(planetLayout)); //REPLACE WITH gc.getGameState().getPlanetLayout()

        //DEV TESTING DELETE
        plan1.setIndex(2, 3);
        plan2.setIndex(0,2);
        cat1.setPlanet(plan2);
        cat2.setPlanet(plan2);
        //DEV TESTING DELETE

        //Player Actions
        Buttons b = new Buttons();
        setButtonEvents(b.createPlayerButtons());
        root.getChildren().addAll(setButtonEvents(b.createPlayerButtons()));

        System.out.println(root.getChildren().size());
        redisplay();

        stage.setScene(scene);
        stage.show();
    }

    //update player controls/cards

    //Update Game info displayed
    public Text[] displayCurrentTurn(int playerTurn, int actionsLeft){
        Text[] toAdd = new Text[2];

        Text currTurn = new Text();
        Text actionsRemain = new Text();

        currTurn.setText("Current Turn: Player " + playerTurn);
        actionsRemain.setText("Actions Remaining: " + actionsLeft);

        currTurn.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        currTurn.setX(25);
        currTurn.setY(25);
        currTurn.setFill(Color.WHITE);

        actionsRemain.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        actionsRemain.setX(25);
        actionsRemain.setY(475);

        toAdd[0] = currTurn;
        toAdd[1] = actionsRemain;
        return toAdd;
    }

    public void initDisplay() {
        launch();
    }

    public void redisplay(){
        if(!firstRun){
            root.getChildren().remove(23, 54);
        }
        else{
            firstRun = false;
        }
        //Draw token counters
        root.getChildren().addAll(gameElements.updateTokenCounters(planetLayout)); //REPLACE WITH gc.getGameState().getPlanetLayout()

        //Draw Flags
        root.getChildren().addAll(gameElements.drawFlags(planetLayout));//REPLACE WITH gc.getGameState().getPlanetLayout()

        //draw Cats
        root.getChildren().addAll(gameElements.drawCats(players));

        //Display turn info
        root.getChildren().addAll(gameElements.displayCurrentTurn(1,3));
        //gc.getGameState().getPlayerTurn()
        //gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()].getActionsRemaining()

        // Draw Resist Cards

        root.getChildren().addAll(setResistEvents(gameElements.displayResistCards(plyr1)));
        System.out.println(root.getChildren().size());

    }

    private Rectangle drawRectangle(int x, int y, int width, int height){

        Rectangle rect = new Rectangle(x,y,width,height);
        rect.setFill(Color.GREY);

        return rect;
    }

    private Button[] setButtonEvents(Button[] playerActions){
        // Travel Up
        playerActions[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRAVEL UP BUTTON PRESSED");
                cat1.setPlanet(plan1);
                System.out.println(root.getChildren().size());
                redisplay();
            }
        });
        // Travel Down
        playerActions[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRAVEL DOWN BUTTON PRESSED");
                cat1.setPlanet(plan2);
                redisplay();
            }
        });
        // Travel Left
        playerActions[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRAVEL LEFT BUTTON PRESSED");
            }
        });
        // Travel Right
        playerActions[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRAVEL RIGHT BUTTON PRESSED");
            }
        });
        // Fight Fascism
        playerActions[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("FIGHT BUTTON PRESSED");
                gc.fight();
            }
        });
        // Restock Resist Cards
        playerActions[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RESTOCK BUTTON PRESSED");
                gc.restock();
            }
        });
        //Use Resist Card
        playerActions[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RESIST BUTTON PRESSED");
            }
        });
        //End Turn (early)
        playerActions[7].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("END BUTTON PRESSED");
                //AWAITING END TURN EARLY IMPLEMENTATION
            }
        });
        return playerActions;
    }

    private Rectangle[] setResistEvents(Rectangle[] cardActions){
        // Travel Up
        cardActions[0].setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("RESIST 0 PICKED");
            }
        });
        cardActions[1].setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("RESIST 1 PICKED");
            }
        });
        cardActions[2].setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("RESIST 2 PICKED");
            }
        });
        return cardActions;
    }
}
