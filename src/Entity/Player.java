package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    GamePanel gp;
    KeyHandler keyIn;

    public Player(GamePanel gp, KeyHandler keyIN){
        this.gp = gp;
        this.keyIn = keyIN;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; // sets default pos of character to down

    }
    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/imageName.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyIn.upPressed){
            direction = "up";
            worldY -= speed;
        }
        else if (keyIn.downPressed){
            direction = "down";
            worldY += speed;
        }
        else if (keyIn.leftPressed){
            direction = "left";
            worldX -= speed;
        }
        else if (keyIn.rightPressed){
            direction = "right";
            worldX += speed;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "right" -> right1;
            case "left" -> left1;
            default -> null;
        };

        g2.drawImage(image, screenX , screenY, gp.tileSize, gp.tileSize, null);
    }

}
