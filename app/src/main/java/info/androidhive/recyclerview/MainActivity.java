package info.androidhive.recyclerview;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.db.utils.DatabaseAdapter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AlphaList.AlphaListActivity;
import AlphaList.AlphabetsList;
import AlphaList.DrList_POJO;

public class MainActivity extends AlphaListActivity {
    private List<Thumbnail_POJO> thumbnailPOJOList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ThumbnailAdapter mAdapter;

    View view;
    LinearLayout RHS_Deatailing;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        //  backupDatabase();
        view = findViewById(R.id.viewid);


        RHS_Deatailing = (LinearLayout) view.findViewById(R.id.rhsdetaling);
        LinearLayout lhsLinearLayout = (LinearLayout) view.findViewById(R.id.lhs);

        AlphabetsList alphabetsList = new AlphabetsList(this);
        lhsLinearLayout.addView(alphabetsList.getAlphabestListView("TBPARTY", false, false, false));
        alphabetsList.setSidepannel(View.VISIBLE);
        alphabetsList.SerachViewVis(View.VISIBLE);


        defaultLayout();


    }

    public final boolean backupDatabase() {
        File from = MainActivity.this.getDatabasePath("CWEPV8");
        File to = this.getBackupDatabaseFile();
        try {
            FileUtils.copyFile(from, to);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("Oho", "Error backuping up database: " + e.getMessage(), e);
        }
        return false;
    }

    public File getBackupDatabaseFile() {
        File dir = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/backupCW");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, "THE" + "_" + "CWEPV8");
    }

    public void defaultLayout() {
        String[][] pdatat = databaseAdapter.genericSelect("SELECT * FROM TBBRND group by col_2", 9);
        if (RHS_Deatailing != null)
            RHS_Deatailing.removeAllViews();

        for (int i = 0; i < pdatat.length; i++) {
            HorizontalRecylerView horizontalRecylerView = new HorizontalRecylerView(this, pdatat[i][1], pdatat[i][0], "");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 215);
            params.setMargins(0, 0, 0, 10);
            RHS_Deatailing.addView(horizontalRecylerView.getHorizontalRecylerView(getSupportFragmentManager()),
                    new LinearLayout.LayoutParams(params));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                databaseAdapter.open();

                if (!s.equals("")) {
                    String[][] pdatat = databaseAdapter.genericSelect("SELECT * FROM TBBRND where col_3 like '%" + s + "%' group by col_2", 9);
                    RHS_Deatailing.removeAllViews();
                    if (pdatat != null)
                        for (int i = 0; i < pdatat.length; i++) {
                            HorizontalRecylerView horizontalRecylerView = new HorizontalRecylerView(MainActivity.this, pdatat[i][1], pdatat[i][0], s);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 215);
                            params.setMargins(0, 0, 0, 10);

                            RHS_Deatailing.addView(horizontalRecylerView.getHorizontalRecylerView(getSupportFragmentManager()),
                                    new LinearLayout.LayoutParams(params));
                        }
                } else {
                    defaultLayout();
                }

                return true;
            }
        });
        return true;


    }


    @Override
    public void onItemListClick(DrList_POJO objDrListPOJO) {
        super.onItemListClick(objDrListPOJO);



    }

    @Override
    public void onItemListMenuClick(DrList_POJO objDrListPOJO) {
        super.onItemListMenuClick(objDrListPOJO);
        Toast.makeText(MainActivity.this, "objDrListPOJO" + objDrListPOJO.getCOL7(), Toast.LENGTH_SHORT).show();

    }
}
