package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Piston_yahud.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Piston_yahud implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Piston_yahud());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    

    public Piston_yahud() {
        this.naik_katup = 0.2;
        this.turun_katup = 0;
        this.turun_katup_2 = 0;
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        
//         Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.95f, 0.95f, 0.95f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);// try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 60.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    //untuk katup piston
    double turunkatup = 0, turun_katup_2 = 0;
    double naik_katup, naik_katup_2 = 0.2;

    //untuk kamera
    static double x = 0;
    static double y = 0;
    static double i = 0;
    static double j = 0;

    double sudut = 0;
    private float view_rotx = 20.0f;
    private float view_roty = 35.0f;
    private int oldMouseX;
    private int oldMouseY;

    float HEIGHT = 0f;

    float geser_kiri = 0;
    float geser_kanan = 0;
    float geser_pipa1 = 0;
    float geser_pipa2 = 0;
    float geser_busi = 0;
    float katup1 = 0;
    float katup2 = 0;
    boolean geser = false;

    int WARNA = Piston.WARNA_HIJAU;
    //katup piston
    double turun_katup = 0, turun_katupp_2 = 0;
    double naikk_katup = 0.2, naik_katupp_2 = 0.2;
    //ndas piston
    double turun_piston = 0;
    double naik_piston = 2;

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glEnable(GL.GL_DEPTH_TEST);
        GLU glu = new GLU();
        glu.gluLookAt(
                i, 0, j, //eye
                x + i, y, -10, //center
                0, 1, 0);
        // Move the "drawing cursor" around
        
        gl.glTranslatef(1f, 1.0f, -17.0f);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glTranslated(0, 3, 0);

        gl.glColor3d(1, 1, 1);
        gl.glColor3d(0.5, 0.5, 0.5);
        gl.glTranslated(0, 0.2, 0);
        gl.glRotated(90, 1, 0, 0);
        //geser busi
        if (geser == true) {
            if (geser_busi <= 3) {
                geser_busi += 0.1;
                gl.glTranslated(0, geser_busi, 0);
                Piston.busi_utuh(gl);
            } else {
                gl.glTranslated(0, 3, 0);
                Piston.busi_utuh(gl);
            }
        } else {
            if (geser_busi >= 0) {
                geser_busi -= 0.1;
                gl.glTranslated(0, geser_busi, 0);
                Piston.busi_utuh(gl);

            } else if (geser_busi <= 0) {
                 Piston.busi_utuh(gl);
            }
        }
        
        gl.glPopMatrix();
        //geser
        if (geser == true) {
            if (geser_pipa1 <= 3) {
                geser_pipa1 += 0.1;
                gl.glTranslated(-geser_pipa1, 0, 0);

            }
        }
        //katup masuk
        gl.glPushMatrix();
        gl.glTranslated(-0.55, -1, 1.3);
        gl.glRotated(15, -7, 0, 1);
        if (WARNA == Piston.WARNA_HIJAU) {
            if (turun_katup < 0.2) {
                gl.glTranslated(0, -turun_katup, 0);
                Piston.katup(gl);
                turunkatup += 0.01;
            } else if (turun_piston < 2) {
                gl.glTranslated(0, -turunkatup, 0);
                Piston.katup(gl);
            } else if (naik_piston > 0) {
                if (naik_katup > 0) {
                    gl.glTranslated(0, -naik_katup, 0);
                    Piston.katup(gl);
                    naik_katup -= 0.01;
                } else {
                    gl.glTranslated(0, -naik_katup, 0);
                    Piston.katup(gl);
                }
            } else {
                turunkatup = 0;
                naik_katup = 0.2;
            }
        } else {
            Piston.katup(gl);
        }
        gl.glPopMatrix();

         gl.glPopMatrix();
        gl.glPushMatrix();
        if ((turun_katup_2 != 0 && naik_katup_2 != 0.2)) {
            gl.glColor3d(1, 0, 0);
        } else {
            gl.glColor3d(0.5, 0.5, 0.5);
        }
        gl.glRotated(90, 0, 0, 1);
        gl.glTranslated(-0.2, -2.25, 0);
        
        
        gl.glPopMatrix();
        if (WARNA == Piston.WARNA_HIJAU && (turun_katup < 0.2 || naik_katup > 0)) {
         } else {
            gl.glColor3d(0.5, 0.5, 0.5);
        }
        
        //katup keluar
        gl.glPushMatrix();
        gl.glTranslated(0.55, 1, 1.33);

        gl.glRotated(15, 2, 4, 1);
        if (WARNA == Piston.WARNA_MERAH) {
            if (turun_piston < 2) {
                gl.glTranslated(0, -turun_katup_2, 0);
                Piston.katup(gl);
            } else if (naik_piston > 0) {
                if (turun_katup_2 < 0.2) {
                    gl.glTranslated(0, -turun_katup_2, 0);
                    Piston.katup(gl);
                    turun_katup_2 += 0.01;
                } else {
                    gl.glTranslated(0, -turun_katup_2, 0);
                    Piston.katup(gl);
                }
            } else {
                turun_katup_2 = 0;
                naik_katup_2 = 0.2;
            }
        } else {
            if (naik_katup_2 > 0) {
                gl.glTranslated(0, -naik_katup_2, 0);
                Piston.katup(gl);
                naik_katup_2 -= 0.01;
            } else {
                Piston.katup(gl);
            }
        }
        gl.glPopMatrix();

    }
    
    

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void keyTyped(KeyEvent ke) {

    }

    public void keyPressed(KeyEvent ke) {

    }

}
