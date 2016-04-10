package ru.megazlo.ffx.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;

import ru.megazlo.ffx.R;

public class FileAdapter extends ArrayAdapter<File> {

    public static final String FONT_AWESOME_OTF = "fontAwesome.otf";
    private LayoutInflater inflater;

    private Typeface typeface;

    public FileAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_layout, null);
            holder.icon = (TextView) convertView.findViewById(R.id.text_icon);
            holder.icon.setTypeface(getTypeFace());
            holder.fileName = (TextView) convertView.findViewById(R.id.file_name);
            holder.fileProp = (TextView) convertView.findViewById(R.id.file_prop);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        File item = this.getItem(position);
        holder.icon.setText(item.isDirectory() ? R.string.fa_folder_o : R.string.fa_file_o);
        holder.fileName.setText(item.getName());
        holder.fileProp.setText(getFileProp(item));
        return convertView;
    }

    private String getFileProp(File file) {
        String rez = "";
        return rez;
    }

    private Typeface getTypeFace() {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(getContext().getAssets(), FONT_AWESOME_OTF);
        }
        return typeface;
    }

    private class ViewHolder {
        TextView icon;
        TextView fileName;
        TextView fileProp;
    }
}
