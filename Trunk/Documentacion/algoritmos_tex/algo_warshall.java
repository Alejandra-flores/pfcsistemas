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

public int [][] algo_Warshall(int [][] G) throws Exception
{
    int i,j,k;
    int tama_G = G.length;
    int [][] resultado = new int[tama_G][tama_G];


    /* Inicializar resultado con la matriz de adyacencia de G. */

    for(i=0; i < tama_G; ++i)
	for(j=0; j < tama_G; ++j)
	    {
		if(G[i][j] == 1)
		    resultado[i][j] = 1;
		else
		    resultado[i][j] = 0;
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
