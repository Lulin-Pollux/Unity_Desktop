package gui;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

class MenuActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//case�� �޴��������� ���� �� �ȿ� �޴��������� �� ���� �ִ´�.
		switch (command) {
		
		case "�ػ� ����":
			new Resolution_Change();
			break;
			
		case "������ ����":
			FileDialog dialog = new FileDialog(MainFrame.frame, "�̹��� ���� ����", FileDialog.LOAD);
			dialog.setFile("*.png;*.jpg;*.bmp");
			dialog.setVisible(true);
			
			if (dialog.getFile() != null) {
				ImportWallpaper.wallpaper_path = dialog.getDirectory() + dialog.getFile();
				MainFrame.desktop_panel.repaint();
			}
			break;
			
		case "������":
			int result = JOptionPane.showConfirmDialog
			(null, "���� �Ͻðڽ��ϱ�?", "���α׷� ����", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
			
		case "Unity Desktop ����":
			new DesktopInfoDialog();
			break;
		}
	}
	
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
