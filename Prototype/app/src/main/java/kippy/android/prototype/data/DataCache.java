package kippy.android.prototype.data;

/**
 * Created by christianwhitehouse on 6/20/14.
 */
public class DataCache {

	//================================================================================
	// Static Variables
	//================================================================================

	private static DataCache sSelf;

	//================================================================================
	// Cache Access
	//================================================================================

	public static DataCache getInstance() {
		if(sSelf == null)
			sSelf = new DataCache();

		return sSelf;
	}

	private DataCache() {}

	//================================================================================
	// Data Access
	//================================================================================

}