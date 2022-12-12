package com.gxrj;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.glu.GLU;

public class ErrorChecker {
    
    static void checkOpenGLError( GL4 gl ) {

        GLU glu = new GLU();
        int err = gl.glGetError();

        while( err != GL4.GL_NO_ERROR ) {
            System.err.println( "glError: " + glu.gluErrorString( err ) );
            err = gl.glGetError();
        }
    }

    private static void printShaderLog( GL4 gl, int shaderObj ) {

        int[] len = new int[1];
        int[] charWritten = new int[1];
        byte[] log = null;

        gl.glGetShaderiv( shaderObj, GL4.GL_INFO_LOG_LENGTH, len, 0 );

        if( len[0] > 0 ) {
            log = new byte[ len[0] ];
            gl.glGetShaderInfoLog( shaderObj, len[0], charWritten, 0, log, 0 );
    
            System.out.println( "Shader Info Log: " );

            for( byte word: log ) 
                System.out.print( (char) word );
        }
    }

    private static void programLog( GL4 gl, int shaderProgram ) {

        int[] len = new int[1];
        int[] charWritten = new int[1];
        byte[] log = null;

        gl.glGetProgramiv( shaderProgram, GL4.GL_INFO_LOG_LENGTH, len, 0 );

        if( len[0] > 0 ) {
            log = new byte[ len[0] ];
            gl.glGetProgramInfoLog( shaderProgram, len[0], charWritten, 0, log, 0 );

            System.out.println( "Program Info Log: " );

            for( byte word: log )
                System.out.print( (char) word );
        } 
    }

    static void getCompilationErrors( GL4 gl, int shaderObj ) {
        int[] compilationStatus = new int[1];
        checkOpenGLError( gl );
        gl.glGetShaderiv( shaderObj, GL4.GL_COMPILE_STATUS, compilationStatus, 0 );
        if( compilationStatus[0] != 1 ) {
            System.out.println( "Compilation failed" );
            printShaderLog( gl, shaderObj );
        }
    }

    static void getLinkingErrors( GL4 gl, int shaderProgram ) {
        int[] linkingStatus = new int[1];
        checkOpenGLError( gl );
        gl.glGetProgramiv( shaderProgram, GL4.GL_LINK_STATUS, linkingStatus, 0 );
        if( linkingStatus[0] != 1 ) {
            System.out.println( "Linking failed" );
            programLog( gl, shaderProgram );
        }
    }
}