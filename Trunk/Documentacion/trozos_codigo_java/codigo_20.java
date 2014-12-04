
/**
 * Leemos el grafo G conexo con todos los vertices pares
 * C = {v}, siendo v un vertice cualquiera de G
 * Mientras que en G queden aristas
 * - Sea v un vertice de C, no aislado en G.
 * - Sea D un ciclo empezando en v.
 * - Eliminar de G las aristas de D
 * - Sustituir en C el vertice v por el ciclo D
 * Retorna C
 * 
 */
    
public void circuito_euleriano(int [][] G)
{
    //Sera euleriano si todos sus vertices son de grado par
	
    int tama_G = G.length;
    int num_vertices = 0;
    boolean salir=false;
    int elto_actual=0;
    euler_matriz = new int[tama_G][tama_G];
    vertices_euler = new ArrayList<Integer>();
    no_aristas = new boolean[tama_G];
    Arrays.fill(no_aristas,true);
	
    for(int i=0; i < tama_G; ++i)
	{
	    if(par(grado_vertice(i,G)))
		num_vertices++;

	    for(int j=0; j < tama_G; ++j)
		euler_matriz[i][j] = G[i][j];
		    
	}

    if(num_vertices == tama_G)
	{
	    System.out.println("Si tiene camino euleriano");
	    visitado_euler = new boolean [tama_G];
		
	    Arrays.fill(visitado_euler, false);

	    euler_rec(0,0,0);
				
	    visitado_euler = new boolean [tama_G];
	    Arrays.fill(visitado_euler,false);

	    euler_rec(0,4,0);

	    System.out.println("MATRIZ GRAFO");
	    System.out.println(Arrays.deepToString(G));
			
	    System.out.println("MATRIZ EULER");
	    System.out.println(Arrays.deepToString(euler_matriz));

	    System.out.println("CAMINO EULERIANO");
	    System.out.print("(");
			
	    for(Integer i=0; i < vertices_euler.size(); ++i)
		{
		    System.out.print(vertices_euler.get(i));
				
		    if(i+1 != vertices_euler.size())
			System.out.print(", ");
		}
	    System.out.println(")");
			
	
	}
    else
	System.out.println("NO tiene camino euleriano");

	
}

private void euler_rec(int origen, int vertice, int ultimo)
{
    int w=0;
    // Para movernos por los vertices adyacentes

    System.out.println("NO_ARISTAS");
    System.out.println(Arrays.toString(no_aristas));
	

    int tama_G = euler_matriz.length;
    boolean salir=false;

    visitado_euler[vertice] = true;
    System.out.println("eltos de euler:"+vertice);
    vertices_euler.add(vertice);
    // Acabamos de visitar dicho vertice
	
    // Ahora recorremos todos los vertices y miramos cuales son
    // adyacentes y de ellos los que no hemos visitado. Con estos
    // vertices seguimos la busqueda en profundidad.

    for(w=0; w < tama_G && !vamonos; ++w)
	{
	    if(euler_matriz[vertice][w] != 0) //Existe arista
		{
		    if(!visitado_euler[w])
			{
			    euler_matriz[vertice][w] = 0;
			    euler_matriz[w][vertice] = 0;
			    /*
			      Recorro la fila de la matriz 
			      para comprobrar si tiene mas aristas 
			      o no el vertice para asi tener una 
			      condicion de parada en la llamada a 
			      esta función. Se detendra cuando todos 
			      los vertices tengan sus filas a 0, 
			      es decir, cuando se hayan eliminado 
			      todas las aristas. 
			    */

			    /*
			      Lo hacemos para ambos vertices porque 
			      el grafo es no dirigido.
			    */

			    for(int j=0; j < tama_G; ++j)
				{
				    if(euler_matriz[w][j] == 0 
				       && no_aristas[w])
					no_aristas[w] = false;
				    if(euler_matriz[vertice][j] == 0 
				       && no_aristas[vertice])
					no_aristas[vertice] = false;    
				}
				
			    //Eliminamos la arista correspondiente

			    euler_rec(origen,w,vertice);
			}
		    else
			{
			    if(ultimo != origen) // Sino es el "padre"
				{
				    System.out.println("ORIGEN:"+origen);
				    vertices_euler.add(origen);
				    vamonos=true;
				    // entonces hay un ciclo
				}
			}
			
		}
	}

    // Si llegamos aqui no se habra encontrado ningun ciclo

}
