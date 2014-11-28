/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

/**
 *
 * @author Jacob-MTech
 */

    
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Jacob-MTech
 */
public class TMazeWorldFrame extends JFrame {

    MainPanel mP;
    
    public TMazeWorldFrame() throws InterruptedException {
        super("TMazeWorld");
        setSize(600, 500);
        mP = new MainPanel();
        add(mP);
        
        //new JButton("restart")
        //add()
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);

        while (true) {
            Thread.sleep(30);
            mP.next();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        TMazeWorldFrame s = new TMazeWorldFrame();

        // TODO code application logic here
    }
}
