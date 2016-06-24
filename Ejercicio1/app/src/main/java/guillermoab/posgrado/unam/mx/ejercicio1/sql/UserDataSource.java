package guillermoab.posgrado.unam.mx.ejercicio1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModelUser;
import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModeloElemento;

/**
 * Created by GuillermoAB on 23/06/2016.
 */
public class UserDataSource {
    private final SQLiteDatabase db;

    public UserDataSource(Context context){
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }
    public void saveUser(ModelUser modelUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_USER_NAME,modelUser.name);
        contentValues.put(MySqliteHelper.COLUMN_USER_PWD,modelUser.pwd);
        contentValues.put(MySqliteHelper.COLUMN_USER_LASTSESSION,modelUser.last_session);
        contentValues.put(MySqliteHelper.COLUMN_USER_TIMEINAPP,modelUser.time_in);
        db.insert(MySqliteHelper.TABLE_USER_NAME,null,contentValues);
    }
    public void deletUser(ModelUser modelUser){
        db.delete(MySqliteHelper.TABLE_USER_NAME,MySqliteHelper.COLUMN_USER_ID+"=?",new String[]{String.valueOf(modelUser.id)});
    }
    public List<ModelUser> getAllUsers(){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_USER_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_ID));
            String userName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME));
            String userPWD = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PWD));
            String userLastSession = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTSESSION));
            String userTimeIn = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_TIMEINAPP));
            ModelUser modelUser = new ModelUser();
            modelUser.id=id;
            modelUser.name=userName;
            modelUser.pwd=userPWD;
            modelUser.last_session=userLastSession;
            modelUser.time_in=userTimeIn;
            modelUserList.add(modelUser);
        }
        return modelUserList;
    }
    public List<ModelUser> getUser(String user, String pwd){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_USER_NAME,null,MySqliteHelper.COLUMN_USER_NAME+"=? and "+MySqliteHelper.COLUMN_USER_PWD+"=?",new String[]{user,pwd},null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_ID));
            String userName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME));
            String userPWD = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PWD));
            String userLastSession = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTSESSION));
            String userTimeIn = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_TIMEINAPP));
            ModelUser modelUser = new ModelUser();
            modelUser.id=id;
            modelUser.name=userName;
            modelUser.pwd=userPWD;
            modelUser.last_session=userLastSession;
            modelUser.time_in=userTimeIn;
            modelUserList.add(modelUser);
        }
        return modelUserList;
    }
    public List<ModelUser> getUserByID(String shared_id){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_USER_NAME,null,MySqliteHelper.COLUMN_USER_ID+"=?",new String[]{shared_id},null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_ID));
            String userName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME));
            String userPWD = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PWD));
            String userLastSession = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTSESSION));
            String userTimeIn = cursor.getColumnName(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_TIMEINAPP));
            ModelUser modelUser = new ModelUser();
            modelUser.id=id;
            modelUser.name=userName;
            modelUser.pwd=userPWD;
            modelUser.last_session=userLastSession;
            modelUser.time_in=userTimeIn;
            modelUserList.add(modelUser);
        }
        return modelUserList;
    }
}
