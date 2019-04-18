package logic.base;

public class CustomException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(String message)
	{
		super(message);
		
	}

	@Override
	public String toString()
	{
		switch(super.getMessage())
		{
			case("Fail Offensive Magic"):
				return "Wrong lucktype of offensiveMagic";
			case("Fail Healing Magic"):
				return "Wrong lucktype of HealingMagic";
			case("Fail Receive Damage"):
				return "Wrong Damage Type";
			case("Fail Recovery Potion"):
				return "Wrong Recovery Potion";
			default:
				return "";
		}
	}
	
}
