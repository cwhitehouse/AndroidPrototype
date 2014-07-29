package kippy.android.prototype.data;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import kippy.android.prototype.MyApplication;

/**
 * Created by christianwhitehouse on 7/23/14.
 */
public abstract class FileCache {

	//================================================================================
	// Constants
	//================================================================================

	protected static final String CACHE_HEADER_SEPARATOR = ":";
	protected static final String CACHE_ENTRY_SEPARATOR = "\n";
	protected static final String CACHE_PART_SEPARATOR = ",";

	//================================================================================
	// Abstact Methods
	//================================================================================

	protected abstract String getFileName();
	protected abstract int getCurrentVersion();
	protected abstract long getDataExpirationLimit();

	//================================================================================
	// General Utility
	//================================================================================

	public void clear() {
		deleteFile();
	}

	//================================================================================
	// File Header Info
	//================================================================================

	protected FileCacheData getValidatedInfoFromCache() {
		String cacheString = readDataFromFile();
		if(!TextUtils.isEmpty(cacheString)) {
			String[] cacheParts = cacheString.split(CACHE_HEADER_SEPARATOR);
			if(cacheParts.length >= 2) {
				String header = cacheParts[0];
				String[] headerParts = header.split(CACHE_PART_SEPARATOR);
				if(headerParts.length >= 2) {
					int cacheVersion = Integer.parseInt(headerParts[0]);
					long cacheUpdatedTime = Long.parseLong(headerParts[1]);

					FileCacheData cacheData = new FileCacheData(cacheParts[1], cacheVersion, cacheUpdatedTime);
					if(cacheData.isValid(getCurrentVersion(), getDataExpirationLimit()))
						return cacheData;
					else
						deleteFile();
				}
			}
		}

		return null;
	}

	protected boolean writeDataWithHeaderToFile(String cacheStringWithoutHeader) {
		if(!TextUtils.isEmpty(cacheStringWithoutHeader)) {
			StringBuilder cacheString = new StringBuilder();
			cacheString.append(getCurrentVersion());
			cacheString.append(CACHE_PART_SEPARATOR);
			cacheString.append(System.currentTimeMillis());
			cacheString.append(CACHE_HEADER_SEPARATOR);
			cacheString.append(CACHE_ENTRY_SEPARATOR);
			cacheString.append(cacheStringWithoutHeader);

			return writeDataToFile(cacheString.toString());
		} else
			return false;
	}

	//================================================================================
	// File Read/Write
	//================================================================================

	protected File getCacheFile() {
		return new File(MyApplication.get().getCacheDir(), getFileName());
	}

	protected boolean writeDataToFile(String cacheString) {
		File cacheFile = getCacheFile();

		try {
			FileOutputStream fos = new FileOutputStream(cacheFile);
			fos.write(cacheString.getBytes());
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {}

		return false;
	}

	protected String readDataFromFile() {
		File cacheFile = getCacheFile();
		if(cacheFile.exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(cacheFile));
				try {
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();

					while (line != null) {
						sb.append(line);
						sb.append("\n");
						line = br.readLine();
					}
					return sb.toString();
				} finally { br.close(); }
			} catch(IOException e) { e.printStackTrace(); }
		}
		return null;
	}

	protected boolean deleteFile() {
		File dataFile = getCacheFile();
		return dataFile.delete();
	}

}
