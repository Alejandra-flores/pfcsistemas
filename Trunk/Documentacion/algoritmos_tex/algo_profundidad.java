/**
 * Método observador
 * Muestra por la salida estándar el recorrido en profundidad del grafo G.
 * @param G matriz de adyacencia del grafo conexo.
 */

public void recorrido_profundidad(int [][] G) 
{
    int tama_G = G.length;
    System.out.println("Tamaño matriz:"+tama_G);
    marcados = new boolean[tama_G];
    int [][] rec = G;
    rec_depth = new ArrayList<Integer>();

    int i;

    System.out.println("REC_PROFUNDIDAD");
    for(i=0; i < tama_G; ++i)
	if(!(marcados[i])) //No visitado
	    recorrido_profundidadRec(i,rec);
    System.out.println("");
}

/**
 * Método observador privado (Llamada recursiva para el método 
 * recorrido_profundidad().
 * Muestra por la salida estándar el recorrido en profundidad del grafo G.
 * @param G matriz de adyacencia del grafo conexo.
 * @param vertice: valor entero que representa un nodo del grafo.
 */

private void recorrido_profundidadRec(int vertice, int [][] G) 
{
    int w;
    int tama_G = G.length;
    int [][] rec = G;
    marcados[vertice] = true;
    System.out.print(vertice+" "); // Procesar vertice
    rec_depth.add(vertice);

    for(w=0; w < tama_G; ++w)
	if(rec[vertice][w] == 1 && marcados[w] == false)
	    recorrido_profundidadRec(w,rec);
}
