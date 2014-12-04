/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;


import java.awt.Color;
import javax.swing.JFileChooser;
import java.awt.Desktop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

 
/**
 * Fichero AbrirFichero.java
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

public class AbrirFichero {

    /**
     * Atributos privados de la clase AbrirFichero
     */ 
    
    private String ruta="";
    private StringBuilder sb;
    private StringBuilder fichero_nuevo;
    
    /**
     * Constructor nulo
     */
    
    public AbrirFichero() {}
    
    /**
     * Constructor
     * @param guardar Especifica si se abre un fichero para su guardado
     * o no.
     */
    
    public AbrirFichero(boolean guardar)
    {
        if(guardar)
        {
            elegirDestinoFichero();
        }
    }
 
    /**
     * Constructor
     * @param valor Variable de tipo boolean que se emplea para la funcionalidad
     * de lectura normal de un fichero o de la muestra del manual de algoritmos.
     * @param modo_escritura Variable de tipo boolean que especifica si se
     * abre un fichero base de estructura de un grafo o uno especificado por
     * el usuario.
     * @param imagen Variable de tipo boolean que especifica si el fichero
     * a guardar es de tipo imagen o no.
     */
    
    public AbrirFichero(boolean valor, boolean modo_escritura, boolean imagen) {
        
        if(valor && !modo_escritura)
        {
	elegirFichero();
        }
        else
        {
            /*
             * Aquí el usuario visualiza en pantalla el documento
             * donde se detallan todos los algoritmos disponibles
             * así como conceptos relacionados con los grafos.
             */
            
            if(!valor)
                Abrir("./src/graphvisualx/Recursos/manual_algoritmos_conceptos.pdf");
        }
        
        if(valor && modo_escritura && !imagen)
        {
            /*
             * Si se entra en esta bifurcación significa que
             * se leerá el fichero base que define la estructura
             * a seguir para la representación de un grafo mediante
             * un fichero de texto.
             */
            
            elegirFichero();   
        }
        
        if(valor && modo_escritura && imagen)
        {
            elegirImagenFichero();
        }
        
    }
    
    /**
     * Método observador.
     * @return Devuelve el contenido del fichero en un tipo String.
     */
    
    public String contenido_fichero()
    {
       return fichero_nuevo.toString();
    }
    
    /**
     * Método observador.
     * @return Devuelve la ruta del fichero seleccionado en formato de tipo
     * String.
     */
    
    public String ruta_fichero()
    {
        return ruta;
    }
    
    /**
     * Método modificador (privado).
     * Este método se emplea para abrir el selector de fichero destino donde se
     * guardará la estructura del grafo en formato de tipo texto.
     */
    
    private void elegirDestinoFichero()
    {
        JFileChooser elegirFichero = new JFileChooser();
	File fichero=null;
	FileFilter extension = new FileNameExtensionFilter("Archivos txt", "txt");
        
	elegirFichero.addChoosableFileFilter(extension);
        elegirFichero.setForeground(new Color(214,217,223));
        
        
 
	int estado = elegirFichero.showDialog(null, "Guardar Archivo");
 
	if (estado == JFileChooser.APPROVE_OPTION) {
	    fichero = elegirFichero.getSelectedFile();
	    describirRuta(fichero.toString());
	}
	else if (estado == JFileChooser.CANCEL_OPTION) {
	    JOptionPane.showMessageDialog(null, "No se guardó el archivo", "Error", 0);
	    
	}
    }
    
    /**
     * Método modificador (privado).
     * Este método se emplea para abrir el selector de fichero destino donde se
     * guardará la imagen del grafo en formato jpg.
     */
    
    private void elegirImagenFichero()
    {
        JFileChooser elegirImagenFichero = new JFileChooser();
	File fichero=null;
	FileFilter extension = new FileNameExtensionFilter("Archivos jpg", "jpg");
	elegirImagenFichero.addChoosableFileFilter(extension);
 
	int estado = elegirImagenFichero.showDialog(null, "Guardar Imagen");
 
	if (estado == JFileChooser.APPROVE_OPTION) {
	    fichero = elegirImagenFichero.getSelectedFile();
	    describirRuta(fichero.toString());
	}
	else if (estado == JFileChooser.CANCEL_OPTION) {
	    JOptionPane.showMessageDialog(null, "No se eligio archivo", "Error", 0);
	    
	}    
    }
    
    /**
     * Método modificador (privado).
     * Este método se emplea para abrir el selector de fichero origen donde se
     * seleccionará el fichero. 
     */
    
    private void elegirFichero() {
	JFileChooser elegirFichero = new JFileChooser();
	File fichero=null;
	FileFilter extension = new FileNameExtensionFilter("Archivos txt", "txt");
	elegirFichero.addChoosableFileFilter(extension);
 
	int estado = elegirFichero.showDialog(null, "Abrir Archivo");
 
	if (estado == JFileChooser.APPROVE_OPTION) {
	    fichero = elegirFichero.getSelectedFile();
	    describirRuta(fichero.toString());
	}
	else if (estado == JFileChooser.CANCEL_OPTION) {
	    JOptionPane.showMessageDialog(null, "No se eligio archivo", "Error", 0);
	    
	}
    }
 
    /**
     * Método modificador.
     * Este método se emplea para introducir la ruta del fichero que se ha
     * procesado por el usuario.
     * @param ruta Parámetro de tipo String que contiene la ruta del fichero
     * origen y que se almacenará en la variable interna de la clase.
     */
    
    private void describirRuta(String ruta) {
	this.ruta=ruta;
    }
 
    /**
     * Método observador (privado).
     * Este método se emplea para obtener en formato String la ruta del fichero
     * en ejecución.
     * @return Devuelve la ruta del fichero en ejecución en formato String.
     */
    
    private String getRuta() {
	return ruta;
    }
 
    /**
     * Método modificador (privado).
     * Este método abre el fichero según la ruta especificada en el parámetro
     * formal. El programa que abrirá dicho fichero será el predeterminado por
     * el sistema operativo.
     * @param ruta Parámetro de tipo String que determinar la ruta del fichero
     * en el sistema UNIX.
     */
    
    private void Abrir(String ruta){
	File fichero = new File(ruta);
	try{
	    Desktop.getDesktop().open(fichero);
	}catch (Exception e) {
	    System.out.println(e);
	}
    }
    
}