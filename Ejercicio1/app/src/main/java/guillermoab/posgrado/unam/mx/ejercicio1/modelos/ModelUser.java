package guillermoab.posgrado.unam.mx.ejercicio1.modelos;

/**
 * Created by GuillermoAB on 23/06/2016.
 */
public class ModelUser {
    public int id;
    public String name;
    public String pwd;
    public String last_session;
    public String time_in;
    public String time_stamp;

    public ModelUser(int id, String name,String pwd,String last_session,String time_in,String time_stamp){
        this.id=id;
        this.name=name;
        this.pwd=pwd;
        this.last_session=last_session;
        this.time_in=time_in;
        this.time_stamp=time_stamp;
    }

}
