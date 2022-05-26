package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class SinglePlayerController implements Initializable {

    //randomly created food
    Random rand = new Random();
    Button buttonRestart;

    double foodX; //Math.floor(Math.random()*11)*50;
    double foodY; //Math.floor(Math.random()*11)*50;
    //----------------------------------------
    double redTimer = 0;
    //speed
    double speedSnake = 50;
    double speedMouse = 25;
    //snake's length
    int snakeLength = 2;
    //A snake body part is 50x50
    private Double snakeSize = 50.;
    //The head of the snake is created, at position (250,250)
    Button additionButton;
    private Rectangle snakeHead = new Rectangle(250, 250, snakeSize, snakeSize);
    //private final Rectangle mouseTest = new Rectangle(snakeSize, snakeSize);


    Rectangle food;
    //----------------------------------------
    
    //First snake tail created behind the head of the snake
    Rectangle snakeTail_1 = new Rectangle(snakeHead.getX() - snakeSize, snakeHead.getY(), snakeSize, snakeSize);
    Rectangle mouse = new Rectangle(50, 50);

    //x and y position of the snake head different from starting position
    double xPos = snakeHead.getLayoutX();
    double yPos = snakeHead.getLayoutY();
    double xPosT = mouse.getLayoutX();
    double yPosT = mouse.getLayoutY();
    double xSnake = 100;
    double ySnake = 100;
    double xMouse = 450;
    double yMouse = 450;
    double xMouseTranslate, yMouseTranslate;
    //Direction snake is moving at start
    private Direction direction = Direction.RIGHT;
   

    //List of all position of the snake head
    private final List<Position> positions = new ArrayList<>();
    List<Position> mousepositions = new ArrayList<>();

    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();


    private List<KeyFrame> keyFrames = new ArrayList<KeyFrame>();

    private int gameTicks = 0;
    boolean isRed = false;



    @FXML
    ImageView imageView;
    private double x = 450.0, y = 375.0;
    private boolean isGameOver = true;

    Image yukari = new Image(getClass().getResourceAsStream("yukari.png"));
    Image asagi = new Image(getClass().getResourceAsStream("asagi.png"));
    Image sag = new Image(getClass().getResourceAsStream("sag.png"));
    Image sol = new Image(getClass().getResourceAsStream("sol.png"));
    Image Apple = new Image(getClass().getResourceAsStream("appleLarge.png"));


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;



    Timeline timeline = new Timeline();
    KeyFrame kf1 = new KeyFrame(Duration.millis(500), e -> {

        if (isGameOver == true) {
            positions.add(new Position(snakeHead.getX() + xPos, snakeHead.getY() + yPos));
            mousepositions.add(new Position(mouse.getX() + xPos, mouse.getY() + yPos));
            moveSnakeHead(snakeHead);
            eatFood(food);
            mouseCut(mouse, snakeHead);
            for (int i = 1; i < snakeBody.size(); i++) {
                moveSnakeTail(snakeBody.get(i), i);
            }
            moveMouse(mouse, xPos, yPos);
            
            movement();
            gameTicks++;

            food.setFill(new ImagePattern(Apple));
        } else if (isGameOver == false) {
            BorderPane borderPane = new BorderPane();
            Scene scene = new Scene(borderPane, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Game Over...");
            borderPane.getChildren().add(new Text("Game Over."));

            stage.show();
            timeline.stop();


        }

        redPoint();


    });
    KeyFrame kf2 = new KeyFrame(Duration.millis(300), e -> {
        System.out.println("keyframe 2");
        redPoint();
    });


    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        snakeHead.setX(100);
        snakeHead.setY(100);
        mouse.setX(450);
        mouse.setY(375);


        snakeBody.add(snakeHead);


        snakeHead.setFill(Color.RED);
        mouse.setFill(Color.GREEN);

        imageView.setImage(sag);
        //BURASI-----------------------------------------
        setFood();
        //--------------
        System.out.println("initial snake x: " + xSnake);
        System.out.println("double x: " + x + " y: " + y);
        System.out.println("initial mouse x: " + mouse.getX() + " y: " + mouse.getY());
        System.out.println("initial mouselayout x: " + mouse.getLayoutX() + " y: " + mouse.getLayoutY());
        //System.out.println("initial mouseTest x: " + mouseTest.getLayoutX());

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(kf1);

        timeline.play();

        snakeBody.add(snakeTail_1);
        

        anchorPane.getChildren().addAll(snakeHead, snakeTail_1, food, mouse);
    }


    //Key event handler
    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.W) && direction != Direction.DOWN) {
            direction = Direction.UP;
        } else if (event.getCode().equals(KeyCode.S) && direction != Direction.UP) {
            direction = Direction.DOWN;
        } else if (event.getCode().equals(KeyCode.A) && direction != Direction.RIGHT) {
            direction = Direction.LEFT;
        } else if (event.getCode().equals(KeyCode.D) && direction != Direction.LEFT) {
            direction = Direction.RIGHT;
        }

    }

    //Red point on the snake
    private Rectangle redPoint() {
        //red: 0xff0000ff
        int randNum = rand.nextInt(1, snakeBody.size());
        getTailPos();
        Rectangle redRect = new Rectangle(50, 50, snakeBody.get(randNum).getX(), snakeBody.get(randNum).getY());
        System.out.println("helo");
        if (isRed) {
            for (int i = 1; i < snakeBody.size(); i++) {

                if (snakeBody.get(i).getFill() == Color.RED) {
                    snakeBody.get(i).setFill(Color.BLACK);
                }
            }
        }
        if (true) {
            for (int i = 0; i < snakeBody.size(); i++) {
                if (i == randNum) {
                    snakeBody.get(i).setFill(Color.RED);
                    redRect = snakeBody.get(i);
                    isRed = true;
                }
            }
        }

        return redRect;

    }


    //Create another snake body part
    @FXML
    void addBodyPart(ActionEvent event) {
        addSnakeTail();

    }

    //Remove snake body part
    @FXML
    void removeBodyPart(ActionEvent event) {
        addSnakeTail();
    }

    //Snake head is moved in the direction specified
    public void gameOver() {

        if (xSnake < 0.0 || xSnake > 1000.0 || ySnake < 0.0 || ySnake > 800.0) isGameOver = false;

    }

    private void moveSnakeHead(Rectangle snakeHead) {

        if (direction.equals(Direction.RIGHT)) {
            xPos = xPos + snakeSize;
            xSnake += 50;
            gameOver();
            snakeHead.setTranslateX(xPos);
            //snakeHead.setX(xPos);
        } else if (direction.equals(Direction.LEFT)) {
            xPos = xPos - snakeSize;
            xSnake -= 50;
            gameOver();
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP)) {
            yPos = yPos - snakeSize;
            ySnake -= 50;
            gameOver();
            snakeHead.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN)) {
            yPos = yPos + snakeSize;
            ySnake += 50;
            gameOver();
            snakeHead.setTranslateY(yPos);
        }
        

    }
   

    //food BURASI-----------------------------------------
    private void setFood() {
        //Creates the first food at the beginning
        double[] coords = newFood();
        double x = coords[0];
        double y = coords[1];

        this.food = new Rectangle(x, y, 50, 50);
    }

    //create food
    private double[] newFood() {

        boolean isDone = false;

        double x, y, xMouse, yMouse;
        double noX = 0, noY = 0;

        var randX = Math.floor(Math.random() * 11) * 50;
        var randY = Math.floor(Math.random() * 11) * 50;

        for (int i = 0; i < snakeBody.size(); i++) {

            x = snakeBody.get(i).getX();
            y = snakeBody.get(i).getY();

            xMouse = mouse.getX();
            yMouse = mouse.getY();
            //check if new generated coordinates are on the snake or on the mouse. If so, generate them again
            if ((randX == x && randY == y) || (randX == xMouse && randY == yMouse)) {

                noX = x;
                noY = y;

                randX = Math.floor(Math.random() * 11) * 50;
                randY = Math.floor(Math.random() * 11) * 50;
            }

            if (randX == noX && randY == noY) {

                randX = Math.floor(Math.random() * 11) * 50;
                randY = Math.floor(Math.random() * 11) * 50;
            }
        }
        double[] coords = new double[]{randX, randY};
        return coords;

    }

    //Eat food
    private void eatFood(Rectangle food) {

        double[] coords = newFood();

        double randX = coords[0];
        double randY = coords[1];
        //If snake's head touches the food, remove the food and add a new one
        if (xPos == food.getX() - 100 && yPos == food.getY() - 100) {
            System.out.println("yes");
            this.food = new Rectangle(randX, randY, 50, 50);
            anchorPane.getChildren().remove(food);
            anchorPane.getChildren().add(this.food);
            addSnakeTail();

        }
    }

    //------------------------------------------------------------

    //speed up
    private void speedUp() {
    }

    //Mouse cut
    private void mouseCut(Rectangle mouse, Rectangle snake) {

        if ((mouse.getX() + 330 == xPos) && (mouse.getY() + 275 == yPos)) {
            
            removeSnakeTail();
        }
    }
    public void moveMouse(Rectangle mouse, double redX, double redY) {
        if (xMouse <= xSnake) {
            xMouseTranslate += speedMouse;
            xMouse += speedMouse;
            mouse.setTranslateX(xMouseTranslate);
            mouse.setFill(new ImagePattern(sag));
            // mouseTest.setX(x += 50);
        } else if (xMouse > xSnake) {
            xMouseTranslate -= speedMouse;
            xMouse -= speedMouse;
            mouse.setTranslateX(xMouseTranslate);
            //mouseTest.setX(x -= 50);
            mouse.setFill(new ImagePattern(sol));
        }

        if (yMouse > ySnake) {

            System.out.println(">");
            yMouseTranslate -= speedMouse;
            yMouse -= speedMouse;
            mouse.setTranslateY(yMouseTranslate);
            mouse.setFill(new ImagePattern(yukari));

            // mouseTest.setY(y += 50);

        } else if (yMouse < ySnake) {
            System.out.println("<");
            yMouseTranslate += speedMouse;
            yMouse += speedMouse;
            mouse.setTranslateY(yMouseTranslate);
            // mouseTest.setY(y -= 50);
            mouse.setFill(new ImagePattern(asagi));
        }

    }

    //A specific tail is moved to the position of the head x game ticks after the head was there
    private void moveSnakeTail(Rectangle snakeTail, int tailNumber) {
        double yPos = positions.get(gameTicks - tailNumber + 1).getYPos() - snakeTail.getY();
        double xPos = positions.get(gameTicks - tailNumber + 1).getXPos() - snakeTail.getX();
        snakeTail.setTranslateX(xPos);
        snakeTail.setTranslateY(yPos);
    }

    //New snake tail is created and added to the snake and the anchor pane
    private void addSnakeTail() {
        Rectangle rectangle = snakeBody.get(snakeBody.size() - 1);
        System.out.println("xPos: " + xPos);
        Rectangle snakeTail = new Rectangle(snakeBody.get(1).getX() + xPos + snakeSize, snakeBody.get(1).getY() + yPos, snakeSize, snakeSize);
        snakeBody.add(snakeTail);
        anchorPane.getChildren().add(snakeTail);
    }

    private void removeSnakeTail() {
        snakeBody.remove(1);
    }

    //Orhan Burası
    public void movement() {
        if (xPos > imageView.getX()) {
            imageView.setImage(sag);
            imageView.setX(x += 15);
        }
        if (yPos > imageView.getLayoutY()) {
            imageView.setImage(asagi);
            imageView.setY(y += 15);
        }
        if (xPos < imageView.getX()) {
            imageView.setImage(sol);
            imageView.setX(x -= 15);
        }
        if (yPos < imageView.getLayoutY()) {
            imageView.setImage(yukari);
            imageView.setY(y -= 15);
        }
    }

    public void getTailPos() {
        for (int i = 0; i <= snakeBody.size(); i++) {
            System.out.println("position of tail number " + i + ": is" + positions.get(i));
        }
    }

    //Orhan Burası
    public void loseTail() {
        int a = (int) (Math.random() * 4) + 1;
        Rectangle snakeTail = new Rectangle(snakeBody.get(1).getX() + xPos + snakeSize, snakeBody.get(1).getY() + yPos, snakeSize, snakeSize);
        Rectangle x = snakeBody.get(a);
        x.setFill(Color.LAVENDER);
        snakeBody.remove(x);
        x.setOpacity(0);
        if (mouse.getX() == x.getX() && mouse.getY() == x.getY()) {
            x.setFill(Color.LAVENDER);
            snakeBody.remove(x);
            x.setOpacity(0);
            System.out.println("crash");
        }
    }

    public void restart() {

    }



}
