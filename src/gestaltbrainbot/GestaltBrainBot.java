x/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;package gestaltbrainbot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 * @author Jacob-MTech
 */
public class GestaltBrainBot extends UniverseObject {
    int bloodSugar=100;
    final int LOW_BLOOD_SUGAR_LEVEL=30;
    
    S bloodSugarSensor=new S();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public void next() {
        bloodSugar--;
        if(bloodSugar<LOW_BLOOD_SUGAR_LEVEL) {
            bloodSugarSensor.addGestalt(new HungerGestalt());
        }
            
    }

    @Override
    public void paintComponent(Graphics g) {
         g.setColor(this.color);
         g.drawLine(x, y, x2, y2);
    }

}


abstract class GestaltBox { //GestaltBox. aka notifiable, can receive signals, dendrite.
    ArrayList<G> gs=new ArrayList<G>();
    
    GestaltBox() {
        
    }
    
    GestaltBox(ArrayList<G> gestalts) {
        this.gs = gestalts;
    }
    
    
    void addG(G g) {
        gs.add(g);
    }    
    
    void addG(G s,Relation l,G t) {
        l.t=t;
        s.addRel(l);
        addG(s);
        addG(t);
    }
    
    void addAllG(Collection<? extends G> i) {
        gs.addAll(i);
    }
//    void addKnowl(String s,String l,String  t) {
//        G sg=new G(s,3);
//        Relation ll=new Relation(l);
//        G tg=new G(t,3);
//        
//        addGToColl(knowl,sg,ll,tg);
//        if(knowl.contains(t)) //#todo
//        addGToColl(gs,sg,ll,tg);
//    }
//    
//    void takeAction() { //next
//        
//    }
    
    public GestaltBox openGs() {
        ArrayList<G> o = new ArrayList<G>();
        for (G g:gs) {
            if (g.isOpen())
                o.add(g);
        }
        return new GestaltBox(o);
    }
}

class G extends GestaltBox { //gestalt, i.e. neuron. Open = firing, closed = not firing. 
    String msg;
    ArrayList<Relation> relations=new ArrayList<Relation>();
//    LinkedHashSet<Relation> relations=new LinkedHashSet<Relation>();
    
    //0: not ack; 1: ack; 2: in prog: 3:done ; 0-2 is open; 3 is closed
    private int sts=0; 
    
    G() {
    }
    
    G(String msg) {
        this.msg=msg;
    }

    G(String msg, int sts) {
        this.msg=msg;
        this.sts=sts;
    }
    
//    void addRel(Relation l) {
//        addG(l);
//    }
//    
//    void addAllRel(Collection<? extends Relation> ls) {
//        addAllG(ls);
//    }
    
    void addRel(String r, G t) {
        addRel(new Relation(r,t));
    }
   

    /**
     * @return the sts
     */
    public int getSts() {
        return sts;
    }

    /**
     * @param sts the sts to set
     */
    public void setSts(int sts) {
        this.sts = sts;
    }

    public void setOpen() {
        this.sts = 0;
    }

    public void setClosed() {
        this.sts = 3;
    }

        
    boolean isOpen() {
        return getSts()<3;
    }
   
}

class Relation extends G {
    double strength=1;
    GestaltBox t; 
    
    Relation() {
        this(null,null);
    }
    
    Relation(String msg) {
        this(msg,null);
    }
    
    Relation(String m,G t) {
        this(m,1.,t);
    }
    
    Relation(String m,double s) {
        this(m,s,null);
    }
    
    Relation(String m,double s,G t) {
        super(m);
        this.strength=s;
        this.t=t;
    }  
    
     boolean isOpen() {
        return t.getSts()<3;
    }
}

class Entity extends GestaltBox {
    S[] s;
    B b;
    M[] m;
    
    GestaltBox toNotify;
    
    Entity(GestaltBox b) {
        this.toNotify=b;
    }
    
    void next() {
        
    }
}

class S extends Entity {    
    S(GestaltBox b) {
        super(b);
    }
    
    public void perceive(G p) {
        toNotify.addG(p);
    }
}

 class Neuron {   
    String label;
    ArrayList<Neuron> connections = new ArrayList<>();
    boolean firing;
    
    Neuron(String label){
        this.label = label;
    }
    
    
    void fire (){
        firing = true;
    }

    void addConnection(Neuron n2){
        connections.add(n2);
    }

    private void fireConnections(){
        for (Neuron n:connections){
            n.fire();
        }
    }
    
    void propogateActivation(){
        
        if(this.isFiring()) {
            fireConnections();
        }
    }
    
    boolean equals(Neuron n2){
        return this.label.equals(n2.label);
    }
    
    boolean isFiring(){
        return firing;
    }
 }

class SensoryNeuron extends Neuron {
    public SensoryNeuron(String label){
        super(label);
    }
}
class MotorNeuron extends Neuron {
    public MotorNeuron(String label){
        super(label);
    }
}


class WormBrain {
    ArrayList<Neuron> wormNeurons = new ArrayList<>();
    
    void addConnection(Neuron s, Neuron t){
        s.addConnection(t);
    }   
    
    void addBiConnection(Neuron s, Neuron t){
        s.addConnection(t);
        t.addConnection(s);
    }
    
    public WormBrain(){
        Neuron hungerG1 = new Neuron("hungerG");
        Neuron moveForwardG1 = new Neuron("moveForwardG");
        
        SensoryNeuron lowBloodSugarS = new SensoryNeuron("lowBloodSugarS");
        addConnection(lowBloodSugarS, hungerG1);
        
        addConnection(hungerG1, moveForwardG1);
 
        MotorNeuron moveForwardM = new MotorNeuron("moveForwardM");
        addConnection(moveForwardG1, moveForwardM);
        
    }
    
            
    ArrayList<Neuron> getOpenGs() {
        ArrayList<Neuron> openGs = new ArrayList<>();
        for (Neuron g: wormNeurons) {
            if (g.isFiring())
                openGs.add(g);
        }
        return openGs;
    }
    
    void matchOpenGsToAllOtherGs(){
        for (Neuron og: getOpenGs()) {
            for (Neuron g: wormNeurons) {
              if (og.equals(g))
                addBiConnection(g,og);
            }
        }
    }
    
    public void next() {
        wormNeurons.add(new SensoryNeuron("lowBloodSugarS"))

        matchOpenGsToAllOtherGs();
        for (Neuron g: wormNeurons) {
            g.propogateActivation();
        }

    }
}
class B extends Entity {
    String[] rels={"instanceOf","respBy"};
    
    
    void next() {
        if isOpenG
              callBoardMeetingFiguringOutHowToRespond()
                      autoRelateGs()
//                        acknowledge all gestalts
//                                connect gestalts to others
//                                       findRelationships
                                      
                      
        /*doBestThing();
                doMostGracefulThing()
                        feelBalanceOfTensionSystems()
                                surveryTensionSystems()
                                        desireToWorkTowardsGoal=howMuchFunIsGoal
                                        valueOfNotChangingGoals*/
        
        surveyGestalts(); //ie interconnect them
        GestaltBox ogs=openGs();
        ogs.takeAction();
    }
    
    private void surveyGestalts() {
        propTransRels(); //swapped order to break up steps
        autoRelateGs();
    }
    
    private void autoRelateGs() {
        for(G g1: gs) {
            g1.addAllRel(autoFindRelGs(g1));
        }
    }    
    
    private ArrayList<Relation> autoFindRelGs(G g1) {
        ArrayList<Relation> o=new ArrayList<Relation>();
        for(G g2: gs) {
            Relation rel=autoCalcHowRelated(g1,g2);
            if(rel!=null)
                o.add(rel);
        }
        
        return o;
    }
    
    public static Relation autoCalcHowRelated(G g1, G g2) {
        if(g1.msg.equals(g2.msg)) {
            Relation rel;
            rel = new Relation("instanceOf",1.);
            return rel;
        }
        else
            return null;
    }
    

    private void propTransRels() {
        for(G g: gs) {
            for(Relation r: g.relations) {
                if(r.msg.equals("instanceOf")) {
                    g.addAllRel(r.t.relations);
                }
                else if(r.msg.equals("respBy")) {
                    if(g.isOpen()) {
                        r.t.setSts(g.getSts());
                        
                    }
                }
            }
        }
    }
    
    B(GestaltBox motor) {
        super(motor);
    }
}

class M extends Entity {
    
    M(GestaltBox universe) {
        super(universe);
    }
    
    private void takeAction() {
        toNotify.addG(null);
    }
}


// --------------------------------------------------------------

class Worm extends Entity {
    Worm(GestaltBox universe) {
        super(universe);
        m=new M[1];
        
        s=new S[1];
        
        m[0]=new TurnM(universe);
        b=new WormB(m[0]);
        s[0]=new TouchS(b);
    }
    
    @Override
    void addG(G g) {
        throw new RuntimeException("Worm is just a container, not a real object. Must add to worm's approp. s");
    }
}

class TouchS extends S {
    TouchS(GestaltBox b) {
        super(b);
    }
    
    void touch() {
        perceive(new G());
    }
}

class PMCGestaltBox extends GestaltBox {
    
}
class TurnG extends G {
    
}

class TouchG extends G {
    public TouchG() {
        super("touch");
    }
}

class HungerG extends G {
    public HungerG() {
        super("hunger");
    }
}

class MoveForwardG extends G {
    public MoveForwardG() {
        super("moveForward");
    }
}

class TurnSubG extends TurnG {
    
}

class TurnLG extends TurnG {
    
}

class TurnRG extends TurnG {
    
}

class WormB extends B {
    
    void addInstinct(String s,String r,String  t) {
        addKnowl(s,r,l);
    }
    
    void addKnowl(String s,String r,String  t) {
        addRel(s,r,t);
    }
//        G sg=new G(s,3);
//        Relation ll=new Relation(l);
//        G tg=new G(t,3);
//        
//        addGToColl(knowl,sg,ll,tg);
//        if(knowl.contains(t)) //#todo
//        addGToColl(gs,sg,ll,tg);
//    }
//    
    WormB(GestaltBox m) {
        super(m);
        
        //hunger -> moveFoward
        addInstinct("hunger", "respBy", "moveFowardG");
//        addInstinct(HungerG, "respBy", MoveForwardG);
        
        
        
        
//        LinkedHashSet<G> knowl=new LinkedHashSet<G>();
        
        LinkedHashSet<G> knowl=new LinkedHashSet<G>();
        
        GestaltBox pmc=new PMCGestaltBox();
        G turnSub=new TurnSubG();
        G touch=new G("touch",3);
        
        touch.addRel("respBy", turnSub);
        
        turnSub.addRel("respBy", toNotify);
        
        addKnowl("touch", "respBy", "turnSub");
        
        addKnowl("touch", "respBy", "turnSub");
        addKnowl("turnSub", "respBy", );            
    }
}

class TurnM extends M {
    TurnM(GestaltBox u) {
        super(u);
    }
    
    private void takeAction() {
        toNotify.addG(new G("turnL"));
    }
} 
