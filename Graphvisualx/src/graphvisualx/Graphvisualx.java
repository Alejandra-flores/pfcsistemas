/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphvisualx;


/**
 * Fichero Graphvisualx.java
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

public class Graphvisualx {

    /**
     * @param args the command line arguments
     */
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    /*try
    {
        UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");

    }
    catch (Exception e)
    {
        e.printStackTrace();
    }*/
    
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Grafico().setVisible(true);
            }
        });
    }
}
