/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

/**
 * Fichero Par.java
 * @author Moisés Gautier Gómez
 * @version 1.0 10/21/11
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

public class Par {

    /*
     * Atributos privados de la clase
     */
    
    /*
     * Esta variable de tipo entero representa el primer valor de la dupla
     * (primero,segundo)
     */
    
    private int primero=0;
    
    /*
     * Esta variable de tipo entero representa el segundo valor de la dupla
     * (primero,segundo)
     */
    
    private int segundo=0;

    /**
     * Constructor
     */
    
    public Par(){}

    /**
     * Constructor
     * @param p parámetro de entrada de tipo Par que representa una dupla
     * de enteros (x,y).
     */
    
    public Par(Par p)
    {
	primero = p.primero();
	segundo = p.segundo();
    }

    /**
     * Constructor
     * @param x parámetro de entrada de tipo entero que representa el primer
     * campo de la dupla (x,y).
     * @param y parámetro de entrada de tipo entero que representa el segundo
     * campos de la dupla (x,y).
     */
    
    public Par(int x, int y)
    {
	if(x >= 0 && y >= 0)
	    {
		primero = x;
		segundo = y;
	    }
    }

    /**
     * Método modificador
     * @param x parámetro de entrada de tipo entero que especifica el valor
     * que tomará el primer campo interno de la clase Par.
     */
    
    public void primero(int x) 
    {
	primero=x;
    }

    /**
     * Método modificador
     * @param y parámetro de entrada de tipo entero que especifica el valor
     * que tomará el segundo campo interno de la clase Par.
     */
    
    public void segundo(int y)
    {
	segundo=y;
    }

    /**
     * Método observador
     * @return Devuelve un valor de tipo entero que representa el valor interno
     * del primer campo de la clase Par.
     */
    
    public int primero()
    {
	return primero;
    }
    
    /**
     * Método observador
     * @return Devuelve un valor de tipo entero que representa el valor interno
     * del segundo campo de la clase Par.
     */

    public int segundo()
    {
	return segundo;
    }
  
}
