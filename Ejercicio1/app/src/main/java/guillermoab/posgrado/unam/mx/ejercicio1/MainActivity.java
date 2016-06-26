package guillermoab.posgrado.unam.mx.ejercicio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModelUser;
import guillermoab.posgrado.unam.mx.ejercicio1.service.ServiceTimer;
import guillermoab.posgrado.unam.mx.ejercicio1.sql.UserDataSource;
import guillermoab.posgrado.unam.mx.ejercicio1.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUser;
    private EditText txtPass;
    private Button btnIngresar;
    private View cargando;
    private UserDataSource userDataSource;
    private PreferenceUtil util;
    private CheckBox chkRemember;
    private int lastTime=0;
    private String last_Time;
    private String shared_date="";
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = (EditText) findViewById(R.id.activity_main_txt_user);
        txtPass = (EditText) findViewById(R.id.activity_main_txt_pwd);
        btnIngresar = (Button) findViewById(R.id.activity_main_btn_ingresar);
        btnIngresar.setOnClickListener(this);
        findViewById(R.id.activity_main_btn_register).setOnClickListener(this);
        chkRemember = (CheckBox) findViewById(R.id.activity_chk_rememberme);
        cargando = (View) findViewById(R.id.activity_main_pb_cargando);
        userDataSource = new UserDataSource(getApplicationContext());
        //Comenzemos con la validación de sharedpreference
        util = new PreferenceUtil(getApplicationContext());
        ModelUser modeluser = util.getUser();
        if(modeluser!=null){
            if(modeluser.name.trim().length()>0 && modeluser.pwd.trim().length()>0){
                txtUser.setText(modeluser.name.toString());
                txtPass.setText(modeluser.pwd.toString());
            }
            if(modeluser.time_stamp.trim().length()>0){
                lastTime = Integer.valueOf(modeluser.time_stamp.toString());
            }else {
                last_Time="";
            }
            if(modeluser.last_session.trim().length()>0){
                shared_date=modeluser.last_session.toString();
            }
        }
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
                        date= new SimpleDateFormat("dd-MM-yyyy hh:mm").format(new Date());
                        if(chkRemember.isChecked()){
                            util.saveUser(new ModelUser(modelUserList.get(0).id,usuario,pwd,date,"0",String.valueOf(lastTime)));
                        }else {
                            util.saveUser(new ModelUser(0,"","",date,"0",String.valueOf(lastTime)));
                        }
                        Toast.makeText(getApplicationContext(),getResources().getText(R.string.msj_in),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ActivityDetalles.class);
                        intent.putExtra("id",modelUserList.get(0).id);
                        intent.putExtra("usuario",modelUserList.get(0).name);
                        intent.putExtra("pwd",modelUserList.get(0).pwd);
                        intent.putExtra("ischk",chkRemember.isChecked());
                        if(!TextUtils.isEmpty(shared_date)){
                            intent.putExtra("date",shared_date);
                        }else{
                            intent.putExtra("date",date);
                        }
                        intent.putExtra("count_val",lastTime);
                        startActivity(intent);
                        startService(new Intent(getApplicationContext(), ServiceTimer.class));
                        //finish();
                    }else{
                        Toast.makeText(getApplicationContext(),getResources().getText(R.string.msj_error_user),Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),getResources().getText(R.string.msj_error_in),Toast.LENGTH_LONG).show();
                }
            }
        },1000*3);
    }
}
