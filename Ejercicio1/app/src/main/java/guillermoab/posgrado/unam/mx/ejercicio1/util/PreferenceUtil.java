package guillermoab.posgrado.unam.mx.ejercicio1.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GuillermoAB on 24/06/2016.
 */
public class PreferenceUtil {
    private static final String FILE_NAME="unam_pref";
    private final SharedPreferences sp;

    public PreferenceUtil (Context context) {
        sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }
}
