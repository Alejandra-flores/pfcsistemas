/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphvisualx;

/**
 * Fichero Arista.java
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

public class Arista implements Cloneable
{

    /**
     * Atributos privados de la clase
     */

    /*
      Valor entero que representa el vértice origen o 
      uno de los extremos de la arista.
      Por defecto se inicializa a 0.
     */

    private int origen=0;

    /*
      Valor entero que representa el vértice destino o 
      uno de los extremos de la arista.
      Por defecto se inicializa a 0.
     */

    private int destino=0;

    /*
      Valor entero que representa el valor asociado
      a la arista, ya sea para grafos de costes
      (Coste >= 0) como para grafos de adyacencia
      (Coste: x ->[0,1], siendo x un entero).
     */

    private int Coste=0;

    /**
     * Constructor nulo.
     */

    public Arista() {}

    /**
     * Método modificador.
     * @param x valor entero que representará el vértice origen de la arista.
     * @return void
     */

    public void v_origen(int x) 
    {
	origen = x;
    }

    /**
     * Método modificador.
     * @param x valor entero que representará el vértice destino de la arista.
     * @return void
     */

    public void v_destino(int x)
    {
	destino = x;
    }

    /**
     * Método observador.
     * @return devuelve el vértice origen de la arista.
     */

    public int v_origen()
    {
	return origen;
    }

    /**
     * Método observador.
     * @return devuelve el vértice destino de la arista.
     */

    public int v_destino()
    {
	return destino;
    }

    /**
     * Método modificador.
     * @param x valor entero que representará el coste de la arista.
     * @return void
     */

    public void coste(int x)
    {
	Coste = x;
    }

    /**
     * Método observador.
     * @return devuelve el coste asociado a la arista.
     */

    public int coste()
    {
	return Coste;
    }

    /**
     * Sobrecarga método toString.
     * @return devuelve una cadena de caracteres que representa la arista.
     */ 

    @Override
    public String toString()
    {
	return "("+origen+","+destino+","+Coste+")";
    }

    /**
     * Sobrecarga método Clone().
     * Defino este método para poder realizar la copia de objetos de Aristas.
     * @return devuelve el objeto Arista correspondiente para clonarlo en otro
     * del mismo tipo.
     */

    public Object Clone()
    {
	Object obj = null;
	try{
	    obj=super.clone();
	}catch(CloneNotSupportedException ex)
	    {
		System.out.println("No se puede duplicar");
	    }

	return obj;
    }
}