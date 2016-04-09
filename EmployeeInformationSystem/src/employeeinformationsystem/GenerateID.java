/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employeeinformationsystem;

/**
 *
 Program:EmployeeInformationSystem.java
 Written by:Viet Tran Quoc Hoang
 Description:This program allows users to enter,edit,find and delete info about its employee and allow simple budget calculations
 Challenges:configuring arraylists ,concatenating department name and position number
 Time Spent:3 days
 Given Input:                   Expected Output:
 --------------------          -------------------------



                   Revision History
 Date:                   By:               Action:
 ---------------------------------------------------
 8/6/2015           VT                      Started writing
 * 9/6/2015         VT                      Figuring out basicinfo and generateID,worked on employeeclient
 * 10/6/2015        VT                      bug fix and submit
 */
import java.util.Random;
public class GenerateID 
{
    private BasicInfo department;
    private String EmployeeID;
    private String positionnum;
    private String departmentID;
    private String concatenatedID;
    //instantiating instance variables
    public GenerateID(BasicInfo department)
    {     
       this.department=department;//constructor method with composition 
       
    }
   
    public void setEmployeeID(String EmployeeID)
    {
       this.EmployeeID = EmployeeID;//mutator method
    }
    
    public void setPositionNum(String positionnum)
    {
       this.positionnum=positionnum;//mutator method
    }
    
    public void setConcatenatedID(String concatenatedID)
    {
        this.concatenatedID=concatenatedID;
    }    
    public String getEmployeeID()//create random number then return it
    {Random randomEmployeeID = new Random();  //creating new random object  
        {
           EmployeeID = Integer.toString(randomEmployeeID.nextInt((9999-1000)+1)+1000);  //generate 4 non-unique digits random number           
        }
     return EmployeeID;
    }
    
    public String getDepartmentID(String department)//takes in a string and return the short handed version to be used later
    {
    if (department.equals("English"))
        departmentID = "ENG";
    else if (department.equals("Sciences"))
        departmentID = "SCI";
    else if (department.equals("Engineering"))
        departmentID = "ENGR";
    else if (department.equals("Math"))    
        departmentID = "MAT";
    else if (department.equals("Social Sciences"))
        departmentID = "SOC";
    else if (department.equals("Computer Science"))
        departmentID = "CSC";
    else if (department.equals("Nursing"))
        departmentID = "NUR";
    else if (department.equals("Arts"))
        departmentID = "ART";
    else if (department.equals("Business"))
        departmentID = "BUS";
    else
        departmentID ="N/A";
       return (departmentID);
    }
    
    public String getPositionNum()//create a random number and return the departmentID concatenated at the beginning
    {
     Random randomPositionNum = new Random();       
       {
           positionnum  = Integer.toString(randomPositionNum.nextInt((9999-1000)+1)+1000); 
           //generating 4 non-unique digits random number
           
           
       }    
       return (positionnum);
    }
    
    public String getConcatenatedID()
    {
        concatenatedID = departmentID+positionnum;
        return concatenatedID;
    }
   
    @Override
    public String toString()//display result, including the BasicInfo object
    {
        return String.format("%s\nEmployeeID: %s\nPosition Number: %s\n",department,EmployeeID,concatenatedID);    
    }    
}
