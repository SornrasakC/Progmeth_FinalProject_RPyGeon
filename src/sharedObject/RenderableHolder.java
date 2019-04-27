package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image playerSpriteNorth;
	public static Image playerSpriteEast;
	public static Image playerSpriteWest; 
	public static Image playerSpriteSouth;
	
	//TODO add some sound
	public static AudioClip song;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		playerSpriteNorth = new Image(ClassLoader.getSystemResourceAsStream("1.png"));
		playerSpriteEast = new Image(ClassLoader.getSystemResourceAsStream("2.png"));
		playerSpriteWest = new Image(ClassLoader.getSystemResourceAsStream("3.png"));
		playerSpriteSouth = new Image(ClassLoader.getSystemResourceAsStream("4.png"));
	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for(IRenderable x: entities){
			
		}
	}

	public void update() {
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
