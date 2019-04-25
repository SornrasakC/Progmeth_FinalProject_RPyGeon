package shops;

import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import item.RecoveryPotion;
import item.StatPotion;
import logic.base.Potion;

public class ItemShop
{
	private ArrayList<Potion> potionList = new ArrayList<Potion>();
	@SuppressWarnings("unchecked")
	public ItemShop()
	{
		
		try
		{
			potionList.addAll((ArrayList<Potion>) (ArrayList<?>) main.Main.readJson("RecoveryPotions.json",new TypeToken<ArrayList<RecoveryPotion>>(){}));
			potionList.addAll((ArrayList<Potion>) (ArrayList<?>) main.Main.readJson("StatPotions.json",new TypeToken<ArrayList<StatPotion>>(){}));
//			potionList.sort
//			(new Comparator<Weapon>()
//				{
//					public int compare(Weapon a, Weapon b)
//					{
//						return a.getPrice() - b.getPrice();
//					}
//				}
//			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<Potion> getPotionList()
	{
		return potionList;
	}
	
}
