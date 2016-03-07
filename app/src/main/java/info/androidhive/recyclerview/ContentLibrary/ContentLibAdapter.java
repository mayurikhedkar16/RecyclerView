package info.androidhive.recyclerview.ContentLibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import info.androidhive.recyclerview.R;

/**
 * Created by GB on 3/7/16.
 */
public class ContentLibAdapter extends RecyclerView.Adapter<ContentLibAdapter.MyViewHolder> {


    private List<ContentLibThumbnail_POJO> moviesList;
    Typeface font;
    Context mContext;
    Integer Imagearr[] = {R.drawable.dempi, R.drawable.newace, R.drawable.newcfix1, R.drawable.newcfix3, R.drawable.newjade
            , R.drawable.newmezzo, R.drawable.newstillsep, R.drawable.solsuna, R.drawable.stelpep, R.drawable.zepine, R.drawable.stelpep, R.drawable.zepine,
            R.drawable.dempi, R.drawable.newace, R.drawable.newcfix1, R.drawable.newcfix3, R.drawable.newjade
            , R.drawable.newmezzo, R.drawable.newstillsep, R.drawable.solsuna, R.drawable.stelpep, R.drawable.zepine, R.drawable.stelpep, R.drawable.zepine};


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre, pageCount, threedotMenu, fb;
        FloatingActionButton refFloatingActionButton, pageFloatingActionButton;
        ImageView imageView;
        RelativeLayout layout;
        ScrollView childScrollView;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            imageView = (ImageView) view.findViewById(R.id.bandimage);
            pageCount = (TextView) view.findViewById(R.id.pagecount);
            threedotMenu = (TextView) view.findViewById(R.id.threedot);
            refFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.ref);

            layout = (RelativeLayout) view.findViewById(R.id.layout);


            childScrollView = (ScrollView) view.findViewById(R.id.childScroll);

            refFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            fb = (TextView) view.findViewById(R.id.close);
            fb.setTypeface(font);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    childScrollView.setVisibility(View.GONE);
                    fb.setVisibility(View.GONE);
                }
            });
            pageFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.page);
            pageFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    childScrollView.setVisibility(View.VISIBLE);
                    fb.setVisibility(View.VISIBLE);


                    int prevTextViewId = 0;
                    for (int i = 0; i < 15; i++) {
                        final TextView textView = new TextView(mContext);
                        textView.setText(i + "  Page Name");
                        textView.setTextColor(Color.parseColor("#FFFFFF"));

                        int curTextViewId = prevTextViewId + 1;
                        textView.setId(curTextViewId);
                        final RelativeLayout.LayoutParams params =
                                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                        params.addRule(RelativeLayout.BELOW, prevTextViewId);
                        params.setMargins(4, 4, 4, 4);
                        textView.setLayoutParams(params);

                        prevTextViewId = curTextViewId;
                        layout.addView(textView, params);
                    }
                }
            });


//            parentScrollView.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    Log.v(TAG, "PARENT TOUCH");
//
//                    findViewById(R.id.child_scroll).getParent()
//                            .requestDisallowInterceptTouchEvent(false);
//                    return false;
//                }
//            });

            childScrollView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //  Log.v(TAG, "CHILD TOUCH");

                    // Disallow the touch request for parent scroll on touch of  child view
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });


        }
    }


    public ContentLibAdapter(List<ContentLibThumbnail_POJO> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
        font = Typeface.createFromAsset(mContext.getAssets(),
                "fontawesome-webfont.ttf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tumbnail_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ContentLibThumbnail_POJO thumbnailPOJO = moviesList.get(position);
        holder.title.setText(thumbnailPOJO.getCategory_TITLE());
        holder.genre.setText(thumbnailPOJO.getCAT_CONTENT_SEQ());
        holder.year.setText(thumbnailPOJO.getCat_SEQ());
        holder.imageView.setImageResource(Imagearr[position]);
        holder.pageCount.setText(thumbnailPOJO.getCat_SEQ());


        // Set material sheet event listener

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
