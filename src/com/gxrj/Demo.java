package com.gxrj;

import javax.swing.JFrame;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

public class Demo extends JFrame implements GLEventListener {
    
    private GLJPanel glJPanel;

    private int renderingProgram;
    private int vao[] = new int[ 1 ];

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

    public static void main( String[] args ) {
        new Demo();
    }

    public void init( GLAutoDrawable  drawable ) {
        GL4 gl = (GL4) drawable.getGL();
        
        this.renderingProgram = ShaderUtils.createShaderProgram( gl, 
                                            "shaders/elementVS.glsl", 
                                          "shaders/elementFS.glsl" );
        gl.glGenVertexArrays( this.vao.length, this.vao, 0 );
        gl.glBindVertexArray( this.vao[ 0 ] );
    } 

    public void display( GLAutoDrawable  drawable ) {
        GL4 gl = (GL4) drawable.getGL();

        gl.glUseProgram( this.renderingProgram );
        gl.glDrawArrays( GL4.GL_TRIANGLES, 0, 3 );
    }

    public void dispose( GLAutoDrawable  drawable ) { }
    public void reshape( GLAutoDrawable  drawable, int x, int y, int width, int height ) { }
}