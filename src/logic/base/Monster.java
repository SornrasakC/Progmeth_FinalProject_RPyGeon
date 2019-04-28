package logic.base;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.logics.Player;

public abstract class Monster extends Character
{
//	private ArrayList<? extends Droppable> dropList = new ArrayList<? extends Droppable>();
	public Monster(String name, int level, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk, int baseMaxMagAtk,
			int basePhyDef, int baseMagDef, int baseMaxHp, int baseMaxMp)
	{

		this.sprite = new Image(ClassLoader.getSystemResourceAsStream("Monster/" + this.name + "1.png"));
		this.spriteAnimation = new Image(ClassLoader.getSystemResourceAsStream("Monster/" + this.name + "2.png"));
		this.name = name;
		this.level = level;

		this.baseMinPhyAtk = baseMinPhyAtk;
		this.baseMaxPhyAtk = baseMaxPhyAtk;
		this.baseMinMagAtk = baseMinMagAtk;
		this.baseMaxMagAtk = baseMaxMagAtk;
		this.basePhyDef = basePhyDef;
		this.baseMagDef = baseMagDef;
		this.baseMaxHp = baseMaxHp;
		this.baseMaxMp = baseMaxMp;
		fullHeal();
//		this.money = rand.nextInt((int)((level - 1) * 30 * 0.50)) + (int)((level - 1) * 30 * 0.75);
	}

	public abstract int attack(Player player) throws CustomException;
	
	public String toString()
	{
		return name + " level: " + level;
	}
	@Override
	public int getMoney()
	{
		Random rand = new Random();
		return rand.nextInt((int) ((level - 1) * 30 * 0.50)) + (int) ((level - 1) * 30 * 0.75);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//TODO add animation
		gc.drawImage(sprite, x, y);
	}

}
