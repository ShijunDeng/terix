package code.xiaodeng.games.tetris.music;

import javax.media.*;
import java.io.*;
import java.net.URL;
/*@author:王朔
 *@time:2012-05-11
 * 背景音乐
 */
public class MusicPlayer implements ControllerListener {
	// 播放对象
	private Player player;
	private URL url;
	private boolean play = true;

	public MusicPlayer(URL url) {

		this.url = url;

	}

	// 播放方法
	public void play() {
		try {
			player = Manager.createPlayer(url);
		} catch (NoPlayerException e) {
			e.printStackTrace();
			System.out.println("不能播放此文件！");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if (player == null) {
			System.out.println("播放文件为空！");
			return;
		}
		player.addControllerListener(this);
		player.prefetch();
		if (play == true)
			player.start();
	}

	public void controllerUpdate(ControllerEvent e) {
		if (e instanceof EndOfMediaEvent) {
			this.play();
		}
	}

	public void stop() {
		if(player!=null&&this.play)
			player.stop();
	}

	public void setPlayer(boolean b) {
		this.play = b;
	}
}
