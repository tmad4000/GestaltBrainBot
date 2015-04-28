package gestaltbrainbot;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gestaltbrainbot;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedHashSet;
//package gestaltbrainbot;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedHashSet;
//
///**
// *
// * @author Jacob-MTech
// */
//
//
//public class OldGestaltBrainBot extends UniverseObject {
//
//    int bloodSugar = 100;
//    final int LOW_BLOOD_SUGAR_LEVEL = 30;
//
//    S bloodSugarSensor = new S();
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//
//    @Override
//    public void next() {
//        bloodSugar--;
//        if (bloodSugar < LOW_BLOOD_SUGAR_LEVEL) {
//            bloodSugarSensor.addGestalt(new HungerGestalt());
//        }
//
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        g.setColor(this.color);
//        g.drawLine(x, y, x2, y2);
//    }
//
//}
//
//abstract class GestaltBox { //GestaltBox. aka notifiable, can receive signals, dendrite.
//
//    ArrayList<G> gs = new ArrayList<G>();
//
//    GestaltBox() {
//
//    }
//
//    GestaltBox(ArrayList<G> gestalts) {
//        this.gs = gestalts;
//    }
//
//    void addG(G g) {
//        gs.add(g);
//    }
//
//    void addG(G s, Relation l, G t) {
//        l.t = t;
//        s.addRel(l);
//        addG(s);
//        addG(t);
//    }
//
//    void addAllG(Collection<? extends G> i) {
//        gs.addAll(i);
//    }
////    void addKnowl(String s,String l,String  t) {
////        G sg=new G(s,3);
////        Relation ll=new Relation(l);
////        G tg=new G(t,3);
////        
////        addGToColl(knowl,sg,ll,tg);
////        if(knowl.contains(t)) //#todo
////        addGToColl(gs,sg,ll,tg);
////    }
////    
////    void takeAction() { //next
////        
////    }
//
//    public GestaltBox openGs() {
//        ArrayList<G> o = new ArrayList<G>();
//        for (G g : gs) {
//            if (g.isOpen()) {
//                o.add(g);
//            }
//        }
//        return new GestaltBox(o);
//    }
//}
//
//class G extends GestaltBox { //gestalt, i.e. neuron. Open = firing, closed = not firing. 
//
//    String msg;
//    ArrayList<Relation> relations = new ArrayList<Relation>();
////    LinkedHashSet<Relation> relations=new LinkedHashSet<Relation>();
//
//    //0: not ack; 1: ack; 2: in prog: 3:done ; 0-2 is open; 3 is closed
//    private int sts = 0;
//
//    G() {
//    }
//
//    G(String msg) {
//        this.msg = msg;
//    }
//
//    G(String msg, int sts) {
//        this.msg = msg;
//        this.sts = sts;
//    }
//
////    void addRel(Relation l) {
////        addG(l);
////    }
////    
////    void addAllRel(Collection<? extends Relation> ls) {
////        addAllG(ls);
////    }
//    void addRel(String r, G t) {
//        addRel(new Relation(r, t));
//    }
//
//    /**
//     * @return the sts
//     */
//    public int getSts() {
//        return sts;
//    }
//
//    /**
//     * @param sts the sts to set
//     */
//    public void setSts(int sts) {
//        this.sts = sts;
//    }
//
//    public void setOpen() {
//        this.sts = 0;
//    }
//
//    public void setClosed() {
//        this.sts = 3;
//    }
//
//    boolean isOpen() {
//        return getSts() < 3;
//    }
//
//}
//
//class Relation extends G {
//
//    double strength = 1;
//    GestaltBox t;
//
//    Relation() {
//        this(null, null);
//    }
//
//    Relation(String msg) {
//        this(msg, null);
//    }
//
//    Relation(String m, G t) {
//        this(m, 1., t);
//    }
//
//    Relation(String m, double s) {
//        this(m, s, null);
//    }
//
//    Relation(String m, double s, G t) {
//        super(m);
//        this.strength = s;
//        this.t = t;
//    }
//
//    boolean isOpen() {
//        return t.getSts() < 3;
//    }
//}
//
//class Entity extends GestaltBox {
//
//    S[] s;
//    B b;
//    M[] m;
//
//    GestaltBox toNotify;
//
//    Entity(GestaltBox b) {
//        this.toNotify = b;
//    }
//
//    void next() {
//
//    }
//}
//
//class S extends Entity {
//
//    S(GestaltBox b) {
//        super(b);
//    }
//
//    public void perceive(G p) {
//        toNotify.addG(p);
//    }
//}
//
//class Gestalt {
//
//    String label;
//    ArrayList<Gestalt> connections = new ArrayList<>();
//    boolean open;
//
//    Gestalt(String label) {
//        this.label = label;
//    }
//    
//    Gestalt(String label, boolean open) {
//        this.label = label;
//        this.open  = open;
//    }
//
//    void open() {
//        open = true;
//    }
//
//    void addConnection(Gestalt n2) {
//        connections.add(n2);
//    }
//    
//    void addBiConnection(Gestalt n2) {
//        connections.add(n2);
//        n2.addConnection(this);
//    }
//
//    private void openConnections() {
//        for (Gestalt n : connections) {
//            n.open();
//        }
//    }
//
//    void propagateActivation() {
//
//        if (this.isOpen()) {
//            openConnections();
//        }
//    }
//
//    boolean equals(Gestalt n2) {
//        return this.label.equals(n2.label);
//    }
//
//    boolean isOpen() {
//        return open;
//    }
//}
////
////class LowBloodSugarG extends Gestalt {
////
////    public LowBloodSugarG() {
////        super("lowBloodSugarG");
////    }
////}
//
//
////class SensoryNeuron extends Gestalt {
////
////    public SensoryNeuron(String label) {
////        super(label);
////    }
////}
//
//class MoveForwardM extends Gestalt {
//    Worm toControl;
//    public MoveForwardM(String label, Worm toControl) {
//        super(label);
//        this.toControl =  toControl;
//    }
//    
//    void propagateActivation() {
//        
//        if (this.isOpen()) {
//            toControl.v = 1;
//        }
//    }
//}
//
//class Worm extends UniverseObject {
//
//    ArrayList<Gestalt> myGestalts = new ArrayList<>();
//
//    int bloodSugar = 100;
//    final int LOW_BLOOD_SUGAR_LEVEL = 30;
//
//
//    public Worm(int x, int y, int dir) {
//
//        this.x = x;
//        this.y = y;
//        this.dir = dir;
//        this.v = 0;
//        
//        this.color = Color.RED;
//    
//        Gestalt hungerG1 = new Gestalt("hungerG");
//        Gestalt moveForwardG1 = new Gestalt("moveForwardG");
//        hungerG1.addConnection(moveForwardG1);
//        
////
////        SensoryNeuron lowBloodSugarS = new SensoryNeuron("lowBloodSugarS");
////        addConnection(lowBloodSugarS, hungerG1);
//
//        
//
//        MoveForwardM moveForwardM = new MoveForwardM("moveForwardM", this);
//        
//        moveForwardG1.addConnection(moveForwardM);
//        
//        myGestalts.add(hungerG1);
//        myGestalts.add(moveForwardG1);
//        myGestalts.add(moveForwardM);
//        
//    }
//
//    ArrayList<Gestalt> getOpenGs() {
//        ArrayList<Gestalt> openGs = new ArrayList<>();
//        for (Gestalt g : myGestalts) {
//            if (g.isOpen()) {
//                openGs.add(g);
//            }
//        }
//        return openGs;
//    }
//
//    void matchOpenGsToAllOtherGs() {
//        for (Gestalt og : getOpenGs()) {
//            for (Gestalt g : myGestalts) {
//                if (og.equals(g)) {
//                    g.addBiConnection(og);
//                }
//            }
//        }
//    }
//
//    public void next() {
//        bloodSugar--;
//        
//        if (bloodSugar < LOW_BLOOD_SUGAR_LEVEL) {
//            myGestalts.add(new Gestalt("lowBloodSugarG", true));
//        }
//
//        matchOpenGsToAllOtherGs();
//        
//        for (Gestalt g : myGestalts) {
//            g.propagateActivation();
//        }
//
//    }
//    
//    @Override
//    public void paintComponent(Graphics g) {
//        g.setColor(this.color);
//        g.drawLine(x, y,v*dirXY()[0]+x, v*dirXY()[1]+y);
//    }
//}
//
//class B extends Entity {
//
//    String[] rels = {"instanceOf", "respBy"};
//
//    void next() {
//        if isOpenG
//               {
//            callBoardMeetingFiguringOutHowToRespond()
//                      
//        }
//        autoRelateGs() //                        acknowledge all gestalts
//                //                                connect gestalts to others
//                //                                       findRelationships
//                /*doBestThing();
//                 doMostGracefulThing()
//                 feelBalanceOfTensionSystems()
//                 surveryTensionSystems()
//                 desireToWorkTowardsGoal=howMuchFunIsGoal
//                 valueOfNotChangingGoals*/
//        
//        surveyGestalts(); //ie interconnect them
//        GestaltBox ogs = openGs();
//        ogs.takeAction();
//    }
//
//    private void surveyGestalts() {
//        propTransRels(); //swapped order to break up steps
//        autoRelateGs();
//    }
//
//    private void autoRelateGs() {
//        for (G g1 : gs) {
//            g1.addAllRel(autoFindRelGs(g1));
//        }
//    }
//
//    private ArrayList<Relation> autoFindRelGs(G g1) {
//        ArrayList<Relation> o = new ArrayList<Relation>();
//        for (G g2 : gs) {
//            Relation rel = autoCalcHowRelated(g1, g2);
//            if (rel != null) {
//                o.add(rel);
//            }
//        }
//
//        return o;
//    }
//
//    public static Relation autoCalcHowRelated(G g1, G g2) {
//        if (g1.msg.equals(g2.msg)) {
//            Relation rel;
//            rel = new Relation("instanceOf", 1.);
//            return rel;
//        } else {
//            return null;
//        }
//    }
//
//    private void propTransRels() {
//        for (G g : gs) {
//            for (Relation r : g.relations) {
//                if (r.msg.equals("instanceOf")) {
//                    g.addAllRel(r.t.relations);
//                } else if (r.msg.equals("respBy")) {
//                    if (g.isOpen()) {
//                        r.t.setSts(g.getSts());
//
//                    }
//                }
//            }
//        }
//    }
//
//    B(GestaltBox motor) {
//        super(motor);
//    }
//}
//
//class M extends Entity {
//
//    M(GestaltBox universe) {
//        super(universe);
//    }
//
//    private void takeAction() {
//        toNotify.addG(null);
//    }
//}
//
//// --------------------------------------------------------------
//class OldWorm extends Entity {
//
//    OldWorm(GestaltBox universe) {
//        super(universe);
//        m = new M[1];
//
//        s = new S[1];
//
//        m[0] = new TurnM(universe);
//        b = new WormB(m[0]);
//        s[0] = new TouchS(b);
//    }
//
//    @Override
//    void addG(G g) {
//        throw new RuntimeException("Worm is just a container, not a real object. Must add to worm's approp. s");
//    }
//}
//
//class TouchS extends S {
//
//    TouchS(GestaltBox b) {
//        super(b);
//    }
//
//    void touch() {
//        perceive(new G());
//    }
//}
//
//class PMCGestaltBox extends GestaltBox {
//
//}
//
//class TurnG extends G {
//
//}
//
//class TouchG extends G {
//
//    public TouchG() {
//        super("touch");
//    }
//}
//
//class HungerG extends G {
//
//    public HungerG() {
//        super("hunger");
//    }
//}
//
//
//class MoveForwardG extends G {
//
//    public MoveForwardG() {
//        super("moveForward");
//    }
//}
//
//class TurnSubG extends TurnG {
//
//}
//
//class TurnLG extends TurnG {
//
//}
//
//class TurnRG extends TurnG {
//
//}
//
//class WormB extends B {
//
//    void addInstinct(String s, String r, String t) {
//        addKnowl(s, r, l);
//    }
//
//    void addKnowl(String s, String r, String t) {
//        addRel(s, r, t);
//    }
////        G sg=new G(s,3);
////        Relation ll=new Relation(l);
////        G tg=new G(t,3);
////        
////        addGToColl(knowl,sg,ll,tg);
////        if(knowl.contains(t)) //#todo
////        addGToColl(gs,sg,ll,tg);
////    }
////    
//
//    WormB(GestaltBox m) {
//        super(m);
//
//        //hunger -> moveFoward
//        addInstinct("hunger", "respBy", "moveFowardG");
////        addInstinct(HungerG, "respBy", MoveForwardG);
//
////        LinkedHashSet<G> knowl=new LinkedHashSet<G>();
//        LinkedHashSet<G> knowl = new LinkedHashSet<G>();
//
//        GestaltBox pmc = new PMCGestaltBox();
//        G turnSub = new TurnSubG();
//        G touch = new G("touch", 3);
//
//        touch.addRel("respBy", turnSub);
//
//        turnSub.addRel("respBy", toNotify);
//
//        addKnowl("touch", "respBy", "turnSub");
//
//        addKnowl("touch", "respBy", "turnSub");
//        addKnowl("turnSub", "respBy",);
//    }
//}
//
//class TurnM extends M {
//
//    TurnM(GestaltBox u) {
//        super(u);
//    }
//
//    private void takeAction() {
//        toNotify.addG(new G("turnL"));
//    }
//}
