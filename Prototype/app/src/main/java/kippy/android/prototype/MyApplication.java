package kippy.android.prototype;

import android.app.Application;

/**
 * Created by christianwhitehouse on 7/29/14.
 */
public class MyApplication extends Application {

	//================================================================================
	// Static Variables
	//================================================================================

	private static MyApplication sSelf;

	//================================================================================
	// Static Access
	//================================================================================

	public static MyApplication get() {
		return sSelf;
	}

	//================================================================================
	// Life Cycle Management
	//================================================================================

	@Override
	public void onCreate() {
		super.onCreate();

		sSelf = this;
	}

}
