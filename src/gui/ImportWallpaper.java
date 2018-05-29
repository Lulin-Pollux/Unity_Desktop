package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImportWallpaper extends JPanel {
	private static final long serialVersionUID = -906959846881325067L;
	
	static String wallpaper_path = System.getProperty("user.dir") + "/wallpaper.jpg";	//배경사진 경로 저장
	
	protected void paintComponent(Graphics g) {
		Image img = new ImageIcon(wallpaper_path).getImage();;

		try {
			Graphics2D g2d = (Graphics2D)g;
			Dimension d = getSize();
			g2d.drawImage(img, 0, 0, d.width, d.height, null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
