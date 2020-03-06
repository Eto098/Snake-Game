import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements KeyListener, ActionListener{
    Timer timer = new Timer(65, this);
    Random random = new Random();
    private int headX = 230;
    private int headY = 230;
    private int score = 0;
    private int targetX = random.nextInt(450);
    private int targetY = random.nextInt(450);
    private final ArrayList<Snake> snakes = new ArrayList<>();
    private int headDirection = 0;// 1 for up, 2 for right, 3 for down, 4 for left
    private int time = 0;//play time (ms)
       
    class Snake{
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }       
    }

    public SnakeGame() {
        snakes.add(new Snake());     
        setBackground(Color.BLACK); 
        timer.start();     
    }
    
    public boolean isCollision(String s){
        switch (s) {
            case "target":
                if(new Rectangle(headX, headY, 20, 20).intersects(new Rectangle(targetX, targetY, 15, 15))){
                    score ++;
                    snakes.add(new Snake());
                    return true;
                }   break;
            case "tail":
                for(int i = 1; i< snakes.size();i++){
                    if(new Rectangle(snakes.get(i).getX(), snakes.get(i).getY(), 20, 20).intersects(new Rectangle(headX, headY, 20, 20))){
                        return true;
                    }
            }   break;
        }
        return false;
    }
    
    public void createTarget(){
        int tempx = random.nextInt(450);
        int tempy = random.nextInt(450);
        int intersection = 0; //to see if the new target intersects with any snake part or not 
        if(tempx != targetX && tempy != targetY){
            for(Snake s: snakes){
                 if(new Rectangle(s.getX(), s.getY(), 20, 20).intersects(new Rectangle(tempx, tempy, 20, 20))){
                     intersection += 1;
                 }
            }
            if(intersection != 0){
                createTarget();
            } else{
                targetX = tempx;
                targetY = tempy;
            }
        }
        else{
        createTarget();
        }
                }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.RED);
        g.fillOval(targetX, targetY, 20, 20);
        g.setColor(Color.YELLOW);
        snakes.stream().forEach((s) -> {
            g.fillOval(s.getX(), s.getY(), 20, 20);
        });
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int dir = e.getKeyCode();
        
        if(dir == KeyEvent.VK_UP){
            if(headDirection != 3){
                headDirection = 1;
            }
        }
        if(dir == KeyEvent.VK_RIGHT){
            if(headDirection != 4){
                headDirection = 2;
            }
        }
        if(dir == KeyEvent.VK_DOWN){
            if(headDirection != 1){
                headDirection = 3;
            }
        }
        if(dir == KeyEvent.VK_LEFT){
            if(headDirection != 2){
                headDirection = 4;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        time += 65;
        if(isCollision("target")){
            createTarget();
        }
        
        if(isCollision("tail")){
            timer.stop();
            String message = "GAME OVER\nScore: "+score+"\nGame Play Time: "+time/1000.0+" Seconds";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }

        
        snakes.get(0).setX(headX);
        snakes.get(0).setY(headY);
        
        for(int i = snakes.size()-1; i>=1; i--){
            snakes.get(i).setX(snakes.get(i-1).getX());
            snakes.get(i).setY(snakes.get(i-1).getY());
        }
        
        if(headDirection == 1){
            if(headY <= 0){
                headY = 500;
            }
            headY -= 22;
            snakes.get(0).setY(snakes.get(0).getY()-22);
        }
        if(headDirection == 2){
            if(headX >= 500){
                headX = -22;
            }
            headX += 22;
            snakes.get(0).setX(snakes.get(0).getX()+22);            
        }
        if(headDirection == 3){
            if(headY >= 500){
                headY = -22;
            }
            headY += 22;
            snakes.get(0).setY(snakes.get(0).getY()+22);
        }
        if(headDirection == 4){
            if(headX <= 0){
                headX = 500;
            }
            headX -= 22;
            snakes.get(0).setX(snakes.get(0).getX()-22);
        }
        repaint();
    }    
}
