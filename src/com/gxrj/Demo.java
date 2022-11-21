package com.gxrj;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

public class Demo extends JFrame implements GLEventListener {
    
    private GLJPanel glJPanel;

    Demo() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "JOGL demo" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );
        glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );
        this.add( glJPanel );
        this.setVisible( true );
    }

    public static void main(String[] args) {
        new Demo();
    }

    public void display( GLAutoDrawable  drawable ) {
        GL gl = drawable.getGL();
        gl.glClear( GL.GL_COLOR_BUFFER_BIT );
    }

    public void init( GLAutoDrawable  drawable ) { }
    public void dispose( GLAutoDrawable  drawable ) { }
    public void reshape( GLAutoDrawable  drawable, int x, int y, int width, int height ) { }
}