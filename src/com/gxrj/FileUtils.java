package com.gxrj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
 
    public static String[] readShaderSource( String filename ) {

        Path path = Paths.get( filename ).toAbsolutePath().normalize();
        try{
            List<String> lines = Files.readAllLines( path );

           //Alternative 1
           return lines.parallelStream()
                         /*Must append a newline separator to the lines as GLSL compiler
                          isn't capable to set preprocessor directives delimiter*/
                        .map( line -> line += "\n" )
                        .toList()
                        .toArray( new String[]{} ); 
        
        /** 
            // Alternative 2
            var arr = lines.toArray( new String[]{} );
            arr[0] += "\n";
            return arr;
        */
        }
        catch( IOException ex ) { 
            ex.printStackTrace();
        }

        return new String[]{};
    }
}