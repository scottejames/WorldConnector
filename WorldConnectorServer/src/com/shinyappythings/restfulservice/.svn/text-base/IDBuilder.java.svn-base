package com.shinyappythings.restfulservice;

import com.shineyappythings.engine.Database;

public class IDBuilder
{
	private static long previousID = Database.getInstance().getNextID() + 1;
	
	public static long getId()
	{
		
		return previousID++;
	}
}
