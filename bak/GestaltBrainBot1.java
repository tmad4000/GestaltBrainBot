/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestaltbrainbot;

/**
 *
 * @author Jacob-MTech
 */
public class GestaltBrainBot1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

class GB {
    
}
class Entity {
    Sensor[] s;
    Brain b;
    Motor[] m;
}

class Sensor {
    Brain bToNotify;
}
class Brain {
    
}
class Motor {
    
}

class Worm extends Entity {
    Worm() {
        
    }
    Sensor[] s;
    Brain b;
    Motor[] m;
}