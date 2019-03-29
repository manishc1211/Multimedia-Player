package gridbag;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GridBag implements ActionListener, MouseListener
{
    JButton b1,b2,b3;
    JPanel p;
    JFrame f;
    JRootPane rp;
    ImageIcon i1;
    int center = GridBagConstraints.CENTER;
    int north = GridBagConstraints.NORTH;
    int south = GridBagConstraints.SOUTH;
    int east = GridBagConstraints.EAST;
    int west = GridBagConstraints.WEST;
    int nw = GridBagConstraints.NORTHWEST;
    int none = GridBagConstraints.NONE;
    int rel = GridBagConstraints.RELATIVE;
    int vertical = GridBagConstraints.VERTICAL;
    int horizontal = GridBagConstraints.HORIZONTAL;
    Insets in = new Insets(10,-11,9,0);
    Insets in2 = new Insets(49,0,0,0);
    Insets in3 = new Insets(0,49,0,0);
    Insets in4 = new Insets(71,38,0,0);
    GridBagConstraints gbc,gbc1;
    GridBag() throws IOException
    {
        f = new JFrame("Media Player");
        f.setSize(501,510);
        GridBagLayout gbag = new GridBagLayout();
        f.setLayout(gbag);
        f.setBackground(new Color(36,36,36,230));
        
        p = new JPanel();
        JPanel pcopy = new JPanel();
        p.setLayout(null);
        pcopy.setLayout(null);
        p.setBackground(new Color(0,0,0,00));
        pcopy.setBackground(new Color(0,0,0,100));
        ImageIcon i = new ImageIcon(((new ImageIcon("b1.png")).getImage()).getScaledInstance(55,55,Image.SCALE_SMOOTH));
        b1 = new JButton(i);
        b1.setOpaque(false);        
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.setContentAreaFilled(false);
        b1.addActionListener(this);
        
        b2 = new JButton(new ImageIcon(((new ImageIcon("b2.png")).getImage()).getScaledInstance(55,55,Image.SCALE_SMOOTH)));
        b2.setBorder(BorderFactory.createEmptyBorder());
        b2.setContentAreaFilled(false);
        b2.addActionListener(this);
        
        b3 = new JButton(new ImageIcon(((new ImageIcon("b3.png")).getImage()).getScaledInstance(55,55,Image.SCALE_SMOOTH)));
        b3.setBorder(BorderFactory.createEmptyBorder());
        b3.setContentAreaFilled(false);
        b3.addActionListener(this);
        
        b1.setBounds(30,122,55,55);
        b2.setBounds(120,122,55,55);
        b3.setBounds(210,122,55,55);
        
        b1.addMouseListener(this);
        b2.addMouseListener(this);
        b3.addMouseListener(this);
        
        p.add(b1);
        p.add(b2);
        p.add(b3);
        gbc = new GridBagConstraints(0,0,0,0,0.0,0.0,center,none,in,300,300);
        f.add(p,gbc);
        f.add(pcopy,gbc);
        Color bcol = new Color(42,32,72,240);
        JPanel P1 = new JPanel();
        P1.setBackground(bcol);
        gbc1 = new GridBagConstraints(20,40,7,10,2.5,1.5,north,horizontal,in2,0,30);
        f.add(P1,gbc1);  
        JPanel P2 = new JPanel();
        P2.setBackground(bcol);
        gbc1 = new GridBagConstraints(0,0,0,0,1,1,west,vertical,in3,30,0);
        f.add(P2,gbc1);
        
        JLabel l = new JLabel("Media Player");
        l.setForeground(Color.CYAN);
        l.setFont(new Font(Font.SERIF,Font.TRUETYPE_FONT+Font.BOLD,51));
        JPanel tp = new JPanel();
        tp.add(l);        
        tp.setBackground(new Color(0,0,0,0));   
        gbc = new GridBagConstraints(0,0,0,0,0.0,0.0,nw,none,in4,100,50);
        f.add(tp,gbc);
                
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1)
        {
            Video v = new Video();  
            v.vid();
            
        }
        else if(ae.getSource()==b2)
        {
            Audio a = new Audio();
        }
        else if(ae.getSource()==b3)
        {
            Img i = new Img();
        }
    }
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable(){@Override public void run() {try {
            GridBag gb = new GridBag();
            } catch (IOException ex) {
                Logger.getLogger(GridBag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
    }

    

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==b1)
        {
            b1.setBounds(28,120,55,55);
        }
        else if(e.getSource()==b2)
        {
             b2.setBounds(118,120,55,55);
        }
        else if(e.getSource()==b3)
        {
            b3.setBounds(208,120,55,55);
        }
        
    }   
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==b1)
        {
            b1.setBounds(30,122,55,55);
        
        }
        else if(e.getSource()==b2)
        {
            b2.setBounds(120,122,55,55);
        
        }
        else if(e.getSource()==b3)
        {
            b3.setBounds(210,122,55,55);
        }
    }
        
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}