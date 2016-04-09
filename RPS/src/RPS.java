/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 Program:RPS
 Written by:Viet Tran Quoc Hoang
 Description:A rock,paper,scissor game simulator where the two players are the user and the program.
 * One game is 3 rounds long. Program will display both both players choice, the result of each round and the final result of the game
 Challenges:while loop as well as covering all different scenarios
 Time Spent:6 hours
 Given Input:                   Expected Output:
 --------------------          -------------------------
1)r,p,s                         2)win,lose,lose you lost!
2)r,p,s                         2)tie, tie, lose you lost!
3)r,p,s                         3)win,tie,lose its a draw!
                   Revision History
 Date:                   By:               Action:
 ---------------------------------------------------
 7/21/2015              VT               Created the code
 */

import java.util.Random;//random library
import javax.swing.JOptionPane;//GUI library
public class RPS 
{   //random number generator to be used in getPCThrow
    private static final Random randomNumbers = new Random();
    
    //enumeration with constants that represent the game status
    private enum Status { TIE,LOSE,WIN};
    
    //constants to represent rock,paper,scissor throws as well as variable to store datas to be used by play()
    private static final int ROCK =1;
    private static final int PAPER =2;
    private static final int SCISSOR=3;
    private static int Player=0;
    private static int PC =0;
    private static String RPS;
    
    public static void main( String[] args)
    {   //create string variable to store input from GUI
        String stringstart= JOptionPane.showInputDialog("Do you want to play a game of Paper,Rock,Scissor? Type y for Yes or n for No");                        
        //while loop to keep running as long as user wants to keep playing
        while (stringstart.equals("y"))
        {   //call for play method
            play();
            //gather user input again to make sure user want to play or stop playing
            stringstart = JOptionPane.showInputDialog("Do you want to keep playing? Type y for Yes or n for No!");            
        }
        JOptionPane.showMessageDialog(null,"Thanks for playing! The program will now end");
        System.exit(0);
    }
    public static void play()
    {   //initializing instance variables to store private datas
        int count=0;//represent number of rounds
        int userwinCount =0;//represent number of times user win
        int PCwinCount =0;//represent numbr of times pc win
        Status gameStatus;//enumeration that can be TIE, WIN or LOSE
        while( count<3 )//this loop will run as long as count is below 3
        {   Player=getUserThrow();//call both methods to get datas regarding what the PC throws and what the user thrws
            PC=getPCThrow();                        
            if (Player == ROCK)//if player throws rock
                {if(PC == SCISSOR)//and pc throws scissor while player throw rocks, user win
                    {userwinCount++;//increment the number of times user win by one
                    count++;         //increment the number of round by one       
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws"+convertnumtoRPS(Player)+"\nYou won round "+count+"!");                   
                    //display results
                    }
                else if(Player-PC==0)// if both throws the same thing then its a draw
                    {count++;       //increment the number of round by one 
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\nIt's a tie for round "+count+"!");                                             
                    }
                else //otherwise, PC won and the user lost
                    {PCwinCount++;//increment the number of times pc win by one
                    count++;    //increment the number of round by one 
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\nYou lost round "+count+"!");           
                    //display results
                    }
                }
            if (Player == PAPER) //if player throws paper
                {if (PC ==ROCK) // and pc throws rock while player throws paper, user won and pc lost
                    {userwinCount++;//increment the number of times user win by one
                    count++;        //increment the number of round by one    
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\n"+"You won round "+count+"!");                                             
                    //display results
                    }
                else if(Player-PC==0)//same as previous if chain
                    {count++;           
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\n"+"It's a tie for round "+count+"!");                                            
                    }
                else //same as previous if chain
                    {PCwinCount++;
                    count++;
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\n"+"You lost round "+count+"!");                                 
                    }
                }
            if (Player==SCISSOR)//if player throws scissor
                {if (PC == PAPER)//and pc throws paper, user win, pc lost
                    {userwinCount++;//increment the number of times user win by one
                    count++;         //increment the number of round by one  
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\nYou won round "+count+"!");                                                 
                    }
                else if(Player -PC==0)//same as previous if chain
                    {count++;
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\nIt's a tie for round "+count+"!");                                             
                    }
                else//same as previous if chain
                    {PCwinCount++;
                    count++;
                    JOptionPane.showMessageDialog(null,"PC throws "+convertnumtoRPS(PC)+"\nUser throws "+convertnumtoRPS(Player)+"\nYou lost round "+count+"!");                                 
                    }
                }           
        }    
            if (userwinCount>PCwinCount)//if user wins more rounds, the game is won by user
                {gameStatus = Status.WIN;
                }
            else if (userwinCount<PCwinCount)//if user loses more rounds, user lost the game
                {gameStatus = Status.LOSE;
                }
            else //otherwise, its a tie
                {gameStatus = Status.TIE;
                }
            if (gameStatus == Status.WIN)//display message when user wins
            {
                JOptionPane.showMessageDialog(null,"Congratulations! You won!");
            }
            if (gameStatus == Status.LOSE)//display message when user loses
            {
                JOptionPane.showMessageDialog(null,"You lost! Better luck next time!");
            }
            if (gameStatus == Status.TIE)//display message when both ties
            {
                JOptionPane.showMessageDialog(null,"It's a tie between you and the machine. Rematch!");
            }
    } 
    public static int getPCThrow()
    {   //get random number then return it
        int num = 1 + randomNumbers.nextInt( 3 );//only 3 values for rock,paper,scissor
        return num;
    }
    
    public static int getUserThrow()
    {   //get user input then return it       
        String userinput=JOptionPane.showInputDialog("Please enter r for Rock,p for Paper and s for Scissor");        
        int num=0;
        switch ( userinput )
            {case "r":               
                num=1;
                break;
            case "p":               
                num=2;
                break;
            case "s":              
                num=3;
                break;       
            }
        return num;
    }  
    
    public static String convertnumtoRPS(int num)//takes in an int number as parameter and return a string to be displayed by GUI
    {    switch (num)
        {   case 1:
                RPS ="Rock";
                break;
            case 2:
                RPS ="Paper";
                break;
            case 3:
                RPS ="Scissor";
                break;
        }
        return RPS;
    }
    
}
