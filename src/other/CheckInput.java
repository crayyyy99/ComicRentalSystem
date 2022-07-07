package other;

public class CheckInput {
	
	public boolean isNull(String input) {
		if(input == null  || input.length() == 0)
			return true;
		else
			return false;
	}
	
	public boolean isDigit(String input)
	{
		return input.matches("[0-9]+");
	}
	
	public boolean isValidIc(String input)
	{
		if(isDigit(input))
		{
			if(input.length() == 12)
				return false;
		}
		return true;
	}
	
	public boolean isValidPhoneNo(String input)
	{
		if(isDigit(input))
		{
			if(input.length() <= 20)
				return false;
		}
		return true;
	}
	
	public boolean isValidEmail(String input)
	{
		if(input.contains("@"))
			return false;
		else 
			return true;
	}
	
	public boolean isOverLimit(String input, int limit)
	{
		if(input.length() > limit)
			return true;
		else
			return false;
	}
	
	public boolean isValidPassword(String input)
	{
		if(input.length() < 8)
			return true;
		else
			return false;
	}

}
