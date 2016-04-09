
package vta3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
public class MyFrameCurrencyConverter  extends JPanel  {
    
    private JTextField value;
    private final JPanel toppanel,bottompanel,overallpanel,countrynamepanel,outputpanel,buttonpanel;
    private final JButton convert,clear;
    private JComboBox currency;
    private JLabel USDdollar,GBPpound,CADdollar,JPYyen,EUReuro;
    private final JLabel USDlabel,EURlabel,GBPlabel,CADlabel,JPYlabel;
    private String i,selectedcurrency;
    private double input,USDoutput,EURoutput,GBPoutput,CADoutput,JPYoutput;
    private double onebillion =1000000000;

    public MyFrameCurrencyConverter(){
        
        //this panel includes buttons,initial currency and text field to store values
        toppanel = new JPanel();
        toppanel.setLayout(new GridLayout(1,4,5,5));
        String[] countrylist = {"USD-$","EUR-€","GBP-£","CAD-C$","JPY-¥"};
        currency = new JComboBox(countrylist);
        ((JLabel)currency.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);//center the text in Jlabel
        value = new JTextField(9);
        convert = new JButton("Convert");
        clear = new JButton("Clear");
        toppanel.add(currency);
        toppanel.add(value);
        toppanel.add(convert);
        toppanel.add(clear);
        
     //creating United States label,icon flag and output
       
        USDlabel = new JLabel("US Dollar-$");
        USDdollar = new JLabel();
        USDdollar.setForeground(Color.blue);
        USDlabel.setForeground(Color.blue);
        
     //creating European Union label and output
        
        EURlabel = new JLabel("Euro-€");
        EUReuro = new JLabel();
        EUReuro.setForeground(Color.GREEN);
        EURlabel.setForeground(Color.GREEN);
      
     //creating Greater Britain label and output
        
        GBPlabel = new JLabel("British Pound-£");
        GBPpound = new JLabel();
        GBPpound.setForeground(Color.RED);
        GBPlabel.setForeground(Color.RED);
        
     //creating Canada labeland output
 
        CADlabel = new JLabel("Canadian Dollar-C$");
        CADdollar = new JLabel();
        CADdollar.setForeground(Color.MAGENTA);
        CADlabel.setForeground(Color.MAGENTA);
       
      //creating Japan label and output
        
        JPYlabel = new JLabel("Japenese Yen-¥");
        JPYyen = new JLabel();
        JPYyen.setForeground(Color.black);
        JPYlabel.setForeground(Color.black);
        
        
        //this panel includes all the country name
        countrynamepanel = new JPanel();
        countrynamepanel.setLayout(new GridLayout(5,1,5,5));
        countrynamepanel.add(USDlabel);
        countrynamepanel.add(EURlabel);
        countrynamepanel.add(GBPlabel);
        countrynamepanel.add(CADlabel);
        countrynamepanel.add(JPYlabel);
        
        //this panel includes all the currency output
        outputpanel = new JPanel();
        outputpanel.setLayout(new GridLayout(5,1,5,5));
        outputpanel.add(USDdollar);
        outputpanel.add(EUReuro);
        outputpanel.add(GBPpound);
        outputpanel.add(CADdollar);
        outputpanel.add(JPYyen);
        //this panel includes all the countries and their currency result
        bottompanel = new JPanel();
        bottompanel.setLayout(new GridLayout(1,2));
        bottompanel.add(countrynamepanel);
        bottompanel.add(outputpanel);
        
        //this panel includes two buttons
        buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(1,2,5,5));
        buttonpanel.add(convert);
        buttonpanel.add(clear);
        //this panel includes all panels
        overallpanel = new JPanel();
        overallpanel.setLayout(new BorderLayout());
        overallpanel.add(toppanel,BorderLayout.NORTH);
        overallpanel.add(bottompanel,BorderLayout.SOUTH);
        overallpanel.add(buttonpanel,BorderLayout.CENTER);
        currency.addActionListener(//event handling for when user change the intial currency from dropbox
                                    //program will update new values 
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        i = value.getText();
                        if( !i.isEmpty()&&i != null){//test for empty string and  null,only execute when 
                                                     //i is not empty, otherwise, do nothing
                        input = Double.parseDouble(i);
                        try{
                        currency = (JComboBox)event.getSource();
                        selectedcurrency = (String) currency.getSelectedItem();//get and store selected item              
                        if(input <=0.0)//detect negative and 0 input
                            throw new ArithmeticException();
                        else if (input > onebillion)
                            throw new OutofRangeException();//throw exception when too big
                        else{
                            //depend on which item is chosen in JComboBox
                           switch(selectedcurrency)
                        {
                            case "USD-US$":
                                USDoutput= input;
                                EURoutput= input*0.89518;
                                GBPoutput= input*0.65958;
                                CADoutput= input*1.32633;
                                JPYoutput= input*119.701;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                                
                            case "EUR-€":
                                USDoutput= input*1.11709;
                                EURoutput= input;
                                GBPoutput= input*0.73682;
                                CADoutput= input*1.48183;
                                JPYoutput= input*133.717;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            
                            case "GBP-£":
                                USDoutput= input*1.51611;
                                EURoutput= input*1.11709;
                                GBPoutput= input;
                                CADoutput= input*1.51611*1.32633;//convert to USD first
                                JPYoutput= input*1.51611*119.701;//convert to USD first
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            
                            case "CAD-C$":
                                USDoutput= input*0.75396;
                                EURoutput= input*0.67493;
                                GBPoutput= input*0.75396*0.65958;//convert to USD first
                                CADoutput= input;
                                JPYoutput= input*0.75396*119.701;//convert to USD first
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                                
                            case "JPY-¥":
                                USDoutput= input*0.00835;
                                EURoutput= input*0.00748;
                                GBPoutput= input*0.00835*0.65958;//convert to USD first
                                CADoutput= input*0.00835*1.32633;//convert to USD first
                                JPYoutput= input;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                        } 
                        }
                        
                    }
                    catch(NumberFormatException error1){//catch non-integer input
                            JOptionPane.showMessageDialog(null,"Please enter a number!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                            }
                    catch(ArithmeticException error2){//catch negative input
                            JOptionPane.showMessageDialog(null,"Please enter positive numbers only!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                    }
                    catch(OutofRangeException error3){//catch numbers that are too big
                            JOptionPane.showMessageDialog(null,"Please enter values below 1 billion!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                    }
                }
                }
               
                }
        );
      
        convert.addActionListener(//similar to when an item is selected in combobox
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) throws ArithmeticException{
                    try{
                        i = value.getText();//dont need to test for empty string since numberformat will catch
                        input = Double.parseDouble(i);
                        selectedcurrency = (String) currency.getSelectedItem();//get input from combobox
                        if(input <=0.0)
                            throw new ArithmeticException();//throw exceptiion when too small
                        else if(input > onebillion)
                            throw new OutofRangeException();//throw exception when too big
                        else        
                            switch(selectedcurrency)//depends on which item is chosen in combo box
                        {
                            case "USD-$":
                                USDoutput= input;
                                EURoutput= input*0.89518;
                                GBPoutput= input*0.65958;
                                CADoutput= input*1.32633;
                                JPYoutput= input*119.701;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                                
                            case "EUR-€":
                                USDoutput= input*1.11709;
                                EURoutput= input;
                                GBPoutput= input*0.73682;
                                CADoutput= input*1.48183;
                                JPYoutput= input*133.717;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            
                            case "GBP-£":
                                USDoutput= input*1.51611;
                                EURoutput= input*1.11709;
                                GBPoutput= input;
                                CADoutput= USDoutput*1.32633;//convert to USD first
                                JPYoutput= USDoutput*119.701;//convert to USD first
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            
                            case "CAD-C$":
                                USDoutput= input*0.75396;
                                EURoutput= input*0.67493;
                                GBPoutput= USDoutput*0.65958;//convert to USD first
                                CADoutput= input;
                                JPYoutput= USDoutput*119.701;//convert to USD first
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            case "JPY-¥":
                                USDoutput= input*0.00835;
                                EURoutput= input*0.00748;
                                GBPoutput= USDoutput*0.65958;//convert to USD first
                                CADoutput= USDoutput*1.32633;//convert to USD first
                                JPYoutput= input;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                        } 
                            }
                    catch(NumberFormatException error1){
                            JOptionPane.showMessageDialog(null,"Please enter a number!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                            }
                    catch(ArithmeticException error2){
                            JOptionPane.showMessageDialog(null,"Please enter positive numbers only!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                    }
                    catch(OutofRangeException error3){//catch numbers that are too big
                            JOptionPane.showMessageDialog(null,"Please enter values below 1 billion!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                    }
                }
               
            });
    value.addKeyListener(new KeyAdapter(){//listener for pressing enter key,similar to pressing convert button
        @Override
        public void keyPressed(KeyEvent event){
            if(event.getKeyCode()==KeyEvent.VK_ENTER){
                try{
                        i = value.getText();//dont need to test for empty string since numberformat will catch
                        input = Double.parseDouble(i);
                        selectedcurrency = (String) currency.getSelectedItem();//get input from combobox
                        if(input <=0.0)
                            throw new ArithmeticException();//throw exeption when too small
                        else if(input > onebillion)
                            throw new OutofRangeException();//throw exception when too big
                        else        
                            switch(selectedcurrency)//depends on which item is chosen in combo box
                        {
                            case "USD-$":
                                USDoutput= input;
                                EURoutput= input*0.89518;
                                GBPoutput= input*0.65958;
                                CADoutput= input*1.32633;
                                JPYoutput= input*119.701;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                                
                            case "EUR-€":
                                USDoutput= input*1.11709;
                                EURoutput= input;
                                GBPoutput= input*0.73682;
                                CADoutput= input*1.48183;
                                JPYoutput= input*133.717;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            
                            case "GBP-£":
                                USDoutput= input*1.51611;
                                EURoutput= input*1.11709;
                                GBPoutput= input;
                                CADoutput= USDoutput*1.32633;//convert to USD first
                                JPYoutput= USDoutput*119.701;//convert to USD first
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            
                            case "CAD-C$":
                                USDoutput= input*0.75396;
                                EURoutput= input*0.67493;
                                GBPoutput= USDoutput*0.65958;//convert to USD first
                                CADoutput= input;
                                JPYoutput= USDoutput*119.701;//convert to USD first
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                            case "JPY-¥":
                                USDoutput= input*0.00835;
                                EURoutput= input*0.00748;
                                GBPoutput= USDoutput*0.65958;//convert to USD first
                                CADoutput= USDoutput*1.32633;//convert to USD first
                                JPYoutput= input;
                                USDdollar.setText(String.format("US$ %.2f",USDoutput));
                                USDdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                EUReuro.setText(String.format("€ %.2f",EURoutput));
                                EUReuro.setHorizontalAlignment(SwingConstants.RIGHT);
                                GBPpound.setText(String.format("£ %.2f",GBPoutput));
                                GBPpound.setHorizontalAlignment(SwingConstants.RIGHT);
                                CADdollar.setText(String.format("C$ %.2f",CADoutput));
                                CADdollar.setHorizontalAlignment(SwingConstants.RIGHT);
                                JPYyen.setText(String.format("¥ %.2f",JPYoutput));
                                JPYyen.setHorizontalAlignment(SwingConstants.RIGHT);
                            break;
                        } 
                            }
  
                    catch(NumberFormatException error1){
                            JOptionPane.showMessageDialog(null,"Please enter a number!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                            }
                    catch(ArithmeticException error2){
                            JOptionPane.showMessageDialog(null,"Please enter positive numbers only!");
                            value.setText("");
                            value.grabFocus();
                            USDdollar.setText("");
                            GBPpound.setText("");
                            CADdollar.setText("");
                            JPYyen.setText("");
                            EUReuro.setText("");
                    }
                    catch(OutofRangeException error3){//catch numbers that are too big
                                JOptionPane.showMessageDialog(null,"Please enter values below 1 billion!");
                                value.setText("");
                                value.grabFocus();
                                USDdollar.setText("");
                                GBPpound.setText("");
                                CADdollar.setText("");
                                JPYyen.setText("");
                                EUReuro.setText("");
                        }
                }
            }
            
        });
                
   clear.addActionListener(//clear button event handling
       new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent event){
                    value.setText("");
                    value.grabFocus();
                    USDdollar.setText("");
                    GBPpound.setText("");
                    CADdollar.setText("");
                    JPYyen.setText("");
                    EUReuro.setText("");
                }
            });
        add(overallpanel);
    }
    

}
 class OutofRangeException extends Exception{//custom exception
     public OutofRangeException()
     {
         
     }
      public OutofRangeException(String message)
      {
          super("Please enter values below 1 billions!");
      }
 }