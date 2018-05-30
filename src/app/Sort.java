package app;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Sort extends JInternalFrame {
	private static final long serialVersionUID = -5824032554481874133L;
	
	
	public Sort() {
		/* ������ ���� */
		//-----------------------------------------------------------------------
		setTitle("���� ���α׷�");
		setSize(397, 370);
		setResizable(false);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		//-----------------------------------------------------------------------
		
		/* ����Ʈ�� */
		//------------------------------------------------------------
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));
		//------------------------------------------------------------
		
		
		/* ���� �� */
		//------------------------------------------------------------
		JLabel lbl1 = new JLabel("������ ������ �Է��ϼ���.");
		lbl1.setForeground(Color.BLACK);
		lbl1.setFont(new Font("���� ���", Font.PLAIN, 18));
		c.add(lbl1);
		//------------------------------------------------------------
		
		
		/* �ؽ�Ʈ �Է� â*/
		//------------------------------------------------------------
		JTextArea ta1 = new JTextArea(13,30);
		ta1.setFont(new Font("����", Font.PLAIN, 15));
		c.add(new JScrollPane(ta1));
		//------------------------------------------------------------
		
		
		/* �������� ��ư */
		//-----------------------------------------------------------------------
		JButton btn1 = new JButton("�������� ����");
		btn1.setFont(new Font("���� ���", Font.PLAIN, 15));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = ta1.getText();
				int rows = ta1.getLineCount();
				StringTokenizer tokens = new StringTokenizer(input, "\n");
				
				String array_tokens[] = new String[rows];
				
				for (int i=0; i<array_tokens.length; i++)
					array_tokens[i] = "";
				
				for(int i=0; tokens.hasMoreTokens(); i++)
					array_tokens[i] = tokens.nextToken();
				
				Arrays.sort(array_tokens);
				
				ta1.setText("");
				for(int i=0; i<array_tokens.length; i++) {
					ta1.append(array_tokens[i]);
					
					if (i != array_tokens.length-1)
						ta1.append("\n");
				}
			}
		});
		c.add(btn1);
		//-----------------------------------------------------------------------
		
		
		/* �������� ��ư */
		//-----------------------------------------------------------------------
		JButton btn2 = new JButton("�������� ����");
		btn2.setFont(new Font("���� ���", Font.PLAIN, 15));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = ta1.getText();
				int rows = ta1.getLineCount();
				StringTokenizer tokens = new StringTokenizer(input, "\n");
				
				String array_tokens[] = new String[rows];
				
				for (int i=0; i<array_tokens.length; i++)
					array_tokens[i] = "";
				
				for(int i=0; tokens.hasMoreTokens(); i++)
					array_tokens[i] = tokens.nextToken();
				
				Arrays.sort(array_tokens);
				
				ta1.setText("");
				for(int i=array_tokens.length-1; i>=0; i--) {
					ta1.append(array_tokens[i]);
					
					if (i != 0)
					   ta1.append("\n");
				}
			}
		});
		c.add(btn2);
		//-----------------------------------------------------------------------
		
		
		/* �ؽ�Ʈ â ����� ��ư */
		//------------------------------------------------------------
		JButton btnReset = new JButton("�����");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ta1.setText("");
			}
		});
		btnReset.setFont(new Font("���� ���", Font.PLAIN, 15));
		getContentPane().add(btnReset);
		//------------------------------------------------------------
		
		
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);  // â ����� ���α׷����� ���� ����
		setVisible(true); // â ǥ�ÿ���
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Sort();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

/*  ������: Lulin Pollux, ���¿� */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
