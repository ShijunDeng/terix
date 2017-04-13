package code.xiaodeng.games.tetris.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

import code.xiaodeng.games.tetris.object.Piece;
import code.xiaodeng.games.tetris.object.Square;
import code.xiaodeng.games.tetris.util.ImageUtil;

class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	MainFrame mainframe;
	private Image bankgroundImage = ImageUtil.getImage("resource"
			+ File.separator + "images" + File.separator
			+ "backgroundImage.jpg");

	public GamePanel(MainFrame mainFrame) {
		this.mainframe = mainFrame;
		this.setPreferredSize(new Dimension());
	}

	public void paint(Graphics g) {
		g.drawImage(this.bankgroundImage, 0, 0, this.getWidth(),
				this.getHeight(), null);

		Piece currentPiece = this.mainframe.getCurrentPiece();
		ImageUtil.paintPiece(g, currentPiece);

		Square[][] squares = this.mainframe.getSquares();
		if (squares == null) {
			return;// 最开始还没有积累很多“方块”
		}

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				Square square = squares[i][j];
				if (square != null)// 很重要
					g.drawImage(square.getImage(), square.getBeginX(),
							square.getBeginY(), this);
			}
		}

	}

}// end of class
