package com.willmayala;

/**
 * This class can be used to select every movie from the MovieDatabase.
 * Its satisfies method always returns true.
 */

public class TrueFilter implements Filter
{
	@Override
	public boolean satisfies(String id)
	{
		return true;
	}
}
