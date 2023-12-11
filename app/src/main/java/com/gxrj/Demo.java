package com.gxrj;

import javax.swing.JFrame;

import com.gxrj.utils.ShaderUtils;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.Animator;

public class Demo extends JFrame implements GLEventListener {
    
    private GLJPanel glJPanel;

    private int renderingProgram;
    private int vao[] = new int[ 1 ];
    
    private float locationX = 0.0f;
    private float increment = 0.01f;

    private long lastRenderTime = 0l;

    Demo() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "JOGL demo" );
        this.setSize( 800, 600 );
        this.setLocation( 270, 65 );

        glJPanel = new GLJPanel();
        glJPanel.addGLEventListener( this );

        this.add( glJPanel );
        this.setVisible( true );

        Animator animator = new Animator( glJPanel );
        animator.start();
    }

    /**Clears the old buffers*/
    private void clearScreenBuffers( GL4 gl ) {
        gl.glClear( GL4.GL_DEPTH_BUFFER_BIT );
        gl.glClear( GL4.GL_COLOR_BUFFER_BIT );
    }

    /**Updates the element position and sends the value to the rendering program 
     * before sends it to the pipeline*/
    private void updatePosition( GL4 gl ) {
        locationX += increment;
        if( locationX > 1.0f || locationX < -1.0f ) { //Bounces when off bounds
            increment = -increment;
        }
        int offsetReference = gl.glGetUniformLocation( renderingProgram, "offset" );
        gl.glProgramUniform1f( renderingProgram, offsetReference, locationX );
    }

    public void init( GLAutoDrawable  drawable ) {
        GL4 gl = (GL4) drawable.getGL();
        
        var shadersPath = "app/src/main/shaders/";

        this.renderingProgram = ShaderUtils
                                    .createShaderProgram( 
                                            gl, shadersPath + "elementVS.glsl", shadersPath + "elementFS.glsl" );

        gl.glGenVertexArrays( this.vao.length, this.vao, 0 );
        gl.glBindVertexArray( this.vao[ 0 ] );
    } 

    public void display( GLAutoDrawable  drawable ) {
        GL4 gl = (GL4) drawable.getGL();

        if( System.currentTimeMillis() > lastRenderTime + 33.33 ) {
            this.clearScreenBuffers( gl );
            
            //Declares the shader program to be used by its reference
            gl.glUseProgram( this.renderingProgram );
            
            this.updatePosition( gl );
            
            //Initiates the pipeline processing
            gl.glDrawArrays( GL4.GL_TRIANGLES, 0, 3 );
            
            lastRenderTime = System.currentTimeMillis();
        }
    }

    public void dispose( GLAutoDrawable  drawable ) { }
    public void reshape( GLAutoDrawable  drawable, int x, int y, int width, int height ) { }

    public static void main( String[] args ) {
        new Demo();
    }
}
