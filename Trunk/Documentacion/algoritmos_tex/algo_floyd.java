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
    int ikj; 
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
		    ikj = Suma(Costes[i][k],Costes[k][j]);
			
		    if(Costes[i][j] > ikj)
			{
			    Costes[i][j] = ikj;
			    Vertices[i][j] = k;
			}
		}

    /* Si se selecciono camino se mostrará para el caso [0,2]. */
    if(camino)
	camino_Floyd(0,2,Vertices);

    return Costes;
}
