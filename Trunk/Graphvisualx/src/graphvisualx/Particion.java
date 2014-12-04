/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

import java.util.Arrays;

/**
 * Fichero Particion.java
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

public class Particion
{

    /**
     * Atributos privados
     */

    /*
      Array de enteros que contiene los padres
      de las posiciones (nodos) del sistema.
     */

    private int [] padre;

    /*
      Valor entero que cuyo contenido es
      el número de nodos que posee el grafo.
     */

    private int nEltos;

    /**
     * Constructor nulo.
     */

    public Particion() {}

    /**
     * Constructor predeterminado.
     * Se implementa un bosque de árboles (con control de altura). 
     * Compresión de caminos.
     * @param n número de elementos de la partición que se crea
     */

    public Particion(int n)
    {
	padre = new int[n];
	nEltos = n;

	Arrays.fill(padre,0,nEltos,-1);
	//Creamos n árboles de altura 0 representada con -1
    }

    /**
     * Método modificador.
     * Une el subconjunto del elemento a y el del elemento b en uno de los subconjuntos
     * arbitrariamente. La partición P queda con un miembro menos.
     * @param a representante de la clase de partición (nodo).
     * @param b representante de la clase de partición (nodo).
     * @return void
     */
    
    public void Union(int a, int b)
    {
	if(padre[b] < padre[a])
	     padre[a] = b;
	 else
	     {
	 	if(padre[a] == padre[b])
	 	    padre[a]--;

	 	padre[b] = a;
	     }
    }

    /**
     * Método observador.
     * Devuelve el representante del subconjunto al que pertenece el elemento x.
     * @param x elemento de la partición.
     * @return devuelve el representante del subconjunto al que pertenece el elemento x.
     */

    public int Encontrar(int x)
    {
	int raiz,y;

	raiz = x;

	 while(padre[raiz] > -1)
	     raiz = padre[raiz];

	 /* Los nodos del camino de x a raiz se hacen hijo de raiz. */
	
	 while(padre[x] >= raiz)
	     {
	 	y = padre[x];
	 	padre[x] = raiz;
	 	x = y;
	     }

	 return raiz;
    }

    
}