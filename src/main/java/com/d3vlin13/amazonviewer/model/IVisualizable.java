package com.d3vlin13.amazonviewer.model;

import java.util.Date;

public interface IVisualizable {
	/**
	 * This method captures the exact display start time
	 * @param dateI It is an object of type {@code Date} with the exact start time
	 * @return Returns the captured date and time
	 */
	Date startToSee(Date dateI);

	/**
	 * This method captures the exact start and end time of viewing.
	 * @param dateI It is an object of type {@code Date} with the exact start time
	 * @param dateF It is an object of type {@code Date} with the exact end time
	 */
	void stopToSee(Date dateI, Date dateF);
}