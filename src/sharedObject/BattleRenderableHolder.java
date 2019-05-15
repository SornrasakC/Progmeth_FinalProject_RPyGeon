package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;

public class BattleRenderableHolder
{
	private static final BattleRenderableHolder instance = new BattleRenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	
	// TODO add sprite
//	public static Image playerSpriteNorth1;
//	public static Image playerSpriteEast1;
	public static Image gaugeBackground;
	public static Image listViewBackground;
	public static Image[] dungeonBackgrounds;

	public BattleRenderableHolder()
	{
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> 
		{
			if (o1.getZ() > o2.getZ()) return 1;
			return -1;
		};
	}
	public static BattleRenderableHolder getInstance() {
		return instance;
	}

	static
	{
		gaugeBackground = new Image(ClassLoader.getSystemResourceAsStream("gauge.png"));
		listViewBackground = new Image(ClassLoader.getSystemResourceAsStream("listView.png"));
		dungeonBackgrounds = new Image[8];
		for(int i = 1; i < 8; i++)
		{
			dungeonBackgrounds[i] = new Image(ClassLoader.getSystemResourceAsStream("dungeon" + i + ".png"));
		}
	}
	
	public void add(IRenderable entity)
	{
		System.out.println("added battle entity");
		entities.add(entity);
		Collections.sort(entities, comparator);
//		for(IRenderable x: entities)
//		{
//			
//		}
		
	}
	public List<IRenderable> getEntities() {
		return entities;
	}
}
