package guillermoab.posgrado.unam.mx.ejercicio1;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class ActivityDetallesMas extends AppCompatActivity implements View.OnClickListener {
    private String nombre;
    private String id;
    private int resourceid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallesmas);
        if(getIntent()!=null) {
            nombre = getIntent().getExtras().getString("Nombre");
            id = getIntent().getExtras().getString("id");
            resourceid = getIntent().getExtras().getInt("resourceid");
            TextView name = (TextView) findViewById(R.id.activity_detalles2_txtperfil);
            ImageView img = (ImageView) findViewById(R.id.activity_detalles2_imgperfil);
            name.setText(nombre + "\n"+ id);
            img.setImageResource(resourceid);
        }else{
            Toast.makeText(getApplicationContext(),"Hubo un error al cargar los datos!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
