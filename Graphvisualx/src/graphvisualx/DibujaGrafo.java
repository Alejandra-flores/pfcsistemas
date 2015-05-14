/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

import java.io.File;
import java.io.IOException;

/**
 * Fichero DibujaGrafo.java
 * @author Moisés Gautier Gómez
 * @version 1.0 10/21/11
 */

/*
 ******************************************************************************
                   (c) Copyright 2011 Moisés Gautier Gómez
 
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************
 */

public class DibujaGrafo {
    
    /*
     * Atributos privados de la clase.
     */

    /*
     * Se empleará para poder guardar el fichero en formato imagen en el
     * sistema.
     */
    
    private File out;
    
    /**
     * Constructor nulo.
     */
    
    public DibujaGrafo(){}
    
    /**
     * Constructor
     * @param matriz Parámetro de tipo array multidimensional de entero. 
     * Contiene la matriz de costes/adyacencia del grafo.
     * @param ady Parámetro de tipo boolean que especifica si el grafo
     * actual es de adyacencia o no.
     */
    
    public DibujaGrafo(int [][] matriz, boolean ady, boolean dir, int nodo_origen) throws IOException 
    {
        System.out.println("Valor de adyacencia: "+ady);
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        gv.addln("rankdir=LR;");
        
        int dimension = matriz.length;
        
        if(!ady)
        {
            String nodo;
            
            for(int i=0; i < dimension; i++)
            {
                if(i == nodo_origen)
                    nodo = new String(""+i+" [style=filled,shape=circle,color=tomato]");
                else
                    nodo = new String(""+i+" [style=filled,shape=circle,color=palegreen]");
                    
             gv.addln(nodo);
            }
            
            if(dir)
            {
                for(int i=0; i < dimension; i++)
                {
                    for(int j=0; j < dimension; j++)
                    {
                        if(matriz[i][j] < 100 && i!= j && matriz[i][j] != 0)
                        {
                            String arista = new String(""+i+" -> "+j+" [label="+matriz[i][j]+" fontcolor=tomato];");
                            gv.addln(arista);
                        }
                    }
                }
            }
            else
            {
                for(int i=0; i < dimension; i++)
                {
                    for(int j=0; j < dimension; j++)
                    {
                        if(matriz[i][j] < 100 && matriz[i][j] != 0 && i != j)
                        {
                            String arista = new String(""+i+" -> "+j+" [arrowhead=none arrowtail=none dir=both label="+matriz[i][j]+" fontcolor=tomato];");
                            gv.addln(arista);
                            matriz[j][i] = 0;
                        }
                    }
                }
            }
        }
        else
        {
            String nodo;
            for(int i=0; i < dimension; i++)
            {
                if(i == nodo_origen)
                    nodo = new String(""+i+"[style=filled,shape=circle,color=tomato]");
                else
                    nodo = new String(""+i+"[style=filled,shape=circle,color=palegreen]");
                
                gv.addln(nodo);
            }
            
            if(dir)
            {
                for(int i=0; i < dimension; i++)
                {
                    for(int j=0; j < dimension; j++)
                    {
                        if(matriz[i][j] != 0 && i != j)
                        {
                            String arista = new String(""+i+" -> "+j+"[label="+matriz[i][j]+" fontcolor=tomato];");
                            gv.addln(arista);
                        }
                    }
                }
            }
            else
            {
                for(int i=0; i < dimension; i++)
                {
                    for(int j=0; j < dimension; j++)
                    {
                        if(matriz[i][j] != 0 && i != j)
                        {
                            String arista = new String(""+i+" -> "+j+" [arrowhead=none arrowtail=none dir=both label="+matriz[i][j]+" fontcolor=tomato];");
                            gv.addln(arista);
                            matriz[j][i] = 0;
                        }
                    }
                }
            }
        }
        
        gv.addln(gv.end_graph());
        System.out.println(gv.getDotSource());
        
        /* 
         * Por defecto he establecido el algoritmo de compresión de imágenes
         * a png.
         */
        
        String type = "png";
        AbrirFichero fichero = new AbrirFichero(true,true,true);
        out = new File(fichero.ruta_fichero()+"."+type);
        
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type),out);
        
    }
    
    /**
     * Método observador.
     * @return Devuelve el apuntador o dirección de memoria a la que pertenece
     * el fichero creado en la clase. El contenido de ese fichero será la 
     * representación gráfica a través de graphviz del grafo original o 
     * procesado.
     */
    
    public File fichero()
    {
        return out;
    }
    
    /**
     * Método modificador.
     * Se emplea para establecer al valor inicial la variable interna
     * de la clase que contendrá el fichero.
     */
    
    public void borrarfichero()
    {
        if(out != null)
        {
         if (out.delete() == false) 
            System.err.println("Cuidado: " + out.getAbsolutePath() + " no se ha podido eliminar!");
        }
    }
}
