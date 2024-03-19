/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentsystem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class IOUtil {
    public static void readStudents(List<Student> students)  throws IOException{
        File file = new File("students.dat");
        if(!file.exists()) {
            file.createNewFile();
            return;
        }
        
        try(
                var fis = new FileInputStream("students.dat");
                var bfis = new BufferedInputStream(fis);
                var dis = new DataInputStream(bfis)) {
            int size;
            try{
                size = dis.readInt();
            } catch(IOException e) {
                return;
            }
            for(int i = 0; i < size; i++) {
                int id = dis.readInt();
                String name = dis.readUTF();
                String address = dis.readUTF();
                char gender = dis.readChar();
                double score = dis.readDouble();
                String note = dis.readUTF();
                String imagePath = dis.readUTF();
                
                Student student = new Student(id, name, score, imagePath, address, note, gender);
                students.add(student);
                
            }
            
        } 
    }
    
    public static void writeStudent(List<Student>  students) throws IOException{
        try (
            var fos = new FileOutputStream("students.dat");
            var bfos = new BufferedOutputStream(fos); // improve the performance
            var dos = new DataOutputStream(bfos);
            ) {
            dos.writeInt(students.size());
            for(var student : students) {
                dos.writeInt(student.getId());
                dos.writeUTF(student.getName());
                dos.writeUTF(student.getAddress());
                dos.writeChar(student.getGender());
                dos.writeDouble(student.getScore());
                dos.writeUTF(student.getNote());
                dos.writeUTF(student.getImagePath());
            }
        }
    }
    
    public static void readStudentFromCsvFile(List<Student> students, String filePath) throws IOException{
        try(
            var fr = new FileReader(filePath);
            var bfr = new BufferedReader(fr);
                ) {
            bfr.readLine(); // ignore the first line (title line)
            String line;
            while((line = bfr.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                double score = Double.parseDouble(values[2]);
                String address = values[3];
                char gender = values[4].toCharArray()[0];
                String note = "";
                if(values.length == 4) {
                    note = values[5];
                }
                
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setScore(score);
                student.setAddress(address);
                student.setGender(gender);
                student.setNote(note);
                student.setImagePath("");
                students.add(student);
            }
        }
    }
}
