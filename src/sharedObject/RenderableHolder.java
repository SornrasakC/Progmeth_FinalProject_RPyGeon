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
	
	public static Image playerSpriteNorth1;
	public static Image playerSpriteEast1;
	public static Image playerSpriteWest1; 
	public static Image playerSpriteSouth1;
	public static Image playerSpriteNorth2;
	public static Image playerSpriteEast2;
	public static Image playerSpriteWest2; 
	public static Image playerSpriteSouth2;
	public static Image genericVendor;
	public static Image emilia1;
	public static Image emilia2;
	
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
		playerSpriteNorth1 = new Image(ClassLoader.getSystemResourceAsStream("11.png"));
		playerSpriteEast1 = new Image(ClassLoader.getSystemResourceAsStream("21.png"));
		playerSpriteSouth1 = new Image(ClassLoader.getSystemResourceAsStream("31.png"));
		playerSpriteWest1 = new Image(ClassLoader.getSystemResourceAsStream("41.png"));
		playerSpriteNorth2 = new Image(ClassLoader.getSystemResourceAsStream("12.png"));
		playerSpriteEast2 = new Image(ClassLoader.getSystemResourceAsStream("22.png"));
		playerSpriteSouth2 = new Image(ClassLoader.getSystemResourceAsStream("32.png"));
		playerSpriteWest2 = new Image(ClassLoader.getSystemResourceAsStream("42.png"));
		genericVendor = new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png"));
		emilia1 = new Image(ClassLoader.getSystemResourceAsStream("01emilia.PNG"),40,60,true,false);
		emilia2 = new Image(ClassLoader.getSystemResourceAsStream("02emilia.PNG"),40,60,true,false);
		
		
	}

	public void add(IRenderable entity) {
		System.out.println("added entity");
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
