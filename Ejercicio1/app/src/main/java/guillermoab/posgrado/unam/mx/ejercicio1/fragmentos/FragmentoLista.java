package guillermoab.posgrado.unam.mx.ejercicio1.fragmentos;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import guillermoab.posgrado.unam.mx.ejercicio1.ActivityDetallesMas;
import guillermoab.posgrado.unam.mx.ejercicio1.R;
import guillermoab.posgrado.unam.mx.ejercicio1.adaptadores.AdaptadorListaElemento;
import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModeloElemento;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class FragmentoLista extends Fragment{
    private ListView listaVista;
    private List<ModeloElemento> arreglodatos = new ArrayList<>();
    private int contador;
    private boolean isImg;
    private Button btnagregar;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista,container,false);
        listaVista = (ListView) view.findViewById(R.id.fragment_lista_lvusuarios);
        listaVista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdaptadorListaElemento adaptador = (AdaptadorListaElemento) parent.getAdapter();
                ModeloElemento modelItem = adaptador.getItem(position);
                ModeloElemento modelItem2 = arreglodatos.get(position);
                Intent intent=new Intent(getActivity(), ActivityDetallesMas.class);
                intent.putExtra("Nombre",modelItem2.elemento);
                intent.putExtra("id",modelItem2.id);
                intent.putExtra("resourceid",modelItem2.resourceid);
                startActivity(intent);
                //Toast.makeText(getActivity(),modelItem2.id,Toast.LENGTH_SHORT).show();
            }
        });
        final EditText listaElementos = (EditText) view.findViewById(R.id.fragment_lista_txtuser);
        btnagregar = (Button) view.findViewById(R.id.fragment_lista_btnagregar);
        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato = listaElementos.getText().toString();
                if(!TextUtils.isEmpty(dato)){
                    ModeloElemento item = new ModeloElemento();
                    item.elemento=dato;
                    item.id="Descripcion "+contador;
                    item.resourceid=isImg?R.drawable.ic_action_accessibility:R.drawable.ic_action_face_unlock;
                    arreglodatos.add(item);
                    listaVista.setAdapter(new AdaptadorListaElemento(getActivity(),arreglodatos));
                    isImg=!isImg;
                    contador++;
                    listaElementos.setText("");
                }
            }
        });
        return view;
    }
}
