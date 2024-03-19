/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author LENOVO
 */
public class StudentSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        List<Student> students = new ArrayList<>();

        
        // firstly, we need to read students data from binary file
        IOUtil.readStudents(students);
        System.out.println(students);
        
        // call the first frame
        Home homeFrame = new Home(students);
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
