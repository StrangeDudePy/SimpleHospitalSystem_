package com.example.Hospital;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;




public class GUI extends PatientManagement implements ActionListener {
    private static String G_URL = "jdbc:sqlite:Databases/HospitalInfo.db";
    private static String Staffpassquery = "SELECT pass FROM staffinfo WHERE staffnum = ? ";
    private static Connection gconnect = null;

    public String logincheck(String id, String querry) {
        String knownpass = null;
        try {
            // Database connection
            gconnect = DriverManager.getConnection(G_URL);
    
            // Using prepared statement to start query with parameters
            try (PreparedStatement pstms = gconnect.prepareStatement(querry)) {
                // Setting the value for query
                pstms.setString(1, id);
    
                try (ResultSet r = pstms.executeQuery()) {
                    // If password is found
                    if (r.next()) {
                        knownpass = r.getString("pass");
                        
                    
                    } else {
                        // The ID is not found
                        System.out.println("Geçersiz id");
                      
                    }
                }
            }
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           
        } finally {
            // Close the database connection in a finally block
            try {
                if (gconnect != null) {
                    gconnect.close();
                }
            } catch (SQLException e) {
                // Handle the closing exception if needed
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    
        return knownpass;
    }
    
    private static JFrame newwindowFrame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();
    private static JButton option1 = new JButton();
 
    private static JPanel panele = new JPanel();
    

    //new window if the login is succesful
    protected static void  NewWindow(JFrame obj){
        obj.dispose();
        MainMenu.createMainMenu(option1,panele,newwindowFrame);
    }
    

    private static JLabel idlabel;
    private static JTextField userid;
    private static JLabel passLabel;
    private static JPasswordField passwordt;
    private static JButton conbutton;
    private static JLabel succes;
    public static void main(String[] args) {
        
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.add(panel);

        panel.setLayout(null);

        idlabel = new JLabel("STAFF ID");
        idlabel.setBounds(10,20,80,25);
        panel.add(idlabel);

        userid = new JTextField();
        userid.setBounds(100,20,165,25);
        panel.add(userid);

        passLabel = new JLabel("Password");
        passLabel.setBounds(10,50,80,25);
        panel.add(passLabel);

        passwordt = new JPasswordField();
        passwordt.setBounds(100,50,165,25);
        panel.add(passwordt);

        conbutton = new JButton("Login");
        conbutton.setBounds(10,80,80,25);
        conbutton.addActionListener(new GUI());
        panel.add(conbutton);

        succes = new JLabel("");
        succes.setBounds(10,110,300,25);
        panel.add(succes);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
       
        String user = userid.getText();
        char [] passwordchar = passwordt.getPassword();
        String password = new String(passwordchar);
        if(e.getSource()==conbutton){
        if (password.equals(logincheck(user,Staffpassquery))){
            succes.setText("Login Succesful");
            System.out.println("a");
            if (e.getSource() == conbutton){
                
                NewWindow(frame);
                

             
            }

           
          
        }
        
        else if(logincheck(user, Staffpassquery) == null){
            succes.setText("Invalid ID");
        }
        else{
            succes.setText("Invalid Credentials");
        }

        }

    

    }

    public static  String getid(){
        String idinfo = userid.getText();
        return idinfo;
    }
    


  
}
