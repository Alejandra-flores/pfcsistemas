/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.border.TitledBorder;


/**
 * Fichero LienzoGrafo.java
 * @author Moisés Gautier Gómez
 * @version 1.0
 * @date 21/10/2011
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

class LienzoGrafo extends JPanel
{

    /*
     * Atributos privados de la clase
     */
    
    /*
     * Un componente de la biblioteca de swing que se utilizará para
     * desplegar un menú emergente cuando se pulse el botón derecho
     * encima de una arista en edición.
     */
    
    private JPopupMenu popup = new JPopupMenu();
    
    /*
     * Una variable de tipo boolean que especifica si se seguirá editando
     * o no nodos en el lienzo de trabajo. Su valor será true cuando 
     * se presione el botón con la etiqueta Nodo del panel del grafo inicial y
     * false cuando se seleccione otra opción.
     */
    
    private boolean continuar = true;
    
    /*
     * Una variable de tipo boolean que especifica si se seguirá editando o no
     * el lienzo de trabajo para el grafo inicial.
     */
    
    private boolean borrado = false;
    
    /*
     * Una variable de tipo boolean que especifica si el grafo de trabajo
     * es dirigido (true) o no (false).
     */
    
    private boolean dirigido = true;
    
    /*
     * Una variable de tipo boolean que especifica si el grafo de trabajo
     * es adyacente (true) o no (false).
     */
    
    private boolean adyacente = true;
    
    /*
     * Una variable de tipo entero que representa el valor del número
     * correspondiente a la n-ésima arista del sistema para ser modificada
     * su valor de ponderación o coste (ya sea de adyacencia o no).
     */
    
    private int arista_modificar = -1;
    
    /*
     * Una variable de tipo boolean que especifica si el grafo de trabajo
     * inicial, editado en el lienzo por el usuario, ha sido aceptado (true)
     * o no (false) para la posterior aplicación de algoritmos en él.
     */
    
    private boolean grafo_aceptado = false;
    
    /*
     * Una variable de tipo boolean que especifica si el valor introducido
     * como coste de la arista pertenece al dominio (true) de los costes para dicha 
     * arista y por consecuencia para dicho grafo.
     */
    
    private boolean coste_valido = false;
    
    /*
     * Una variable de tipo booelan que especifica si el método Drawarrow() 
     * puede representar en pantalla principal o no los costes asociados
     * a cada arista.
     */
    
    private boolean no_costes = false;
    
    /*
     * Una estructura de tipo ArrayList cuyo contenido es de tipo Par (dupla
     * de elementos de tipo int). Esta estructura representa el contenido 
     * obtenido como resultado de la aplicación de algún algoritmo de grafos 
     * sobre el grafo inicial del sistema.
     */
    
    ArrayList<Par> vector_resultado = null;
    
    /*
     * Una estructura de tipo ArrayList cuyo contenido es de tipo Par (dupla
     * de elementos de tipo int). Esta estructura representa el contenido del 
     * grafo original del sistema.
     */
    
    ArrayList<Par> vector_no_cambia = null;

    /*
     * Una estructrua de tipo ArrayList cuyo contenido es de tipo CamposNodos
     * (clase que agrupa las propiedades de un nodo del sistema representado
     * en pantalla). Su contenido son todos los nodos que el usuario va
     * dibujando en el lienzo de trabajo.
     */
    
    ArrayList<CamposNodos> nodos_ = null;
    
    /*
     * Una estructrua de tipo ArrayList cuyo contenido es de tipo CamposArista
     * (clase que agrupa las propiedades de un nodo del sistema representado
     * en pantalla). Su contenido son todas las aristas que el usuario va
     * dibujando en el lienzo de trabajo.
     */
    
    ArrayList<CamposArista> aristas_ = null;

    /* 
     * Varible que empleo para que la insercción en el ArrayList
     * se realice en la misma posición, ya que de otra manera tendría
     * que llevar un índice con la posición más recientemente introducida
     * ya que los campos se van rellenando en sucesivas llamadas al sistema 
     */

    private CamposArista ca;
   
    /*
     * Se usa para poder dibujar las aristas en el lienzo una vez se ha
     * cargado en el sistema el grafo desde un fichero de texto.
     */
    
    private boolean es_adyacente_arista = false;
    
    /*
     * Se usa para establecer o no el movimiento de nodos en el lienzo
     * por parte del usuario, debido a que la visualación anterior o mostrada
     * no sea óptima.
     */
    
    private boolean movimiento = false;
    
    /*
     * Se usa para poder pasar información al escuchador de ratón que se 
     * encarga de dar movimiento a los nodos. Su contenido serán coordenadas
     * del lienzo.
     */
    
    private Par coordenadas;
    
    /*
     * Se usa para conocer en tiempo de ejecución cual es el nodo que
     * el usuario ha decidido mover de posición en el lienzo.
     */
    
    private int nodo_movimiento;
    
    /*
     * Se usa para almacenar las coordenadas del punto origen de la
     * arista que se ha dibujado en el lienzo. Sirve para no dibujar
     * dos veces la misma etiqueta o ponderación de la arista.
     */
    
    private ArrayList<Par> punto_inicial;
    
    /*
     * Se usa para almacenar las coordenadas del punto destino de la
     * arista que se ha dibujado en el lienzo. Sirve para no dibujar
     * dos veces la misma etiqueta o pondreación de la arista.
     */
    
    private ArrayList<Par> punto_final;
    
    /*
     * Se usa para saber si la arista, según el orden de llegada, se ha
     * pintado o no ya en el lienzo según las coordenadas que fueran. Simplemente
     * sirve para comprobar que haya una arista previa a comprobar que la nueva
     * es la misma pero en dirección opuesta (para los grafos no dirigidos).
     */
    
    private ArrayList<Integer> pintada;
    
    
    public boolean ExisteArista(int x, int y)
    {
        for(int i=0; i < aristas_.size(); i++)
        {
            if(aristas_.get(i).nodos_arista().primero() == x && 
                    aristas_.get(i).nodos_arista().segundo() == y)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Método observador.
     * @return Devuelve un tipo boolean que contendrá el valor true si el
     * grafo es dirigido o false sino lo es.
     */
    
    public boolean dirigido_campo()
    {
        return dirigido;
    }
    
    /**
     * Método modificador.
     * @param valor parámetro de entrada de tipo boolean que especificará
     * si se representan los costes asociados a las aristas del lienzo resultado
     * (true) o no (false).
     */
    
    public void no_costes(boolean valor)
    {
        no_costes = valor;
    }
    
    /**
     * Método observador.
     * @return Devuelve un valor de tipo boolean que especificará si el
     * grafo es de tipo adyacente (true) o no (false).
     */
    
    public boolean adyacente_campo()
    {
        return adyacente;
    }
    
    /**
     * Método observador.
     * @return Devuelve una estructura de tipo ArrayList cuyo contenido es de
     * tipo CamposNodos (clase que agrupa las propiedades de los nodos
     * representados en el lienzo de trabajo). El contenido de la estructura
     * son los nodos representados en el lienzo de trabajo que el usuario
     * ha definido o cargado en el sistema.
     */
    
    public ArrayList<CamposNodos> nodos ()
    {
        return nodos_;
    }
    
    /**
     * Método observador.
     * @return Devuelve una estructura de tipo ArrayList cuyo contenido es de
     * tipo CamposArista (clase que agrupa las propiedades de las aristas
     * representados en el lienzo de trabajo). El contenido de la estructura
     * son las aristas representadas en el lienzo de trabajo que el usuario
     * ha definido o cargado en el sistema.
     */
    
    public ArrayList<CamposArista> aristas()
    {
        return aristas_;
    }
    
    /**
     * Método modificador.
     * Se utiliza para borrar un nodo del lienzo de trabajo.
     * @param n parámetro de entrada de tipo entero que representa el n-ésimo
     * nodo del grafo actual que el usuario esta editando en el lienzo.
     */
    
    void borrar_nodo_arista(int n)
    {
	//System.out.println("El valor de n es:"+n);
	if(!nodos_.isEmpty() && n >= 0)
	    {
		nodos_.remove(n);
	    }    
    }

    /**
     * Método observador
     * Comprueba si existe una arista entre los dos nodos especificados.
     * @param a parámetro de entrada que especifica el nodo origen de la arista
     * @param b parámetro de entrada que especifica el nodo destino de la arista
     * @return Devuelve la posición de la n-ésima arista en la estructura
     * interna del sistema donde se encuentran almacenadas.
     */
    
    private int ComprobarArista(int a, int b)
    {
	for(int i=0; i < aristas_.size(); i++)
	    {
		if(a < ((aristas_.get(i).pos_origen().primero() + aristas_.get(i).pos_destino().primero())/2 + 75) && a > ((aristas_.get(i).pos_origen().primero() + aristas_.get(i).pos_destino().primero())/2 + 10) )
		    {
			if(b < ((aristas_.get(i).pos_origen().segundo() + aristas_.get(i).pos_destino().segundo())/2) + 3 && b > ((aristas_.get(i).pos_origen().segundo() + aristas_.get(i).pos_destino().segundo())/2 - 12))
			    {
				return i;
			    }
		    }
	    }
	
	return -1;
	
    }
    
    /**
     * Método modificador.
     * Una vez que se ha aceptado el grafo de edición en el lienzo
     * se deshabilita la opción de seguir editandolo, borrando para ello
     * los eventos de ratón asociados al lienzo.
     */
    
    public void CambiarEstado()
    {
        grafo_aceptado = true;   
     
        //System.out.println("grafo_aceptado: "+grafo_aceptado);
        //miObjCanvas.removeMouseListener(new ProcRaton());
        
        //miObjCanvas.removeMouseListener(new ProcRaton());
        //miObjCanvas.addMouseMotionListener(new ProcRatonInteractivo());
        
        
    }
    
    /**
     * Método modificador.
     * Método que establece en el sistema la funcionalidad de mover
     * nodos dentro del lienzo de trabajo de grafos.
     * @param valor Parámetro de tipo boolean que permite seleccionar
     * entre movimiento de nodos (true) o deshabilitación del mismo (false).
     */
    
    public void EstablecerMovimiento(boolean valor)
    {
        if(valor)
            movimiento = true;
        else
            movimiento = false;
    }
    
    /**
     * Método modificador.
     * Este método se emplea para modificar el estado interno del grafo
     * como aceptado para el caso de que se defina el grafo a través
     * de un fichero cargado previamente en el sistema y no por la edición
     * de un grafo en el lienzo de trabajo.
     */
    
    public void CambiarEstadoCargar()
    {
        grafo_aceptado = true;
    }
    
    /**
     * Método observador
     * Método que comprueba si para las coordenadas pasadas como parámetros
     * de entrada (a,b) existe alguna coordenada (x,y) que coincida con ellas.
     * Si es así devuelve el n-ésimo termino de la iteración en la búsqueda
     * entre todos los resultados posibles de la estructura interna de nodos.
     * @param a parámetro de entrada de tipo entero que representa la abscisa 
     * (x) del nodo.
     * @param b parámetro de entrada de tipo entero que representa la ordenada
     * (y) del nodo.
     * @return Devuelve el valor entero del nodo correspondiente según
     * las coordenadas cartesianas pasadas con los parámetros (a,b). Sino
     * devuelve -1.
     */
    private int ComprobarNodo(int a, int b)
    {
	for(int i=0; i < nodos_.size(); i++)
	    {
		if(a < (nodos_.get(i).obtener_nodo().primero() + 30) && a > (nodos_.get(i).obtener_nodo().primero() + 3) )
		    {
			if(b < (nodos_.get(i).obtener_nodo().segundo() + 30) && b > (nodos_.get(i).obtener_nodo().segundo() + 3))
			    {
				return i;
			    }
		    }
                
                if(a == nodos_.get(i).obtener_nodo().primero() && b == nodos_.get(i).obtener_nodo().segundo())
                    return i;
	    }
	
	return -1;
    }

    /**
     * Método modificador.
     * @param n parámetro de entrada de tipo entero que representa
     * la posición de la arista a modificar.
     * @param valor parámetro de entrada de tipo String que representa
     * el valor nuevo de la arista seleccionada.
     */

    private void modificar_arista(int n, String valor)
    {
	if(!aristas_.isEmpty() && n>= 0)
	    {
		aristas_.get(n).coste_arista(Integer.parseInt(valor));
	    }

	miObjCanvas.repaint();
    }
    
    /**
     * Método modificador.
     * @param n parámetro de entrada entero que representa la posición de la arista
     * en el sistema para ser eliminada.
     */
    
    private void elim_arista(int n)
    {
	if(!aristas_.isEmpty() && n >= 0)
	    aristas_.remove(n);

        /*
         * Volvemos a actualizar el lienzo una vez se ha
         * efectuado el borrado de la arista.
         */
        
	miObjCanvas.repaint();
    }
    
    /**
     * Método modificador.
     * Borra el contenido de la estructura interna de aristas.
     */
    
    private void eliminar_todas_aristas()
    {
	aristas_ = new ArrayList<CamposArista>();
        
        /*
         * Este método simplemente establece a un valor por defecto o predeterminado
         * el contenido de la estructura interna de las aristas.
         */
    }

    /**
     * Método modificador.
     * Este método se emplea para modificar el atributo interno que
     * guarda los nodos del grafo inicial.
     * @param array parámetro de entrada de tipo ArrayList cuyo contenido
     * es de tipo Par (dupla de enteros). 
     */
    
    public void modificar_vector_no_cambia(ArrayList<Par> array)    
    {
        vector_no_cambia = new ArrayList<Par>(array);
    }
    
    /**
     * Método modificador
     * Este método se emplea para modificar el atributo interno
     * que guarda los nodos del grafo resultado de aplicar un algoritmo.
     * @param array parámetro de entrada de tipo ArrayList cuyo contenido
     * es de tipo Par (dupla de enteros).
     */
    
    public void modificar_vector(ArrayList<Par> array)
    {
        vector_resultado = new ArrayList<Par>(array);
    }
    
    /**
     * Método modificador.
     * @param matriz parámetro de entrada de tipo array bidimensional de enteros
     * que representa la matriz de costes/adyacencia del grafo resultado
     * de aplicar un algoritmo al grafo inicial.
     * @param ady parámetro de entrada de tipo boolean que especifica
     * si el grafo resultado es adyacente (true) o no (false).
     * @param dir parámetro de entrada de tipo boolean que especifica
     * si el grafo resultado es dirigido (true) o no (false).
     */
    
    public void grafo_resultante(int [][] matriz, boolean ady, boolean dir)
    {

	nodos_ = new ArrayList<CamposNodos>();
	aristas_ = new ArrayList<CamposArista>();
        dirigido = dir;
        adyacente = ady;
        
        double grado = 0.0;
        double coor_x = 0.0;
        double coor_y = 0.0;
        
        /*
         * Se crea un borderLayout y se fija el espaciado entre los 
         * componentes que va a albergar.
         * 
         */

	BorderLayout miLayout = new BorderLayout();
	miLayout.setVgap(30);
	miLayout.setHgap(30);

	this.setLayout(miLayout);
	
	this.setSize(270,198);


	/*
         * Se añade el objeto MiCanvas creado al Centro del objeto
	 * Frame a través del BorderLayout.
         */
        
        miObjCanvas = null;
        miObjCanvas_fichero = new MiCanvas();

	this.add(miObjCanvas_fichero, "Center");
    
        /*
         * Ahora se podrán ver
         */
        
	this.setVisible(true);
        //System.out.println("Me llamo moi");
        
        /*
         * Introducimos la posición de los nodos correspondientes en la 
         * estructura interna del sistema para nodos_.
         */
        
        for(int i=0; i < matriz.length; i++)
        {
            grado = (i) * 2 * Math.PI/matriz.length;
            coor_x = 100 + 50 * Math.cos(grado);
            coor_y = 100 - 50 * Math.sin(grado);
            
            Par p = new Par();
            p.primero((int)coor_x);
            p.segundo((int)coor_y);
            //System.out.println("Pos_x nodo: "+p.primero()+" Pos_y nodo:"+p.segundo());
            CamposNodos cn = new CamposNodos(p);
            nodos_.add(cn);
            //System.out.println("Me llamo");
            
        }
       
        /*
         * Introducimos la matriz de costes/adyacencia en la estructura
         * interna del sistema para aristas_.
         */
        
        for(int i=0; i < matriz.length; i++)
        {
            for(int j=0; j < matriz.length; j++)
            {
              if(ady)
              {
                  if(matriz[i][j] != 0)
                  {
                      Par p = new Par();
                      p.primero(nodos_.get(i).obtener_nodo().primero()+15);
                      p.segundo(nodos_.get(i).obtener_nodo().segundo()+15);
                      
                      Par q = new Par();
                      q.primero(nodos_.get(j).obtener_nodo().primero()+15);
                      q.segundo(nodos_.get(j).obtener_nodo().segundo()+15);
                                        
                      Par r = new Par();
                      r.primero(ComprobarNodo(p.primero(),p.segundo()));
                      r.segundo(ComprobarNodo(q.primero(),q.segundo()));
                      
                      CamposArista ca_ = new CamposArista();
                      ca_.pos_origen(p);
                      ca_.pos_destino(q);
                      ca_.nodos_arista(r);
                      ca_.coste_arista(1);
                      
                      aristas_.add(ca_);
                     
                  }
              }
              else
              {
                  if(!ady)
                  {
                      if(matriz[i][j] != 100)
                      {
                          //System.out.println("Me llamo lopillo");
                          Par p = new Par();
                          p.primero(nodos_.get(i).obtener_nodo().primero()+15);
                          p.segundo(nodos_.get(i).obtener_nodo().segundo()+15);
                      
                          //System.out.println("i: "+i+"valor x:"+p.primero()+" y:"+p.segundo());
                          
                          Par q = new Par();
                          q.primero(nodos_.get(j).obtener_nodo().primero()+15);
                          q.segundo(nodos_.get(j).obtener_nodo().segundo()+15);
                                        
                          //System.out.println("i: "+i+"valor x:"+q.primero()+" y:"+q.segundo());
                          
                          Par r = new Par();
                          r.primero(ComprobarNodo(p.primero(),p.segundo()));
                          r.segundo(ComprobarNodo(q.primero(),q.segundo()));
                      
                          System.out.println("i: "+i+"valor x_nodo:"+r.primero()+" y_nodo:"+r.segundo());
                          
                          CamposArista ca_ = new CamposArista();
                          ca_.pos_origen(p);
                          ca_.pos_destino(q);
                          ca_.nodos_arista(r);
                          ca_.coste_arista(matriz[i][j]);
                      
                          aristas_.add(ca_);
                     
                      }
                  }
              }
                
            }
        }
        
        LienzoGrafo.this.EstablecerMovimiento(true);
        miObjCanvas_fichero.addMouseListener(new ProcRaton());
        miObjCanvas_fichero.addMouseMotionListener(new ProcRatonInteractivo());
        miObjCanvas_fichero.repaint();    
    }

    /**
     * Método modificador
     * Transforma la información representada sobre el lienzo de trabajo
     * a un tipo grafo más cómodo para trabajar con las demás operaciones.
     * @return Devuelve el contenido representado en el lienzo en un tipo grafo
     * para su posterior uso en diferentes métodos si fuera necesario.
     * @throws Exception 
     */
    
    public Grafo ContenidoLienzo() throws Exception
    {
        Grafo g = new Grafo(nodos_.size(),adyacente,dirigido);
        //System.out.println("Valor de la adyacencia:"+adyacente);
        
        if(!aristas_.isEmpty())
        {
            for(int i=0; i < aristas_.size(); i++)
            {
                g.nueva_arista(aristas_.get(i).nodos_arista().primero(),aristas_.get(i).nodos_arista().segundo(),aristas_.get(i).coste_arista());
            }
            
        }
        
        return g;
    }
    
    
    /*
     * Esta variable de tipo MiCanvas contendrá el lienzo donde el usuario
     * dibujará/editara el grafo inicial de trabajo.
     */

    MiCanvas miObjCanvas = new MiCanvas();
    
    /*
     * Esta variable de tipo MiCanvas contendrá otro lienzo de trabajo
     * donde el grafo será definido por la carga previamente de un grafo
     * guardado en un fichero del sistema.
     */
    
    MiCanvas miObjCanvas_fichero = new MiCanvas();
    
    /**
     * Constructor
     * @param x parámetro de entrada de tipo ArrayList cuyo contenido es
     * de tipo CamposNodos (clase que agrupa las propiedades de los nodos
     * representados en el lienzo de trabajo). El contenido de la estructura
     * son los nodos representados en el lienzo de trabajo que el usuario
     * ha definido o cargado en el sistema.
     * @param y parámetro de entrada de tipo ArrayList cuyo contenido es
     * de tipo CamposArista (clase que agrupa las propiedades de las aristas
     * representadas en el lienzo de trabajo). El contenido de la estructura
     * son las aristas representados en el lienzo de trabajo que el usuario
     * ha definido o cargado en el sistema.
     */
    
    public LienzoGrafo(ArrayList<CamposNodos> x, ArrayList<CamposArista> y)
    {
        nodos_ = new ArrayList<CamposNodos>(x);
        aristas_ = new ArrayList<CamposArista>(y);
        punto_inicial = new ArrayList<Par>();
        punto_final = new ArrayList<Par>();
        pintada = new ArrayList<Integer>(aristas_.size());
        miObjCanvas_fichero.repaint();    
    }
    
    /**
     * Constructor
     */
    
    public LienzoGrafo()
    {
        aristas_ = new ArrayList<CamposArista>();
        nodos_ = new ArrayList<CamposNodos>();
        punto_inicial = new ArrayList<Par>();
        pintada = new ArrayList<Integer>(aristas_.size());
        punto_final = new ArrayList<Par>();
    }
    
    /**
     * Constructor
     */
    
    public LienzoGrafo(LienzoGrafo lienzo_grafo, boolean fichero)
    {
        aristas_ = new ArrayList<CamposArista>();
        nodos_ = new ArrayList<CamposNodos>();
        
        if(!lienzo_grafo.aristas_.isEmpty())
        {
        for(int i=0; i < lienzo_grafo.aristas_.size(); i++)
        {
            Par pos_origen_ = new Par(lienzo_grafo.aristas_.get(i).pos_origen());
            Par pos_destino_ = new Par(lienzo_grafo.aristas_.get(i).pos_destino());
            Par par_nodos_ = new Par(lienzo_grafo.aristas_.get(i).nodos_arista());
            
            CamposArista ca = new CamposArista();
            ca.coste_arista(lienzo_grafo.aristas_.get(i).coste_arista());
            ca.nodos_arista(par_nodos_);
            ca.pos_origen(pos_origen_);
            ca.pos_destino(pos_destino_);
            
            aristas_.add(ca);
            
        }
        }
        
        if(!lienzo_grafo.nodos_.isEmpty())
        {
        for(int i=0; i < lienzo_grafo.nodos_.size(); i++)
        {
            Par coordenadas_nodo_ = new Par(lienzo_grafo.nodos_.get(i).obtener_nodo());
            
            CamposNodos cn = new CamposNodos(coordenadas_nodo_);
            
            nodos_.add(cn);
            
        }
        }
        
        /*
         * Se crea un borderLayout y se fija el espaciado entre los
	 * componentes que va a albergar.
         */

        
	BorderLayout miLayout = new BorderLayout();
	miLayout.setVgap(30);
	miLayout.setHgap(30);

	this.setLayout(miLayout);
	this.setSize(250,198);


	/*
         * Se añade el objeto MiCanvas creado al Centro del objeto
	 * Frame a través del BorderLayout
         */

        if(fichero == false)
        {
            miObjCanvas_fichero = null;
        
            miObjCanvas = new MiCanvas();
        
            this.add(miObjCanvas, "Center");
        }
        else
        {
            if(fichero == true)
            {
                miObjCanvas = null;
                
                miObjCanvas_fichero = new MiCanvas();
                
                this.add(miObjCanvas_fichero, "Center");
            }
        }
        
        /*
         * Ahora se podrán ver
         */
        
	this.setVisible(true);

	/* 
         * Se instancia y registra un objeto receptor de eventos de la ventana
	 * para poder concluir la aplicación cuando el usuario
	 * cierra el Frame.
         */

	/* 
         * Se instancia y registra un objeto Listener para procesar los
	 * eventos del ratón y poder determinar las coordenadas en que se 
	 * encuentra el cursor cada vez que el usuario pulse el botón sobre
	 * el objeto MiCanvas.
	 * El objeto receptor de eventos es instanciado anónimamente y no tiene
	 * ninguna referencia de MiCanvas, ya que no se le pasa nada
	 * en el constructor.
         */

        if(fichero == false)
            miObjCanvas.repaint();
        else
            miObjCanvas_fichero.repaint();
        
    }
    
    /**
     * Constructor
     * @param matriz parámetro de entrada de tipo array bidimensional de
     * enteros que representa la matriz de costes/adyacencia del grafo inicial 
     * creado por el usuario o cargado previamente en el sistema. 
     * @param ady parámetro de entrada de tipo boolean que especifica si
     * el grafo es de adyacencia (true) o no (false).
     * @param dir parámetro de entrada de tipo boolean que especifica si
     * el grafo es dirigido (true) o no (false).
     */
    
    public LienzoGrafo(int [][] matriz, boolean ady, boolean dir)
    {
    	
	nodos_ = new ArrayList<CamposNodos>();
	aristas_ = new ArrayList<CamposArista>();
        punto_inicial = new ArrayList<Par>();
        punto_final = new ArrayList<Par>();
        pintada = new ArrayList<Integer>(aristas_.size());
        dirigido = dir;
        adyacente = ady;
        
        double grado = 0.0;
        double coor_x = 0.0;
        double coor_y = 0.0;
        
        /*
         * Se crea un borderLayout y se fija el espaciado entre los
	 * componentes que va a albergar.
         */


	BorderLayout miLayout = new BorderLayout();
	miLayout.setVgap(30);
	miLayout.setHgap(30);

	this.setLayout(miLayout);
	
	this.setSize(250,198);


	/*
         * Se añade el objeto MiCanvas creado al Centro del objeto
	 * Frame a través del BorderLayout
         */
        
        miObjCanvas = null;
        miObjCanvas_fichero = new MiCanvas();

	this.add(miObjCanvas_fichero, "Center");
    
        /*
         * Ahora se podrán ver
         */
        
	this.setVisible(true);
        //System.out.println("Me llamo moi");
        for(int i=0; i < matriz.length; i++)
        {
            grado = (i) * 2 * Math.PI/matriz.length;
            coor_x = 50 + 50 + 50 * Math.cos(grado);
            coor_y = 50 + 50 - 50 * Math.sin(grado);
            
            Par p = new Par();
            p.primero((int)coor_x);
            p.segundo((int)coor_y);
            //System.out.println("nodo: "+i+"Pos_x nodo: "+p.primero()+" Pos_y nodo:"+p.segundo());
            CamposNodos cn = new CamposNodos(p);
            nodos_.add(cn);
            //System.out.println("Me llamo");
            
        }
        
        for(int i=0; i < matriz.length; i++)
        {
            for(int j=0; j < matriz.length; j++)
            {
              if(ady)
              {
                  if(matriz[i][j] != 0)
                  {                     
                      Par p = new Par();
                      p.primero(nodos_.get(i).obtener_nodo().primero()+15);
                      p.segundo(nodos_.get(i).obtener_nodo().segundo()+15);
                      //System.out.println("vertices_matriz: i: "+i+" j: "+j);
                      //System.out.println("Nodo origen: "+ComprobarNodo(p.primero(),p.segundo()));
                      //System.out.println("Coordenadas x: "+p.primero()+ "y: "+p.segundo());
                      
                      Par q = new Par();
                      q.primero(nodos_.get(j).obtener_nodo().primero()+15);
                      q.segundo(nodos_.get(j).obtener_nodo().segundo()+15);
                      //System.out.println("Nodo destino: "+ComprobarNodo(q.primero(),q.segundo()));
                      //System.out.println("Coordenadas x: "+q.primero()+ "y: "+q.segundo());
                                        
                      Par r = new Par();
                      r.primero(ComprobarNodo(p.primero(),p.segundo()));
                      r.segundo(ComprobarNodo(q.primero(),q.segundo()));
                      
                      
                      CamposArista ca_ = new CamposArista();
                      ca_.pos_origen(p);
                      ca_.pos_destino(q);
                      ca_.nodos_arista(r);
                      ca_.coste_arista(matriz[i][j]);
                      
                      aristas_.add(ca_);
                     
                  }
              }
              else
              {
                  if(!ady)
                  {
                      if(matriz[i][j] != 100)
                      {
                          //System.out.println("Me llamo lopillo");
                          Par p = new Par();
                          p.primero(nodos_.get(i).obtener_nodo().primero()+15);
                          p.segundo(nodos_.get(i).obtener_nodo().segundo()+15);
                      
                          //System.out.println("i: "+i+"valor x:"+p.primero()+" y:"+p.segundo());
                          
                          Par q = new Par();
                          q.primero(nodos_.get(j).obtener_nodo().primero()+15);
                          q.segundo(nodos_.get(j).obtener_nodo().segundo()+15);
                                        
                          //System.out.println("i: "+i+"valor x:"+q.primero()+" y:"+q.segundo());
                          
                          Par r = new Par();
                          r.primero(ComprobarNodo(p.primero(),p.segundo()));
                          r.segundo(ComprobarNodo(q.primero(),q.segundo()));
                      
                          System.out.println("i: "+i+"valor x_nodo:"+r.primero()+" y_nodo:"+r.segundo());
                          
                          CamposArista ca_ = new CamposArista();
                          ca_.pos_origen(p);
                          ca_.pos_destino(q);
                          ca_.nodos_arista(r);
                          ca_.coste_arista(matriz[i][j]);
                      
                          aristas_.add(ca_);
                     
                      }
                  }
              }
                
            }
        }
        
        LienzoGrafo.this.EstablecerMovimiento(true);
        miObjCanvas_fichero.addMouseListener(new ProcRaton());
        miObjCanvas_fichero.addMouseMotionListener(new ProcRatonInteractivo());
        miObjCanvas_fichero.repaint();
    }

    /**
     * Constructor
     * @param botones parámetro de entrada de tipo componente de la biblioteca
     * de java swing JButton. Es un array de elementos del tipo JButton que
     * servirán de base para la edición del lienzo de trabajo del grafo
     * inicial por parte del usuario.
     * @param ady parámetro de entrada de tipo boolean que especifica si
     * el grafo es de adyacencia (true) o no (false).
     * @param dir parámetro de entrada de tipo boolean que especifica si
     * el grafo es dirigido (true) o no (false).
     */
    
    public LienzoGrafo(JButton [] botones, boolean ady, boolean dir){

	nodos_ = new ArrayList<CamposNodos>();
	aristas_ = new ArrayList<CamposArista>();
        punto_inicial = new ArrayList<Par>();
        punto_final = new ArrayList<Par>();
        pintada = new ArrayList<Integer>(aristas_.size());
        adyacente = ady;
        dirigido = dir;

        //System.out.println("Valor adyacente:"+adyacente);
        //System.out.println("Valor dirigido:"+dirigido);
                
        /*
         * Se crea un borderLayout y se fija el espaciado entre los
	 * componentes que va a albergar.
         */

        
	BorderLayout miLayout = new BorderLayout();
	miLayout.setVgap(30);
	miLayout.setHgap(30);

	this.setLayout(miLayout);
	this.setSize(250,198);


	/*
         * Se añade el objeto MiCanvas creado al Centro del objeto
	 * Frame a través del BorderLayout
         */

        miObjCanvas_fichero = null;
        
        miObjCanvas = new MiCanvas();
        
	this.add(miObjCanvas, "Center");

	/*
         * Se crea el menu de popup para la elección entre borrar o modificar
	 * arista.
         */

	ActionListener actionListen = new ActionListener() {
            @Override
		public void actionPerformed(ActionEvent evento) {
		    if("Borrar Arista".equals(((JMenuItem) evento.getSource()).getText()))
			{
			    //System.out.println("Borra la arista: "+arista_modificar);

			    elim_arista(arista_modificar);
			}
		    
		    if("Modificar Coste".equals(((JMenuItem) evento.getSource()).getText()))
			{
			    String coste = JOptionPane.showInputDialog("Introduzca el coste de la arista");

			    //System.out.println("Se ha modificado la arista: "+arista_modificar);
			    modificar_arista(arista_modificar,coste);
			}
		}
	    };
	
	JMenuItem item = new JMenuItem("Borrar Arista");
	item.addActionListener(actionListen);
	popup.add(item);
	
	item = new JMenuItem("Modificar Coste");
	item.addActionListener(actionListen);
	popup.add(item);
	
	
	/*
         * Se añaden los botones no-funcionales en los bordes del objeto
	 * Frame a través del BorderLayout
         */

	/*
         * NODOS
         */
        
	botones[0].addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent evento)
		{
		    continuar=true;
		    borrado=false;
                    LienzoGrafo.this.EstablecerMovimiento(false);
		    miObjCanvas.addMouseListener(new ProcRaton());
		}
	    });

	/*
         * ARISTAS
         */
        
	botones[1].addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent evento)
		{
		    continuar=false;
		    borrado=false;
                    LienzoGrafo.this.EstablecerMovimiento(false);
		    miObjCanvas.addMouseListener(new ProcRaton());
		}
	    });

	/*
         * LIMPIAR LIENZO
         */
        
	botones[2].addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent evento)
		{
                    nodos_ = new ArrayList<CamposNodos>();
                    aristas_ = new ArrayList<CamposArista>();
                    LienzoGrafo.this.EstablecerMovimiento(false);
		    miObjCanvas.repaint();

		}
	    });

        /*
         * HABILITAR MOVIMIENTO
         */
        
        botones[3].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent evento)
                    {
                        LienzoGrafo.this.EstablecerMovimiento(true);
                        miObjCanvas.addMouseMotionListener(new ProcRatonInteractivo());
                    }
                });
	/*
         * Ahora se podrán ver
         */
        
	this.setVisible(true);

	/* 
         * Se instancia y registra un objeto receptor de eventos de la ventana
	 * para poder concluir la aplicación cuando el usuario
	 * cierra el Frame.
         */

	/* 
         * Se instancia y registra un objeto Listener para procesar los
	 * eventos del ratón y poder determinar las coordenadas en que se 
	 * encuentra el cursor cada vez que el usuario pulse el botón sobre
	 * el objeto MiCanvas.
	 * El objeto receptor de eventos es instanciado anónimamente y no tiene
	 * ninguna referencia de MiCanvas, ya que no se le pasa nada
	 * en el constructor.
         */

        miObjCanvas.repaint();
    
    }

    /**
     * CLASE INTERNA MiCanvas
     */
    
    private class MiCanvas extends Canvas{

        /**
         * Constructor clase interna de LienzoGrafo
         */
        
	public MiCanvas()
	{
            /*
             * Color del fondo del lienzo
             */
            
	    this.setBackground(Color.white);
          
	}

	/**
         * Método modificador.
         * Se sobreescribe el método paint()
         * @param g parámetro de entrada que representa una clase abstracta
         * donde se representarán objetos visuales en la ventana principal
         * del frame (o componente de la swing de java) del cual se herede 
         * esta clase.
         */

        @Override
	public void paint(Graphics g)
	{
            update(g);
        }
        
        @Override
        public void update(Graphics g)
        {
	    Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//antialiasing

            BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2dbi = bi.createGraphics();
            g2dbi.setClip(LienzoGrafo.this.getX(), LienzoGrafo.this.getY(), LienzoGrafo.this.getWidth(), LienzoGrafo.this.getHeight());
            pintarLienzo(g2dbi);

            g2d.drawImage(bi, 0, 0, this);
        }
        
        public void pintarLienzo(Graphics2D g2)
        { 
            int nodo_origen = 0;
            int nodo_destino = 0;
	    boolean seguir = true;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//antialiasing
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	    
	    if(aristas_.isEmpty())
                System.out.println("El vector de aristas esta vacío.");
            
	    if(!aristas_.isEmpty()){

		for(int j=0; j < aristas_.size(); j++)
		    {
                        nodo_origen = ComprobarNodo(aristas_.get(j).pos_origen().primero(),aristas_.get(j).pos_origen().segundo());
                        nodo_destino = ComprobarNodo(aristas_.get(j).pos_destino().primero(),aristas_.get(j).pos_destino().segundo());
                        
			if((nodo_origen) != -1 && (nodo_destino) != -1 )
			    {
                                
				drawArrow(g2,aristas_.get(j).pos_origen().primero(),aristas_.get(j).pos_origen().segundo(),aristas_.get(j).pos_destino().primero(),aristas_.get(j).pos_destino().segundo());
                                //drawArrow(g2,nodos_.get(nodo_origen).obtener_nodo().primero()+30,nodos_.get(nodo_origen).obtener_nodo().segundo()+15,nodos_.get(nodo_destino).obtener_nodo().primero(),nodos_.get(nodo_destino).obtener_nodo().segundo()+15);
				String coste;
                                
                                if(!grafo_aceptado)
                                {
                                    if(aristas_.get(j).coste_arista() == 0)
				    {
                                        coste_valido = true;
                                        do{
					coste = JOptionPane.showInputDialog("Introduzca el coste de la arista");
                                        //System.out.println("dialogo");
                                        //System.out.println("adyacente_dialogo: "+adyacente);
                                        
                                        try{
                                        if(coste == null || coste.isEmpty())
                                        {
                                            coste_valido=false;
                                            //System.out.println("entro aki");
                                        }
                                        else
                                        {
                                        if(adyacente && Integer.parseInt(coste) == 1)                                        
                                            coste_valido=false;
                                        
                                        
                                        if(!adyacente && Integer.parseInt(coste) < 100 && Integer.parseInt(coste) != 0)
                                            coste_valido=false;
                                        
                                        }}catch(NumberFormatException e)
                                        {
                                            coste_valido = true;
                                        }
                                        }while(coste_valido);

                                        if(coste != null && !coste.isEmpty())
                                        {
                                            
                                            aristas_.get(j).coste_arista(Integer.parseInt(coste));
                                            					
                                            Par p = new Par();
                                            p.primero(ComprobarNodo(aristas_.get(j).pos_origen().primero(),aristas_.get(j).pos_origen().segundo()));
                                            p.segundo(ComprobarNodo(aristas_.get(j).pos_destino().primero(),aristas_.get(j).pos_destino().segundo()));
                                            aristas_.get(j).nodos_arista(p);

                                        }
                                        
				    }
                                    else
                                    {
                                        //System.out.println("Entro aqui para arista: i:"+aristas_.get(j).nodos_arista().primero()+" j:"+aristas_.get(j).nodos_arista().segundo());
                                    }
                                }
			    }
			else
			    {
                                if(!adyacente)
                                {
                                //System.out.println("Se ha entrado aki sin permiso");
                                aristas_.remove(j);
				super.repaint();
                                }
                                else
                                {
                                    aristas_.remove(j);
                                }
                                /*if(aristas_.get(j).coste_arista() == 0)
                                {
                                    aristas_.remove(j);
				    super.repaint();
                                }*/
			    }
		    

		    }

	    }

	    for(int k=0; k < aristas_.size(); k++)
		{
		    if(!aristas_.isEmpty())
			{
                          
                            if(aristas_.get(k).coste_arista() != 0)
                            {
                                    drawCaracter(g2,aristas_.get(k).pos_origen().primero(),aristas_.get(k).pos_origen().segundo(),aristas_.get(k).pos_destino().primero(),aristas_.get(k).pos_destino().segundo(),""+aristas_.get(k).coste_arista());
                            }
                            else
                            {
                                aristas_.remove(k);
				super.repaint();
                            }
			}
		}
            
            if(!nodos_.isEmpty()){
		for(int j=0; j < nodos_.size() ; j++)
		    {
                        g2.setColor(new Color(204,204,255));
                        
                        Ellipse2D elipse = new Ellipse2D.Double(nodos_.get(j).obtener_nodo().primero(), nodos_.get(j).obtener_nodo().segundo(), 30, 30);
                        elipse.setFrame(nodos_.get(j).obtener_nodo().primero(), nodos_.get(j).obtener_nodo().segundo(), 30, 30);
                        
			g2.draw(elipse);	
                        
                        g2.setColor(new Color(204,255,204));
                        
                        g2.fill(elipse);	 
                        
                        g2.setColor(Color.BLACK);
			etiqueta_nodos(g2,nodos_.get(j).obtener_nodo().primero()+12,nodos_.get(j).obtener_nodo().segundo()+20,j);
		    }}
	    else
		System.out.println("El vector esta vacío");

	}


        /**
         * Método modificador.
         * Etiqueta el nodo seleccionado a través de sus coordenadas y
         * su posición con respecto a la estructura interna del sistema.
         * @param h parámetro de entrada que representa una clase abstracta
         * donde se representarán objetos visuales en la ventana principal
         * del frame (o componente de la swing de java) del cual se herede 
         * este método de clase.
         * @param x parámetro de entrada de tipo entero que representa la 
         * abscisa del sistema de coordenadas del Canvas donde se situa.
         * @param y parámetro de entrada de tipo entero que representa la
         * ordenada del sistema de coordenadas del Canvas donde se situa.
         * @param nodo parámetro de entrada de tipo entero que especifica
         * la posoción n-ésima del nodo donde se colocará la etiqueta
         * corresondiente.
         */
        
	public void etiqueta_nodos(Graphics2D h, int x, int y, int nodo)
	{
	    h.drawString(""+nodo,x,y);
	}

        /**
         * Método modificador
         * Este método se encarga de dibujar sobre el lienzo de trabajo
         * las aristas correspondientes.
         * @param h parámetro de entrada que representa una clase abstracta
         * donde se representarán objetos visuales en la ventana principal
         * del frame (o componente de la swing de java) del cual se herede 
         * este método de clase.
         * @param a parámetro de entrada de tipo entero que representa la 
         * abscisa del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (a,b) correspondiente al nodo origen.
         * @param b parámetro de entrada de tipo entero que representa la 
         * ordenada del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (a,b) correspondiente al nodo origen.
         * @param c parámetro de entrada de tipo entero que representa la 
         * abscisa del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (c,d) correspondiente al nodo destino.
         * @param d parámetro de entrada de tipo entero que representa la 
         * ordenada del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (c,d) correspondiente al nodo destino.
         */
        
	public void drawArrow(Graphics2D h, int a, int b, int c, int d)
	{
            double alfa=Math.atan2(d-b,c-a);
	    double alfa_dir=Math.atan2(b-d,a-c);
            
            int destino_primero = a+3*(c-a)/4;
            int destino_segundo = b+3*(d-b)/4;
            
            if(vector_resultado == null)
            {
            
            /*
             * Estas dos variables hacen referencia al punto medio de la
             * arista en donde ira la flecha que indicara el sentido
             * de la navegacion.
             */
            
            h.setColor(Color.black);
            if(dirigido)
            {
            
            /*
             * El valor de k establece cuan grande sera la flecha de
             * navegabilidad de la arista. Cuanto mas grande sea mas grande
             * sera la flecha.
             */
            
	    int k=6;
	    int x_a=(int)(destino_primero-k*Math.cos(alfa+1));
	    int y_a=(int)(destino_segundo-k*Math.sin(alfa+1));
            
	    /*
             * Se dibuja un extremo de la dirección de la flecha
	     * (parte superior)
             */
            
	    h.drawLine(x_a,y_a,destino_primero,destino_segundo);

	    x_a=(int)(destino_primero-k*Math.cos(alfa-1));
	    y_a=(int)(destino_segundo-k*Math.sin(alfa-1));
            
	    /*
             * Se dibuja el otro extremo de la dirección de la flecha.
	     * (parte inferior)
             */
            
	    h.drawLine(x_a,y_a,destino_primero,destino_segundo); 
            }
	    h.drawLine(a,b,c,d);
            
            }
            else
            {
                for(int i=0; i < vector_resultado.size(); i++)
                {
                    
                if(ComprobarNodo(a,b) == vector_resultado.get(i).primero() && ComprobarNodo(c,d) == vector_resultado.get(i).segundo())
                {
                    
                    /*
                    * Estas dos variables hacen referencia al punto medio de la
                    * arista en donde ira la flecha que indicara el sentido
                    * de la navegacion.
                    */
            
                    h.setColor(Color.red);
                    
                    if(dirigido)
                    {
            
                    /*
                    * El valor de k establece cuan grande sera la flecha de
                    * navegabilidad de la arista. Cuanto mas grande sea mas grande
                    * sera la flecha.
                    */
            
                    int k=6;
                    int x_a=(int)(destino_primero-k*Math.cos(alfa+1));
                    int y_a=(int)(destino_segundo-k*Math.sin(alfa+1));
            
                    /*
                    * Se dibuja un extremo de la dirección de la flecha
                    * (parte superior)
                    */
            
                    h.drawLine(x_a,y_a,destino_primero,destino_segundo);

                    x_a=(int)(destino_primero-k*Math.cos(alfa-1));
                    y_a=(int)(destino_segundo-k*Math.sin(alfa-1));
            
                    /*
                    * Se dibuja el otro extremo de la dirección de la flecha.
                    * (parte inferior)
                    */
            
                    h.drawLine(x_a,y_a,destino_primero,destino_segundo); 
                    }
                    h.drawLine(a,b,c,d);
                    
                }
                
                if(vector_no_cambia != null)
                {
                if(i <= vector_no_cambia.size())
                {
                if(ComprobarNodo(a,b) == vector_no_cambia.get(i).primero() && ComprobarNodo(c,d) == vector_no_cambia.get(i).segundo())
                {
                    
                    /*
                    * Estas dos variables hacen referencia al punto medio de la
                    * arista en donde ira la flecha que indicara el sentido
                    * de la navegacion.
                    */
            
                    h.setColor(Color.red);
                    
                    if(dirigido)
                    {
            
                    /*
                    * El valor de k establece cuan grande sera la flecha de
                    * navegabilidad de la arista. Cuanto mas grande sea mas grande
                    * sera la flecha.
                    */
            
                    int k=6;
                    int x_a=(int)(destino_primero-k*Math.cos(alfa+1));
                    int y_a=(int)(destino_segundo-k*Math.sin(alfa+1));
            
                    /*
                     * Se dibuja un extremo de la dirección de la flecha
                     * (parte superior)
                    */
            
                    h.drawLine(x_a,y_a,destino_primero,destino_segundo);

                    x_a=(int)(destino_primero-k*Math.cos(alfa-1));
                    y_a=(int)(destino_segundo-k*Math.sin(alfa-1));
            
                    /*
                    * Se dibuja el otro extremo de la dirección de la flecha.
                    * (parte inferior)
                    */
            
                    h.drawLine(x_a,y_a,destino_primero,destino_segundo); 
                    }
                    h.drawLine(a,b,c,d);
                    }
                }
                }
                }
            }
        }


        /**
         * Método modificador.
         * Este método se emplea para representar en el lienzo, en las
         * coordenadas especificadas por los parámetros de entrada, el coste
         * asociado a las aristas del grafo.
         * @param h parámetro de entrada que representa una clase abstracta
         * donde se representarán objetos visuales en la ventana principal
         * del frame (o componente de la swing de java) del cual se herede 
         * este método de clase.
         * @param a parámetro de entrada de tipo entero que representa la 
         * abscisa del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (a,b) correspondiente al nodo origen.
         * @param b parámetro de entrada de tipo entero que representa la 
         * ordenada del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (a,b) correspondiente al nodo origen.
         * @param c parámetro de entrada de tipo entero que representa la 
         * abscisa del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (c,d) correspondiente al nodo destino.
         * @param d parámetro de entrada de tipo entero que representa la 
         * ordenada del sistema de coordenadas del Canvas donde se situa. 
         * Pertence a la dupla (c,d) correspondiente al nodo destino.
         * @param texto parámetro de entrada de tipo String que representa
         * el contenido asociado a la arista que será representada
         * en el lienzo.
         */
        
	public void drawCaracter(Graphics2D h, int a, int b, int c, int d, String texto)
	{
            double alfa=Math.atan2(d-b,c-a);
            
            Par punto_ini = new Par(a,b);
            Par punto_fin = new Par(c,d);
            
            punto_inicial.add(punto_ini);
            punto_final.add(punto_fin);
            
            
            boolean repetida = false;
            
            int mitad_x = (a+c)/2;
            int mitad_y = (b+d)/2;
                    
            /*
            for(int i=0; i < punto_inicial.size(); ++i)
            {
                System.out.println("HOLA HOLA");
                System.out.println("Tam de pintada: "+pintada.size());
                System.out.println("a: "+a+" b: "+b+" c: "+c+" d: "+d);
                System.out.println("punto_ini-> x: "+punto_inicial.get(i).primero()+" y: "+punto_inicial.get(i).segundo());
                System.out.println("punto_final-> x: "+punto_final.get(i).primero()+" y: "+punto_final.get(i).segundo());
                
                if(pintada.size() == punto_inicial.size())
                {
                if(punto_ini == punto_inicial.get(i) && punto_fin == punto_final.get(i) && pintada.get(i) == 1)
                {
                    System.out.println("Se repite");
                    repetida = true;
                }
                }
            }*/
            
            if(!repetida)
            {
            if(vector_resultado == null)
            {
                h.setColor(Color.red);
                
                /*
                 * Se establece el texto en la perpendicular del punto medio
                 * de la arista. Se realiza la conversion explicita porque
                 * el sistema de coordenadas trabaja con numeros enteros.
                 */
                
                System.out.println("Por aqui");
                h.drawString(texto,(int)(mitad_x+15*Math.cos(alfa+90)),(int)(mitad_y-12*Math.sin(alfa+90)));
                pintada.add(1);
            }
            else
            {
                if(!no_costes)
                {
                for(int i=0; i < vector_resultado.size(); i++)
                {
                  if(ComprobarNodo(a,b) == vector_resultado.get(i).primero() && ComprobarNodo(c,d) == vector_resultado.get(i).segundo())    
                  {
                      h.setColor(Color.red);
                      h.drawString(texto,(int)(mitad_x+15*Math.cos(alfa+90)),(int)(mitad_y-12*Math.sin(alfa+90)));
                      pintada.add(1);
                  }
                }
                }
                if(vector_no_cambia != null)
                {
                for(int i=0; i < vector_no_cambia.size(); i++)
                {
                  if(ComprobarNodo(a,b) == vector_no_cambia.get(i).primero() && ComprobarNodo(c,d) == vector_no_cambia.get(i).segundo())    
                  {
                      h.setColor(Color.red);
                      h.drawString(texto,(int)(mitad_x+15*Math.cos(alfa+90)),(int)(mitad_y-12*Math.sin(alfa+90)));
                      pintada.add(1);
                  }
                }
                }
            }
            }
	}

    
    }

    /**
     * CLASE INTERNA de LienzoGrafo
     */
    
    private class ProcRatonInteractivo extends MouseMotionAdapter
    {   
         
         public void mouseDragged(MouseEvent evento)
         {
             //System.out.println("Entro en el Motion");
             int nodo_pos = 0;
             
             
             /*
              * Estas variables las empleo para saber a que distancia, dentro
              * del radio del nodo, se encuetra de la abcisa y la ordenada
              * ya que cuanto más grande es la posicíon de la dupla coordenadas
              * mayor sera la diferencia con respecto al punto 0 de la abcisa
              * y ordenada. Así sucede lo inverso cuanto menor sea la dupla.
              */
             
             int mov_x = 0;
             int mov_y = 0;
             
             if(coordenadas.primero() > 15 &&  coordenadas.primero() <= 30 && coordenadas.segundo() > 15 && coordenadas.segundo() <= 30)
             {
                 mov_x = 30 + coordenadas.primero();
                 mov_y = 30 + coordenadas.segundo();
             }
                          
             if(coordenadas.primero() <= 15 && coordenadas.primero() > 0 && coordenadas.segundo() <= 15 && coordenadas.segundo() > 0)
             {
                 mov_x = 15 + coordenadas.primero();
                 mov_y = 15 + coordenadas.segundo();
             }
                          
             if(coordenadas.primero() > 15 && coordenadas.primero() <= 30 && coordenadas.segundo() <= 15 && coordenadas.segundo() > 0)
             {
                 mov_x = 30 + coordenadas.primero();
                 mov_y = 15 + coordenadas.segundo();
             }
             
             if(coordenadas.primero() <= 15 && coordenadas.primero() > 0 && coordenadas.segundo() > 15 && coordenadas.segundo() <= 30)
             {
                 mov_x = 15 + coordenadas.primero();
                 mov_y = 30 + coordenadas.segundo();
             }
             
             //System.out.println("X: "+evento.getX()+" Y: "+evento.getY());
             //System.out.println("coordenadas.primero(): "+coordenadas.primero()+" coordenadas.segundo(): "+coordenadas.segundo());
             //System.out.println("mov_x: "+mov_x+" mov_y: "+mov_y);
             
             if(evento.getX() > mov_x/2 && evento.getY() > mov_y/2 && evento.getX() < LienzoGrafo.this.getWidth()-15 && evento.getY() < LienzoGrafo.this.getHeight()-15)
             {
             if(movimiento)
             {
                 nodo_pos = ComprobarNodo(evento.getX(),evento.getY());
                 
                 if(nodo_movimiento == nodo_pos)
                 {
                 System.out.println("El nodo es: "+nodo_pos);
                 Par p = new Par();
                 p.primero((int)evento.getX()-coordenadas.primero());
                 p.segundo((int)evento.getY()-coordenadas.segundo());
                 
                 CamposNodos cn = new CamposNodos();
                 cn.introducir_nodo(p);
                         
                 nodos_.set(nodo_movimiento, cn);
                 
                 for(int i=0; i < aristas_.size();i++)
                 {
                     if(aristas_.get(i).nodos_arista().primero() == nodo_pos)
                     {
                         Par q = new Par();
                         q.primero(p.primero()+20);
                         q.segundo(p.segundo()+15);
                         
                         aristas_.get(i).pos_origen(q);
                     }
                     
                     if(aristas_.get(i).nodos_arista().segundo() == nodo_pos)
                     {
                         Par q = new Par();
                         q.primero(p.primero()+20);
                         q.segundo(p.segundo()+15);
                         
                         aristas_.get(i).pos_destino(q);
                     }
                 }
                 
                 evento.getComponent().repaint();
                 }
             }
             
             }
         }
    }
    
    private class ProcRaton extends MouseAdapter {
		
        /**
         * Método modificador
         * Este método sobreescrito se usa para obtener la información
         * de la posición relativa a las aristas que el usuario introduzca.
         * @param evento Evento de entrada que se produce cuando el usuario
         * presiona algún botón del ratón.
         */
        
        @Override
	public void mousePressed( MouseEvent evento )
	{
            if(!movimiento)
            {
            if(!grafo_aceptado)
            {
	    if(!continuar)
		{
		    if(!aristas_.isEmpty() && ComprobarNodo(evento.getX(),evento.getY()) != -1)
			{
			    if( aristas_.get(aristas_.size()-1).pos_origen().primero() != evento.getX() )
				{
				    //System.out.println("Pulso arista");
				    Par p = new Par();
				    p.primero(evento.getX());
				    p.segundo(evento.getY());
				    ca = new CamposArista();
				    ca.pos_origen(p);
				    
				    aristas_.add(ca);
				}
			}
		    else
			{
			    if(ComprobarNodo(evento.getX(),evento.getY()) != -1)
				{
				    //System.out.println("Pulso arista por defecto");
				    Par p = new Par();
				    p.primero(evento.getX());
				    p.segundo(evento.getY());
				    ca = new CamposArista();
				    ca.pos_origen(p);
				    
				    aristas_.add(ca);

				}
			}
		}
            }
            }
            else
            {
                int nodo = ComprobarNodo((int)evento.getX(),(int)evento.getY());
                int coor_x = nodos_.get(nodo).obtener_nodo().primero();
                int coor_y = nodos_.get(nodo).obtener_nodo().segundo();
                if(nodo != - 1)
                {
                if ((((int)evento.getX() >  coor_x && (int)evento.getX() < coor_x + 30) || ((int)evento.getX() > coor_x + 250 && (int)evento.getX() < coor_x + 250 + 30)) && ((int)evento.getY() > coor_y && (int)evento.getY() < coor_y + 30)) {
                    
                    nodo_movimiento = nodo;
                    coordenadas = new Par();
                    coordenadas.primero((int)evento.getX()-coor_x);
                    coordenadas.segundo((int)evento.getY()-coor_y);
                    
                    System.out.println("xactual: "+coordenadas.primero()+" yactual: "+coordenadas.segundo());
                    
                }
                }
            }
	}
    
        /**
         * Método modificador.
         * Este método se emplea para obtener información relacionada con:
         * la posición de la arista, la modificación/eliminación de una arista
         * , la creación de nodos y la eliminación de nodos.
         * @param evento Evento de entrada que se produce cuando el usuario
         * deja de presionar el botón del ratón que en un estado anterior
         * debió de estar presionado.
         */
        
        @Override
	public void mouseReleased( MouseEvent evento )
	{
            if(!movimiento)
            {
            if(!grafo_aceptado)
            {
	    if(!continuar)
		{
		    if(!evento.isMetaDown())
			{
			    if(!aristas_.isEmpty())
				{
				    if(aristas_.get(aristas_.size()-1).pos_destino().primero() != evento.getX() && ComprobarNodo(evento.getX(),evento.getY()) != -1)
					{
					    //System.out.println("Suelto arista");
                                            
                                            /* Comprueba si la nueva arista
                                             * que se creará ya existe en
                                             * el grafo. Si existe la arista
                                             * entre los nodos origen y destino
                                             * se eliminará los datos del origen
                                             * de la arista para la posición
                                             * anterior de la estructura arista_
                                             */
                                            
                                            if(ComprobarNodo(evento.getX(),evento.getY()) == ComprobarNodo(aristas_.get(aristas_.size()-1).pos_origen().primero(),aristas_.get(aristas_.size()-1).pos_origen().segundo()))
                                            {
                                                if(aristas_.get(aristas_.size()-1).pos_destino().primero() == 0)
                                                {
                                                    aristas_.remove(aristas_.size()-1);
                                                }
                                            }
                                            else
                                            {
                                            if(ExisteArista(ComprobarNodo(aristas_.get(aristas_.size()-1).pos_origen().primero(),aristas_.get(aristas_.size()-1).pos_origen().segundo()), ComprobarNodo(evento.getX(),evento.getY())))
                                            {
                                                if(aristas_.get(aristas_.size()-1).pos_destino().primero() == 0)
                                                {
                                                    aristas_.remove(aristas_.size()-1);
                                                }
                                            }
                                            else
                                            {
                                                aristas_.get(aristas_.size()-1).pos_destino().primero(evento.getX());
					        aristas_.get(aristas_.size()-1).pos_destino().segundo(evento.getY());
                                            }
                                            }
			
					}
				}
			    else
				{
				    if(ComprobarNodo(evento.getX(),evento.getY()) != -1)
					{
					    //System.out.println("Suelto arista 2.0");
					    aristas_.get(aristas_.size()-1).pos_destino().primero(evento.getX());
					    aristas_.get(aristas_.size()-1).pos_destino().segundo(evento.getY());
					}
				}
			}
		    else
			{
			    if (!evento.isPopupTrigger() && ComprobarNodo(evento.getX(),evento.getY()) == -1 && ComprobarArista(evento.getX(),evento.getY()) != -1)
				{
				    arista_modificar = -1;
				    arista_modificar = ComprobarArista(evento.getX(),evento.getY());
				    popup.show(((MiCanvas)evento.getComponent()), evento.getX(), evento.getY());
				}

			}
		}
	    else
		{
		    if(!evento.isMetaDown())
			{
			    
			    if(!nodos_.isEmpty())
				{
				    if(nodos_.get(nodos_.size()-1).obtener_nodo().primero() != evento.getX())
					{
					    //System.out.println("Entro aki");
					    Par p = new Par();
					    p.primero(evento.getX());
					    p.segundo(evento.getY());
					    CamposNodos cn = new CamposNodos(p);

					    nodos_.add(cn);
					}
				}
			    else
				{
				    Par p = new Par();
				    p.primero(evento.getX());
				    p.segundo(evento.getY());
				    CamposNodos cn = new CamposNodos(p);
				    
				    nodos_.add(cn);
				}
			}
		    else
			{
			    if(ComprobarNodo(evento.getX(),evento.getY()) != -1)
				borrar_nodo_arista(ComprobarNodo(evento.getX(),evento.getY()));
			}
		    
		}
	    
	    evento.getComponent().repaint();

            }
        }
       
            
    }
	
    }


}