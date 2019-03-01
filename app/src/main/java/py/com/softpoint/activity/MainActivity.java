package py.com.softpoint.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import py.com.softpoint.pojos.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_usuario, txt_password;
    private Button btnIngresar;
    private String error;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_usuario  = (EditText) findViewById(R.id.usuario);
        txt_password = (EditText) findViewById(R.id.password);

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()) {

            case R.id.btnIngresar: {
                Log.i("MainActivity", "Se pulzo el boton : "+v.getId());

                ClienteWsLogin cli = new ClienteWsLogin(getApplication());
                cli.execute(txt_usuario.getText().toString());
            }


            default: break;

        }

    }

    //
    //   Clase de Conexion a Ws
    //
    private class ClienteWsLogin extends AsyncTask<String, Integer, Usuario>{

        private static final String URL_BASE =  "http://192.168.0.11:8080/ServiceMobiles/rest/user/";
        ProgressDialog progressDialog;
        private Usuario resp;
        private Context ctx;

        public ClienteWsLogin(Context c){
            this.ctx = c;
        }


        // Se ejecuta antes del odIn
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Aguarde un momento",
                    "Conectando");

            resp = new Usuario();
        }

        @Override
        protected Usuario doInBackground(String... strings) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet getuser =  new HttpGet(URL_BASE+strings[0]);
            getuser.setHeader("content-type", "application/json");

            try {


                httpClient.getParams().setParameter("http.socket.timeout", new Integer(10*1000));
                HttpResponse httpr = httpClient.execute(getuser);

                if( httpr.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK ) {

                    String respStr = EntityUtils.toString(httpr.getEntity());
                    Log.i("ClienteWsLogin", "RESP : " + respStr);
                    JSONObject jsonObject = new JSONObject(respStr);

                    // Se carga el Usuario de Contexto
                    resp.setId(jsonObject.getLong("id"));
                    resp.setName(jsonObject.getString("name"));
                    resp.setNombreCompleto(jsonObject.getString("nombreCompleto"));
                    resp.setOrgId(jsonObject.getLong("orgId"));
                    resp.setPassword(jsonObject.getString("password"));
                    resp.setUnitId(jsonObject.getLong("unitId"));


                }else if ( httpr.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_NO_CONTENT){
                    resp = null;
                    error = "No existe este usuario";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ctx,"ERROR CODE : "+error,Toast.LENGTH_LONG).show();
                        }
                    });

                } else {

                    resp = null;
                   // Log.i("ClienteWsLogin", "ERROR CODE : "+httpr.getStatusLine().getStatusCode());
                    error = String.valueOf(httpr.getStatusLine().getStatusCode() );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ctx,"ERROR CODE : "+error,Toast.LENGTH_LONG).show();
                        }
                    });

                }


            }catch (Exception e){
                Log.e("ClienteWsLogin", e.getMessage() );
                resp = null;
                error = e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx,error,Toast.LENGTH_LONG).show();
                    }
                });

            }

            return resp;
        }



        // Se ejecuta durante la ejecucion del metodo doInBack
        @Override
        protected void onPostExecute(Usuario user) {
            super.onPostExecute(user);
            progressDialog.dismiss();

            if( resp != null ) {
                Log.i("ClienteWsLogin","User : "+user.getName() +"  Pass: "+user.getPassword());
                Log.i("ClienteWsLogin","User : "+txt_usuario.getText() +"  Pass: "+txt_password.getText());

                if (resp.getName().equalsIgnoreCase(txt_usuario.getText().toString()) &&
                    resp.getPassword().equalsIgnoreCase(txt_password.getText().toString()) )
                {


                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                    finish();


                }else{
                    error = "Credenciales invalidas";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ctx,error,Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

        }

        // Se ejecuta despues del metodo doInBac
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

    }
}
