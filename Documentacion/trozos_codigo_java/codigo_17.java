for(int aux=0; aux < rep_grafo.size()-1 && it.hasNext(); ++aux)
    {
	Object o = it.next();
	rep_grafo.get(o).add(aux,infinito);
    }
