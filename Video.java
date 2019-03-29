package gridbag;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
class Counter extends Thread
{
    static long i;  
    MediaPlayer mp;
    static int js1val=0; 
    long time,ttime,h,m,s;
    String time1,h1,m1,s1;
    JLabel duration = new JLabel(), length = new JLabel();
    JSlider js1 = new JSlider(0,100,0);
    Video v = new Video();  
    int temp=0;
    Counter(){}
    Counter(MediaPlayer mplay, JLabel l,JLabel l1, JSlider ts) throws IOException
        {
            mp = mplay;
            duration = l;
            length = l1;
            js1 = ts;
        }
    
    @Override
    public void run() 
    {
        
        while(true)
        {
            try
            {
                Thread.sleep(501);
            } 
            catch (InterruptedException ex){}
            tm2();
            while(temp==0)
            {
                ttime = mp.getLength();
                
                ttime = ttime/1000;           
                h = ttime/3600;
                ttime = ttime%3600;
                m = ttime/60;
                s = ttime%60;
                h1 = String.format("%02d",h);
                m1 = String.format("%02d",m);
                s1 = String.format("%02d",s);
                time1 = h1+":"+m1+":"+s1+"\n";      
                length.setText(time1);
                temp++;
            }
            }
        }        
        public void tm2()
        {
            time = mp.getTime();
            time = time/1000;
            h = time/3600;
            time = time%3600;
            m = time/60;
            s = time%60;
            try 
                {
                    Thread.sleep(497);
                } catch (InterruptedException ex) {}
            h1 = String.format("%02d",h);
            m1 = String.format("%02d",m);
            s1 = String.format("%02d",s);
            time1 = " "+h1+":"+m1+":"+s1+"\n";      
            duration.setText(time1);
           
            js1.setValue((int)(100*mp.getPosition()));
            try 
        {
            Thread.sleep(50);
            js1val=js1.getValue();
        } catch (InterruptedException ex){}
            System.out.println(js1val);           
        }

}

public class Video implements ActionListener, ChangeListener
{
    
    JFrame f;
    Insets pi1 = new Insets(0,0,0,0);
    Insets pi2 = new Insets(0,0,40,0);
    Insets pib1 = new Insets(0,7,7,0);
    Insets pib2 = new Insets(0,55,7,0);
    Insets pib3 = new Insets(0,95,7,0);
    Insets pib4 = new Insets(0,130,7,0);
    Insets pib5 = new Insets(0,165,7,0);
    Insets pis1 = new Insets(0,57,0,57);
    Insets pis2 = new Insets(0,0,10,10);
    Insets piv = new Insets(0,0,10,10);
    Insets vpi = new Insets(0,0,60,0);
    JButton pb1,pb2,pb3,pb4,pb5,vb;
    JSlider ps1,ps2;
    public JLabel duration,length;
    JPanel p1,p2,vpanel;
    MediaPlayer mplay;
    Icon vi1 = new ImageIcon(((new ImageIcon("pb5.1.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
    Icon vi2 = new ImageIcon(((new ImageIcon("pb5.2.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
    Icon pi2_1 = new ImageIcon(((new ImageIcon("pb2.1.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
    Icon pi2_2 = new ImageIcon(((new ImageIcon("pb2.2.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
    static boolean vbstatus = true;
    static boolean playstopstatus = true;
    static boolean playpausestatus = false;
    File file;
    GridBagConstraints gbcf;
    JMenuItem ma1,ma2,ma3,ma4,ma5,mp1,mp2,mp3,mp4,mp5;
    int center = GridBagConstraints.CENTER;
    int north = GridBagConstraints.NORTH;
    int south = GridBagConstraints.SOUTH;
    int east = GridBagConstraints.EAST;
    int west = GridBagConstraints.WEST;
    int nw = GridBagConstraints.NORTHWEST;
    int sw = GridBagConstraints.SOUTHWEST;
    int se = GridBagConstraints.SOUTHEAST;
    int none = GridBagConstraints.NONE;
    int rel = GridBagConstraints.RELATIVE;
    int vertical = GridBagConstraints.VERTICAL;
    int horizontal = GridBagConstraints.HORIZONTAL;
    int both = GridBagConstraints.BOTH;
    Counter c;
    public void vid()
    {
        GridBagLayout lgbag = new GridBagLayout();
        f =  new JFrame("Video");
        f.setSize(501,510);
        f.setLocation(520,0);
        f.setMinimumSize(new Dimension(330,330));
        f.setLayout(lgbag);
        
        //Menubar
        JMenuBar mb = new JMenuBar();
        JMenu mf = new JMenu("Playback");
        
        JMenu ma = new JMenu("Aspect Ratio");
        ma1 = new JMenuItem("1:1");
        ma2 = new JMenuItem("3:2");
        ma3 = new JMenuItem("4:3");
        ma4 = new JMenuItem("5:4");
        ma5 = new JMenuItem("16:9");
        ma.add(ma1);
        ma.add(ma2);
        ma.add(ma3);
        ma.add(ma4);
        ma.add(ma5);
        
        JMenu mp = new JMenu("Speed");
        mp1 = new JMenuItem("x0.5");
        mp2 = new JMenuItem("x1.0");
        mp3 = new JMenuItem("x1.5");
        mp4 = new JMenuItem("x2.0");
        mp5 = new JMenuItem("x4.0");
        mp.add(mp1);
        mp.add(mp2);
        mp.add(mp3);
        mp.add(mp4);
        mp.add(mp5);
        
        
        ma1.addActionListener(this);
        ma2.addActionListener(this);
        ma3.addActionListener(this);
        ma4.addActionListener(this);
        ma5.addActionListener(this);
        mp1.addActionListener(this);
        mp2.addActionListener(this);
        mp3.addActionListener(this);
        mp4.addActionListener(this);
        mp5.addActionListener(this);
        
        
        mf.add(ma);
        mf.add(mp);
        
        mb.add(mf);
        
        f.setJMenuBar(mb);
        //
        
        //Panel that will contain video player component
        gbcf = new GridBagConstraints(0,0,0,0,1,1,north,both,vpi,0,0);
        vpanel = new JPanel();
        vpanel.setLayout(new BorderLayout());
        f.add(vpanel,gbcf);
        //
        
        p1 = new JPanel();
        JPanel tsp1 = new JPanel();
        p1.setLayout(lgbag);
        p1.setBackground(Color.DARK_GRAY);
        tsp1.setLayout(null);
        tsp1.setBackground(Color.LIGHT_GRAY);
        gbcf = new GridBagConstraints(0,0,0,0,1,1,south,horizontal,pi1,0,6);
        f.add(p1,gbcf);
        
        Icon i = new ImageIcon(((new ImageIcon("pb1.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
        pb1 = new JButton(i);
        pb1.setMargin(pi1);
        pb1.setBorder(BorderFactory.createEmptyBorder());
        pb2 = new JButton(pi2_1);
        pb2.setMargin(pi1);
        pb2.setBorder(BorderFactory.createEmptyBorder());
        pb3 = new JButton(new ImageIcon(((new ImageIcon("pb2.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH)));
        pb3.setMargin(pi1);
        pb3.setBorder(BorderFactory.createEmptyBorder());
        pb4 = new JButton(new ImageIcon(((new ImageIcon("pb3.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH)));
        pb4.setMargin(pi1);
        pb4.setBorder(BorderFactory.createEmptyBorder());
        pb5 = new JButton(new ImageIcon(((new ImageIcon("pb4.png")).getImage()).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH)));
        pb5.setBorder(BorderFactory.createEmptyBorder());
        pb5.setMargin(pi1);
        
        vb = new JButton(vi1);
        vb.setBorder(BorderFactory.createEmptyBorder());
        vb.setContentAreaFilled(false);
        vb.addActionListener(this);
        
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,sw,none,pib1,8,8);
        p1.add(pb1,gbcf);
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,sw,none,pib2,6,6);
        p1.add(pb2,gbcf);
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,sw,none,pib3,6,6);
        p1.add(pb3,gbcf);
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,sw,none,pib4,6,6);
        p1.add(pb4,gbcf);
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,sw,none,pib5,6,6);
        p1.add(pb5,gbcf);
        gbcf = new GridBagConstraints(0,0,0,0,1,1,se,none,pis2,110,21);
        //p1.add(tsp1,gbcf);
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,se,none,piv,0,0);
        p1.add(vb,gbcf);
        pb1.addActionListener(this);
        pb2.addActionListener(this);
        pb3.addActionListener(this);
        pb4.addActionListener(this);
        pb5.addActionListener(this);
        
        p2 = new JPanel();
        p2.setLayout(lgbag);
        p2.setBackground(Color.LIGHT_GRAY);
        gbcf = new GridBagConstraints(0,0,0,0,1,1,south,horizontal,pi2,0,1);
        f.add(p2,gbcf);
        duration = new JLabel(" Duration");
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,west,none,pi1,5,5);
        p2.add(duration,gbcf);
        length = new JLabel("Length  ");
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,east,none,pi1,5,5);
        p2.add(length,gbcf);
        ps1 = new JSlider(0,100,0);
        ps1.setBackground(Color.DARK_GRAY);
        
        gbcf = new GridBagConstraints(0,0,0,0,0.1,0.1,center,horizontal,pis1,0,5);
        p2.add(ps1,gbcf);
        ps2 = new JSlider();
        ps2.setBounds(2,1,107,19);
        tsp1.add(ps2);
        
        // Creating Media Player on vpanel
        new NativeDiscovery().discover();
        EmbeddedMediaPlayerComponent mcomp = new EmbeddedMediaPlayerComponent();
        mplay = mcomp.getMediaPlayer();
        Canvas videoSurf = mcomp.getVideoSurface();
        vpanel.add(videoSurf);
        //
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        if(ae.getSource()==pb1)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\manis\\Desktop"));
            fileChooser.setSelectedFile(new File("Tom & Jerry - Blast Off to Mars.mkv"));

            int result = fileChooser.showOpenDialog(null);

            switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    File tempf = fileChooser.getSelectedFile();
                    mplay.playMedia(tempf.getAbsolutePath(), (String) null);
                    break;
                case JFileChooser.CANCEL_OPTION:
                    JOptionPane.showMessageDialog(null, "You selected nothing.");
                    break;
                case JFileChooser.ERROR_OPTION:
                    JOptionPane.showMessageDialog(null, "An error occurred.");
                    break;
                default:  
                    break;
            }
                
            try 
            {
                c = new Counter(mplay,duration,length,ps1);
            } catch (IOException ex) {
            }
            c.start();
        }
        else if(ae.getSource()==pb2)
        {
            if(playstopstatus)
            {
                playstopstatus=false;
                pb2.setIcon(pi2_2);
                mplay.stop();
                c.suspend();
                duration.setText("00:00:00");
            }
            else
            {
                playstopstatus=true;
                pb2.setIcon(pi2_1);
                mplay.start();
                c.resume();
            }
        }
        else if(ae.getSource()==pb3)
        {    
            mplay.skip(-10000);
        }
        else if(ae.getSource()==pb4)
        {
            if(!playpausestatus)
            {
                playpausestatus = true;
                mplay.setPause(playpausestatus);
                c.suspend();
            }
            else
            {
                playpausestatus = false;
                mplay.setPause(playpausestatus);
                c.resume();
            }
                 
        }
        else if(ae.getSource()==pb5)
        {
            mplay.skip(10000); 
        }     
        else if(ae.getSource()==ma1)
        {
            mplay.setAspectRatio("1:1");
        }     
        else if(ae.getSource()==ma2)
        {
            mplay.setAspectRatio("3:2");
        }     
        else if(ae.getSource()==ma3)
        {
            mplay.setAspectRatio("4:3");
        }     
        else if(ae.getSource()==ma4)
        {
            mplay.setAspectRatio("5:4");
        }     
        else if(ae.getSource()==ma5)
        {
            mplay.setAspectRatio("16:9");
        }   
        
        
        else if(ae.getSource()==mp1)
        {
            mplay.setRate(0.5f);
        }     
        else if(ae.getSource()==mp2)
        {
            mplay.setRate(1.0f);
        }     
        else if(ae.getSource()==mp3)
        {
            mplay.setRate(1.5f);
        }     
        else if(ae.getSource()==mp4)
        {
            mplay.setRate(2.0f);
        }     
        else if(ae.getSource()==mp5)
        {
            mplay.setRate(4.0f);
        }     
  
        else if(ae.getSource()==vb)
        {
            if(vbstatus)
            {
                vbstatus=false;
                vb.setIcon(vi2);
                mplay.mute(true);
            }
            else
            {
                vbstatus=true;
                vb.setIcon(vi1);
                mplay.mute(false);
            }
        } 
    }  
    
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        mplay.setPosition(1000*ps1.getValue());
    }
}