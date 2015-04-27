/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaltbrainbot;

import java.awt.*;
import java.util.*;

/**
 *
 * @author Jacob-MTech
 */


public class Worm extends UniverseObject {

    ArrayList<Gestalt> myGestalts = new ArrayList<>();

    int bloodSugar = 33;
    final int LOW_BLOOD_SUGAR_LEVEL = 30;


    public Worm(int x, int y, int dir) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.v = 0;
        
        this.color = Color.RED;
    
        Gestalt lowBloodSugarG1 = new Gestalt("lowBloodSugarG");
        Gestalt moveForwardG1 = new Gestalt("moveForwardG");
        lowBloodSugarG1.addConnection(moveForwardG1);
        
//        SensoryNeuron lowBloodSugarS = new SensoryNeuron("lowBloodSugarS");
//        addConnection(lowBloodSugarS, hungerG1);

        MoveForwardM moveForwardM = new MoveForwardM("moveForwardM", this);
        
        moveForwardG1.addConnection(moveForwardM);
        
        
        Gestalt touchG1 = new Gestalt("touchG");
        Gestalt turnRightG1 = new Gestalt("turnRightG");
        touchG1.addConnection(turnRightG1);
        
        TurnRightM turnRightM = new TurnRightM("turnRightM", this);
        turnRightG1.addConnection(turnRightM);
        
        myGestalts.add(lowBloodSugarG1);
        myGestalts.add(moveForwardG1);
        myGestalts.add(moveForwardM);      
        
        myGestalts.add(touchG1);
        myGestalts.add(turnRightG1);
        myGestalts.add(turnRightM);
        
        
    }

    ArrayList<Gestalt> getOpenGs() {
        ArrayList<Gestalt> openGs = new ArrayList<>();
        for (Gestalt g : myGestalts) {
            if (g.isOpen()) {
                openGs.add(g);
            }
        }
        return openGs;
    }

    void matchOpenGsToAllOtherGs() {
        for (Gestalt og : getOpenGs()) {
            for (Gestalt g : myGestalts) {
                if (og.equals(g)) {
                    g.addBiConnection(og);
                }
            }
        }
    }

    public void addGestalt(Gestalt g){
        myGestalts.add(g);
    }
            
    public void next() {
        System.out.println(this);
        System.out.println(myGestalts.get(0));
        
        for (Gestalt g : myGestalts) {
            System.out.print(g + ", ");
        }
        System.out.println(" ");
        
        bloodSugar--;
        
        if (bloodSugar < LOW_BLOOD_SUGAR_LEVEL) {
            addGestalt(new Gestalt("lowBloodSugarG", true));
        }

        matchOpenGsToAllOtherGs();
        
        for (Gestalt g : myGestalts) {
            g.propagateActivation();
        }

    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillOval(x-5, y-5, 10, 10);
        g.drawLine(x, y, (5 + v)*dirXY()[0]+x, (5+v)*dirXY()[1]+y);
    }
    
    public String toString() {
    
        return "(" + x + ", " +  y + ") " + v + " bloodsugar:" + bloodSugar;
        
    }
}

class Gestalt {

    String label;
    ArrayList<Gestalt> connections = new ArrayList<>();
    boolean open;

    Gestalt(String label) {
        this.label = label;
    }
    
    Gestalt(String label, boolean open) {
        this.label = label;
        this.open  = open;
    }

    void open() {
        open = true;
    }
    void close() {
        open = false;
    }
    
    

    void addConnection(Gestalt n2) {
        connections.add(n2);
    }
    
    void addBiConnection(Gestalt n2) {
        connections.add(n2);
        n2.addConnection(this);
    }

    private void openConnected() {
        for (Gestalt n : connections) {
            n.open();
//            System.out.print(n);
//            System.out.println("opened");
        }
    }

    void propagateActivation() {

        if (this.isOpen()) {
            openConnected();
        }
    }

    boolean equals(Gestalt n2) {
        return this.label.equals(n2.label);
    }

    boolean isOpen() {
        return open;
    }
    
    public String toString() {
        return label + " " + (open?"open":"closed");
    }   
}
//
//class LowBloodSugarG extends Gestalt {
//
//    public LowBloodSugarG() {
//        super("lowBloodSugarG");
//    }
//}


//class SensoryNeuron extends Gestalt {
//
//    public SensoryNeuron(String label) {
//        super(label);
//    }
//}

abstract class Motor extends Gestalt{
    Worm toControl;
    public Motor(String label, Worm toControl) {
        super(label);
        this.toControl =  toControl;
    }
    
    
}

class TurnRightM extends Motor {
    
    public TurnRightM(String label, Worm toControl) {
        super(label,toControl);
    }
    
    void propagateActivation() {
       
        
        if (this.isOpen()) {
              toControl.dir = (toControl.dir + 1)%4 ;
        }
        super.propagateActivation();

    }
}

class MoveForwardM extends Motor {
    
    public MoveForwardM(String label, Worm toControl) {
        super(label,toControl);
    }
    
    void propagateActivation() {
        
        if (this.isOpen()) {
            toControl.v = 3;
        }
        super.propagateActivation();
    }
}
