/**
 * Método observador (Algoritmo de QuickSort - Ordenación Rápida)
 * Supongamos que un elemento arbitrario p de los n índices o elementos 
 * que tenemos que ordenar. Quicksort separa los n-1 restantes elementos 
 * en dos montones: un primer montón inferior que contiene todos los 
 * elementos que aparecen antes de p en orden creciente (son menores que p)
 * y otro montón superior que contiene todos los elementos que aparecen 
 * después de p en orden creciente (son mayores que p). 
 * Los montones inferior y superior denotan las posiciones de los 
 * elementos del vector, colocando un elemento central o pivote que 
 * diferencia ambos montones.
 * @param x índice del primer elemento de la estructura.
 * @param y índide del último elemento de la estructura.
 * @return void
 */


private void Quicksort(int x, int y)
{
    if(y <= x)
	return;

    int i = particion(x,y);

    Quicksort(x,i-1);
    Quicksort(i+1,y);
}

/**
 * Función auxiliar que busca el pivote de la estructura que pasemos.
 * @param x: valor entero que representa la posición inicial de la 
 * estructura interna.
 * @param y: valor entero que representa el numero de elementos total
 * de la estructura. (Indice final de la estructura)
 */

private int particion (int x, int y)
{
    int i = x-1, j = y;
    Arista v = new Arista();
    v.v_origen(vector_kruskal.get(y).v_origen());
    v.v_destino(vector_kruskal.get(y).v_destino());
    v.coste(vector_kruskal.get(y).coste());

    for(;;)
	{
	    while(vector_kruskal.get(++i).coste() < v.coste());

	    while(v.coste() < vector_kruskal.get(--j).coste())
		if(j == x)
		    break;

	    if(i >= j)
		break;

	    cambio(i,j);
	}

    cambio(i,y);
    return i;
}

/**
 * Método modificador. (privado)
 * El método intercambia los valores de las posiciones vertice_i y
 * vertice_j de la estructura interna que almacena los nodos adyacentes
 * por cada uno de los nodos del grafo de trabajo.
 * @param vertice_i: valor entero que representa un vértice de la arista
 * (u,v) (u)
 * @param vertice_j: valor entero que representa el otro vértice de la 
 * arista (u,v) (v)
 */

private void cambio(int vertice_i, int vertice_j)
