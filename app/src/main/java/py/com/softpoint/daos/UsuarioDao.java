package py.com.softpoint.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import py.com.softpoint.database.ConexionDbHelper;
import py.com.softpoint.database.UtilsDb;
import py.com.softpoint.pojos.Usuario;


/**
*  DAO para Tabla de Usuarios
*/
public class UsuarioDao implements GenericDAO<Usuario> {

    private SQLiteDatabase db;


    public UsuarioDao(Context ctx) {
        ConexionDbHelper conn = new ConexionDbHelper(ctx, UtilsDb.DB_NAME,null,UtilsDb.VERSION_DB);
        db = conn.getWritableDatabase();
    }


    /**
    * Guardar un Usuario
    * @param usuario
    * @return
    * @throws Exception
    */
    @Override
    public long guardar(Usuario usuario) throws Exception {

        ContentValues entity = new ContentValues();

        entity.put(UtilsDb.F_USUARIO_ID, usuario.getId());
        entity.put(UtilsDb.F_USUARIO_NAME, usuario.getName());
        entity.put(UtilsDb.F_USUARIO_NOMBRE_COMPLETO, usuario.getNombreCompleto());
        entity.put(UtilsDb.F_USUARIO_PASSWORD, usuario.getPassword());
        entity.put(UtilsDb.F_USUARIO_ORG_ID, usuario.getOrgId());
        entity.put(UtilsDb.F_USUARIO_UNIT_ID, usuario.getUnitId());

        return  db.insert(UtilsDb.TAB_USUARIO,null, entity);

    }


    @Override
    public boolean modificar(Usuario usuario) throws Exception {

        ContentValues entity = new ContentValues();

        entity.put(UtilsDb.F_USUARIO_ID, usuario.getId());
        entity.put(UtilsDb.F_USUARIO_NAME, usuario.getName());
        entity.put(UtilsDb.F_USUARIO_NOMBRE_COMPLETO, usuario.getNombreCompleto());
        entity.put(UtilsDb.F_USUARIO_PASSWORD, usuario.getPassword());
        entity.put(UtilsDb.F_USUARIO_ORG_ID, usuario.getOrgId());
        entity.put(UtilsDb.F_USUARIO_UNIT_ID, usuario.getUnitId());

        String where = UtilsDb.F_USUARIO_ID+" = ?";
        String[] args = {String.valueOf( usuario.getId() )};

        int r = db.update(UtilsDb.TAB_USUARIO, entity, where, args);

        if( r > 0 ) { return  true; }
        else { return false ;}

    }


    @Override
    public boolean borrar(Usuario usuario) throws Exception {

        String where = UtilsDb.F_USUARIO_ID+" = ?";
        String[] args = {String.valueOf( usuario.getId() )};

        int r = db.delete(UtilsDb.TAB_USUARIO, where, args);

        if( r > 0 ) { return  true; }
        else { return false ;}

    }


    /**
    * Recupera un Usuario apartir del id
    * @param id de Usuario
    * @return Usuario
    */
    @Override
    public Usuario buscar(Long id) {

        Usuario resp = null;

        String[] colums = {UtilsDb.F_USUARIO_ID, UtilsDb.F_USUARIO_NAME, UtilsDb.F_USUARIO_NOMBRE_COMPLETO,
                           UtilsDb.F_USUARIO_PASSWORD, UtilsDb.F_USUARIO_ORG_ID, UtilsDb.F_USUARIO_UNIT_ID};
        String where = UtilsDb.F_USUARIO_ID+" = ?";
        String[] args  = {String.valueOf(id)};

        Cursor cur = db.query(UtilsDb.TAB_USUARIO, colums, where, args, null,null, null);

            if( cur.moveToFirst() ){
                resp = setValues(cur);
            }

        return resp;
    }


    /**
     * Retorna un usario a partir del nombre
     * @param nombre
     * @return Usuario
     */
    public Usuario buscar(String nombre) {

        Usuario resp = null;

        String[] colums = {UtilsDb.F_USUARIO_ID, UtilsDb.F_USUARIO_NAME, UtilsDb.F_USUARIO_NOMBRE_COMPLETO,
                UtilsDb.F_USUARIO_PASSWORD, UtilsDb.F_USUARIO_ORG_ID, UtilsDb.F_USUARIO_UNIT_ID};
        String where = UtilsDb.F_USUARIO_NAME+" = ?";
        String[] args  = {nombre};

        Cursor cur = db.query(UtilsDb.TAB_USUARIO, colums, where, args, null,null, null);

            if( cur.moveToFirst() ){
                resp = setValues(cur);
            }

        return resp;
    }


    /**
    *
    * @param siteId
    * @return
    */
    public List<Usuario> getAllbySiteId(Long siteId){

        List<Usuario> resp = null;

        String[] colums = {UtilsDb.F_USUARIO_ID, UtilsDb.F_USUARIO_NAME, UtilsDb.F_USUARIO_NOMBRE_COMPLETO,
                UtilsDb.F_USUARIO_PASSWORD, UtilsDb.F_USUARIO_ORG_ID, UtilsDb.F_USUARIO_UNIT_ID};
        String where = UtilsDb.F_USUARIO_NAME+" = ?";
        String[] args  = {String.valueOf(siteId)};

        Cursor cur = db.query(UtilsDb.TAB_USUARIO, colums, where, args, null,null, null);

            do {
                Usuario x = setValues(cur);
                resp.add(x);
            }while (cur.moveToFirst());

        return resp;
    }


    /**
    *
    * @param cur
    * @return
    */
    private Usuario setValues(Cursor cur){

        Usuario usr = new Usuario();

        usr.setId(cur.getLong(cur.getColumnIndex( UtilsDb.F_USUARIO_ID )));
        usr.setName(cur.getString(cur.getColumnIndex( UtilsDb.F_USUARIO_NAME )));
        usr.setNombreCompleto(cur.getString(cur.getColumnIndex(UtilsDb.F_USUARIO_NOMBRE_COMPLETO)));
        usr.setPassword(cur.getString(cur.getColumnIndex(UtilsDb.F_USUARIO_PASSWORD)));
        usr.setOrgId(cur.getLong(cur.getColumnIndex(UtilsDb.F_USUARIO_ORG_ID)));
        usr.setUnitId(cur.getLong(cur.getColumnIndex(UtilsDb.F_USUARIO_UNIT_ID)));

        return usr;
    }



}
