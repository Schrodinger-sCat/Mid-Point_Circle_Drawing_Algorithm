import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math.*;
//import java.math.*;
import java.util.Scanner;


public class CircleDrawing  implements GLEventListener {

    private GLU glu;
    public static int iteration=1;

    public void init(GLAutoDrawable gld) {

        Scanner scanner = new Scanner(System.in);

        scanner.close();
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        Center c=new Center(0, 0);
        int r=100;
        drawCircle(gl, r, c);
        Center c1=new Center(c.getX(), c.getY()+(r/2));
        drawCircle(gl, r/2, c1);
        Center c2=new Center(c.getX(), c.getY()-(r/2));
        drawCircle(gl, r/2, c2);
        Center c3=new Center((r/2)+c.getX(), c.getY());
        drawCircle(gl, r/2, c3);
        Center c4=new Center(c.getX()-(r/2), c.getY());
        drawCircle(gl, r/2, c4);
        int new_center= (int) Math.round(Math.sqrt((r*r)/2)/2);
        Center c5=new Center(c.getX()+new_center, c.getY()+new_center);
        drawCircle(gl, r/2, c5);
        Center c6=new Center(c.getX()-new_center, c.getY()+new_center);
        drawCircle(gl, r/2, c6);
        Center c7=new Center(c.getX()-new_center, c.getY()-new_center);
        drawCircle(gl, r/2, c7);
        Center c8=new Center(c.getX()+new_center, c.getY()-new_center);
        drawCircle(gl, r/2, c8);
    }

    private void drawCircle(GL2 gl, int r, Center c) {

        //ENTER YOUR CODE HERE
        int x=0;
        int y=r;
        int d=1-r;
        draw8Way(gl, x, y, c);
        while(x<y){
            if(d<0){
                d=d+2*x+3;
                x++;
            }
            else{
                d=d+2*x-2*y+5;
                x++;
                y--;
            }
            draw8Way(gl, x, y, c);
        }
        //System.out.println(x+", "+y);
    }

    private void draw8Way(GL2 gl, int x, int y, Center c) {
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x+c.getX(), y+c.getY());
        gl.glVertex2d(y+c.getX(), x+c.getY());

        print(x+c.getX(), y+c.getY());
        print(y+c.getX(), x+c.getY());

        gl.glVertex2d(-x+c.getX(), y+c.getY());
        gl.glVertex2d(-y+c.getX(), x+c.getY());

        print(-x+c.getX(), y+c.getY());
        print(-y+c.getX(), x+c.getY());

        gl.glVertex2d(-x+c.getX(), -y+c.getY());
        gl.glVertex2d(-y+c.getX(), -x+c.getY());

        print(-x+c.getX(), -y+c.getY());
        print(-y+c.getX(), -x+c.getY());


        gl.glVertex2d(x+c.getX(), -y+c.getY());
        gl.glVertex2d(y+c.getX(), -x+c.getY());

        print(x+c.getX(), -y+c.getY());
        print(y+c.getX(), -x+c.getY());
        gl.glEnd();

    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }
    public static void print(int x, int y){
        int i=(iteration/8)+1;
        System.out.println("Iteration: "+i);
        System.out.println("x: "+x+" y: "+y);
        System.out.println("______________________________________________");
        iteration++;
    }

}

