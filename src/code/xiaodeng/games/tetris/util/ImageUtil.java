package code.xiaodeng.games.tetris.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import code.xiaodeng.games.tetris.exception.GameException;
import code.xiaodeng.games.tetris.object.Piece;
import code.xiaodeng.games.tetris.object.Square;

public class ImageUtil {

	public static BufferedImage getImage(String imagePath) {
		try {
			return ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "��ȡ����ͼƬʧ�ܣ�" + e.getMessage()
					+ "!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			throw new GameException("��ȡ����ͼƬʧ�ܣ�" + e.getMessage());

		}
	}//getImage
	
	public static void paintPiece(Graphics g,Piece piece){
		if(piece==null)return ;	
		for(Square square:piece.getSquares()){
			g.drawImage(square.getImage(),square.getBeginX(),square.getBeginY(),null);
		}
	}
	
	
}
