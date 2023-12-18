package com.example.Hospital;

import javax.swing.*;

import java.awt.*;
import java.sql.Date;

public class MainMenu extends GUI { 
    static PatientManagement managementObj = new PatientManagement();
    static String[] IDdata = managementObj.getpatintID();
    static String[] NameData = managementObj.getpatintName();
    static int[] NumberData = managementObj.getpatintNo();
    static   Date[] DateData = managementObj.getpatintDate();

    protected static void createMainMenu(JButton option1, JButton option2, JButton option3, JButton option4, JPanel panel, JFrame frame) {

        frame.setSize(1200, 800);
        frame.add(panel);
        frame.setLayout(null);
        frame.setResizable(false);
        MainMenu nMainMenu = new MainMenu();
        PatientManagement mngObj = new PatientManagement();
        ImageIcon imageIcon = new ImageIcon("/home/sefa/Downloads/HospitalAdminPanel.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon newImageIcon = new ImageIcon(newImage);

        // Resim etiketini oluştur ve konumunu ayarla
        JLabel imageLabel = new JLabel(newImageIcon);
        imageLabel.setBounds(400, 100, 400, 300);
        frame.add(imageLabel);

        option1 = new JButton("Patient Management System");
        option1.setBounds(300, 450, 600, 50);
        option1.addActionListener(e -> {System.out.println("Poo");
        mngObj.getpatintNo();
        mngObj.getpatintID();
        mngObj.getpatintName();
        mngObj.CreateTheO1(nMainMenu);
        
    });
        frame.add(option1);
    

        option2 = new JButton("Option 2 ");
        option2.setBounds(300, 525, 600, 50);
        option2.addActionListener(new GUI());
        frame.add(option2);

        option3 = new JButton("Option 3 ");
        option3.setBounds(300, 600, 600, 50);
        option3.addActionListener(new GUI());
        frame.add(option3);

        option4 = new JButton("Option 4");
        option4.setBounds(300, 675, 600, 50);
        option4.addActionListener(new GUI());
        frame.add(option4);


        frame.setVisible(true);

     
    
    
        
}

    public void CreateOption1Page(JFrame x , JTable y){
        Object[][] data = new Object[100][4];
        
        
        x = new JFrame();
        x.setResizable(false);
       
      
        x.setTitle("Patients");
 
        for(int k = 0; NameData[k]!=null;k++)
        {
            for (int j = 0 ; j <= k ; j++){
                data[k][j] = NumberData[k];
                data[k][j+1] = NameData[k];
                data[k][j+2] = IDdata[k];
                data[k][j+3] = DateData[k];

            }
            
        }
 
       



        String[] columnNames = { "Patient No", "Patient Name-Surname", "ID","Appointment Date" };
 
       
        y= new JTable(data, columnNames);
        y.setBounds(30, 40, 300, 450);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(y);
        x.add(sp,BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        x.add(buttonPanel,BorderLayout.SOUTH);
        x.setSize(500,300);

        // Frame Size
        x.setSize(600, 400);
        // Frame Visible = true
        x.setVisible(true);
    
 
    }
}



