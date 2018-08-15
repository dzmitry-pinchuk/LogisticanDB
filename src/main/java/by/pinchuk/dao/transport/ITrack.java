package by.pinchuk.dao.transport;

import java.util.ArrayList;

import by.pinchuk.table.transport.Track;

public interface ITrack {
	
	public ArrayList<Track> allTrack();
	
	public Track selectById(long id);
	
	public void deleteById(long id);
	
	public void createNewTrack(Track tr);

}
