package logic.base;

public enum LuckType
{
	NORMAL, RANDOM, BADRANDOM;
	public static LuckType convert(String luckType)
	{
		switch(luckType)
		{
			case "NORMAL":
				return NORMAL;
			case "RANDOM":
				return RANDOM;
			case "BADRANDOM":
				return BADRANDOM;
			default:
				return null;
		}
	}
}
