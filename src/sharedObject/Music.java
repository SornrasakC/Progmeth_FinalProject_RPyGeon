package sharedObject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music
{
	public static MediaPlayer currentBGM;
	
	public static MediaPlayer villageBGM;
	public static MediaPlayer battleBGM;
	
	static
	{
		villageBGM = new MediaPlayer(new Media(ClassLoader.getSystemResource("Village.mp3").toExternalForm()));
		villageBGM.setOnEndOfMedia(()->villageBGM.play());
		battleBGM = new MediaPlayer(new Media(ClassLoader.getSystemResource("Battle.mp3").toExternalForm()));
		battleBGM.setOnEndOfMedia(()->battleBGM.play());
		currentBGM = villageBGM;
	}
	
	public static void changeBGM(MediaPlayer song)
	{
		if(currentBGM.equals(song))
		{
			return;
		}
		currentBGM.stop();
		currentBGM = song;
		currentBGM.play();
	}
//	Media backgroundMusic = new Media(getClass().getClassLoader().getResource("FindTheWay.mp3").toExternalForm());
//	mediaPlayer = new MediaPlayer(backgroundMusic);
//	mediaPlayer.play();
//	mediaPlayer.setOnEndOfMedia(()->mediaPlayer.play());
}
