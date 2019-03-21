package spilklient;

import model.LokalBruger;
import spilserver.SpilInterface;
import spilserver.SpilserverInterface;
import view.Userinterface;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SpillerKlient {
    static int score= 100;

    public static void main(String[] arg)  throws MalformedURLException, RemoteException, NotBoundException, URISyntaxException, NullPointerException {

       // SpilserverInterface serverinterface = (SpilserverInterface) Naming.lookup("rmi://localhost:4107/galgespillet");
        SpilserverInterface serverinterface = (SpilserverInterface) Naming.lookup("rmi://130.225.170.204:4107/galgespillet");
        Userinterface gui = new Userinterface();
        LokalBruger bruger = gui.loginDialog();
        String temp= "Failed";
        while(temp.equals("Failed")){
            try {
                temp=serverinterface.login(bruger.getNavn(),bruger.getPassword());

            } catch (Exception e) {
                int close = gui.failedLogin();
                if (close == JOptionPane.CLOSED_OPTION) {
                    System.exit(0);
                }
                bruger = gui.loginDialog();

            }

        }

        bruger.setSessionid(temp);
        gui.showhighscore(serverinterface.gethighscore());
        Boolean vundet = false;
        try {
            if (!bruger.getSessionid().equals("Failed")) {
                SpilInterface spil= serverinterface.startSpil(bruger.getNavn(),bruger.getSessionid());
                if(spil.erSpilletSlut()){
                    spil.nulstil(bruger.getNavn(),bruger.getSessionid());
                }



                while (!spil.erSpilletSlut()) {
                    if(vundet){
                        gui.showhighscore(serverinterface.gethighscore());
                    }
                    int res = gui.resultatDialog(spil.getSynligtOrd(), spil.getBrugteBogstaver(), spil.getAntalForkerteBogstaver());
                    if (res == JOptionPane.CLOSED_OPTION) {
                        break;
                    }

                    String tast = gui.gætBogstavDialog();
                    spil.gætBogstav(tast,bruger.getNavn(),bruger.getSessionid());

                    int resultat = 99;
                    if (spil.erSpilletVundet()) {
                        score= score + spil.getordet().length()*10-spil.getAntalForkerteBogstaver()*10;
                        resultat = gui.afslutningsDialog("Spillet er slut, du har vundet! Ordet var ! "+spil.getordet()+" Din Score er " + score +" vil du spille igen?", "Success!");
                        serverinterface.addnewScore(bruger.getNavn(),score);
                        vundet=true;
                    }

                    if (spil.erSpilletTabt()) {
                        resultat = gui.afslutningsDialog("Spillet er slut, du har tabt! Ordet var"+ spil.getordet()+" vil du spille igen?", "Failure!");

                    }
                    if (resultat == JOptionPane.YES_OPTION) {
                        spil.nulstil(bruger.getNavn(),bruger.getSessionid());
                    }
                    if (resultat == JOptionPane.NO_OPTION) {
                        break;
                    }

                }
            }

        } catch (NullPointerException e){
            serverinterface.logud(bruger.getNavn());
        }
        }



}
