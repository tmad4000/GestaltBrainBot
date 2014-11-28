/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Jacob-MTech
 */
class UniversePanel extends JPanel {
    Universe u;

    //        public UniversePanel () {
    //            u = new Universe();
    //            setSize(300, 300);
    //            setBackground(Color.blue);
    //            add(new JButton("Uoe"));
    //        }
    public UniversePanel(Universe u) {
        this.setPreferredSize(new Dimension(300, 500));
//        setBackground(Color.green);
        this.u = u;
        //            this.setLayout(new BoxLayout(this, WIDTH));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        u.paintComponent(g);
    }

    public void next() {
        u.next();
        repaint();
    }
    
}
