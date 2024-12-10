package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // pixel size 16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    //controlling screen size and also character size through tileSize
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //map parameters
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    int FPS = 60;

    // key input and gameThread initializing
    TileManager tileM = new TileManager(this);
    KeyHandler keyIn = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyIn);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyIn);
        this.setFocusable(true);
    }
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }


    //game loop "where the magic happens"
    @Override
    public void run() {
        double drawnInterval = (double) 1_000_000_000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawnInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }
    public void update(){
        player.update();

    }

    //graphic engine
    public void paintComponent(Graphics g){
        //built in method
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //extends graphics class more custom functions for 2d graphic
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

}