package py.com.softpoint.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import py.com.softpoint.database.ConexionDbHelper;
import py.com.softpoint.database.UtilsDb;
import py.com.softpoint.pojos.Usuario;

public class ImportUsuarios {

    private Context context;

    public ImportUsuarios(Context context) {
        this.context = context;
    }


    public void RunProcess(){

        aImportarUsuarios aim = new aImportarUsuarios();
        aim.execute();
    }


    /**
    * Cliente Rest para Importar Usuarios de la Base de Datos
    */
    private class aImportarUsuarios extends AsyncTask<String, Integer, Usuario>{

        private static final String URL_BASE =  "http://192.168.0.11:8080/ServiceMobiles/rest/user/all";
        ProgressDialog progressDialog;


        @Override
        protected Usuario doInBackground(String... strings) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet getusers =  new HttpGet(URL_BASE);
            getusers.setHeader("content-type", "application/json");

            try {

                httpClient.getParams().setParameter("http.socket.timeout", new Integer(10*1000));
                HttpResponse httpr = httpClient.execute(getusers);

                    if( httpr.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK){

                        String respStr = EntityUtils.toString(httpr.getEntity());
                        JSONArray listUser = new JSONArray(respStr);

                        for( int i = 0; i < listUser.length(); i++){
                            JSONObject obj = listUser.getJSONObject(i);

                            Usuario usr = new Usuario();
                            usr.setId(obj.getLong("id"));
                            usr.setName(obj.getString("name"));
                            usr.setNombreCompleto(obj.getString("nombreCompleto"));
                            usr.setPassword(obj.getString("password"));
                            usr.setOrgId(obj.getLong("orgId"));
                            usr.setUnitId(obj.getLong("unitId"));

                            Log.i("USER", " id :"+usr.getId());


                        }

                    }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

}
