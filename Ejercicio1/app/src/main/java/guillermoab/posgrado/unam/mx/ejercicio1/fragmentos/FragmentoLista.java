package guillermoab.posgrado.unam.mx.ejercicio1.fragmentos;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
import guillermoab.posgrado.unam.mx.ejercicio1.sql.ItemDataSource;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class FragmentoLista extends Fragment{
    private ListView listaVista;
    //private List<ModeloElemento> arreglodatos = new ArrayList<>(); Por que ahora se va a guardar en una db
    private int contador;
    private boolean isImg;
    private ItemDataSource itemDataSource;
    private Button btnagregar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }

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
                //ModeloElemento modelItem2 = arreglodatos.get(position);
                Intent intent=new Intent(getActivity(), ActivityDetallesMas.class);
                intent.putExtra("Nombre",modelItem.elemento);
                intent.putExtra("id",modelItem.id);
                intent.putExtra("resourceid",modelItem.resourceid);
                startActivity(intent);
                //Toast.makeText(getActivity(),modelItem2.id,Toast.LENGTH_SHORT).show();
            }
        });
        List<ModeloElemento> modelItemList = itemDataSource.getAllItems();
        listaVista.setAdapter(new AdaptadorListaElemento(getActivity(),modelItemList));
        listaVista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AdaptadorListaElemento adapter = (AdaptadorListaElemento) parent.getAdapter();
                final ModeloElemento modelItem = adapter.getItem(position);
                String msg_del =  String.valueOf(R.string.delete_message);
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.delete_title)
                        .setMessage(String.format(msg_del + " %s?",modelItem.elemento))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemDataSource.deleteItem(modelItem);
                                listaVista.setAdapter(new AdaptadorListaElemento(getActivity(),itemDataSource.getAllItems()));
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();
                return true;
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
                    item.description="Descripcion "+contador;
                    item.resourceid=isImg?R.drawable.ic_action_accessibility:R.drawable.ic_action_face_unlock;
                    //arreglodatos.add(item); Se comenta por que ahora se guarda en una db
                    listaVista.setAdapter(new AdaptadorListaElemento(getActivity(),itemDataSource.getAllItems()));
                    isImg=!isImg;
                    contador++;
                    listaElementos.setText("");
                }
            }
        });
        return view;
    }
}
