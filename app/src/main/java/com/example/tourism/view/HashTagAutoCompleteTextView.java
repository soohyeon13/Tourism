package com.example.tourism.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.example.tourism.R;
import com.example.tourism.view.adapter.HashTagSuggestAdapter;
import com.google.j2objc.annotations.ObjectiveCName;


@SuppressLint("AppCompatCustomView")
public class HashTagAutoCompleteTextView extends AutoCompleteTextView {
    public HashTagAutoCompleteTextView(Context context) {
        super(context);
    }

    public HashTagAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.autoCompleteTextViewStyle);
    }

    public HashTagAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void replaceText(CharSequence text) {
        clearComposingText();

        HashTagSuggestAdapter adapter = (HashTagSuggestAdapter) getAdapter();
        HashTagSuggestAdapter.HashTagFilter filter = (HashTagSuggestAdapter.HashTagFilter)adapter.getFilter();

        Editable span = getText();
        span.replace(filter.start,filter.end,text);
    }
}