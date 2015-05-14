/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphvisualx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Fichero Algoritmos.java
 * @author Moisés Gautier Gómez
 * @version 1.0 05/04/11
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
public class Algoritmos{

    /*
      Vector de valores booleanos que servirá para llevar las marcas de los
      nodos procesados. (Método Acíclico)
     */

    private boolean [] visitados; 

    /*
      Vector de valores enteros se utiliza para llegar la cuenta de los nodos 
      ancentros. (Método Acíclico)
     */

    private int [] ancestros; 

    /*
      Vector de booleanos que servirá para llevar las marcas de los nodos
      visitados. (Método Recorrido en profundidad)
     */

    private boolean [] marcados;

    /*
      Estructura interna de tipo FIFO que se emplea para almacenar los nodos
      recorridos en orden topológico en dicho método. (Método Orden Topológico)
     */

    private Stack<Integer> pila;

    /*
      Vector de booleanos que se emplea para llevar la cuenta de los nodos
      visitados del grafo. (Método Orden Topológico)
     */

    private boolean [] vertice_visitado;

    /*
      Vector de booleanos que se emplea para llevar la cuenta de los nodos
      visitados del grafo. (Método Hamilton)
     */

    private boolean [] visitado_hamilton;

    /*
      Estructura interna de tipo FIFO que se emplea para almacenar los nodos
      recorridos en el camino y luego poder mostrarlos en orden. 
      (Método Hamilton)
     */

    private Stack<Integer> pila_hamilton;

    /*
      Valor entero que representa el nodo origen sobre el comenzará el
      algoritmo de comprobación de caminos hamiltonianos a comprobar.
      (Método Hamilton)
     */

    private int origen_hamilton;

    /*
      Vector de enteros que se corresponde con los grados para cada nodo (en orden creciente, 0..n-1). (Método Euler)
     */

    private int [] grado_euler;

    /*
      Estructura interna de tipo FIFO que se emplea para almacenar los nodos
      recorridos en el camino y luego poder mostrarlos en orden. 
      (Método Euler)
     */

    private Stack<Integer> pila_euler;

    /*
      Estructura interna de tipo Map que se emplea para almacenar los nodos
      (clave - entero) junto a sus nodos adyacentes que conecta 
      (valor - ArrayList de elementos enteros).
      (Método Euler)
     */

    private HashMap<Integer,ArrayList<Integer>> hash_euler;

    /*
      Valor entero que representa el nodo de partida del camino euleriano (si 
      es que existe). (Método Euler)
     */

    private int v_euler=0;

    /*
      Variable de tipo boolean que se empleará para comprobar si existe 
      o no camino euleriano a través del procesamiento normal del algoritmo.
      (Método Euler)
     */

    private boolean encontrado_euler;

    /*
      Valor entero que se emplea para guardar una copia del nodo inicial de 
      partida en la búsqueda del ciclo euleriano en el grafo.
      (Método Euler)
     */

    private int vert_orig_euler;

    /*
      Variable booleana que se utiliza para realizar la comprobación sobre el
      tipo de grafo pasado como parámetro, es decir, si es dirigido (true) 
      o no (false). (Método Euler)
     */

    private boolean dirigido_euler;
    
    /*
      Variable booleana que se utiliza para realizar la comprobación sobre el
      tipo de grafo pasado como parámetro, es decir, si es adyacente (true) 
      o no (false).
     */

    private boolean grafo_adyacente;

    /*
     * Variable booleana que se utiliza para realizar la comprobación sobre
     * el tipo de grafo pasado como parámetro, es decir, si es dirigido (true)
     * o no (false).
     */
    
    private boolean grafo_dirigido;

    /*
      Estrucutra de tipo ArrayList cuyo contenido es del tipo definido Arista. 
      Este tipo definido Arista tiene como campos: vértice origen (.v_origen())
      , vértice destino (.v_destino()) y peso asociado o coste de 
      la arista (.coste()) (Método Kruskal)
    */

    private ArrayList<Arista> vector_kruskal;

    /*
      Estructura interna de tipo Map que se emplea para almacenar los nodos
      (clave - entero) junto a sus nodos adyacentes que conecta 
      (valor - ArrayList de enteros).
      (Método Puentes)
     */

    private HashMap<Integer,ArrayList<Integer>> hash_puentes;

    /*
      Estrucutra de tipo ArrayList con elementos entero, cuyo contenido son los
      nodos del grafo según se vaya realizando el recorrido del mismo
      especificado por el algoritmo de recorrido en profundidad.
      (Método Recorrido en profundidad - k_colores)
    */

    private ArrayList<Integer> rec_depth;  

    /*
      Estructura de tipo ArrayList con elementos entero, cuyo contenido son los
      nodos del grafo según se vaya realizando el recorrido del mismo
      especificado por el algoritmo de recorrido en anchura.
      (Método Recorrido en anchura)
    */

    private ArrayList<Integer> rec_breadth; 

    /*
      Estructura de tipo ArrayList, cuyo contenido son las
      aristas resultado del procesamiento del algoritmo Bellman-Ford.
      (Algoritmo de Bellman-Ford)
     */

    private ArrayList<Arista> vector_bell_ford;
    
    /*
     * Estructura de tipo HashMap, cuyo contenido son las aristas
     * resultado del procesamiento del algoritmo de Bellman-Ford, adjuntando
     * el dato del índice del nodo. Por lo tanto la dupla será en la forma
     * <numero_nodo,aristas_que_pertenecen>
     */

    private HashMap<Integer,Arista> vertices;
    
    /*
     * Se usa para devolver por valor el nodo origen desde el cuál se realiza
     * el recorrido del algoritmo: orden topológico.
     */
    
    private int nodo_origen_topo_;

    /**
     * Constructor nulo.
     */

    public Algoritmos() {}
    
    /**
     * Constructor
     * Se establece la variable interna de la clase para la adyacencia
     * del mismo.
     * @param adyacente parámetro que especifica si el grafo introducido es
     * de adyacencia o no.
     * @param dirigido parámetro que especifica si el grafo introducido es
     * de dirigido o no.
     */
    
    public Algoritmos(boolean adyacente, boolean dirigido)
    {
        grafo_adyacente = adyacente;
        grafo_dirigido = dirigido;
    }

    /**
     * Método observador (Algoritmo de Dijkstra).
     * Calcula los caminos de coste mínimo entre origen y todos los vértices del grafo G.
     * @param origen vértice desde el que se realiza la computación.
     * @param G matriz de costes asociada al grafo G.
     * @return Un vector de tamaño G.length con estos costes mínimos (fila 0 de la matriz) y un vector de tamaño G.length tal que vector[i] es el último vértice del camino origen a i (fila 1 de la matriz).
     * @exception Exception
     */

    public int[][] algo_Dijkstra(int origen, int [][] G) throws Exception
    {
	int tama_G=G.length;

	//Voy a devolver como resultado de la función la variable definida int [][] Costes_Vertices [2][G.length].
	//Donde la primera fila es el vector de costes obtenido al aplicar Dijkstra y la segunda fila es el vector.
	//obtenido del recorrido según se obtienen los caminos mas cortos a partir del origen.

	int [][] Costes_Vertices = new int[2][tama_G];

	boolean [] Visitado = new boolean[tama_G];
	int i;
	int v,w=0;
	int CosteMin, Owv;
	
	/* Marcamos como visitado el origen. */
	Visitado[origen] = true;

	for(v=0; v < tama_G; ++v)
	    {
		Costes_Vertices[0][v] = G[origen][v];

		/*Asignamos todos los costes asociados partiendo desde
		  el vertice origen. */

		Costes_Vertices[1][v] = origen;
		//El vector tiene como elementos al vertice origen.
	    }
        
	for(i=0; i < tama_G-1; ++i)
	    {
		/*Localizar vértice w no incluido en S con coste
		  mínimo desde origen. */
		CosteMin = Integer.MAX_VALUE;

		for(v=0; v < tama_G;++v)
                {
                    System.out.println("v: "+v+" Costes_Vertices[0][v]: "+Costes_Vertices[0][v]);
                    System.out.println("CosteMin: "+CosteMin);
                    
                    
                    if(!Visitado[v] && Costes_Vertices[0][v] < CosteMin)
                    {
                                CosteMin = Costes_Vertices[0][v];
                                w = v;
                    }
                    
                }

		Visitado[w] = true;

                
		for(v=0; v < tama_G;++v)
		    {
			Owv = Suma(Costes_Vertices[0][w],G[w][v]);

                        System.out.println("v: "+v+" Costes_Vertices[0][v]: "+Costes_Vertices[0][v]);
                        System.out.println("Owv: "+Owv);
                        
                        if(!Visitado[v] && Costes_Vertices[0][v] > Owv)
                        {
                             Costes_Vertices[0][v] = Owv;
                             Costes_Vertices[1][v] = w;
                        }                        
		    }
	    }
        

	return Costes_Vertices;
    }

    /**
     * Método observador (Algoritmo de Floyd).
     * Calcula los caminos de coste mínimo entre cada par de vértices del grafo G.
     * @param G matriz de costes asociada al grafo G.
     * @param camino valor booleano que especifica si se quiere mostrar o 
     * no el camino asociado a la computación de Floyd.
     * @return una matriz(int) de costes mínimos de tamaño NxN.
     * @exception Exception
     */

    public int[][] algo_Floyd(int [][] G, boolean camino) throws Exception
    {
	int i,j,k;
	int ikj=0; 
	int tama_G = G.length;
	int [][] Costes = new int[tama_G][tama_G];
	int [][] Vertices = new int[tama_G][tama_G];

        
	for(i=0; i < tama_G; ++i)
	    for(j=0; j < tama_G; ++j)
		{
		    Costes[i][j] = G[i][j];
		    Vertices[i][j] = -1;
		}

	for(i=0; i < tama_G; ++i)
	    Costes[i][i] = 0;

	for(k=0; k < tama_G; ++k)
	    for(i=0; i < tama_G; ++i)
		for(j=0; j < tama_G; ++j)
		    {
                        if(!grafo_adyacente)
                        {
                            ikj = Suma(Costes[i][k],Costes[k][j]);
			
			if(Costes[i][j] > ikj)
			    {
				Costes[i][j] = 0;
				Vertices[i][j] = k;
			    }
                        }
                        else
                        {
                            ikj = Suma(Costes[i][k],Costes[k][j]);
                            
                            if(Costes[i][j] > ikj)
                            {
                                System.out.println("i: "+i+" j: "+j+" k: "+k);
                                Vertices[i][j] = k;
                            }
                        }
		    }

	/* Si se selecciono camino se mostrará para el caso [0,2]. */
	if(camino)
	    camino_Floyd(0,2,Vertices);

	return Costes;
    }

    /**
     * Método observador (Algoritmo de Warshall).
     * Determina si hay un camino entre cada par de vértices del grafo G.
     * @param G matriz de adyacencia asociada al grafo G.
     * @return una matriz entera de tamaño NxN, tal que matriz[i][j] == true(1)
     * si existe al menos un camino entre el vértice i y el vértice j, 
     * y A[i][j] == false(0) si no existe ningún camino entre los 
     * vértices i y j.
     * @exception Exception
     */

    public int [][] algo_Warshall(int [][] G, boolean adyacente) throws Exception
    {
	int i,j,k;
	int tama_G = G.length;
	int [][] resultado = new int[tama_G][tama_G];


	/* Inicializar resultado con la matriz de adyacencia de G. */

        if(adyacente)
        {
	for(i=0; i < tama_G; ++i)
	    for(j=0; j < tama_G; ++j)
		{
		    if(G[i][j] == 1)
			resultado[i][j] = 1;
		    else
			resultado[i][j] = 0;
		}
        }
        else
        {
        for(i=0; i < tama_G; ++i)
	    for(j=0; j < tama_G; ++j)
		{
		    if(G[i][j] != 0 && G[i][j] < 100)
			resultado[i][j] = 1;
		    else
			resultado[i][j] = 0;
		}    
        }
        
	/* Comprobamos camino entre cada par de vértices i, j a través
	   de cada vértice k. */

	for(k=0; k < tama_G; ++k)
	    for(i=0; i < tama_G; ++i)
		for(j=0; j < tama_G; ++j)
		    if(resultado[i][j] != 1)
			resultado[i][j] = resultado[i][k] * resultado[k][j];

	return resultado;
    }

    /**
     * Método observador (Algoritmo de Prim).
     * Devuelve en un vector el conjunto de aristas que forman un árbol
     * generador de coste mínimo de un grafo conexo.
     * @param G matriz de costes asociada al grafo G.
     * @return devuelve un ArrayList con el conjunto de aristas que forman 
     * un árbol generador de coste mínimo del grafo conexo G.
     * @exception Exception
     */

    public ArrayList<Arista> algo_Prim(int [][] G) throws Exception
    {
	int tama_G = G.length;
	boolean [] U = new boolean [tama_G];
	
	int j,k; //Vértices.
	int i,destino=0;
	int CosteMin;
	Arista a = new Arista();

	ArrayList<Arista> conj_aristas = new ArrayList<Arista>(Collections.nCopies(tama_G-1,a));


	U[0] = true;
	

	/* Buscar una arista a = (u,v) de coste mínimo, tal que u está ya
	   en el conjunto U y v no está en U.
	   (a = [conj_aristas[0][x],conj_aristas[1][x]], siendo x <= N)
	*/
	for(i=0; i < tama_G-1; ++i)
	    {
		CosteMin = Integer.MAX_VALUE;

		for(j=0; j < tama_G; ++j)
		    for(k=0; k < tama_G; ++k)
			if(U[j] && !U[k])
			    if(G[j][k] <= CosteMin)
				{
				    CosteMin = G[j][k];
				    a = new Arista();
				    a.v_origen(j); //Vértice origen.
				    a.v_destino(k); //Vértice destino.
				}

		conj_aristas.set(i, a);
		U[a.v_destino()] = true;
	    }

	return conj_aristas;
    }

    /**
     * Función auxiliar que busca el pivote de la estructura que pasemos.
     * @param x valor entero que representa la posición inicial de la 
     * estructura interna.
     * @param y valor entero que representa el numero de elementos total
     * de la estructura. (Indice final de la estructura)
     * @return devuelve el nuevo valor de y que se ha intercambiado internamente
     * con otras variables.
     */

    private int particion (int x, int y)
    {
	int i = x-1, j = y;
	Arista v = new Arista();
	v.v_origen(vector_kruskal.get(y).v_origen());
	v.v_destino(vector_kruskal.get(y).v_destino());
	v.coste(vector_kruskal.get(y).coste());

	for(;;)
	    {
		while(vector_kruskal.get(++i).coste() < v.coste());

		while(v.coste() < vector_kruskal.get(--j).coste())
		    if(j == x)
			break;

		if(i >= j)
		    break;

		cambio(i,j);
	    }

	cambio(i,y);
	return i;
    }

    /**
     * Método modificador. (privado)
     * El método intercambia los valores de las posiciones vertice_i y
     * vertice_j de la estructura interna que almacena los nodos adyacentes
     * por cada uno de los nodos del grafo de trabajo.
     * @param vertice_i valor entero que representa un vértice de la arista
     * (u,v) (u).
     * @param vertice_j valor entero que representa el otro vértice de la 
     * arista (u,v) (v).
     */

    private void cambio(int vertice_i, int vertice_j)
    {
	Arista aux = new Arista();
	
	aux.v_origen(vector_kruskal.get(vertice_i).v_origen());
	aux.v_destino(vector_kruskal.get(vertice_i).v_destino());
	aux.coste(vector_kruskal.get(vertice_i).coste());

	vector_kruskal.set(vertice_i,vector_kruskal.get(vertice_j));
	vector_kruskal.set(vertice_j,aux);

    }

    /**
     * Método observador (Algoritmo de Quicksort - Ordenación Rápida)
     * Supongamos que un elemento arbitrario p de los n índices o elementos 
     * que tenemos que ordenar. Quicksort separa los n-1 restantes elementos 
     * en dos montones: un primer montón inferior que contiene todos los 
     * elementos que aparecen antes de p en orden creciente (son menores que p)
     * y otro montón superior que contiene todos los elementos que aparecen 
     * después de p en orden creciente (son mayores que p). 
     * Los montones inferior y superior denotan las posiciones de los 
     * elementos del vector, colocando un elemento central o pivote que 
     * diferencia ambos montones.
     * @param x índice del primer elemento de la estructura.
     * @param y índide del último elemento de la estructura.
     */


    private void Quicksort(int x, int y)
    {
	if(y <= x)
	    return;

	int i = particion(x,y);

	Quicksort(x,i-1);
	Quicksort(i+1,y);
    }

 
   /**
     * Método observador (Algoritmo de Kruskal)
     * Devuelve en un ArrayList el conjunto de aristas que forman un árbol 
     * generador de coste mínimo de un grafo conexo G.
     * @param G matriz de costes del grafo conexo G.
     * @param n valor entero que representa el número de nodos del grafo.
     * @param m valor entero que representa el número de aristas del grafo.
     * @return devuelve un ArrayList que será el conjunto de aristas que 
     * forman un árbol generador de coste mínimo de un grafo conexo G.
     */
 

    public ArrayList algo_Kruskal(int [][] G, int n, int m)
    {
	int i,j; //Indices.
	int p,q; //Indices posición nodos.
	int cont_aristas = 0;
	int tama_G = G.length;

	Arista a;
	Particion part = new Particion(n);
	vector_kruskal = new ArrayList<Arista>(m);
	ArrayList<Arista> F = new ArrayList<Arista>(m);
	int [][] aux = new int [tama_G][tama_G];

	for(i=0; i < tama_G; ++i)
	    for(j=0; j < tama_G; ++j)
		{
		    /* 100 es el valor más grande posible
		       aunque siempre se puede poner el rango
		       máximo de un Integer */

		    if(G[i][j] != 0 && G[i][j] < 100)
			aux[i][j] = G[i][j];
		    else
			aux[i][j] = 0;
		}

	for(i=0; i < n; ++i)
	    {
		for(j=0; j < n; ++j)
		    {
			if(aux[i][j] != 0)
			    {
				a = new Arista();
				a.v_origen(i);
				a.v_destino(j);
				a.coste(G[i][j]);
				vector_kruskal.add(cont_aristas,a);
				cont_aristas++;
				
				//Eliminamos la arista del grafo no dirigido.
				aux[i][j] = 0;
				aux[j][i] = 0;
			    }
		    }
	    }

	Quicksort(0,m-1);
	/* Obtenemos el conjunto de aristas ordenadas de menor a mayor para 
	   empezar a aplicar el principio del algoritmo de Kruskal */


	i=0;
	int ab=0;
	Arista e;
	while(i < tama_G-1)
	    {
		e = new Arista();
		e.v_origen(vector_kruskal.get(ab).v_origen());
		e.v_destino(vector_kruskal.get(ab).v_destino());
		e.coste(vector_kruskal.get(ab).coste());

		p = part.Encontrar(e.v_origen());
		q = part.Encontrar(e.v_destino());

		if(p != q)
		    {
			part.Union(p,q);
			// Incluir e en F.
			F.add(i,e);
			++i;
		    }
		++ab;
	    }
		
	return F;
	
    }

   /**
    * Procedimiento de Bellman-Ford (llamada al algoritmo principal)
    * @param G matriz de costes del grafo conexo G.
    * @param x valor entero que representa el nodo origen.
    * @return devuelve un ArrayList de Arista que representa el procesamiento
    * del algoritmo sobre el grafo.
    */

    public ArrayList Bellman_Ford (int [][] G, int x)
    {
	int i;
	
        ArrayList<Arista> resultado = new ArrayList<Arista>(G.length);
        
	if(algo_Bellman_Ford(G,x) == true)
	    {
		for(i=0; i < vertices.size(); ++i)
		    {
                        resultado.add(vertices.get(i));
		    }
	    }
	
        return resultado;
        
    }


   /**
     * Método observador (Algoritmo de Bellman-Ford)
     * Devuelve en un valor booelano que servirá para saber
     * si el grafo tiene un camino posible a través de dicho
     * algoritmo.
     * @param G matriz de costes del grafo conexo G.
     * @param origen valor entero que representa el vértice de partida
     * para el algoritmo.
     * @return devuelve un valor booleano que servirá para comprobar
     * si se ha encontrado un posible ciclo de pesos negativos desde
     * el vértice origen (true) o no (false).
     */

    private boolean algo_Bellman_Ford(int [][] G, int origen)
    {

	int i,j;

	ArrayList<Arista> edges = new ArrayList<Arista>();
	Arista edge = new Arista();

	vertices = new HashMap<Integer,Arista>();

	/* Por defecto se colocará a 0
	   el valor del nodo o vértice
	   origen desde el que parte
	   el procesamiento del 
	   algoritmo.
	*/
	
	for(i=0; i < G.length; ++i)
	    {
		Arista a = new Arista();
		a.coste(1000); 
		/*
		  Representamos el coste del vértice
		  usando el campo coste de la clase
		  Arista.
		*/

		if(i == origen)
		    {
			a.coste(0);
			vertices.put(i,a);
		    }
		else
		    vertices.put(i,a);
	    }

	for(i=0; i < G.length; ++i)
	    {
		for(j=0; j < G.length; ++j)
		    {
			if(G[i][j] != 0 && G[i][j] < 100)
			    {
				edge = new Arista();
				edge.v_origen(i);
				edge.v_destino(j);
				edge.coste(G[i][j]);
				edges.add(edge);
                                System.out.println("Arista añadida: ["+edge.v_origen()+","+edge.v_destino()+"] = "+edge.coste());
			    }
		    }
	    }

	int suma=0;
	int posicion=0;
	int pos_u=0,pos_v=0;

	for(i=0; i < vertices.size(); ++i)
	    {
		for(j=0; j < edges.size(); ++j)
		    {
			
			suma = vertices.get(edges.get(j).v_origen()).coste() + edges.get(j).coste();
                        
                        System.out.println("edges.get(j).v_origen(): "+edges.get(j).v_origen()+" vertices.get(antes).coste()"+vertices.get(edges.get(j).v_origen()).coste());
                        System.out.println("edges.get(j).coste() "+edges.get(j).coste());
                        System.out.println("edges.get(j).v_destino(): "+edges.get(j).v_destino()+ "vertices.get(antes).coste()"+vertices.get(edges.get(j).v_destino()).coste());
                        System.out.println("Suma: "+suma);
                        
			if(suma < vertices.get(edges.get(j).v_destino()).coste())
			    {
				/* 
				   Significa que el peso del vértice es mayor
				   que el peso de la arista de llegada más
				   el peso del vértice origen.
				*/

				pos_u = edges.get(j).v_origen();
				pos_v = edges.get(j).v_destino();
				
				if(pos_v != origen)
				    {
                                        System.out.println("pos_u: "+pos_u+" pos_v: "+pos_v);
					vertices.get(pos_v).v_origen(pos_u);
					vertices.get(pos_v).v_destino(pos_v);
					vertices.get(pos_v).coste(suma);
				    }
			    }
			    
		    }
	    }

	for(i=0; i < edges.size(); ++i)
	    {
		suma = vertices.get(edges.get(i).v_origen()).coste() + vertices.get(edges.get(i).v_destino()).coste();
                
                System.out.println("vertices.get(edges.get(i).v_origen()).coste(): "+vertices.get(edges.get(i).v_origen()).coste());
                System.out.println("vertices.get(edges.get(i).v_destino()).coste(): "+vertices.get(edges.get(i).v_destino()).coste());
                System.out.println("suma: "+suma);
                
		if(vertices.get(edges.get(i).v_destino()).coste() <= suma)
                {
		    return true;
                }
	    }

	return false;

    }

    /**
     * Método observador
     * Muestra por la salida estándar el recorrido en profundidad del grafo G.
     * @param G matriz de adyacencia del grafo conexo.
     * @return devuelve un ArrayList de tipo entero que contiene el recorrido
     * en profundidad del grafo.
     */

    public ArrayList<Integer> recorrido_profundidad(int [][] G) 
    {
	int tama_G = G.length;
	
	marcados = new boolean[tama_G];
	int [][] rec = G;
	rec_depth = new ArrayList<Integer>();

	int i;

	System.out.println("REC_PROFUNDIDAD");
	for(i=0; i < tama_G; ++i)
	    if(!(marcados[i])) //No visitado
		recorrido_profundidadRec(i,rec);
	System.out.println("");
        
        return rec_depth;
    }

    /**
     * Método observador privado (Llamada recursiva para el método 
     * recorrido_profundidad()).
     * Muestra por la salida estándar el recorrido en profundidad del grafo G.
     * @param vertice valor entero que representa un nodo del grafo.
     * @param G matriz de adyacencia del grafo conexo.
     */

    private void recorrido_profundidadRec(int vertice, int [][] G) 
    {
	int w;
	int tama_G = G.length;
	int [][] rec = G;
	marcados[vertice] = true;
	System.out.print(vertice+" "); // Procesar vertice
	rec_depth.add(vertice);

	for(w=0; w < tama_G; ++w)
	    if(rec[vertice][w] != 0 && marcados[w] == false)
		recorrido_profundidadRec(w,rec);
    }

    /**
     * Método observador.
     * Muestra por la salida estándar el recorrido en anchura del grafo G.
     * @param G matriz de adyacencia del grafo conexo.
     * @return devuelve un ArrayList de tipo entero que contiene el recorrido
     * en anchura del grafo. 
     */

    public ArrayList<Integer> recorrido_anchura(int [][] G) 
    {
	int tama_G = G.length;

	boolean [] marcas = new boolean [tama_G];
	int i,v,w; //Vértices.
        
        System.out.println("RECORRIDO_ANCHURA");
        System.out.print("(");

	LinkedList<Integer> Cola = new LinkedList();
	rec_breadth = new ArrayList<Integer>();

	Arrays.fill(marcas,0,tama_G,false);
	
	for(i=0; i < tama_G; ++i)
	    if(!marcas[i]) //NO visitado
		{
		    Cola.add(i); //Frente de la Cola
                    System.out.println("Elemento i: "+i);
		    
		    do{
			v = Cola.getFirst();
                        System.out.println("Elemento v: "+v);
                        System.out.println("Elemento de la Cola (primero) que se borra: "+Cola.getFirst());
			Cola.removeFirst(); //Eliminamos el elemento.
			if(!marcas[v]) //NO visitado.
			    {
				/* Marcar y procesar. */
				marcas[v] = true; //Visitado.
				System.out.print("Elto: "+v);
				rec_breadth.add(v);
				
				/* Encolar los adyacentes no visitados. */

				for(w=0; w < tama_G; ++w)
				    if(G[v][w] != 0  && marcas[w] == false)
                                    {
                                        System.out.println("Elemento w: "+w);
					Cola.add(w);
                                    }
			    }
		    }while(!(Cola.isEmpty()));
		}
	System.out.print(")");
        System.out.println("");
        return rec_breadth;
    }



    /**
     * Método observador. (para grafos dirigidos)
     * Comprueba si un grafo dirigido es aciclico o no.
     * @param G array multidimensional (matriz) que contiene la matriz
     * de adyacencia del grafo G asociado.
     * @return devuelve true o false (verdadero o falso) si se cumple
     * que sea o no acíclico.
     */

    public boolean AciclicoGD(int [][] G)
    {
	
	int tama_G = G.length;
	int i;
	boolean aciclico = true; 
	// El grafo es acíclico hasta que se demuestre lo contrario.
	visitados = new boolean[tama_G];
	ancestros = new int[tama_G];
	// Para llevar la cuenta de los ancestros.

	// Al comienzo no hay ningún vértice visitado.
	Arrays.fill(visitados, false);

	/* Vamos iterando de componente en componente conexa
	   para sacar el árbol de expansión */

	for(i=0; i < tama_G && aciclico == true; ++i)
	    {
		if(!visitados[i])
		    aciclico = AciclicoGDRec(i,G,0);
	    }
	
	return aciclico;
    }

    /**
     * Función privada de la clase que sirve para iterar sobre la estructura
     * de aristas del grafo.
     * @param vertice vértice inicial desde el que se realiza la búsqueda 
     * en el grafo.
     * @param G array multidimensional (matriz) que contiene la matriz
     * de adyacencia del grafo G asociado.
     * @param num_ancestros número de vértices visitados anteriormente desde 
     * el vértice de partida.
     * @return devuelve true o false (verdadero o falso) dependiendo de si 
     * encuentra un ciclo o no en la estructura.
     */

    private boolean AciclicoGDRec(int vertice, int [][] G, int num_ancestros) 
    {
	int w=0;
	// Para movernos por los vértices adyacentes.

	int i;
	// Para movernos por los ancestros.

	visitados[vertice] = true;
	// Acabamos de visitar dicho vértice.

	ancestros[num_ancestros] = vertice;
	// Guardamos el ancestro.
	

	/* Ahora recorremos todos los vértices y miramos cuales son
	   adyacentes y de ellos los que no hemos visitado. Con estos
	   vértices seguimos la búsqueda en profundidad. */

	for(w=0; w < G[vertice][w]; ++w)
	    {
		if(G[vertice][w] != 0)
		    {
			if(!visitados[w])
			    return (AciclicoGDRec(w,G,num_ancestros++));
			else
			    {
				// Si el vértice ya se ha visitado.
				for(i=0; i <= num_ancestros; ++i)
				    {
					// Lo encontramos hay una arista.
					if(ancestros[i] == w)
					    return (false); //Hay un ciclo.
					
				    }
			    }
		    }
	    }
	
	return (true); // Si llegamos aquí no se habrá encontrado ningún ciclo.
    }

    /**
     * Método observador. (para grafos no dirigidos)
     * Comprueba si un grafo no dirigido es aciclico o no
     * @param G array multidimensional (matriz) que contiene la matriz
     * de adyacencia del grafo G asociado.
     * @return devuelve true o false (verdadero o falso) si se cumple
     * que sea o no acíclico.
     */

    public boolean AciclicoGND(int [][] G) 
    {
	int i;
	int tama_G = G.length;
	boolean aciclico = true; 
	// El grafo es acíclico hasta que se demuestre lo contrario.

	visitados = new boolean [tama_G];

	// Al comienzo no hay ningún vértice visitado.
	for(i=0; i < tama_G; i++)
	    visitados[i] = false;

	
	/* Vamos recorriendo de una en una las componentes conexas para
	   extraer el árbol de expansión. */

	for(i=0; i < tama_G && aciclico == true; ++i)
	    {
		if(!visitados[i])
		    aciclico = AciclicoGNDRec(i,G,i);
	    }

	return (aciclico);

    }
    
    /**
     * Función privada de la clase que sirve para iterar sobre la estructura
     * de aristas del grafo.
     * @param vertice vértice inicial desde el que se realiza la búsqueda 
     * en el grafo.
     * @param G array multidimensional (matriz) que contiene la matriz
     * de adyacencia del grafo G asociado.
     * @param v_ultimo ultimo vértice visitado anteriormente desde el 
     * vértice de partida.
     * @return devuelve true o false (verdadero o falso) dependiendo de si 
     * encuentra un ciclo o no en la estructura.
     */

    private boolean AciclicoGNDRec(int vertice, int [][] G, int v_ultimo)
    {
	int w=0;
	// Para movernos por los vértices adyacentes.

	int tama_G = G.length;

	visitados[vertice] = true;
	// Acabamos de visitar dicho vértice.

	/* Ahora recorremos todos los vértices y miramos cuales son
	   adyacentes y de ellos los que no hemos visitado. Con estos
	   vértices seguimos la búsqueda en profundidad.*/

	for(w=0; w < tama_G; ++w)
	    {
		if(G[vertice][w] != 0) //Existe arista.
		    {
			if(!visitados[w])
			    return (AciclicoGNDRec(w,G,vertice));
			else
			    {
				if(v_ultimo != w) // Sino es el "padre"
				    return (false); // entonces hay un ciclo.
			    }

		    }
	    }

	return (true); // Si llegamos aquí no se habrá encontrado ningún ciclo.

    }


    /**
     * Función privada de la clase que sirve para insertar en la 
     * salida estándar el recorrido que hace el algoritmo desde un nodo o 
     * vértice origen hasta haber recorrido todo el grafo. 
     * (Algoritmo de Dijkstra)
     * @param v_origen vértice inicial del recorrido.
     * @param i vértice actual sobre el que nos encontramos.
     * @param camino array que contiene los vértices intermedios para cada.
     * índice del array que se corresponde con cada nodo del grafo.
     */

    static void camino_Dijkstra(int v_origen, int i, int [] camino)
    {
	if(camino[i] != v_origen)
	    {
		camino_Dijkstra(v_origen,camino[i],camino);
		System.out.print("Camino:"+camino[i]);
	    }
    }

    /**
     * Función privada de la clase que sirve para insertar en la 
     * salida estándar el recorrido que hace el algoritmo, sobre el grafo,
     * entre dos nodos o vértices. (Algoritmo de Floyd)
     * @param v_i vértice inicial del recorrido.
     * @param v_j vértice extremo del recorrido.
     * @param camino array multidimensional (matriz) que contiene los vértices 
     * intermedios para cada índice del array que se corresponde con cada 
     * nodo del grafo.
     */

    static void camino_Floyd(int v_i, int v_j, int [][] camino)
    {
	int vertice;

	vertice = camino[v_i][v_j];
	if(vertice != -1)
	    {
		camino_Floyd(v_i,vertice,camino);
		System.out.print("Camino:"+vertice);
		camino_Floyd(vertice,v_j,camino);
	    }
    }

    /**
     * Función privada de la clase que sirve obtener el minimo de los dos 
     * enteros.
     * @param x valor entero.
     * @param y valor entero.
     * @return devuelve el menor de x e y.
     */

    static int Minimo(int x, int y)
    {
	if(x < y)
	    return x;
	else
	    return y;
    }

    /**
     * Función privada de la clase que sirve para sumar dos valores enteros.
     * @param x valor entero que es un operando.
     * @param y valor entero que es un operando.
     * @return devuelve la suma de x e y.
     */

    static int Suma(int x, int y)
    {
	return x+y;
    }    

    /**
     * Método observador (Camino Hamiltoniano)
     * Un grafo dirigido tendrá un camino Hamiltoniano 
     * (será grafo hamiltoniano) si existe un vértice que partiendo desde él 
     * se pase por cada vértice restante del grafo, una sola vez por nodo, y 
     * volviendo exactamente al nodo de partida.
     * @param G matriz bidimensional de enteros que representa al grafo
     * de trabajo.
     * @param v valor entero que representa el nodo de partida para el 
     * camino.
     * @param w valor entero que representa el nodo anterior visitado.
     */

    public void algo_Hamilton(int [][] G, int v, int w)
    {
	int tam_G = G.length;
	visitado_hamilton = new boolean[tam_G];
	Arrays.fill(visitado_hamilton,false);
	pila_hamilton = new Stack<Integer>();
	origen_hamilton = v;

	boolean encontrado = algo_HamiltonRec(v,w,G,tam_G);

	if(encontrado)
	    {
		Stack<Integer> salida_hamilton = new Stack<Integer>();
		
		while(!pila_hamilton.empty())
		    {
			salida_hamilton.push(pila_hamilton.peek());
			pila_hamilton.pop();
		    }

		System.out.print("(");
		while(!salida_hamilton.empty())
		    {
			System.out.print(" "+salida_hamilton.peek());
			salida_hamilton.pop();
		    }
		System.out.println(" )");
	
	    }
	else
	    System.out.println("No hay camino hamiltoniano para el grafo");
    }

    /**
     * Método observador. (privado)
     * Método que realiza el cálculo recursivo sobre los nodos necesarios
     * del grafo.
     * @param v valor entero que representa el nodo actual. 
     * @param w valor entero que representa el nodo anterior procesado.
     * @param G matriz bidimensional de enteros que representa el grafo de 
     * trabajo actual.
     * @param d valor entero que representa el número de nodos del grafo.
     */

    private boolean algo_HamiltonRec(int v, int w, int [][] G, int d)
    {
	int auxiliar = 0;

	visitado_hamilton[v] = true;
	pila_hamilton.push(v); 
	//Tengo esta pila para luego rehacer el recorrido.

	for(int j=0; j < visitado_hamilton.length; ++j)
	    if(visitado_hamilton[j])
		auxiliar++;

	if(auxiliar == visitado_hamilton.length)
	    if(G[v][origen_hamilton] == 1 && d-1 == 0)
		{
		    pila_hamilton.push(origen_hamilton);
		    return true;
		}



	for(int i = 0; i < G.length; ++i)
	    if(G[v][i] != 0 && !visitado_hamilton[i])
		if(algo_HamiltonRec(i,v,G,d-1))
		    return true;

	pila_hamilton.pop();
	visitado_hamilton[v] = false;
	return false;
    }
	
    /**
     * Método observador (Número cromático del grafo)
     * @param G matriz bidimensional de enteros que representa al grafo de
     * trabajo actual.
     * @return int que es el número cromático del grafo.
     */

    public int k_colores(int [][] G)
    {
	int tama_G = G.length;
	int elto=0, original=0;
	int [] color = new int [tama_G];
	Arrays.fill(color,0); //Rellenamos con el color vacío todo el vector.
	int i;
	boolean salto=false;

        if(G.length == 0)
        {
            return 0;
        }
        
        if(G.length == 1)
        {
            return 1;
        }
                
        if(G.length == 2)
        {
            return 2;
        }
        else
        {
	HashMap<Integer,ArrayList<Integer>>  v_ady = new HashMap<Integer,ArrayList<Integer>>();
	ArrayList<Integer> ady;


	for(i=0; i < tama_G; ++i)
	    {
		ady = new ArrayList<Integer>();

		for(int j=0; j < tama_G; ++j)
		    {
			if(G[i][j] != 0)
			    {
				ady.add(j);
			    }
		    }

		v_ady.put(i,ady);
	    }

	i=0;
	int z=0;
	while(i < tama_G)
	    {
		z = rec_depth.get(i);
		color[z] += 1;
		original = color[z];

		for(int j=0; j < v_ady.get(z).size() && !salto; ++j)
		    {
			elto = v_ady.get(z).get(j);
			if(color[elto] == color[z])
			    {
				color[z] += 1;
				salto = true;
			    }
		    }

       
		if(original == color[z])
		    i++;
		else
		    i=0;
	    } 

	elto = color[0];
	
	for(i=0; i < tama_G; ++i)
	    if(color[i] > elto)
		elto = color[i];

	return elto;

        }
	}

    /**
     * Método modificador
     * Método que halla las aristas de corte (puentes) de un grafo.
     * @param G matriz bidimensional de enteros que representa el grafo de
     * trabajo actual.
     * @return devuelve un tipo ArrayList<Arista> que contendrá las aristas
     * que son puentes del grafo.
     */

    public ArrayList<Arista> puentes(int [][] G)
    {
	int tama_G = G.length;
	hash_puentes = new HashMap<Integer,ArrayList<Integer>>();
	ArrayList<Integer> array_puentes;
	ArrayList<Arista> bridge = new ArrayList<Arista>();
        ArrayList<Arista> resultado = new ArrayList<Arista>();
	Arista I_J;

	grado_vector(G);
	boolean siguiente;

	for(int i=0; i < tama_G; ++i)
	    {
		array_puentes = new ArrayList<Integer>();
		
		for(int j=0; j < tama_G; ++j)
		    {
			if(G[i][j] != 0)
			    array_puentes.add(j);
		    }

		hash_puentes.put(i,array_puentes);

	    }
	
	
	for(int i=0; i < tama_G; ++i)
	    for(int j=0; j < hash_puentes.get(i).size(); ++j)
		{

		    I_J = new Arista();
		    siguiente = true;

		    if(!bridge.isEmpty())
			{
			    if(i == bridge.get(0).v_destino() && hash_puentes.get(i).get(j) == bridge.get(0).v_origen()) 
				{
				    siguiente = false;
				    bridge.remove(0);
				}
			
			}

		    if(siguiente)
			if(puentes_rec(i,hash_puentes.get(i).get(j),tama_G))
			    {
				
                                Arista auxiliar = new Arista();
                                auxiliar.v_origen(i);
                                auxiliar.v_destino(hash_puentes.get(i).get(j));
                                auxiliar.coste(G[i][hash_puentes.get(i).get(j)]);
                                resultado.add(auxiliar);
                                
				I_J.v_origen(i);
				I_J.v_destino(hash_puentes.get(i).get(j));
				I_J.coste(0);
				bridge.add(I_J);
                                
			    }
                    
		}	

        
        return resultado;
    }

    /**
     * Método observador. (privado)
     * Método recursivo que ayuda a la ejecución de la llamada principal de 
     * puentes. Comprueba si existe un puente para la arista de entrada como
     * parámetro formal formada por sus vértices.
     * @param u: valor entero que representa un extremo de la arista.
     * @param v: valor entero que representa el otro extremo de la arista.
     * @param tama_G: valor entero que representa el número de nodos del grafo.
     * @return boolean: devolverá true o false dependiendo de si la arista
     * es una arista de corte (puente) o no.
     */

    private boolean puentes_rec(int u, int v, int tama_G)
    {
	int pos_valencia=0;
	boolean camino=false;
	boolean fin=false;


	int i=u;
	int ant_u=u;
	int ant_v=v; //Almacenamos los vértices origen.

	ArrayList<Integer> vertice_i = new ArrayList<Integer>();
	ArrayList<Integer> vertice_j = new ArrayList<Integer>();

	while(!fin)
	    {
		/* Primero insertamos los nodos adyacentes de vertice_i (u)
		   y luego hacemos lo mismo para los nodos adyacentes de
		   vertice_j (v). Por ello cuando cambiemos los valores serán
		   iteraciones pares (vertice_i) o impares (vertice_j).
		 */

		if(pos_valencia % 2 == 0)
		    {

			if(!vertice_i.isEmpty())
			    {
				ant_u = ant_v;
				i = vertice_i.get(0);
				vertice_i.remove(0);


				for(int j=0; j < hash_puentes.get(i).size(); ++j)
				    {
					/* En la primera estructura metemos 
					   los adyacentes del nodo u de la 
					   arista (u,v) */

					if(hash_puentes.get(i).get(j) != ant_u)
					    vertice_i.add(hash_puentes.get(i).get(j));
				    }
			    }
			else{

			    if(hash_puentes.get(i).size() == 1 && hash_puentes.get(i).get(0) == v)
				{
				    fin = true;
				    camino = true;
				}

			for(int j=0; j < hash_puentes.get(i).size(); ++j)
			    {
				/* En la primera estructura metemos los 
				   adyacentes del nodo u de la arista (u,v) */
	
				if(hash_puentes.get(i).get(j) != v)
				    vertice_i.add(hash_puentes.get(i).get(j));
			    }}


		    }

		if(pos_valencia % 2 != 0)
		    {
			if(!vertice_j.isEmpty())
			    {
				ant_v = ant_u;
				v = vertice_j.get(0);
				vertice_j.remove(0);
				
				for(int j=0; j < hash_puentes.get(v).size(); ++j)
				    {
					/* En la segunda estructura metemos 
					   los adyacentes del nodo v de la 
					   arista (u,v) */
		
					if(hash_puentes.get(v).get(j) != ant_v)			    
					    vertice_j.add(hash_puentes.get(v).get(j));
					
				    }
				
			    }
			else
			    {


				if(hash_puentes.get(v).size() == 1 && hash_puentes.get(v).get(0) == u)
				    {
					fin = true;
					camino = true;
				    }

				    
				for(int j=0; j < hash_puentes.get(v).size(); ++j)
				    {
					/* En la segunda estructura metemos 
					   los adyacentes del nodo
					   v de la arista (u,v)*/
					
					if(hash_puentes.get(v).get(j) != u)			    
					    vertice_j.add(hash_puentes.get(v).get(j));
					
				    }
			    }
		    }
		
		for(int j=0; j < vertice_i.size() && !camino; ++j)
		    for(int k=0; k < vertice_j.size() && !camino; ++k)
			{
		
			    if(vertice_i.get(j) == vertice_j.get(k))
				{
				    /* Al llegar a este caso significa
				       que se ha recorrido todo los nodos
				       adyacentes posibles y no se ha hallado
				       ningún camino auxiliar que conecte
				       u y v. Por tanto (u,v) es una arista
				       de corte.
				     */

				    camino = false;
				    fin = true;
				}
			}
	
		pos_valencia++;

		if(vertice_i.size() == 2)
		    if(vertice_i.get(0) == vertice_i.get(1))
			{
			    /* Al llegar a este caso significa
			       que se ha encontrado un camino 
			       secundario para la arista (u,v),
			       que conectaría los nodos u y v */

			    fin = true;
			    camino = true;
			}

		if(vertice_j.size() == 2)
		    if(vertice_j.get(0) == vertice_j.get(1))
			{
			    /* Al llegar a este caso significa
			       que se ha encontrado un camino 
			       secundario para la arista (u,v),
			       que conectaría los nodos u y v */

			    fin = true;
			    camino = true;
			}
	    }
		return camino;
		
    }


    /**
     * Método modificador
     * Se muestra por el flujo de salida estándar el contenido del camino
     * euleriano del grafo (si es que existe). 
     * @return Devuelve una estructura ArrayList cuyo contenido es de tipo
     * entero donde se encuentra un camino euleriano posible, si es que el 
     * grafo posee algún camino.
     */
    
    public ArrayList<Integer> mostrar_euler()
    {
	Stack<Integer> Intercambio = new Stack<Integer>();
        ArrayList<Integer> resultado = new ArrayList<Integer>();

	if(!encontrado_euler)
	    return null;

	while(tour_euler(v_euler) == v_euler && !pila_euler.empty())
	    {
		/* Si hay un camino que empieza y termina en el mismo 
		   nodo y además la pila de elementos o nodos aún 
		   no esta vacía */

		v_euler = pila_euler.pop();
		Intercambio.push(v_euler);

		/* Si no es dirigido se puede mostrar el contenido de
		   la pila sin problema porque se podrá verificar en ambos
		   sentidos (incidente o adyacente), pero por el contrario 
		   para grafos dirigidos debe de mostrarse tal cual sea la 
		   arista y por tanto el motivo del intercambio de la pila
		   inicial en otra para devolver el camino correcto */

		if(!dirigido_euler)
                {
                    resultado.add(v_euler);
                }
	    }
	
	if(!dirigido_euler)
	    System.out.println("");
	else
	    {
                resultado = new ArrayList<Integer>();
                
		while(!Intercambio.empty())
                {
                    resultado.add(Intercambio.peek());
		    System.out.print(Intercambio.pop()+" ");
                }
		
		System.out.println("");
	    }

        return resultado;
    }

    /**
     * Método observador. (privado)
     * Realiza un recorrido desde el nodo inicial hasta encontrar un ciclo
     * cuyo fin termine en el nodo inicial.
     * @param v: valor entero que representa el nodo origen del camino 
     * o tour.
     * @return int: valor entero que se representa con el último nodo 
     * procesado.
     */

    private int tour_euler(int v)
    {
	int w=0;

	while(true)
	    {
		
		if(hash_euler.get(v).isEmpty())
		    break;
		
		/* Siempre tomamos por defecto el primer elemento 
		   del ArrayList de los vértices adyacentes al v 
		   en este caso. */

		w = hash_euler.get(v).get(0);
		
		pila_euler.push(v);

		/* Borramos las aristas que ya se han procesado */

		if(dirigido_euler)
		    borrar_arista_eulerGD(v,w);
		else
		    borrar_arista_eulerGND(v,w);

		v = w;

		if(hash_euler.get(v).isEmpty() && v == vert_orig_euler)
		    pila_euler.push(v);
	    }

	return v;
    }

    /**
     * Método modificador. (privado - para grafos dirigidos)
     * Método que elimina las aristas procesadas en la función tour y que
     * no nos sirven para la obtención del camino euleriano del grafo.
     * @param v: valor entero que representa un vértice de la arista.
     * @param w: valor entero que representa el otro vértice de la arista.
     */

    private void borrar_arista_eulerGD(int v, int w)
    {
	if(v >= hash_euler.size() && w >= hash_euler.size())
	    {
		System.out.println("Los valores no son validos para v y w");
		return ;
	    }

	for(int j=0; j < hash_euler.get(v).size(); ++j)
	    if(hash_euler.get(v).get(j) == w)
		hash_euler.get(v).remove(j);

    }

    /**
     * Método modificador. (privado - para grafos no dirigidos)
     * Método que elimina las aristas procesadas en la función tour y que
     * no nos sirven para la obtención del camino euleriano del grafo.
     * @param v: valor entero que representa un vértice de la arista.
     * @param w: valor entero que representa el otro vértice de la arista.
     */

    private void borrar_arista_eulerGND(int v, int w)
    {

	if(v >= hash_euler.size() && w >= hash_euler.size())
	    {
		System.out.println("Los valores no son validos para v y w");
		return ;
	    }

	for(int i=0; i < hash_euler.get(w).size(); ++i)
	    if(hash_euler.get(w).get(i) == v)
		hash_euler.get(w).remove(i);

	for(int j=0; j < hash_euler.get(v).size(); ++j)
	    if(hash_euler.get(v).get(j) == w)
		hash_euler.get(v).remove(j);
	
    }


    /**
     * Método observador. (privado)
     * Método que comprueba si se cumplen las condiciones para que un grafo
     * sea euleriano y tenga camino euleriano.
     * @param tama_G: valor entero que representa el número de nodos del grafo.
     * @return boolean: devuelve true o false dependiendo de si el grafo
     * cumple las condiciones impuestas para grafos dirigidos.
     */

    private boolean entrada_salida_euler(int tama_G)
    {
	int salir,num_vertices;
	int cond_uno=0,cond_dos=0;

	/*Primera condición de Euler para Digrafos: Es Euleriano si, 
	  y sólo si, todos sus vértices tienen el mismo grado de 
	  entrada que de salida. */
	
	for(int i=0; i < hash_euler.size(); ++i)
	    {
		salir=grado_euler[i]; 
		//Grado salida del vértice correspondiente.

		num_vertices = 0;
		for(int j=0; j < hash_euler.size(); ++j)
		    {
			for(int k=0; k < hash_euler.get(j).size(); ++k)
			    {
				if(hash_euler.get(j).get(k) == i && i != j)
				    {
					salir--;
					num_vertices++;
				    }
			    }
		    }

		//Grado de entrada.
		if(salir != 0 && num_vertices == grado_euler[i]) 
		    return false;
		
		//Grado_ent(a) = Grado_sal(a)+1.
		if(num_vertices != grado_euler[i]+1 && cond_uno==0) 
		    cond_uno++;
		else
		    {
			if(num_vertices+1 != grado_euler[i] && cond_dos==0)
			    cond_dos++;
		    }
		
	    }
	
	if(cond_uno == 1 && cond_dos == 1)
	    {
		System.out.println("El grafo es euleriano y tiene un recorrido");
		return true;
	    }

	System.out.println("El grafo es euleriano pero no tiene recorrido");
	return true; 
	/* Al llegar aquí tendríamos comprobado que el grafo es euleriano. */

    }

    /**
     * Método modificador (Algoritmo Euler para grafos dirigidos)
     * Un camino euleriano: un camino partiendo de un nodo origen que
     * pase por todos los vértices (se pueden repetir) empleando todas
     * las aristas posibles y regresando al nodo origen (ciclo).
     * @param G: matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     * @param v: valor entero que representa el nodo origen para el camino
     * euleriano.
     * @param w: valor entero que representa el nodo anterior procesado 
     * (en la primera llamada será el mismo nodo que v).
     */

    private void algo_EulerGD(int [][] G, int v, int w)
    {
	grado_vector(G);
	int tama_G = G.length;
	int t = grado_euler[v] + grado_euler[w];
	dirigido_euler = true;

	v_euler = v;
	pila_euler = new Stack<Integer>();
	hash_euler = new HashMap<Integer,ArrayList<Integer>>();
	ArrayList<Integer> adyacencias;

	for(int i=0; i < tama_G; ++i)
	    {
		adyacencias = new ArrayList<Integer>();

		for(int j=0; j < tama_G; ++j)
		    {
			if(G[i][j] != 0)
			    {

				adyacencias.add(j);
			    }
		    }

		hash_euler.put(i,adyacencias);
	    }

	encontrado_euler = entrada_salida_euler(G.length);

    }

    /**
     * Método modificador (Algoritmo Euler para grafos no dirigidos)
     * Un camino euleriano: un camino partiendo de un nodo origen que
     * pase por todos los vértices (se pueden repetir) empleando todas
     * las aristas posibles y regresando al nodo origen (ciclo).
     * @param G: matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     * @param v: valor entero que representa el nodo origen para el camino
     * euleriano.
     * @param w: valor entero que representa el nodo anterior procesado 
     * (en la primera llamada será el mismo nodo que v).
     */

    private void algo_EulerGND(int [][] G, int v, int w)
    {
	grado_vector(G);
	int tama_G = G.length;
	int t = grado_euler[v] + grado_euler[w];
	dirigido_euler = false;
	
	v_euler = v;
	pila_euler = new Stack<Integer>();
	hash_euler = new HashMap<Integer,ArrayList<Integer>>();
	ArrayList<Integer> adyacencias;

	for(int i=0; i < tama_G; ++i)
	    {
		adyacencias = new ArrayList<Integer>();

		for(int j=0; j < tama_G; ++j)
		    {
			if(G[i][j] != 0)
			    {
				adyacencias.add(j);
			    }
		    }

		hash_euler.put(i,adyacencias);
	    }

	if((t%2) != 0)
	    {
		encontrado_euler = false;
		return ;
	    }

	for(t=0; t < G.length; ++t)
	    if((t != v) && (t != w))
		if((grado_euler[t] % 2) != 0)
		    {
			encontrado_euler = false;
			return ;
		    }


	encontrado_euler = true;
	
    }

    /**
     * Método observador (Euler dirigido)
     * Método que llama al procedimiento general para la obtención del camino
     * euleriano (si existe) del grafo euleriano (si lo es).
     * @param G matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     * @param v valor entero que representa el nodo origen para el camino
     * euleriano.
     * @param w valor entero que representa el nodo anterior procesado 
     * (en la primera llamada será el mismo nodo que v).
     * @return boolean: devuelve true o fale si hay encontrado o no un 
     * camino euleriano para el grafo.
     */
    
    public boolean camino_eulerGD(int [][] G, int v, int w)
    {
	vert_orig_euler = w;
	algo_EulerGD(G,v,w);
	

	return encontrado_euler;
    }

    /**
     * Método observador (Euler no dirigido)
     * Método que llama al procedimiento general para la obtención del camino
     * euleriano (si existe) del grafo euleriano (si lo es).
     * @param G matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     * @param v valor entero que representa el nodo origen para el camino
     * euleriano.
     * @param w valor entero que representa el nodo anterior procesado 
     * (en la primera llamada será el mismo nodo que v).
     * @return boolean: devuelve true o fale si hay encontrado o no un 
     * camino euleriano para el grafo.
     */

    public boolean camino_eulerGND(int [][] G, int v, int w)
    {
	vert_orig_euler = w;
	algo_EulerGND(G,v,w);
	

	return encontrado_euler;
    }

    /**
     * Método modificador. (privado)
     * Introduce en una variable privada a la clase los grados de los nodos
     * del grafo. Esta función se ha realizado para el cálculo de los grados
     * de los nodos del algoritmo de Euler.
     * @param G: matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     */

    private void grado_vector(int [][] G)
    {
	grado_euler = new int [G.length];

	for(int i=0; i < G.length; ++i)
	    grado_euler[i] = grado_vertice(i,G);
    }

    /**
     * Método observador
     * @param vertice valor de tipo entero que se corresponde con el vértice
     * o nodo del grafo.
     * @param G matriz de adyacencia o de costes asociada al grafo.
     * @return int: valor entero que se corresponde con la valencia del nodo.
     * Número de valencia o grado de un vértice son las aristas 
     * incidentes en él que contenga.
     */

    public int grado_vertice(int vertice, int [][] G)
    {

	int grado=0;
	int tama_G = G.length;

	if(vertice < 0 || vertice > tama_G)
	    {
		System.out.println("El vértice no esta en el grafo");
		return 0;
	    }

	for(int i=0; i < tama_G; ++i)
	    {
                if(!grafo_adyacente)
                {
                    if(G[vertice][i] != 0 && G[vertice][i] < 100)
                        grado++;
                }
                else
                {
                    if(G[vertice][i] != 0)
                        grado++;
                }
	    }

	return grado;
    }



    /**
     * Método observador (privado)
     * @param x valor entero
     * @return devuelve true o false si el valor es par o no.
     */
    
    private boolean par(int x)
    {
	return (x%2 == 0);
    }

    /**
     * Método modificador 
     * Realiza la ordenación topológica del grafo de trabajo y la muestra
     * por el flujo de salida estándar.
     * @param G matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     * @return Devuelve una estructura de tipo ArrayList cuyo contenido es 
     * entero. En la estructura se encuentra la ordenación topológica del grafo
     * procesado.
     */

    public ArrayList<Integer> ordenacion_topologica(int [][] G)
    {
	System.out.println("ORDENACION TOPOLOGICA");
        
        ArrayList<Integer> resultado = new ArrayList<Integer>();
	
	int tama_G = G.length;
        
        /*
         * Esta variable la empleo simplemente para que introduzca
         * el primer elemento del recorrido del grafo en orden
         * topológico en la variable nodo_origen_topo_ y así
         * poder realizar el coloreado correctamente.
         */
        
        int vueltas = 0;
        
	vertice_visitado = new boolean [tama_G];
	pila = new Stack<Integer>();

	for(int i=0; i < tama_G; ++i)
	    vertice_visitado[i] = true;

	for(int i=0; i < tama_G; ++i)
	    if(vertice_visitado[i])
		topolog_rec(i,G);

	Stack<Integer> salida = new Stack<Integer>();

	while(!pila.empty())
	    {
		salida.push(pila.peek());
		pila.pop();
	    }

	System.out.print("(");
	while(!salida.empty())
	    {
		System.out.print(" "+salida.peek());
                
                if(vueltas == 0)
                {
                    nodo_origen_topo_ = salida.peek();
                    vueltas++;
                }
                
                resultado.add(salida.peek());
		salida.pop();
	    }
	System.out.println(" )");
        
        return resultado;
    }

    /**
     * Método observador (público)
     * Devuelve el valor del nodo origen para el recorrido en orden
     * topológico del grafo para así poder realizar la coloración del grafo
     * según el algoritmo establezca el origen.
     * @return Devuelve el valor del nodo origen (int) para el recorrido en orden
     * topológico del grafo para así poder realizar la coloración del grafo
     * según el algoritmo establezca el origen.
     */
    
    public int nodo_origen_topo ()
    {
        return nodo_origen_topo_;
    }
    
    /**
     * Método observador. (privado)
     * Es la función auxiliar que ayuda a procesar el orden topológico
     * para el grafo actual.
     * @param G matriz bidimensional de enteros cuyo contenido es el grafo
     * de trabajo actual.
     * @param vertice valor entero que representa un nodo del grafo G.
     */

    private void topolog_rec(int vertice, int [][] G)
    {
	vertice_visitado[vertice] = false;
	int tama_G = G.length;

	for(int i=0; i < tama_G; ++i)
	    if(G[i][vertice] != 0)
		if(vertice_visitado[i])
		    topolog_rec(i,G);

	pila.push(vertice);
    }

    
}