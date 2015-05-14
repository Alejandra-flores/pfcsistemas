/**
 * Método observador (Euler dirigido)
 * Método que llama al procedimiento general para la obtención del camino
 * euleriano (si existe) del grafo euleriano (si lo es).
 * @param G: matriz bidimensional de enteros cuyo contenido es el grafo
 * de trabajo actual.
 * @param v: valor entero que representa el nodo origen para el camino
 * euleriano.
 * @param w: valor entero que representa el nodo anterior procesado 
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
