package by.pinchuk.dao.transport;

import java.util.List;

import by.pinchuk.table.transport.Track;

public interface ITrack {
	
	public List<Track> allTrack();
	
	public Track selectById(long id);
	
	public void deleteById(long id);
	
	public void createNewTrack(Track tr);

}
