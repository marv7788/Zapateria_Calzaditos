/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JFrame;
import negocios.seed.ClientesSeed;
import ui.JAppmain_ui;

/**
 *
 * @author REBOOTSYSTEM
 */
public class JMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       // UsuariosSeed.insertUsuarios();
       // ClientesSeed.insertClients();
        
        
        JFrame login = new JLogin_ui();
        login.setVisible(true);
        
        /*
        JFrame main_ui = new JAppmain_ui();
        main_ui.setTitle("Aplicación para Zapaterías");
        main_ui.setVisible(true);
*/
    }
    
}
