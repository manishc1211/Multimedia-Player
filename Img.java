package gridbag;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class Img implements ActionListener
{
    int center = GridBagConstraints.CENTER;
    int north = GridBagConstraints.NORTH;
    int south = GridBagConstraints.SOUTH; 
    int horizontal = GridBagConstraints.HORIZONTAL;
    int both = GridBagConstraints.BOTH;
    int none = GridBagConstraints.NONE;
    Insets in0 = new Insets(0,0,0,0);
    Insets in2 = new Insets(0,110,0,0);
    Insets in4 = new Insets(0,0,0,110);
    Insets insp = new Insets(0,0,40,0);
    
    JLabel image;
    JPanel sp,jp;
    JLabel limage;
    GridBagConstraints gbc,gbc1;
    ImageIcon img,timg,bi4,bi2,bi3;
    Dimension d = new Dimension(1300,768);
    int x,y,fi=0;
    File[] files;
    JButton b2,b3,b4;
    JFrame f;
    Img()
    {
        f =  new JFrame("Image");
        f.setSize(601,610);
        f.setMaximumSize(d);
        f.setLayout(new GridBagLayout());
        
        bi2 = new ImageIcon(((new ImageIcon("i4.png")).getImage()).getScaledInstance(32,32,Image.SCALE_SMOOTH));
        bi3 = new ImageIcon(((new ImageIcon("i3.png")).getImage()).getScaledInstance(32,32,Image.SCALE_SMOOTH));
        bi4 = new ImageIcon(((new ImageIcon("i2.png")).getImage()).getScaledInstance(32,32,Image.SCALE_SMOOTH));
        
        jp = new JPanel();
        sp = new JPanel();
        b2 = new JButton(bi2);
        b3 = new JButton(bi3);
        b4 = new JButton(bi4);
        b2.setMargin(new Insets(0,0,0,0));
        b3.setMargin(new Insets(0,0,0,0));
        b4.setMargin(new Insets(0,0,0,0));
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b2.setBorder(null);
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b3.setBorder(null);
        b3.setBorderPainted(false);
        b3.setContentAreaFilled(false);
        b4.setBorder(null);
        b4.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        
        limage = new JLabel();
        
        sp.setBackground(Color.BLACK);
        sp.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints(0,0,0,0,1,1,center,none,in0,0,0);
        sp.add(limage,gbc);
        
        
        jp.setBackground(Color.black);
        jp.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints(0,0,0,0,1,1,center,none,in2,0,0);
        jp.add(b2,gbc);
        gbc = new GridBagConstraints(0,0,0,0,1,1,center,none,in0,0,0);
        jp.add(b3,gbc);
        gbc = new GridBagConstraints(0,0,0,0,1,1,center,none,in4,0,0);
        jp.add(b4,gbc);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
      
        
        gbc = new GridBagConstraints(0,0,0,0,1,1,south,horizontal,in0,0,14);
        f.add(jp,gbc);
        gbc = new GridBagConstraints(0,0,0,0,1,1,north,both,insp,0,0);
        f.add(sp,gbc);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b3)
        {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:\\Users\\manis\\Desktop"));
            fc.setMultiSelectionEnabled(true);
            fc.showOpenDialog(null);
            files = fc.getSelectedFiles();
            display();
        }
        if(ae.getSource()==b4)
        {
            if(fi>0)
            {
                fi--;
                display();
            }
            else
                JOptionPane.showMessageDialog(null,"No more selected files.");
        }
        if(ae.getSource()==b2)
        {
            if(fi<files.length-1)
            {
                fi++;
                display();
            }
            else
                JOptionPane.showMessageDialog(null,"No more selected files.");
        }
    }  
    public void display()
    {
        img = new ImageIcon(files[fi].getAbsolutePath());
            if(img.getIconHeight()>705)
                y=705;
            else
                y=img.getIconHeight();
            if(img.getIconWidth()>1070)
                x=1070;
            else
                x=img.getIconWidth();
        
        timg = new ImageIcon((img.getImage()).getScaledInstance(x,y,Image.SCALE_SMOOTH));
        limage.setIcon(timg);
    }
    public static void main(String args[])
    {
        Img i = new Img();
    }
}