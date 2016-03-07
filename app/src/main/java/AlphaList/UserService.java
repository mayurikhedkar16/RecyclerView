package AlphaList;

import android.content.Context;
import android.database.Cursor;

import com.db.utils.DatabaseAdapter;

import java.util.Collections;
import java.util.Vector;

public class UserService {

    public static Vector<DrList_POJO> getUserList(Context ctx, String TabelName) {
        Vector<DrList_POJO> drListPOJOList = new Vector<DrList_POJO>();

        DrList_POJO drListPOJO;

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(ctx);
        databaseAdapter.open();

        // Get all sampleData from db
        try {
            Cursor cursor = databaseAdapter.getAllData(TabelName, null);
            cursor.moveToFirst();

            if (cursor.getCount() != 0) {
                drListPOJOList = new Vector<DrList_POJO>();
                do {
                    drListPOJO = new DrList_POJO();
                    drListPOJO.setCOL1(cursor.getString(cursor.getColumnIndex("COL1")));
                    drListPOJO.setCOL2(cursor.getString(cursor.getColumnIndex("COL2")));
                    drListPOJO.setCOL3(cursor.getString(cursor.getColumnIndex("COL3")));
                    drListPOJO.setCOL4(cursor.getString(cursor.getColumnIndex("COL4")));
                    drListPOJO.setCOL5(cursor.getString(cursor.getColumnIndex("COL5")));
                    drListPOJO.setCOL6(cursor.getString(cursor.getColumnIndex("COL6")));
                    drListPOJO.setCOL7(cursor.getString(cursor.getColumnIndex("COL7")));
                    drListPOJO.setCOL8(cursor.getString(cursor.getColumnIndex("COL8")));
                    drListPOJO.setCOL9(cursor.getString(cursor.getColumnIndex("COL9")));
                    drListPOJO.setCOL10(cursor.getString(cursor.getColumnIndex("COL10")));
                    drListPOJO.setCOL11(cursor.getString(cursor.getColumnIndex("COL11")));
                    drListPOJO.setCOL12(cursor.getString(cursor.getColumnIndex("COL12")));
                    drListPOJO.setCOL13(cursor.getString(cursor.getColumnIndex("COL13")));


                    drListPOJOList.add(drListPOJO);
                } while (cursor.moveToNext());
            }

            // deactivate and closing cursor
            cursor.deactivate();
            cursor.close();

            // Closing db connection
            databaseAdapter.close();

            Collections.sort(drListPOJOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drListPOJOList;
    }

}
