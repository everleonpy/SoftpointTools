package py.com.softpoint.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRecepOffLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnRecepOffLine = (Button) findViewById(R.id.btnRecepcionOffLine);
        btnRecepOffLine.setOnClickListener(this);

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.i("MenuActivity","Se punso back");
    }



    /**
     * Eventos de botones de menu
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent xint;

        switch (v.getId()){

            case R.id.btnRecepcionOffLine: {

                xint = new Intent(getApplicationContext(), ReceipOffActivity.class);
                startActivity(xint);


            }
            case R.id.btnSalir:{

                    
            }

            default:break;
        }



    }


}
