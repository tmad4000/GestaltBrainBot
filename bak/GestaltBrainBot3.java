/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    HGraph<G> conn=new HGraph<G>();
    
    void addG(G g) {
        gs.add(g);
    }
}
class G {
    String msg;
    G() {
    }
    
    G(String msg) {
        this.msg=msg;
    }
}
class HGraph<T> extends LinkedHashMap<T,RelTuple<T>> {
    void putC(T s, RelTuple[] ts) {
        for(RelTuple<T> r:ts) {
            putC(s,r);
        }
    }
    
    void putC(T s, RelTuple t) {
        put(s,t);
    }
    
    void putC(T s,String r, T t) {
        put(s,new RelTuple<T>(r,t));
    }
    
    class Link<T> {
        
    }
}

class Tuple<R,T> {
    R rel;
    T obj;
    

    public Tuple(R r,T t) {
        this.rel=r;
        this.obj=t;
    }
    
}

//class Relation extends G {
//    G s;
//    G t;
//    public Relation() {
//        
//    }
//    
//    public Relation(G s,G t) {
//        
//    }
//}

class Relation extends Tuple<String, G> {
    double strength=1;

    public Relation(String r,G t) {
        this(r,1,t);
        this.strength=1;
    }

    public Relation(Tuple<Double,String> r,G t) {
        this(r.obj,r.rel,t);    
    }
    
    public Relation(String r,double s,G t) {
        super(r,t);
        this.strength=s;
    }
}

class Relation {
    double strength=1;
    double msg;
    G targ;
    
    public Relation(String m,G t) {
        this(m,1,t);
        this.strength=1;
    }

    public Relation(Tuple<Double,String> r,G t) {
        this(r.obj,r.rel,t);    
    }
    
    public Relation(String m,double s,G t) {
        this.msg=m;
        this.strength=s;
        this.targ=t;
    }
}



class Entity extends GB {
    Sensor[] s;
    Brain b;
    Motor[] m;
    
    void next() {
        
    }
}

class S extends Entity {
    GB toNotify;
    
    S(GB b) {
        this.toNotify=b;
    }
    
    void perceive(G p) {
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
    }
    
    void surveyGestalts() {
        propTransRels(); //swapped order to break up steps
        autoRelateGs();
    }
    
    void autoRelateGs() {
        for(G g1: gs) {
            conn.putC(g1,autoFindRelGs(g1));
        }
    }    
    
    RelTuple<G>[] autoFindRelGs(G g1) {
        ArrayList<RelTuple<G>> o=new ArrayList<RelTuple<G>>();
                
        for(G g1: gs) {
            for(G g2: gs) {
                Tuple<Double,String> rel=autoCalcHowRelated(g1,g2);
                if(rel!=null)
                {
                    o.add(new RelTuple<G>());
                }
            }
        }
    }
    
    static Tuple<Double,String> autoCalcHowRelated(G g1, G g2) {
        
        
        if(g1.msg.equals(g2.msg)) {
            Tuple<Double,String> o;
            o = new Tuple<Double,String>(1.,"instanceOf");
            return o;
        }
        else
            return null;
        
        
    }
    
    void propTransRels() {
        
    }
    
    
    GB toNotify;
    
    B(GB b) {
        this.toNotify=b;
    }
    
}
class M extends Entity {
    
}

class Worm extends Entity {
    Worm() {
        
    }
    Sensor[] s;
    Brain b;
    Motor[] m;
}

class TouchS extends S {
    (GB b) {
        this.toNotify=b;
    }
    
    void perceive(G p) {
        toNotify.addG(p);
    }
    
    void touch() {
        perceive(new G());
    }
}