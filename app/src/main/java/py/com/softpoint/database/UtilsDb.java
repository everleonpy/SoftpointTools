package py.com.softpoint.database;

import java.util.StringTokenizer;

public class UtilsDb {


    public static final String DB_NAME = "appmgr";
    public static final int    VERSION_DB=1;
    public static final String TAB_USUARIO = "USUARIOS";

    public static final String F_USUARIO_ID = "id";
    public static final String F_USUARIO_NAME = "name";
    public static final String F_USUARIO_NOMBRE_COMPLETO = "nombreCompleto";
    public static final String F_USUARIO_PASSWORD = "password";
    public static final String F_USUARIO_ORG_ID = "orgId";
    public static final String F_USUARIO_UNIT_ID =  "unitId";


    public static final String CREAR_TABLA_USUARIO = "create table "+TAB_USUARIO+"( "+  F_USUARIO_ID+" INTEGER PRIMARY KEY, "+
                                                                                        F_USUARIO_NAME+" TEXT, "+
                                                                                        F_USUARIO_NOMBRE_COMPLETO+" TEXT, "+
                                                                                        F_USUARIO_PASSWORD+" TEXT, "+
                                                                                        F_USUARIO_ORG_ID+" INTEGER , "+
                                                                                        F_USUARIO_UNIT_ID+" INTEGER )";




    public static final String TAB_HEAD_RECEPCIONO = "HEAD_RECEPCION";


    public static final String CREAR_HAED_RECEPCION = new StringBuilder( "create table " +TAB_HEAD_RECEPCIONO
                                                                        +"()" ).toString();
}
