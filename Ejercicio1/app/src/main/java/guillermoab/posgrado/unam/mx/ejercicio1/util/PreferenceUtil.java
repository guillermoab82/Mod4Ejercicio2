package guillermoab.posgrado.unam.mx.ejercicio1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import guillermoab.posgrado.unam.mx.ejercicio1.modelos.ModelUser;

/**
 * Created by GuillermoAB on 24/06/2016.
 */
public class PreferenceUtil {
    private static final String FILE_NAME="unam_pref";
    private final SharedPreferences sp;

    public PreferenceUtil (Context context) {
        sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }
    public void saveUser(ModelUser modelUser){
        if(modelUser!=null){
            sp.edit().putString("id",String.valueOf(modelUser.id)).apply();
            sp.edit().putString("user_name",modelUser.name).apply();
            sp.edit().putString("user_pwd",modelUser.pwd).apply();
            sp.edit().putString("user_date",modelUser.last_session).apply();
            sp.edit().putString("timestamp",modelUser.time_stamp).apply();
        }
    }
    public ModelUser getUser(){
        String mUserID = sp.getString("id",null);
        String mUSerName = sp.getString("user_name",null);
        String mUserPWD =sp.getString("user_pwd",null);
        String mUserDate = sp.getString("user_date",null);
        String mUserTime = sp.getString("timestamp",null);
        int id;
        if((TextUtils.isEmpty(mUserID) || TextUtils.isEmpty(mUserDate)) ){
            if(TextUtils.isEmpty(mUserTime)){
                return  null;
            }else{
                return new ModelUser(0,mUSerName,mUserPWD,mUserDate,"0",mUserTime);
            }
        }else{
            return new ModelUser(Integer.valueOf(mUserID),mUSerName,mUserPWD,mUserDate,"0",mUserTime);
        }
    }
}
