package code.xiaodeng.games.tetris.nativaData;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import code.xiaodeng.games.tetris.ui.MainFrame;
import code.xiaodeng.games.tetris.util.ImageUtil;

public class Rule extends JFrame {
	private static final long serialVersionUID = 796873832294141242L;
	JPanel jpanel;
	JLabel jlabel;
	Image image;
	MainFrame mainFrame;

	public Rule(MainFrame mainFrame) {
		super("游戏规则");
		this.mainFrame=mainFrame;
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				"resource" + File.separator + "images" + File.separator
						+ "main_frame.jpg"));
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 10,
				Toolkit.getDefaultToolkit().getScreenSize().height * 13 / 100,
				Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit
						.getDefaultToolkit().getScreenSize().height * 9 / 16);
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - this
						.getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - this
						.getHeight()) / 2);
		setVisible(true);
		setResizable(false);
		jlabel = new JLabel("  游戏规则");
		jlabel.setFont(new Font("Serif", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);

		jpanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			// 加载背景
			protected void paintComponent(Graphics g) {
				Image img;
				img = ImageUtil.getImage("resource" + File.separator + "images"
						+ File.separator + "bg_image_rule.jpg");
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		start();
	}

	void start() {
		jpanel.setLayout(null);
		jpanel.add(jlabel);
		jlabel.setBounds((int) (this.getWidth() / 3.4), this.getHeight() / 5,
				this.getWidth() / 2, this.getHeight() / 14);
		jlabel.setBackground(Color.ORANGE);
		jlabel.setFont(new Font("Serif", Font.BOLD, 27));
		JTextArea text = new JTextArea(5, 20);
		text.setLineWrap(true);
		text.setEditable(false);
		String message = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			fis = new FileInputStream("resource" + File.separator
					+ "properties" + File.separator + "rules.txt");// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
											// InputStreamReader的对象
			while ((str = br.readLine()) != null) {
				message += str + "\n";
			}
			br.close();
			isr.close();
			fis.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		text.append(message);
		text.append(" \n");
		JScrollPane s = new JScrollPane(text);
		jpanel.add(s);
		jpanel.add(jlabel);
		text.setWrapStyleWord(true);
		jlabel.setBounds((int) (this.getWidth() / 2.4),
				(int) (this.getHeight() / 7), this.getWidth() / 2,
				this.getHeight() / 13);
		s.setBounds(this.getWidth() / 8, (int) (this.getHeight() / 4),
				this.getWidth() * 3 / 4, (int) (this.getHeight() * 2.5 / 5));
		this.add(jpanel);
		s.setVisible(true);
		this.setVisible(true);
	}

}
