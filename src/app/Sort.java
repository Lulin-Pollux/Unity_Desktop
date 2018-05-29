package app;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Sort extends JInternalFrame {
	private static final long serialVersionUID = -5824032554481874133L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort frame = new Sort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sort() {
		setTitle("정렬 프로그램");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 450, 300);

	}

}
