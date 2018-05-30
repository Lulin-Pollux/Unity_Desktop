package gui;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

class MenuActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//case에 메뉴아이템을 적고 그 안에 메뉴아이템이 할 일을 넣는다.
		switch (command) {
		
		case "해상도 변경":
			new Resolution_Change();
			break;
			
		case "배경사진 변경":
			FileDialog dialog = new FileDialog(MainFrame.frame, "이미지 파일 열기", FileDialog.LOAD);
			dialog.setFile("*.png;*.jpg;*.bmp");
			dialog.setVisible(true);
			
			if (dialog.getFile() != null) {
				ImportWallpaper.wallpaper_path = dialog.getDirectory() + dialog.getFile();
				MainFrame.desktop_panel.repaint();
			}
			break;
			
		case "끝내기":
			int result = JOptionPane.showConfirmDialog
			(null, "종료 하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
			
		case "Unity Desktop 정보":
			new DesktopInfoDialog();
			break;
		}
	}
	
}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
