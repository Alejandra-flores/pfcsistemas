/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Fichero Grafo.java
 * @author Moisés Gautier Gómez
 * @version 1.0
 * @date 4/05/2011
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

@SuppressWarnings("unchecked")
public class Grafo {
    
    /**
     * Atributos privados de la clase Grafo.
     */
    
    /*
      Estructura interna para trabajar con grafos.
     */

    private Envoltorio e; 

    /*
      Para comprobar si es de adyacencia la matriz del grafo.
     */

    private boolean adyacencia=false; 

    /*
      Para comprobar si es dirigido o no el grafo.
     */
    private boolean dirigido=false; 

    /*
      Variable privada que almacenará los valores de valencia de cada nodo
      del grafo.
     */

    private ArrayList<Integer> grados_interno; 

    /*
      Número de iteraciones para comprobar el camino.
     */

    private int contador=0; 
    
    /**
     * Constructor nulo.
     */
    
    public Grafo() {}
    
    /**
     * Constructor predeterminado.
     * @param n es el número de nodos total para el grafo.
     * @param dir: valor booleano que especifica si el grafo será dirigido o no.
     * @exception Exception
     * 
     */
    
    public Grafo(int n, boolean dir) throws Exception
    {
	e = new Envoltorio(n);
	dirigido = dir;
    }

    /**
     * Constructor predeterminado.
     * @param n es el número de nodos total para el grafo.
     * @param ady es un valor booleano que indica si es o no un grafo de aristas
     * adyacentes y no de aristas con costes asociados.
     * @param dir: valor booleano que especifica si el grafo será dirigido o no.
     * @exception Exception
     */

    public Grafo(int n, boolean ady, boolean dir) throws Exception
    {
	e = new Envoltorio(n,ady,dir);
	adyacencia = ady;
	dirigido = dir;
    }
    
    /**
     * Constructor predeterminado.
     * @param fileName: cadena de caracteres que representa el nombre del
     * fichero en donde se encuentra definido la matriz del grafo y su
     * número de nodos, si es de adyacencia o no; y si se dirigido o no.
     * @exception Exception
     */

    public Grafo(String fileName) throws Exception
    {
	e = new Envoltorio(fileName);
	adyacencia = e.fich_adyacente();
	dirigido = e.fich_dirigido();
    }


    /**
     * Método observador.
     * Devuelve el número de aristas que componen el grafo.
     * @return número de aristas del grafo.
     * @exception Exception
     */
    
    public int n_aristas() throws Exception
    {
	return e._aristas_numero_();
    }
   
    /**
     * Método observador.
     * Devuelve el número de nodos o vértices que componen el grafo.
     * @return número de vértices del grafo.
     * @exception Exception
     */
   
    public int n_nodos() throws Exception
    {
	return e._nodos_numero_();
    }
   
    /**
     * Método observador.
     * Devuelve el valor del peso de la arista que une ambos vértices.
     * @param vertice_i valor entero que corresponde al vértice i de la arista.
     * @param vertice_j valor entero que corresponde al vértice j de la arista.
     * @return devuelve el valor del peso de la arista.
     * @exception Exception
     */
   
    public int peso_arista(int vertice_i, int vertice_j) throws Exception
    {
	return e._arista_contenido_(vertice_i,vertice_j);
    }
   
    /**
     * Método observador.
     * Devuelve el valor del peso del vértice (si es que tiene).
     * @param vértice valor entero que se corresponde con un nodo del grafo (debe de existir).
     * @return devuelve el peso correspondiente para el vértice (si es que tiene).
     * @exception Exception
     */
   
    public int peso_vertice(int vertice) throws Exception
    {
	return e._vertice_contenido_(vertice);
    }
   
    /**
     * Método modificador.
     * Inserta un nuevo nodo en el grafo.
     * @param peso: valor entero que corresponde con el peso del nodo.
     * @param componente: valor entero que corresponde con la componente del grafo (por defecto la primera componente será 0).
     * @return void
     * @exception Exception
     */
   
    public void nuevo_nodo(int peso, int componente) throws Exception
    {
	if(adyacencia)
	    e._nodo_nuevo_(0,componente,0);
	else
	    e._nodo_nuevo_(peso,componente,100);
	    
    }

      /**
     * Método modificador (sobrecargado).
     * Inserta un nuevo nodo en el grafo (si no queremos insertar un peso
     * para el nodo en concreto).
     * @param componente: valor entero que corresponde con la componente del grafo (por defecto la primera componente será 0).
     * @return void
     * @exception Exception
     */
   
    public void nuevo_nodo(int componente) throws Exception
    {
	if(adyacencia)
	    e._nodo_nuevo_(0,componente,0);
	else
	    e._nodo_nuevo_(100,componente,100);
	    
    }

    /**
     * Método modificador (matriz de adyacencia).
     * Inserta una nueva arista en el grafo.
     * @param vertice_i valor entero que corresponde al vértice i del grafo.
     * @param vertice_j valor entero que corresponde vértice j del grafo.
     * @return void
     * @exception Exception
     */
   
    public void nueva_arista (int vertice_i, int vertice_j) throws Exception
    {
	if(adyacencia)
	    e._arista_nueva_(vertice_i,vertice_j,1,dirigido);
	else
	    System.out.println("Lanzar Excepción. No es de adyacencia!");
    }

   
    /**
     * Método modificador (matriz de costes - sobrecargado).
     * Inserta una nueva arista en el grafo.
     * @param vertice_i valor entero que corresponde con el vértice i del grafo.
     * @param vertice_j valor entero que corresponde con el vértice j del grafo.
     * @param peso: valor entero que corresponde con el peso del nodo.
     * @return void
     * @exception Exception
     */
   
    public void nueva_arista (int vertice_i, int vertice_j, int peso) throws Exception
    {
       e._arista_nueva_(vertice_i,vertice_j,peso,dirigido);
    }

    /**
     * Método modificador.
     * Inserta una nueva componente en el grafo.
     * @param g: Componente conexa final (grafo) que se añadirá a la componente
     * conexa actual (grafo de trabajo).
     * @return void
     * @exception Exception
     */

    public void nueva_componente(Grafo g) throws Exception
    {
	if(adyacencia && g.es_adyacencia())
	    e._componente_nueva_(g.devolver_estruc_interna(),0);
	else
	    {
		if(!(adyacencia || g.es_adyacencia()))
		    e._componente_nueva_(g.devolver_estruc_interna(),100);
		else
		    System.out.println("Lanzar excepción: no se puede añadir una matriz de costes a una matriz de adyacencia o viceversa");
	    }
    }

    /**
     * Método observador.
     * Dice si el grafo trabaja con valores de adyacencia para sus nodos
     * o no.
     * @return boolean: devuelve true o false dependiendo de si el grafo es de adyacencia o no.
     * @exception Exception
     */

    public boolean es_adyacencia() throws Exception
    {
	return adyacencia;
    }
    
    /**
     * Método observador.
     * Dice si el grafo tiene la aristas en forma dirigida
     * o no.
     * @return boolean: devuelve true o false dependiendo de si el grafo es dirigido o no.
     * @exception Exception
     */

    public boolean es_dirigido() throws Exception
    {
	return dirigido;
    }


    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es conexa o no.
     * @return boolean: devolverá true o false si el grafo es o no conexo.
     * @exception Exception
     */
   
    public boolean es_conexo() throws Exception
    {
	return e.es_conexo();
    }

    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es circular o no.
     * @return boolean: devolverá true o false si el grafo es o no circular.
     * @exception Exception
     */

    public boolean es_circular() throws Exception
    {
	int grado_grafo=0;
	int tama_G = this.devolver_matriz().length;

	if(this.n_nodos() >= 3)
	    {
		for(int i=0; i < tama_G; ++i)
		    if(grado_nodo(i,this.devolver_matriz()) == 2)
			grado_grafo++;
		

		if(grado_grafo == tama_G)
		    {
			System.out.println("El grafo es circular");
			return true;
		    }
	    }
	
	return false;

    
    }

    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es vacío o no.
     * Esta función comprueba que hay vértice pero no tiene aristas.
     * @return boolean: devolverá true o false si el grafo es o no vacío.
     * @exception Exception
     */

    public boolean es_vacio() throws Exception
    {
	if(this.n_aristas() == 0)
	    {
		System.out.println("El grafo es vacío");
		return true;
	    }
	else
	    return false;
    }


    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es completo o no.
     * @return boolean: devolverá true o false si el grafo es o no completo.
     * @exception Exception
     */

    public boolean es_completo() throws Exception
    {
	return e.es_completo();
    }

    /**
     * Método observador.
     * Comprueba si el grafo (o componente) es lineal o no.
     * @return boolean: devolverá true o false si el grafo es o no lineal.
     * @exception Exception
     */

    public boolean es_lineal() throws Exception
    {
	int cont_uno=0,cont_dos=0;
	int grado=0;
	int tama_G = this.devolver_matriz().length;

	if(this.n_nodos() >= 2)
	    {
		for(int i=0; i < tama_G; ++i)
		    {
			grado = grado_nodo(i,this.devolver_matriz());
			if(grado == 1)
			    cont_uno++;

			if(grado == 2)
			    cont_dos++;
		    }

		if(cont_uno+cont_dos == tama_G)
		    if(cont_uno == 2)
			if(cont_dos == tama_G - cont_uno)
			    {
				System.out.println("El grafo es lineal");
				return true;
			    }

	    }
	
	return false;

    }


    /**
     * Método observador.
     * Devuelve la representación interna del grafo(Conjunto de ArrayList).
     * @return Collection: Es un conjunto de claves-valor (ArrayList) que representa
     * la estructura interna del grafo según se ha definido.
     * @exception Exception
     */
    
    /*No se utiliza en ningún momento*/
    public Collection devolver_grafo() throws Exception
    {
	return e.devolver_Envoltorio();
    }

    /**
     * Método observador.
     * Devuelve la estructura Envoltorio para uso interno de la clase Grafo.
     * @return Devuelve el atributo privado de la clase "Envoltorio e".
     * @exception Exception
     */

    private Envoltorio devolver_estruc_interna() throws Exception
    {
	return e;
    }

    /**
     * Método observador.
     * Devuelve las componentes de un grafo junto al número de sus nodos.
     * @return Collection: Es un conjunto de claves-valor (HashMap) que representa
     * la estructura interna de las componentes del grafo (por defecto habrá 1 componente).
     * @exception Exception
     */


    private Collection devolver_componente() throws Exception
    {
	return e.devolver_componente();
    }

    /**
     * Método observador.
     * Devuelve una matriz con la estructura de los elementos del grafo.
     * @return int[][]: matriz (Array multidimensional) con la estructura de los 
     * elementos del grafo.
     * @exception Exception
     */

    public int [][] devolver_matriz() throws Exception
    {
	return e.devolver_matriz_Envoltorio();
    }

    /**
     * Método observador.
     * Muestra por la salida estándar el contenido del grafo.
     * @return void
     * @exception Exception
     */

    public void mostrar_grafo() throws Exception
    {
	e.mostrar_Envoltorio();
    }
    
    /**
     * Método observador
     * Muestra por la salida estándar el contenido del grafo, esta vez bajo una
     * representación de una matriz.
     * @return void
     * @exception Exception
     */

    public void mostrar_matriz() throws Exception
    {
	e.mostrar_matriz_Envoltorio();
    }
 

    /**
     * Método observador (privado)
     * Muestra por la salida estándar el contenido del grafo, esta vez bajo una
     * representación de una matriz .
     * @param M: matriz bidimensional de enteros que representa al grafo de trabajo.
     * @return void
     * @exception Exception
     */

    private void mostrar_matriz_pantalla(int [][] M)
    {
	for(int i=0; i < M.length; ++i)
	    {
		System.out.print("[");

		for(int j=0; j < M[0].length; ++j)
		    {
			if(j!=0)
			    System.out.print(",");
			
			System.out.print(M[i][j]);
		    }

		System.out.println("]");
	    }
    }
    
    /**
     * Algoritmos sobre grafos
     */

    /**
     * Algoritmo de Dijkstra - Método modificador
     * Calcula los caminos de coste mínimo entre origen y todos los vértices 
     * del grafo G (en este caso origen es 0).
     * @return void
     * @exception Exception
     */

    public void Dijkstra() throws Exception 
    {
	Algoritmos A = new Algoritmos(adyacencia);

	int [][] resultado;
	
	resultado = A.algo_Dijkstra(0,this.devolver_matriz());

	System.out.println("DIJKSTRA");
	this.mostrar_matriz_pantalla(resultado);

    }

    /**
     * Algoritmo de Dijkstra - Método modificador
     * Calcula los caminos de coste mínimo entre origen y todos los vértices 
     * del grafo G.
     * @param origen: valor entero que representa el nodo origen para el
     * algoritmo.
     * @return devuelve la matriz resultante del procesamiento de Dijkstra.
     * @exception Exception
     */

    public int [][] Dijkstra(int origen) throws Exception 
    {
	Algoritmos A = new Algoritmos(adyacencia);

	int [][] resultado;
	
	resultado = A.algo_Dijkstra(origen,this.devolver_matriz());
        System.out.println("DIJKSTRA 2.0");
	this.mostrar_matriz_pantalla(resultado);
	
        return resultado;

    }
    
    /**
     * Algoritmo de Floyd - Método modificador.
     * Calcula los caminos de coste mínimo entre cada par de vértice de todo
     * el grafo.
     * @return devuelve una matriz de enteros que representará el resultado
     * del procesamiento del grafo.
     * @exception Exception
     */

    public int [][] Floyd() throws Exception 
    {
	Algoritmos A = new Algoritmos(adyacencia);

	int [][] resultado;

	resultado = A.algo_Floyd(this.devolver_matriz(),false);
        
        mostrar_matriz_pantalla(resultado);

	return resultado;

    }

    /**
     * Algoritmo de Warshall - Método modificador.
     * Determina si hay un camino entre cada par de vértices del grafo G.
     * @return devuelve una matriz de enteros que será el resultado
     * del procesamiento del algoritmo sobre el grafo.
     * @exception Exception
     */
    
    public int [][] Warshall() throws Exception 
    {
	Algoritmos A = new Algoritmos();

	int [][] resultado;

	resultado = A.algo_Warshall(this.devolver_matriz(),this.es_adyacencia());

	return resultado;
    }


    /**
     * Algoritmo de Prim - Método modificador.
     * Devuelve el conjunto de aristas que forman un árbol
     * generador de coste mínimo del grafo conexo.
     * @return una estructura de tipo ArrayList cuyo contenido son del tipo
     * Arista. En esta estructura se encuentra un conjunto de aristas que forman
     * un árbol generador de coste mínimo del grafo conexo.
     * @exception Exception
     */

    public ArrayList<Arista> Prim() throws Exception 
    {
	Algoritmos A = new Algoritmos();

	ArrayList<Arista> resultado = new ArrayList<Arista>(A.algo_Prim(this.devolver_matriz()));

        ArrayList<Arista> prim = new ArrayList<Arista>(resultado.size());
        
        int [][] matriz = this.devolver_matriz();
        
        Arista a;
        
	for(Integer i=0; i < resultado.size(); ++i)
	    {		
		a = new Arista();
                a.v_origen(resultado.get(i).v_origen());
                a.v_destino(resultado.get(i).v_destino());
                a.coste(matriz[resultado.get(i).v_origen()][resultado.get(i).v_destino()]);
                prim.add(a);
	    }
	
        return prim;
    }

    /**
     * Algoritmo de Kruskal - Método modificador.
     * Muestra por la salida estándar el conjunto de aristas que forman un árbol
     * generador de coste mínimo del grafo conexo.
     * @return devuelve un ArrayList de tipo Arista que contiene el árbol
     * de expansión mínimo del grafo según el procesamiento del algoritmo.
     * @exception Exception
     */

    public ArrayList<Arista> Kruskal() throws Exception 
    {
	Algoritmos A = new Algoritmos();

	ArrayList<Arista> resultado = new ArrayList<Arista>(A.algo_Kruskal(this.devolver_matriz(),this.n_nodos(),this.n_aristas()));

        return resultado;
    }

    /**
     * Algoritmo de Recorrido en Profundidad (DFS) - Método modificador.
     * Muestra por la salida estándar el conjunto de nodos que forman el camino
     * tras realizar una búsqueda en profundidad de sus elementos.
     * @return devuelve un ArrayList de tipo entero que contiene el recorrido
     * en profundidad del grafo.
     * @exception Exception
     */

    public ArrayList<Integer> Rec_Profundidad() throws Exception 
    {
	Algoritmos A = new Algoritmos();

        if(!adyacencia)
            return A.recorrido_profundidad(this.devolver_matriz_adyacencia(this.devolver_matriz()));
        else
            return A.recorrido_profundidad(this.devolver_matriz());

    }

    /**
     * Algoritmo de Recorrido en Anchura (BFS) - Método modificador.
     * Muestra por la salida estándar el conjunto de nodos que forman el camino
     * tras realizar una búsqueda en anchura de sus elementos.
     * @return devuelve un ArrayList de tipo entero que contiene el recorrido
     * en anchura del grafo.
     * @exception Exception
     */

    public ArrayList<Integer> Rec_Anchura() throws Exception 
    {
	Algoritmos A = new Algoritmos();

        if(!adyacencia)
            return A.recorrido_anchura(this.devolver_matriz_adyacencia(this.devolver_matriz()));
        else
            return A.recorrido_anchura(this.devolver_matriz());
    }

    /**
     * Algoritmo de comprobación de ciclos - Método modificador.
     * Muestra por la salida estándar si el grafo contiene ciclos o no
     * (Se realiza comprobación para grafos dirigidos y no dirigidos).
     * @return Devuelve un tipo boolean que se corresponderá con los valores
     * true (aciclico) o false (no aciclico).
     * @exception Exception
     */

    public boolean Aciclico() throws Exception 
    {
	Algoritmos A = new Algoritmos();

	if(dirigido)
	    {
		if(A.AciclicoGD(this.devolver_matriz()))
                {
		    System.out.println("El grafo tiene ciclos");
                    return true;
                }
		else
                {
		    System.out.println("El grafo no tiene ciclos");
                    return false;
                }
	    }
	else
	    {
		if(A.AciclicoGND(this.devolver_matriz()))
                {
		    System.out.println("El grafo tiene ciclos");
                    return false;
                }
		else
                {
		    System.out.println("El grafo no tiene ciclos");
                    return true;
                }
	    }
	
	
    }

    /**
     * Algoritmo de comprobación hamiltoniana - Método modificador.
     * Muestra por la salida estándar el conjunto de nodos que conforman
     * el camino hamiltoniano, siempre y cuando el grafo sea hamiltoniano.
     * @return void
     * @exception Exception
     */

    public void Hamilton() throws Exception
    {
	System.out.println("CICLO HAMILTONIANO");
	boolean no_grado_par=true;
	int operacion=0;
	int operacion_2=0;
	Algoritmos A = new Algoritmos();
	array_grados(); //Llamo a este método para que el arraylist contenga
	// todos los grados de los nodos del grafo antes de la comprobación.

	/*if(this.es_conexo())
	    {
	    System.out.println("Es conexo el grafo: Es grafo hamiltoniano");
	    }
	else
	    {
		no_grado_par = false;
		System.out.println("No es conexo el grafo: No es grafo hamiltoniano");
	    }

		
	//No debe tener vértices de corte porque sino no se podrían
	//recorrer todos los vértices al eliminar dicho punto.
	if(this.vertice_corte())
	    {
		System.out.println("Tiene vértices de corte: No es grafo hamiltoniano");
	    }
	else
	    System.out.println("No tiene vértices de corte: Es grafo hamiltoniano");

	//Teorema de Dirac. Premisa
	if(this.n_nodos() >= 3)
	    System.out.println("Tiene 3 o más de 3 vértices: Es grafo hamiltoniano");
	else
	    {
		no_grado_par = false;
		System.out.println("No tiene más de 3 vértices: No es grafo hamiltoniano");
	    }


	for(Integer i=0; i < grados_interno.size(); ++i)
	    {
		if(grados_interno.get(i) == 1)
		    {
			//Algún vértice es de valencia 1
			System.out.println("Error: Valencia igual a 1");
			no_grado_par=false;
		    }
		
		if(grados_interno.get(i) >= (this.n_nodos()/2))
		    {
			//El grafo no es hamiltoniano
			System.out.println("Error: Grafo no hamiltoniano");
			no_grado_par=false;
		    }
		
		if(grados_interno.get(i) >= ((this.n_nodos()-1)/2))
		    {
			//El grafo no tiene camino hamiltoniano
			System.out.println("Error: Grafo no tiene camino hamiltoniano");
			no_grado_par=false;
		    }
		
		    }*/
	
	System.out.println("No_grado_par:"+no_grado_par);
	
	if(no_grado_par)
	    A.algo_Hamilton(this.devolver_matriz(),0,0);
	else
	    System.out.println("Grafo: No es un grafo hamiltoniano");
	
	
    }

    /**
     * Algoritmo de obtención del número cromático - Método modificador.
     * Muestra por la salida estándar el número cromático posible para la
     * coloración de vértices del grafo de trabajo.
     * @return un valor de tipo entero que representará uno de los posibles
     * números cromáticos del grafo.
     * @exception Exception
     */

    public int colores() throws Exception
    {
	Algoritmos A = new Algoritmos();
	A.recorrido_profundidad(this.devolver_matriz());

	return A.k_colores(this.devolver_matriz());
    }

    /**
     * Algoritmo de comprobación de camino euleriano - Método modificador.
     * Devuelve el conjunto de nodos que forman un camino
     * euleriano, siempre y cuando el grafo sea euleriano (Distinción entre
     * grafos dirigidos y no dirigidos).
     * @return Devuelve una estructura de tipo ArrayList cuyo contenido es
     * de tipo Integer. En la estructura se encuentra el conjunto de nodos
     * que forman un camino euleriano, siempre y cuando el grafo sea euleriano.
     * @exception Exception
     */

    public ArrayList<Integer> euler() throws Exception
    {
	Algoritmos A = new Algoritmos();
	System.out.println("CIRCUITO EULERIANO");
        ArrayList<Integer> resultado = null;

	if(dirigido)
	    {
		if(A.camino_eulerGD(this.devolver_matriz(),0,0))
		    resultado = new ArrayList<Integer>(A.mostrar_euler());
	    }
	else
	    {
		if(A.camino_eulerGND(this.devolver_matriz(),0,0))
		    resultado = new ArrayList<Integer>(A.mostrar_euler());
	    }
        
        return resultado;
    }

    /**
     * Método modificador.
     * Devuelve para el vértice del grafo, el valor de valencia o grado 
     * en tipo entero.
     * @param vertice: valor entero que se corresponde con el nodo del grafo.
     * @param G: matriz bidimensional de enteros que representa el grafo de
     * trabajo.
     * @return int: valor entero que representa el grado del nodo vértice.
     * @exception Exception
     */

    public int grado_nodo(int vertice, int [][] G) throws Exception
    {
	Algoritmos A = new Algoritmos(adyacencia);

	return A.grado_vertice(vertice,G);
    }

    /**
     * Método modificador (sobrecargado).
     * Muestra por la salida estándar para el vértice del grafo, el 
     * valor de valencia o grado en tipo entero.
     * @param vertice: valor entero que se corresponde con el nodo del grafo.
     * @return devuelve un valor de tipo entero que representa el grado del 
     * nodo vértice.
     * @exception Exception
     */

    public int grado_nodo(int vertice) throws Exception
    {
	Algoritmos A = new Algoritmos(adyacencia);

	System.out.println("Grado vértice:"+vertice+" es:"+A.grado_vertice(vertice,this.devolver_matriz()));

        return A.grado_vertice(vertice,this.devolver_matriz());
    }

    /**
     * Método modificador (sobrecargado).
     * Muestra por la salida estándar los grados de todos los nodos del grafo
     * de trabajo.
     * @return void
     * @exception Exception
     */

    public void grado_nodo() throws Exception
    {
	mostrar_grados_nodos(this.devolver_matriz());
    }

    /**
     * Método modificador (privado).
     * Muestra por la salida estándar los grados de todos los nodos del grafo
     * de trabajo.
     * @param G: matriz bidimensional que representa el grafo de trabajo actual.
     * @return void
     * @exception Exception
     */

    private void mostrar_grados_nodos(int [][] G) throws Exception
    {
	System.out.println("Grados del grafo");
	System.out.println("(vértice,grado)");

	Algoritmos A = new Algoritmos();
	
	int tama_G = G.length;

	System.out.print("(");

	for(int i=0; i < tama_G; ++i)
	    {
		System.out.print("("+i+","+A.grado_vertice(i,G)+")");
	    }

	System.out.println(")");
    }

    /**
     * Método modificador (privado).
     * Almacena en una variable de ámbito privada, los grados o valencia de
     * todos los nodos del grafo en orden creciente (0 ... n-1).
     * @return void
     * @exception Exception
     */

    private void array_grados() throws Exception
    {
	grados_interno = new ArrayList<Integer>();
	Algoritmos A = new Algoritmos();
	
	for(int i = 0; i < this.devolver_matriz().length; ++i)
	    grados_interno.add(A.grado_vertice(i,this.devolver_matriz()));
	
    }


    /**
     * Método modificador (sobrecarga - privado)
     * Almacena en una variable de ámbito privada, los grados o valencia de
     * todos los nodos del grafo en orden creciente (0 ... n-1).
     * @param G: matriz bidimensional de enteros que representa la estructura
     * interna del grafo.
     * @return void
     * @exception Exception
     */

    private void array_grados(int [][] G) throws Exception
    {
	grados_interno = new ArrayList<Integer>();
	Algoritmos A = new Algoritmos();
	
	for(int i = 0; i < G.length; ++i)
	    grados_interno.add(A.grado_vertice(i,G));

    }

    /**
     * Algoritmo de comprobación de aristas de corte - Método modificador.
     * Muestra por la salida estándar los pares de nodos cuya arista 
     * es de corte.
     * @return devolverá un tipo ArrayList<Arista> que contedrá las aristas
     * que formarán los puentes del grafo.
     * @exception Exception
     */

    public ArrayList<Arista> hallar_puente() throws Exception
    {
	System.out.println("Se procede a la búsqueda de puentes");
	Algoritmos A = new Algoritmos();

	return A.puentes(this.devolver_matriz());
	
    }

    /**
     * Algoritmo de comprobación de vértices de corte - Método modificador.
     * Muestra por la salida estándar los nodos de corte del grafo.
     * @return boolean: representa un valor lógico que indica si se ha encontrado
     * un punto de corte(true) o no(false) en el grafo.
     * @exception Exception
     */

    public boolean vertice_corte() throws Exception
    {
	System.out.println("Se procede a la búsqueda de vértice de corte");
	array_grados();

	int tama_G = this.devolver_matriz().length;
	int aux=0;
	boolean punto_corte=true;

	for(int i=0; i < tama_G; ++i)
	    {
		aux = grados_interno.get(i);
		if(this.n_aristas() - aux < (this.n_nodos() - 1))
		    punto_corte = false;
	    }

	return punto_corte;
    }


    /**
     * Algoritmo de comprobación de vértices de corte - Método modificador.
     * Muestra por la salida estándar los nodos de corte del grafo.
     * @return ArrayList<Integer> que contendrá los vértices de corte
     * del grafo.
     * @exception Exception
     */

    public ArrayList<Integer> hallar_corte() throws Exception
    {
	System.out.println("Se procede a la búsqueda de vértice de corte");
	array_grados();
        ArrayList<Integer> resultado = new ArrayList<Integer>();

	int tama_G = this.devolver_matriz().length;
	int aux=0;

	for(int i=0; i < tama_G; ++i)
	    {
		aux = grados_interno.get(i);
		if(this.n_aristas() - aux < (this.n_nodos() - 1))
                {
		    System.out.println("Vértice:"+i);
                    resultado.add(i);
                }
                
	    }
        
        return resultado;
    }

    /**
     * Algoritmo de ordenación topológica - Método modificador.
     * Muestra por la salida estándar los nodos del grafo siguiente un patrón
     * de ordenación topológica.
     * @return resultado que es un ArrayList donde se encuentra el recorrido en
     * orden topológico del grafo.
     * @exception Exception
     */

    public ArrayList<Integer> orden_topologico() throws Exception
    {
	Algoritmos A = new Algoritmos();
        ArrayList<Integer> resultado = new ArrayList<Integer>(A.ordenacion_topologica(this.devolver_matriz()));

	return resultado;
    }

    /**
     * Algoritmo de Bellman-Ford - Método modificador
     * Calcula los caminos de coste mínimo entre origen y todos los vértices 
     * del grafo G (en este caso origen es 0 y los costes pueden ser negativos).
     * @return Devuelve una estructura de tipo ArrayList cuyo contenido es de
     * tipo Arista. La estructura contendrá los caminos de coste mínimo desde
     * un nodo origen para todos los demás nodos.
     * @exception Exception
     */

    public ArrayList<Arista> Bellman_Ford(int origen) throws Exception
    {
	Algoritmos A = new Algoritmos();

	return A.Bellman_Ford(this.devolver_matriz(),origen);
    }
    
    private int [][] devolver_matriz_adyacencia(int [][] matriz_costes)
    {
        if(!adyacencia)
        {
            int tam_ = matriz_costes.length;
            int [][] resultado = new int [tam_][tam_];
            
            for(int i=0; i < tam_; i++)
                for(int j=0; j < tam_; j++)
                {
                    if(matriz_costes[i][j] != 0 && matriz_costes[i][j] < 100)
                        resultado[i][j] = 1;
                    else
                    {
                        if(matriz_costes[i][j] == 0 || matriz_costes[i][j] == 100)
                        {
                            resultado[i][j] = 0;
                        }
                    }
                }
            
            return  resultado;
        }
        else
            return null;
    }

}