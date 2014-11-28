/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

import java.util.LinkedHashSet;

/**
 *
 * @author Jacob-MTech
 */
public class GestaltBrainBot2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}


class GB { //notifiable
    HGraph<G> conn=new HGraph<G>();
    void addG(G g) {
        conn.add(g);
    }
}
class G {
    
}
class HGraph<T> extends LinkedHashSet<T> {
}


class Entity extends GB {
    Sensor[] s;
    Brain b;
    Motor[] m;
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

class W extends Entity { //wire
    W() {
        
    }
    
    w(GB s, )
    
}

class B extends Entity {
    
    
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