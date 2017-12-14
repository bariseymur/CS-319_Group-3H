/**
 * Hero Type 1 Class
 * @ author Emre Gürçay
 * @ version 14.12.2017
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.io.IOException;


public class HeroType1 extends Hero{
    protected Attacker target;
    protected BufferedImage kapowImage;
    private boolean fight = false;
    private int waitTime = 0;

    public HeroType1(GameMap gameMap, int endRow, int endColumn){
        super(gameMap,endRow,endColumn);
        damage = 5;
     //   rateOfAttack = 300;
        target = null;

        try
        {
            image = ImageIO.read(new File(Assets.hero1));
            kapowImage = ImageIO.read(new File(Assets.kapow));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void attack() {

        if (target == null) {
            target = killList();
        }
        if (waitTime >= 250) {
            waitTime = 0;
            //System.out.println("attacker null");
            if (target != null) {
                if ((this.getY() - target.yPosTile * GameMap.tileEdge) > 40 || (this.getX() - target.xPosTile * GameMap.tileEdge) > 40)
                {
                    target = null;
                    System.out.println("attacker is null null");
                }
                else
                {
                    target.setHealth(target.getHealth() - damage);
                    fight = true;
                    System.out.println("attacking");
                    System.out.println(target.getHealth());
                    if (target.getHealth() <= 0) {
                        target = null;
                        System.out.println("target is dead");
                        fight = false;
                        moveAttackers();
                    }

                }
            }
        }
        else
        {
            waitTime++;
        }

    }
    public void draw( int xPosition, int yPosition, Graphics g, int width, int height) {
        //panel.paintComponent( g );
        g.drawImage( image, xPosition, yPosition, width, height, null, null);
    }
    @Override
    public void draw(Graphics g)
    {
       // System.out.println("Graphics :" + x);
        g.drawImage(image,x, y, 50, 50,null,null);
        if(fight)
        {
            //System.out.println("kapoww");
            g.drawImage(kapowImage,x- GameMap.tileEdge/2, y-GameMap.tileEdge/2, 75, 75,null,null);
        }
    }
}

