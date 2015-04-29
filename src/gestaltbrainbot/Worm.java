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


    int bloodSugar = 1030;
    final int LOW_BLOOD_SUGAR_LEVEL = 1000;
    boolean touchPressed=false;

    
    Gestalt bloodSugarS;
    Gestalt touchS;
    
    MoveForwardM moveForwardM;
    TurnRightM turnRightM;

    ArrayList<Gestalt> myGestalts = new ArrayList<>();

    
    public Worm(double x, double y, double dir) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.v = 0;
        this.color = Color.RED;


        moveForwardM = new MoveForwardM("moveForwardM",this);
        bloodSugarS = new Gestalt("bloodSugarS",moveForwardM);
        
        turnRightM = new TurnRightM("turnRightM",this);
        touchS = new Gestalt("touchS",turnRightM);
        
        
        myGestalts.add(bloodSugarS);
        myGestalts.add(moveForwardM);  

        myGestalts.add(touchS);
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
    
    private void inheritConnections() {
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
    
    public void touch() {
    	touchPressed=true;
    }
            
    public void next() {
        
        for (Gestalt g : myGestalts) {
            g.propagateActivation();
        }
        
        
        if (bloodSugar < LOW_BLOOD_SUGAR_LEVEL) {
            bloodSugarS.open();
        } else {
            bloodSugarS.close();
        }
        	
        
        if (touchPressed) {
        	touchS.open();
        }
        
        System.out.println(this);
        printGestalts();
        

//        matchOpenGsToAllOtherGs();
//        inheritConnections();

        
        
        bloodSugar--;
        touchPressed=false;
        
    }

	@Override
    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillOval((int)(x-5), (int)(y-5), 10, 10);
        g.drawLine((int)x, (int)y, (int)((5 + v)*dirXY()[0]+x), (int)((5+v)*dirXY()[1]+y));
    }
    
	public void printGestalts() {
		  for (Gestalt g : myGestalts) {
	            System.out.print(g + ", ");
	        }
	        System.out.println(" ");
	}
	
    public String toString() {
    
        return String.format("(%.0f,%.0f) v:%.0f dir:%.1f bloodSugar: %d", x,y,v,dir,bloodSugar); //"(" + (int)x + ", " +  (int)y + ") " + v + " " + dir + " bloodsugar:" + bloodSugar;
        
    }
}


class TouchG extends Gestalt { 

	public TouchG() {
		super("touchG");
	}
	
	public TouchG(boolean open) {

		super("touchG",open);
		
	}
	
    public void endTurn() {
		this.open=false;
		
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
//
//abstract class Sensor extends Gestalt {
//    Gestalt toNotify;
//    public Sensor(String label, GestaltBox toNotify) {
//        super(label);
//        this.toControl =  toControl;
//    }
//    
//    
//}
//
//class TouchS extends Sensor {
//    
//	public void touch(){
//		addGestalt(new Gestalt("touch",true));
//	}
//	void next(){
//		
//	}
//	
//    public TurnRightM(String label, GestaltBox toNotify) {
//        super(label,toControl);
//    }
//    
//    void propagateActivation() {
//       
//        
//        if (this.isOpen()) {
//              toControl.dir = (toControl.dir + 1)%4 ;
//        }
//        super.propagateActivation();
//
//    }
//}


//class Entity extends GestaltBox {
//	String label;
//	
//	
//}
//

//class TouchS extends Entity {
//	
//	public TouchS()
//	 Gestalt lowBloodSugarG1 = new Gestalt("lowBloodSugarG");
//     Gestalt moveForwardG1 = new Gestalt("moveForwardG");
//     lowBloodSugarG1.addConnection(moveForwardG1);
//     
////     SensoryNeuron lowBloodSugarS = new SensoryNeuron("lowBloodSugarS");
////     addConnection(lowBloodSugarS, hungerG1);
//
//     MoveForwardM moveForwardM = new MoveForwardM("moveForwardM", this);
//     
//     moveForwardG1.addConnection(moveForwardM);
//     
//     
//     Gestalt touchG1 = new TouchG();
//     Gestalt turnRightG1 = new Gestalt("turnRightG");
//     touchG1.addConnection(turnRightG1);
//     
//     TurnRightM turnRightM = new TurnRightM("turnRightM", this);
//     turnRightG1.addConnection(turnRightM);
//     
//     myGestalts.add(lowBloodSugarG1);
//     myGestalts.add(moveForwardG1);
//     myGestalts.add(moveForwardM);      
//     
//	
//}


abstract class Motor extends Gestalt {
    Worm toControl;
    
    public Motor(String label, Worm toControl) {
        super(label);
        this.toControl =  toControl;
    }
    
    
}

class TurnRightM extends Motor {
    
	public TurnRightM(String label, Worm toControl) {
        super(label, toControl);
    }
    
    void propagateActivation() {

        if (this.isOpen()) {
              toControl.dir = (toControl.dir + 1)%360 ;
        }
        super.propagateActivation();
    }
}

class MoveForwardM extends Motor {
    
    public MoveForwardM(String label, Worm toControl) {
        super(label, toControl);
    }
    
    void propagateActivation() {
        
        if (this.isOpen()) {
            toControl.v = 3;
        }
        super.propagateActivation();
    }
}