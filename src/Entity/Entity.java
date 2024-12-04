package Entity;
//super class for player, monster, npc, ect.

import java.awt.image.BufferedImage;

public abstract class Entity {

    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

}

