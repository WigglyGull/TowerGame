package seng201.team25.models;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import seng201.team25.gui.MainGameController;
import seng201.team25.services.GameOverManager;
import javafx.geometry.Point2D;

public class Cart {
    private Image cartSprite = new Image(getClass().getResourceAsStream("/assets/cart/cart.png"));
    private Image treeDisplay = new Image(getClass().getResourceAsStream("/assets/cart/cartTree.png"));
    private Image rockDisplay = new Image(getClass().getResourceAsStream("/assets/cart/cartRock.png"));
    private Image fruitDisplay = new Image(getClass().getResourceAsStream("/assets/cart/cartFruit.png"));
    private int xLayout = 531;
    private int totalResource;
    private int currentResource=0;
    private Label amount;
    private Point2D position;
    private int resourceType;

    /**
    * Spawns a cart that moves upwards by a certain speed
    * @param _anchorPane Anchor pane to attach the cart image to.
    * @param speed Speed at which the cart moves
    * @param _resourceType Type of resource the cart can be filled by.
    * @param _totalResource Amount of resources it needs to be filled with
    * @param mg Game controller to send to game over manager.
    **/
    public Cart(AnchorPane anchorPane, float speed, int _resourceType, int _totalResource, MainGameController mg){
        ImageView cart = new ImageView();
        ImageView cartDisplay = new ImageView();
        amount = new Label();
        totalResource = _totalResource;
        resourceType = _resourceType;

        cart.setImage(cartSprite);
        setDisplay(cartDisplay);

        cartDisplay.setLayoutX(xLayout);
        cartDisplay.setLayoutY(505);
        amount.setLayoutX(575);
        amount.setLayoutY(720);
        amount.setText(currentResource+"/"+totalResource);
        amount.setTextFill(Color.BLACK);

        cart.setLayoutX(528);
        cart.setLayoutY(600);
        
        cartDisplay.setScaleX(0.7);
        cartDisplay.setScaleY(0.7);
        cart.setScaleX(0.7);
        cart.setScaleY(0.7);
        amount.setFont(Font.font(30));
        amount.setStyle("-fx-font-weight: bold");

        position = new Point2D(0, cart.getLayoutY());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                cart.setLayoutY(cart.getLayoutY() - speed);
                cartDisplay.setLayoutY(cartDisplay.getLayoutY() - speed);
                amount.setLayoutY(amount.getLayoutY() - speed);
                position = new Point2D(0, cart.getLayoutY());
                if(cart.getLayoutY() <= -100){
                    anchorPane.getChildren().remove(cart);
                    anchorPane.getChildren().remove(cartDisplay);
                    anchorPane.getChildren().remove(amount);
                    
                    if(currentResource != totalResource){
                        GameOverManager.gameOver = true;
                        GameOverManager.gameOverScreen(anchorPane, mg);
                    }
                    this.stop();
                }
            }
        };
        timer.start();

        anchorPane.getChildren().add(cart);
        anchorPane.getChildren().add(cartDisplay);
        anchorPane.getChildren().add(amount);
    }

    /**
    * Sets the display image of the cart to the resource of the cart
    * @param cartDisplay To attach the cart image to.
    **/
    private void setDisplay(ImageView cartDisplay){
        if(resourceType == 0){
            cartDisplay.setImage(treeDisplay);
        }else if(resourceType == 1){
            cartDisplay.setImage(rockDisplay);
        }else if(resourceType == 2){
            cartDisplay.setImage(fruitDisplay);
        }
    }

    /**
    * Fills the cart and changes label to display fill.
    * @param fillAmount Amount to fill cart changes depending on level of tower.
    **/
    public void fillCart(int fillAmount){
        currentResource += fillAmount;
        if(currentResource > totalResource) currentResource = totalResource;
        amount.setText(currentResource+"/"+totalResource);
    }

    /**
    * Returns carts x and y position.
    **/
    public Point2D getPosition(){
        return position;
    }

    /**
    * Returns carts resource type.
    **/
    public int getResourceType(){
        return resourceType;
    }
}
