/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Jacob-MTech
 */
class MainPanel extends JPanel {
    Universe u;
//    GestaltPanel gP;
    UniversePanel sP;

    public MainPanel() {
        //setBackground(Color.green);
        //this.setPreferredSize(new Dimension(500, 500));
        u = new Universe();
        //
        sP = new UniversePanel(u);
        //add(sP);
        ////            jp.add(new JButton("aoeu"));
        //
        ////            add(jp)
        //
//        gP = GestaltPanel(u.s.sm);
        //add(gP);
        //
        setLayout(new BorderLayout());
        //
        add(sP, BorderLayout.CENTER);
//        add(gP, BorderLayout.EAST);
    }

    //        public void paint(Graphics g) {
    //            g.drawLine(100, 111, 200, 222);
    //        }
    public void next() {
        sP.next();
//        gP.next();
    }
    
}



/*St Catz
        sensor
            members (p1, p2)
        brain
            GestaltBox
            JCR
        motor
            workers


St Catz
        sensor
            members (p1, p2)
        brain
            GestaltBox
                s: keyboards/mice
                b: logic
                m: screens
            JCR
                s: reps eyes
                b: reps collective brains
                m: orders to workers
        motor
            workers


Snake
        sensor
            bumper
        brain
            GestaltBox
            JCR
        motor
            Do Nothing
            Forward, Back
            Turn
                (turnL, turnR)

St Catz
        sensor
        brain
            GestaltBox
            JCR
        motor*/