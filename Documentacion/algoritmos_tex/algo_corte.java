/**
 * Algoritmo de comprobación de vértices de corte - Método modificador.
 * Muestra por la salida estándar los nodos de corte del grafo.
 * @return boolean: representa un valor lógico que indica si se ha encontrado
 * un punto de corte(true) o no(false) en el grafo.
 * @exception Exception
 */

public boolean vertice_corte() throws Exception
{
    System.out.println("Se procede a la búsqueda de vértice de corte");
    array_grados();

    int tama_G = this.devolver_matriz().length;
    int aux=0;
    boolean punto_corte=true;

    for(int i=0; i < tama_G; ++i)
	{
	    aux = grados_interno.get(i);
	    if(this.n_aristas() - aux < (this.n_nodos() - 1))
		punto_corte = false;
	}

    return punto_corte;
}
