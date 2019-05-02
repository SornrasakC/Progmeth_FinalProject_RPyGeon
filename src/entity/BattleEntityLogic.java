package entity;

import java.util.ArrayList;
import java.util.List;

import logic.logics.Player;
import scene.Battle;
import sharedObject.BattleRenderableHolder;


public class BattleEntityLogic 
{
	private static final int WIDTH = 1280 * 4 / 5;
	private static final int HEIGHT = 720 * 4 / 5;
	
	private List<Entity> gameObjectContainer;
	private static PlayerBattleEntity playerEntity;
	private static MonsterBattleEntity monsterEntity;
	private static HpMpGaugeEntity playerGauge, monsterGauge;
	
//	private Canvas canvas;
	public BattleEntityLogic()
	{
		this.gameObjectContainer = new ArrayList<Entity>();
		playerEntity = new PlayerBattleEntity(WIDTH / 2 - 150, HEIGHT / 2);
		monsterEntity = new MonsterBattleEntity(WIDTH / 2 + 150, HEIGHT / 2, Battle.getMonster());
		playerGauge = new HpMpGaugeEntity(0, 0, Player.player);
		monsterGauge = new HpMpGaugeEntity(0, 0, Battle.getMonster());
		
		addNewObject(playerEntity);
		addNewObject(monsterEntity);
		addNewObject(playerGauge);
		addNewObject(monsterGauge);
		
	}
	protected void addNewObject(Entity entity)
	{
		gameObjectContainer.add(entity);
		BattleRenderableHolder.getInstance().add(entity);
	}
	public void logicUpdate()
	{
		playerEntity.update();
		monsterEntity.update();
		playerGauge.update();
		monsterGauge.update();
	}
}
