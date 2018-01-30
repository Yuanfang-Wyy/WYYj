package io.transwarp.poc.utils;

public class RandomInt
{
	private int maxValue;

	public RandomInt(int maxValue)
	{
		this.maxValue = maxValue;
	}

	public String getColumnData()
	{
		StringBuffer sb = new StringBuffer();
		int s =(int)((Math.random() * maxValue));
		sb.append(s);

		return sb.toString();
	}

	public static void main(String[] args)
	{
		RandomInt randomInt = new RandomInt(1000);
		for (int i = 0; i < 10000; i++)
		{
			System.out.println(randomInt.getColumnData());
		}
	}

}
