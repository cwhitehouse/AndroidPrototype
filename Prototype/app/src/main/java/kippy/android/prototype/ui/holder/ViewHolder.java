package kippy.android.prototype.ui.holder;

import android.view.View;

/**
 * Created by christianwhitehouse on 6/20/14.
 */
public abstract class ViewHolder {

	//================================================================================
	// Variables
	//================================================================================

	protected int mPosition;
	public View vBase;

	//================================================================================
	// Constructor
	//================================================================================

	public ViewHolder(View convertView, int position) {
		vBase = convertView;
		vBase.setTag(this);

		mPosition = position;
	}

	//================================================================================
	// Reset
	//================================================================================

	public void resetViewHolder(int newPosition) {
		if(mPosition != newPosition)
			reset();
	}

	protected abstract void reset();
}
