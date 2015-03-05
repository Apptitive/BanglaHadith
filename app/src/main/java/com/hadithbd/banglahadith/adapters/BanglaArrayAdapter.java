package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.tables.hadith.HadithBook;

import java.util.List;

/**
 * Created by Sharif on 3/5/2015.
 */
public class BanglaArrayAdapter extends ArrayAdapter<HadithBook> {

    private TextView btvTitle;
    private int itemResourceId;
    private int dropDownResourceId;

    public BanglaArrayAdapter(Context context, List<HadithBook> hadithBooks) {
        super(context, R.layout.simple_spinner_item, R.layout.simple_spinner_dropdown_item, hadithBooks);
        itemResourceId = R.layout.simple_spinner_item;
        dropDownResourceId = R.layout.simple_spinner_dropdown_item;
    }

    @Override
    public int getViewTypeCount() {
        if (getCount() != 0)
            return getCount();

        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mBanglaTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    itemResourceId, parent, false);
            btvTitle = (TextView) convertView.findViewById(android.R.id.text1);
        }
        btvTitle.setText(UtilBanglaSupport.getBanglaSpannableString(getItem(position).getNameBengali()));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    dropDownResourceId, parent, false);
            holder = new ViewHolder();
            holder.mBanglaTextView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mBanglaTextView.setText(UtilBanglaSupport.getBanglaSpannableString(getItem(position).getNameBengali()));
        return convertView;
    }
}