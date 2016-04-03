package ru.megazlo.ffx;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.androidannotations.annotations.EBean;

/**
 * @author iv - 31.03.2016
 */
@EBean
public class LeftMenuClickListener implements ListView.OnItemClickListener {
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Toast.makeText(view.getContext(), "id: " + l, 1000).show();
	}
}
