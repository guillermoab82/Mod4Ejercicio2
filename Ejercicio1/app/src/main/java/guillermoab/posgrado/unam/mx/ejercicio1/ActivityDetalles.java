package guillermoab.posgrado.unam.mx.ejercicio1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import guillermoab.posgrado.unam.mx.ejercicio1.fragmentos.FragmentoLista;
import guillermoab.posgrado.unam.mx.ejercicio1.fragmentos.FragmentoPerfil;
import guillermoab.posgrado.unam.mx.ejercicio1.service.ServiceTimer;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class ActivityDetalles extends AppCompatActivity implements View.OnClickListener{
    private TextView txt;
    private Button btnPerfil;
    private Button btnLista;
    private String userName;
    private TextView txtTimer;
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getExtras().getInt("timer");
            String msg = String.valueOf(R.string.session_time);
            txtTimer.setText(String.format(msg + "%s",counter));
        }
    };

    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detalles);

        txt = (TextView) findViewById(R.id.activity_detalles_txtbienvenido);
        if(getIntent()!=null){
            userName=getIntent().getExtras().getString("usuario");
            txt.setText(R.string.welcome + userName);
            btnPerfil=(Button) findViewById(R.id.activity_detalles_btnperfil);
            btnPerfil.setOnClickListener(this);
            btnLista = (Button) findViewById(R.id.activity_detalles_btnlista);
            btnLista.setOnClickListener(this);
            txtTimer = (TextView) findViewById(R.id.activity_detalles_txttimer);
        }else{
            Toast.makeText(getApplicationContext(),R.string.NoData,Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_detalles_btnperfil:
                cambiar_vista_perfil();
                break;
            case R.id.activity_detalles_btnlista:
                cambiar_vista_lista();
                break;
        }
    }

    private void cambiar_vista_lista() {
        getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new FragmentoLista()).commit();
    }

    private void cambiar_vista_perfil() {
        FragmentoPerfil f = FragmentoPerfil.newInstance(userName);
        getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,f).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,filter);
        Log.d(ServiceTimer.TAG,"OnResume, se reinicia boradcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ServiceTimer.TAG,"onPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTimer.TAG,"OnDestroy, terminando servicio");
        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }
}
