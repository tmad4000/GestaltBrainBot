package gestaltbrainbot;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Jacob-MTech
 */
class GestaltPanel extends JPanel {
    Agent s;
    JTextArea ta;

    public GestaltPanel(GestaltBox s) {
        this.s = s;
        this.setPreferredSize(new Dimension(200, 300));
        setBackground(Color.PINK);
        setLayout(new BorderLayout());
        ta = new JTextArea("");
        //setPreferredSize(new Dimension(100,100));
        ta.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(ta);
        //            scrollPane.setPreferredSize(new Dimension(120,100));
        ta.setEditable(false);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void next() {
        ta.setText(s.toString());
    }
    
}
