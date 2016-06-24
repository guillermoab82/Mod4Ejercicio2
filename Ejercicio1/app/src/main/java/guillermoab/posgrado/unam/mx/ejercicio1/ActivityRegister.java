package guillermoab.posgrado.unam.mx.ejercicio1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModelUser;
import guillermoab.posgrado.unam.mx.ejercicio1.sql.UserDataSource;

/**
 * Created by GuillermoAB on 23/06/2016.
 */
public class ActivityRegister extends AppCompatActivity {
    private UserDataSource userDataSource;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText mUser = (EditText) findViewById(R.id.txtUser_Register);
        final EditText mPwd = (EditText) findViewById(R.id.txtPassword_Register);
        findViewById(R.id.btnResgister_User).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUser.getText().toString();
                String pwd=mPwd.getText().toString();
                String date= new SimpleDateFormat("dd-MM-yyyy hh:mm").format(new Date());
                if(!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(pwd)){
                    ModelUser modelUser = new ModelUser();
                    modelUser.name=user;
                    modelUser.pwd=pwd;
                    modelUser.last_session=date;
                    modelUser.time_in="0";
                    userDataSource.saveUser(modelUser);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.msj_error_in,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
