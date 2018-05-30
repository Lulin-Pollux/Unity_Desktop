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
		/* Dialog 세팅 */
		//-----------------------------------------------------------
		setTitle("외부 앱 실행기");
		setSize(415,321);
        setResizable(false);
        setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		//-----------------------------------------------------------
        
		getContentPane().setLayout(new BorderLayout());
		
		
		/* Center 지역 컴포넌트 목록*/
		//--------------------------------------------------------
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(3, 1, 0, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel_mode = new JPanel();
		panel_mode.setBorder(new TitledBorder(null, "앱 실행 모드", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_mode.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 5));
		contentPanel.add(panel_mode);
		
		ButtonGroup group = new ButtonGroup();
			rdbtnCui = new JRadioButton("CUI (*.*)");
			rdbtnCui.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			panel_mode.add(rdbtnCui);
			
			rdbtnGui = new JRadioButton("GUI (*.exe)");
			rdbtnGui.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			panel_mode.add(rdbtnGui);
		group.add(rdbtnCui);
		group.add(rdbtnGui);
		
		JPanel panel_path = new JPanel();
		panel_path.setBorder(new TitledBorder(null, "앱 경로", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_path);
		panel_path.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		
		txtPath = new JTextField();
		txtPath.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		panel_path.add(txtPath);
		txtPath.setColumns(21);
		
		JButton btnPathFinder = new JButton("");
		btnPathFinder.addActionListener(new ButtonActionListener());
		btnPathFinder.setActionCommand("경로 찾기");
		btnPathFinder.setToolTipText("경로 찾기");
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("folder_icon.png"));
		Image changedimg = img.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnPathFinder.setIcon(new ImageIcon(changedimg));
		btnPathFinder.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		panel_path.add(btnPathFinder);
		
		JPanel panel_parameter = new JPanel();
		panel_parameter.setBorder(new TitledBorder(null, "매개변수 (선택적)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_parameter);
		panel_parameter.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		
		txtParameter = new JTextField();
		txtParameter.setToolTipText("앱 실행시 매개변수가 필요하면 입력하세요.");
		txtParameter.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		panel_parameter.add(txtParameter);
		txtParameter.setColumns(24);
		//--------------------------------------------------------
		
		
		/* South 지역 컴포넌트 목록 */
		//--------------------------------------------------------
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("실행");
		okButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		okButton.setActionCommand("실행");
		okButton.addActionListener(new ButtonActionListener());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("취소");
		cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		cancelButton.setActionCommand("취소");
		cancelButton.addActionListener(new ButtonActionListener());
		buttonPane.add(cancelButton);
		//--------------------------------------------------------
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
	/* 이벤트 클래스 모음 */
	//---------------------------------------------------------------------
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String command = arg0.getActionCommand();
			
			switch (command) {
			
			case "경로 찾기":
				FileDialog dialog = new FileDialog(MainFrame.frame, "앱 실행파일 열기", FileDialog.LOAD);
				dialog.setVisible(true);
				
				if (dialog.getFile() != null)
					txtPath.setText(dialog.getDirectory() + dialog.getFile());
				break;
				
			case "실행":
				String path;
				
				//만약 라디오 버튼 둘다 선택 안했으면 실행 중단
				if ((rdbtnCui.isSelected() == false) && (rdbtnGui.isSelected() == false)) {
					String message = "앱 실행 모드를 선택해주세요!";
					JOptionPane.showMessageDialog(null, message, "경고", JOptionPane.WARNING_MESSAGE);
					break;
				}
				//만약 앱 경로가 비어있으면 실행 중단
				else if (txtPath.getText().equals("")) {
					String message = "실행할 앱을 지정해 주세요!";
					JOptionPane.showMessageDialog(null, message, "경고", JOptionPane.WARNING_MESSAGE);
					break;
				}
				
				//만약 CUI를 체크했으면 아래 문장을 추가하고 아니라면 추가안함.
				if (rdbtnCui.isSelected() == true)
					path = "cmd.exe /c start cmd /c call \"" + txtPath.getText() + "\"";
				else
					path = "\"" + txtPath.getText() + "\"";
				
				//만약 매개변수가 있다면 path 뒤에 매개변수를 추가함.
				if (txtParameter.getText().equals("") == false)
					path = path.concat(" " + txtParameter.getText());
				
				//본격적인 실행시작
				try {
					Process process = Runtime.getRuntime().exec(path);
					process.getErrorStream().close();
					process.getInputStream().close();
					process.getOutputStream().close();
				} catch (IOException e) {
					String message = "파일 선택에 문제가 발생했습니다. \n";
					JOptionPane.showMessageDialog(null, message + e, "오류", JOptionPane.ERROR_MESSAGE);
					break;
				}
				dispose();
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
