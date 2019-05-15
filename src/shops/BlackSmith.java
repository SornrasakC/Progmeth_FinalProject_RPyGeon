package shops;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;

import item.Weapon;
import logic.logics.Player;

public class BlackSmith
{
	private ArrayList<Weapon> allWeaponList = new ArrayList<Weapon>();
	private ArrayList<Weapon> weaponAvailableList = new ArrayList<Weapon>();
	@SuppressWarnings("unchecked")
	public BlackSmith()
	{
		
		try
		{
			
			allWeaponList.addAll((ArrayList<Weapon>) (ArrayList<?>) main.Main.readJson("WeaponsStore.json",new TypeToken<ArrayList<Weapon>>(){}));
			allWeaponList.sort
			(new Comparator<Weapon>()
				{
					public int compare(Weapon a, Weapon b)
					{
						return a.getPrice() - b.getPrice();
					}
				}
			);
			weaponAvailableList = allWeaponList.stream().filter(x -> x.getFloor() <= Player.player.getConqueredFloor()).collect(Collectors.toCollection(ArrayList::new));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<Weapon> getWeaponAvailableList(Player player)
	{
		return weaponAvailableList;
	}
	public ArrayList<Weapon> getAllWeaponList()
	{
		return allWeaponList;
	}
	
}
