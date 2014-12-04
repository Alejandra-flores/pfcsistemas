/**
 * Método observador (Número cromático del grafo)
 * @param G: matriz bidimensional de enteros que representa al grafo de
 * trabajo actual.
 * @return int que es el número cromático del grafo.
 */

public int k_colores(int [][] G)
{
    int tama_G = G.length;
    int elto=0, original=0;
    int [] color = new int [tama_G];
    Arrays.fill(color,0); //Rellenamos con el color vacío todo el vector.
    int i;
    boolean salto=false;


    HashMap<Integer,ArrayList<Integer>>  v_ady 
	= new HashMap<Integer,ArrayList<Integer>>();
    ArrayList<Integer> ady;


    for(i=0; i < tama_G; ++i)
	{
	    ady = new ArrayList<Integer>();

	    for(int j=0; j < tama_G; ++j)
		{
		    if(G[i][j] != 0)
			{
			    ady.add(j);
			}
		}

	    v_ady.put(i,ady);
	}

    i=0;
    int z=0;
    while(i < tama_G)
	{
	    z = rec_depth.get(i);
	    color[z] += 1;
	    original = color[z];

	    for(int j=0; j < v_ady.get(z).size() && !salto; ++j)
		{
		    elto = v_ady.get(z).get(j);
		    if(color[elto] == color[z])
			{
			    color[z] += 1;
			    salto = true;
			}
		}

       
	    if(original == color[z])
		i++;
	    else
		i=0;
	} 

    elto = color[0];
	
    for(i=0; i < tama_G; ++i)
	if(color[i] > elto)
	    elto = color[i];

    return elto;

	
}
