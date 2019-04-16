package logic.base;

public class FalseConstructionError extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FalseConstructionError(String message)
	{
		super(message);
		
	}

	@Override
	public String toString()
	{
		switch(super.getMessage())
		{
			case("Fail Offensive Magic"):
				return "(possible) Wrong lucktype of offensiveMagic";
			case("Fail Healing Magic"):
				return "(possible) Wrong lucktype of HealingMagic";
			default:
				return "";
		}
	}
	
}
