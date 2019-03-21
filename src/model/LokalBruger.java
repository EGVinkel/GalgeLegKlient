package model;


public class LokalBruger {
    String password;
    String navn;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    String sessionid;
    public LokalBruger(String navn, String password){
        this.navn= navn;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getNavn() {
        return navn;
    }





}
