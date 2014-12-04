/**
 * Método modificador 
 * Realiza la ordenación topológica del grafo de trabajo y la muestra
 * por el flujo de salida estándar.
 * @param G: matriz bidimensional de enteros cuyo contenido es el grafo
 * de trabajo actual.
 */

public void ordenacion_topologica(int [][] G)
{
    System.out.println("ORDENACION TOPOLOGICA");
	
    int tama_G = G.length;
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
	    salida.pop();
	}
    System.out.println(" )");
}

/**
 * Método observador. (privado)
 * Es la función auxiliar que ayuda a procesar el orden topológico
 * para el grafo actual.
 * @param G: matriz bidimensional de enteros cuyo contenido es el grafo
 * de trabajo actual.
 * @param vertice: valor entero que representa un nodo del grafo G.
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
