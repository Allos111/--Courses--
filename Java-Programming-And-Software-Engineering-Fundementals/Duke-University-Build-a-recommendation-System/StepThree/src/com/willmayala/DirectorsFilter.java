package com.willmayala;

import java.util.*;

public class DirectorsFilter implements Filter
{
	private String theDirectors;
	
	public DirectorsFilter (String directors)
	{
		theDirectors = directors;
	}

	@Override
	public boolean satisfies(String id)
	{
		/*
		String[] theDir = theDirectors.split(",");
		for (String director : theDir)
		{
			if (MovieDatabase.getDirector(id).contains(director)) 
			{
				return true;
			}
		}
		return false;
		*/
		List<String> DirectorsList = Arrays.asList(theDirectors.split(","));
		boolean k = false;

		for (String DirectorFromTheList : DirectorsList)
		{
			k = k || MovieDatabase.getDirector(id).contains(DirectorFromTheList);
		}
		return k;
	}
}
