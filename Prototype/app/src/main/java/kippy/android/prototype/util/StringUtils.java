package kippy.android.prototype.util;

import android.text.TextUtils;

/**
 * Created by christianwhitehouse on 7/29/14.
 */
public class StringUtils {

	//================================================================================
	// Convenience
	//================================================================================

	public static String trimLastCharacter(String text) {
		if(!TextUtils.isEmpty(text) && text.length() > 1)
			return text.substring(0, text.length()-1);
		else
			return text;
	}

}
