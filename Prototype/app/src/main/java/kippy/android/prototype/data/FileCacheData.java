package kippy.android.prototype.data;

/**
 * Created by christianwhitehouse on 7/24/14.
 */
public class FileCacheData {

	//================================================================================
	// Variables
	//================================================================================

	protected String mDataString;
	protected int mDataVersion;
	protected long mUpdateTime;

	//================================================================================
	// Constructor
	//================================================================================

	public FileCacheData(String dataString, int dataVersion, long updateTime) {
		mDataString = dataString;
		mDataVersion = dataVersion;
		mUpdateTime = updateTime;
	}

	//================================================================================
	// Access
	//================================================================================

	public String getData() {
		return mDataString;
	}

	//================================================================================
	// Validation
	//================================================================================

	public boolean isValid(int currentVersion, long expirationLimit) {
		return !isOldVersion(currentVersion) && !isExpired(expirationLimit);
	}

	public boolean isExpired(long expirationLimit) {
		if(expirationLimit >= 0)
			return (System.currentTimeMillis() - mUpdateTime) > expirationLimit;
		else
			return false;
	}

	public boolean isOldVersion(int currentVersion) {
		return mDataVersion < currentVersion;
	}

	public boolean wasUpdatedSinceBoot() {
		return System.currentTimeMillis() - mUpdateTime < android.os.SystemClock.elapsedRealtime();
	}

}
