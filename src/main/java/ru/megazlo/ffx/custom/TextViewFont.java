package ru.megazlo.ffx.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import ru.megazlo.ffx.R;

/**
 * @author iv - 04.04.2016
 */
public class TextViewFont extends TextView {
	public TextViewFont(Context context) {
		super(context);
	}

	public TextViewFont(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextViewFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextViewFont);
			String fontName = a.getString(R.styleable.TextViewFont_fontName);
			if (fontName != null) {
				Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
				setTypeface(myTypeface);
			}
			a.recycle();
		}
	}
}
