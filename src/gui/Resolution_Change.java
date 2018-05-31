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
	
	/* 멤버 변수 목록 */
	//-----------------------------------------------------
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JCheckBox checkBox_ratio;
	//-----------------------------------------------------
	
	
	/* 이 클래스를 따로 실행할 수 있게 해주는 메소드 */
	public static void main(String[] args) {
		try {
			new Resolution_Change();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/* Dialog 생성 */
	Resolution_Change() {
		setAutoRequestFocus(false);
		/* Dialog 세팅 */
		//-----------------------------------------------------------
		setTitle("해상도 변경");
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
		panel.setBorder(new TitledBorder(null, "크기 조정", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		contentPanel.add(panel);
		
		JLabel lblWidth = new JLabel("가로: ");
		lblWidth.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
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
							JOptionPane.showMessageDialog(null, "가로에 정수를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
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
							JOptionPane.showMessageDialog(null, "가로에 정수를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
							txtWidth.setText("");
						}
					}
				}
			}
		});
		txtWidth.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		txtWidth.setColumns(8);
		panel.add(txtWidth);
		
		JLabel lblHeight = new JLabel("세로: ");
		lblHeight.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
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
							JOptionPane.showMessageDialog(null, "세로에 정수를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
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
							JOptionPane.showMessageDialog(null, "세로에 정수를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
							txtHeight.setText("");
						}
					}
				}
			}
		});
		txtHeight.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		txtHeight.setColumns(8);
		panel.add(txtHeight);
		
		checkBox_ratio = new JCheckBox("가로 세로 비율 유지", true);
		checkBox_ratio.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		panel.add(checkBox_ratio);
		//-----------------------------------------------------------
		
		
		/* South 지역 컴포넌트 목록 */
		//-----------------------------------------------------------
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("확인");
			okButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			okButton.setActionCommand("확인");
			okButton.addActionListener(new ButtonActionListener());
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("취소");
			cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			cancelButton.setActionCommand("취소");
			cancelButton.addActionListener(new ButtonActionListener());
			buttonPane.add(cancelButton);
		}
		//-----------------------------------------------------------
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	/* 이벤트 클래스 모음 */
	//---------------------------------------------------------------------
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			switch (command) {
			
			case "확인":
				if ((txtWidth.getText().equals("")) || (txtHeight.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "가로 또는 세로를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
					break;
				}
				try {
					int width = Integer.parseInt(txtWidth.getText());
					int height = Integer.parseInt(txtHeight.getText());
					MainFrame.frame.setSize(width, height);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "가로, 세로에 정수를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
					break;
				}
				break;
				
			case "취소":
				dispose();
				break;
			}
		}
	}
	//---------------------------------------------------------------------
}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
