package by.pinchuk.dao.payment;

import java.util.ArrayList;
import java.util.List;

import by.pinchuk.table.payment.CheckFare;

public interface ICheckFares {
	

	public List<CheckFare> allCheckFares();

	public CheckFare selectById(long id);

	public void deleteById(long id);

	public void createNewCheckFares(CheckFare cf);
	
	public ArrayList<CheckFare> allCheckFaresByTrackID(long idTrack);

}
