package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class DesktopInfoDialog extends JDialog {
	private static final long serialVersionUID = -4939499895107162638L;
	
	private JPanel contentPanel = new JPanel();

	/* 이 클래스를 따로 실행할 수 있게 해주는 메소드 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new DesktopInfoDialog();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	DesktopInfoDialog() {
		/* Dialog 세팅 */
		//-----------------------------------------------------------
		setTitle("Unity Desktop 정보");
		setSize(220,120);
        setModal(true);
        setResizable(false);		//화면 크기 조정 불가
		setLocationRelativeTo(null);	//화면 중앙에 프레임 띄우기
		//-----------------------------------------------------------
		
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
        
        JLabel label1 = new JLabel("Unity Desktop v1.3.0");
        label1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        
        JLabel label2 = new JLabel("Made by Lulin Pollux");
        label2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        
        contentPanel.add(label1);
        contentPanel.add(label2);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
	}

}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
