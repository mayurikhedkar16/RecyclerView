package info.androidhive.recyclerview.ContentLibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.db.utils.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerview.DividerItemDecoration;
import info.androidhive.recyclerview.HorizontalRecylerView;
import info.androidhive.recyclerview.R;



public class ContentLibrary extends AppCompatActivity {
    private List<ContentLibThumbnail_POJO> thumbnailPOJOList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContentLibAdapter mAdapter;


    DatabaseAdapter databaseAdapter;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lib);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        view = findViewById(R.id.contne_view);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_for_hr);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ContentLibAdapter(thumbnailPOJOList,this);

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new HorizontalRecylerView.RecyclerTouchListener(this, recyclerView, new HorizontalRecylerView.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareMovieData("B", "");


    }


    private void prepareMovieData(String Code, String whereQuery) {

        String Query = "";


        if (whereQuery.length() == 0)
            Query = "SELECT * FROM TBBRND where  col_1 = '" + Code + "'";
        else {
            Query = "SELECT * FROM TBBRND where col_1='" + Code + "' AND col_3 like '%" + whereQuery + "%'";
        }

        String[][] pdatat = databaseAdapter.genericSelect(Query, 9);


        ContentLibThumbnail_POJO thumbnailPOJO = null;

        if (pdatat != null) {
            for (int i = 0; i < pdatat.length; i++) {
                thumbnailPOJO = new ContentLibThumbnail_POJO(pdatat[i][0], pdatat[i][1], pdatat[i][2], pdatat[i][3],
                        pdatat[i][4], pdatat[i][5], pdatat[i][6], pdatat[i][7], pdatat[i][8]);
                thumbnailPOJOList.add(thumbnailPOJO);

            }
            mAdapter.notifyDataSetChanged();
        }

    }

}
