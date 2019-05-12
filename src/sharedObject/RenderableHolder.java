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
	public static Image emiliaN1;
	public static Image emiliaN2;
	public static Image emiliaE1;
	public static Image emiliaE2;
	public static Image emiliaW1;
	public static Image emiliaW2;
	public static Image emiliaS1;
	public static Image emiliaS2;
	
	//itemshop
	public static Image redCookie;
	public static Image blueCookie;
	public static Image hpPotion;
	public static Image mpPotion;
	public static Image chickenDinner;
	public static Image cocain;
	public static Image phoenixKit;
	public static Image lowRecPotion;
	public static Image highRecPotion;
	public static Image m44;
	public static Image rightArm;
	public static Image otenTear;
	public static Image mirrorForce;
	public static Image coin;
	
	
	//TODO add some sound
	public static AudioClip song;
	
	//epilogue
	public static Image epilogueBackground;

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
		emiliaE1 = new Image(ClassLoader.getSystemResourceAsStream("emiliaright01.PNG"));
		emiliaE2 = new Image(ClassLoader.getSystemResourceAsStream("emiliaright02.PNG"));
		emiliaW1 = new Image(ClassLoader.getSystemResourceAsStream("emilialeft01.PNG"));
		emiliaW2 = new Image(ClassLoader.getSystemResourceAsStream("emilialeft02.PNG"));
		emiliaN1 = new Image(ClassLoader.getSystemResourceAsStream("emiliaback01.png"));
		emiliaN2 = new Image(ClassLoader.getSystemResourceAsStream("emiliaback02.png"));
		emiliaS1 = new Image(ClassLoader.getSystemResourceAsStream("emiliafront01.png"));
		emiliaS2 = new Image(ClassLoader.getSystemResourceAsStream("emiliafront02.png"));
		
		//items
		redCookie = new Image(ClassLoader.getSystemResourceAsStream("redcookie.png"),80,80,true,false);
		blueCookie = new Image(ClassLoader.getSystemResourceAsStream("bluecookie.png"),80,80,true,false);
		hpPotion = new Image(ClassLoader.getSystemResourceAsStream("redpotion.png"),80,80,true,false);
		mpPotion = new Image(ClassLoader.getSystemResourceAsStream("bluepotion.png"),80,80,true,false);
		chickenDinner = new Image(ClassLoader.getSystemResourceAsStream("chickendinner.png"),80,80,true,false);
		cocain = new Image(ClassLoader.getSystemResourceAsStream("cocain.png"),80,80,true,false);
		phoenixKit = new Image(ClassLoader.getSystemResourceAsStream("phoenixkit2.png"),80,80,true,false);
		lowRecPotion = new Image(ClassLoader.getSystemResourceAsStream("lowrecoverypotion.png"),80,80,true,false);
		highRecPotion = new Image(ClassLoader.getSystemResourceAsStream("highrecoverypotion.png"),80,80,true,false);
		m44 = new Image(ClassLoader.getSystemResourceAsStream("M44a.png"),120,80,true,false);
		rightArm = new Image(ClassLoader.getSystemResourceAsStream("WIP.png"),80,80,true,false);
		otenTear = new Image(ClassLoader.getSystemResourceAsStream("otentear.png"),80,80,true,false);
		mirrorForce = new Image(ClassLoader.getSystemResourceAsStream("trapcard.png"),80,80,true,false);
		coin = new Image(ClassLoader.getSystemResourceAsStream("coin.png"),80,80,true,false);
		
		//epilogue
		epilogueBackground = new Image(ClassLoader.getSystemResourceAsStream("epilogue.png"));
		
	}

	public void add(IRenderable entity) {
		System.out.println("added entity");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for(IRenderable x: entities){
			
		}
	}



	public List<IRenderable> getEntities() {
		return entities;
	}
}
