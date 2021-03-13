package com.willmayala;

/**
 * @author Allos111
 */
public class GenreFilter implements Filter
{
	private String myGenre;
	
	public GenreFilter (String genre)
	{
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies (String id)
	{
		String genres = MovieDatabase.getGenres(id);
		return genres.contains(myGenre);
//		if (genres.indexOf(myGenre) != -1)
//		{
//			return true;
//		}
//		return false;
	}
}
