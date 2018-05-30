package app;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import gui.MainFrame;

public class ExternalAppExecute extends JInternalFrame {
	private static final long serialVersionUID = 7425187512607635340L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPath;
	private JTextField txtParameter;
	private JRadioButton rdbtnCui;
	private JRadioButton rdbtnGui;

	
	public ExternalAppExecute() {
		/* Dialog ���� */
		//-----------------------------------------------------------
		setTitle("�ܺ� �� �����");
		setSize(415,321);
        setResizable(false);
        setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		//-----------------------------------------------------------
        
		getContentPane().setLayout(new BorderLayout());
		
		
		/* Center ���� ������Ʈ ���*/
		//--------------------------------------------------------
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(3, 1, 0, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel_mode = new JPanel();
		panel_mode.setBorder(new TitledBorder(null, "�� ���� ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_mode.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 5));
		contentPanel.add(panel_mode);
		
		ButtonGroup group = new ButtonGroup();
			rdbtnCui = new JRadioButton("CUI (*.*)");
			rdbtnCui.setFont(new Font("���� ���", Font.BOLD, 18));
			panel_mode.add(rdbtnCui);
			
			rdbtnGui = new JRadioButton("GUI (*.exe)");
			rdbtnGui.setFont(new Font("���� ���", Font.BOLD, 18));
			panel_mode.add(rdbtnGui);
		group.add(rdbtnCui);
		group.add(rdbtnGui);
		
		JPanel panel_path = new JPanel();
		panel_path.setBorder(new TitledBorder(null, "�� ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_path);
		panel_path.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		
		txtPath = new JTextField();
		txtPath.setFont(new Font("���� ���", Font.PLAIN, 15));
		panel_path.add(txtPath);
		txtPath.setColumns(21);
		
		JButton btnPathFinder = new JButton("");
		btnPathFinder.addActionListener(new ButtonActionListener());
		btnPathFinder.setActionCommand("��� ã��");
		btnPathFinder.setToolTipText("��� ã��");
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("folder_icon.png"));
		Image changedimg = img.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnPathFinder.setIcon(new ImageIcon(changedimg));
		btnPathFinder.setFont(new Font("���� ���", Font.PLAIN, 17));
		panel_path.add(btnPathFinder);
		
		JPanel panel_parameter = new JPanel();
		panel_parameter.setBorder(new TitledBorder(null, "�Ű����� (������)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_parameter);
		panel_parameter.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		
		txtParameter = new JTextField();
		txtParameter.setToolTipText("�� ����� �Ű������� �ʿ��ϸ� �Է��ϼ���.");
		txtParameter.setFont(new Font("���� ���", Font.PLAIN, 17));
		panel_parameter.add(txtParameter);
		txtParameter.setColumns(24);
		//--------------------------------------------------------
		
		
		/* South ���� ������Ʈ ��� */
		//--------------------------------------------------------
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("����");
		okButton.setFont(new Font("���� ���", Font.PLAIN, 13));
		okButton.setActionCommand("����");
		okButton.addActionListener(new ButtonActionListener());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("���");
		cancelButton.setFont(new Font("���� ���", Font.PLAIN, 13));
		cancelButton.setActionCommand("���");
		cancelButton.addActionListener(new ButtonActionListener());
		buttonPane.add(cancelButton);
		//--------------------------------------------------------
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
	/* �̺�Ʈ Ŭ���� ���� */
	//---------------------------------------------------------------------
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String command = arg0.getActionCommand();
			
			switch (command) {
			
			case "��� ã��":
				FileDialog dialog = new FileDialog(MainFrame.frame, "�� �������� ����", FileDialog.LOAD);
				dialog.setVisible(true);
				
				if (dialog.getFile() != null)
					txtPath.setText(dialog.getDirectory() + dialog.getFile());
				break;
				
			case "����":
				String path;
				
				//���� ���� ��ư �Ѵ� ���� �������� ���� �ߴ�
				if ((rdbtnCui.isSelected() == false) && (rdbtnGui.isSelected() == false)) {
					String message = "�� ���� ��带 �������ּ���!";
					JOptionPane.showMessageDialog(null, message, "���", JOptionPane.WARNING_MESSAGE);
					break;
				}
				//���� �� ��ΰ� ��������� ���� �ߴ�
				else if (txtPath.getText().equals("")) {
					String message = "������ ���� ������ �ּ���!";
					JOptionPane.showMessageDialog(null, message, "���", JOptionPane.WARNING_MESSAGE);
					break;
				}
				
				//���� CUI�� üũ������ �Ʒ� ������ �߰��ϰ� �ƴ϶�� �߰�����.
				if (rdbtnCui.isSelected() == true)
					path = "cmd.exe /c start cmd /c call \"" + txtPath.getText() + "\"";
				else
					path = "\"" + txtPath.getText() + "\"";
				
				//���� �Ű������� �ִٸ� path �ڿ� �Ű������� �߰���.
				if (txtParameter.getText().equals("") == false)
					path = path.concat(" " + txtParameter.getText());
				
				//�������� �������
				try {
					Process process = Runtime.getRuntime().exec(path);
					process.getErrorStream().close();
					process.getInputStream().close();
					process.getOutputStream().close();
				} catch (IOException e) {
					String message = "���� ���ÿ� ������ �߻��߽��ϴ�. \n";
					JOptionPane.showMessageDialog(null, message + e, "����", JOptionPane.ERROR_MESSAGE);
					break;
				}
				dispose();
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
