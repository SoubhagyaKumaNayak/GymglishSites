package com.lfbl.gymglishsites;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lfbl.gymglishsites.adapter.WebsitesAdapter;
import com.lfbl.gymglishsites.data.GymglishContract;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class WebsitesActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String BUNDLE_LIST_POSITION = "BLP";
    private static final int WEBSITES_LOADER = 1;

    private static final String[] WEBSITE_COLUMNS = {
            GymglishContract.SitesEntry._ID,
            GymglishContract.SitesEntry.COLUMN_NAME,
            GymglishContract.SitesEntry.COLUMN_DESC,
            GymglishContract.SitesEntry.COLUMN_URL
    };

    public static final int COL_WEBSITE_ID = 0;
    public static final int COL_WEBSITE_NAME = 1;
    public static final int COL_WEBSITE_DESC = 2;
    public static final int COL_WEBSITE_URL = 3;

    private WebsitesAdapter mWebsitesAdapter;
    private ListView mListView;

    private int mPosition = ListView.INVALID_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websites);

        mWebsitesAdapter = new WebsitesAdapter(this, null, 0);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(mWebsitesAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor c = (Cursor) adapterView.getItemAtPosition(position);
                if (c != null) {
                    String cUrl = c.getString(COL_WEBSITE_URL);

                    Intent intent = new Intent(WebsitesActivity.this, WebsiteDetailActivity.class);
                    intent.putExtra(WebsiteDetailActivity.EXTRA_WEBSITE_URL, cUrl);
                    startActivity(intent);
                }
                mPosition = position;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_LIST_POSITION)) {
            mPosition = savedInstanceState.getInt(BUNDLE_LIST_POSITION);
        }

        getSupportLoaderManager().initLoader(WEBSITES_LOADER, savedInstanceState, this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(BUNDLE_LIST_POSITION, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    // region  LoaderCallbacks<Cursor>

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                GymglishContract.SitesEntry.CONTENT_URI,
                WEBSITE_COLUMNS,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mWebsitesAdapter.swapCursor(data);

        // Scroll list position to the last website selected
        if (mPosition != ListView.INVALID_POSITION) {
            mListView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mWebsitesAdapter.swapCursor(null);
    }

    //endregion
}
