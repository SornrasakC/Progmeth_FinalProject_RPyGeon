package entity;

import java.util.ArrayList;
import java.util.List;

import logic.base.Monster;
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
		playerEntity = new PlayerBattleEntity(WIDTH / 2 - 250, HEIGHT / 2 - 75);
		monsterEntity = new MonsterBattleEntity(WIDTH / 2 + 250, HEIGHT / 2 - 75, Battle.getMonster());
//		playerGauge = new HpMpGaugeEntity(WIDTH / 2 - 300, HEIGHT / 2 + 75, Player.player);
//		monsterGauge = new HpMpGaugeEntity(WIDTH / 2 + 200, HEIGHT / 2 + 75, Battle.getMonster());
		playerGauge = new HpMpGaugeEntity(5, HEIGHT - 90, Player.player);
		monsterGauge = new HpMpGaugeEntity(WIDTH - 5, HEIGHT - 90, Battle.getMonster());
		
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
	public void renewMonster(Monster monster)
	{
		monsterGauge.setCharacter(monster);
		monsterEntity.setMonster(monster);
	}
	public static PlayerBattleEntity getPlayerEntity()
	{
		return playerEntity;
	}
	public static void setPlayerEntity(PlayerBattleEntity playerEntity)
	{
		BattleEntityLogic.playerEntity = playerEntity;
	}
	public static MonsterBattleEntity getMonsterEntity()
	{
		return monsterEntity;
	}
	public static void setMonsterEntity(MonsterBattleEntity monsterEntity)
	{
		BattleEntityLogic.monsterEntity = monsterEntity;
	}
	public static HpMpGaugeEntity getPlayerGauge()
	{
		return playerGauge;
	}
	public static void setPlayerGauge(HpMpGaugeEntity playerGauge)
	{
		BattleEntityLogic.playerGauge = playerGauge;
	}
	public static HpMpGaugeEntity getMonsterGauge()
	{
		return monsterGauge;
	}
	public static void setMonsterGauge(HpMpGaugeEntity monsterGauge)
	{
		BattleEntityLogic.monsterGauge = monsterGauge;
	}
	
}
