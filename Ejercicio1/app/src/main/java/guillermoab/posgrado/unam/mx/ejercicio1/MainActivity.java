package guillermoab.posgrado.unam.mx.ejercicio1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUser;
    private EditText txtPass;
    private Button btnIngresar;
    private View cargando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = (EditText) findViewById(R.id.activity_main_txt_user);
        txtPass = (EditText) findViewById(R.id.activity_main_txt_pwd);
        btnIngresar = (Button) findViewById(R.id.activity_main_btn_ingresar);
        btnIngresar.setOnClickListener(this);
        cargando = (View) findViewById(R.id.activity_main_pb_cargando);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_btn_ingresar:
                abriractividad();
                break;
        }
    }

    private void abriractividad() {
        final String usuario = txtUser.getText().toString();
        final String pwd = txtPass.getText().toString();
        cargando.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
               cargando.setVisibility(View.GONE);
                if(usuario!="" && pwd!=""){//Nos importa que tanto el usuario como el pass no sean vacíos
                    Toast.makeText(getApplicationContext(),"Ingresando",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ActivityDetalles.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR!!! Datos necesarios.",Toast.LENGTH_LONG).show();
                }
            }
        },1000*3);
    }
}
