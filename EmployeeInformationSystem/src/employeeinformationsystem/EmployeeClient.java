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
import java.util.ArrayList;//importing various library for usage
import java.util.Scanner;
public class EmployeeClient 
{   private static int count=0;//instantiating static variable to be used to count how many employees added

    public static void main(String[] args) 
    {   
        ArrayList<String>employeefName = new ArrayList<>();//arraylist of various private datas to store info
        ArrayList<String>employeelName = new ArrayList<>();
        ArrayList<String>employeeID = new ArrayList<>();
        ArrayList<String>employeeDept = new ArrayList<>();
        ArrayList<String>employeePhone = new ArrayList<>();
        ArrayList<String>employeeAddress = new ArrayList<>();
        ArrayList<String>employeeEmail = new ArrayList<>();
        ArrayList<String>employeeTitle = new ArrayList<>();
        ArrayList<String>employeeWorkID = new ArrayList<>();
        ArrayList<String>employeePositionNum = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Employee Information Managing Programme!\nPress y to start!");//as long as input is y, loop will keep going and asking the user what to do until user wants to quit
        String start = input.nextLine();
        while (start.equals("y"))
        {
            System.out.println(" There are currently "+employeefName.size()+" employees stored in the system.");//display current number of employees added
            System.out.println(" Press a to start adding employee's info,f to find info about employee,e to edit employee's info,\n  d to delete,m for editing financial info and q to quit the program!!");//presenting options
            String whattodo = input.nextLine();
            if (whattodo.equals("a"))
            {   //ask users to entere relevant info
                System.out.println("Please enter his/her first name:");
                String fName= input.nextLine();
                System.out.println("Please enter his/her last name:");
                String lName = input.nextLine();
                System.out.println("Please enter his/her ID number:");
                String ID = input.nextLine();
                System.out.println("Please enter his/her department:");
                String department = input.nextLine();
                System.out.println("Please enter his/her phone number:");
                String phone = input.nextLine();
                System.out.println("Please enter his/her address:");
                String address = input.nextLine();
                System.out.println("Please enter his/her email:");
                String email = input.nextLine();
                System.out.println("Please enter his/her title:");
                String title = input.nextLine();
                employeefName.add(fName);
                employeelName.add(lName);
                employeeID.add(ID);
                employeeDept.add(department);
                employeePhone.add(phone);
                employeeAddress.add(address);
                employeeEmail.add(email);
                employeeTitle.add(title);        //add info into appropriate arraylists         
                BasicInfo employee = new BasicInfo(employeefName.get(count),employeelName.get(count),employeeID.get(count),employeeAddress.get(count),employeeDept.get(count),employeePhone.get(count),employeeEmail.get(count),employeeTitle.get(count));                        
                GenerateID employeeIDno =new GenerateID(employee);     //creating new objects to be used by toString           
                employeeWorkID.add(employeeIDno.getEmployeeID());  //generate random numbers and save into array
                employeeIDno.getDepartmentID(department);//grab departmentID to be concatenated to positionnum
                employeePositionNum.add(employeeIDno.getPositionNum());   //add info into appropriate arraylists 
                employeeIDno.getConcatenatedID();//combine departmentID and positionnum...this is done individually and not saved into an array 
                System.out.printf("%n%s:%n%n%s%n%n","Updated employee info", employeeIDno.toString());//display using toString
                count++;
            }
            
            if (whattodo.equals("f"))
            {
                System.out.println("Please enter the employee's first name:");//find employee via searching through arraylists
                String Name= input.nextLine();
                if(employeefName.contains(Name))//if input matches one of the arraylist entries
                {
                    int index=employeefName.indexOf(Name);//display found info using the indexOf function to generate the index number
                    BasicInfo employee = new BasicInfo(employeefName.get(index),employeelName.get(index),employeeID.get(index),employeeAddress.get(index),employeeDept.get(index),employeePhone.get(index),employeeEmail.get(index),employeeTitle.get(index));
                    GenerateID employeeIDno =new GenerateID(employee);                  
                    employeeIDno.getDepartmentID(employeeDept.get(index));
                    employeeIDno.setEmployeeID(employeeWorkID.get(index));
                    employeeIDno.setPositionNum(employeePositionNum.get(index));
                    employeeIDno.getConcatenatedID();
                    System.out.printf("%n%s:%n%n%s%n%n","Employee found: ", employeeIDno.toString());     
                }
                else
                    System.out.println("Employee cannnot be found in the database!");
            }
            
            if (whattodo.equals("e"))
            {
                System.out.println("Please enter the employee's first name for identification purpose");
                String Nametobefound = input.nextLine();//using the same mechanism as above to edit info
                if(employeefName.contains(Nametobefound))
                {
                    int indexno=employeefName.indexOf(Nametobefound);
                    int editoption=0;
                    
                    while (editoption<9) //going into each different arraylist and individually edit the corresponding entry
                        {   System.out.println("What categories of info would you like to edit about employee "+employeefName.get(indexno)+ ":");
                            System.out.println("Enter 1 for first name,2 for last name, 3 for ID, 4 for department,\n5 for phone number, 6 for address, 7 for email and 8 for position title and 9 or any other number to quit:");
                            editoption=input.nextInt();
                            input.nextLine();
                            switch(editoption)
                            {case 1:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new first name: ");
                                String newfirstN=input.nextLine();
                                employeefName.set(indexno,newfirstN);
                                break;
                            case 2:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new last name: ");
                                String newlastN=input.nextLine();
                                employeelName.set(indexno,newlastN);
                                break;    
                            case 3:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new ID: ");
                                String newID=input.nextLine();
                                employeeID.set(indexno,newID);
                                break;    
                            case 4:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new department: ");
                                String newdept=input.nextLine();
                                employeeDept.set(indexno,newdept);
                                break;
                            case 5:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new phone number: ");
                                String newphone=input.nextLine();
                                employeeAddress.set(indexno,newphone);
                                break;
                            case 6:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new address: ");
                                String newaddress=input.nextLine();
                                employeeAddress.set(indexno,newaddress);
                                break;
                            case 7:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new email: ");
                                String newemail=input.nextLine();
                                employeeEmail.set(indexno,newemail);
                                break;   
                            case 8:
                                System.out.println("Please enter "+employeefName.get(indexno)+"'s new position title: ");
                                String newtitle=input.nextLine();
                                employeeEmail.set(indexno,newtitle);
                                break; 
                            default:
                                System.out.println("Editing completed!");
                                break;
                            }
                    BasicInfo employee = new BasicInfo(employeefName.get(indexno),employeelName.get(indexno),employeeID.get(indexno),employeeAddress.get(indexno),employeeDept.get(indexno),employeePhone.get(indexno),employeeEmail.get(indexno),employeeTitle.get(indexno));
                    GenerateID employeeIDno =new GenerateID(employee);                  
                    employeeIDno.getDepartmentID(employeeDept.get(indexno));
                    employeeIDno.setEmployeeID(employeeWorkID.get(indexno));
                    employeeIDno.setPositionNum(employeePositionNum.get(indexno));
                    employeeIDno.getConcatenatedID();//display edited info
                    System.out.printf("%n%s:%n%n%s%n%n","Employee info has been edited: ", employeeIDno.toString());  
                        }  
                }
                    else
                    {
                        System.out.println("Employee cannot be found");
                    }
                
            }
            
            if(whattodo.equals("d"))
            {
                System.out.println("Please enter the employee's first name for identification purpose");
                String Nametobedeleted= input.nextLine();
                if(employeefName.contains(Nametobedeleted))//find approriate entry using the same mechanism as above
                {
                    int indexdelete=employeefName.indexOf(Nametobedeleted);
                    BasicInfo employee = new BasicInfo(employeefName.get(indexdelete),employeelName.get(indexdelete),employeeID.get(indexdelete),employeeAddress.get(indexdelete),employeeDept.get(indexdelete),employeePhone.get(indexdelete),employeeEmail.get(indexdelete),employeeTitle.get(indexdelete));
                    System.out.printf("%n%s:%n%n%s%n%n","Employee found: ", employee.toString());
                    System.out.println("Do you want to remove this employee from the database? Enter y for yes");
                    String deleteconfirm=input.nextLine();
                    if(deleteconfirm.equals("y"))
                    {
                        System.out.println("Deleting employee "+employeefName.get(indexdelete)+ "'s info");
                        employeefName.remove(indexdelete);
                        employeelName.remove(indexdelete);
                        employeeID.remove(indexdelete);
                        employeeDept.remove(indexdelete);
                        employeePhone.remove(indexdelete);
                        employeeAddress.remove(indexdelete);
                        employeeEmail.remove(indexdelete);
                        employeeTitle.remove(indexdelete);
                        employeeWorkID.remove(indexdelete);
                        employeePositionNum.remove(indexdelete);//remove entry from array list
                        count--;
                    }
                }
                else
                    System.out.println("Employee cannot be found");
            }
            
            if ( whattodo.equals("m"))//ask user to enter appropriate input in order to deduce calculation
            {   
               
                    System.out.println("Please enter the wage per hours: ");
                    int salary = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the total budget: ");
                    int budget = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the total hours worked per week: ");
                    int timeworked= input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the total insurance cost: ");
                    int insurance = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the total bonus to be given out: ");
                    int bonus = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the total overtime hours worked per week: ");
                    int overtimeworked = input.nextInt();
                    input.nextLine();
                    SalaryCalc company = new SalaryCalc(salary,budget,timeworked,insurance,bonus,overtimeworked);
                    company.totalYearlySalary();
                    company.miscYearlyCostCalc();
                    System.out.println(" There are currently "+employeefName.size()+" employees stored in the system.");
                    System.out.println(" Is this the correct number of employees?Enter y for yes and n for no!");
                    String confirmnumofemployees=input.nextLine();
                    if (confirmnumofemployees.equals("y"))
                    {
                        company.yearlyExpenseCalc(employeefName.size());//takes in number of employee using the size of the array lists
                        company.budgetDeficitCalc();
                    }
                    else
                    {   
                        System.out.println("Please enter the number of employees: ");
                        int numofemployees = input.nextInt();
                        input.nextLine();
                        company.yearlyExpenseCalc(numofemployees);//takes in entered number of employee instead of array lists
                        company.budgetDeficitCalc();
                    }
            }
            
            
            if (whattodo.equals("q"))
            {
                System.out.println("The program will now end!");
                break;
            }
            
            
        }
    }
}
