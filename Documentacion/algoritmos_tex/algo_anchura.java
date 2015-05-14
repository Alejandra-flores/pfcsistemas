/**
 * Método observador.
 * Muestra por la salida estándar el recorrido en anchura del grafo G.
 * @param G matriz de adyacencia del grafo conexo.
 */

public void recorrido_anchura(int [][] G) 
{
    int tama_G = G.length;

    boolean [] marcas = new boolean [tama_G];
    int i,v,w; //Vértices.

    LinkedList<Integer> Cola = new LinkedList();
    rec_breadth = new ArrayList<Integer>();

    Arrays.fill(marcas,0,tama_G,false);
    System.out.println("REC_ANCHURA");
    for(i=0; i < tama_G; ++i)
	if(!marcas[i]) //NO visitado
	    {
		Cola.add(i); //Frente de la Cola
		    
		do{
		    v = Cola.getFirst();
		    Cola.removeFirst(); //Eliminamos el elemento.
		    if(!marcas[v]) //NO visitado.
			{
			    /* Marcar y procesar. */
			    marcas[v] = true; //Visitado.
			    System.out.print(v+" ");
			    rec_breadth.add(v);
				
			    /* Encolar los adyacentes no visitados. */

			    for(w=0; w < tama_G; ++w)
				if(G[v][w] == 1 && marcas[w] == false)
				    Cola.add(w);
			}
		}while(!(Cola.isEmpty()));
	    }
    System.out.println("");
}
