/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krb;

import javax.swing.JFrame;
import pages.login;

/**
 *
 * @author Mahardicka
 */
public class Krb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame loginPage = new login();
        System.out.println("Render login page");
        loginPage.setVisible(true);
        
    }
    
}
