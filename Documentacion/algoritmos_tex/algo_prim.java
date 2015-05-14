/**
 * Método observador (Algoritmo de Prim).
 * Devuelve en un vector el conjunto de aristas que forman un árbol
 * generador de coste mínimo de un grafo conexo.
 * @param G matriz de costes asociada al grafo G.
 * @return devuelve un ArrayList con el conjunto de aristas que forman 
 * un árbol generador de coste mínimo del grafo conexo G.
 * @exception Exception
 */

public ArrayList algo_Prim(int [][] G) throws Exception
{
    int tama_G = G.length;
    boolean [] U = new boolean [tama_G];
	
    int j,k; //Vértices.
    int i,destino=0;
    int CosteMin;
    Arista a = new Arista();

    ArrayList<Arista> conj_aristas 
	= new ArrayList<Arista>(Collections.nCopies(tama_G-1,a));


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
