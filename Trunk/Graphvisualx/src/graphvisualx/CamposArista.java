/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

/**
 * Fichero CamposArista.java
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

public class CamposArista {

    /*
     * Atributos privados de la clase
     */
    
    /*
     * Se empleará para guardar las coordenadas del punto origen
     * donde el usuario presione con el ratón.
     */
    
    private Par pos_origen_ = new Par(0,0);

    /*
     * Se empleará para guardar las coordenadas del punto destino
     * donde el usuario deje de presionar el ratón.
     */
    
    private Par pos_destino_ = new Par(0,0);

    /*
     * Se empleará para guardar los nodos origen y destino a los que
     * va adjunta la arista que el usuario ha delimitado en el lienzo.
     */
    
    private Par nodos_ = new Par(0,0);

    /*
     * Se empleará para guardar el coste asociado de la arista.
     */
    
    private int coste_arista_ = 0;

    
    /**
     * Constructor nulo.
     */
    
    public CamposArista() {}

    /**
     * Método observador.
     * @return Devuelve un tipo Par que se compondrá de las posiciones x e y
     * (coordenadas) de la posición origen de la arista.
     */
    
    public Par pos_origen()
    {
	return pos_origen_; 
    }

    /**
     * Método modificador.
     * Almacena la posición inicial de la arista en el campo interno de la clase
     * destinado.
     * @param p Parámetro de entrada de tipo Par (dupla de enteros) que
     * representará la dupla de coordenadas (x,y) de la posición origen
     * de la arista.
     */
    
    public void pos_origen(Par p)
    {
	if(p.primero() >= 0 && p.segundo() >= 0)
	    {
		pos_origen_.primero(p.primero());
		pos_origen_.segundo(p.segundo());
	    }
    }

    /**
     * Método observador.
     * @return Devuelve un tipo Par que se compondrá de las posiciones x e y
     * (coordenadas) de la posición destino de la arista.
     */
    
    public Par pos_destino()
    {
	return pos_destino_;
    }

    /**
     * Método modificador.
     * Almacena la posición inicial de la arista en el campo interno de la clase
     * destinado.
     * @param p Parámetro de entrada de tipo Par (dupla de enteros) que
     * representará la dupla de coordenadas (x,y) de la posición destino
     * de la arista.
     */
    
    public void pos_destino(Par p)
    {
	if(p.primero() >= 0 && p.segundo() >= 0)
	    {
		pos_destino_.primero(p.primero());
		pos_destino_.segundo(p.segundo());
	    }
    }

    /**
     * Método observador.
     * @return Devuelve un tipo Par que se compondrá de los nodos origen
     * y destino que pertenecen a la arista actual.
     */
    
    public Par nodos_arista()
    {
	return nodos_;
    }

    /**
     * Método modificador.
     * Almacena los nodos origen y destino de la arista en el campo interno 
     * de la clase destinado.
     * @param p Parámetro de entrada de tipo Par (dupla de enteros) que
     * representará la dupla de índices (x,y) de los nodos origen y destino
     * de la arista actual.
     */
    
    public void nodos_arista(Par p)
    {
	if(p.primero() >= 0 && p.segundo() >= 0)
	    {
		nodos_.primero(p.primero());
		nodos_.segundo(p.segundo());
	    }
    }

    /**
     * Método observador.
     * @return Devuelve un entero que representa el coste de la arista
     * actual.
     */
    
    public int coste_arista()
    {
	return coste_arista_;
    }
    
    /**
     * Método modificador.
     * @param x Parámetro de tipo entero que representa el coste de la arista
     * actual.
     */

    public void coste_arista(int x)
    {
	coste_arista_ = x;
    } 
    
}
