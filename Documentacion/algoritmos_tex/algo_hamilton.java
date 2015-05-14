/**
 * Método observador (Camino Hamiltoniano)
 * Un grafo dirigido tendrá un camino Hamiltoniano 
 * (será grafo hamiltoniano) si existe un vértice que partiendo desde él 
 * se pase por cada vértice restante del grafo, una sola vez por nodo, y 
 * volviendo exactamente al nodo de partida.
 * @param G: matriz bidimensional de enteros que representa al grafo
 * de trabajo.
 * @param v: valor entero que representa el nodo de partida para el 
 * camino.
 * @param w: valor entero que representa el nodo anterior visitado.
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
 * @param v: valor entero que representa el nodo actual. 
 * @param w: valor entero que representa el nodo anterior procesado.
 * @param G: matriz bidimensional de enteros que representa el grafo de 
 * trabajo actual.
 * @param d: valor entero que representa el número de nodos del grafo.
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
