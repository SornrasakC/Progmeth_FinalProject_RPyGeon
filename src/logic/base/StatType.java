package logic.base;

public enum StatType
{
	PHYATK, MAGATK, PHYDEF, MAGDEF;
	public static StatType convert(String statType)
	{
		switch(statType)
		{
			case("PHYATK"):
				return PHYATK;
			case("MAGATK"):
				return MAGATK;
			case("PHYDEF"):
				return PHYDEF;
			case("MAGDEF"):
				return MAGDEF;
			default:
				return null;
		}
	}
}
