/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JApplet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/**
 *
 Program:Analog Timer
 Written by:Viet Tran Quoc Hoang
 Description:Count down display on an analog clock and play sound once timer runs out 
 Challenges:Debug, converting from angle to time
 Time Spent:6 hours
 Given Input:                   Expected Output:
 --------------------          -------------------------
Interactive input               Interactive output


                   Revision History
 Date:                   By:               Action:
 ---------------------------------------------------
 11/19/2015              VT                Wrote,debug
 */
public class AnalogTimer extends JApplet {
   private Timer clock;
   private Point centerPoint,secondPoint;
   private JPanel bottomPanel,clockPanel,soundPanel,gridPanel;
   private JTextField timeInput;
   private JButton timerSet,timerStop;
   private JRadioButton bellButton,alarmButton,dogButton;
   private JLabel bellLabel,alarmLabel,dogLabel;
   private JButton playButton;
   private AudioClip sound;
   private ButtonGroup group;
   private ImageIcon bellIcon,alarmIcon,dogIcon;
   private int radius=140;
   private int xcoord,ycoord,x1,y1;
   private double angle,length,roundedAngle,timeLeft;
   private String userInput;
    public void init() {
        setSize(650,500);
        centerPoint = new Point((getWidth() / 2), (getHeight() / 2-50));
        secondPoint = centerPoint;
        sound =  getAudioClip(getCodeBase(),"alarm.au");
        
        addMouseListener(
            new MouseAdapter()
                {@Override
                    public void mousePressed(MouseEvent event){
                        xcoord = event.getX();
                        ycoord = event.getY();
                        //System.out.printf("x = %d,y = %d", xcoord,ycoord);
                        length = Math.sqrt((Math.pow(ycoord-centerPoint.y,2)+Math.pow(xcoord-centerPoint.x,2)));
                        if(length<=radius){//do not receive input if click outside the clock
                        angle = Math.atan2(ycoord-centerPoint.y,xcoord-centerPoint.x);
                        //angle= Math.atan2(-1,0);
                        secondPoint = new Point((int)(Math.cos(angle) * (radius)) + centerPoint.x,(int)(Math.sin(angle) * (radius)) + centerPoint.y);    
                        //System.out.printf("angle = %f\n",angle);
                        timeLeft= (angle/(2*Math.PI)*60)+15;//ewwwww
                         if(timeLeft<0)
                         timeLeft+=60;//ewww
                        repaint(); 
                        clock.start();//once timer start showStatus("Time left: "+(int)timeLeft); 
                        
                        }
                    }
               });
        
        clockPanel = new JPanel(){
            @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.drawOval(centerPoint.x - radius, centerPoint.y - radius, radius * 2,radius * 2);
                    g2d.drawLine(centerPoint.x, centerPoint.y, secondPoint.x, secondPoint.y);
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,13));
                    g2d.drawString("0", centerPoint.x-3, centerPoint.y - radius+19);
                    g2d.drawString("15", centerPoint.x + radius-17 , centerPoint.y+4);
                    g2d.drawString("30", centerPoint.x-7, centerPoint.y + radius-3);
                    g2d.drawString("45", centerPoint.x - radius, centerPoint.y+5);  
                    }
            };
        clockPanel.setDoubleBuffered(true); 
        clock = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //roundedAngle = Math.PI*2/60*Math.round(60*(angle)/2*Math.PI);
                //System.out.printf("roundedAngle = %f\nangle = %f\n",roundedAngle,angle);
                //timeLeft = (roundedAngle+(Math.PI/2))/(120*Math.PI)*60;
                //System.out.printf("timeLeft = %f\n",timeLeft);    
                showStatus("Time left: "+(int)timeLeft);
                System.out.printf("time = %f\n",timeLeft);
                angle= ((--timeLeft-15) / 60.)* 2 * Math.PI;//ewww
                secondPoint = new Point((int)(Math.cos(angle) * (radius)) + centerPoint.x,(int)(Math.sin(angle) * (radius)) + centerPoint.y);    
                if(timeLeft<0){
                     sound.play();
                     System.out.printf("sound =%s",sound);
                     clock.stop();
                     secondPoint= centerPoint;
                 }
                repaint();               
            }
        });
        dogIcon = new ImageIcon(getClass().getResource("dog.png"));
        alarmIcon = new ImageIcon(getClass().getResource("alarm.png")); 
        bellIcon = new ImageIcon(getClass().getResource("bell.png"));
        dogLabel = new JLabel(dogIcon);
        alarmLabel = new JLabel(alarmIcon);
        bellLabel = new JLabel(bellIcon);
        alarmButton = new JRadioButton("Alarm",true);
        alarmButton.addItemListener(
            new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    if(e.getStateChange()==ItemEvent.SELECTED){
                    sound =  getAudioClip(getCodeBase(),"alarm.au");
                    showStatus("Current Audio: Alarm sound");
                    }
                }
            }
        );
        bellButton = new JRadioButton("Bell");
        bellButton.addItemListener(
            new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    if(e.getStateChange()==ItemEvent.SELECTED){
                    sound =  getAudioClip(getCodeBase(),"bell.au");
                    showStatus("Current Audio: Bell rings");
                    }
                }
            }
        );
        dogButton = new JRadioButton("Dog");
        dogButton.addItemListener(
            new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e){
                    if(e.getStateChange()==ItemEvent.SELECTED){
                    sound =  getAudioClip(getCodeBase(),"dog.au");
                    showStatus("Current Audio: Dog barks");
                    }
                }
            }
        );
        playButton = new JButton("Play Audio");
        playButton.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                sound.play();   
                showStatus("Alarm played");
                    
                }
            }
        );
        
        group = new ButtonGroup();
        group.add(alarmButton);
        group.add(bellButton);
        group.add(dogButton);
        soundPanel = new JPanel();
        soundPanel.setLayout(new FlowLayout());
        soundPanel.add(alarmButton);
        soundPanel.add(alarmLabel);
        soundPanel.add(bellButton);
        soundPanel.add(bellLabel);
        soundPanel.add(dogButton);
        soundPanel.add(dogLabel);
       
        timeInput = new JTextField(9);
        timeInput.addKeyListener(new KeyAdapter(){//listener for pressing enter key
        @Override
        public void keyPressed(KeyEvent event){
            if(event.getKeyCode()==KeyEvent.VK_ENTER){
                userInput = timeInput.getText();
                timeLeft=Double.parseDouble(userInput);
                if(timeLeft>60){
                showStatus("Maximum time allowed is 60 seconds only!");
                }
                else{
                clock.start();
                showStatus("Time left: "+(int)timeLeft);
                }
            }
        }
        });
        timerSet = new JButton("Set Timer(in seconds)");
        timerSet.addActionListener(
           new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                userInput = timeInput.getText();
                timeLeft=Double.parseDouble(userInput);
                if(timeLeft>60){
                showStatus("Maximum time allowed is 60 seconds only!");
                }
                else{
                clock.start();
                showStatus("Time left: "+(int)timeLeft);
                }
                }
            }
        );
        
        timerStop = new JButton("Cancel Timer");
        timerStop.addActionListener(
           new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                clock.stop();
                showStatus("Timer cancelled");
                secondPoint = centerPoint;
                repaint();
                }
            }
        );
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(timeInput);
        bottomPanel.add(timerSet);
        bottomPanel.add(playButton);
        bottomPanel.add(timerStop);
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2,1));
        gridPanel.add(soundPanel);
        gridPanel.add(bottomPanel);
        add(gridPanel,BorderLayout.SOUTH);
        add(clockPanel,BorderLayout.CENTER);
        
    }
        
    // TODO overwrite start(), stop() and destroy() methods
    public void stop() {
        clock.stop();               
    }
}
  