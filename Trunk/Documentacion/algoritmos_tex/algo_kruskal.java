/**
 * Método observador (Algoritmo de Kruskal)
 * Devuelve en un ArrayList el conjunto de aristas que forman un árbol 
 * generador de coste mínimo de un grafo conexo G.
 * @param G: matriz de costes del grafo conexo G.
 * @param n: valor entero que representa el número de nodos del grafo.
 * @param m: valor entero que representa el número de aristas del grafo.
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
