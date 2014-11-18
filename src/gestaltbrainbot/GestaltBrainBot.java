x/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 * @author Jacob-MTech
 */
public class GestaltBrainBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}


class GB { //notifiable
    ArrayList<G> gs=new ArrayList<G>();
    
    GB() {
        
    }
    
    GB(ArrayList<G> gestalts) {
        this.gs = gestalts;
    }
    
    
    void addG(G g) {
        gs.add(g);
    }    
    
    void addG(G s,Link l,G t) {
        l.t=t;
        s.addL(l);
        addG(s);
        addG(t);
    }
    
    void addAllG(Collection<? extends G> i) {
        gs.addAll(i);
    }
//    void addKnowl(String s,String l,String  t) {
//        G sg=new G(s,3);
//        Link ll=new Link(l);
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
    
    public GB openGs() {
        ArrayList<G> o = new ArrayList<G>();
        for (G g:gs) {
            if (g.isOpen())
                o.add(g);
        }
        return new GB(o);
    }
}

class G extends GB {
    String msg;
    ArrayList<Link> gs=new ArrayList<Link>();
    ArrayList<Link> links=gs;
//    LinkedHashSet<Link> links=new LinkedHashSet<Link>();
    private int sts=0; //0: not ack; 1: ack; 2: in prog: 3:done
    
    G() {
    }
    
    G(String msg) {
        this.msg=msg;
    }

    G(String msg, int sts) {
        this.msg=msg;
        this.sts=sts;
    }
    
    void addL(Link l) {
        addG(l);
    }
    
    void addAllL(Collection<? extends Link> ls) {
        addAllG(ls);
    }
    
    void addL(String r, G t) {
        addL(new Link(r,t));
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

    boolean isOpen() {
        return getSts()<3;
    }
   
}

class Link extends G {
    double strength=1;
    GB t; 
    
    Link() {
        this(null,null);
    }
    
    Link(String msg) {
        this(msg,null);
    }
    
    Link(String m,G t) {
        this(m,1.,t);
    }
    
    Link(String m,double s) {
        this(m,s,null);
    }
    
    Link(String m,double s,G t) {
        super(m);
        this.strength=s;
        this.t=t;
    }  
    
     boolean isOpen() {
        return t.getSts()<3;
    }
}

class Entity extends GB {
    S[] s;
    B b;
    M[] m;
    
    GB toNotify;
    
    Entity(GB b) {
        this.toNotify=b;
    }
    
    void next() {
        
    }
}

class S extends Entity {    
    S(GB b) {
        super(b);
    }
    
    public void perceive(G p) {
        toNotify.addG(p);
    }
}

class B extends Entity {
    String[] rels={"instanceOf","respBy"};
    
    
    void next() {
        /*doBestThing();
                doMostGracefulThing()
                        feelBalanceOfTensionSystems()
                                surveryTensionSystems()
                                        desireToWorkTowardsGoal=howMuchFunIsGoal
                                        valueOfNotChangingGoals*/
        
        surveyGestalts(); //ie interconnect them
        GB ogs=openGs();
        ogs.takeAction();
    }
    
    private void surveyGestalts() {
        propTransRels(); //swapped order to break up steps
        autoRelateGs();
    }
    
    private void autoRelateGs() {
        for(G g1: gs) {
            g1.addAllL(autoFindRelGs(g1));
        }
    }    
    
    private ArrayList<Link> autoFindRelGs(G g1) {
        ArrayList<Link> o=new ArrayList<Link>();
        for(G g2: gs) {
            Link rel=autoCalcHowRelated(g1,g2);
            if(rel!=null)
                o.add(rel);
        }
        
        return o;
    }
    
    public static Link autoCalcHowRelated(G g1, G g2) {
        if(g1.msg.equals(g2.msg)) {
            Link rel;
            rel = new Link("instanceOf",1.);
            return rel;
        }
        else
            return null;
    }
    
    private void propTransRels() {
        for(G g: gs) {
            for(Link l: g.links) {
                if(l.msg.equals("instanceOf")) {
                    g.addAllL(l.t.links);
                }
                else if(l.msg.equals("respBy")) {
                    if(g.getSts()==0) {
                        l.t.setSts(0);
                        
                    }
                }
            }
        }
    }
    
    B(GB motor) {
        super(motor);
    }
}

class M extends Entity {
    
    M(GB universe) {
        super(universe);
    }
    
    private void takeAction() {
        toNotify.addG(null);
    }
}


// --------------------------------------------------------------

class Worm extends Entity {
    Worm(GB universe) {
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
    TouchS(GB b) {
        super(b);
    }
    
    void touch() {
        perceive(new G());
    }
}

class PMCGB extends GB {
    
}
class TurnG extends G {
    
}

class TurnSubG extends TurnG {
    
}

class TurnLG extends TurnG {
    
}

class TurnRG extends TurnG {
    
}

class WormB extends B {
    WormB(GB m) {
        super(m);
        
//        LinkedHashSet<G> knowl=new LinkedHashSet<G>();
        
        LinkedHashSet<G> knowl=new LinkedHashSet<G>();
        
        GB pmc=new PMCGB();
        G turnSub=new TurnSubG();
        G touch=new G("touch",3);
        touch.addL("respBy", turnSub);
        
        turnSub.addL("respBy", toNotify);
        
        addKnowl("touch", "respBy", "turnSub");
        
        addKnowl("touch", "respBy", "turnSub");
        addKnowl("turnSub", "respBy", );            
    }
}

class TurnM extends M {
    TurnM(GB u) {
        super(u);
    }
    
    private void takeAction() {
        toNotify.addG(new G("turnL"));
    }
} 
