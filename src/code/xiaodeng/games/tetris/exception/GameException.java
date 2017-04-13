package code.xiaodeng.games.tetris.exception;

public class GameException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public GameException(String infomation){
		super(infomation);
	}
}
