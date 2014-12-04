/**
 * Método observador.
 * Comprueba si el grafo (o componente) es vacío o no.
 * Esta función comprueba que hay vértice pero no tiene aristas.
 * @return boolean: devolverá true o false si el grafo es o no vacío.
 * @exception Exception
 */

public boolean es_vacio() throws Exception
{
    if(this.n_aristas() == 0)
	{
	    System.out.println("El grafo es vacío");
	    return true;
	}
    else
	return false;
}
