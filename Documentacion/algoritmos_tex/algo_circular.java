/**
 * Método observador.
 * Comprueba si el grafo (o componente) es circular o no.
 * @return boolean: devolverá true o false si el grafo es o no circular.
 * @exception Exception
 */

public boolean es_circular() throws Exception
{
    int grado_grafo=0;
    int tama_G = this.devolver_matriz().length;

    if(this.n_nodos() >= 3)
	{
	    for(int i=0; i < tama_G; ++i)
		if(grado_nodo(i,this.devolver_matriz()) == 2)
		    grado_grafo++;
		

	    if(grado_grafo == tama_G)
		{
		    System.out.println("El grafo es circular");
		    return true;
		}
	}
	
    return false;

    
}
