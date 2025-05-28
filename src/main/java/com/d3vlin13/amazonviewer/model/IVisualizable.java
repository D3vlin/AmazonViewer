package com.d3vlin13.amazonviewer.model;

import java.util.Date;

public interface IVisualizable {
	Date startToSee(Date dateI);
	void stopToSee(Date dateI, Date dateF);
	
}
