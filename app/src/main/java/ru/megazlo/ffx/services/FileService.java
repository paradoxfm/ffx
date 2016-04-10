package ru.megazlo.ffx.services;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ru.megazlo.ffx.adapters.FileAdapter;

@EBean
public class FileService implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;

    private FileAdapter fileAdapter;

    private File rootPath;
    private File currentPath;

    /*@Getter
    private StorageType storageType;

    public FileService(StorageType storageType) throws StorageNotFoundException {
        this.storageType = storageType;
        init();
    }

    private void init() throws StorageNotFoundException {
        if (storageType == StorageType.SD && Environment.getExternalStorageState() == null) {
            throw new StorageNotFoundException();
        }


        File extStore = Environment.getExternalStorageDirectory();
    }*/

    public void init(ListView listView) {
        this.listView = listView;
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new FileAdapter(listView.getContext(), 0));
        fileAdapter = (FileAdapter) listView.getAdapter();
        readRoot();
    }

    public boolean isRoot() {
        return rootPath.equals(currentPath);
    }

    public void moveToParent() {
        setNewData(currentPath.getParentFile());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File item = fileAdapter.getItem(position);
        if (item.isDirectory()) {
            setNewData(item);
        } else {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.fromFile(item), getMimeType(item.getAbsolutePath()));
            view.getContext().startActivity(i);
        }
    }

    public static String getMimeType(String url) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return null;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    private void readRoot() {
        if (Environment.getExternalStorageState() == null) {
            //throw new StorageNotFoundException();
        }
        rootPath = Environment.getExternalStorageDirectory();
        setNewData(rootPath);
    }

    private void setNewData(File parent) {
        currentPath = parent;
        new ReadFilesTask().execute(parent);
    }

    private class ReadFilesTask extends AsyncTask<File, Integer, Long> implements Comparator<File> {
        List<File> items;

        protected Long doInBackground(File... urls) {
            items = Arrays.asList(urls[0].listFiles());
            Collections.sort(items, this);
            return (long) items.size();
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            fileAdapter.clear();
            fileAdapter.addAll(items);
            fileAdapter.notifyDataSetChanged();
        }

        @Override
        public int compare(File lhs, File rhs) {
            if (lhs.isDirectory() != rhs.isDirectory()) {
                return lhs.isDirectory() ? -1 : 1;
            }
            return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());
        }
    }

}
