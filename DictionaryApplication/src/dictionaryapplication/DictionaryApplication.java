/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dictionaryapplication;

import java.io.File;

/**
 *
 * @author LENOVO
 */
public class DictionaryApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new SplashScreen(null, true).setVisible(true);
        Home home = new Home();
        home.setVisible(true);
    }
    
}
