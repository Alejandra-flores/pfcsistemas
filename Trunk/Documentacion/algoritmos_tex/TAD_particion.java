public class Particion
{

    /**
     * Atributos privados
     */

    /*
      Array de enteros que contiene los padres
      de las posiciones (nodos) del sistema.
    */

    private int [] padre;

    /*
      Valor entero que cuyo contenido es
      el número de nodos que posee el grafo.
    */

    private int nEltos;

    /**
     * Constructor nulo.
     */

    public Particion() {}

    /**
     * Constructor predeterminado.
     * Se implementa un bosque de árboles (con control de altura). 
     * Compresión de caminos.
     * @param n número de elementos de la partición que se crea
     */

    public Particion(int n)
    {
	padre = new int[n];
	nEltos = n;

	Arrays.fill(padre,0,nEltos,-1);
	//Creamos n árboles de altura 0 representada con -1
    }

    /**
     * Método modificador.
     * Une el subconjunto del elemento a y el del elemento b en uno 
     * de los subconjuntos arbitrariamente. La partición P queda 
     * con un miembro menos.
     * @param a representante de la clase de partición (nodo).
     * @param b representante de la clase de partición (nodo).
     * @return void
     */
    
    public void Union(int a, int b)
    {
	if(padre[b] < padre[a])
	    padre[a] = b;
	else
	    {
	 	if(padre[a] == padre[b])
	 	    padre[a]--;

	 	padre[b] = a;
	    }
    }

    /**
     * Método observador.
     * Devuelve el representante del subconjunto al que 
     * pertenece el elemento x.
     * @param x elemento de la partición.
     * @return devuelve el representante del subconjunto 
     * al que pertenece el elemento x.
     */

    public int Encontrar(int x)
    {
	int raiz,y;

	raiz = x;

	while(padre[raiz] > -1)
	    raiz = padre[raiz];

	/* Los nodos del camino de x a raiz se hacen 
	   hijo de raiz. */
	
	while(padre[x] >= raiz)
	    {
	 	y = padre[x];
	 	padre[x] = raiz;
	 	x = y;
	    }

	return raiz;
    }

    
}
