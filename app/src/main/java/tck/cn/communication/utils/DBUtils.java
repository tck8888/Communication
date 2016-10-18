package tck.cn.communication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :数据库操作类
 * <p>
 * Created by tck on 2016/10/17.
 */

public class DBUtils {

    private static Context sContext;
    private static boolean isInit;

    public static void initDB(Context context) {
        sContext = context.getApplicationContext();
        isInit = true;
    }

    public static List<String> getContacts(String username) {
        if (!isInit) {
            throw new RuntimeException("使用DBUtils之前请 现在Application中初始化！");
        }
        ContactSQLiteOpenHelper dbHelper = new ContactSQLiteOpenHelper(sContext);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query(ContactSQLiteOpenHelper.T_CONTACT,
                new String[]{ContactSQLiteOpenHelper.CONTACT},
                ContactSQLiteOpenHelper.USERNAME + "=?", new String[]{username},
                null, null, ContactSQLiteOpenHelper.CONTACT);

        ArrayList<String> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            list.add(string);
        }
        cursor.close();
        readableDatabase.close();
        return list;
    }

    /**
     * 更新联系人
     * 1.先删除username的所有联系人
     * 2.再将contactsList所有的联系人添加进去
     *
     * @param username
     * @param contactsList
     */
    public static void updateContacts(String username, List<String> contactsList) {
        ContactSQLiteOpenHelper contactSQLiteOpenHelper = new ContactSQLiteOpenHelper(sContext);
        SQLiteDatabase db = contactSQLiteOpenHelper.getWritableDatabase();
        db.beginTransaction();
        db.delete(ContactSQLiteOpenHelper.T_CONTACT, ContactSQLiteOpenHelper.USERNAME + "=?", new String[]{username});

        ContentValues values = new ContentValues();

        values.put(ContactSQLiteOpenHelper.USERNAME, username);
        for (int i = 0; i < contactsList.size(); i++) {
            String s = contactsList.get(i);
            values.put(ContactSQLiteOpenHelper.CONTACT, s);
            db.insert(ContactSQLiteOpenHelper.T_CONTACT, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
}
