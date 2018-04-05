package ro.utcluj.sd;

import java.util.Base64;

import ro.utcluj.sd.dal.PlayerDAO;
import ro.utcluj.sd.model.Player;

public class Login {

    private PlayerDAO p;

    public PlayerDAO getP() {
        return p;
    }

    public int loginWrap(String uiemail, String uipass){

        String dbpassencoded, dbpassdecoded;

        String encrypted = new String(Base64.getEncoder().encode(uipass.getBytes()));

        //PlayerDAO p = new PlayerDAO();
        p = new PlayerDAO();
        Player pp =null;

        pp = p.findByEmail(uiemail);
        if(pp==null)
            return -1;

        dbpassencoded = pp.getPassword();


        dbpassdecoded = new String(Base64.getDecoder().decode(dbpassencoded.getBytes()));

        if(dbpassencoded == ""){
            return -1;
        }
        System.out.println(pp.toString());
        if(dbpassdecoded.equals((String)uipass)){
            //System.out.println("Avem parola! " + uipass);

            boolean adminRights = pp.getAdmin();

            if(adminRights){
                return 2;
            }else{
                return 1;
            }
        }
        return 0;
    }



}
