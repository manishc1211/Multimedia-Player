package gridbag;
import java.io.IOException;
import javax.swing.filechooser.*;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.*;
import java.io.BufferedInputStream;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import javax.swing.*;
import java.io.*;
import java.io.File;
//import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//import javax.swing.plaf.basic.BasicSliderUI;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.AudioDeviceBase;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


public class Audio implements  ActionListener
{
    int i=0,min=0,sec,sec1,tmin,tsec,next_song=0,pre_song=0,count=1;
     playThread m;
    fileThread c;
    slider slider;
    File temp;
    String filepath;
    int pauselocation=0, totalocation;
    FileInputStream fis;
    static Boolean playstatus=true;
    int duration,duration1,newduration;
    JFileChooser fc;
    File[] f1;
    JSlider s,s1;
    JPanel p;
    Player player;
    JRootPane rp;
    JButton b1,b2,b3,b4,b5;
    JFrame f;
    JLabel la,lb,li;
    Audio()
    {
        f=new JFrame("AUDIO");
        f.setLocation(550,50);
        b1 = new JButton(new ImageIcon(((new ImageIcon("play.png")).getImage()).getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH)));
        b1.setMargin(new Insets(0,0,0,0));
        b1.setBorderPainted(false);
        b1.setOpaque(false);
        b1.setToolTipText("play");
        b1.setBackground(new Color(0,100,150,100));
        
        b2 = new JButton(new ImageIcon(((new ImageIcon("pause.png")).getImage()).getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH)));
        b2.setMargin(new Insets(0,0,0,0));
        b2.setBorderPainted(false);
        b2.setOpaque(false);
        b2.setToolTipText("pause");
        b2.setVisible(false);
        b2.setBackground(new Color(0,100,150,100));
        
        b3 = new JButton(new ImageIcon(((new ImageIcon("fastforward.png")).getImage()).getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH)));
        b3.setMargin(new Insets(0,0,0,0));
        b3.setBorderPainted(false);
        b3.setOpaque(false);
        b3.setToolTipText("next song");
        b3.setBackground(new Color(0,100,150,100));
        
        b4 = new JButton(new ImageIcon(((new ImageIcon("backword.png")).getImage()).getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH)));
        b4.setMargin(new Insets(0,0,0,0));
        b4.setBorderPainted(false);
        b4.setOpaque(false);
        b4.setToolTipText("previous song");
        b4.setBackground(new Color(0,100,150,100));
        
        b5=new JButton(new ImageIcon(((new ImageIcon("plus.png")).getImage()).getScaledInstance(35,35,java.awt.Image.SCALE_SMOOTH)));     
        b5.setMargin(new Insets(0,0,0,0));
        b5.setBorderPainted(false);
        b5.setOpaque(false);
        b5.setToolTipText("Add File");
        b5.setBackground(new Color(0,100,150,100));

        b1.setBounds(220,110,35,35);
        b2.setBounds(220,110,35,35);
        b3.setBounds(285,110,35,35);
        b4.setBounds(150,110,35,35);
        b5.setBounds(20,110,35,35);
        
        s=new JSlider(0,300,0);
        s.setBounds(35,70,450,20);
        s.setBackground(Color.DARK_GRAY);
        la=new JLabel();
        lb=new JLabel();
        la.setForeground(Color.WHITE);
        lb.setForeground(Color.WHITE);
        la.setText("0:0");
        lb.setText("0:0");
        la.setBounds(5,70,30,20);
        lb.setBounds(500,70,30,20);
        s1=new JSlider(0,100,30);
        s1.setBounds(350,120,170,20);
        s1.setBackground(Color.DARK_GRAY);
        f.add(s1);
        s1.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent eee)
                {
                    int valu=s1.getValue();
                        try {
                            setSystemVolume(valu);
                        } catch (IOException ex) {
                            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                });
        f.add(la);
        f.add(lb);
        f.add(s);
        f.add(b5);
        f.add(b4);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        f. setSize(550,200);
        f.setResizable(false);
        f.setBackground(new Color(0,0,0,170));
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f. setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b5)
        {
            c=new fileThread();
             c.start();
        }

        if(e.getSource()==b1)
        {
            if(playstatus==false)
            {
                playstatus=true;
                b1.setVisible(false);
                b2.setVisible(true);
                m.resume();
    
            }
        }
        
       if(e.getSource()==b2)
        { 
            m.suspend();
            b1.setVisible(true);
            b2.setVisible(false);
            playstatus=false;
        }
       
       if(e.getSource()==b3)
       {
           System.out.println("hello ");
           if(player!=null)
           {
              player.close();
              i++;
              filechoose filechoose=new filechoose();
              filechoose.start();
           }
          
       }
       if(e.getSource()==b4)
       {
          System.out.println("hello ");
           if(player!=null)
           {
              player.close();
              i--;
              filechoose filechoose=new filechoose();
              filechoose.start();
           }
       }
    }
    
   
                class slider extends Thread
                {
                     slider()
                        {
                           
                        }
                        @Override
                        public void run()
                         {  
                                 s.setMaximum(duration1);
                                 s.setValue(0);
                                 while(s.getValue()!=duration1)
                                 {
                                     System.out.println("");
                                     s.setValue(sec1);
                                 }
                           
                         }
                 }

class filechoose extends Thread
{
     filechoose()
    {}
    @Override
    public void run()
     {
       f1=fc.getSelectedFiles();
        for(;i<f1.length;i++)
           { 
                filepath=f1[i].getAbsolutePath();
                System.out.println(filepath);
                li=new JLabel();
                li.setLayout(new FlowLayout(FlowLayout.CENTER));
                li.setForeground(Color.CYAN);
                li.setFont(new Font(Font.SERIF,Font.TRUETYPE_FONT+Font.ITALIC,20));
                li.setBounds(16,0,500,50);
                f.add(li);
                li.setText(f1[i].getName());
                b1.setVisible(false);
                b2.setVisible(true);
                try {
                        m=new playThread(f1[i]);
                        } catch (JavaLayerException | IOException | InterruptedException ex) {} catch (CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
                          Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                          }
                    m.start();
                    audioduration();
                  /* try {
                        fileThread.sleep(5000);
                        } catch (InterruptedException ex) {
                        Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                             
                               
                  
           }
     }
   
}
    
    
             class fileThread extends Thread
             {
              
             @Override
             public void run()
             {
                 if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
                       { 
                           filechoose filechoose=new filechoose();
                           filechoose.start();
                           
                         }
                
             }
             fileThread()
             {
                     fc =new JFileChooser();
                     FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3 & Wav","Mp3","Wav");
                     fc.setFileFilter(filter);
                     fc.setCurrentDirectory(new File("C:\\Users\\manis\\Desktop"));
                     fc.setMultiSelectionEnabled(true);
                 
             }
             }
    
   
class playThread extends Thread
            {
               
                playThread(File file) throws JavaLayerException, IOException, InterruptedException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException
                {
                    try {
                        temp=file;
                        fis= new FileInputStream(temp);
                        BufferedInputStream buffer=new BufferedInputStream(fis);
                        player=new Player(buffer);
                        totalocation=fis.available();
                        AudioFile audioFile = AudioFileIO.read(temp);
                        duration1= audioFile.getAudioHeader().getTrackLength();
                        tsec=(int)duration1%60;
                        tmin=(int)duration1/60;
                        la.setText("");
                        lb.setText("");
                        String b=String.valueOf(tmin);
                        String a=String.valueOf(tsec);
                        lb.setText(b+":"+a);
                        
                        
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                @Override
                    public void run()
                    {
                    try {
                        slider=new slider();
                        slider.start();
                        player.play();
                        li.setText(" ");
                         } catch (JavaLayerException ex) {}
                    }
                    
            }

            public void audioduration() 
            {   
                while(player.isComplete()==false)
                         {
                             duration=player.getPosition(); 
                             sec1=(int)duration/1000;
                             sec=sec1%60;
                             min=sec1/60;
                             String minutes=String.valueOf(min);
                             String secs=String.valueOf(sec);
                             la.setText(minutes+":"+secs);
                             
                             
                        }
            }
            public void setSystemVolume(int volume) throws IOException
            {
                
             if(volume < 0 || volume > 100)
                 {
                     throw new RuntimeException("Error: " + volume + " is not a valid number. Choose a number between 0 and 100");
                 }

                   else
                      {
                          double endVolume = 655.35 * volume;
                          Runtime rt = Runtime.getRuntime();
                          Process pr;
                          pr = rt.exec("nircmd.exe" + " setsysvolume " + endVolume);
                          pr = rt.exec("nircmd.exe" + " mutesysvolume 0");
                      }
            }
            
    
     public void resume(File filelocation)
    {
         try{
                 b1.setVisible(false);
                 b2.setVisible(true);
                 fis= new FileInputStream(filelocation);
                 BufferedInputStream buffer=new BufferedInputStream(fis);
                 player=new Player(buffer);
                 newduration=duration+30;
                 fis.skip(newduration);
                 new Thread()
                 {
                   @Override
                   public void run()
                   {
                       try {
                           player.play();
                          } catch (JavaLayerException ex) {}
                   }
                 }.start();
                 
                
                } catch(Exception ae){JOptionPane.showMessageDialog(rp,"FILE NOT FOUND");}
    }
  
     
     public static void main(String args[])
    {
        
        Audio sf=new Audio();
    }
}
  