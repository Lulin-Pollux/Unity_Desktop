package app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class NewUser extends JInternalFrame  {//JFrame ���
	
//�޺��ڽ��� üũ�ڽ��� ���� �迭 ����
private String[] local = {"����","�뱸������", "����Ư����", "�λ걤����", "���ֱ�����", "��Ÿ"};
private String[] special1 = {"Ư��1", "�ܱ���", "��ǻ��", "�౸","�Ǳ⿬��", "��Ÿ"};
private String[] special2 = {"Ư��2", "�ܱ���", "��ǻ��", "�౸","�Ǳ⿬��", "��Ÿ"};

public NewUser() {
	super("ȸ������");// ������ ����
	setClosable(true);
	setIconifiable(true);
	setMaximizable(true);
	setResizable(false);
	
	
	Container c = getContentPane();//����Ʈ�� �ޱ�(�����̳ʰ�ü)
	c.setLayout(new BorderLayout());//c ��ġ������ ����
	c.add(new MyPanel(), BorderLayout.SOUTH);
	
	
	JPanel p1 = new JPanel();//�� �г� ���� �� �ʱ�ȭ
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	JPanel p8 = new JPanel();
	JPanel p9 = new JPanel();
	JPanel p10 = new JPanel();
	JPanel p11 = new JPanel();
	JPanel p12 = new JPanel();
	JPanel p13 = new JPanel();
	JPanel p14 = new JPanel();
	
	c.add(p1, BorderLayout.NORTH);//�г��� �����̳ʿ� �߰�(��ġ�� ����)
	
	
	
	p1.setLayout(new BorderLayout());//�� �гο� ��ġ������ ����
	p2.setLayout(new FlowLayout());//Flow>>��ġ�������� ������� ��ġ
	p3.setLayout(new BorderLayout());//Border>>������ ��ġ��� ��ġ
	p4.setLayout(new BorderLayout());
	p5.setLayout(new BorderLayout());
	p6.setLayout(new BorderLayout());
	p7.setLayout(new BorderLayout());
	p8.setLayout(new BorderLayout());
	p9.setLayout(new BorderLayout());
	p10.setLayout(new BorderLayout());
	p11.setLayout(new FlowLayout());
	p12.setLayout(new FlowLayout());
	p13.setLayout(new FlowLayout());
	p14.setLayout(new FlowLayout());
	
	JLabel nameLabel = new JLabel("�� ��(ID)");//JLabel ���� �� �ʱ�ȭ
	JTextField nameField = new JTextField(10);
	//10�� �ؽ�Ʈ ���� JTextField ����
	p6.add(nameLabel, BorderLayout.WEST);//p6�� ���� ��ġ��� 
	p6.add(nameField, BorderLayout.EAST);//��ġ
	p3.add(p6, BorderLayout.WEST);//p3�� p6��ġ
	
	
	JLabel passwdLabel = new JLabel("�� ȣ");
	//JLabel ��ü ���� �� �ʱ�ȭ
	 JPasswordField passField = new JPasswordField(10);
	//10�� ���� �н������ʵ� ����
	p7.add(passwdLabel, BorderLayout.WEST);//p7�� �����Ѵ�� ��ġ
	p7.add(passField, BorderLayout.EAST);
	p3.add(p7, BorderLayout.EAST);//p3�� �����Ѵ�� ��ġ
	
	JLabel gradeLabel = new JLabel("�� ��");
	ButtonGroup gra = new ButtonGroup(); //��ư�׷찴ü ���� �� �ʱ�ȭ
	JRadioButton grade1 = new JRadioButton("����");
	JRadioButton grade2 = new JRadioButton("����");
	JRadioButton grade3 = new JRadioButton("����");
	JRadioButton grade4 = new JRadioButton("���п�");
	p11.add(grade1);//�г�p11�� �з� �߰��ϱ�
	p11.add(grade2);
	p11.add(grade3);
	p11.add(grade4);
	gra.add(grade1); //������ư �׷쿡 ����
	gra.add(grade2); // �ϳ��� ������ �� ����
	gra.add(grade3);
	gra.add(grade4);
	p8.add(gradeLabel, BorderLayout.WEST);
	//�г�p8�� gradeLabel�� ���ʿ� �߰�
	p8.add(p11, BorderLayout.CENTER);
	//�г�p8�� p11�� �߾ӿ� �߰�
	JLabel bornLabel = new JLabel("�����");
	
	JComboBox born = new JComboBox(local);
	
	
	p9.add(bornLabel, BorderLayout.WEST);
	p9.add(born, BorderLayout.EAST);
	
	
	
	JLabel hobbyLabel = new JLabel("�� ��");//�� �󺧿� 
	JCheckBox chk1 = new JCheckBox("����"); //�Էµ� �ؽ�Ʈ
	JCheckBox chk2 = new JCheckBox("����");
	JCheckBox chk3 = new JCheckBox("����");
	JCheckBox chk4 = new JCheckBox("���");
	JCheckBox chk5 = new JCheckBox("����");
	JCheckBox chk6 = new JCheckBox("������"); 
	JCheckBox chk7 = new JCheckBox("��Ÿ");
	
	ButtonGroup hobby = new ButtonGroup();//üũ�ڽ��� �ϳ��� ��ư�׷�����
	hobby.add( chk1);
	hobby.add( chk2);
	hobby.add( chk3);
	hobby.add( chk4);
	hobby.add( chk5);
	hobby.add( chk6);
	hobby.add( chk7);
	
	
	p12.add(chk1); //p12�� üũ�ڽ��� ���� ��� ��ü���� �߰�
	p12.add(chk2);
	p12.add(chk3);
	p12.add(chk4);
	p12.add(chk5);
	p12.add(chk6);
	p12.add(chk7);
	p10.add(hobbyLabel, BorderLayout.WEST);
	//p10�� hobbyLabel�� ���ʿ� �߰�
	p10.add(p12, BorderLayout.EAST);
	//p10�� p12�� �����ʿ� �߰�
	p4.add(p8, BorderLayout.NORTH);
	//p4�� p8�� ���ʿ� �߰�
	p4.add(p9, BorderLayout.CENTER);
	//p4�� p9�� �߾ӿ��߰�
	p4.add(p10, BorderLayout.SOUTH);
	//p4�� p10�� ���ʿ� �߰� 
	
	JLabel PriceLabel = new JLabel("Ư ��");
	//JLabel ������ �ʱ�ȭ
	JComboBox Price1 = new JComboBox(special1);
	//JComboBox ���� �� �ʱ�ȭ
	JComboBox Price2 = new JComboBox(special2);
	p13.add(Price1); //p13�� price1�߰� 
	p13.add(Price2);//p13�� price2�߰� 
	p5.add(PriceLabel, BorderLayout.WEST);
	
	p5.add(p13, BorderLayout.EAST);
	
	p1.add(p3, BorderLayout.NORTH);//p3�� p1���ʿ� ��ġ
	p1.add(p4, BorderLayout.CENTER);//p4�� p1�߾ӿ� ��ġ
	p1.add(p5, BorderLayout.SOUTH);//p5�� p1�Ʒ��ʿ� ��ġ
	
	c.add(p14,BorderLayout.CENTER);
	JButton resetBtn = new JButton("Reset");
	p14.add(resetBtn,BorderLayout.CENTER);
	
	
	
	
	setSize(500,270);//������ ũ�� �˸°�
	setVisible(true);//ȭ�鿡 �������� ���
	
	
	resetBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent c) {
			nameField.setText(null); //���� ��ư�� ������ ������
			passField.setText(null);//�ʱ�ȭ ����
			gra.clearSelection();
			hobby.clearSelection();
			born.setSelectedItem("����");
			Price1.setSelectedItem("Ư��1");
			Price2.setSelectedItem("Ư��2");
		}
	});
}

class MyPanel extends Panel  {

	JButton inputBtn = new JButton("Ȯ��");		
	JTextField tf = new JTextField(10);
	JButton exitBtn = new JButton("����");
	JButton messageBtn = new JButton("Help");
	
	MyPanel() {
		//�� ��ư�� ������ ����
		add(inputBtn);
		add(exitBtn);
		add(messageBtn);
		add(tf);
		
		//������ �̺�Ʈ (��ư�� ������)
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �Է� ���̾�α� ����
				
				String pw = JOptionPane.showInputDialog(" ����Ʈ ��й�ȣ�� �Է��� �ּ��� ");
				
				
				// ����ڰ� �Է��� ���ڿ��� �ؽ�Ʈ�ʵ� â�� ���
				 if(pw.equals("1234")) { //�̸� ������ ��ȣâ
					tf.setText(" ȸ�����ԵǼ̽��ϴ�");
					 }
				else
					 {
					
					 pw = JOptionPane.showInputDialog(" �߸� �Է��ϼ̽��ϴ� \n  "+
			 "�ѹ� �� �Է��� �ּ���");
			 
			    if(pw.equals("kwangwoon1485")) { //�̸� ������ ��ȣâ
					tf.setText(" ȸ�����ԵǼ̽��ϴ�");
					 }
			    
			    else {
			    	
					 pw = JOptionPane.showInputDialog(" �߸� �Է��ϼ̽��ϴ� \n  "+
					 "�ѹ� �� �Է��� �ּ���");
					 
					    if(pw.equals("kangwon1485")) { //�̸� ������ ��ȣâ
							tf.setText(" ȸ�����ԵǼ̽��ϴ�");
							 }
					    else
					    {
					    	 pw = JOptionPane.showInputDialog(" �߸� �Է��ϼ̽��ϴ� \n  "+
									 "�ѹ� �� �Է��� �ּ���");
					    	 if(pw.equals("kangwon1485")) { //�̸� ������ ��ȣâ
									tf.setText(" ȸ�����ԵǼ̽��ϴ�");
									 }
							    else
							    {
									JOptionPane.showMessageDialog(null, "3����� �߸��Է��ϼ̽��ϴ�", "error", JOptionPane.ERROR_MESSAGE);
									dispose();// "3����� �߸��Է��ϼ̽��ϴ�" �޽��� â �߰�
									//���α׷� ����
									 }
					    	 /* �Է��� �ϰ� �ùٸ��� ������ ȸ�����ԿϷ�
					    	  Ʋ���� �ٽ� �Է�â ����... �׸��� 3�� Ʋ������*/
					    }
			    }	    
				
					 }
			}
		});
		
		
		exitBtn.addActionListener(new ActionListener() {//���� ��ư �̺�Ʈ
			public void actionPerformed(ActionEvent e) {//
				//��� ���̾�α� ����
				int result = JOptionPane.showConfirmDialog(null, 
						"���α׷��� ���� �Ͻðڽ��ϱ�?", "Exit", JOptionPane.YES_NO_OPTION);
				
				// ����ڰ� ������ ��ư�� ���� ���ڿ��� �ؽ�Ʈ�ʵ� â�� ���
				if(result == JOptionPane.CLOSED_OPTION)
					tf.setText("Just Closed Out");
				else if(result == JOptionPane.YES_OPTION) { //yes ������
			
					dispose();// ���α׷� ����
				}
				else
					tf.setText(" ");
			}
		});
		
		
		

			
		
		
		messageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//���â �̺�Ʈ
				// �޽��� ���̾�α� ����					
				JOptionPane.showMessageDialog(null, "���߿� ���α׷� ���� �� ������� ���� �� �ֽ��ϴ�", "Help", JOptionPane.ERROR_MESSAGE); 
			}
		});
	}
}




	public static void main(String args[]) {
		new NewUser(); //��ü ������ ������ ȣ��
	}

}

/*  ������: ������ */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
