/**
 * Procedimiento de Bellman-Ford (llamada al algoritmo principal)
 * @param G: matriz de costes del grafo conexo G.
 * @param x: valor entero que representa el nodo origen.
 * @return void
 */

public void Bellman_Ford (int [][] G, int x)
{
    System.out.println("BELLMAN-FORD");

    int i;
	
    if(algo_Bellman_Ford(G,x) == true)
	{
	    System.out.println("Camino de Bellman-Ford");
	    for(i=0; i < vertices.size(); ++i)
		System.out.println("Arista:"+vertices.get(i).toString());

	}
	
}


/**
 * Método observador (Algoritmo de Bellman-Ford)
 * Devuelve en un valor booelano que servirá para saber
 * si el grafo tiene un camino posible a través de dicho
 * algoritmo.
 * @param G: matriz de costes del grafo conexo G.
 * @param origen: valor entero que representa el vértice de partida
 * para el algoritmo.
 * @return devuelve un valor booleano que servirá para comprobar
 * si se ha encontrado un posible ciclo de pesos negativos desde
 * el vértice origen (true) o no (false).
 */

private boolean algo_Bellman_Ford(int [][] G, int origen)
{

    int i,j;

    ArrayList<Arista> edges = new ArrayList<Arista>();
    Arista edge = new Arista();

    vertices = new HashMap<Integer,Arista>();

    /* Por defecto se colocará a 0
       el valor del nodo o vértice
       origen desde el que parte
       el procesamiento del 
       algoritmo.
    */
	
    for(i=0; i < G.length; ++i)
	{
	    Arista a = new Arista();
	    a.coste(1000); 
	    /*
	      Representamos el coste del vértice
	      usando el campo coste de la clase
	      Arista.
	    */

	    if(i == origen)
		{
		    a.coste(0);
		    vertices.put(i,a);
		}
	    else
		vertices.put(i,a);
	}

    for(i=0; i < G.length; ++i)
	{
	    for(j=0; j < G.length; ++j)
		{
		    if(G[i][j] != 0 && G[i][j] < 100)
			{
			    edge = new Arista();
			    edge.v_origen(i);
			    edge.v_destino(j);
			    edge.coste(G[i][j]);
			    edges.add(edge);
			}
		}
	}

    int suma=0;
    int posicion=0;
    int pos_u=0,pos_v=0;

    for(i=0; i < vertices.size(); ++i)
	{
	    for(j=0; j < edges.size(); ++j)
		{
			
		    suma = vertices.get(edges.get(j).v_origen()).coste() 
			+ edges.get(j).coste();
		    
		    if(suma 
		       < vertices.get(edges.get(j).v_destino()).coste())
			{
			    /* 
			       Significa que el peso del vértice es mayor
			       que el peso de la arista de llegada más
			       el peso del vértice origen.
			    */

			    pos_u = edges.get(j).v_origen();
			    pos_v = edges.get(j).v_destino();
				
			    if(pos_v != origen)
				{
				    vertices.get(pos_v).v_origen(pos_u);
				    vertices.get(pos_v).v_destino(pos_v);
				    vertices.get(pos_v).coste(suma);
				}
			}
			    
		}
	}

    for(i=0; i < edges.size(); ++i)
	{
	    suma = vertices.get(edges.get(i).v_origen()).coste() 
		+ vertices.get(edges.get(i).v_destino()).coste();

	    if(vertices.get(edges.get(i).v_destino()).coste() < suma)
		return true;
	}

    return false;

}
