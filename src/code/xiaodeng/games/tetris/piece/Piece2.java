package code.xiaodeng.games.tetris.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import code.xiaodeng.games.tetris.object.Piece;
import code.xiaodeng.games.tetris.object.Square;
//��L
public class Piece2 extends Piece {

	public Piece2(Image image) {
		// ��һ�ֱ仯����L
		List<Square> squares0 = new ArrayList<Square>();
		squares0.add(new Square(image, image.getWidth(null), 0));
		squares0.add(new Square(image, image.getWidth(null), image
				.getHeight(null)));
		squares0.add(new Square(image, image.getWidth(null), image
				.getHeight(null) * 2));
		squares0.add(new Square(image, image.getWidth(null) * 2, image
				.getHeight(null) * 2));

		// �ڶ��ֱ仯��|������������������������
		List<Square> squares1 = new ArrayList<Square>();
		squares1.add(new Square(image, 0, image.getHeight(null)));
		squares1.add(new Square(image, image.getWidth(null), image
				.getHeight(null)));
		squares1.add(new Square(image, image.getWidth(null) * 2, image
				.getHeight(null)));
		squares1.add(new Square(image, 0, image.getHeight(null) * 2));
		
		// �����ֱ仯��7
		List<Square> squares2 = new ArrayList<Square>();
		squares2.add(new Square(image, image.getWidth(null) , 0));
		squares2.add(new Square(image, image.getWidth(null), image
				.getHeight(null)));
		squares2.add(new Square(image, image.getWidth(null), image
				.getHeight(null) * 2));
		squares2.add(new Square(image,0, 0));
		
		// �����ֱ仯��______|
		List<Square> squares3 = new ArrayList<Square>();
		squares3.add(new Square(image, image.getWidth(null) * 2,0));
		squares3.add(new Square(image, 0, image.getHeight(null)));
		squares3.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		squares3.add(new Square(image, image.getWidth(null) * 2, image.getHeight(null)));

		super.changes.add(squares0);
		super.changes.add(squares1);
		super.changes.add(squares2);
		super.changes.add(squares3);
		super.setSquares(getDefault());
	}//constructor
}//end of class