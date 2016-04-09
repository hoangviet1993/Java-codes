/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employeeinformationsystem;

/**
 *
 Program:
 Written by:
 Description:
 Challenges::
 Time Spent:
 Given Input:                   Expected Output:
 --------------------          -------------------------



                   Revision History
 Date:                   By:               Action:
 ---------------------------------------------------
 01/31/2010         (initials)       Created
 */
public class SalaryCalc extends HoursAndSalary
{//several different simple calculations to be done to try and find various financial aspects
    private int insurance;
    private int overtimepayperhour;
    private int overtimeworked;//based on 1 week
    private double bonus;
    private double miscCost;
    private double yearlyExpense;
    private double totalsalary;
    public SalaryCalc(int payperhours,int budget,int totalhoursperweek,int insurance,double bonus, int overtimeworked )
    {
        super(payperhours,budget,totalhoursperweek);
        if (insurance < 0)
            {System.out.println("Numbers have to be non-negative. It has to been set to zero");
            insurance =0;
            }
        if (overtimeworked < 0)
            {
            System.out.println("Numbers have to be non-negative.It has to been set to zero.");
            overtimeworked =0;
            }
        if (bonus < 0)
            {
            System.out.println("Numbers have to be non-negative.It has to been set to zero.");
            bonus =0;
            }
        overtimepayperhour=payperhours*(1/3)+payperhours;//overtime work modifier
        this.insurance=insurance;
        this.bonus= bonus;
        this.overtimeworked = overtimeworked;
    }
    
    public void setInsurance(int insurance)
    {
        if (insurance < 0)
            {System.out.println("Numbers have to be non-negative. It has to been set to zero");
            insurance =0;
            }
        
        this.insurance=insurance;
    }
    
    public void setOvertimePay( int overtimepayperhour)
    {
        if (overtimepayperhour < 9)
           {
            System.out.println("Numbers have to be non-negative.It has to been set to zero.");
            overtimeworked =0;
           }
        
        this.overtimepayperhour= overtimepayperhour;
    }
    
    public void setBonus ( int bonus)
    {      
        if (bonus < 0)
           {
            System.out.println("Numbers have to be non-negative.It has to been set to zero.");
            bonus =0;
           }
        this.bonus=bonus;     
    }
    
    public void setOvertimeWorked( int overtimeworked)
    {
        if (overtimeworked < 0)
            {
            System.out.println("Numbers have to be non-negative.It has to been set to zero.");
            bonus =0;
            }
        this.overtimeworked=overtimeworked;     
    }
    public int getInsurance()
    {
        return insurance;
    }
    
    public int getOvertimePay ()
    {
        return overtimepayperhour;
    }
    
    public double getBonus()
    {
        return bonus;
    }
    
    public double getOvertimeWorked()
    {
        return overtimeworked;
    }
    
    public double totalYearlySalary()
    {
        totalsalary = (totalhoursperweek*payperhours*52)+(overtimepayperhour*overtimeworked*52); //52 weeks in a year    
        System.out.println("The total amount of salary paid out to employee including overtime is $"+totalsalary);
        return totalsalary;
    }
   
    public double miscYearlyCostCalc()
    {
        miscCost=bonus+insurance;
        System.out.println("The miscelanous cost has an amount of $"+miscCost);
        return miscCost;
    }
    
    public double yearlyExpenseCalc ( int noofemployee)
    {
        yearlyExpense=totalsalary*noofemployee+miscCost;
        System.out.println("The yearly expense has an amount of $"+yearlyExpense+"which consisted of misc. cost and total salary paid to ");
        return yearlyExpense;
    }
            
    public void budgetDeficitCalc ()
    {
        double Deficit= budget-yearlyExpense;
        if (Deficit> 0.0)
            System.out.println("The current budget has a surplus of $"+Deficit);
        else
            System.out.println("The current budget has a deficit of $"+Deficit);
    }
            
}
