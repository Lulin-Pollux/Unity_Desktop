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

	/* �� Ŭ������ ���� ������ �� �ְ� ���ִ� �޼ҵ� */
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
		/* Dialog ���� */
		//-----------------------------------------------------------
		setTitle("Unity Desktop ����");
		setSize(220,120);
        setModal(true);
        setResizable(false);		//ȭ�� ũ�� ���� �Ұ�
		setLocationRelativeTo(null);	//ȭ�� �߾ӿ� ������ ����
		//-----------------------------------------------------------
		
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
        
        JLabel label1 = new JLabel("Unity Desktop v1.3.0");
        label1.setFont(new Font("���� ���", Font.BOLD, 14));
        
        JLabel label2 = new JLabel("Made by Lulin Pollux");
        label2.setFont(new Font("���� ���", Font.BOLD, 14));
        
        contentPanel.add(label1);
        contentPanel.add(label2);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
	}

}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
