package guillermoab.posgrado.unam.mx.ejercicio1.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import guillermoab.posgrado.unam.mx.ejercicio1.R;

/**
 * Created by GuillermoAB on 14/06/2016.
 */
public class FragmentoPerfil extends Fragment{
    public static FragmentoPerfil newInstance(String nombre){
        FragmentoPerfil f = new FragmentoPerfil();
        Bundle b= new Bundle();
        b.putString("usuario",nombre);
        f.setArguments(b);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        ImageView imgPerfil = (ImageView) view.findViewById(R.id.fragment_perfil_imgperfil);
        TextView txtPerfil = (TextView) view.findViewById(R.id.fragment_perfil_txtuser);
        Bundle bundle = getArguments();
        String usuario = bundle.getString("usuario");
        int letra = usuario.charAt(0);
        if((letra>=65 && letra<=77) || (letra>=97 && letra<=109)){
           imgPerfil.setImageResource(R.drawable.ic_action_account_child);
        }else{
            if((letra>=78 && letra<=90) || (letra>=110 && letra<=122)){
                imgPerfil.setImageResource(R.drawable.ic_action_face_unlock);
            }
        }
        txtPerfil.setText(usuario);
        return view;
    }
}
