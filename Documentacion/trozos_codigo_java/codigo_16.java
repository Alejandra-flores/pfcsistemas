public void _arista_nueva_ (int v_i, int v_j, int x, boolean diri)
{

    if(v_i < num_nodos_ || v_j < num_nodos_)
	{
	    if(v_i != v_j)
		{
		    rep_grafo.get(v_i).set(v_j,x);
		    if(diri)
			rep_grafo.get(v_j).set(v_i,x);
			
		}
	    else
		{
		    if(adyacente)
			{
			    rep_grafo.get(v_i).set(v_j,1);
			    if(diri)
				rep_grafo.get(v_j).set(v_i,1);
			}
		}
		    
	}
    else
	System.out.println("No existe el nodo: Lanzar exepcion");
       
}
