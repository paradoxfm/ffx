package ru.megazlo.ffx.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.megazlo.ffx.models.SlideMenuItem;

import java.util.List;

/**
 * @author iv - 31.03.2016
 */
public class MenuAdapter extends ArrayAdapter<SlideMenuItem> {

	private Activity activity;
	private List<SlideMenuItem> itemList;
	private int row;

	public MenuAdapter(Activity act, int resource, List<SlideMenuItem> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
		this.row = resource;
		this.itemList = arrayList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			/*holder.tvTitle = (TextView) view.findViewById(R.id.menu_title);
			holder.imgView = (ImageView) view.findViewById(R.id.menu_icon);*/
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((itemList == null) || ((position + 1) > itemList.size())) {
			return view;
		}

		SlideMenuItem item = itemList.get(position);
		holder.tvTitle.setText(item.getTitle());
		holder.imgView.setImageResource(item.getIconId());
		return view;
	}

	private class ViewHolder {
		Long id;
		TextView tvTitle;
		ImageView imgView;
	}

}