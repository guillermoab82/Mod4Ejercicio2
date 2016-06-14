package guillermoab.posgrado.unam.mx.ejercicio1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import guillermoab.posgrado.unam.mx.ejercicio1.fragmentos.FragmentoLista;
import guillermoab.posgrado.unam.mx.ejercicio1.fragmentos.FragmentoPerfil;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class ActivityDetalles extends AppCompatActivity implements View.OnClickListener{
    private TextView txt;
    private Button btnPerfil;
    private Button btnLista;
    private String userName;

    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detalles);
        txt = (TextView) findViewById(R.id.activity_detalles_txtbienvenido);
        if(getIntent()!=null){
            userName=getIntent().getExtras().getString("usuario");
        }else{
            Toast.makeText(getApplicationContext(),"No hay dato!!!",Toast.LENGTH_SHORT).show();
        }
        txt.setText("Bienvenid@ al sistema " + userName);
        btnPerfil=(Button) findViewById(R.id.activity_detalles_btnperfil);
        btnPerfil.setOnClickListener(this);
        btnLista = (Button) findViewById(R.id.activity_detalles_btnlista);
        btnLista.setOnClickListener(this);
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
}
