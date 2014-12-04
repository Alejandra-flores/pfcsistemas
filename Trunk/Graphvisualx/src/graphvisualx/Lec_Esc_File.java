/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

import java.io.*;
import java.util.*;

/**
 * Fichero Graphvisualx.java
 * @author Moisés Gautier Gómez
 * @version 1.0
 * @date 21/10/2011
 */

/*
 ******************************************************************************
                   (c) Copyright 2011 Moisés Gautier Gómez
 
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************
 */

public class Lec_Esc_File extends ArrayList<String> 
{
    
    /**
     * Constructor
     * @param fileName parámetro de entrada de tipo String que será la ruta
     * del fichero en el sistema además del nombre del fichero.
     * @param splitter parámetor de entrada que representa el formato del
     * fichero con el que se va a trabajar. (R lectura, W escritura).
     */
    
    public Lec_Esc_File(String fileName, String splitter) {
	super(Arrays.asList(read(fileName).split(splitter)));
        
	/* 
         * El método split() con expresiones regulares suele dejar 
         * una cadena de caracteres vacía en la primera posición.
         */
        
	if (get(0).equals(""))
	    remove(0);
    }
    
    /**
     * Constructor
     * @param fileName parámetro de entrada de tipo String que será la ruta
     * del fichero en el sistema además del nombre del fichero.
     */
    
    public Lec_Esc_File(String fileName) {
	this(fileName, "\n");
    }
    
    
    /**
     * Método observador (estático)
     * Se emplea para leer un archivo y devolverlo al sistema como una única
     * cadena de caracteres.
     * @param fileName parámetro de entrada de tipo String que representa
     * la ruta del fichero en el sistema, además del nombre del mismo.
     * @return Devuelve un valor de tipo Strig que será el contenido del fichero
     * del sistema. En caso contrario devolverá null.
     */
    
    public static String read (String fileName) {
	StringBuilder sb = new StringBuilder();
	try{
	    BufferedReader in = new BufferedReader (new FileReader (new File(fileName).getAbsoluteFile()));
	    try{
		String s;
		while((s = in.readLine()) != null) {
		    sb.append(s);
		    sb.append("\n");
		}
	    } finally {
		in.close();
	    }
	} catch(IOException e) {
	    throw new RuntimeException(e);
	}
	return sb.toString();
    }
    
    /**
     * Método modificador (estático)
     * Se emplea para la escritura de texto en un fichero de texto plano.
     * @param fileName parámetro de entrada de tipo String que será la ruta
     * del fichero en el sistema además del nombre del fichero.
     * @param text parámetro de entrada de tipo String que contendrá el texto
     * que se quiere introducir en el fichero.
     */
    
    public static void write(String fileName, String text) {
	try{
	    PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
	    try{
		out.print(text);
	    } finally {
		out.close();
	    }
	} catch(IOException e) {
	    throw new RuntimeException(e);
	}
    }
    
    /**
     * Método modificador (estático)
     * Se emplea para la escritura de texto en un fichero de texto plano.
     * @param fileName parámetro de entrada de tipo String que será la ruta
     * del fichero en el sistema además del nombre del fichero.
     */

    public void write(String fileName) {
	try {
	    PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
	    try{
		for(String item : this)
		    out.print(item);
	    } finally {
		out.close();
	    }
	} catch(IOException e) {
	    throw new RuntimeException(e);
	}
    }    
}