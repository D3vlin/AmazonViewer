package com.d3vlin13.amazonviewer.model;

import com.d3vlin13.amazonviewer.model.dao.IMovieDao;

import java.util.ArrayList;
import java.util.Date;

/**
 * Inherits from {@link Film}
 * Implements of {@link IVisualizable}
 */
public class Movie extends Film implements IVisualizable, IMovieDao {
	private int id;
	private int timeViewed;

	public Movie() {
		super();
	}

	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTimeViewed() {
		return timeViewed;
	}

	public void setTimeViewed(int timeViewed) {
		this.timeViewed = timeViewed;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "\n :: MOVIE ::" + 
				"\n Title: " + getTitle() +
				"\n Genero: " + getGenre() + 
				"\n Year: " + getYear() + 
				"\n Creator: " + getCreator() +
				"\n Duration: " + getDuration();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date startToSee(Date dateI) {
		return dateI;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopToSee(Date dateI, Date dateF) {
		if (dateF.getTime() > dateI.getTime()) {
			setTimeViewed((int)(dateF.getTime() - dateI.getTime()));
		}else {
			setTimeViewed(0);
		}
	}
	
	public static ArrayList<Movie> makeMoviesList() {
		Movie movie = new Movie();
		return movie.read();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void view() {
		setViewed(true);
		Movie movie = new Movie();
		movie.setMovieViewed(this);
		Date dateI = startToSee(new Date());

		for (int i = 0; i < 100000; i++) {
			System.out.println("..........");
		}

		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Viste: " + toString());
		System.out.println("Por: " + getTimeViewed() + " milisegundos");
	}
}