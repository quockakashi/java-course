/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package studentsystem;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Home extends javax.swing.JFrame {
    
    private List<Student> students;
    private JFileChooser fileChooser;
    private Student selectedStudent;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        scaleImage();
    }
    
    public Home(List<Student> students) {
        this.students = students;
        initComponents();
        init();
        
    }
    
    public void init() {
        genderBtnGroup.add(maleRadioBtn);
        genderBtnGroup.add(femaleRadioBtn);
        studentTable.setAutoCreateRowSorter(true);
        
        // set file chooser for choosing image button
        this.fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "png", "jpg");
        fileChooser.setFileFilter(filter);
        
        scaleImage();
        fillStudentsIntoTable();
        ListSelectionModel listSelectionModel =  studentTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener((e) -> {
            fillSelectiveStudent();
        });
        if(students.size() > 0) {
            studentTable.setRowSelectionInterval(0, 0);
            fillSelectiveStudent();
        }
        
        
  
   
    }
    
    public void fillSelectiveStudent() {
        int row = studentTable.getSelectedRow();
        
        Student selectiveStudent;
        
        if(studentTable.getSelectedRow() >= 0) {
            int studentId = (Integer) studentTable.getValueAt(row, 0);
        
        selectiveStudent = students.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElse(null);
        } else {
            selectiveStudent = new Student();
        }
        this.selectedStudent = selectiveStudent;
        
        if(selectiveStudent != null) {
            this.idTextField.setText(String.valueOf(selectiveStudent.getId()));
            this.nameTextField.setText(selectiveStudent.getName());
            this.addressTextField.setText(selectiveStudent.getAddress());
            this.scoreTextField.setText(String.valueOf(selectiveStudent.getScore()));
            this.noteTextField.setText(String.valueOf(selectiveStudent.getNote()));
            if(selectiveStudent.getGender() == 'M') {
                maleRadioBtn.setSelected(true);
            } else {
                femaleRadioBtn.setSelected(true);
            }
            if(selectiveStudent.getImagePath() != null && selectiveStudent.getImagePath().trim().compareTo("") != 0) {
                System.out.println(selectiveStudent.getImagePath());
                fillStudentImg(selectiveStudent.getImagePath());
            } else {
                scaleImage();
            }
        }
    }
    
    public void fillStudentsIntoTable() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        studentTable.setModel(defaultTableModel);
        
        
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Score");
        defaultTableModel.addColumn("Address");
        defaultTableModel.addColumn("Gender");
        defaultTableModel.addColumn("Note");
        
        students.forEach(student -> {
            defaultTableModel.addRow(new Object[] {student.getId(), student.getName(), student.getScore(), student.getAddress(), student.getGender(), student.getNote()});
        });
    }
    
    public void fillStudentImg(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(studentImage.getWidth(), studentImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        studentImage.setIcon(scaledIcon);
    }
    
    public boolean checkDuplicateId(int id) {
        return students.stream().anyMatch(student -> student.getId() == id);
    }
    
  public void scaleImage() {
        ImageIcon icon = new ImageIcon("images\\user.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(studentImage.getWidth(), studentImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        studentImage.setIcon(scaledIcon);
    }
    
    public void saveStudentHandler(Student student) {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.addRow(new Object[] {student.getId(), student.getName(), student.getScore(), student.getAddress(), student.getGender(), student.getNote()});
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderBtnGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        exportCsvBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        studentImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        maleRadioBtn = new javax.swing.JRadioButton();
        femaleRadioBtn = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        scoreTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        noteTextField = new javax.swing.JTextArea();
        updateBtn = new javax.swing.JButton();
        idTextField = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        browseImgBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        exportCsvBtn.setBackground(new java.awt.Color(0, 0, 0));
        exportCsvBtn.setForeground(new java.awt.Color(255, 255, 255));
        exportCsvBtn.setText("Export CSV");
        exportCsvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCsvBtnActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel2.setText("ID");

        jLabel5.setText("Name");

        jLabel6.setText("Address");

        jLabel7.setText("Gender");

        maleRadioBtn.setText("M");
        maleRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleRadioBtnActionPerformed(evt);
            }
        });

        femaleRadioBtn.setText("F");
        femaleRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleRadioBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Score");

        jLabel9.setText("Note");

        noteTextField.setColumns(20);
        noteTextField.setRows(5);
        jScrollPane1.setViewportView(noteTextField);

        updateBtn.setBackground(new java.awt.Color(0, 0, 0));
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        idTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextFieldActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(255, 0, 0));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Change Image");

        browseImgBtn.setText("Browse image");
        browseImgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseImgBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(1, 1, 1)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(nameTextField))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(6, 6, 6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(maleRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(femaleRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(studentImage, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(79, 79, 79)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(deleteBtn)
                        .addGap(36, 36, 36)
                        .addComponent(updateBtn)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(browseImgBtn)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(studentImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(scoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(maleRadioBtn)
                            .addComponent(femaleRadioBtn)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(browseImgBtn))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn))
                .addGap(36, 36, 36))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("STUDENT LIST");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Score", "Address", "Gender", "Note"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(studentTable);

        addBtn.setText("Add Student");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Import CSV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvImportBtn(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportCsvBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportCsvBtn)
                    .addComponent(addBtn)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportCsvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCsvBtnActionPerformed
        // TODO add your handling code here:
        JFileChooser saveChooser = new JFileChooser();
        saveChooser.setDialogTitle("Sepecify a file save");
        int option = fileChooser.showSaveDialog(this);
        if(option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if(!filePath.endsWith(".csv")) {
                filePath += ".csv";
            }
            
            try (FileWriter fw = new FileWriter(filePath);
                BufferedWriter bw = new BufferedWriter(fw);
                 ) {
                
                for(int i = 0; i < studentTable.getColumnCount(); i++) {
                    String columnName = studentTable.getColumnName(i);
                    bw.write(columnName);
                    if(i < studentTable.getColumnCount() - 1) {
                        bw.write(',');
                    }
                    
                }
                bw.newLine();
                
                for(int i = 0; i < studentTable.getRowCount(); i++) {
                    for(int j = 0; j < studentTable.getColumnCount(); j++) {
                        bw.append(studentTable.getValueAt(i, j).toString());
                        bw.write(",");
                    }
                    bw.newLine();
                }
                 JOptionPane.showMessageDialog(null, "Successfully saved", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch(IOException e) {
                JOptionPane.showMessageDialog(this, "Have a error when writing", "Error", JOptionPane.WARNING_MESSAGE );
            }
        }
    }//GEN-LAST:event_exportCsvBtnActionPerformed

    private void maleRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleRadioBtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_maleRadioBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        System.out.println(studentTable.getSelectedRow());
        System.out.println(((DefaultTableModel) studentTable.getModel()).getValueAt(0, 0));
        String name = nameTextField.getText().trim();
        String address = addressTextField.getText().trim();
        if(name == "") {
            JOptionPane.showMessageDialog(this, "Name required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            selectedStudent.setName(name);
        }
        int id = Integer.parseInt(idTextField.getText());
        if(selectedStudent.getId() != id) {
            if(!checkDuplicateId(id)) {
                selectedStudent.setId(id);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid id", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if(address == "") {
            JOptionPane.showMessageDialog(this, "Address required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            selectedStudent.setAddress(address);
        }
        if(maleRadioBtn.isSelected()) {
            selectedStudent.setGender('M');
        } else {
            selectedStudent.setGender('F');
        }
        selectedStudent.setScore(Double.parseDouble(scoreTextField.getText()));
        selectedStudent.setNote(noteTextField.getText());
        
        try {
            if(fileChooser.getSelectedFile() != null) {
            selectedStudent.changeAndSaveImage(fileChooser.getSelectedFile());
            }
            
            fileChooser.setSelectedFile(null);
            IOUtil.writeStudent(students);
            
            int selectedRow = studentTable.getSelectedRow();
            studentTable.setValueAt(selectedStudent.getId(), selectedRow, 0);
            studentTable.setValueAt(selectedStudent.getName(), selectedRow, 1);
            studentTable.setValueAt(selectedStudent.getScore(), selectedRow , 2);
            studentTable.setValueAt(selectedStudent.getAddress(), selectedRow , 3);
            studentTable.setValueAt(selectedStudent.getGender(), selectedRow , 4);
            studentTable.setValueAt(selectedStudent.getNote(), selectedRow , 5);
        }   catch(IOException e) {
            System.err.println(e);
        }
        
        JOptionPane.showMessageDialog(this, "Student updated", "Information", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_updateBtnActionPerformed

    private void idTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextFieldActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
          NewStudentForm newStudentForm = new NewStudentForm(students, this::saveStudentHandler);
          
          newStudentForm.pack();
          newStudentForm.setVisible(true);
          newStudentForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       
    }//GEN-LAST:event_addBtnActionPerformed

    private void femaleRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleRadioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleRadioBtnActionPerformed

    private void browseImgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseImgBtnActionPerformed
        // TODO add your handling code here:
                // select image button
        
        int output = fileChooser.showSaveDialog(fileChooser);
        if(output == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            fillStudentImg(path);
        }
  
    }//GEN-LAST:event_browseImgBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int row = studentTable.getSelectedRow();
        if(row != -1) {
            // get student id from the row
            int studentId = (Integer) studentTable.getValueAt(row, 0);
            Student selectedStudent = students.stream().filter((student) -> student.getId() == studentId).findFirst().orElse(null);
            int option = JOptionPane.showConfirmDialog(null, "Do your realy want to delete this student", "Confirm", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION) {
                students.remove(selectedStudent);
                selectedStudent.removeImage();
                int modelRow = studentTable.convertRowIndexToModel(row);
                DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
                model.removeRow(modelRow);
                
                try {
                    IOUtil.writeStudent(students);
                } catch(Exception e) {
                    System.err.println(e);
                }
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void csvImportBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvImportBtn
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Import student from other file will remove all current student. Do you want to continue?", "Warning", JOptionPane.WARNING_MESSAGE);
        if(option == JOptionPane.YES_OPTION) {
            students.clear();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();
            try {
                IOUtil.readStudentFromCsvFile(students, filePath);
                IOUtil.writeStudent(students);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Have error when reading!", "Error", JOptionPane.ERROR);
                return;
            }
            
            studentTable.removeAll();
            fillStudentsIntoTable();
        }
        }
    }//GEN-LAST:event_csvImportBtn

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton browseImgBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton exportCsvBtn;
    private javax.swing.JRadioButton femaleRadioBtn;
    private javax.swing.ButtonGroup genderBtnGroup;
    private javax.swing.JTextField idTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton maleRadioBtn;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextArea noteTextField;
    private javax.swing.JTextField scoreTextField;
    private javax.swing.JLabel studentImage;
    private javax.swing.JTable studentTable;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
