import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameFild extends JPanel implements ActionListener {
    private final int SIZE = 380;
    private final int DOT_SIZE = 20;
    private final int ALL_DOTS = 400;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];

    private Image apple;
    private Image point;
    private int appleX;
    private int appleY;
    private int dots;
    private Timer timer;
    private int timerSec = 250;

    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private boolean right = true;
    private boolean inGame = true;


    public GameFild() {
        setBackground(Color.BLACK);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener() );
        setFocusable(true);
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 60 - i*DOT_SIZE;
            y[i] = 60;
        }
        timer = new Timer(timerSec, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
        System.out.println("Apple "+appleX+" "+appleY);
    }

    public void loadImages() {
        ImageIcon iia = new ImageIcon("aple.jpg");
        apple = iia.getImage();

        ImageIcon iip = new ImageIcon("point.jpg");
        point = iip.getImage();
    }

    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(point, x[i], y[i], this);
            }
        }else {
            String endStr = "Game Over";
            String score = "You Score = "+ (dots- 3);
            Font f = new Font(endStr, Font.BOLD, 20);
            g.setColor(Color.ORANGE);
            g.setFont(f);
            setBackground(Color.WHITE);
            g.drawString(endStr,145 ,SIZE/2 );

            g.drawString(score, 135, SIZE/2+40);
        }
    }
    public void checkApple(){
        if (x[0]==appleX && y[0] == appleY){
            dots++;
            createApple();

        }
    }

    public void cheakCollision(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }

        }
        if (x[0] >SIZE  || y[0]>SIZE || x[0]<0 || y[0]<0 ){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            cheakCollision();
            move();
        }
        repaint();
    }
    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_A | key == KeyEvent.VK_LEFT) && !right){
                left = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_D | key == KeyEvent.VK_RIGHT) && !left){
                right = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_W | key == KeyEvent.VK_UP) && !down){
                up = true;
                left = false;
                right = false;
            }
            if  ((key == KeyEvent.VK_S | key == KeyEvent.VK_DOWN) && !up){
                down = true;
                left = false;
                right = false;
            }

        }
    }
}
