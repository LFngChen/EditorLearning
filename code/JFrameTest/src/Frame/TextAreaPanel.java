/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *����mainFrame���ĵ��༭��Ϊ���ı��༭��
 * @author ���Ƹ�
 */
public class TextAreaPanel extends JPanel{
    static JFrame mf;
    private JComboBox combox_name, combox_size;// ���塢�ֺ���Ͽ�  
    private JButton button_larger,button_smaller,button_color;//�������С����ɫѡ����  
    private JCheckBox checkb_bold, checkb_italic;// ���塢б�帴ѡ��  
    private JPopupMenu popupmenu;  
    public JTextArea ta = new JTextArea();  
    private JScrollPane sp = new JScrollPane(ta);
    
    public TextAreaPanel(JFrame mf){
        this.mf = mf;
        this.setLayout(new BorderLayout());
        
        JToolBar toolbar = new JToolBar(); // ����������  
        this.add(toolbar, BorderLayout.NORTH); // ��������ӵ����񱱲�  
        this.add(sp);  
        ta.setLineWrap(true);// ����  
        //////////////////����  
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  //��õ�ǰ���Ե����廷��
        String[] fontsName = ge.getAvailableFontFamilyNames(); // ���ϵͳ����  
        combox_name = new JComboBox(fontsName);  
        toolbar.add(combox_name);  
        combox_name.addActionListener(new ActionListener() {// �ֺ�  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //������� ��ԭ�����Σ� 
                int size = font.getSize();  	//��������С��ԭ�д�С��
                ta.setFont(new Font(fontname, style, size));  //�������������ʽ
            }  
        });  
        /////////////////�ֺ�  
        String sizestr[] = { "20", "30", "40", "50", "60", "70" ,"80","90","100"};  
        combox_size = new JComboBox(sizestr);  
        combox_size.setEditable(true);  
        toolbar.add(combox_size);  
        combox_size.addActionListener(new ActionListener() {// �ֺ�  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                int size = Integer.parseInt((String)combox_size.getSelectedItem());  //���ѡ�����Ҫ���ĵ��ֺ���
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////////�ֺżӼ���ť  
        button_larger=new JButton("A+");  
        toolbar.add(button_larger);  
        button_larger.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize()+5; 		//�ڵ�ǰ�������С�Ļ����ϼ�5Ȼ���������� 
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        button_smaller=new JButton("A-");  
        toolbar.add(button_smaller);  
        button_smaller.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize()-5;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        /////////////////J  
        /////////////////�����б��  
        checkb_bold = new JCheckBox("����"); //���θ�ѡ��  
        toolbar.add(checkb_bold);  
        checkb_bold.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                style = style ^ 1;     			//λ�����㣬����������Խ���ǰ�����̴���
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        checkb_italic = new JCheckBox("б��");  
        toolbar.add(checkb_italic);  
        checkb_italic.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//���������  
                Font font = ta.getFont();     //����ı����ĵ�ǰ�������  
                int style = font.getStyle();      //�������  
                int size = font.getSize();  
                style = style ^ 2;  			//����������Խ���ǰ������б��
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////  
        JRadioButton radiob_color[];  						//java��ѡ����ͬһ��������Ȼ�ж����ѡ����ڣ�Ȼ��ͬһʱ��ֻ����һ����ѡ����ѡ��״̬
        String colorstr[]={"��","��","��"};  
        ButtonGroup bgroup_color = new ButtonGroup();      //��ť��  
        radiob_color = new JRadioButton[colorstr.length];  //��ɫ��ѡ��ť����  
        for (int i=0; i<radiob_color.length; i++){  
            radiob_color[i]=new JRadioButton(colorstr[i]);   
            bgroup_color.add(radiob_color[i]); //��ӵ���ť��  
            toolbar.add(radiob_color[i]);     //��ӵ�������  
        }          
        radiob_color[0].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.red);// ������ɫ  
            }  
        });  
        radiob_color[1].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.green);  
            }  
        });  
        radiob_color[2].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.blue);  
            }  
        });  
        ///////////////��ɫѡ����  
        button_color=new JButton("����");  
        toolbar.add(button_color);  
        button_color.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                Color color;  
                color=JColorChooser.showDialog(TextAreaPanel.this,"��ɫѡ��", Color.black);  
                ta.setForeground(color);// ������ɫ  
            }  
        });  
        ////////////////����¼�  
        ta.addMouseListener(new MouseAdapter() {// ����¼����������һ������˵�  
            public void mouseClicked(MouseEvent e) {  
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) // ������������Ҽ�  
                    popupmenu.show(ta, e.getX(), e.getY()); // ����굥������ʾ��ݲ˵�  
            }  
        });  
        this.setVisible(true);
    }
    
    public static void main(String[] args){
        JFrame jf = new JFrame("�������");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        //Dimension dim = getToolkit().getScreenSize(); // �����Ļ�ֱ���  
        jf.setBounds(900, 900, 700, 480);
        /**
        jf.setContentPane(new TextAreaPanel(jf));
        jf.pack();
        * **/
        jf.setVisible(true);
      
    }
}
