package info.androidhive.recyclerview.ContentLibrary;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.db.utils.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerview.DividerItemDecoration;
import info.androidhive.recyclerview.R;
import info.androidhive.recyclerview.ThumbnailAdapter;
import info.androidhive.recyclerview.Thumbnail_POJO;

/**
 * Created by GB on 3/7/16.
 */
public class ContentLibraryList {


    Context mContext;
    private List<Thumbnail_POJO> thumbnailPOJOList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ThumbnailAdapter mAdapter;
    private String Header;
    private String Cat_Code;
    DatabaseAdapter databaseAdapter;
    String WhereQuery;
    Typeface font;
    View second;

    public ContentLibraryList(Context mContext, String Header, String Cat_Code, String WhereQuery) {
        this.mContext = mContext;
        this.Header = Header;
        this.Cat_Code = Cat_Code;
        databaseAdapter = new DatabaseAdapter(mContext);
        databaseAdapter.open();
        this.WhereQuery = WhereQuery;
        font = Typeface.createFromAsset(mContext.getAssets(),
                "fontawesome-webfont.ttf");

    }

    public View getHorizontalRecylerView(android.support.v4.app.FragmentManager manager) {
        View mRecylerView = null;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        mRecylerView = inflater.inflate(R.layout.horizontalscrollrecylerview, null);
        recyclerView = (RecyclerView) mRecylerView.findViewById(R.id.recycler_view_for_hr);


        second = mRecylerView.findViewById(R.id.secondrec);

        TextView HeaderTextView = (TextView) mRecylerView.findViewById(R.id.header);
        HeaderTextView.setText(mContext.getResources().getString(R.string.threebar) + " " + Header);
        HeaderTextView.setTypeface(font);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ThumbnailAdapter(thumbnailPOJOList, mContext, manager);

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Thumbnail_POJO thumbnailPOJO = thumbnailPOJOList.get(position);



                second.setVisibility(View.VISIBLE);

//                Intent intent = new Intent(mContext, ContentLibrary.class);
//                mContext.startActivity(intent);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData(Cat_Code, WhereQuery);

        return mRecylerView;
    }

    private void prepareMovieData(String Code, String whereQuery) {

        String Query = "";


        if (whereQuery.length() == 0)
            Query = "SELECT * FROM TBBRND where  col_1 = '" + Code + "'";
        else {
            Query = "SELECT * FROM TBBRND where col_1='" + Code + "' AND col_3 like '%" + whereQuery + "%'";
        }

        String[][] pdatat = databaseAdapter.genericSelect(Query, 9);


        Thumbnail_POJO thumbnailPOJO = null;

        if (pdatat != null) {
            for (int i = 0; i < pdatat.length; i++) {
                thumbnailPOJO = new Thumbnail_POJO(pdatat[i][0], pdatat[i][1], pdatat[i][2], pdatat[i][3],
                        pdatat[i][4], pdatat[i][5], pdatat[i][6], pdatat[i][7], pdatat[i][8]);
                thumbnailPOJOList.add(thumbnailPOJO);

            }
            mAdapter.notifyDataSetChanged();
        }

    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
