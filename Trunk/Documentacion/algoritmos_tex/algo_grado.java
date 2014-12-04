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
	    if(G[vertice][i] != 0)
		grado++;
	}

    return grado;
}
