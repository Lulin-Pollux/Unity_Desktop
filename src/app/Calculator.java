package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Calculator extends JInternalFrame {
	private static final long serialVersionUID = -4757435002385107956L;
	
	/* ��� ������Ʈ */
	//-----------------------------------
	private JPanel contentPane;
	private JTextField txtResult;
	private JLabel txtFormula;
	//-----------------------------------
	
	/* ��� ���� */
	//------------------------------------
	private boolean opEnable = false;
	private boolean calculate_exception = false;
	//------------------------------------
	
	/* ��� �޼ҵ� */
	//------------------------------------
	private double compute() {
		String formula = txtFormula.getText();	//�� â�� ������ �޾ƿ�
		//�����̽��� �������� �ϴ� ��ū �к��⸦ ����
		StringTokenizer tokens = new StringTokenizer(formula, " ");
		
		String formula_tokens[] = new String[100];	//�� ��ū�� ������ �迭 ����
		int EndOfArray = 0;		//�迭�� �� ����
		double result = 0;
		
		for (int i=0; tokens.hasMoreElements(); i++) {
			formula_tokens[i] = tokens.nextToken();
			if (tokens.hasMoreTokens() == false) {
				EndOfArray = i;
			}
		}
		
		txtFormula.setText("");
		
		for (int i=0; i<=EndOfArray; i++) {
			switch (formula_tokens[i]) {
			
			case "+":
				if (result == 0)
					result = Double.parseDouble(formula_tokens[0]) + Double.parseDouble(formula_tokens[2]);
				else
					result += Double.parseDouble(formula_tokens[i+1]);
				break;
				
			case "-":
				if (result == 0)
					result = Double.parseDouble(formula_tokens[0]) - Double.parseDouble(formula_tokens[2]);
				else
					result -= Double.parseDouble(formula_tokens[i+1]);
				break;
				
			case "x":
				if (result == 0)
					result = Double.parseDouble(formula_tokens[0]) * Double.parseDouble(formula_tokens[2]);
				else
					result *= Double.parseDouble(formula_tokens[i+1]);
				break;
				
			case "/":
				if (result == 0) {
					if (formula_tokens[2].equals("0")) {
						txtResult.setText("0���� ���� �� �����ϴ�.");
						txtResult.setFont(new Font("���� ���", Font.BOLD, 32));
						result = 0;
						calculate_exception = true;
					}
					else
						result = Double.parseDouble(formula_tokens[0]) / Double.parseDouble(formula_tokens[2]);
				}
				else if (formula_tokens[i+1].equals("0")) {
					txtResult.setText("0���� ���� �� �����ϴ�.");
					txtResult.setFont(new Font("���� ���", Font.BOLD, 32));
					result = 0;
					calculate_exception = true;
				}
				else
					result /= Double.parseDouble(formula_tokens[i+1]);
				break;
			}
		}
		return result;
	}
	//------------------------------------

	
	/* ������ ���� */
	//--------------------------------------------------------------
	public Calculator() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		/* ������ ���� */
		//----------------------------------------------------------------
		setTitle("����");
		setSize(318, 500);			//������ ũ�� ����
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		//----------------------------------------------------------------
		
		/* ����Ʈ�� ���� */
		//--------------------------------------------------
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		//--------------------------------------------------
		
		
		/* ���� �г� */
		//--------------------------------------------------
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(northPanel);
		northPanel.setLayout(new GridLayout(2, 1, 4, 4));
		
		JPanel result_panel = new JPanel();
		result_panel.setBackground(Color.DARK_GRAY);
		northPanel.add(result_panel);
		result_panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		txtFormula = new JLabel();
		txtFormula.setFont(new Font("���� ���", Font.PLAIN, 16));
		txtFormula.setForeground(Color.WHITE);
		txtFormula.setBackground(Color.DARK_GRAY);
		txtFormula.setVerticalAlignment(SwingConstants.BOTTOM);
		txtFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		result_panel.add(txtFormula);
		
		txtResult = new JTextField();
		txtResult.setForeground(Color.WHITE);
		txtResult.setBackground(Color.DARK_GRAY);
		txtResult.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtResult.setFont(new Font("���� ���", Font.BOLD, 45));
		txtResult.setHorizontalAlignment(SwingConstants.RIGHT);
		txtResult.setEditable(false);
		txtResult.setText("0");
		result_panel.add(txtResult);
		txtResult.setColumns(10);
		
		JPanel additional_buttonPanel = new JPanel();
		additional_buttonPanel.setBackground(Color.DARK_GRAY);
		northPanel.add(additional_buttonPanel);
		additional_buttonPanel.setLayout(new GridLayout(2, 1, 4, 4));
		
		JPanel blank_panel = new JPanel();
		additional_buttonPanel.add(blank_panel);
		blank_panel.setBackground(Color.DARK_GRAY);
		blank_panel.setLayout(new GridLayout(1, 4, 4, 4));
		
		JPanel button_panel1 = new JPanel();
		button_panel1.setBorder(new EmptyBorder(0, 0, 1, 0));
		additional_buttonPanel.add(button_panel1);
		button_panel1.setBackground(Color.DARK_GRAY);
		button_panel1.setLayout(new GridLayout(1, 4, 4, 4));
		
		JButton btnCe = new JButton("CE");
		btnCe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResult.setText("0");
				calculate_exception = false;
			}
		});
		btnCe.setFont(new Font("���� ���", Font.BOLD, 16));
		button_panel1.add(btnCe);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResult.setText("0");
				txtFormula.setText("");
				opEnable = false;
				calculate_exception = false;
			}
		});
		btnC.setFont(new Font("���� ���", Font.BOLD, 16));
		button_panel1.add(btnC);
		
		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String num = txtResult.getText();
				if (num.length() == 1)
					txtResult.setText("0");
				else if (calculate_exception == true) {
					txtResult.setText("0");
					calculate_exception = false;
				}
				else {
					num = num.substring(0, num.length()-1);
					txtResult.setText(num);
				}
			}
		});
		btnDel.setFont(new Font("���� ���", Font.BOLD, 16));
		button_panel1.add(btnDel);
		
		JButton buttonDivide = new JButton("��");
		buttonDivide.setActionCommand("/");
		buttonDivide.addActionListener(new InputOperator());
		buttonDivide.setFont(new Font("���� ���", Font.PLAIN, 28));
		button_panel1.add(buttonDivide);
		//--------------------------------------------------
		
		
		/* ���� �г� */
		//--------------------------------------------------
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(southPanel);
		southPanel.setLayout(new GridLayout(4, 4, 4, 4));
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new InputNumber());
		button_7.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new InputNumber());
		button_8.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new InputNumber());
		button_9.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_9);
		
		JButton button���ϱ� = new JButton("��");
		button���ϱ�.setActionCommand("x");
		button���ϱ�.addActionListener(new InputOperator());
		button���ϱ�.setFont(new Font("���� ���", Font.PLAIN, 28));
		southPanel.add(button���ϱ�);
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new InputNumber());
		button_4.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new InputNumber());
		button_5.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new InputNumber());
		button_6.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_6);
		
		JButton buttonMinus = new JButton("-");
		buttonMinus.setActionCommand("-");
		buttonMinus.addActionListener(new InputOperator());
		buttonMinus.setFont(new Font("���� ���", Font.PLAIN, 28));
		southPanel.add(buttonMinus);
		
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new InputNumber());
		button_1.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new InputNumber());
		button_2.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new InputNumber());
		button_3.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_3);
		
		JButton buttonPlus = new JButton("+");
		buttonPlus.setActionCommand("+");
		buttonPlus.addActionListener(new InputOperator());
		buttonPlus.setFont(new Font("���� ���", Font.PLAIN, 28));
		southPanel.add(buttonPlus);
		
		JButton button��ȣ���� = new JButton("��");
		button��ȣ����.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//��ȣ�� +�̸� -��ȣ ���̰�, -�̸� ��ȣ�� ����.
				if (txtResult.getText().startsWith("-") == false)
					txtResult.setText("-" + txtResult.getText());
				else
					txtResult.setText(txtResult.getText().replace("-", ""));
			}
		});
		button��ȣ����.setFont(new Font("���� ���", Font.PLAIN, 25));
		southPanel.add(button��ȣ����);
		
		JButton button_0 = new JButton("0");
		button_0.addActionListener(new InputNumber());
		button_0.setFont(new Font("���� ���", Font.BOLD, 24));
		southPanel.add(button_0);
		
		JButton button�Ҽ��� = new JButton(".");
		button�Ҽ���.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (txtResult.getText().indexOf(".") < 0)
					txtResult.setText(txtResult.getText() + ".");
				else {
					//�ƹ��͵� ���� �ʴ´�.
				}
			}
		});
		button�Ҽ���.setFont(new Font("���� ���", Font.BOLD, 28));
		southPanel.add(button�Ҽ���);
		
		JButton buttonEqual = new JButton("��");
		buttonEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//��� â�� �ִ� ���ڸ� �� â���� �ű�
				txtFormula.setText(txtFormula.getText() + txtResult.getText());
				double result = compute();
				String str_result;
				
				if (calculate_exception == false) {
					//������� �����̸� �Ҽ��� ���� ǥ��, �Ǽ��̸� �״�� ǥ���Ѵ�.
					if((result % 1.0) == 0)
						str_result = String.format("%,d", (long)result);
				    else
				    	str_result = String.format("%s", result);

					txtResult.setText(str_result);
				}
			}
		});
		buttonEqual.setFont(new Font("���� ���", Font.PLAIN, 28));
		southPanel.add(buttonEqual);
		//--------------------------------------------------
		
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	//--------------------------------------------------------------

	
	/* �̺�Ʈ Ŭ���� ����*/
	//--------------------------------------------------------------
	private class InputNumber implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton eventedButton = (JButton)e.getSource();
			String number = eventedButton.getText();
			
			//���â�� �ʱⰪ 0 ���� ���������� ����� ä������
			if (txtResult.getText().equals("0")) {
				txtResult.setText("");
			}
			
			if (opEnable == true) {
				txtResult.setText("");
				opEnable = false;
			}
			
			txtResult.setText(txtResult.getText() + number);
		}
	}
	
	private class InputOperator implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String op = e.getActionCommand();
			opEnable = true;
			
			String inputNumber = txtResult.getText();	//���â�� ���� ����
			
			txtFormula.setText(txtFormula.getText() + inputNumber + " " + op + " ");
		}
	}
	//--------------------------------------------------------------
}
