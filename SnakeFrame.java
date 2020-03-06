
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class SnakeFrame extends JFrame{

    public SnakeFrame(String title) throws HeadlessException {
        super(title);
        this.setResizable(false);
        this.setFocusable(false);
        this.setBounds(400, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SnakeGame game = new SnakeGame();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        this.add(game);
        this.setVisible(true);
    }
    
    //public static void main(String[] args) {
        //SnakeFrame frame = new SnakeFrame("Snake Game");
        /*frame.setResizable(false);
        frame.setFocusable(false);
        frame.setBounds(400, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SnakeGame game = new SnakeGame();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        frame.add(game);
        frame.setVisible(true);*/
    //}
}
