package guillermoab.posgrado.unam.mx.ejercicio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModelUser;
import guillermoab.posgrado.unam.mx.ejercicio1.service.ServiceTimer;
import guillermoab.posgrado.unam.mx.ejercicio1.sql.UserDataSource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUser;
    private EditText txtPass;
    private Button btnIngresar;
    private View cargando;
    private UserDataSource userDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = (EditText) findViewById(R.id.activity_main_txt_user);
        txtPass = (EditText) findViewById(R.id.activity_main_txt_pwd);
        btnIngresar = (Button) findViewById(R.id.activity_main_btn_ingresar);
        btnIngresar.setOnClickListener(this);
        findViewById(R.id.activity_main_btn_register).setOnClickListener(this);
        cargando = (View) findViewById(R.id.activity_main_pb_cargando);
        userDataSource = new UserDataSource(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_btn_ingresar:
                abriractividad();
                break;
            case R.id.activity_main_btn_register:
                launchRegister();
        }
    }

    private void launchRegister() {
        Intent intent = new Intent(getApplicationContext(), ActivityRegister.class);
        startActivity(intent);
    }

    private void abriractividad() {
        final String usuario = txtUser.getText().toString();
        final String pwd = txtPass.getText().toString();
        cargando.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
               cargando.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(pwd)){//Nos importa que tanto el usuario como el pass no sean vacíos
                    List<ModelUser> modelUserList=userDataSource.getUser(usuario,pwd);
                    if(!modelUserList.isEmpty()){
                        Toast.makeText(getApplicationContext(),R.string.msj_in,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ActivityDetalles.class);
                        intent.putExtra("usuario",modelUserList.get(0).name);
                        startActivity(intent);
                        startService(new Intent(getApplicationContext(), ServiceTimer.class));
                    }else{
                        Toast.makeText(getApplicationContext(),R.string.msj_error_user,Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),R.string.msj_error_in,Toast.LENGTH_LONG).show();
                }
            }
        },1000*3);
    }
}
