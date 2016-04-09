
package vta3;

import javax.swing.JFrame;

/**
 *
 Program:VTA3-Currency Converter
 Written by:Viet Tran Quoc Hoang
 Description:Converts from selected currency to 4 other currencies(including current currency) using given formula
 Challenges:Setting up layout,testing for empty string
 Time Spent:8h
 Given Input:                   Expected Output:
 --------------------          -------------------------
USD 1                           USD1.00 Euro0.90 Pound0.66 CAD1.33 Yen119.70   
EU  1                           USD1.12 Euro1.00 Pound0.74 CAD0.67 Yen0.001 
JPY 100                         USD0.84 Euro0.75 Pound0.55 CAD1.11 Yen100.00
                   Revision History
 Date:                   By:               Action:
 ---------------------------------------------------
 10/16/2015              VT       Created and debugged
 */
public class VTA3 {

    public static void main(String[] args) {
        MyFrameCurrencyConverter panel = new MyFrameCurrencyConverter();
        JFrame frame = new JFrame("Currency Conversion");
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


