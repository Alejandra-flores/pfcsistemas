/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

/**
 * Fichero CamposNodos.java
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

public class CamposNodos {
    
    /*
     * Atributos privados de la clase
     */
    
    /*
     * Se empleará para almacenar las coordenadas cartesianas del plano
     * en donde se encuentran dibujados los nodos.
     */
    
    private Par nodos = new Par(-1,-1);

    /**
     * Constructor nulo.
     */
    
    public CamposNodos() {}

    /**
     * Constructor
     * @param p Parámetro de entrada que es de tipo Par (dupla de enteros) que
     * representan las coordenadas (x,y) en donde se encuentra dibujado en el 
     * lienzo el nodo actual.
     */
    
    public CamposNodos(Par p)
    {
	if(p.primero() >= -1 && p.segundo() >= -1)
	    {
		nodos.primero(p.primero());
		nodos.segundo(p.segundo());
	    }
    }

    /**
     * Método observador.
     * @return Devuelve un tipo Par (dupla de enteros) que representa las
     * coordeanadas x e y donde se encuentra dibujado el nodo en el lienzo.
     */
    
    public Par obtener_nodo()
    {
	return nodos;
    }

    /**
     * Método modificador.
     * @param p Parámetro de entrada de tipo Par (dupla de enteros) que 
     * representa las coordenadas x e y donde se encuentra dibujado
     * el nodo en el lienzo.
     */
    
    public void introducir_nodo(Par p)
    {
	if(p.primero() >= -1 && p.segundo() >= -1)
	    {
		nodos.primero(p.primero());
		nodos.segundo(p.segundo());
	    }
    }
    
}
