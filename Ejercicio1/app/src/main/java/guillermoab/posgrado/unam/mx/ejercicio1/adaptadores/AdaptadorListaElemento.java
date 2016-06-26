package guillermoab.posgrado.unam.mx.ejercicio1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import guillermoab.posgrado.unam.mx.ejercicio1.R;
import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModeloElemento;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class AdaptadorListaElemento extends ArrayAdapter<ModeloElemento> {
    public AdaptadorListaElemento(Context context, List<ModeloElemento> objetos) {
        super(context, 0,objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=(LayoutInflater.from(parent.getContext())).inflate(R.layout.lista_renglon,parent,false);
        }
        TextView txtDescripcionElemento = (TextView) convertView.findViewById(R.id.lista_renglon_txtDescripcion);
        TextView txtTituloElemento = (TextView) convertView.findViewById(R.id.lista_renglon_txttitulo);
        ImageView imgElemento = (ImageView) convertView.findViewById(R.id.lista_renglon_imgrenglon);
        ModeloElemento modelItem = getItem(position);
        txtTituloElemento.setText(modelItem.elemento);
        txtDescripcionElemento.setText(modelItem.description);
        imgElemento.setImageResource(modelItem.resourceid);
        return convertView;
    }
}
