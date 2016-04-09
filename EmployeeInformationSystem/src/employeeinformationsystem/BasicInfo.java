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
public class BasicInfo 
{
    private String firstName,lastName,ID,department,phone,address,email,title;//íntantiating instance variable to stor datas
    
    public BasicInfo(String firstName,String lastName, String ID,String address, String department, String phone, String email,String title)
    {
        this.firstName= firstName;
        this.lastName=lastName;
        this.ID=ID;
        this.department=department;
        this.phone=phone;
        this.address=address;
        this.email=email;
        this.title=title;
        //constructor method
    }
    
    public void setFirstName(String firstName)//
    {
        this.firstName = firstName;
        //mutator method
    }
    
    public void setLastName(String lastName)
    {
        this.lastName =lastName;
         //mutator method
    }
    
    public void setID(String ID)
    {
        this.ID=ID;
         //mutator method
    }
    
    public void setDepartment(String department)
    {
        this.department= department;
         //mutator method
    }
    
    
    public void setAddress(String address)
    {
        this.address=address;
         //mutator method
    }
    public void setEmail(String email)
    {
        this.email=email; 
         //mutator method
    }
    
    public void setTitle(String title)
    {
        this.title=title;
         //mutator method
    }
    
    public void setPhone(String phone)
    {
        this.phone=phone;
         //mutator method
    }
    
    public String getfirstName()
    {
        return firstName;
         //assessor method
    }
    
    public String getlastName()
    {
        return lastName;
        //assessor method
    }
    
    public String getID()
    {
        return ID;
        //assessor method
    }
    
    public String getDepartment()
    {   
        return department;
        //assessor method
    }
    
    public String getPhone()
    {
        return phone;
        //assessor method
    }
    
    public String getAddress()
    {
        return address;
        //assessor method
    }
    
    public String getEmail()
    {
        return email;
        //assessor method
    }
    
    public String getTitle()
    {
        return title;
        //assessor method
    }
    
    @Override
    public String toString()
    {
        return String.format("First Name: %s Last Name: %s\nID: %s\nDepartment: %s\nPhone Number: %s\nAddress: %s\nEmail Address: %s\nTitle: %s", getfirstName(), getlastName(),getID(),getDepartment(),getPhone(),getAddress(),getEmail(),getTitle());
        //overriden toString method to display all the entered variables.
    }
}
