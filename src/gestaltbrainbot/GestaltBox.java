package gestaltbrainbot;

import java.util.*;


//class GestaltBox {
//    ArrayList<Gestalt> myGestalts = new ArrayList<>();
//
//    public void addGestalt(Gestalt g){
//        myGestalts.add(g);
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
//}



class Gestalt {

    String label;
    ArrayList<Gestalt> connections = new ArrayList<>();
    boolean open;

    
    Gestalt(String label, Gestalt... connections) {
    	this(label);
    	this.connections=new ArrayList<Gestalt>(Arrays.asList(connections));
    }

    Gestalt(String label) {
        this(label,false);
    }

	Gestalt(String label, boolean open) {
        this.label = label;
        this.open  = open;
    }
    
    public void endTurn() {
		// TODO Auto-generated method stub
		
	}
    
    void open() {
        open = true;// TODO Auto-generated method stub
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
        
        this.close();
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
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package gestaltbrainbot;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
///**
// *
// * @author Jacob-MTech
// */
//class GestaltBox {
//    ArrayList<Gestalt> gestalts = new ArrayList<Gestalt>();
//
////    HyperGraph<String> conn=new HyperGraph<String>();
//     
//    public GestaltBox() {
//    }
//
//    public GestaltBox(ArrayList<Gestalt> gestalts) {
//        this.gestalts = gestalts;
//    }
//
//    public void addGestalt(Gestalt g) {
//        gestalts.add(g);
//    }
//
//    public GestaltBox openGs() {
//        ArrayList<Gestalt> o = new ArrayList<Gestalt>();
//        for (int i = 0; i < gestalts.size(); i++) {
//            Gestalt g = gestalts.get(i);
//            if (g.sts < 4) {
//                o.add(g);
//            }
//        }
//        return new GestaltBox(o);
//    }
//
//    public GestaltBox filter(String q) {
//        ArrayList<Gestalt> o = new ArrayList<Gestalt>();
//        for (int i = 0; i < gestalts.size(); i++) {
//            Gestalt g = gestalts.get(i);
//            if (g.contains(q)) {
//                o.add(g);
//            }
//        }
//        return new GestaltBox(o);
//    }
//
//    public Gestalt findOpenG(String msg) {
//        return findOpenG(new Gestalt(msg));
//    }
//
//    public Gestalt findOpenG(Gestalt g) {
//        int i = openGs().gestalts.indexOf(g);
//        if (i >= 0) {
//            return openGs().gestalts.get(i);
//        } else {
//            return null;
//        }
//    }
//
//    public boolean contains(Gestalt g) {
//        return gestalts.contains(g);
//    }
//
//    public boolean isOpenG(String s) {
//        for (Gestalt g : openGs().gestalts) {
//            if (s.equals(g.msg)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    //close all gestalts in this box. Useful in JQuery-like method chaining
//    void close() {
//        for (Gestalt g : openGs().gestalts) {
//            g.close();
//        }
//    }
//
//    public String toString() {
//        Collections.sort(gestalts);
//        StringBuffer ot = new StringBuffer();
//        for (Gestalt g : gestalts) {
//            ot.append(g.toString() + "\n");
//            //if(g.sts<4)
//        }
//        System.out.println(gestalts.size());
//        return ot.toString();
//    }
//    
//}
