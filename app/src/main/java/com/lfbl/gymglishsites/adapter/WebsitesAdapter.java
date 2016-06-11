package com.lfbl.gymglishsites.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfbl.gymglishsites.R;
import com.lfbl.gymglishsites.WebsitesActivity;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class WebsitesAdapter extends CursorAdapter {

    public WebsitesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public static class ViewHolder {
        TextView textName;
        TextView textDescription;
        TextView textUrl;

        public ViewHolder(View view) {
            textName = (TextView) view.findViewById(R.id.textName);
            textDescription = (TextView) view.findViewById(R.id.textDescription);
            textUrl = (TextView) view.findViewById(R.id.textUrl);
        }
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_websites, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String cWebsiteName = cursor.getString(WebsitesActivity.COL_WEBSITE_NAME);
        String cWebsiteDesc = cursor.getString(WebsitesActivity.COL_WEBSITE_DESC);
        String cWebsiteUrl = cursor.getString(WebsitesActivity.COL_WEBSITE_URL);

        viewHolder.textName.setText(cWebsiteName);
        viewHolder.textDescription.setText(cWebsiteDesc);
        viewHolder.textUrl.setText(cWebsiteUrl);
    }
}
