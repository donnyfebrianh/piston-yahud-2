/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Donny
 */
public class Piston {

    static int WARNA_HIJAU = 0;
    static int WARNA_MERAH = 1;

    static void busi_utuh(GL gl) {
        busi_kerucut(gl);
        busi(gl);
        busi_tengah(gl);
        busi_atas(gl);
        busi_kerucut_atas(gl);
    }

    static void busi_kerucut_atas(GL gl) {
        gl.glTranslated(0, 0, 0.2);
        gl.glRotated(90, 1, 0, 0);
        float BODY_LENGTH = 0.2f;
        float BODY_RADIUS = 0.1f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        gl.glRotated(90, 1, 0, 0);
        GLUquadric q = glu.gluNewQuadric();
        glu.gluCylinder(q, 0, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);

    }

    static void busi_atas(GL gl) {
        float BODY_LENGTH = 0.4f;
        float BODY_RADIUS = 0.15f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, BODY_RADIUS, 0.1f, BODY_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, 0.1f, SLICES, STACKS);
    }

    static void busi_tengah(GL gl) {
        float BODY_LENGTH = 0.3f;
        float BODY_RADIUS = 0.3f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, BODY_RADIUS, 0.15f, BODY_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, 0.15f, SLICES, STACKS);
    }

    static void busi(GL gl) {
        float BODY_LENGTH = 0.9f;
        float BODY_RADIUS = 0.3f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, 0.15f, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, 0.15f, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
    }

    static void busi_kerucut(GL gl) {
        gl.glRotated(90, 1, 0, 0);
        float BODY_LENGTH = 0.2f;
        float BODY_RADIUS = 0.15f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        gl.glRotated(90, 1, 0, 0);
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, 0, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
    }

    static void katup(GL gl) {
        lengan_katup(gl);
        gl.glTranslated(0, 0.6, 0);
        katup_kerucut(gl);
    }

    static void lengan_katup(GL gl) {
        float BODY_LENGTH = 2.5f;
        float BODY_RADIUS = 0.1f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        gl.glPushMatrix();
        gl.glRotated(90, 1, 0, 0);
        gl.glTranslated(0, 0, -3);
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, BODY_RADIUS, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
        gl.glPopMatrix();
    }

    static void katup_kerucut(GL gl) {
        float BODY_LENGTH = 0.3f;
        float BODY_RADIUS = 0.4f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        gl.glRotated(90, 1, 0, 0);
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, 0, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
    }

}
