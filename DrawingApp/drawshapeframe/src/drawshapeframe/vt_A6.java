package drawshapeframe;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 Program:VTA6-Currency Converter
 Written by:Viet Tran Quoc Hoang
 Description:Converts from selected currency to 4 other currencies(including current currency) using given formula
 Challenges:Setting up layout,testing for empty string
 Time Spent:19h
 Given Input:                   Expected Output:
 --------------------          -------------------------
Interactive input               Interactive output
                   Revision History
 Date:                   By:               Action:
 ---------------------------------------------------
 11/19/2015              VT       Created and debugged
 */
public class vt_A6 extends JFrame {

  public static void main(String[] args) {
      vt_A6 drawshapeframe = new vt_A6();//intialize new frame object
  }
    private final JMenu file,window;
    private final JMenuBar bar;
    private final JMenuItem newPictureMenuItem,exitMenuItem;
    private JMenuItem newPictureWindow;
    private int pictureNumber=1;
    private JDesktopPane desktop;
    private JInternalFrame internalframe;
  public vt_A6() {
    super("Draw Shapes");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    desktop = new JDesktopPane();
    bar = new JMenuBar();
    newPictureMenuItem = new JMenuItem("New");
    //action listener for creating new picture window
    newPictureMenuItem.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event){
            internalframe = new JInternalFrame("Picture "+ pictureNumber++,true,true,true,true);  //set up new internal frame                
            internalframe.setLayout(new BorderLayout());
            internalframe.add(new PaintSurface(),BorderLayout.CENTER);//add drawing panel to center of internal frame
            Random randomGenerator = new Random();
            int randomcoord = randomGenerator.nextInt(300); //generate location so that window doesnt go out of the desktop        
            internalframe.setSize(500,400);
            internalframe.setLocation(randomcoord,randomcoord);      
            desktop.add(internalframe);
            internalframe.setVisible(true);         
            }
        });
    
    exitMenuItem = new JMenuItem("Exit");
    //action listener for quitting the program
    exitMenuItem.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event){
            System.exit(0);
            }
        });
    file = new JMenu("File");
    window = new JMenu("Window");
    //actionlistener for generating menu item and also access child internal frame
    window.addMenuListener(new MenuListener(){
        @Override
          public void menuSelected(MenuEvent me) {
        window.removeAll();
        for( JInternalFrame ji : desktop.getAllFrames()){//for every existing internal frame,creating one corresponding jmenuitem
            newPictureWindow = new JMenuItem(ji.getTitle());          
            newPictureWindow.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event){
                       JMenuItem clickedItem=(JMenuItem)event.getSource();
                       String menuItemName = clickedItem.getText();//compare the name of the menuitem with title of the interal frame                     
                        for(JInternalFrame ji : desktop.getAllFrames()){
                            if(ji.getTitle().equals(menuItemName)){
                                try {
                                    ji.setSelected(true);//set focus onto the frame once click on the jmenuitem for that frame
                                } catch (PropertyVetoException ex) {
                                    Logger.getLogger(vt_A6.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }                                                 
                        }                                                                   
                    }                   
                });
            window.add(newPictureWindow);//add item to menu
        }
        
        window.repaint();//refresh menu
          }
         @Override
            public void menuDeselected(MenuEvent me) {
            }

            @Override
            public void menuCanceled(MenuEvent me) {
            }
    });
    
    file.add(newPictureMenuItem);
    file.add(exitMenuItem);
    bar.add(file);
    bar.add(window);//adding menuitems to menu and menus to bar
    this.setJMenuBar(bar);//setup jmenubar
    this.add(desktop,BorderLayout.CENTER);//add desktop to center of original window
    this.setVisible(true);//must-have
    
  }

  private class PaintSurface extends JComponent {
    private int arcwidth=15,archeight=15,startingangle=0,sweepingangle=180,red=0,green=0,blue=0;//predefined variables
    private ArrayList<Shape> unfilledshapes = new ArrayList<>(); //store unfilled shapes 
    private ArrayList<Shape> filledshapes = new ArrayList<>(); //store filled shapes
    private ArrayList<Color> colorarray = new ArrayList<>();//store colors input by user
    private Point startDrag, endDrag;
    private final  JPanel drawpanel,toppanel,bottompanel;
    private final  JCheckBox filled;
    private JComboBox shapeselect;
    private final JButton colormix,clear;
    private Color color;
    private String selectedshape;
    private boolean fillcheck=false;
    private JTextField blueTextField,greenTextField,redTextField;
    private final   JLabel redlabel,greenlabel,bluelabel;
    private final  JSlider redslider,greenslider,blueslider;
    public PaintSurface() {
        colormix = new JButton("Mix Color");//after user finish choosing color, mix color
        colormix.addActionListener(
            new ActionListener(){
                 @Override
                     public void actionPerformed(ActionEvent event){
                        try{
                        
                        green =Integer.parseInt(greenTextField.getText());
                        red = Integer.parseInt(redTextField.getText());
                        blue =Integer.parseInt(blueTextField.getText());
                        if(green <0 || green >255||blue<0||blue>255||red<0||red>255)//out of range exception
                            {
                        throw new ArithmeticException();
                            }
                        else{
                        blueslider.setValue(blue);//setting values for sliders
                        greenslider.setValue(green);
                        redslider.setValue(red);
                        fillcheck=true;
                        filled.setSelected(true);//changing fill check box
                        }
                        
                        }
                        catch(NumberFormatException error1){
                             JOptionPane.showMessageDialog(null,"Please enter a number for all 3 colors!");//invalid exception
                        }
                        catch(ArithmeticException error2){
                            JOptionPane.showMessageDialog(null,"Please enter values between 0-255 only!");//range exception
                        }
                     }
                 });
        clear = new JButton("Clear");//clear all shapes and reset color settings
         clear.addActionListener(
            new ActionListener(){
                 @Override
                    public void actionPerformed(ActionEvent event){
                       filledshapes.clear();
                       unfilledshapes.clear();
                       colorarray.clear();
                       blueslider.setValue(0);
                       greenslider.setValue(0);
                       redslider.setValue(0);
                       fillcheck=false;
                       filled.setSelected(false);
                       repaint(); 
                     }     
                      
                 });       
        String dropboxshapes[] ={"Rectangle","Square","Circle","Line","Rounded Rectangle","Arc"};
        selectedshape = "Rectangle";
        shapeselect = new JComboBox(dropboxshapes);//JComboBox and actionlistener
        shapeselect.addActionListener(
              new ActionListener()
              {
                  @Override
                  public void actionPerformed(ActionEvent event)
                  {
                      shapeselect =  (JComboBox)event.getSource();
                      selectedshape = (String)shapeselect.getSelectedItem();                       
                  }   
              });
                    
        filled = new JCheckBox("Fill");//checkbox and actionlister for checkbox
        filled.addActionListener(
                new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent event)
                        {
                            JCheckBox checkBox =(JCheckBox)event.getSource();
                            if(checkBox.isSelected()){
                                fillcheck=true;
                                checkBox.setSelected(true);
                                
                            } 
                            else
                                fillcheck=false;
                        }
                    });
        
        toppanel= new JPanel();
        toppanel.setLayout(new FlowLayout());
        toppanel.add(shapeselect);
        toppanel.add(filled);
        toppanel.add(colormix);
        toppanel.add(clear);//toppanel setup
        
        blueTextField = new JTextField("");//textfield and actionlistener when user press enter
        blueTextField.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
         try{
              blue =Integer.parseInt(blueTextField.getText());
             if(blue <0 || blue >255)//exception handing
             {
                 throw new ArithmeticException();
                 
             }
             else
             blueslider.setValue(blue);//set value to slider
             fillcheck=true;//update fill check box
             filled.setSelected(true);
         }
         catch(NumberFormatException error1){
             JOptionPane.showMessageDialog(null,"Please enter a number!");
         }
         catch(ArithmeticException error2){
             JOptionPane.showMessageDialog(null,"Please enter values between 0-255 only!");
         }
      }
        });
        redTextField = new JTextField("");//textfield and actionlistener when user press enter
        redTextField.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
         try{
              red =Integer.parseInt(redTextField.getText());//exception handling
             if(red <0 || red >255)
             {
                 throw new ArithmeticException();
             }
             else
             redslider.setValue(red);//set value to slider
             fillcheck=true;//update fill checkbox
             filled.setSelected(true);
         }
         catch(NumberFormatException error1){
             JOptionPane.showMessageDialog(null,"Please enter a number!");
         }
         catch(ArithmeticException error2){
             JOptionPane.showMessageDialog(null,"Please enter values between 0-255 only!");          
         }
      }
        });
        greenTextField = new JTextField("");//textfield and actionlistener when user press enter
        greenTextField.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
         try{
             green =Integer.parseInt(greenTextField.getText());
             if(green <0 || green >255)//exception handling
             {
                 throw new ArithmeticException();
             }
             else
             greenslider.setValue(green);//set value to slider
             fillcheck=true;//update fill checkbox
             filled.setSelected(true);
         }
         catch(NumberFormatException error1){
             JOptionPane.showMessageDialog(null,"Please enter a number!");
         }
         catch(ArithmeticException error2){
             JOptionPane.showMessageDialog(null,"Please enter values between 0-255 only!");
         }
      }
        });
        redlabel = new JLabel("Red:");
        greenlabel = new JLabel("Green:");
        bluelabel = new JLabel("Blue:");
        redslider = new JSlider(JSlider.HORIZONTAL,0,255,0);//Jslider and actionlistnr
        redslider.addChangeListener(
            new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e){
                    JSlider source = (JSlider)e.getSource();
                    red = (int)source.getValue();
                    redTextField.setText(Integer.toString(red));//change text to value of jslider
                    fillcheck=true;//update fill checkbox
                    filled.setSelected(true);
                }
            });
        blueslider = new JSlider(JSlider.HORIZONTAL,0,255,0);
        blueslider.addChangeListener(
            new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e){
                    JSlider source = (JSlider)e.getSource();
                    blue = (int)source.getValue();
                    blueTextField.setText(Integer.toString(blue));//change text to value of jslider
                    fillcheck=true;//update fill checkbox
                    filled.setSelected(true);
                }
            });
        greenslider = new JSlider(JSlider.HORIZONTAL,0,255,0);
        greenslider.addChangeListener(
            new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e){
                    JSlider source = (JSlider)e.getSource();
                    green = (int)source.getValue();
                    greenTextField.setText(Integer.toString(green));//change text to value of jslider
                    fillcheck=true;//update fill checkbox
                    filled.setSelected(true);
                }
            });   
               
        bottompanel= new JPanel();//bottom panel setup
        bottompanel.setLayout(new GridLayout(3,3,5,5));
        bottompanel.add(redlabel);
        bottompanel.add(redslider);
        bottompanel.add(redTextField);
        bottompanel.add(greenlabel);
        bottompanel.add(greenslider);
        bottompanel.add(greenTextField);
        bottompanel.add(bluelabel);
        bottompanel.add(blueslider);
        bottompanel.add(blueTextField);
        
        drawpanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                paintBackground(g2);
                int colorIndex = 0;                
                g2.setStroke(new BasicStroke(2));
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
                //draw array of unfilled shapes    
                for (Shape s : unfilledshapes) {
                          g2.setPaint(Color.BLACK);
                          g2.draw(s);                                                                          
                }
                //draw array of filled shapes
                for (Shape s2: filledshapes){
                          g2.setPaint(Color.BLACK);
                          g2.draw(s2);                        
                          g2.setPaint(colorarray.get(colorIndex++));//use the color array to fill all shapes
                          g2.fill(s2); 
                }

                Shape r;   
                if (startDrag != null && endDrag != null) {//draw one shape at a time
                  g2.setPaint(Color.LIGHT_GRAY);
                  switch(selectedshape){
                      case"Rectangle":
                          r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          g2.draw(r);
                         
                      break;
                     
                      case"Square":
                          r = makeSquare2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          g2.draw(r);
                         
                      break;
                      case"Circle":
                          r = makeCircle2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          g2.draw(r);
                         
                      break;
                      case"Line":
                          r = makeLine2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          g2.draw(r);
                         
                      break;
                      case"Rounded Rectangle":
                          r = makeRoundedRectangle2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y,arcwidth,archeight);
                          g2.draw(r);
                         
                      break; 
                      case"Arc":
                          r=makeArc2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y,startingangle,sweepingangle);                                                 
                          g2.draw(r);
                          
                      break;    
                           
                  }
                  
                  
                }
            }
        };     
        
        this.setLayout(new BorderLayout());
        this.add(toppanel,BorderLayout.NORTH);
        this.add(drawpanel,BorderLayout.CENTER);
        this.add(bottompanel,BorderLayout.SOUTH);//overall setup
        
        this.addMouseListener(new MouseAdapter() {//mouse listener action handling
        @Override
        public void mousePressed(MouseEvent e) {
          startDrag = new Point(e.getX(), e.getY());
          endDrag = startDrag;
          repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {//mouse listener action handling
          Shape r;
          switch(selectedshape){//once released start drawing by adding shapes to array with chosen shape
                      case"Rectangle":
                          r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);                          
                          if(fillcheck)//if filled shapes,add to fill shape array and also add color to color array
                          {
                              filledshapes.add(r);
                              color = new Color(red,green,blue);
                              colorarray.add(color);  
                          }
                          else{
                              unfilledshapes.add(r);
                          }
                                                
                      break;
                      case"Square"://if filled shapes,add to fill shape array and also add color to color array
                          r = makeSquare2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          
                          if(fillcheck)
                          {
                              filledshapes.add(r);
                              color = new Color(red,green,blue);
                              colorarray.add(color);  
                          }
                          else{
                              unfilledshapes.add(r);
                          }
                          
                      break;
                      case"Circle"://if filled shapes,add to fill shape array and also add color to color array
                          r = makeCircle2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          
                          if(fillcheck)
                          {
                              filledshapes.add(r);
                              color = new Color(red,green,blue);
                              colorarray.add(color);  
                          }
                          else{
                              unfilledshapes.add(r);
                          }
                      break;
                      case"Line"://if filled shapes,add to fill shape array and also add color to color array
                          r = makeLine2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                          
                          if(fillcheck)
                          {
                              filledshapes.add(r);
                              color = new Color(red,green,blue);
                              colorarray.add(color);  
                          }
                          else{
                              unfilledshapes.add(r);
                          }
                      break;
                      case"Rounded Rectangle"://if filled shapes,add to fill shape array and also add color to color array
                          r = makeRoundedRectangle2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y,arcwidth,archeight);
                          
                          if(fillcheck)
                          {
                              filledshapes.add(r);
                              color = new Color(red,green,blue);
                              colorarray.add(color);  
                          }
                          else{
                              unfilledshapes.add(r);
                          }
                      break; 
                      case"Arc"://if filled shapes,add to fill shape array and also add color to color array
                          r=makeArc2D(startDrag.x, startDrag.y, endDrag.x, endDrag.y,startingangle,sweepingangle);                                                  
                          if(fillcheck)
                          {
                              filledshapes.add(r);
                              color = new Color(red,green,blue);
                              colorarray.add(color);  
                          }
                          else{
                              unfilledshapes.add(r);
                          }
                      break;  
                          
                  }
          
          startDrag = null;//set mouse position to null to stop drawing
          endDrag = null;
          repaint();
        }
      });

      this.addMouseMotionListener(new MouseMotionAdapter() {//action handling for mouselistener
        @Override
        public void mouseDragged(MouseEvent e) {
          endDrag = new Point(e.getX(), e.getY());//update new mouse location
          repaint();
        }
      });
    }
    private void paintBackground(Graphics2D g2){//paint the background
      g2.setPaint(Color.LIGHT_GRAY);
      for (int i = 0; i < getSize().width; i += 10) {
        Shape line = new Line2D.Float(i, 0, i, getSize().height);
        g2.draw(line);
      }

      for (int i = 0; i < getSize().height; i += 10) {
        Shape line = new Line2D.Float(0, i, getSize().width, i);
        g2.draw(line);
      }  
    }
    
    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {//methods for generating 2d shapes
      return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
    
    private RoundRectangle2D.Float makeRoundedRectangle2D(int x, int y, int x1, int y1,int arcwidth, int archeight){
       return new RoundRectangle2D.Float(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(y - y1),arcwidth,archeight);
    }
    
    private Ellipse2D.Float makeCircle2D(int x, int y, int x1, int y1){
       return new Ellipse2D.Float(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(x - y1));
    }
    
    private Line2D.Float makeLine2D(int x,int y,int x1,int y1){
       return new Line2D.Float(x,y,x1,y1);
    }
    
    private Rectangle2D.Float makeSquare2D(int x, int y, int x1, int y1) {
      return new Rectangle2D.Float(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(x - x1));
    }
    
    private Arc2D.Float makeArc2D(int x,int y, int x1,int y1,int startingangle, int sweepingangle){
       return new Arc2D.Float(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(x - x1),startingangle,sweepingangle,Arc2D.OPEN);
    }
    
  }
}