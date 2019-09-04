package com.example.tourism.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashTagSuggestAdapter extends ArrayAdapter<String> {

    private HashTagFilter filter;
    private List<String> objects;
    private List<String> suggests = new ArrayList<>();
    private CursorPositionListener listener;

    public HashTagSuggestAdapter(@NonNull Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.objects = Arrays.asList(objects);
    }

    public interface CursorPositionListener {
        int currentCursorPosition();
    }

    public void setCursorPositionListener(CursorPositionListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return suggests.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return suggests.get(position);
    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new HashTagFilter();
        }
        return filter;
    }

    public class HashTagFilter extends Filter {

        private final Pattern pattern = Pattern.compile("([ㄱ-ㅎ가-힣])+");

        public int start;
        public int end;

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return "#" + resultValue.toString() + " ";
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                suggests.clear();

                int cursorPosition = listener.currentCursorPosition();

                Matcher m = pattern.matcher(constraint.toString());
                while (m.find()) {

                    if (m.start() < cursorPosition && cursorPosition <= m.end()) {
                        start = m.start();
                        end = m.end();

                        String tag = constraint.subSequence(m.start(), m.end()).toString();
                        for (int i = 0; i < objects.size(); i++) {
                            String word = objects.get(i);
                            if (word.startsWith(tag)) {
                                suggests.add(word);
                            }
                        }
                    }
                }
            }

            filterResults.values = suggests;
            filterResults.count = suggests.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
