package code.xiaodeng.games.tetris.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import code.xiaodeng.games.tetris.object.Piece;
import code.xiaodeng.games.tetris.object.Square;

public class Piece5 extends Piece {

	public Piece5(Image image) {
		// ��һ�ֱ仯��_--
		List<Square> squares0 = new ArrayList<Square>();
		squares0.add(new Square(image, 0, 0));
		squares0.add(new Square(image, image.getWidth(null), 0));
		squares0.add(new Square(image, image.getWidth(null), image
				.getHeight(null)));
		squares0.add(new Square(image, image.getWidth(null) * 2, image
				.getHeight(null)));

		// �ڶ��ֱ仯����
		List<Square> squares1 = new ArrayList<Square>();
		squares1.add(new Square(image, image.getWidth(null), 0));
		squares1.add(new Square(image, image.getWidth(null), image
				.getHeight(null)));
		squares1.add(new Square(image, 0, image.getHeight(null)));
		squares1.add(new Square(image, 0, image.getHeight(null) * 2));

		super.changes.add(squares0);
		super.changes.add(squares1);
		super.setSquares(getDefault());
	}// constructor
}// end of class
