package code.xiaodeng.games.tetris.object.PieceCreatorImpl;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import code.xiaodeng.games.tetris.object.Piece;
import code.xiaodeng.games.tetris.object.PieceCreator;
import code.xiaodeng.games.tetris.piece.Piece0;
import code.xiaodeng.games.tetris.piece.Piece1;
import code.xiaodeng.games.tetris.piece.Piece2;
import code.xiaodeng.games.tetris.piece.Piece3;
import code.xiaodeng.games.tetris.piece.Piece4;
import code.xiaodeng.games.tetris.piece.Piece5;
import code.xiaodeng.games.tetris.piece.Piece6;
import code.xiaodeng.games.tetris.util.ImageUtil;

public class PieceCreatorImpl implements PieceCreator {

	static private final int AMOUNTOFSQUARE = 7;// 方块的数量
	static private final int AMOUNTOFCOLOR = 7;
	static private Map<Integer, Image> images = new HashMap<Integer, Image>();
	static {
		for (int i = 0; i < AMOUNTOFSQUARE ; i++) {
			images.put(
					i,
					ImageUtil.getImage("resource" + File.separator + "images"
							+ File.separator + "square" + i + ".jpg"));
		}
	}

	private Random random = new Random();

	public Piece createPiece(int x, int y) {

		Piece piece = initPiece(getImage(random.nextInt(AMOUNTOFCOLOR)));
		piece.setSquaresXLocation(x);
		piece.setSquaresYLocation(y);
		return piece;
	}

	public Piece getPiece() {

		return null;
	}

	private Image getImage(int key) {
		return images.get(key);
	}

	private Piece initPiece(Image image) {
		int pieceType = random.nextInt(AMOUNTOFSQUARE);
		switch (pieceType) {
		case 0:
			return new Piece0(images.get(new Integer(pieceType)));
		case 1:
			return new Piece1(images.get(new Integer(pieceType)));
		case 2:
			return new Piece2(images.get(new Integer(pieceType)));
		case 3:
			return new Piece3(images.get(new Integer(pieceType)));
		case 4:
			return new Piece4(images.get(new Integer(pieceType)));
		case 5:
			return new Piece5(images.get(new Integer(pieceType)));
		case 6:
			return new Piece6(images.get(new Integer(pieceType)));

		}
		return null;

	}

}
