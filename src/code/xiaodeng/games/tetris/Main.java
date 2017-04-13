package code.xiaodeng.games.tetris;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import code.xiaodeng.games.tetris.ui.MainFrame;

public class Main {
	public static void main(String[] args) {

		final TrayIcon trayIcon;
		if (!SystemTray.isSupported()) {
			System.out.println("系统托盘不支持！");
		} else {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(
					"resource" + File.separator + "images" + File.separator
							+ "systemTray.jpg");
			PopupMenu popupMenu = new PopupMenu();
			MenuItem setItem = new MenuItem("设置");
			MenuItem helpItem = new MenuItem("帮助");
			MenuItem aboutItem = new MenuItem("关于");
			MenuItem exitItem = new MenuItem("退出");

			aboutItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
							"版本1.0 tetris\n由小邓开发  本人保留所有权利\n欢迎大家对本游戏提出建议",
							"关于游戏", JOptionPane.INFORMATION_MESSAGE);
				}
			});

			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			popupMenu.add(setItem);
			popupMenu.add(helpItem);
			popupMenu.add(aboutItem);
			popupMenu.addSeparator();
			popupMenu.add(exitItem);
			trayIcon = new TrayIcon(image, "Tetris", popupMenu);
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}// end else if
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

}
