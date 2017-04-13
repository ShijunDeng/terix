package code.xiaodeng.games.tetris.object;

import java.awt.*;

//Ð¡·½¿é
public class Square {

	protected Image image;
	protected int beginX;
	protected int beginY;

	public Square(Image image, int beginX, int beginY) {
		this.image = image;
		this.beginX = beginX;
		this.beginY = beginY;
	}

	public Square(int beginX, int beginY) {
		this.beginX = beginX;
		this.beginY = beginY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public boolean equals(Object object) {
		if (object instanceof Square) {
			Square s = (Square) object;
			return this.getBeginX() == s.getBeginX()
					&& this.getBeginY() == s.getBeginY();
		}
		return false;
	}

}// Square
