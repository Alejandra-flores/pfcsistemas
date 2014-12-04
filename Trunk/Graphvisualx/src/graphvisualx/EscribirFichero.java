/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Fichero EscribirFichero.java
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

public class EscribirFichero {
    
    /*
     * Atributos privados de la clase
     */
    
    /**
     * Constructor
     * Genera el fichero en formato txt (texto plano) que representará la
     * estructura interna del grafo de trabajo.
     * @param ady Parámetro de entrada de tipo boolean que especifica si el
     * grafo es o no adyacente.
     * @param dir Parámetro de entrada de tipo booelan que especifica si el
     * grafo es o no dirigido.
     * @param matriz Parámetro de entrada de tipo array bidimensional de enteros
     * que representa la matriz de costes/adyacencia del grafo de trabajo.
     * @throws IOException 
     */
    
    public EscribirFichero(boolean ady, boolean dir, int [][] matriz) throws IOException
    {
                
         AbrirFichero fichero = new AbrirFichero(true);
        
         PrintWriter pw = new PrintWriter(fichero.ruta_fichero()+".txt");

         pw.println(matriz.length+" "+ady+" "+dir);
         
            for (int i = 0; i < matriz.length; i++)
            {
                for(int j=0; j < matriz.length; j++)
                {
                    pw.print(matriz[i][j]+" ");   
                }
                pw.println("");
            }
            
            pw.close();

    }
}    

