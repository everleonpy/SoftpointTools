package py.com.softpoint.database;

public class UtilsDb {


    public static final String DB_NAME = "appmgr";
    public static final String TAB_USUARIO = "USUARIOS";

    public static final String F_ID = "id";
    public static final String F_NAME = "name";
    public static final String F_NOMBRE_COMPLETO = "nombreCompleto";
    public static final String F_PASSWORD = "password";
    public static final String F_ORG_ID = "orgId";
    public static final String F_UNIT_ID =  "unitId";


    public static final String CREAR_TABLA_USUARIO = "create table "+TAB_USUARIO+"( "+  F_ID+" INTEGER PRIMARY KEY, "+
                                                                                        F_NAME+" TEXT, "+
                                                                                        F_NOMBRE_COMPLETO+" TEXT, "+
                                                                                        F_PASSWORD+" TEXT, "+
                                                                                        F_ORG_ID+" INTEGER , "+
                                                                                        F_UNIT_ID+" INTEGER )";

}
