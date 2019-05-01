package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BattleRenderableHolder
{
	private static final BattleRenderableHolder instance = new BattleRenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	
	// TODO add sprite
//	public static Image playerSpriteNorth1;
//	public static Image playerSpriteEast1;

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
		//TODO start
	}
	
	public void add(IRenderable entity)
	{
		System.out.println("added entity");
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
