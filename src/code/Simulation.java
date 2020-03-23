package code;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Simulation extends JFrame{

    boolean isRunning = true;
    int fps = 100;
    int windowWidth = 500;
    int windowHeight = 500;

    BufferedImage backBuffer;
    Insets insets;

    int x = 250, y = 250;
    int xh = 250, yh = 250;

    public static void main(String [] args){

        Simulation sim = new Simulation();
        sim.run();
        System.exit(0);

    }

    public void run(){

        init();

        while(isRunning){

            long time = System.currentTimeMillis();

            upd();
            draw();

            //  delay for each frame  -   time it took for one frame
            time = (1000 / fps) - (System.currentTimeMillis() - time);

            if (time > 0){
                try{

                    Thread.sleep(time);

                }catch(Exception e){}
            }

        }

        setVisible(false);

    }

    void init(){

        setTitle("Brownian Motion");
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        insets = getInsets();
        setSize(insets.left + windowWidth + insets.right,
                insets.top + windowHeight + insets.bottom);

        backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);

        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, windowWidth, windowHeight);

        g.drawImage(backBuffer, insets.left, insets.top, this);

    }

    void upd(){

        double a = Math.random(), b = Math.random(), c = Math.random(), d = Math.random();

        xh = x;
        yh = y;

        if(b > a) x--;
        if(b < a) x++;

        if(d > c) y--;
        if(d < c) y++;

    }

    void draw(){

        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setPaintMode();

        bbg.setColor(Color.GREEN);
        bbg.drawOval(x, y, 4, 4);

        bbg.setColor(Color.DARK_GRAY);
        bbg.drawOval(xh, yh, 4, 4);

        bbg.setColor(Color.GRAY);
        bbg.drawOval(xh, yh, 3, 3);

        g.drawImage(backBuffer, insets.left, insets.top, this);

    }

}
