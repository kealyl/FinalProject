
public class Transfers {
	public int from_stopID;
	public int to_stopID;
	public int transfer_type;
	public int min_transfer_time;
	
	Transfers(int from_stopID, int to_stopID, int transfer_type, int min_transfer_time)
	{
		this.from_stopID = from_stopID;
		this.to_stopID = to_stopID;
		this.transfer_type = transfer_type;
		this.min_transfer_time = min_transfer_time;
	}

}
