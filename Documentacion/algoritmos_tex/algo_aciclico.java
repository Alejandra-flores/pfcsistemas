/**
 * Método observador. (para grafos dirigidos)
 * Comprueba si un grafo dirigido es aciclico o no.
 * @param G array multidimensional (matriz) que contiene la matriz
 * de adyacencia del grafo G asociado.
 * @return devuelve true o false (verdadero o falso) si se cumple
 * que sea o no acíclico.
 */

public boolean AciclicoGD(int [][] G)
{
	
    int tama_G = G.length;
    int i;
    boolean aciclico = true; 
    // El grafo es acíclico hasta que se demuestre lo contrario.
    visitados = new boolean[tama_G];
    ancestros = new int[tama_G];
    // Para llevar la cuenta de los ancestros.

    // Al comienzo no hay ningún vértice visitado.
    Arrays.fill(visitados, false);

    /* Vamos iterando de componente en componente conexa
       para sacar el árbol de expansión */

    for(i=0; i < tama_G && aciclico == true; ++i)
	{
	    if(!visitados[i])
		aciclico = AciclicoGDRec(i,G,0);
	}
	
    return aciclico;
}

/**
 * Función privada de la clase que sirve para iterar sobre la estructura
 * de aristas del grafo.
 * @param vertice vértice inicial desde el que se realiza la búsqueda 
 * en el grafo.
 * @param G array multidimensional (matriz) que contiene la matriz
 * de adyacencia del grafo G asociado.
 * @param num_ancestros número de vértices visitados anteriormente desde 
 * el vértice de partida.
 * @return devuelve true o false (verdadero o falso) dependiendo de si 
 * encuentra un ciclo o no en la estructura.
 */

private boolean AciclicoGDRec(int vertice, int [][] G, int num_ancestros) 
{
    int w=0;
    // Para movernos por los vértices adyacentes.

    int i;
    // Para movernos por los ancestros.

    visitados[vertice] = true;
    // Acabamos de visitar dicho vértice.

    ancestros[num_ancestros] = vertice;
    // Guardamos el ancestro.
	

    /* Ahora recorremos todos los vértices y miramos cuales son
       adyacentes y de ellos los que no hemos visitado. Con estos
       vértices seguimos la búsqueda en profundidad. */

    for(w=0; w < G[vertice][w]; ++w)
	{
	    if(G[vertice][w] != 0)
		{
		    if(!visitados[w])
			return (AciclicoGDRec(w,G,num_ancestros++));
		    else
			{
			    // Si el vértice ya se ha visitado.
			    for(i=0; i <= num_ancestros; ++i)
				{
				    // Lo encontramos hay una arista.
				    if(ancestros[i] == w)
					return (false); //Hay un ciclo.
					
				}
			}
		}
	}
	
    return (true); // Si llegamos aquí no se habrá encontrado ningún ciclo.
}
