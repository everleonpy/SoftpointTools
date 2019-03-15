package py.com.softpoint.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Manager de Base de Datos
 */
public class ConexionDbHelper  extends SQLiteOpenHelper {
    private static final String TAG = "SQL_DB";

    public ConexionDbHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(UtilsDb.CREAR_TABLA_USUARIO);
        }catch (Exception e){
            Log.i(TAG, "ERROR al crear la Base de datos : "+ e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
