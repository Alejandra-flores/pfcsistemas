
for(v=0; v < tama_G; ++v)
    {
	Costes_Vertices[0][v] = G[origen][v];

	//Asignamos todos los costes asociados partiendo desde
	//el vertice origen
	Costes_Vertices[1][v] = origen;
	//El vector tiene como elementos al vertice origen
    }

