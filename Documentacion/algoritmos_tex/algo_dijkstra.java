/**
 * Método observador (Algoritmo de Dijkstra).
 * Calcula los caminos de coste mínimo entre origen y 
 * todos los vértices del grafo G.
 * @param origen vértice desde el que se realiza la computación.
 * @param G matriz de costes asociada al grafo G.
 * @return Un vector de tamaño G.length con estos costes 
 * mínimos (fila 0 de la matriz) y un vector de tamaño G.length 
 * tal que vector[i] es el último vértice del camino origen a i 
 * (fila 1 de la matriz).
 * @exception Exception
 */

public int[][] algo_Dijkstra(int origen, int [][] G) throws Exception
{
    int tama_G=G.length;

    /*
      Voy a devolver como resultado de la función la variable definida 
      int [][] Costes_Vertices [2][G.length]. Donde la primera fila es 
      el vector de costes obtenido al aplicar Dijkstra y la segunda 
      fila es el vector obtenido del recorrido según se obtienen los 
      caminos mas cortos a partir del origen. 
    */

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
		if(!Visitado[v] && Costes_Vertices[0][v] < CosteMin)
		    {
			CosteMin = Costes_Vertices[0][v];
			w = v;
		    }

	    Visitado[w] = true;

	    for(v=0; v < tama_G;++v)
		{
		    Owv = Suma(Costes_Vertices[0][w],G[w][v]);

		    if(!Visitado[v] && Costes_Vertices[0][v] > Owv)
			{
			    Costes_Vertices[0][v] = Owv;
			    Costes_Vertices[1][v] = w;
			}
		}
	}

    return Costes_Vertices;
}
