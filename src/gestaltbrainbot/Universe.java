/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaltbrainbot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

/**
 *
 * @author Jacob-MTech
 */
class Universe {

    //HWall w;

    private Wall[] walls = {
        new Wall(200, 0, 90, 200), new Wall(200, 200, 180, 100), new Wall(100, 200, 90, 75),
        new Wall(300, 0, 90, 200), new Wall(300, 200, 0, 100), new Wall(400, 200, 90, 75),
        new Wall(100, 275, 0, 300), //        new Wall(300, 0, 2, 500),
    //        new Wall(100, 200, 1, 300),
    //        new Wall(100, 250, 1, 300),  //horizontal, right
    //        new Wall(200, 0, 2, 200), new Wall(250, 0, 2, 200) //vertical,down
    };
    private Worm s;

    int time = 0;
//    UniverseObject[] uOs = new UniverseObject[w.length + 0];
    UniverseObject[] uOs = new UniverseObject[walls.length + 1];

    //default universe
    public Universe() {
        //w = new HWall(100);
        s = new Worm(250, 60, 90); //snake goes down at first
        uOs[0] = s;
//        uOs[0] =  new Wall(5000, 0, 2, 200);
        for (int i = 0; i < walls.length; i++) {
            uOs[i + 1] = walls[i];
        }
        // if(o instanceof Path)
//        Path np = new Path(200, 0, 200, 300, 2);
//        System.out.print(w[0] + " " + np + " ");
//        System.out.println(w[5].crosses(w[6]));
        //System.out.println(w[0].crosses(w[1]));
    }

    void next() throws InterruptedException {
        Thread.sleep(30);
        for (UniverseObject o : uOs) {
            if (o instanceof Worm) {
                Worm wormCast = (Worm)o;
                o.next();
                
                double dx = o.dirXY()[0], dy = o.dirXY()[1];
                dx *= o.v;
                dy *= o.v;

                double nx = o.x + dx, ny = o.y + dy;

                boolean hitWall = false;
                Path p = new Path(o.x, o.y, nx, ny, o.dir);

                

                //System.out.println("testpath " + new Path(200, 0, 200, 400, 2).crosses(new Path(100, 100, 300, 100, 1)));
                for (Wall wall : walls) {
                    //                     System.out.println(new Path(200, 0, 200, 400, 2).crosses(currW));
                    if (p.crosses(wall)) {
                        hitWall = true;
                        break;
                    }
                }

                if (hitWall) {
                
                    wormCast.touch();
                    System.out.println("Snake hit a wall");
                } else {
                    o.y = ny;
                    o.x = nx;
                }
                
            }
        }
        time++;
        if (time > 50) {
//            s.x = 250;
//            s.y = 60;
//            s.dir = 2;
//            time = 0;
//            System.out.println("Restart");
//            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        
        for (UniverseObject uO : uOs) {
            if (uO != null) {
                uO.paintComponent(g);
            }
        }
    }

}

/**
 *
 * @author Jacob-MTech
 */
abstract class UniverseObject {

    double  x;
    double  y;
    double  dir; //dir is 0 1 2 3 clockwise from top
    double v;
    Color color;

    public abstract void next();

    public abstract void paintComponent(Graphics g);

    public String dirName() {
        switch (dir) {
            case 0:
                return "UP";
            case 90:
                return "RIGHT";
            case 180:
                return "DOWN";
            case 270:
                return "LEFT";
            default:
                 return String.valueOf(dir); //throw new RuntimeException("EXCEPTION: dir is not 0 to 3");
        }
    }

    /**
     *
     * @return 0-3 starting up going clocksiwse
     */
    public double[] dirXY() {
        double[] o = {Math.cos(dir*2*Math.PI/360),Math.sin(dir*2*Math.PI/360)};
        return o;
    }

}

/**
 *
 * @author Jacob-MTech
 */
class Path extends UniverseObject {

    //not really "UniverseObject" just want the methods

    double  x2;
    double  y2;

    public Path(double  x1, double  y1, double  x2, double  y2, double  dir) {
        this.x = x1;
        this.y = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.dir = dir;
        this.color = Color.RED;
    }

    //no internal updates to make for a path!
    @Override
    public void next() {
    }

    public boolean crosses(Path w) {
        return Line2D.linesIntersect(x, y, x2, y2, w.x, w.y, w.x2, w.y2);
//        
//        //assuming rectilinear now
//        //System.out.println("dir" + Arrays.toString(dirXY())+Arrays.toString(w.dirXY()));
//        if (dirXY()[0] != 0) {
//            //this is horiz path
//            if (w.dirXY()[1] != 0) {
//                if (w.x >= Math.min(this.x, this.x2) && w.x <= Math.max(this.x, this.x2)) {
//                    if (this.y >= Math.min(w.y, w.y2) && this.y <= Math.max(w.y, w.y2)) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        } else if (dirXY()[1] != 0) {
//            //this is vert path
//            if (w.dirXY()[0] != 0) {
//                if (w.y >= Math.min(this.y, this.y2) && w.y <= Math.max(this.y, this.y2)) {
//                    if (this.x >= Math.min(w.x, w.x2) && this.x <= Math.max(w.x, w.x2)) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        }
//        return true;
//        //           else {
//        //                throw new RuntimeException("Wall not rectilinear");
//        //            }
//        // old:
//        //y=m1 x + b1
//        // y=m2 x + b2
//        // -(b1-b2)/(m1-m2) = x
    }

    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.drawLine((int)x, (int)y, (int)x2, (int)y2);
    }

    public String toString() {
        return "((" + x + "," + y + "),(" + x2 + "," + y2 + "))";
    }

}

/**
 *
 * @author Jacob-MTech
 */
class Wall extends Path {

    double  len;

    public Wall(double  x, double  y, double  dir, double  len) {
        super(x, y, 0, 0, dir); //#hack
        this.len = len;
        this.x2 = x + dirXY()[0] * len;
        this.y2 = y + dirXY()[1] * len;
        this.color = Color.BLACK;
    }

}
