package code.xiaodeng.games.tetris.object;

import java.util.*;

//����С����ĸ���ͼ��
public class Piece {
	protected List<Square> squares;
	protected List<List<Square>> changes = new ArrayList<List<Square>>();

	Random random = new Random();
	private int currentIndex = 0;
	public static final int SQUARE_BORDER = 16;

	public List<Square> getSquares() {
		return this.squares;
	}

	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}

	public List<Square> getDefault() {
		this.currentIndex = random.nextInt(changes.size());
		return this.changes.get(this.currentIndex);
	}

	public void change() {
		if (this.changes.size() <= 0)
			return;
		this.currentIndex++;
		if (this.currentIndex >= this.changes.size())
			this.currentIndex = 0;
		this.squares = this.changes.get(this.currentIndex);
	}

	// �����x�����x
	public void setSquaresXLocation(int x) {
		for (List<Square> squares : this.changes) {
			for (Square square : squares) {
				square.setBeginX(square.getBeginX() + x);
			}
		}
	}

	// �����y�����y
	public void setSquaresYLocation(int y) {
		for (List<Square> squares : this.changes) {
			for (Square square : squares) {
				square.setBeginY(square.getBeginY() + y);
			}
		}
	}

	// �õ���ǰ�仯��x����Сֵ
	public int getMinXLocation() {
		int result = Integer.MAX_VALUE;
		for (Square square : this.squares) {
			if (square.getBeginX() < result)
				result = square.getBeginX();
		}
		return result;
	}

	// �õ���ǰ�仯��x�����ֵ
	public int getMaxXLocation() {
		int result = Integer.MIN_VALUE;
		for (Square square : this.squares) {
			if (square.getBeginX() > result)
				result = square.getBeginX();
		}
		return result;
	}

	// �õ���ǰ�仯��y����Сֵ
	public int getMinYLocation() {
		int result = Integer.MAX_VALUE;
		for (Square square : this.squares) {
			if (square.getBeginY() < result)
				result = square.getBeginY();
		}
		return result + SQUARE_BORDER;
	}

	// �õ���ǰ�仯��y�����ֵ
	public int getMaxYLocation() {
		int result = Integer.MIN_VALUE;
		for (Square square : this.squares) {
			if (square.getBeginY() > result)
				result = square.getBeginY();
		}
		return result + SQUARE_BORDER;
	}

}// Piece
