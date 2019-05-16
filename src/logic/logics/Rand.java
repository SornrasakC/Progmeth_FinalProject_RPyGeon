package logic.logics;

import java.util.ArrayList;
import java.util.Random;

import logic.base.CustomException;

public class Rand extends Random
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int randInclusive(int min, int max)
	{
		if (max < min) 
		{
			new CustomException("Max < Min random").printStackTrace();
			return 0;
		}
		if(max == 0) return 0;
		Random r = new Random();
		return min + r.nextInt(max - min + 1);
	}
	public static int randExclusive(int min, int max)
	{
		if (max < min) 
		{
			new CustomException("Max < Min random").printStackTrace();
			return 0;
		}
		if(max == 0) return 0;
		Random r = new Random();
		return min + r.nextInt(max - min);
	}

	public static int rand(int max)
	{
		if(max == 0) return 0;
		Random r = new Random();
		return r.nextInt(max);
	}

	public static <T> Object rand(ArrayList<T> t)
	{
//		Random r = new Random();
		if(t.size() == 0)
			return null;
		return t.get(rand(t.size()));
	}

	public static boolean chance(int chance)
	{
		Random r = new Random();
		return r.nextInt(100) < chance;
	}
}
