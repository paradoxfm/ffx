package ru.megazlo.ffx.models;

import android.graphics.Bitmap;

/**
 * @author iv - 31.03.2016
 */
public class SlideMenuItem {

	private Bitmap icon;
	private String title;
	private int iconID;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public int getIconId() {
		return iconID;
	}

	public void setIconID(int icon) {
		this.iconID = icon;
	}

}