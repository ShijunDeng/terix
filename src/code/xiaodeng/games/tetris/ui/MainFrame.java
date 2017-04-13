package code.xiaodeng.games.tetris.ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import code.xiaodeng.games.tetris.music.MusicPlayer;
import code.xiaodeng.games.tetris.nativaData.Rule;
import code.xiaodeng.games.tetris.object.Piece;
import code.xiaodeng.games.tetris.object.Square;
import code.xiaodeng.games.tetris.object.PieceCreatorImpl.PieceCreatorImpl;
import code.xiaodeng.games.tetris.util.ImageUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

/**
 * 
 * @author ���˾�
 * @time 2012-7
 * @version0.1
 * @function:��Ϸ������
 * 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();;
	private JMenu menu_game = new JMenu("��   Ϸ(G)");
	private JMenu menu_help = new JMenu("��   ��(H)");
	private JMenu menu_record = new JMenu("ͳ����Ϣ");
	private JMenu menu_game_set = new JMenu("��Ϸ�趨");
	private JMenuItem menuItem_game_exit = new JMenuItem("�˳�");
	private JMenuItem menuItem_help_about = new JMenuItem("������Ϸ");
	private JMenuItem menuItem_help_rule = new JMenuItem("�鿴����");
	ButtonGroup groupMusic = new ButtonGroup();
	ButtonGroup groupRecord = new ButtonGroup();
	private JRadioButtonMenuItem onMusic = new JRadioButtonMenuItem("��������");
	private JRadioButtonMenuItem offMusic = new JRadioButtonMenuItem("�ر�����");
	private JRadioButtonMenuItem lookRecord = new JRadioButtonMenuItem("�鿴��¼");
	private JRadioButtonMenuItem clearRecord = new JRadioButtonMenuItem("ɾ����¼");
	// ����
	private MusicPlayer musicPlayer;
	AudioClip sound_win_exit;
	AudioClip sound_gameover;
	AudioClip sound_clearRow;
	AudioClip click_01;
	private boolean sound_on = true;// �Ƿ�������
	// ����
	private JLabel levelTextLabel = new JLabel("��     ��");
	private JLabel levelLabel = new JLabel();// ��ʾ����
	private Box levelTextBox = Box.createHorizontalBox();
	private Box levelBox = Box.createHorizontalBox();

	// ����
	private JLabel scoreTextLabel = new JLabel("��    ��");// ��ʾ����
	private JLabel scoreLabel = new JLabel();
	private Box scoreTextBox = Box.createHorizontalBox();
	private Box scoreBox = Box.createHorizontalBox();

	// ��һ��
	private JLabel nextTextJLabel = new JLabel("�� һ ��");
	private Box nextTextBox = Box.createHorizontalBox();

	// ����
	private JLabel resumeJLabel = new JLabel();
	private Box resumeBox = Box.createHorizontalBox();

	// ��ͣ
	private JLabel pauseJLabel = new JLabel();
	private Box pauseBox = Box.createHorizontalBox();

	// ��ʼ
	private JLabel startJLabel = new JLabel();
	private Box startBox = Box.createHorizontalBox();

	// ����
	private int score = 0;
	// ����
	private int currentLevel = 1;
	//
	private JPanel mainFrameJPanel = new JPanel();

	private JPanel toolPanel = new JPanel();
	private Box blankBox = Box.createHorizontalBox();

	private Square[][] squares;
	TetrisTask tetrisTask;
	private GamePanel gamePanel;
	private PieceCreatorImpl pieceCreator = new PieceCreatorImpl();
	private Piece currentPiece, nextPiece;
	private Timer timer;
	// ��ͣ�ı�ʶ, trueΪ��ͣ
	private boolean pauseFlag = false;

	@SuppressWarnings("deprecation")
	public MainFrame() {
		menu_game.add(menu_record);
		menu_game.add(menu_game_set);
		menu_game.addSeparator();
		menu_game.add(menuItem_game_exit);
		menu_help.add(menuItem_help_rule);
		menu_help.add(menuItem_help_about);
		menuBar.add(menu_game);
		menuBar.add(menu_help);
		groupMusic.add(onMusic);
		groupMusic.add(offMusic);
		groupRecord.add(lookRecord);
		lookRecord.setSelected(true);
		groupRecord.add(clearRecord);
		menu_record.add(lookRecord);
		menu_record.add(clearRecord);
		menu_game_set.add(onMusic);
		menu_game_set.add(offMusic);
		offMusic.setSelected(true);
		this.setJMenuBar(menuBar);

		BoxLayout toolPanelLayout = new BoxLayout(this.toolPanel,
				BoxLayout.Y_AXIS);
		this.toolPanel.setLayout(toolPanelLayout);
		this.toolPanel.setBorder(new EtchedBorder());
		this.toolPanel.setBackground(Color.GRAY);

		// ����
		this.scoreTextBox.add(this.scoreTextLabel);
		this.scoreLabel.setText(String.valueOf(this.score));
		this.scoreBox.add(scoreLabel);

		// ����
		this.levelTextBox.add(this.levelTextLabel);
		this.levelLabel.setText(String.valueOf(this.currentLevel));
		this.levelBox.add(this.levelLabel);
		// ����
		this.resumeJLabel.setIcon(RESUME_ON_ICON);
		this.resumeJLabel.setPreferredSize(new Dimension(3, 25));
		this.resumeBox.add(this.resumeJLabel);
		// ��ʼ
		this.startJLabel.setIcon(START_ON_ICON);
		this.startJLabel.setPreferredSize(new Dimension(3, 25));
		this.startBox.add(this.startJLabel);
		// ��ͣ
		this.pauseJLabel.setIcon(PAUSE_ON_ICON);
		this.pauseJLabel.setPreferredSize(new Dimension(3, 25));
		this.pauseBox.add(this.pauseJLabel);
		// ��һ��
		this.nextTextBox.add(this.nextTextJLabel);

		this.toolPanel.add(Box.createVerticalStrut(10));
		this.toolPanel.add(this.scoreTextBox);
		this.toolPanel.add(Box.createVerticalStrut(10));
		this.toolPanel.add(this.scoreBox);
		this.toolPanel.add(Box.createVerticalStrut(10));
		this.toolPanel.add(this.levelTextBox);
		this.toolPanel.add(Box.createVerticalStrut(10));
		this.toolPanel.add(this.levelBox);
		this.toolPanel.add(Box.createVerticalStrut(20));
		this.toolPanel.add(this.resumeBox);
		this.toolPanel.add(Box.createVerticalStrut(20));
		this.toolPanel.add(this.pauseBox);
		this.toolPanel.add(Box.createVerticalStrut(20));
		this.toolPanel.add(this.startBox);
		this.toolPanel.add(Box.createVerticalStrut(20));
		this.toolPanel.add(this.nextTextBox);

		this.blankBox.add(Box.createVerticalStrut(99));
		this.toolPanel.add(blankBox);

		this.setIconImage(MainFrame.toolkit
				.getImage("resource" + File.separator + "images"
						+ File.separator + "main_frame.jpg"));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		this.gamePanel = new GamePanel(this);
		this.setSize(448, 640);
		this.setLocation(250, 60);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("tetris-xiaodeng");

		this.add(mainFrameJPanel);

		mainFrameJPanel.setLayout(null);
		mainFrameJPanel.add(this.gamePanel);
		this.gamePanel.setBounds(0, 0, this.getWidth() * 5 / 7,
				this.getHeight());
		gamePanel.setVisible(true);
		mainFrameJPanel.add(this.toolPanel);
		this.toolPanel.setBounds(this.getWidth() * 5 / 7, 0,
				this.getWidth() * 2 / 7, this.getHeight());
		toolPanel.setVisible(true);
		setVisible(true);

		// ��������
		try {
			URL url = new File("resource" + File.separator + "sounds"
					+ File.separator + "bg_music01.wav").toURI().toURL();
			musicPlayer = new MusicPlayer(url);
			this.sound_win_exit = Applet.newAudioClip((new File("resource"
					+ File.separator + "sounds" + File.separator + "exit.wav"))
					.toURL());
			this.sound_clearRow = Applet.newAudioClip((new File("resource"
					+ File.separator + "sounds" + File.separator
					+ "clearRow.wav")).toURL());
			this.sound_gameover = Applet.newAudioClip((new File("resource"
					+ File.separator + "sounds" + File.separator
					+ "gameover.wav")).toURL());
			this.click_01 = Applet.newAudioClip((new File("resource"
					+ File.separator + "sounds" + File.separator
					+ "CLICK_01.wav")).toURL());
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(this, "��Ϸ������ʧ�ܣ�" + e.getMessage()
					+ "!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
		initListeners();

	}// ���캯��

	private void initListeners() {

		this.resumeJLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				resumeJLabel.setIcon(RESUME_OFF_ICON);
				if (sound_on) {
					click_01.play();
				}
			}

			public void mouseExited(MouseEvent e) {
				resumeJLabel.setIcon(RESUME_ON_ICON);
			}

			public void mouseClicked(MouseEvent e) {
				resume();
			}
		});

		this.startJLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startJLabel.setIcon(START_OFF_ICON);
				if (sound_on) {
					click_01.play();
				}
			}

			public void mouseExited(MouseEvent e) {
				startJLabel.setIcon(START_ON_ICON);
			}

			public void mouseClicked(MouseEvent e) {
				if (sound_on) {
					musicPlayer.stop();
					musicPlayer.play();
				}
				start();
			}

		});
		this.pauseJLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				pauseJLabel.setIcon(PAUSE_OFF_ICON);
				if (sound_on) {
					click_01.play();
				}

			}

			public void mouseExited(MouseEvent e) {
				pauseJLabel.setIcon(PAUSE_ON_ICON);
			}

			public void mouseClicked(MouseEvent e) {
				pause();
			}

		});

		onMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound_on = true;
				musicPlayer.stop();// �ڲ��ž�Ҫ�ر�
				musicPlayer.play();
			}
		});// onMusic

		offMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound_on = false;
				musicPlayer.stop();
			}
		});// offMusic

		menuItem_help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this,
						"�汾1.0 tetris\n��С�˿���  ���˱�������Ȩ��\n��ӭ��ҶԱ���Ϸ�������", "������Ϸ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		menuItem_game_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
				if (JOptionPane.showConfirmDialog(MainFrame.this, "��ȷ���˳���",
						"��ʾ", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					if (sound_on && sound_win_exit != null) {
						musicPlayer.stop();
						sound_win_exit.play();
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					System.exit(0);
				}
				resume();
			}
		});
		menuItem_help_rule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Rule(MainFrame.this);
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					leftMove(1);
					break;
				case KeyEvent.VK_RIGHT:
					rightMove(1);
					break;
				case KeyEvent.VK_DOWN:
					down();
					break;
				case KeyEvent.VK_UP:
					change();
					break;
				}
			}

		});
	}// initListeners()

	public void down() {
		if (this.pauseFlag || this.currentPiece == null || isBlock()
				|| isButtom()) {
			return;
		}
		this.currentPiece.setSquaresYLocation(Piece.SQUARE_BORDER);
		showNext();
		this.gamePanel.repaint();
	}

	public void leftMove(int size) {
		if (this.pauseFlag || this.currentPiece == null || isLeftBlock()
				|| this.currentPiece.getMinXLocation() <= 0) {
			return;
		}
		this.currentPiece.setSquaresXLocation(-size * Piece.SQUARE_BORDER);
		this.gamePanel.repaint();
	}

	public void rightMove(int size) {
		if (this.pauseFlag
				|| this.currentPiece == null
				|| isRightBlock()
				|| this.currentPiece.getMaxXLocation() + Piece.SQUARE_BORDER >= this.gamePanel
						.getWidth()) {
			return;
		}
		this.currentPiece.setSquaresXLocation(size * Piece.SQUARE_BORDER);
		this.gamePanel.repaint();
	}

	public void addScore() {
		this.score += 10;
		this.scoreLabel.setText(String.valueOf(score));
		// ������Ա�100����, ���һ��
		if ((this.score % 100) == 0) {
			this.currentLevel += 1;
			this.levelLabel.setText(String.valueOf(this.currentLevel));
			// �������ö�ʱ��
			this.timer.cancel();
			this.timer = new Timer();
			this.tetrisTask = new TetrisTask(this);
			int time = 1000 / this.currentLevel;
			timer.schedule(this.tetrisTask, 0, time);
		}
	}

	// һ����������½�
	private void finishDown() {

		this.currentPiece = this.nextPiece;
		/*
		 * ���������꣺��Ϊthis.currentPiece =
		 * this.nextPiece;ֱ�ӽ��ұߵ�Ԥ�����󸳸�currentPieceҲ�������ڵ�currentPiece���ұ�Ԥ��ͼ���Ǹ�
		 * ����Ҫ�����ƶ�������������ʹ�ó�����ʽ����Ϊ�������ܸպý������Ƶ���ά������
		 */
		this.currentPiece.setSquaresXLocation(-NEXT_X);// ����
		this.currentPiece.setSquaresXLocation(BEGIN_X);// ����
		this.currentPiece.setSquaresYLocation(-NEXT_Y);
		this.currentPiece.setSquaresYLocation(BEGIN_Y);

		createNextPiece();
	}

	private void appendToSquares() {
		for (Square square : this.getCurrentPiece().getSquares()) {
			for (int i = 0; i < this.squares.length; i++)
				for (int j = 0; j < squares[i].length; j++) {
					if (square.equals(this.squares[i][j]))
						this.squares[i][j] = square;
				}
		}
	}

	// ����������������Square��"�½�", ����Ϊ��ɾ�����е���������
	private boolean handleDown(List<Integer> rowIndexs) {
		if (rowIndexs.size() == 0)
			return false;
		int minCleanRow = rowIndexs.get(0);
		int cleanRowSize = rowIndexs.size();
		for (int j = this.squares[0].length - 1; j >= 0; j--) {
			if (j < minCleanRow) {
				for (int i = 0; i < this.squares.length; i++) {
					Square square = this.squares[i][j];
					if (square.getImage() != null) {
						Image image = square.getImage();
						square.setImage(null);
						this.squares[i][j + cleanRowSize].setImage(image);
					}
				}
			}
		}

		return true;

	}

	private boolean cleanRows() {
		List<Integer> rowIndex = new ArrayList<Integer>();
		for (int j = 0; j < this.squares[0].length; j++) {
			int count = 0;
			for (int i = 0; i < this.squares.length
					&& this.squares[i][j].getImage() != null; i++) {
				count++;
			}
			if (count == this.squares.length) {
				rowIndex.add(j);
				for (int i = 0; i < this.squares.length; i++) {
					Square square = this.squares[i][j];
					square.setImage(null);// �������Ѿ����,����������Ҳ������
				}
				addScore();
			}
		}
		return handleDown(rowIndex);
	}

	public void showNext() {
		if (isBlock() || isButtom()) {
			appendToSquares();
			if (isLost()) {
				this.repaint();
				this.timer.cancel();
				this.currentPiece = null;

				JOptionPane.showMessageDialog(this, "��Ϸʧ��!", "��Ϸ���",
						JOptionPane.INFORMATION_MESSAGE);
				if (sound_on && sound_win_exit != null) {
					musicPlayer.stop();
					sound_gameover.play();
					try {
						Thread.sleep(800);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				return;
			}
			// ������
			boolean haveCleared = cleanRows();// �Ƿ���ȥ��
			finishDown();
			try {
				Thread.sleep((long) (800/Math.pow(this.currentLevel, 0.25)));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (haveCleared) {
				sound_clearRow.play();
				try {
					Thread.sleep(600);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void pause() {
		this.pauseFlag = true;
		if (this.timer != null)
			this.timer.cancel();
		this.timer = null;
	}

	private void resume() {
		if (!this.pauseFlag)
			return;
		this.timer = new Timer();
		this.tetrisTask = new TetrisTask(this);
		int time = 1000 / this.currentLevel;
		timer.schedule(this.tetrisTask, 0, time);
		this.pauseFlag = false;
	}

	private void change() {
		if (this.pauseFlag || this.currentPiece == null)
			return;
		this.currentPiece.change();
		// ����ת����Խ�������

		int minX = this.currentPiece.getMinXLocation();
		if (minX < 0) {// ���Խ��
			this.currentPiece.setSquaresXLocation(-minX);
		}
		int maxX = this.currentPiece.getMaxXLocation();
		if (maxX > this.gamePanel.getWidth()) {// ���Խ��
			this.currentPiece.setSquaresXLocation(this.gamePanel.getWidth()
					- maxX);
		}
		this.gamePanel.repaint();
	}

	// ����з���
	public boolean isLeftBlock() {
		List<Square> squares = this.getCurrentPiece().getSquares();
		for (Square square : squares) {
			if (this.getSquare(square.getBeginX() - Piece.SQUARE_BORDER,
					square.getBeginY()) != null)
				return true;
		}
		return false;
	}

	// �ұ��з���
	public boolean isRightBlock() {
		List<Square> squares = this.getCurrentPiece().getSquares();
		for (Square square : squares) {
			if (this.getSquare(square.getBeginX() + Piece.SQUARE_BORDER,
					square.getBeginY()) != null)
				return true;
		}
		return false;
	}

	// �Ƿ񵽴�ײ�
	public boolean isButtom() {
		if (this.currentPiece == null)
			return false;
		return this.currentPiece.getMaxYLocation() + Piece.SQUARE_BORDER * 3 >= this.gamePanel
				.getHeight();
	}

	// ���½��������Ƿ������ϰ�:true������false��û������
	public boolean isBlock() {
		if (this.getCurrentPiece() != null) {
			List<Square> squares = this.getCurrentPiece().getSquares();
			if (squares != null)
				for (Square square : squares) {
					if (this.getSquare(square.getBeginX(), square.getBeginY()
							+ Piece.SQUARE_BORDER) != null)
						return true;
				}
		}
		return false;

	}

	// �ж��Ƿ�ʧ��, trueΪʧ��, false��֮:���������ļ��ɣ�������߼������
	private boolean isLost() {
		for (Square[] squares : getSquares()) {
			if (squares[0].getImage() != null)
				return true;
		}
		return false;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public Square getSquare(int beginX, int beginY) {
		for (Square[] squares : this.squares) {
			for (Square square : squares) {
				if ((square.getImage() != null)
						&& (square.getBeginX() == beginX)
						&& (square.getBeginY() == beginY))
					return square;
			}
		}
		return null;
	}

	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public Piece getNextPiece() {
		return nextPiece;
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	private void createNextPiece() {
		this.nextPiece = this.pieceCreator.createPiece(NEXT_X, NEXT_Y);
		this.repaint();
	}

	public void initSquares() {
		int x_size = this.gamePanel.getWidth() / Piece.SQUARE_BORDER;
		int y_size = this.gamePanel.getHeight() / Piece.SQUARE_BORDER;
		this.squares = new Square[x_size][y_size];
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				this.squares[i][j] = new Square(Piece.SQUARE_BORDER * i,
						Piece.SQUARE_BORDER * j);
			}
		}

	}

	public void paint(Graphics g) {
		super.paint(g);
		if (this.nextPiece == null)
			return;
		ImageUtil.paintPiece(g, nextPiece);

	}

	private void start() {
		initSquares();
		if (this.timer != null) {
			this.timer.cancel();
		}
		createNextPiece();
		this.currentPiece = pieceCreator.createPiece(BEGIN_X, BEGIN_Y);
		this.timer = new Timer();
		// ��ʼ����ʱ��
		this.tetrisTask = new TetrisTask(this);
		int time = 1000 / this.currentLevel;
		this.timer.schedule(this.tetrisTask, 0, time);
		this.pauseFlag = false;
		this.currentLevel = 1;
		this.score = 0;
		this.scoreLabel.setText(String.valueOf(this.score));
	}

	private static final int NEXT_X = 360;
	private static final int NEXT_Y = 520;
	private static final int BEGIN_X = Piece.SQUARE_BORDER * 8;
	private static final int BEGIN_Y = -Piece.SQUARE_BORDER * 2;
	static final Toolkit toolkit = Toolkit.getDefaultToolkit();
	// ��꾭����ͣ��ťʽ��ʾ�İ�ť
	private static final ImageIcon PAUSE_ON_ICON = new ImageIcon("resource"
			+ File.separator + "images" + File.separator
			+ "button_pause_on.jpg");
	private static final ImageIcon PAUSE_OFF_ICON = new ImageIcon("resource"
			+ File.separator + "images" + File.separator
			+ "button_pause_off.jpg");
	private static final ImageIcon START_ON_ICON = new ImageIcon("resource"
			+ File.separator + "images" + File.separator
			+ "button_start_on.jpg");
	private static final ImageIcon START_OFF_ICON = new ImageIcon("resource"
			+ File.separator + "images" + File.separator
			+ "button_start_off.jpg");
	private static final ImageIcon RESUME_ON_ICON = new ImageIcon("resource"
			+ File.separator + "images" + File.separator
			+ "button_resume_on.jpg");
	private static final ImageIcon RESUME_OFF_ICON = new ImageIcon("resource"
			+ File.separator + "images" + File.separator
			+ "button_resume_off.jpg");
}// MainFrame

class TetrisTask extends TimerTask {
	// ���������
	private MainFrame mainFrame;

	public TetrisTask(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void run() {
		// �õ���ǰ�����˶��Ĵ󷽿�
		Piece currentPiece = this.mainFrame.getCurrentPiece();
		// �жϿ����½����Ƿ����ϰ����ߵ��ײ�
		if (currentPiece != null) {
			if (this.mainFrame.isBlock() || this.mainFrame.isButtom()) {
				this.mainFrame.showNext();
				return;
			}

			currentPiece.setSquaresYLocation(Piece.SQUARE_BORDER);
		}
		this.mainFrame.getGamePanel().repaint();
	}
}