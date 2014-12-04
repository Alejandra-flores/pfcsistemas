/**
 * Método modificador
 * Método que halla las aristas de corte (puentes) de un grafo.
 * @param G: matriz bidimensional de enteros que representa el grafo de
 * trabajo actual.
 */

public void puentes(int [][] G)
{
    int tama_G = G.length;
    hash_puentes = new HashMap<Integer,ArrayList<Integer>>();
    ArrayList<Integer> array_puentes;
    ArrayList<Arista> bridge = new ArrayList<Arista>();
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

		if(bridge.size() != 0)
		    {
			if(i == bridge.get(0).v_destino() 
			   && hash_puentes.get(i).get(j) 
			   == bridge.get(0).v_origen()) 
			    {
				siguiente = false;
				bridge.remove(0);
			    }
				
		    }

		if(siguiente)
		    if(puentes_rec(i,hash_puentes.get(i).get(j),tama_G))
			{
			    System.out.println("Puente en: i:"+i+" j:"
					       +hash_puentes.get(i).get(j));
			    I_J.v_origen(i);
			    I_J.v_destino(hash_puentes.get(i).get(j));
			    I_J.coste(0);
			    bridge.add(I_J);
			}
	    }	

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

		    if(vertice_i.size() != 0)
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

			if(hash_puentes.get(i).size() == 1 
			   && hash_puentes.get(i).get(0) == v)
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
		    if(vertice_j.size() != 0)
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


			    if(hash_puentes.get(v).size() == 1 
			       && hash_puentes.get(v).get(0) == u)
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
