/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.FileChannel;

/**
 *
 * @author LENOVO
 */
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double score;
    private String imagePath;
    private String address;
    private String note;
    private char gender; // M: male, F: female
    
    
    public Student() {
       
    }
    
    public Student(int id, String name, double score, String imagePath, String address, String note, char gender) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.imagePath = imagePath;
        this.address = address;
        this.note = note;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", score=" + score + ", imagePath=" + imagePath + ", address=" + address + ", note=" + note + ", gender=" + gender + '}';
    }
    
    public void changeAndSaveImage(File file) throws IOException{
        if(imagePath != null) {
            File existedFile = new File(imagePath);
            if(existedFile.exists()) {
                existedFile.delete();
            }
        }
            
        String fileExtension = file.getName().substring(file.getName().lastIndexOf('.')); // get file extension inclues '.'

        File desDir = new File("student_imgs");
        if(!desDir.exists()) {
            desDir.mkdir();
        }
        try(var fis = new FileInputStream(file);
                var fos = new FileOutputStream("student_imgs\\" + id + fileExtension)) {

            FileChannel sourceChannel = fis.getChannel();
            FileChannel desChannel = fos.getChannel();

            desChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            this.imagePath = "student_imgs\\" + id + fileExtension;

            System.out.println("Image saved");
        }
    
    }
    
    public void removeImage() {
        if(imagePath != null) {
            File file = new File(imagePath);
            if(file.exists()) {
                file.delete();
            }
        }
    }
    
    
}
