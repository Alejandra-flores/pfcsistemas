/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphvisualx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//import org.apache.commons.io.IOUtils;

/**
 * Fichero Grafico.java
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

public class Grafico extends javax.swing.JFrame {

    /*
     * Atributos privados de la clase.
     */
   
    /*
     * Se empleará para establecer si el grafo es dirigido o no.
     */
   
    private boolean dirigido_valor = true;
    
    /*
     * Se empleará para establecer si el grafo es adyacente o no.
     */

    private boolean adyacente_valor = true;
    
    /*
     * Se empleará para establecer si el grafo es dirigido o no en tiempo de
     * ejecución del programa cuando el usuario seleccione la opción.
     */
    
    private boolean dirigido_valor_nuevo_nodo_ = false;
    
    /*
     * Se empleará para establecer si el grafo es adyacente o no en tiempo de
     * ejecución del programa cuando el usuario seleccione la opción.
     */

    private boolean adyacente_valor_nuevo_nodo_ = false;
    
    /*
     * Se empleará para establecer el número de nodos que tiene el grafo de
     * trabajo actual.
     */
    
    private int numero_de_nodos = 0;
    
    /*
     * Se empleará para almacenar el grafo de trabajo actual en el sistema.
     */

    private Grafo grafo;
    
    /*
     * Se empleará para almacenar el vértice origen para el procesamiento de 
     * los algoritmos de coste mínimos Dijkstra y Bellman-Ford.
     */

    private int vertice_origen = 0;
    
    /*
     * Se empleará para almacenar el lienzo donde sera la representación
     * gráfica del grafo por parte del usuario así como del procesamiento
     * de los algoritmos sobre el grafo.
     */
    
    private LienzoGrafo dibujo;
    
    /*
     * Se empleará para almacenar el lienzo del grafo representado en pantalla
     * obtenido como resultado de la carga satisfactoria de un fichero cuyo
     * contenido es la estructura interna de un grafo previamente definido.
     */
    
    private LienzoGrafo lg_;
    
    /*
     * Se empleará para almacenar el lienzo del grafo representado en pantalla
     * obtenido como resultado del procesamiento de un algoritmo sobre el grafo
     * original generado por el usuario o cargado en el sistema.
     */
    
    private LienzoGrafo pintar_algoritmo;
    
    /*
     * Se empleará para almacenar las contenedores de la biblioteca swing, 
     * en concreto JButton. Este array compone los JButton que crean un nodo [0],
     * crean una arista [1] y eliminar el contenido del grafo [2].
     */
    
    private JButton vector_botones [] = new JButton [4];
    
    /*
     * Se empleará para almacenar la selección que el usuario marque cuando
     * cree un nuevo grafo. En este caso para especificar si el grafo es
     * dirigido o no.
     */
    
    private int eleccion_dirigido = 0;

    /*
     * Se empleará para almacenar la selección que el usuario marque cuando
     * cree un nuevo grafo. En este caso para especificar si el grafo es
     * adyacente o no.
     */

    private int eleccion_adyacente = 0;
    
    /*
     * Se empleará para almacenar el grafo obtenido con el procesamiento de
     * un algoritmo sobre el grafo original creado por el usuario o cargado
     * en el sistema.
     */
    
    private Grafo grafo_resultado;
    
    /*
     * Se usa para saber cuál es el nodo origen que ha establecido el usuario
     * o el algoritmo para ese grafo.
     */
    
    private int nodo_origen_seleccionado = 0;
   
    
    /**
     * Constructor
     */
    
    public Grafico() {
        initComponents();
        setLocationRelativeTo(null);
        BotonAlgoritmo.setVisible(false);
        BotonNodo.setVisible(false);
        BotonArista.setVisible(false);
        BotonLimpiar.setVisible(false);
        BotonMovimiento.setVisible(false);
        BotonAceptarGrafo.setVisible(false);
        BarraEdicionLienzo.setVisible(false);
        BarraEdicionResultado.setVisible(false);
        PanelTexto1.setVisible(false);
        PanelTexto2.setVisible(false);
                

        MenuItemGuardar.setVisible(false);
        MenuItemGuardarImagen.setVisible(false);
        MenuItemGuardarImagenResultado.setVisible(false);
        
        try {
            this.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        vector_botones[0] = BotonNodo;
        vector_botones[1] = BotonArista;
        vector_botones[2] = BotonLimpiar;
        vector_botones[3] = BotonMovimiento;
        
        
        //PanelImagen1.setBounds(20,30,500,800);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-488)/2, (screenSize.height-493)/2, 488, 493);
        this.setSize(780, 625);
        
    }
    
    /** Este método es invocado sin que el constructor inicialize el
     * formulario.
     * PRECAUCIÓN: NO modifique este código. El contenido de este método
     * es generado de nuevo por el sistema en cada ejecución.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        ScrollPanelContenedor = new javax.swing.JScrollPane();
        PanelContenedor = new javax.swing.JPanel();
        BarraEdicionGrafo = new javax.swing.JToolBar();
        BotonNuevoGrafo = new javax.swing.JButton();
        BotonCargarFichero = new javax.swing.JButton();
        BotonAlgoritmo = new javax.swing.JButton();
        BarraEdicionLienzo = new javax.swing.JToolBar();
        BotonNodo = new javax.swing.JButton();
        BotonArista = new javax.swing.JButton();
        BotonLimpiar = new javax.swing.JButton();
        BotonMovimiento = new javax.swing.JButton();
        BotonAceptarGrafo = new javax.swing.JButton();
        BarraEdicionResultado = new javax.swing.JToolBar();
        BotonMovimiento2 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        PanelImagen1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        PanelImagen2 = new javax.swing.JPanel();
        PanelTexto1 = new javax.swing.JPanel();
        jToolBar8 = new javax.swing.JToolBar();
        EtiquetaLineal = new javax.swing.JLabel();
        EtiquetaCircular = new javax.swing.JLabel();
        EtiquetaCompleto = new javax.swing.JLabel();
        jToolBar9 = new javax.swing.JToolBar();
        EtiquetaConexo = new javax.swing.JLabel();
        EtiquetaAciclico = new javax.swing.JLabel();
        EtiquetaDirigido = new javax.swing.JLabel();
        jToolBar10 = new javax.swing.JToolBar();
        EtiquetaPuntosCorte = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BotonPuentes = new javax.swing.JButton();
        jToolBar11 = new javax.swing.JToolBar();
        EtiquetaAdyacente = new javax.swing.JLabel();
        EtiquetaGradoVertice = new javax.swing.JLabel();
        BotonGradoVertice = new javax.swing.JButton();
        PanelTexto2 = new javax.swing.JPanel();
        jToolBar4 = new javax.swing.JToolBar();
        EtiquetaLinealB = new javax.swing.JLabel();
        EtiquetaCircularB = new javax.swing.JLabel();
        EtiquetaCompletoB = new javax.swing.JLabel();
        jToolBar5 = new javax.swing.JToolBar();
        EtiquetaConexoB = new javax.swing.JLabel();
        EtiquetaAciclicoB = new javax.swing.JLabel();
        EtiquetaDirigidoB = new javax.swing.JLabel();
        jToolBar6 = new javax.swing.JToolBar();
        EtiquetaPuntosCorteB = new javax.swing.JLabel();
        EtiquetaPuentesB = new javax.swing.JLabel();
        jToolBar7 = new javax.swing.JToolBar();
        EtiquetaAdyacenteB = new javax.swing.JLabel();
        EtiquetaGradoVerticeB = new javax.swing.JLabel();
        jToolBar3 = new javax.swing.JToolBar();
        BotonGradoVerticeB = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuItemGuardar = new javax.swing.JMenuItem();
        MenuItemGuardarImagen = new javax.swing.JMenuItem();
        MenuItemGuardarImagenResultado = new javax.swing.JMenuItem();
        MenuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuItemGuia = new javax.swing.JMenuItem();
        MenuItemAlgoConceptos = new javax.swing.JMenuItem();
        MenuItemSobreGraphvisualx = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Graphvisualx");
        setBackground(new java.awt.Color(168, 201, 244));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setForeground(new java.awt.Color(168, 201, 244));
        setName("FrameGraphvisualx"); // NOI18N
        setResizable(false);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ScrollPanelContenedor.setBackground(new java.awt.Color(247, 248, 250));
        ScrollPanelContenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ScrollPanelContenedor.setForeground(new java.awt.Color(247, 248, 250));
        ScrollPanelContenedor.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPanelContenedor.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ScrollPanelContenedor.setAutoscrolls(true);
        ScrollPanelContenedor.setMaximumSize(Grafico.this.getMaximumSize());
        ScrollPanelContenedor.setMinimumSize(Grafico.this.getMinimumSize());
        ScrollPanelContenedor.setPreferredSize(Grafico.this.getPreferredSize());

        PanelContenedor.setPreferredSize(new java.awt.Dimension(780, 650));

        BarraEdicionGrafo.setBackground(new java.awt.Color(247, 248, 250));
        BarraEdicionGrafo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Creación grafo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 12), new java.awt.Color(76, 71, 209))); // NOI18N
        BarraEdicionGrafo.setFloatable(false);
        BarraEdicionGrafo.setForeground(new java.awt.Color(247, 248, 250));
        BarraEdicionGrafo.setRollover(true);

        BotonNuevoGrafo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/nuevo.png"))); // NOI18N
        BotonNuevoGrafo.setToolTipText("Nuevo grafo");
        BotonNuevoGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNuevoGrafoActionPerformed(evt);
            }
        });
        BarraEdicionGrafo.add(BotonNuevoGrafo);

        BotonCargarFichero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/cargar.png"))); // NOI18N
        BotonCargarFichero.setToolTipText("Cargar fichero");
        BotonCargarFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCargarFicheroActionPerformed(evt);
            }
        });
        BarraEdicionGrafo.add(BotonCargarFichero);

        BotonAlgoritmo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/algoritmos.png"))); // NOI18N
        BotonAlgoritmo.setToolTipText("Aplicar algoritmos");
        BotonAlgoritmo.setEnabled(false);
        BotonAlgoritmo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonAlgoritmoMouseClicked(evt);
            }
        });
        BarraEdicionGrafo.add(BotonAlgoritmo);

        BarraEdicionLienzo.setBackground(new java.awt.Color(247, 248, 250));
        BarraEdicionLienzo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Edición grafo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 12), new java.awt.Color(76, 71, 209))); // NOI18N
        BarraEdicionLienzo.setFloatable(false);
        BarraEdicionLienzo.setForeground(new java.awt.Color(247, 248, 250));
        BarraEdicionLienzo.setRollover(true);
        BarraEdicionLienzo.setEnabled(false);

        BotonNodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/nodo.png"))); // NOI18N
        BotonNodo.setToolTipText("Pulsa aquí para insertar/borrar algún nodo.");
        BotonNodo.setEnabled(false);
        BotonNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNodoActionPerformed(evt);
            }
        });
        BarraEdicionLienzo.add(BotonNodo);

        BotonArista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/arista.png"))); // NOI18N
        BotonArista.setToolTipText("Pulse aquí para insertar/borrar una arista.");
        BotonArista.setEnabled(false);
        BarraEdicionLienzo.add(BotonArista);

        BotonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/eraser.png"))); // NOI18N
        BotonLimpiar.setToolTipText("Pulse aquí para borrar el contenido de la pantalla.");
        BotonLimpiar.setEnabled(false);
        BarraEdicionLienzo.add(BotonLimpiar);

        BotonMovimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/mover.png"))); // NOI18N
        BotonMovimiento.setToolTipText("Mover nodos.");
        BotonMovimiento.setEnabled(false);
        BotonMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonMovimientoActionPerformed(evt);
            }
        });
        BarraEdicionLienzo.add(BotonMovimiento);

        BotonAceptarGrafo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/aceptar.png"))); // NOI18N
        BotonAceptarGrafo.setToolTipText("Pulsa aquí una vez finalice la edición del grafo.");
        BotonAceptarGrafo.setEnabled(false);
        BotonAceptarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAceptarGrafoActionPerformed(evt);
            }
        });
        BarraEdicionLienzo.add(BotonAceptarGrafo);

        BarraEdicionResultado.setBackground(new java.awt.Color(247, 248, 250));
        BarraEdicionResultado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Edición grafo resultante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(76, 71, 209)));
        BarraEdicionResultado.setFloatable(false);
        BarraEdicionResultado.setForeground(new java.awt.Color(247, 248, 250));

        BotonMovimiento2.setBackground(new java.awt.Color(247, 248, 250));
        BotonMovimiento2.setForeground(new java.awt.Color(247, 248, 250));
        BotonMovimiento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/mover.png"))); // NOI18N
        BotonMovimiento2.setToolTipText("Mover nodos.");
        BotonMovimiento2.setEnabled(false);
        BotonMovimiento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonMovimiento2ActionPerformed(evt);
            }
        });
        BarraEdicionResultado.add(BotonMovimiento2);

        jToolBar1.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lienzo de Trabajo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 12), new java.awt.Color(76, 71, 209))); // NOI18N
        jToolBar1.setFloatable(false);
        jToolBar1.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar1.setFocusable(false);
        jToolBar1.setMaximumSize(new java.awt.Dimension(250, 221));
        jToolBar1.setPreferredSize(new java.awt.Dimension(260, 235));

        PanelImagen1.setBackground(new java.awt.Color(254, 254, 254));
        PanelImagen1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PanelImagen1.setToolTipText("Grafo editable");
        PanelImagen1.setMaximumSize(new java.awt.Dimension(230, 220));
        PanelImagen1.setOpaque(false);
        PanelImagen1.setPreferredSize(new java.awt.Dimension(250, 230));

        javax.swing.GroupLayout PanelImagen1Layout = new javax.swing.GroupLayout(PanelImagen1);
        PanelImagen1.setLayout(PanelImagen1Layout);
        PanelImagen1Layout.setHorizontalGroup(
            PanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        PanelImagen1Layout.setVerticalGroup(
            PanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        jToolBar1.add(PanelImagen1);

        jToolBar2.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lienzo Algoritmos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 12), new java.awt.Color(76, 71, 209))); // NOI18N
        jToolBar2.setFloatable(false);
        jToolBar2.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar2.setRollover(true);

        PanelImagen2.setBackground(new java.awt.Color(247, 248, 250));
        PanelImagen2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PanelImagen2.setToolTipText("Grafo resultado");
        PanelImagen2.setOpaque(false);
        PanelImagen2.setPreferredSize(new java.awt.Dimension(270, 200));
        PanelImagen2.setRequestFocusEnabled(false);

        javax.swing.GroupLayout PanelImagen2Layout = new javax.swing.GroupLayout(PanelImagen2);
        PanelImagen2.setLayout(PanelImagen2Layout);
        PanelImagen2Layout.setHorizontalGroup(
            PanelImagen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        PanelImagen2Layout.setVerticalGroup(
            PanelImagen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        jToolBar2.add(PanelImagen2);

        PanelTexto1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Propiedades ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(76, 71, 209)));
        PanelTexto1.setPreferredSize(new java.awt.Dimension(200, 170));

        jToolBar8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar8.setFloatable(false);
        jToolBar8.setRollover(true);

        EtiquetaLineal.setText("Lineal:");
        EtiquetaLineal.setPreferredSize(new java.awt.Dimension(70, 17));
        jToolBar8.add(EtiquetaLineal);

        EtiquetaCircular.setText("Circular:");
        EtiquetaCircular.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar8.add(EtiquetaCircular);

        EtiquetaCompleto.setText("Completo:");
        EtiquetaCompleto.setPreferredSize(new java.awt.Dimension(100, 17));
        jToolBar8.add(EtiquetaCompleto);

        jToolBar9.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar9.setFloatable(false);
        jToolBar9.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar9.setRollover(true);

        EtiquetaConexo.setText("Conexo:");
        EtiquetaConexo.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar9.add(EtiquetaConexo);

        EtiquetaAciclico.setText("Aciclico:");
        EtiquetaAciclico.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar9.add(EtiquetaAciclico);

        EtiquetaDirigido.setText("Dirigido:");
        EtiquetaDirigido.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar9.add(EtiquetaDirigido);

        jToolBar10.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar10.setFloatable(false);
        jToolBar10.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar10.setRollover(true);

        EtiquetaPuntosCorte.setText("Puntos de corte:");
        EtiquetaPuntosCorte.setPreferredSize(new java.awt.Dimension(200, 17));
        jToolBar10.add(EtiquetaPuntosCorte);

        jLabel1.setText("Puentes:");
        jLabel1.setPreferredSize(new java.awt.Dimension(60, 17));
        jToolBar10.add(jLabel1);

        BotonPuentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/action.gif"))); // NOI18N
        BotonPuentes.setToolTipText("Pulsa aquí para ver los puentes del grafo.");
        BotonPuentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPuentesActionPerformed(evt);
            }
        });
        jToolBar10.add(BotonPuentes);

        jToolBar11.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar11.setFloatable(false);
        jToolBar11.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar11.setRollover(true);

        EtiquetaAdyacente.setText("Adyacente:");
        EtiquetaAdyacente.setPreferredSize(new java.awt.Dimension(110, 17));
        jToolBar11.add(EtiquetaAdyacente);

        EtiquetaGradoVertice.setText("Grado vértice:");
        jToolBar11.add(EtiquetaGradoVertice);

        BotonGradoVertice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/action.gif"))); // NOI18N
        BotonGradoVertice.setToolTipText("Pulsa aquí para insertar el vértice.");
        BotonGradoVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGradoVerticeActionPerformed(evt);
            }
        });
        jToolBar11.add(BotonGradoVertice);

        javax.swing.GroupLayout PanelTexto1Layout = new javax.swing.GroupLayout(PanelTexto1);
        PanelTexto1.setLayout(PanelTexto1Layout);
        PanelTexto1Layout.setHorizontalGroup(
            PanelTexto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTexto1Layout.createSequentialGroup()
                .addGroup(PanelTexto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar10, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar11, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelTexto1Layout.setVerticalGroup(
            PanelTexto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTexto1Layout.createSequentialGroup()
                .addComponent(jToolBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        PanelTexto2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Propiedades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(76, 71, 209)));
        PanelTexto2.setPreferredSize(new java.awt.Dimension(338, 143));

        jToolBar4.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar4.setFloatable(false);
        jToolBar4.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar4.setRollover(true);

        EtiquetaLinealB.setText("Lineal:");
        EtiquetaLinealB.setPreferredSize(new java.awt.Dimension(70, 17));
        jToolBar4.add(EtiquetaLinealB);

        EtiquetaCircularB.setText("Circular:");
        EtiquetaCircularB.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar4.add(EtiquetaCircularB);

        EtiquetaCompletoB.setText("Completo:");
        EtiquetaCompletoB.setPreferredSize(new java.awt.Dimension(100, 17));
        jToolBar4.add(EtiquetaCompletoB);

        jToolBar5.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar5.setFloatable(false);
        jToolBar5.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar5.setRollover(true);

        EtiquetaConexoB.setText("Conexo:");
        EtiquetaConexoB.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar5.add(EtiquetaConexoB);

        EtiquetaAciclicoB.setText("Aciclico:");
        EtiquetaAciclicoB.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar5.add(EtiquetaAciclicoB);

        EtiquetaDirigidoB.setText("Dirigido:");
        EtiquetaDirigidoB.setPreferredSize(new java.awt.Dimension(80, 17));
        jToolBar5.add(EtiquetaDirigidoB);

        jToolBar6.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar6.setFloatable(false);
        jToolBar6.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar6.setRollover(true);

        EtiquetaPuntosCorteB.setText("Puntos de corte:");
        EtiquetaPuntosCorteB.setPreferredSize(new java.awt.Dimension(200, 17));
        jToolBar6.add(EtiquetaPuntosCorteB);

        EtiquetaPuentesB.setText("Puentes:");
        EtiquetaPuentesB.setPreferredSize(new java.awt.Dimension(100, 17));
        jToolBar6.add(EtiquetaPuentesB);

        jToolBar7.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar7.setFloatable(false);
        jToolBar7.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar7.setRollover(true);

        EtiquetaAdyacenteB.setText("Adyacente:");
        EtiquetaAdyacenteB.setPreferredSize(new java.awt.Dimension(110, 17));
        jToolBar7.add(EtiquetaAdyacenteB);

        EtiquetaGradoVerticeB.setText("Grado vértice:");
        EtiquetaGradoVerticeB.setPreferredSize(new java.awt.Dimension(100, 17));
        jToolBar7.add(EtiquetaGradoVerticeB);

        jToolBar3.setBackground(new java.awt.Color(247, 248, 250));
        jToolBar3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar3.setFloatable(false);
        jToolBar3.setForeground(new java.awt.Color(247, 248, 250));
        jToolBar3.setRollover(true);

        BotonGradoVerticeB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/action.gif"))); // NOI18N
        BotonGradoVerticeB.setToolTipText("Pulsa aquí para insertar el vértice.");
        BotonGradoVerticeB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BotonGradoVerticeB.setPreferredSize(new java.awt.Dimension(20, 20));
        BotonGradoVerticeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGradoVerticeBActionPerformed(evt);
            }
        });
        jToolBar3.add(BotonGradoVerticeB);

        jToolBar7.add(jToolBar3);

        javax.swing.GroupLayout PanelTexto2Layout = new javax.swing.GroupLayout(PanelTexto2);
        PanelTexto2.setLayout(PanelTexto2Layout);
        PanelTexto2Layout.setHorizontalGroup(
            PanelTexto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTexto2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTexto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelTexto2Layout.setVerticalGroup(
            PanelTexto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTexto2Layout.createSequentialGroup()
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelContenedorLayout = new javax.swing.GroupLayout(PanelContenedor);
        PanelContenedor.setLayout(PanelContenedorLayout);
        PanelContenedorLayout.setHorizontalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelContenedorLayout.createSequentialGroup()
                        .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelContenedorLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(PanelTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelContenedorLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelContenedorLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelContenedorLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(PanelTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelContenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelContenedorLayout.createSequentialGroup()
                                .addComponent(BarraEdicionLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addComponent(BarraEdicionResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BarraEdicionGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        PanelContenedorLayout.setVerticalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(BarraEdicionGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BarraEdicionResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BarraEdicionLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelTexto1, 0, 149, Short.MAX_VALUE)
                    .addComponent(PanelTexto2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(86, 86, 86))
        );

        ScrollPanelContenedor.setViewportView(PanelContenedor);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/archivo.png"))); // NOI18N
        jMenu1.setText("Archivo");

        MenuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/guardar.png"))); // NOI18N
        MenuItemGuardar.setText("Guardar grafo inicial");
        MenuItemGuardar.setToolTipText("Pulsa aquí para guardar en un fichero la estructura del grafo inicial.");
        MenuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemGuardar);

        MenuItemGuardarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/guardar_imagen.png"))); // NOI18N
        MenuItemGuardarImagen.setText("Guardar grafo como imagen");
        MenuItemGuardarImagen.setToolTipText("Pulsa aquí para guardar el grafo como imagen.");
        MenuItemGuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemGuardarImagenActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemGuardarImagen);

        MenuItemGuardarImagenResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/guardar_imagen.png"))); // NOI18N
        MenuItemGuardarImagenResultado.setText("Guardar grafo resultado como imagen");
        MenuItemGuardarImagenResultado.setToolTipText("Pulsa aquí para guardar el grafo resultado como imagen.");
        MenuItemGuardarImagenResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemGuardarImagenResultadoActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemGuardarImagenResultado);

        MenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/exit.png"))); // NOI18N
        MenuItemSalir.setText("Salir");
        MenuItemSalir.setToolTipText("Pulsa para salir.");
        MenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/ayuda.png"))); // NOI18N
        jMenu2.setText("Ayuda");

        MenuItemGuia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/guide.png"))); // NOI18N
        MenuItemGuia.setText("Guia de uso");
        MenuItemGuia.setToolTipText("Pulsa para obtener información adicional.");
        MenuItemGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemGuiaActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItemGuia);

        MenuItemAlgoConceptos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemAlgoConceptos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/manual.png"))); // NOI18N
        MenuItemAlgoConceptos.setText("Algoritmos y conceptos");
        MenuItemAlgoConceptos.setToolTipText("Pulsa para obtener información detallada sobre la materia.");
        MenuItemAlgoConceptos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAlgoConceptosActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItemAlgoConceptos);

        MenuItemSobreGraphvisualx.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemSobreGraphvisualx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphvisualx/Recursos/about.png"))); // NOI18N
        MenuItemSobreGraphvisualx.setText("Sobre Graphvisualx");
        MenuItemSobreGraphvisualx.setToolTipText("Pulsa para consultar sobre el proyecto Graphvisualx.");
        MenuItemSobreGraphvisualx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSobreGraphvisualxActionPerformed(evt);
            }
        });
        jMenu2.add(MenuItemSobreGraphvisualx);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScrollPanelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(ScrollPanelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(535, 535, 535))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Evento
     * Este evento se encarga de manejar la salida controlada del programa.
     * @param evt Evento de entrada que será la pulsación del item del menú
     * o bien con el atajo de teclado Ctrl-X.
     */
    
    private void MenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_MenuItemSalirActionPerformed
    
    /**
     * Evento
     * Este evento se encarga de mostrar la guia del programa al usuario.
     * @param evt Evento de entrada que será debido a la pulsación del item
     * del menú o bien del atajo de teclado Ctrl-G.
     */
    
    private void MenuItemGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemGuiaActionPerformed
        
       
       AbrirFichero fichero = new AbrirFichero("/graphvisualx/Recursos/guia_de_usuario.pdf","guia_de_usuario.pdf");
        
    }//GEN-LAST:event_MenuItemGuiaActionPerformed
    
    
    /**
     * Método modificador.
     * Establece un estilo visual a la aplicación.
     * @param estilo Parámetro de entrada de tipo String que especifica cual
     * es el estilo que se aplicará a la interfaz gráfica.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    
    private void setLookAndFeel(String estilo) throws ClassNotFoundException, InstantiationException, IllegalAccessException

    {
            if (estilo==null) {

                    estilo=UIManager.getSystemLookAndFeelClassName();

            }

            else

            {
                try {
                    UIManager.setLookAndFeel(estilo);
                    /*
                     * Establece el color de los items seleccionados (azul)
                     */
                    UIManager.put("nimbusBase", new Color(47,92,180)); 
                    
                    /*
                     * Establece los colores para los fondos de los diferentes
                     * JFrame, JOptionPane y demás componentes de la aplicación.
                     */
                    
                    UIManager.put("OptionPane.background", new Color(247,248,250));
                    UIManager.put("FileChooser.background", new Color(247,248,250));
                    UIManager.put("FileChooser.foreground", new Color(247,248,250));
                    UIManager.put("FileChooser.disabled", new Color(247,248,250));
                    UIManager.put("FileChooser[Enabled].backgroundPainter", new Color(247,248,250));
                    UIManager.put("Panel.background", new Color(247,248,250)); 
                    
                    
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

         
          SwingUtilities.updateComponentTreeUI(this);

    }
    
    /**
     * Evento
     * Este evento se utiliza para mostra al usuario el manual de los 
     * algoritmos y conceptos relacionados con los grafos. Se abrirá
     * un pdf con el contenido del mismo y ejemplo gráficos.
     * @param evt Evento de entrada que sucederá cuando se pulse sobre el item
     * del menú o se presione el atajo Ctrl-M.
     */
    
    private void MenuItemAlgoConceptosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAlgoConceptosActionPerformed
        
            AbrirFichero fichero = new AbrirFichero("/graphvisualx/Recursos/manual_algoritmos_conceptos.pdf","manual_algoritmos_conceptos.pdf");
    }//GEN-LAST:event_MenuItemAlgoConceptosActionPerformed
    
    /**
     * Evento
     * Este evento se utiliza para obtener información relativa al proyecto
     * graphvisualx.
     * @param evt Evento de entrad que sucederá cuando se pulse sobre el item
     * del menú o se presione el atajo Ctrl-I.
     */
    
    private void MenuItemSobreGraphvisualxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSobreGraphvisualxActionPerformed
        
        final JDialog Ventana2 = new JDialog(this,"Sobre Graphvisualx",true);
        JLabel JL_1 = new JLabel(" Aplicación: Graphvisualx ");
        JLabel JL_2 = new JLabel(" Universidad de Cádiz ");
        JLabel JL_3 = new JLabel(" Proyecto: Suite informática de teoría ");
        JLabel JL_4 = new JLabel(" algorítmica de grafos");
        JLabel JL_5 = new JLabel(" Alumno:");
        JLabel JL_6 = new JLabel(" Moisés Gautier Gómez");
        JLabel logo_uca = new JLabel();
        
        //JLabel.setBounds(int x, int y, int width, int weight);
        JL_1.setBounds(20,20,400,25);
        JL_2.setBounds(20,45,350,25);
        JL_3.setBounds(20,70,350,25);
        JL_4.setBounds(20,95,350,25);
        JL_5.setBounds(20,140,350,25);
        JL_6.setBounds(20,165,350,25);
        logo_uca.setIcon(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/logo_uca.png")));
        logo_uca.setBounds(350,10,350,200);

        JButton salir_ventana = new JButton(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/volver.png")));
        
        //salir_ventana.setHorizontalTextPosition(SwingConstants.LEFT);
        //salir_ventana.setVerticalTextPosition(SwingConstants.LE) ;
        //salir_ventana.setVerticalAlignment(SwingConstants.CENTER);
        
        salir_ventana.setBounds(400,200,25,25);
        salir_ventana.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Ventana2.dispose();
            }
        });
        
        
        
        Ventana2.add(JL_1);
        Ventana2.add(JL_2);
        Ventana2.add(JL_3);
        Ventana2.add(JL_4);
        Ventana2.add(JL_5);        
        Ventana2.add(JL_6);
        Ventana2.add(logo_uca);
        Ventana2.add(salir_ventana);
        Ventana2.setLayout(null);
        
        Ventana2.setSize(600, 300);
        Ventana2.setResizable(false);
        Ventana2.setLocationRelativeTo(this); // Centra el JDialog al JFrame
        Ventana2.setVisible(true);

    }//GEN-LAST:event_MenuItemSobreGraphvisualxActionPerformed
    
    /**
     * Evento
     * Evento que crea en el sistema un nuevo grafo.
     * @param evt Evento de entrada que se produce cuando el usuario presione
     * el boton con la etiqueta "Nuevo Grafo".
     */
    
    private void BotonNuevoGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNuevoGrafoActionPerformed
        
        BotonAlgoritmo.setEnabled(false);
        BotonNodo.setEnabled(false);
        BotonArista.setEnabled(false);
        BotonLimpiar.setEnabled(false);
        BotonMovimiento.setEnabled(false);
        BotonAceptarGrafo.setEnabled(false);
        MenuItemGuardar.setEnabled(false);
        MenuItemGuardarImagen.setEnabled(false);
        MenuItemGuardarImagenResultado.setVisible(false);
        MenuItemGuardarImagenResultado.setEnabled(false);
        BarraEdicionLienzo.setEnabled(false);
        BarraEdicionLienzo.setVisible(false);
        BotonAlgoritmo.setEnabled(false);
        BotonAlgoritmo.setVisible(false);

        
        PanelTexto1.setVisible(false);
        PanelTexto2.setVisible(false);
        
        eleccion_dirigido = 0;
        eleccion_adyacente = 0;
        
        grafo = null;
        
        if(grafo_resultado != null)
        {
            grafo_resultado = null;
            
            if(pintar_algoritmo != null)
            {
                PanelImagen2.remove(pintar_algoritmo);
                pintar_algoritmo = null;
            }
        }
        
        
        BorderLayout border = new BorderLayout();
        FlowLayout flow = new FlowLayout();    
        
        final JFrame datos = new JFrame("Datos del grafo");
        datos.setSize(300,250);
        datos.setVisible(true);
        datos.setEnabled(true);
        datos.setResizable(false);
        
        datos.addWindowListener(new WindowAdapter()
                {
                 
                public void windowClosing(WindowEvent evento)
                {
                    if(Grafico.this.lg_ != null)
                            {
                                Grafico.this.PanelImagen1.remove(lg_);
                            }
                            if(Grafico.this.dibujo != null)
                            {
                                Grafico.this.PanelImagen1.remove(dibujo);
                            }
                    
                            grafo = null;
                            dibujo = null;
                            
                            
                            BotonNodo.setEnabled(false);
                            BotonNodo.setVisible(false);
                            BotonArista.setEnabled(false);
                            BotonArista.setVisible(false);
                            BotonLimpiar.setEnabled(false);
                            BotonLimpiar.setVisible(false);
                            BotonMovimiento.setEnabled(false);
                            BotonMovimiento.setVisible(false);
                            BotonAceptarGrafo.setEnabled(false);
                            BotonAceptarGrafo.setVisible(false);
                            BarraEdicionLienzo.setEnabled(false);
                            BarraEdicionLienzo.setVisible(false);
                            BotonAlgoritmo.setEnabled(false);
                            BotonAlgoritmo.setVisible(false);
                            MenuItemGuardar.setEnabled(false);
                            MenuItemGuardar.setVisible(false);
                            MenuItemGuardarImagen.setEnabled(false);
                            MenuItemGuardarImagen.setVisible(false);
                            MenuItemGuardarImagenResultado.setVisible(false);
                            MenuItemGuardarImagenResultado.setEnabled(false);
        

                }
                    
                });
         
        /*
         * Creo una cuadrícula de 3 filas por 1 columna, en donde
         * colocaré los botones de dirigido, adyacente y aceptación/cancelación
         * del proceso de creación de un nuevo grafo.
         */        
        
        JPanel panel = new JPanel (new GridLayout(3,1,4,4));
        JPanel boton = new JPanel();
        JPanel centro = new JPanel();
        JPanel sur = new JPanel();        
        
        ButtonGroup dirigido = new ButtonGroup();
        JRadioButton dirigido_si = new JRadioButton("Si",false);
        dirigido_si.addItemListener(new ItemListener()
                {
            
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                    if(evento.getStateChange() == ItemEvent.SELECTED)
                       {
                           Grafico.this.dirigido_valor_nuevo_nodo_ = true;
                           Grafico.this.eleccion_dirigido = 1;
                       }
                    else
                    {
                        Grafico.this.dirigido_valor_nuevo_nodo_ = false;
                    }
                    
                    
                   }
                });
        
        JRadioButton dirigido_no = new JRadioButton("No",false);
        dirigido_no.addItemListener(new ItemListener()
                {
       
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                       
                       if(evento.getStateChange() == ItemEvent.SELECTED)
                       {
                           Grafico.this.dirigido_valor_nuevo_nodo_ = false;
                           Grafico.this.eleccion_dirigido = 1;
                       }
                       else
                        {
                            Grafico.this.dirigido_valor_nuevo_nodo_ = true;
                        }
                   }
                });
        
        JLabel etiqueta_dirigido = new JLabel("Dirigido:");
       
        centro.add(etiqueta_dirigido);
        centro.add(dirigido_si);
        centro.add(dirigido_no);
        
        dirigido.add(dirigido_si);
        dirigido.add(dirigido_no);

        ButtonGroup adyacente = new ButtonGroup();
        JRadioButton adyacente_si = new JRadioButton("Si",false);
        adyacente_si.addItemListener(new ItemListener()
                {
       
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                    if(evento.getStateChange() == ItemEvent.SELECTED)
                       {
                           Grafico.this.adyacente_valor_nuevo_nodo_ = true;
                           Grafico.this.eleccion_adyacente = 1;
                       }
                    else
                       {
                           Grafico.this.adyacente_valor_nuevo_nodo_ = false;
                       }
                   }
                });
        
        JRadioButton adyacente_no = new JRadioButton("No",false);
        adyacente_no.addItemListener(new ItemListener()
                {
       
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                       if(evento.getStateChange() == ItemEvent.SELECTED)
                       {
                           Grafico.this.adyacente_valor_nuevo_nodo_ = false;
                           Grafico.this.eleccion_adyacente = 1;
                       }
                       else
                       {
                        Grafico.this.adyacente_valor_nuevo_nodo_ = true;
                       }
                   }
                });
    
        JLabel etiqueta_adyacente = new JLabel("Adyacente:");
        
        sur.add(etiqueta_adyacente);
        sur.add(adyacente_si);
        sur.add(adyacente_no);
        
        adyacente.add(adyacente_si);
        adyacente.add(adyacente_no);
        
        JButton salir = new JButton(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/yes.png")));
        salir.setSize(38, 38);
            salir.addActionListener(new ActionListener()
                {
       
            @Override
                    public void actionPerformed(ActionEvent evento)
                    {
                        for(int i=0; i < 2; i++)
                    {
                        if(Grafico.this.eleccion_dirigido == 1 && Grafico.this.eleccion_adyacente == 1)
                        {
                            
                            if(Grafico.this.lg_ != null)
                            {
                                Grafico.this.PanelImagen1.remove(lg_);
                            }
                            if(Grafico.this.dibujo != null)
                            {
                                Grafico.this.PanelImagen1.remove(dibujo);
                            }
                            
                            /*
                             * Se establece el lienzo de trabajo 
                             * con las opciones seleccionadas anteriormente.
                             */
                            
                            Grafico.this.dibujo = new LienzoGrafo(vector_botones,adyacente_valor_nuevo_nodo_,dirigido_valor_nuevo_nodo_);
                           
                            Grafico.this.PanelImagen1.add(dibujo);                            
                            Grafico.this.PanelImagen1.setVisible(true);
                            Grafico.this.PanelImagen1.setEnabled(true);
                            
                            /*
                             * El método validate sirve para volver a validar
                             * (pintar) todos los componentes que heredan
                             * de la clase (base en este caso y JFrame) al 
                             * sufrir algún cambio en su Layout.
                             */
                            
                            Grafico.this.validate();
                            Grafico.this.eleccion_dirigido = 0;
                            Grafico.this.eleccion_adyacente = 0;
                            BotonAlgoritmo.setEnabled(false);
                            BotonAlgoritmo.setVisible(false);

                            /* 
                             * Cierra el frame (ventana) emergente.
                             */
                            
                            datos.dispose();
                        }
                    }}
                    
                });
    
        
        JButton cancelar = new JButton(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/no.png")));
        cancelar.setSize(38, 38);
            cancelar.addActionListener(new ActionListener()
                {
       
            @Override
                    public void actionPerformed(ActionEvent evento)
                    {
                            if(Grafico.this.lg_ != null)
                            {
                                Grafico.this.PanelImagen1.remove(lg_);
                            }
                            if(Grafico.this.dibujo != null)
                            {
                                Grafico.this.PanelImagen1.remove(dibujo);
                            }
                    
                            grafo = null;
                            dibujo = null;
                            
                            
                            BotonNodo.setEnabled(false);
                            BotonNodo.setVisible(false);
                            BotonArista.setEnabled(false);
                            BotonArista.setVisible(false);
                            BotonLimpiar.setEnabled(false);
                            BotonLimpiar.setVisible(false);
                            BotonMovimiento.setEnabled(false);
                            BotonMovimiento.setVisible(false);
                            BotonAceptarGrafo.setEnabled(false);
                            BotonAceptarGrafo.setVisible(false);
                            BarraEdicionLienzo.setEnabled(false);
                            BarraEdicionLienzo.setVisible(false);
                            BotonAlgoritmo.setEnabled(false);
                            BotonAlgoritmo.setVisible(false);
                            MenuItemGuardar.setEnabled(false);
                            MenuItemGuardar.setVisible(false);
                            MenuItemGuardarImagen.setEnabled(false);
                            MenuItemGuardarImagen.setVisible(false);
                            MenuItemGuardarImagenResultado.setVisible(false);
                            MenuItemGuardarImagenResultado.setEnabled(false);

                            datos.dispose();
                    }
                });
            
            
        boton.add(salir);
        boton.add(cancelar);
                
        panel.add(centro);
        panel.add(sur);
        panel.add(boton);
        
        datos.add(panel);
        datos.setLocationRelativeTo(this);
        
        
        BotonAlgoritmo.setVisible(false);
        BotonAlgoritmo.setEnabled(false);
        BotonNodo.setEnabled(true);
        BotonArista.setEnabled(true);
        BotonLimpiar.setEnabled(true);
        BotonMovimiento.setEnabled(true);
        BotonAceptarGrafo.setEnabled(true);
        BotonNodo.setVisible(true);
        BotonArista.setVisible(true);
        BotonLimpiar.setVisible(true);
        BotonMovimiento.setVisible(true);
        BotonAceptarGrafo.setVisible(true);
        
        MenuItemGuardar.setVisible(true);
        MenuItemGuardar.setEnabled(true);   
        MenuItemGuardarImagen.setVisible(true);
        MenuItemGuardarImagen.setEnabled(true);
        
        BarraEdicionLienzo.setEnabled(true);
        BarraEdicionLienzo.setVisible(true);
        BarraEdicionResultado.setVisible(false);
        BarraEdicionResultado.setEnabled(false);
        BotonMovimiento2.setVisible(false);
        BotonMovimiento2.setEnabled(false);                
             
        
    }//GEN-LAST:event_BotonNuevoGrafoActionPerformed

    /**
     * Evento
     * Evento que se encarga de cargar en el sistema un fichero de texto plano
     * en donde se encuentra la estructura interna del grafo, previamente
     * almacenada en este fichero o creada por el usuario.
     * @param evt Evento de entrada que se produce cuando el usuario presione
     * el boton con la etiqueta "Cargar fichero".
     */
    
    private void BotonCargarFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCargarFicheroActionPerformed
        try {
            BotonAlgoritmo.setEnabled(false);
            BotonAlgoritmo.setVisible(false);
            BotonNodo.setEnabled(false);
            BotonArista.setEnabled(false);
            BotonLimpiar.setEnabled(false);
            BotonMovimiento.setEnabled(true);
            BotonAceptarGrafo.setEnabled(false);
            BotonNodo.setVisible(true);
            BotonArista.setVisible(true);
            BotonLimpiar.setVisible(true);
            BotonMovimiento.setVisible(true);
            BotonAceptarGrafo.setVisible(true);
            MenuItemGuardar.setVisible(false);
            MenuItemGuardar.setEnabled(false);
            MenuItemGuardarImagen.setVisible(false);
            MenuItemGuardarImagen.setEnabled(false);
            MenuItemGuardarImagenResultado.setVisible(false);
            MenuItemGuardarImagenResultado.setEnabled(false);
            BarraEdicionLienzo.setEnabled(true);
            BarraEdicionLienzo.setVisible(true);
            BarraEdicionResultado.setVisible(false);
            BarraEdicionResultado.setEnabled(false);
            BotonMovimiento2.setVisible(false);
            BotonMovimiento2.setEnabled(false);
                    
            
            PanelTexto1.setVisible(false);
            PanelTexto2.setVisible(false);
            
            grafo = null;
            boolean fallo = false;
            
            if(Grafico.this.grafo_resultado != null)
            {
                Grafico.this.grafo_resultado = null;
                Grafico.this.PanelImagen2.remove(pintar_algoritmo);
                Grafico.this.pintar_algoritmo = null;
                
            }
            
            if(Grafico.this.lg_ != null)
            {
                Grafico.this.PanelImagen1.remove(lg_);
            }
            if(Grafico.this.dibujo != null)
            {
                Grafico.this.PanelImagen1.remove(dibujo);
            }
            
            AbrirFichero a = new AbrirFichero();
            
            AbrirFichero fichero = new AbrirFichero(true,false,false);
            
            try {
                //Ahora inicializo el fichero utilizando la clase Grafo
                
                grafo = new Grafo(fichero.ruta_fichero());
                lg_ = new LienzoGrafo(grafo.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido());
                lg_.CambiarEstadoCargar();
            
                if(dibujo != null)
                    PanelImagen1.remove(dibujo);
                
                PanelImagen1.add(lg_);
                grafo.mostrar_grafo();
                numero_de_nodos = grafo.n_nodos();
                
                
            } catch (Exception ex) {
                Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                
                if(Grafico.this.lg_ != null)
                                {
                                    Grafico.this.PanelImagen1.remove(lg_);
                                }
                                if(Grafico.this.dibujo != null)
                                {
                                    Grafico.this.PanelImagen1.remove(dibujo);
                                }
                        
                                grafo = null;
                                dibujo = null;
                                
                                
                                BotonNodo.setEnabled(false);
                                BotonNodo.setVisible(false);
                                BotonArista.setEnabled(false);
                                BotonArista.setVisible(false);
                                BotonLimpiar.setEnabled(false);
                                BotonLimpiar.setVisible(false);
                                BotonMovimiento.setEnabled(false);
                                BotonMovimiento.setVisible(false);
                                BotonAceptarGrafo.setEnabled(false);
                                BotonAceptarGrafo.setVisible(false);
                                BarraEdicionLienzo.setEnabled(false);
                                BarraEdicionLienzo.setVisible(false);
                                BotonAlgoritmo.setEnabled(false);
                                BotonAlgoritmo.setVisible(false);
                                
                                fallo=true;
            }
            
            if(!fallo)
            {
                BotonAlgoritmo.setVisible(true);
                BotonAlgoritmo.setEnabled(true);
               
                MenuItemGuardar.setVisible(true);
                MenuItemGuardar.setEnabled(true);
            
                MenuItemGuardarImagen.setVisible(true);
                MenuItemGuardarImagen.setEnabled(true);
            
                PanelTexto1.setVisible(true);
                try {
                    PanelTextoContenido();
                } catch (Exception ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BotonCargarFicheroActionPerformed

    /**
     * Evento
     * Evento que genera una ventana emergente en donde el usuario puede
     * elegir entre los algoritmos disponibles que dispone la aplicación.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el boton con la etiqueta "Algoritmos".
     */

    private void BotonAlgoritmoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonAlgoritmoMouseClicked
        
        final JFrame ventana = new JFrame("Algoritmos disponibles");
        
        MenuItemGuardarImagenResultado.setVisible(false);
        MenuItemGuardarImagenResultado.setEnabled(false);
        BarraEdicionResultado.setVisible(false);
        BarraEdicionResultado.setEnabled(false);
        
        try {
            numero_de_nodos = grafo.n_nodos();
        } catch (Exception ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        if(pintar_algoritmo != null)
        {
           PanelImagen2.remove(pintar_algoritmo);
        }
        
        
        //ventana.pack();
        
        ventana.setLocationRelativeTo(this);
        ventana.setSize(500,350);
        ventana.setLocation(300,300);
        
	JPanel panel = new JPanel(new GridLayout(6,1,3,3));
        JPanel panel_uno = new JPanel();
        JPanel panel_dos = new JPanel();
	JPanel panel_tres = new JPanel();
	JPanel panel_cuatro = new JPanel();
	JPanel panel_cinco = new JPanel();


        ButtonGroup algoritmos = new ButtonGroup();

	/* Primera tanda de algoritmos */
        JRadioButton algo_dijkstra = new JRadioButton("Dijkstra",false);
        JRadioButton algo_floyd = new JRadioButton("Floyd",false);
	JRadioButton algo_bellman = new JRadioButton("Bellman-Ford",false);
        JLabel etiqueta_algo_corto = new JLabel("Algoritmo camino más corto:");

	panel_uno.add(etiqueta_algo_corto);
	panel_uno.add(algo_dijkstra);
	panel_uno.add(algo_floyd);
	panel_uno.add(algo_bellman);

	/* Segunda tanda de algoritmos */
	JRadioButton algo_warshall = new JRadioButton("Warshall",false);
	JLabel etiqueta_algo_camino = new JLabel("Algoritmos de existencia camino:");
	
	panel_dos.add(etiqueta_algo_camino);
	panel_dos.add(algo_warshall);

	/* Tercera tanda de algoritmos */

	JRadioButton algo_kruskal = new JRadioButton("Kruskal",false);
	JRadioButton algo_prim = new JRadioButton("Prim",false);
	JLabel etiqueta_algo_expansion = new JLabel("Algoritmos de expansión mínimos:");

	panel_tres.add(etiqueta_algo_expansion);
	panel_tres.add(algo_kruskal);
	panel_tres.add(algo_prim);

	/* Cuarta tanda de algoritmos */

	JRadioButton algo_profundidad = new JRadioButton("Profundidad",false);
	JRadioButton algo_anchura = new JRadioButton("Anchura",false);
	JLabel etiqueta_algo_busqueda = new JLabel("Búsqueda en grafos:");

	panel_cuatro.add(etiqueta_algo_busqueda);
	panel_cuatro.add(algo_profundidad);
	panel_cuatro.add(algo_anchura);

	/* Quinta tanda de algoritmos */

	JRadioButton algo_colores = new JRadioButton("Número cromático",false);
	JRadioButton algo_topologica = new JRadioButton("Ordenación Topológica",false);
	JRadioButton algo_euler = new JRadioButton("Euler",false);
	JLabel etiqueta_otros_algo = new JLabel("Otros algoritmos:");

	panel_cinco.add(etiqueta_otros_algo);
	panel_cinco.add(algo_colores);
	panel_cinco.add(algo_topologica);
	panel_cinco.add(algo_euler);

	algoritmos.add(algo_dijkstra);
	algoritmos.add(algo_floyd);
	algoritmos.add(algo_bellman);
	algoritmos.add(algo_warshall);
	algoritmos.add(algo_kruskal);
	algoritmos.add(algo_prim);
	algoritmos.add(algo_profundidad);
	algoritmos.add(algo_anchura);
	algoritmos.add(algo_colores);
	algoritmos.add(algo_topologica);
	algoritmos.add(algo_euler);


        algo_dijkstra.addItemListener(new ItemListener()
                {        
                    @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                    
                    if(evento.getStateChange() == ItemEvent.SELECTED)
                    {
                    if(Grafico.this.pintar_algoritmo != null)
                    {
                        Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                    }
                    
                       String nodo_elegido;
                       boolean nodo_valido = true;
                       do{
                        
                                nodo_elegido = JOptionPane.showInputDialog("Algoritmo de Dijkstra","Introduce el valor del nodo");
                           
                           
                           try{
                                 if(nodo_elegido == null || nodo_elegido.isEmpty())
                                 {
                                    nodo_valido=false;          
                                 }
                                 else
                                 {
                                     System.out.println("El nodo elegido es: "+nodo_elegido+" y el numero de nodos es: "+numero_de_nodos);
                                    if(Integer.parseInt(nodo_elegido) >= 0 && Integer.parseInt(nodo_elegido) <= numero_de_nodos)                                        
                                       nodo_valido=false;       
                                 }
                                 
                           }catch(NumberFormatException e)
                                 {
                                       nodo_valido = true;
                                 }
                           }while(nodo_valido);

                           /*
                            * Con esta condición se evita que se puedan
                            * introducir costes nulos o vacíos para
                            * las aristas. Además de que soluciona el problema
                            * de representación de la superposición de aristas.
                            */
                       
                            if(nodo_elegido != null && !nodo_elegido.isEmpty())
                            {
                            vertice_origen = Integer.parseInt(nodo_elegido);
                            ArrayList<Par> auxiliar = new ArrayList<Par>();
                            int [][] matriz_auxiliar = null;
                            Par p;
                            
                            try {
                                matriz_auxiliar = new int[grafo.n_nodos()][grafo.n_nodos()];
                                matriz_auxiliar = grafo.Dijkstra(vertice_origen);
                            } catch (Exception ex) {
                                Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                            if(vertice_origen >= 0)
                            {
                                
                                try {
                                    
                                    
                                    grafo.mostrar_grafo();
                                    
                                    /* Aquí inicializo una variable auxiliar de tipo grafo
                                     * en donde almacenaré el contenido en formato grafo
                                     * del procesamiento del algoritmo sobre el grafo original
                                     * para, si el usuario lo desea, pueda guardar dicho resultado
                                     * del procesamiento como una imagen en el sistema.
                                     */
                                    
                                    grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                                
                                    for(int i=0; i < grafo.n_nodos(); i++)
                                    {
                                        p = new Par(matriz_auxiliar[1][i],i);
                                        auxiliar.add(p);
                                        
                                        grafo_resultado.nueva_arista(p.primero(), p.segundo(),grafo.peso_arista(p.primero(), p.segundo()));
                                    }
                                    
                                    
                                    nodo_origen_seleccionado = Integer.parseInt(nodo_elegido);
                                    Grafico.this.pintar_algoritmo = new LienzoGrafo();
                                    Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                                    Grafico.this.pintar_algoritmo.grafo_resultante(grafo.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),Integer.parseInt(nodo_elegido));
                                    Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                                    Grafico.this.validate();
                                    
                                    BarraEdicionResultado.setVisible(true);
                                    BarraEdicionResultado.setEnabled(true);
                                    BotonMovimiento2.setVisible(true);
                                    BotonMovimiento2.setEnabled(true);
                                    
                                    
                                    Grafico.this.PanelTextoContenidoB();
                                } catch (Exception ex) {
                                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                //ventana.dispose();
                            }

                            }
                       
                    }  
                       
                   }
                });

        algo_floyd.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                       try {
                            
                           if(Grafico.this.pintar_algoritmo != null)
                            {
                                Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                            }
                           
                           Par p = new Par();
                           ArrayList<Par> auxiliar = new ArrayList<Par>();
                           ArrayList<Par> auxiliar_2 = new ArrayList<Par>();
                           
                           int [][] matrix_1 = new int [numero_de_nodos][numero_de_nodos];
                           int [][] matrix_2 = new int [numero_de_nodos][numero_de_nodos];
                           matrix_1 = grafo.devolver_matriz();
                           matrix_2 = grafo.Floyd();
                           
                           /* Aquí inicializo una variable auxiliar de tipo grafo
                            * en donde almacenaré el contenido en formato grafo
                            * del procesamiento del algoritmo sobre el grafo original
                            * para, si el usuario lo desea, pueda guardar dicho resultado
                            * del procesamiento como una imagen en el sistema.
                            */
                                    
                            grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                                
                           
                           for(int i=0; i < matrix_1.length; i++)
                               for(int j=0; j < matrix_1.length; j++)
                               {
                                 if(matrix_1[i][j] != matrix_2[i][j])
                                 {
                                        p = new Par(i,j);
                                        auxiliar.add(p);
                                        grafo_resultado.nueva_arista(p.primero(), p.segundo(),matrix_2[i][j]);
                                                                                
                                 }
                                 
                                 if(matrix_1[i][j] == matrix_2[i][j] && matrix_2[i][j] != 0)
                                 {
                                     p = new Par(i,j);
                                     auxiliar_2.add(p);
                                     grafo_resultado.nueva_arista(p.primero(), p.segundo(),matrix_2[i][j]);
                                 }
                               }
                           
                           grafo_resultado.mostrar_grafo();
                           
                           Grafico.this.pintar_algoritmo = new LienzoGrafo();
                           
                           if(grafo.es_dirigido())
                           {
                                Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                                Grafico.this.pintar_algoritmo.modificar_vector_no_cambia(auxiliar_2);
                           }
                           else
                           {
                               Grafico.this.pintar_algoritmo.modificar_vector(auxiliar_2);
                               Grafico.this.pintar_algoritmo.modificar_vector_no_cambia(auxiliar);
                           }
                           
                           nodo_origen_seleccionado = 0;
                           Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                           Grafico.this.pintar_algoritmo.CambiarEstadoCargar();
                           
                           Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                           Grafico.this.validate();
                           
                           BarraEdicionResultado.setVisible(true);
                           BarraEdicionResultado.setEnabled(true);
                           BotonMovimiento2.setVisible(true);
                           BotonMovimiento2.setEnabled(true);
                
                           
                           Grafico.this.PanelTextoContenidoB();

                       } catch (Exception ex) {
                          Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                   }
                });

        algo_bellman.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                       if(evento.getStateChange() == ItemEvent.SELECTED)
                       {
                       if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                       
                      String nodo_elegido;
                       boolean nodo_valido = true;
                       do{
                           nodo_elegido = JOptionPane.showInputDialog("Algoritmo de Bellman-Ford","Introduce el valor del nodo");
                           
                           
                           try{
                                 if(nodo_elegido == null || nodo_elegido.isEmpty())
                                 {
                                    nodo_valido=false;          
                                 }
                                 else
                                 {
                                    if(Integer.parseInt(nodo_elegido) <= numero_de_nodos && Integer.parseInt(nodo_elegido) >= 0)                                        
                                       nodo_valido=false;       
                                 }
                                 
                           }catch(NumberFormatException e)
                                 {
                                       nodo_valido = true;
                                 }
                           }while(nodo_valido);

                            vertice_origen = Integer.parseInt(nodo_elegido);
                            ArrayList<Par> auxiliar = new ArrayList<Par>();
                            ArrayList<Arista> resultado = new ArrayList<Arista>();
                            
                            try {
                                resultado = new ArrayList<Arista>(grafo.Bellman_Ford(vertice_origen));
                            } catch (Exception ex) {
                            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Par p;
                        
                            if(vertice_origen >= 0)
                            {
                                
                                try {
                                    
                                    
                                    grafo.mostrar_grafo();
                                    
                                    /* Aquí inicializo una variable auxiliar de tipo grafo
                                     * en donde almacenaré el contenido en formato grafo
                                     * del procesamiento del algoritmo sobre el grafo original
                                     * para, si el usuario lo desea, pueda guardar dicho resultado
                                     * del procesamiento como una imagen en el sistema.
                                     */
                                    
                                    grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                                
                                    for(int i=0; i < grafo.n_nodos(); i++)
                                    {
                                        p = new Par(resultado.get(i).v_origen(),resultado.get(i).v_destino());
                                        auxiliar.add(p);
                                        System.out.println("Fuente: "+p.primero()+" Destino: "+p.segundo());
                                        grafo_resultado.nueva_arista(p.primero(), p.segundo(),resultado.get(i).coste());
                                    }
                                    
                                    grafo_resultado.mostrar_grafo();
                                    
                                    Grafico.this.pintar_algoritmo = new LienzoGrafo();
                                    Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                                    
                                    /*
                                     * Para este algoritmo la adyacencia o no no se toma como
                                     * relevante dado que va calculando el coste máximo
                                     * de la ruta desde el nodo origen hasta
                                     * el nodo destino que se encuentre.
                                     */
                                    
                                    nodo_origen_seleccionado = Integer.parseInt(nodo_elegido);
                                    Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),false,grafo.es_dirigido(),Integer.parseInt(nodo_elegido));
                                    Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                                    Grafico.this.pintar_algoritmo.CambiarEstadoCargar();
                                    Grafico.this.validate();
                                    
                                    BarraEdicionResultado.setVisible(true);
                                    BarraEdicionResultado.setEnabled(true);
                                    BotonMovimiento2.setVisible(true);
                                    BotonMovimiento2.setEnabled(true);
                                    
                                    Grafico.this.PanelTextoContenidoB();
                                    
                                } catch (Exception ex) {
                                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                //ventana.dispose();
                            }
                       }
                   }
                });

        algo_warshall.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                      try {
                             
                              /* Aquí inicializo una variable auxiliar de tipo grafo
                               * en donde almacenaré el contenido en formato grafo
                               * del procesamiento del algoritmo sobre el grafo original
                               * para, si el usuario lo desea, pueda guardar dicho resultado
                               * del procesamiento como una imagen en el sistema.
                               */
                                    
                              Par p = new Par();
                              ArrayList<Par> auxiliar = new ArrayList<Par>();
                              int [][] matrix = new int[grafo.n_nodos()][grafo.n_nodos()];
                              matrix = grafo.Warshall();
                              
                              grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                                
                              for(int i=0; i < grafo.n_nodos(); i++)
                                  for(int j=0; j < grafo.n_nodos();j++)
                                    {
                                        if(matrix[i][j] != 0)
                                        {
                                            p = new Par(i,j);
                                            auxiliar.add(p);
                                            grafo_resultado.nueva_arista(p.primero(), p.segundo(),matrix[i][j]);
                                        }
                                    }
 
                              
                               grafo_resultado.mostrar_grafo();
                                    
                               nodo_origen_seleccionado = 0;
                               Grafico.this.pintar_algoritmo = new LienzoGrafo();
                               Grafico.this.pintar_algoritmo.no_costes(true);
                               Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                               Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                               Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                               
                               Grafico.this.validate();
                               
                               BarraEdicionResultado.setVisible(true);
                               BarraEdicionResultado.setEnabled(true);
                               BotonMovimiento2.setVisible(true);
                               BotonMovimiento2.setEnabled(true);
                
        
                               Grafico.this.PanelTextoContenidoB();
                               
                       } catch (Exception ex) {
                          Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                });

        algo_kruskal.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                       try {
                        ArrayList<Arista> resultado = new ArrayList<Arista>(grafo.Kruskal());
                        ArrayList<Par> auxiliar = new ArrayList<Par>();
                        Par p = new Par();
                        
                        /* Aquí inicializo una variable auxiliar de tipo grafo
                         * en donde almacenaré el contenido en formato grafo
                         * del procesamiento del algoritmo sobre el grafo original
                         * para, si el usuario lo desea, pueda guardar dicho resultado
                         * del procesamiento como una imagen en el sistema.
                         */
                                   
                         grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                               
                         for(int i=0; i < resultado.size(); i++)
                         {
                            p = new Par(resultado.get(i).v_origen(),resultado.get(i).v_destino());
                            auxiliar.add(p);
                            
                            grafo_resultado.nueva_arista(p.primero(), p.segundo(),resultado.get(i).coste());
                         }
                                    
                         nodo_origen_seleccionado = 0;
                         Grafico.this.pintar_algoritmo = new LienzoGrafo();
                         Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                         Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                         Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                         Grafico.this.validate();
                         
                         BarraEdicionResultado.setVisible(true);
                         BarraEdicionResultado.setEnabled(true);
                         BotonMovimiento2.setVisible(true);
                         BotonMovimiento2.setEnabled(true);
                
                        
                         Grafico.this.PanelTextoContenidoB();
                         
                       } catch (Exception ex) {
                          Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                       }        
                       
                   }
                });

        algo_prim.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                      try {
                        
                        ArrayList<Arista> resultado = new ArrayList<Arista>(grafo.Prim());
                        ArrayList<Par> auxiliar = new ArrayList<Par>();
                        Par p = new Par();
                        
                        /* Aquí inicializo una variable auxiliar de tipo grafo
                         * en donde almacenaré el contenido en formato grafo
                         * del procesamiento del algoritmo sobre el grafo original
                         * para, si el usuario lo desea, pueda guardar dicho resultado
                         * del procesamiento como una imagen en el sistema.
                         */
                                   
                         grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                               
                         for(int i=0; i < resultado.size(); i++)
                         {
                            p = new Par(resultado.get(i).v_origen(),resultado.get(i).v_destino());
                            auxiliar.add(p);
                            
                            grafo_resultado.nueva_arista(p.primero(), p.segundo(),resultado.get(i).coste());
                         }
                                    
                         nodo_origen_seleccionado = 0;
                         Grafico.this.pintar_algoritmo = new LienzoGrafo();
                         Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                         Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                         Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                         
                         Grafico.this.validate();
                         
                         BarraEdicionResultado.setVisible(true);
                         BarraEdicionResultado.setEnabled(true);
                         BotonMovimiento2.setVisible(true);
                         BotonMovimiento2.setEnabled(true);
                
                         
                         Grafico.this.PanelTextoContenidoB();
                          
                       } catch (Exception ex) {
                          Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                });

        algo_profundidad.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                     try {
                         
                        ArrayList<Integer> resultado = new ArrayList<Integer>(grafo.Rec_Profundidad()); 
                        ArrayList<Par> auxiliar = new ArrayList<Par>();
                        Par p = new Par();
                        
                        /* Aquí inicializo una variable auxiliar de tipo grafo
                         * en donde almacenaré el contenido en formato grafo
                         * del procesamiento del algoritmo sobre el grafo original
                         * para, si el usuario lo desea, pueda guardar dicho resultado
                         * del procesamiento como una imagen en el sistema.
                         */
                                   
                         grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                         
                         for(int i=0; i < resultado.size(); i++)
                         {
                             if(i+1 < resultado.size())
                             {
                                p = new Par(resultado.get(i),resultado.get(i+1));
                                auxiliar.add(p);
                                
                                if(!grafo.es_adyacencia())
                                    grafo_resultado.nueva_arista(p.primero(), p.segundo(),1);
                                else
                                    grafo_resultado.nueva_arista(p.primero(), p.segundo());
                             }
                         }
                                    
                         nodo_origen_seleccionado = 0;
                         Grafico.this.pintar_algoritmo = new LienzoGrafo();
                         Grafico.this.pintar_algoritmo.no_costes(true);
                         Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                         Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                         Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                         
                         Grafico.this.validate();
                         
                         BarraEdicionResultado.setVisible(true);
                         BarraEdicionResultado.setEnabled(true);
                         BotonMovimiento2.setVisible(true);
                         BotonMovimiento2.setEnabled(true);
                
                         
                         Grafico.this.PanelTextoContenidoB();
                         
                       } catch (Exception ex) {
                          Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                });

        algo_anchura.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                     try {
                             
                        ArrayList<Integer> resultado = new ArrayList<Integer>(grafo.Rec_Anchura()); 
                        ArrayList<Par> auxiliar = new ArrayList<Par>();
                        Par p = new Par();
                        
                        
                        
                        /* Aquí inicializo una variable auxiliar de tipo grafo
                         * en donde almacenaré el contenido en formato grafo
                         * del procesamiento del algoritmo sobre el grafo original
                         * para, si el usuario lo desea, pueda guardar dicho resultado
                         * del procesamiento como una imagen en el sistema.
                         */
                                   
                         grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                         
                         for(int i=0; i < resultado.size(); i++)
                         {
                             if(i+1 < resultado.size())
                             {
                                p = new Par(resultado.get(i),resultado.get(i+1));
                                auxiliar.add(p);
                                
                                grafo_resultado.nueva_arista(p.primero(), p.segundo(),1);
                             }
                         }
                                    
                         
                         nodo_origen_seleccionado = 0;
                         Grafico.this.pintar_algoritmo = new LienzoGrafo();
                         Grafico.this.pintar_algoritmo.no_costes(true);
                         Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                         Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo_resultado.es_adyacencia(),grafo_resultado.es_dirigido(),0);
                         Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);
                         
                         Grafico.this.validate();
                         
                         BarraEdicionResultado.setVisible(true);
                         BarraEdicionResultado.setEnabled(true);
                         BotonMovimiento2.setVisible(true);
                         BotonMovimiento2.setEnabled(true);
                         
                         Grafico.this.PanelTextoContenidoB();
                         
                       } catch (Exception ex) {
                          Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                });

        algo_colores.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                    try {
                        
                        if(evento.getStateChange() == ItemEvent.SELECTED)
                            JOptionPane.showMessageDialog(Grafico.this.BotonAlgoritmo,"El número cromático aproximado del grafo es "+grafo.colores() ,"Número cromático",JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
                });

        algo_topologica.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                       System.out.println(evento.getItemSelectable());
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                    try {
                    
                        ArrayList<Integer> resultado = new ArrayList<Integer>(grafo.orden_topologico()); 
                        ArrayList<Par> auxiliar = new ArrayList<Par>();
                        Par p = new Par();
                        
                        /* Aquí inicializo una variable auxiliar de tipo grafo
                         * en donde almacenaré el contenido en formato grafo
                         * del procesamiento del algoritmo sobre el grafo original
                         * para, si el usuario lo desea, pueda guardar dicho resultado
                         * del procesamiento como una imagen en el sistema.
                         */
                                   
                         grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                         
                         for(int i=0; i < resultado.size(); i++)
                         {
                             if(i+1 < resultado.size())
                             {
                                p = new Par(resultado.get(i),resultado.get(i+1));
                                auxiliar.add(p);
                                
                                grafo_resultado.nueva_arista(p.primero(), p.segundo(),1);
                             }
                         }
                                    
                         nodo_origen_seleccionado = grafo.primer_nodo_topo();
                         Grafico.this.pintar_algoritmo = new LienzoGrafo();
                         Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                         Grafico.this.pintar_algoritmo.grafo_resultante(grafo_resultado.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),grafo.primer_nodo_topo());
                         Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);    
                         
                         Grafico.this.validate();
                         
                         BarraEdicionResultado.setVisible(true);
                         BarraEdicionResultado.setEnabled(true);
                         BotonMovimiento2.setVisible(true);
                         BotonMovimiento2.setEnabled(true);
                
                         
                         Grafico.this.PanelTextoContenidoB();
                        
                    } catch (Exception ex) {
                        Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
                });

        algo_euler.addItemListener(new ItemListener()
                {
            @Override
                   public void itemStateChanged(ItemEvent evento)
                   {
                       if(evento.getStateChange() == ItemEvent.SELECTED)
                       {
                        if(Grafico.this.pintar_algoritmo != null)
                        {
                            Grafico.this.PanelImagen2.remove(Grafico.this.pintar_algoritmo);
                        }
                        
                       try
                       {
                        ArrayList<Integer> resultado = new ArrayList<Integer>(grafo.euler()); 
                        ArrayList<Par> auxiliar = new ArrayList<Par>();
                        Par p = new Par();
                        int [][] matrix = new int[grafo.n_nodos()][grafo.n_nodos()];
                        matrix = grafo.devolver_matriz();
                        
                        /* Aquí inicializo una variable auxiliar de tipo grafo
                         * en donde almacenaré el contenido en formato grafo
                         * del procesamiento del algoritmo sobre el grafo original
                         * para, si el usuario lo desea, pueda guardar dicho resultado
                         * del procesamiento como una imagen en el sistema.
                         */
                                   
                         grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                         
                         for(int i=0; i < resultado.size(); i++)
                         {
                             if(i+1 < resultado.size())
                             {
                                p = new Par(resultado.get(i),resultado.get(i+1));
                                auxiliar.add(p);
                                
                                grafo_resultado.nueva_arista(p.primero(), p.segundo(),matrix[p.primero()][p.segundo()]);
                             }
                         }
                                    
                         nodo_origen_seleccionado = 0;
                         Grafico.this.pintar_algoritmo = new LienzoGrafo();
                         Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
                         Grafico.this.pintar_algoritmo.grafo_resultante(grafo.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                         Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);    
                         
                         Grafico.this.validate();
                         
                         BarraEdicionResultado.setVisible(true);
                         BarraEdicionResultado.setEnabled(true);
                         BotonMovimiento2.setVisible(true);
                         BotonMovimiento2.setEnabled(true);
                         
                         Grafico.this.PanelTextoContenidoB();
                        
                    } catch (Exception ex) {
                        //Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null," El grafo no acepta camino Euleriano ","Oops",JOptionPane.INFORMATION_MESSAGE);
                    }
                   }
                }
             });

	JPanel fin_ventana = new JPanel();

        JButton aceptar_algo = new JButton(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/yes.png")));
        aceptar_algo.setToolTipText("Pulsalo si es el algoritmo deseado");
        aceptar_algo.setSize(38,38);
        aceptar_algo.addActionListener(new ActionListener()
        {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                        ventana.dispose();
             }
        });
        
        JButton salir = new JButton(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/no.png")));
        salir.setSize(38,38);
        salir.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ventana.dispose();
            }
        });
        
        JButton descripcion = new JButton(new ImageIcon(getClass().getResource("/graphvisualx/Recursos/info.png")));
        descripcion.setSize(38,38);
        descripcion.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame mensaje = new JFrame("Información");
                mensaje.setVisible(true);
                mensaje.setLocationRelativeTo(ventana);
                mensaje.setSize(600,100);
                mensaje.setLocation(300,300);
      
                JLabel texto = new JLabel("<html>Aceptar: Selecciona el algoritmo del cuadro de texto. Volver: vuelve a la ventana principal.</html>");
                mensaje.add(texto);
            }
        });

	fin_ventana.add(aceptar_algo);
	fin_ventana.add(salir);
	fin_ventana.add(descripcion);


	panel.add(panel_uno);
	panel.add(panel_dos);
	panel.add(panel_tres);
	panel.add(panel_cuatro);
	panel.add(panel_cinco);
	panel.add(fin_ventana);
        
        ventana.add(panel);
        
        ventana.setVisible(true);
        
        MenuItemGuardarImagenResultado.setVisible(true);
        MenuItemGuardarImagenResultado.setEnabled(true);

    }//GEN-LAST:event_BotonAlgoritmoMouseClicked
    
    /**
     * Evento
     * Evento que permite que el usuario pueda editar en el lienzo
     * los nodos del grafo.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el boton con la etiqueta "Nodo".
     */
    
    private void BotonNodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNodoActionPerformed
        
        dirigido_valor = dirigido_valor_nuevo_nodo_;
        adyacente_valor = adyacente_valor_nuevo_nodo_;
        
        Grafico.this.BotonNodo.getModel().setArmed(false);
        Grafico.this.BotonNodo.getModel().setPressed(false);
        
        /*
         * setContentAreaFilled realiza una marca alrededor del jbutton una
         * vez que se ha presionado para mostar que se ha escogido ese jbutton
         */
        
        Grafico.this.BotonNodo.setContentAreaFilled(true);
        
    }//GEN-LAST:event_BotonNodoActionPerformed
    
    /**
     * Evento
     * Evento que permite que el usuario pueda guardar el contenido
     * del grafo editado en el lienzo en un fichero de texto plano.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el item el menú "Guardar grafo inicial".
     */

    
    private void MenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemGuardarActionPerformed
        
        if(grafo != null)
        {         
            
                try {
                    EscribirFichero ef = new EscribirFichero(adyacente_valor,dirigido_valor,grafo.devolver_matriz());
                } catch (Exception ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            JOptionPane.showMessageDialog(null," No hay ningún grafo cargado en el sistema.","Oops",JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_MenuItemGuardarActionPerformed

    /**
     * Evento
     * Evento que acepta en el sistema el grafo que el usuario ha dibujado
     * en el lienzo para su posterior análisis y procesado de algoritmos.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el botón con la etiqueta "Aceptar".
     */
    
    private void BotonAceptarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAceptarGrafoActionPerformed
        
        BotonNodo.setEnabled(false);
        BotonArista.setEnabled(false);
        BotonLimpiar.setEnabled(false);
        BotonNodo.setVisible(false);
        BotonArista.setVisible(false);
        BotonLimpiar.setVisible(false);
        BarraEdicionLienzo.setEnabled(true);
        BarraEdicionLienzo.setVisible(true);
        BotonAlgoritmo.setEnabled(true);
        BotonAlgoritmo.setVisible(true);


        
        if(dibujo != null)
        {
        try {
            dibujo.CambiarEstado();
            dibujo.EstablecerMovimiento(false);
            grafo = dibujo.ContenidoLienzo();
            grafo.mostrar_grafo();
            if(grafo.n_nodos() == 0)
                grafo = null;
            
        } catch (Exception ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        BotonNodo.setVisible(true);
        BotonNodo.setEnabled(false);
        BotonArista.setVisible(true);
        BotonArista.setEnabled(false);
        BotonLimpiar.setVisible(true);
        BotonLimpiar.setEnabled(false);
        BotonAceptarGrafo.setEnabled(false);
        BotonAceptarGrafo.setVisible(true);
        
        PanelTexto1.setVisible(true);
        try {
            
             if(grafo != null)
                PanelTextoContenido();
             else
             {
                 BarraEdicionLienzo.setVisible(false);
                 PanelTexto1.setVisible(false);
                 JOptionPane.showMessageDialog(null," No hay ningún nodo en el lienzo","Oops",JOptionPane.INFORMATION_MESSAGE);
             }
        } catch (Exception ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BotonAceptarGrafoActionPerformed
    
    /**
     * Evento
     * Evento que permite guardar en formato imagen (jpg) el grafo actual
     * de trabajo que se haya cargado previamente o definido mediante
     * el lienzo de trabajo.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el item del menú "Guardar grafo como imagen".
     */
    
    
    private void MenuItemGuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemGuardarImagenActionPerformed
        
        if(grafo != null)
        {         
            
                try {
                    DibujaGrafo dg = new DibujaGrafo(grafo.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
                } catch (Exception ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            JOptionPane.showMessageDialog(null," No hay ningún grafo cargado en el sistema.","Oops",JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_MenuItemGuardarImagenActionPerformed
    
    /**
     * Evento
     * Evento que permite al usuario guardar en formato imagen (jpg) el grafo
     * resultado de la aplicación de un algoritmo sobre el grafo de trabajo
     * inicial.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el item el menú "Guardar grafo resultado como imagen".
     */
    
    private void MenuItemGuardarImagenResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemGuardarImagenResultadoActionPerformed
        
        if(grafo_resultado != null)
        {         
            
                try {
                    DibujaGrafo dg = new DibujaGrafo(grafo_resultado.devolver_matriz(),grafo_resultado.es_adyacencia(),grafo_resultado.es_dirigido(),nodo_origen_seleccionado);
                } catch (Exception ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            JOptionPane.showMessageDialog(null," No hay ningún grafo cargado en el sistema.","Oops",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_MenuItemGuardarImagenResultadoActionPerformed
    
    /**
     * Evento
     * Evento que permite introducir el vértice del grafo sobre el cual
     * el usuario desea saber el grado o número de valencia del mismo.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el botón con la etiqueta "Pulsa" adjunto a la etiqueta "Grado vértice"
     * situada en la parte inferior de los lienzos de la aplicación.
     */
    
    private void BotonGradoVerticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGradoVerticeActionPerformed
        
        MenuItemGuardarImagenResultado.setVisible(false);
        MenuItemGuardarImagenResultado.setEnabled(false);
        
        try{
            String texto = null;
            boolean salir=true;
            
            do
            {
               texto = JOptionPane.showInputDialog(null,"Grado de un vértice","Introduce el vértice");
                            
                try{
             
                    if(texto == null || texto.isEmpty())
                    {
                      salir=true;        
                    }
                    else
                    {
                      if(Integer.parseInt(texto) >= 0 && Integer.parseInt(texto) < grafo.n_nodos())   
                         salir=false;
                    }
                }catch(NumberFormatException e)
                 {
                    salir = true;
                 }
                            
             }while(salir);
                        
             JOptionPane.showMessageDialog(null,"El grado del vértice "+texto+" es "+grafo.grado_nodo(Integer.parseInt(texto)), "Grado de un vértice", JOptionPane.INFORMATION_MESSAGE);
                        
             } catch (Exception ex) {
               Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
             }    
                            
    }//GEN-LAST:event_BotonGradoVerticeActionPerformed
    
    /**
     * Evento
     * Evento que permite dibujar en el lienzo de grafo resultado un nuevo grafo
     * con los puentes hallados para el grafo en cuestión.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el botón con la etiqueta  "Pulsa" adjunto a la etiqueta "Puentes"
     * situada en la parte inferior de los lienzos de la aplicación. 
     */
    
    private void BotonPuentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPuentesActionPerformed
         
        
        if(pintar_algoritmo != null)
            {
                PanelImagen2.remove(pintar_algoritmo);
                pintar_algoritmo = null;
            }
        
        try
        {
            ArrayList<Arista> resultado = new ArrayList<Arista>(grafo.hallar_puente()); 
            
            ArrayList<Par> auxiliar = new ArrayList<Par>();
            Par p = new Par();
            int [][] matrix = new int[grafo.n_nodos()][grafo.n_nodos()];
            matrix = grafo.devolver_matriz();
                        
            /* Aquí inicializo una variable auxiliar de tipo grafo
             * en donde almacenaré el contenido en formato grafo
             * del procesamiento del algoritmo sobre el grafo original
             * para, si el usuario lo desea, pueda guardar dicho resultado
             * del procesamiento como una imagen en el sistema.
             */
                                   
            grafo_resultado = new Grafo(grafo.n_nodos(),grafo.es_adyacencia(),grafo.es_dirigido());
                         
            for(int i=0; i < resultado.size(); i++)
            {
               p = new Par(resultado.get(i).v_origen(),resultado.get(i).v_destino());
               auxiliar.add(p);
               
               
               grafo_resultado.nueva_arista(p.primero(), p.segundo(),matrix[p.primero()][p.segundo()]);
            }
                                    
            grafo_resultado.mostrar_grafo();
            Grafico.this.pintar_algoritmo = new LienzoGrafo();
            Grafico.this.pintar_algoritmo.modificar_vector(auxiliar);
            Grafico.this.pintar_algoritmo.grafo_resultante(grafo.devolver_matriz(),grafo.es_adyacencia(),grafo.es_dirigido(),0);
            Grafico.this.PanelImagen2.add(Grafico.this.pintar_algoritmo);    
                        
            } catch (Exception ex) {
                 Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        MenuItemGuardarImagenResultado.setVisible(true);
        MenuItemGuardarImagenResultado.setEnabled(true);
        BarraEdicionResultado.setVisible(true);
        BarraEdicionResultado.setEnabled(true);
        BotonMovimiento2.setVisible(true);
        BotonMovimiento2.setEnabled(true);
       
    }//GEN-LAST:event_BotonPuentesActionPerformed
    
    /**
     * Evento
     * Evento que permite introducir el vértice del grafo sobre el cual
     * el usuario desea saber el grado o número de valencia del mismo.
     * @param evt Evento de entrada que se produce cuando el usuario presiona
     * el botón con la etiqueta "Pulsa" adjunto a la etiqueta "Grado vértice"
     * situada en la parte inferior de los lienzos de la aplicación.
     */
    
    private void BotonGradoVerticeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGradoVerticeBActionPerformed
        
        MenuItemGuardarImagenResultado.setVisible(false);
        MenuItemGuardarImagenResultado.setEnabled(false);
        
        try{
            String texto = null;
            boolean salir=true;
            
            do
            {
               texto = JOptionPane.showInputDialog(null,"Grado de un vértice","Introduce el vértice");
                            
                try{
             
                    if(texto == null || texto.isEmpty())
                    {
                      salir=true;        
                    }
                    else
                    {
                      if(Integer.parseInt(texto) >= 0 && Integer.parseInt(texto) < grafo_resultado.n_nodos())   
                         salir=false;
                    }
                }catch(NumberFormatException e)
                 {
                    salir = true;
                 }
                            
             }while(salir);
                        
             JOptionPane.showMessageDialog(null,"El grado del vértice "+texto+" es "+grafo_resultado.grado_nodo(Integer.parseInt(texto)), "Grado de un vértice", JOptionPane.INFORMATION_MESSAGE);
                        
             } catch (Exception ex) {
               Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
             }    
    }//GEN-LAST:event_BotonGradoVerticeBActionPerformed

    private void BotonMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonMovimientoActionPerformed
        
        if(dibujo != null)
        {
            dibujo.EstablecerMovimiento(true);
        }
        else
        {
            if(lg_ != null)
            {
                lg_.EstablecerMovimiento(true);
            }
        }
        
    }//GEN-LAST:event_BotonMovimientoActionPerformed

    private void BotonMovimiento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonMovimiento2ActionPerformed
       
        if(pintar_algoritmo != null)
        {
            pintar_algoritmo.EstablecerMovimiento(true);
        }
        
    }//GEN-LAST:event_BotonMovimiento2ActionPerformed
    
    /**
     * Método modificador
     * Este método se emplea para mostrar en la ventana principal los
     * atributos o propiedades de cada grafo de los lienzo en su parte inferior.
     * @throws Exception 
     */
    
    private void PanelTextoContenidoB() throws Exception
    {
        PanelTexto2.setEnabled(true);
        PanelTexto2.setVisible(true); 
        
        /* En este procedimiento lo que haré sera modificar los contenidos del 
         * panel que se encuentra debajo de cada lienzo de dibujos y que 
         * contendrá información relativa al grafo que se halla en el parte 
         * superior; ya sea el original o el obtenido del procesamiento 
         * a través de algunos de los algoritmos disponibles.
         */
        
        if(grafo_resultado.es_lineal())
        {
            EtiquetaLinealB.setText("Lineal: SI");
        }
        else
        {
            EtiquetaLinealB.setText("Lineal: NO");
        }
        
        if(grafo_resultado.es_circular())
        {
            EtiquetaCircularB.setText("Circular: SI");
        }
        else
        {
            EtiquetaCircularB.setText("Circular: NO");
        }
        
        if(grafo_resultado.es_completo())
        {
            EtiquetaCompletoB.setText("Completo: SI");
        }
        else
        {
            EtiquetaCompletoB.setText("Completo: NO");
        }
        
        if(grafo_resultado.Aciclico())
        {
            EtiquetaAciclicoB.setText("Aciclico: SI");
        }
        else
        {
            EtiquetaAciclicoB.setText("Aciclico: NO");
        }
        
        if(grafo_resultado.es_adyacencia())
        {
            EtiquetaAdyacenteB.setText("Adyacente: SI");
        }
        else
        {
            EtiquetaAdyacenteB.setText("Adyacente: NO");
        }
        
        if(grafo_resultado.es_dirigido())
        {
            EtiquetaDirigidoB.setText("Dirigido: SI");
        }
        else
        {
            EtiquetaDirigidoB.setText("Dirigido: NO");
        }
        
        if(grafo_resultado.es_conexo())
        {
            EtiquetaConexoB.setText("Conexo: SI");
        }
        else
        {
            EtiquetaConexoB.setText("Conexo: NO");
        }
        
    
    }
    
    /**
     * Método modificador
     * Este método se emplea para mostrar en la ventana principal los
     * atributos o propiedades de cada grafo de los lienzo en su parte inferior.
     * @throws Exception 
     */    
    
    private void PanelTextoContenido() throws Exception
    {
        PanelTexto1.setEnabled(true);
        PanelTexto1.setVisible(true);
        
        ArrayList<Integer> puntos_cortes = new ArrayList<Integer>(grafo.hallar_corte());
        String corte = new String();
        
        
        /* En este procedimiento lo que haré sera modificar los contenidos del 
         * panel que se encuentra debajo de cada lienzo de dibujos y que 
         * contendrá información relativa al grafo que se halla en el parte 
         * superior; ya sea el original o el obtenido del procesamiento 
         * a través de algunos de los algoritmos disponibles.
         */
        
        if(grafo.es_lineal())
        {
            EtiquetaLineal.setText("Lineal: SI");
        }
        else
        {
            EtiquetaLineal.setText("Lineal: NO");
        }
        
        if(grafo.es_circular())
        {
            EtiquetaCircular.setText("Circular: SI");
        }
        else
        {
            EtiquetaCircular.setText("Circular: NO");
        }
        
        if(grafo.es_completo())
        {
            EtiquetaCompleto.setText("Completo: SI");
        }
        else
        {
            EtiquetaCompleto.setText("Completo: NO");
        }
        
        if(grafo.Aciclico())
        {
            EtiquetaAciclico.setText("Aciclico: SI");
        }
        else
        {
            EtiquetaAciclico.setText("Aciclico: NO");
        }
        
        if(grafo.es_adyacencia())
        {
            EtiquetaAdyacente.setText("Adyacente: SI");
        }
        else
        {
            EtiquetaAdyacente.setText("Adyacente: NO");
        }
        
        if(grafo.es_dirigido())
        {
            EtiquetaDirigido.setText("Dirigido: SI");
        }
        else
        {
            EtiquetaDirigido.setText("Dirigido: NO");
        }
        
        if(grafo.es_conexo())
        {
            EtiquetaConexo.setText("Conexo: SI");
        }
        else
        {
            EtiquetaConexo.setText("Conexo: NO");
        }
        
        if(puntos_cortes != null)
        {
        for(int i=0; i < puntos_cortes.size(); i++)
        {
            corte = corte.concat(puntos_cortes.get(i).toString());
            
            if(i+1 < puntos_cortes.size())
            {
              corte = corte.concat(", ");  
            }
            
            
        }
        EtiquetaPuntosCorte.setText("Puntos de corte: "+corte);
        }

        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar BarraEdicionGrafo;
    private javax.swing.JToolBar BarraEdicionLienzo;
    private javax.swing.JToolBar BarraEdicionResultado;
    private javax.swing.JButton BotonAceptarGrafo;
    private javax.swing.JButton BotonAlgoritmo;
    private javax.swing.JButton BotonArista;
    private javax.swing.JButton BotonCargarFichero;
    private javax.swing.JButton BotonGradoVertice;
    private javax.swing.JButton BotonGradoVerticeB;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JButton BotonMovimiento;
    private javax.swing.JButton BotonMovimiento2;
    private javax.swing.JButton BotonNodo;
    private javax.swing.JButton BotonNuevoGrafo;
    private javax.swing.JButton BotonPuentes;
    private javax.swing.JLabel EtiquetaAciclico;
    private javax.swing.JLabel EtiquetaAciclicoB;
    private javax.swing.JLabel EtiquetaAdyacente;
    private javax.swing.JLabel EtiquetaAdyacenteB;
    private javax.swing.JLabel EtiquetaCircular;
    private javax.swing.JLabel EtiquetaCircularB;
    private javax.swing.JLabel EtiquetaCompleto;
    private javax.swing.JLabel EtiquetaCompletoB;
    private javax.swing.JLabel EtiquetaConexo;
    private javax.swing.JLabel EtiquetaConexoB;
    private javax.swing.JLabel EtiquetaDirigido;
    private javax.swing.JLabel EtiquetaDirigidoB;
    private javax.swing.JLabel EtiquetaGradoVertice;
    private javax.swing.JLabel EtiquetaGradoVerticeB;
    private javax.swing.JLabel EtiquetaLineal;
    private javax.swing.JLabel EtiquetaLinealB;
    private javax.swing.JLabel EtiquetaPuentesB;
    private javax.swing.JLabel EtiquetaPuntosCorte;
    private javax.swing.JLabel EtiquetaPuntosCorteB;
    private javax.swing.JMenuItem MenuItemAlgoConceptos;
    private javax.swing.JMenuItem MenuItemGuardar;
    private javax.swing.JMenuItem MenuItemGuardarImagen;
    private javax.swing.JMenuItem MenuItemGuardarImagenResultado;
    private javax.swing.JMenuItem MenuItemGuia;
    private javax.swing.JMenuItem MenuItemSalir;
    private javax.swing.JMenuItem MenuItemSobreGraphvisualx;
    private javax.swing.JPanel PanelContenedor;
    private javax.swing.JPanel PanelImagen1;
    private javax.swing.JPanel PanelImagen2;
    private javax.swing.JPanel PanelTexto1;
    private javax.swing.JPanel PanelTexto2;
    private javax.swing.JScrollPane ScrollPanelContenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar10;
    private javax.swing.JToolBar jToolBar11;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JToolBar jToolBar7;
    private javax.swing.JToolBar jToolBar8;
    private javax.swing.JToolBar jToolBar9;
    // End of variables declaration//GEN-END:variables
}
