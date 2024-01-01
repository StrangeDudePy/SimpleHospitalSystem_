package com.example.Hospital;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;

public class ErrorPreventforAddMenu  {
    private static AddButton aButton = new AddButton();
    private static String EURL="jdbc:sqlite:Databases/Patients.db";
    private static String DateQuerry = "SELECT  AppointDate,AppointTime FROM PatientsInfo WHERE AppointDate=? AND AppointTime=?";
    private String nameFormatter(Object[] storedData) {
        String nameString = storedData[1].toString();
        String[] wordSeparated = nameString.split("\\s+");
        StringBuilder result = new StringBuilder();
    
        for (String word : wordSeparated) {
            if (!word.isEmpty() && !word.isBlank()) {
                String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                result.append(formattedWord).append(" ");
            
            } else {
                break;
            }
        }
    
        return result.toString().trim();
    }

  
    

    public void getNameFormatter(Object[] Data){
        nameFormatter(Data);
    }

    private  boolean isNumeric(String str) {
        
        return str.matches("\\d+");
    }

    public static boolean containsNumeric(String input) {
        return input.matches(".*\\d.*");
    }


    private boolean isNameLegit(Object[] nameData){
        boolean namelegit=false;
        if (containsNumeric(nameFormatter(nameData))){
            return namelegit;
        }

        else if(containsNumeric(nameFormatter(nameData))==false) {
            namelegit=true;
            return namelegit;
        }

        else{
            System.out.println("Unknown Error");
            return namelegit;
        }
    }



    private boolean IsIdlegit(Object[] dataID){
        boolean idvalid = false;
        String idString = dataID[0].toString();
        String trimmedString = idString.trim();
        if(trimmedString.length()== 8 && isNumeric(idString)) {
            idvalid = true;
            return idvalid;
        }

        else {
            return idvalid;
        }


    }


    public boolean getNameLegitMethod(Object[] name_data){
        return isNameLegit(name_data);
    }

    

    public boolean getIdMethod(Object[] Data){
        return IsIdlegit(Data);
        
    }

    private boolean IsPhoneLegit(Object[] DataP){
        boolean pvalid = false;
        String pString = DataP[2].toString();
        String trimmedpString = pString.trim();
        if(trimmedpString.length()== 12 && isNumeric(pString)) {
            pvalid = true;
            return pvalid;
        }

        else {
            return pvalid;
        }
    }

    public boolean getPhoneMethod(Object[] DataP_){
        return IsPhoneLegit(DataP_);
    }



    private boolean isDateAvaliable(Object[] D_Data){
        boolean dateOK= false;
        String DateString = D_Data[3].toString();
        String TimeString = aButton.getTimeFormatter();
        try {
            
            try (Connection connection = DriverManager.getConnection(EURL)) {
    
                try (PreparedStatement DateStatement = connection.prepareStatement(DateQuerry)) {


                    DateStatement.setString(1,DateString);
                    DateStatement.setString(2, TimeString);
    
                    try (ResultSet rD = DateStatement.executeQuery()) {
                        
                        if(rD.next()){
                            return dateOK;
                        }

                        else{
                            dateOK=true;
                            return dateOK;
                        }
                        
                    }
                }
            }
    
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return dateOK;
        }
        
    }

    public boolean getDateOkMethod(Object[] date_data){
        return isDateAvaliable(date_data);
    }

   

    private void CheckEverything(Object[] id ,Object[] phonenumber ,Object[] date,Object[] name){
            Object Checks[][] = {{false,"ID(Must be 8 numerical digits)"},{false,"Phone Number(Must be 12 numerical digits)"},{false,"Appointment Date-Time(The selected date already booked)"}};
            Checks[0][0]=(Boolean) IsIdlegit(id);
            Checks[1][0]=(Boolean) IsPhoneLegit(phonenumber);
            Checks[2][0]=(Boolean) isDateAvaliable(date);
    
            
            String errMessage="";
            int numberoferrors=0;
            for(int i = 0 ; i <= 2  ;i++){
                if(!(Boolean) Checks[i][0]){
                String currentError= Checks[i][1].toString();
                numberoferrors++;
                if(i==0){
                    errMessage = "<html>Invalid " + "<br>" +currentError;
                }
                else{
                    errMessage += "<br>" + currentError;
                }
              
                }
                
            }
            
            if(nameFormatter(name).isEmpty() || nameFormatter(name).isBlank()|| isNameLegit(name)==false){
                    errMessage += "<br>Name(Name not entered and can't contain any numerical character)";
                    numberoferrors++;
                }
            
            if (numberoferrors==0){
                System.out.println("Success");
            }

            else{
                JFrame alertfFrame = new JFrame();
                JOptionPane.showMessageDialog(alertfFrame, errMessage+"<br>" + "(Also Check whitespaces)" + "</html>");
            }
            

          
            
           
            
    }

    public void getCheckMethod(Object[] id ,Object[] phonenumber ,Object[] date,Object[] name ){
        CheckEverything(id,phonenumber,date,name);
    }
    
  
}
