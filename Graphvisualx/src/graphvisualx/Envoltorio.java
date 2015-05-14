/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphvisualx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Fichero Envoltorio.java
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

public class Envoltorio
{
    /*
      Atributos privados.
     */

    /*
      Número de nodos del grafo.
     */

    private int num_nodos_ = 0;

    /*
      Número de aristas del grafo.
     */

    private int num_aristas_ = 0;

    /*
      Variable que almacenará el valor de verdad para la característica de 
      adyacencia del grafo.
     */

    private boolean adyacente = false;

    /*
      Variable que almacenará el valor de verdad para la característica de 
      dirigido del grafo.
     */

    private boolean dirigido = false;

    /*
      Aquí almaceno un par que se corresponde con los pares 
      componente:num_nodos para así saber, dentro un super grafo conexo, cuales
      son sus componentes individuales.
     */

    private ArrayList<Integer> componentes_grafo = new ArrayList<Integer>();

    /*
      Representacíon interna del grafo con un Hashmap. <Integer> representa 
      el índice de la fila de la matriz. <ArrayList<Integer>> representa las 
      columnas para el índice de la fila.
     */

    private HashMap<Integer,ArrayList<Integer>> rep_grafo = new HashMap<Integer,ArrayList<Integer>>(); 

    /*
      Es un array en donde se almacenan las columnas correspondientes a 
      cada fila.
     */

    private ArrayList<Integer> vertices_grafo = new ArrayList<Integer>(rep_grafo.size()); 

    /**
     * Constructor nulo.
     */

    public Envoltorio(){}

    /**
     * Constructor predeterminado.
     * @param n numero de elementos del Envoltorio.
     * @exception Exception
     */

    public Envoltorio(int n) throws Exception
    {
	num_nodos_ = n;
        
	for(int i=0; i < n; ++i)
	    vertices_grafo.add(100);

	for(int j=0; j < vertices_grafo.size(); ++j)
	    {
		ArrayList <Integer> lista = new ArrayList<Integer>(vertices_grafo);
		Collections.fill(lista,100);
		rep_grafo.put(j,lista);
	    }
	
	/* Para la primera vez solo tendremos una componente, que será
	   el grafo mismo en si. */

	componentes_grafo.add(num_nodos_);

    }
    
    /**
     * Constructor predeterminado.
     * @param n numero de elementos del Envoltorio.
     * @param ady el envoltorio será para estructura de grafos 
     * con nodos adyacentes o no.
     * @param dir el grafo será dirigido o no.
     * @exception Exception
     */

    public Envoltorio(int n, boolean ady, boolean dir) throws Exception
    {
	num_nodos_ = n;
        
        if(ady)
        {
	for(int i=0; i < n; ++i)
	    vertices_grafo.add(0);

	for(int j=0; j < vertices_grafo.size(); ++j)
	    {
		ArrayList <Integer> lista = new ArrayList<Integer>(vertices_grafo);
		rep_grafo.put(j,lista);
	    }
        }
        else
        {
          for(int i=0; i < n; ++i)
	    vertices_grafo.add(100);

          for(int j=0; j < vertices_grafo.size(); ++j)
	    {
		ArrayList <Integer> lista = new ArrayList<Integer>(vertices_grafo);
		Collections.fill(lista,100);
		rep_grafo.put(j,lista);
	    }
  
        }
        
	/* Para la primera vez solo tendremos una componente, que será
	   el grafo mismo en si. */
	
	componentes_grafo.add(num_nodos_);
	adyacente = ady;
	dirigido = dir;

    }


    /**
     * Constructor predeterminado.
     * @param fileName cadena de caracteres que representa el nombre del
     * fichero en donde se encuentra definido la matriz del grafo y su
     * número de nodos, si es de adyacencia o no; y si se dirigido o no.
     * @exception Exception
     */

    public Envoltorio(String fileName) throws Exception
    {
	List<String> texto = new ArrayList<String>(new Lec_Esc_File(fileName, "\\W+"));

	int posicion = 3; 
        
        num_nodos_ = Integer.parseInt(texto.get(0)); //Número de nodos;
	componentes_grafo.add(num_nodos_);
	adyacente = Boolean.parseBoolean(texto.get(1));;
	dirigido = Boolean.parseBoolean(texto.get(2));

	/*
	  posicion a partir de la cual se debe buscar
	  en el ArrayList que contiene los datos
	  del fichero.
	*/

        if(adyacente)
        {
            for(int i=0; i < num_nodos_; ++i)
                vertices_grafo.add(0);

            for(int j=0; j < vertices_grafo.size(); ++j)
                {
                    ArrayList <Integer> lista = new ArrayList<Integer>(vertices_grafo);
                    rep_grafo.put(j,lista);
                }
        }
        else
        {
            for(int i=0; i < num_nodos_; ++i)
                vertices_grafo.add(100);

            for(int j=0; j < vertices_grafo.size(); ++j)
	    {
		ArrayList <Integer> lista = new ArrayList<Integer>(vertices_grafo);
		Collections.fill(lista,100);
		rep_grafo.put(j,lista);
	    }
	
        }
	

	for(int i=0; i < num_nodos_; ++i)
	    for(int j=0; j < num_nodos_; ++j)
		{
		    if(i != j)
			{
			    this._arista_nueva_(i, j, Integer.parseInt(texto.get(posicion)), dirigido);}
			    posicion++;
			
		}

    }

    /**
     * Método observador.
     * Devuelve un valor booleano que se dice si el grafo es o no
     * de adyacencia. (Sólo se usará en los casos que se lea
     * el contenido del grafo desde un fichero).
     * @return true (adyacente) o false (no adyacente)
     */

    public boolean fich_adyacente () 
    {
	return this.adyacente;
    }


    /**
     * Método observador.
     * Devuelve un valor booleano que se dice si el grafo es o no
     * de dirigido. (Sólo se usará en los casos que se lea
     * el contenido del grafo desde un fichero).
     * @return true (dirigido) o false (no dirigido)
     */

    public boolean fich_dirigido () 
    {
	return this.dirigido;
    }

    /**
     * Método observador.
     * Devuelve el número de aristas que componen el grafo.
     * @return numero de aristas del grafo.
     */
    
    public int _aristas_numero_()
    {
	if(num_aristas_ == 0)
	    {

		int suma=0;

		for(Integer x=0; x < rep_grafo.size(); ++x) 
		    {			
			for(Integer i=0; i < vertices_grafo.size(); ++i)
			    if(rep_grafo.get(x).get(i) > 0 && x != i && rep_grafo.get(x).get(i) < 100)
				num_aristas_++;
			
		    }
	    }
	
	if(dirigido)
	    return num_aristas_;
	else
	    return num_aristas_/2;
    }
   
    /**
     * Método observador.
     * Devuelve el número de nodos o vértices que componen el grafo.
     * @return número de vértices del grafo.
     */
   
    public int _nodos_numero_()
    {
	return num_nodos_;
    }
   
    /**
     * Método observador.
     * Devuelve el valor del x de la arista que une ambos vértices.
     * @param v_i valor entero que representa el vértice v_i de la arista.
     * @param v_j valor entero que representa el vértice v_j de la arista.
     * @return devuelve el contenido de la arista para [v_i,v_j].
     */
   
    public int _arista_contenido_ (int v_i, int v_j)
    {
	if(v_i != v_j)
	    {
		return rep_grafo.get((Integer)v_i).get(v_j);
	    }
	else
	    {
		if(adyacente)
		    return rep_grafo.get((Integer)v_i).get(v_j);
		else
		    return 0;
	    }
    }
   
    /**
     * Método observador.
     * Devuelve el valor del x del vértice (si es que tiene).
     * @param v valor entero que se corresponde con un vértice del grafo.
     * @return devuelve el contenido correspondiente para el vértice (si es que tiene).
     */
   
    public int _vertice_contenido_ (int v)
    {
	if(v <= vertices_grafo.size())
	    return rep_grafo.get(v).get(v);
	else
	    return 0;
    }
   
    /**
     * Método modificador.
     * Inserta un nuevo nodo en el grafo.
     * @param x valor entero que corresponde con el peso del nodo.
     * @param y valor entero que corresponde con la componente del grafo (por defecto la primera componente será 0).
     * @param infinito valor entero que especifica si el contenido de la matriz es para
     * una matriz de adyacencia o de costes. (adyacente->infinito==0, !adyacente->infinito==100)
     */
   
    public void _nodo_nuevo_ (int x, int y, int infinito)
    {

	/*
	  Añadimos un vértice más al grafo (un elemento más para el array)
	  y este se colocará al final del mismo (array) y al final
	  de los indices para las claves del HashMap.
	*/

	vertices_grafo.add(infinito);
	int tama_g = rep_grafo.size()+1;
	
	ArrayList<Integer> aux_nodo = new ArrayList<Integer>(tama_g); 

	for(int i=0; i < tama_g-1; ++i)
	    aux_nodo.add(infinito);

	rep_grafo.put(rep_grafo.size(),aux_nodo);
	
	/*
	  Ahora vamos añadiendo el valor 0 o nulo, según representación,
	  a cada array (valor para la clave del HashMap), que se colocará
	  al final como anteriormente.
	*/
	
	x=0;

	for(int aux=0; aux < rep_grafo.size() && x < rep_grafo.size(); ++aux)
	    {
		if(aux+1 == rep_grafo.size())
		    rep_grafo.get(x).add(aux,x);
		else
		    rep_grafo.get(x).add(aux,infinito);

		++x;
	    }
	
	num_nodos_++;  
	
	/*
	  Al aumentar el número de nodos de la componente
	  hay que actualizar el HashMap donde se lleva la cuenta.
	*/

	componentes_grafo.add(y,num_nodos_);
    }
    
   
    /**
     * Método modificador.
     * Inserta una nueva arista en el grafo.
     * @param x valor entero que corresponde con el x del nodo.
     * @param v_i valor entero que representa el vértice i del grafo.
     * @param v_j valor entero que representa el vértice j del grafo.
     * @param diri valor booleano que especifica si es dirigido o no el grafo.
     */
   
    public void _arista_nueva_ (int v_i, int v_j, int x, boolean diri)
    {
        
        try{
	if(v_i < num_nodos_ || v_j < num_nodos_)
	    {
		rep_grafo.get(v_i).set(v_j,x);
		if(!diri)
                    rep_grafo.get(v_j).set(v_i,x);
	    }
        }catch(Exception ex){
	    System.out.println("No existe el nodo: Lanzar exepción");}
       
    }

    /**
     * Método modificador.
     * Inserta una nueva componente en el grafo.
     * @param g Estructura Envoltorio que será nuestra componente a añadir.
     * @param  infinito valor entero que especifica si el contenido de la matriz es para
     * una matriz de adyacencia o de costes. (adyacente->infinito==0, !adyacente->infinito==100)
     */

    public void _componente_nueva_ (Envoltorio g, int infinito)
    {
	//Hay que comprobar si dicha componente es conexa o no.
	
	if(g.es_conexo())
	    {
		/*
		  Sumamos sus nodos y sus aristas al grafo del
		  área de trabajo.
		*/
		
		int salir=0;
		int ultimo_indice = rep_grafo.keySet().size(); 
		int tama_g = g._nodos_numero_();
		
		/*
		  Aquí almaceno el tamaño del ArrayList (del puntero this) anterior a que 
		  se añadiese la nueva componente. El tamaño real esta comprendido
		  entre 0 y size()-1.
		*/
		
		int [][] aux = new int[g._nodos_numero_()][g._nodos_numero_()];
		aux = g.devolver_matriz_Envoltorio();

		/*
		Obtenemos la estructura del objeto Grafo (parámetro actual)
		Almacenamos dicha matriz en un ArrayList (mismo tipo que
		valores del HashMap) que nos facilita la operacion de
		inserción por la cola del mismo. Llevamos la cuenta
		con una variable llamada salir que será el número de vueltas
		(filas) que daremos hasta rellenar la estructura interna.
		*/

		for(Integer x=0; x < rep_grafo.size(); ++x)
		    {
			for(int i=0; i < tama_g; ++i)
			    rep_grafo.get(x).add(ultimo_indice+i,infinito);
		    }

		while(salir < tama_g)
		    {	    
			ArrayList<Integer> matriz_devuelta = new ArrayList<Integer>(Collections.nCopies(tama_g+ultimo_indice,infinito));

			for(int i=0; i < tama_g; ++i)
				matriz_devuelta.set(ultimo_indice+i,aux[salir][i]);


			rep_grafo.put(rep_grafo.size(),matriz_devuelta);
			++salir;
		    }

		/*
		  Insertamos por el final del HashMap que tenemos para
		  el objeto this.
		*/

		componentes_grafo.add(componentes_grafo.size(),tama_g);

		/*
		  Hemos de llevar la cuenta de componentes del grafo
		  de las cuales se compone.
		*/
           
		num_nodos_ = num_nodos_ + tama_g;
		num_aristas_ = num_aristas_ + tama_g;

	    }
    }

    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es conexa o no.
     * @return boolean devolverá true o false si el grafo es o no conexo.
     */
    
    public boolean es_conexo()
    {
	return (this._aristas_numero_() >= (this._nodos_numero_() - 1));
    }

    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es completa o no.
     * @return boolean: devolverá true o false si el grafo es o no completo.
     */

    public boolean es_completo()
    {
	int operacion= this._nodos_numero_() * (this._nodos_numero_()-1);

	return (this._aristas_numero_() == (operacion/2));
    }

    /**
     * Método observador.
     * Devuelve la representación interna del Envoltorio(Conjunto de ArrayList).
     * @return Collection: Es un conjunto de claves-valor (ArrayList) que representa
     * la estructura interna del grafo según se ha definido.
     */

    public Collection devolver_Envoltorio()
    {
	return this.rep_grafo.keySet();
    }

    /**
     * Método observador.
     * Devuelve las componentes de un grafo junto al número de sus nodos.
     * @return Collection: Es un conjunto de claves-valor (HashMap) que representa
     * la estructura interna de las componentes del grafo. (por defecto habrá 1 componente)
     */


    public Collection devolver_componente()
    {
	return this.componentes_grafo;
    }


    /**
     * Método observador.
     * Devuelve una matriz con la estructura de los elementos del grafo.
     * @return int[][]: matriz (Array multidimensional) con la estructura de los 
     * elementos del grafo.
     */

    public int [][] devolver_matriz_Envoltorio()
    {
	int [][] matriz_costes_grafo = new int[rep_grafo.size()][rep_grafo.size()]; 

	/*
	Representación en formato matriz multidimensional que se empleará 
	para trabajar con algoritmos del estilo Floyd, Dijkstra, etc.
	*/

	
	int suma=0;

	for(Integer x=0; x < rep_grafo.size(); ++x)
	    {	
		for(int i=0; i < rep_grafo.size(); ++i)
		    matriz_costes_grafo[suma][i] = (rep_grafo.get(x).get(i));
		++suma;
	    }

	return matriz_costes_grafo;
    }

    /**
     * Método observador.
     * Muestra por la salida estándar el contenido del grafo.
     */


    public void mostrar_Envoltorio()
    {
	for(Integer i=0; i < rep_grafo.size(); ++i)
	    {
		System.out.println(i+":"+rep_grafo.get(i));
	    }	
    }
    
    /**
     * Método observador.
     * Muestra por la salida estándar el contenido del grafo, esta vez bajo una
     * representación de una matriz.
     */


    public void mostrar_matriz_Envoltorio()
    {
	int [][] matriz_costes_grafo = new int[vertices_grafo.size()][vertices_grafo.size()]; 

	/*
	Representación en formato matriz multidimensional que se empleará 
	para trabajar con algoritmos del estilo Floyd, Dijkstra, etc.
	*/

	matriz_costes_grafo = this.devolver_matriz_Envoltorio();
	
	int fila = matriz_costes_grafo.length;
	int columna = matriz_costes_grafo[0].length;

	for(int i=0; i < fila; ++i)
	    {
		System.out.print("[");

		for(int j=0; j < columna; ++j)
		    {
			if(j!=0)
			    System.out.print(",");
			
			System.out.print(matriz_costes_grafo[i][j]);
			    
		    }

		System.out.println("]"); 
	    }
    }
}