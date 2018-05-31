package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

class Resolution_Change extends JDialog {
	private static final long serialVersionUID = 3898963747207781514L;
	
	/* ��� ���� ��� */
	//-----------------------------------------------------
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JCheckBox checkBox_ratio;
	//-----------------------------------------------------
	
	
	/* �� Ŭ������ ���� ������ �� �ְ� ���ִ� �޼ҵ� */
	public static void main(String[] args) {
		try {
			new Resolution_Change();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/* Dialog ���� */
	Resolution_Change() {
		setAutoRequestFocus(false);
		/* Dialog ���� */
		//-----------------------------------------------------------
		setTitle("�ػ� ����");
		setSize(230, 226);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		//-----------------------------------------------------------
		
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 1, 10, 10));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "ũ�� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		contentPanel.add(panel);
		
		JLabel lblWidth = new JLabel("����: ");
		lblWidth.setFont(new Font("���� ���", Font.PLAIN, 15));
		panel.add(lblWidth);
		
		txtWidth = new JTextField("1280");
		txtWidth.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) == false)
					e.consume();
			}
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) == false)
					e.consume();
				else if (checkBox_ratio.isSelected() == true) {
					if (txtWidth.getText().equals("")) {
						txtHeight.setText("");
					}
					else {
						try {
							int width = Integer.parseInt(txtWidth.getText());
							String height = String.valueOf(Math.round(width / 1.666667));
							txtHeight.setText(height);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "���ο� ������ �Է����ּ���!", "����", JOptionPane.ERROR_MESSAGE);
							txtWidth.setText("");
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && checkBox_ratio.isSelected() == true) {
					if (txtWidth.getText().equals("")) {
						txtHeight.setText("");
					}
					else {
						try {
							int width = Integer.parseInt(txtWidth.getText());
							String height = String.valueOf(Math.round(width / 1.666667));
							txtHeight.setText(height);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "���ο� ������ �Է����ּ���!", "����", JOptionPane.ERROR_MESSAGE);
							txtWidth.setText("");
						}
					}
				}
			}
		});
		txtWidth.setFont(new Font("���� ���", Font.PLAIN, 15));
		txtWidth.setColumns(8);
		panel.add(txtWidth);
		
		JLabel lblHeight = new JLabel("����: ");
		lblHeight.setFont(new Font("���� ���", Font.PLAIN, 15));
		panel.add(lblHeight);
		
		txtHeight = new JTextField("768");
		txtHeight.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) == false)
					e.consume();
			}
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) == false)
					e.consume();
				else if (checkBox_ratio.isSelected() == true) {
					if (txtHeight.getText().equals("")) {
						txtWidth.setText("");
					}
					else {
						try {
							int height = Integer.parseInt(txtHeight.getText());
							String width = String.valueOf(Math.round(height * 1.666667));
							txtWidth.setText(width);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "���ο� ������ �Է����ּ���!", "����", JOptionPane.ERROR_MESSAGE);
							txtHeight.setText("");
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && checkBox_ratio.isSelected() == true) {
					if (txtHeight.getText().equals("")) {
						txtWidth.setText("");
					}
					else {
						try {
							int height = Integer.parseInt(txtHeight.getText());
							String width = String.valueOf(Math.round(height * 1.666667));
							txtWidth.setText(width);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "���ο� ������ �Է����ּ���!", "����", JOptionPane.ERROR_MESSAGE);
							txtHeight.setText("");
						}
					}
				}
			}
		});
		txtHeight.setFont(new Font("���� ���", Font.PLAIN, 15));
		txtHeight.setColumns(8);
		panel.add(txtHeight);
		
		checkBox_ratio = new JCheckBox("���� ���� ���� ����", true);
		checkBox_ratio.setFont(new Font("���� ���", Font.PLAIN, 15));
		panel.add(checkBox_ratio);
		//-----------------------------------------------------------
		
		
		/* South ���� ������Ʈ ��� */
		//-----------------------------------------------------------
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("Ȯ��");
			okButton.setFont(new Font("���� ���", Font.PLAIN, 13));
			okButton.setActionCommand("Ȯ��");
			okButton.addActionListener(new ButtonActionListener());
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("���");
			cancelButton.setFont(new Font("���� ���", Font.PLAIN, 13));
			cancelButton.setActionCommand("���");
			cancelButton.addActionListener(new ButtonActionListener());
			buttonPane.add(cancelButton);
		}
		//-----------------------------------------------------------
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	/* �̺�Ʈ Ŭ���� ���� */
	//---------------------------------------------------------------------
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			switch (command) {
			
			case "Ȯ��":
				if ((txtWidth.getText().equals("")) || (txtHeight.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "���� �Ǵ� ���θ� �Է����ּ���!", "����", JOptionPane.ERROR_MESSAGE);
					break;
				}
				try {
					int width = Integer.parseInt(txtWidth.getText());
					int height = Integer.parseInt(txtHeight.getText());
					MainFrame.frame.setSize(width, height);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "����, ���ο� ������ �Է����ּ���!", "����", JOptionPane.ERROR_MESSAGE);
					break;
				}
				break;
				
			case "���":
				dispose();
				break;
			}
		}
	}
	//---------------------------------------------------------------------
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
