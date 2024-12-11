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

    BufferedImage up1, up2, up3;
    BufferedImage down1, down2, down3;
    BufferedImage right1, right2, right3;
    BufferedImage left1, left2, left3;

    int spriteCounter = 0;
    int spriteNum = 1;

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
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down3.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        boolean moving = false; 

        if (keyIn.upPressed){
            direction = "up";
            worldY -= speed;
            moving = true;
        }
        else if (keyIn.downPressed){
            direction = "down";
            worldY += speed;
            moving = true;
        }
        else if (keyIn.leftPressed){
            direction = "left";
            worldX -= speed;
            moving = true;
        }
        else if (keyIn.rightPressed){
            direction = "right";
            worldX += speed;
            moving = true;
        }

        if(moving){
            spriteCounter++;
            if(spriteCounter > 10) {
                spriteNum = (spriteNum % 3 ) + 1;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
        }

    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : (spriteNum == 2) ? up2 : up3;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : (spriteNum == 2) ? down2 : down3;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : (spriteNum == 2) ? left2 : left3;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : (spriteNum == 2) ? right2 : right3;
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
