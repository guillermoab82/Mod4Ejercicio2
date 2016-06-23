package guillermoab.posgrado.unam.mx.ejercicio1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModeloElemento;

/**
 * Created by GuillermoAB on 22/06/2016.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;

    public ItemDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }
    public void saveItem(ModeloElemento modelItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_NAME,modelItem.elemento);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_DESC,modelItem.description);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.resourceid);
        db.insert(MySqliteHelper.TABLE_ITEM_NAME,null,contentValues);
    }
    public void deleteItem(ModeloElemento modelItem){
        db.delete(MySqliteHelper.TABLE_ITEM_NAME,MySqliteHelper.COLUMN_ITEM_ID+"=?",
                new String[]{String.valueOf(modelItem.id)});
    }
    public List<ModeloElemento> getAllItems(){
        List<ModeloElemento> modelItemList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_ITEM_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_ID));
            String itemName=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            int resource_id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));
            ModeloElemento modelItem = new ModeloElemento();
            modelItem.id = id;
            modelItem.resourceid=resource_id;
            modelItem.description=description;
            modelItem.elemento = itemName;
            modelItemList.add(modelItem);
        }
        return modelItemList;
    }
}
