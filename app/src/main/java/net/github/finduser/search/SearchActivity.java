package net.github.finduser.search;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.github.finduser.BaseApp;
import net.github.finduser.R;
import net.github.finduser.models.SearchResponse;
import net.github.finduser.networking.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends BaseApp implements SearchView {
    private RecyclerView mRecyclerView;
    private List<SearchResponse.ItemsBean> mArrayList;
    private SearchAdapter mAdapter;
    ProgressBar progressBar;
    @Inject
    public Service service;
    SearchPresenter presenter;
    private TextView nodata,searchmsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
    }

    private void initViews(){
        mRecyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        progressBar = findViewById(R.id.progress);
        nodata=(TextView) findViewById(R.id.nodata);
        searchmsg=(TextView) findViewById(R.id.searchmsg);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        presenter = new SearchPresenter(service, this);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String errorMsg) {

    }

    @Override
    public void getUserListSuccess(SearchResponse response) {
        if(response!=null && response.getItems().size()>0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.GONE);
            mArrayList = response.getItems();
            mAdapter = new SearchAdapter(SearchActivity.this, mArrayList);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            nodata.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(final android.support.v7.widget.SearchView searchView) {

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String s=query;
                searchView.clearFocus();
                presenter.getCityList(s);
                searchmsg.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }


        });
    }
}
