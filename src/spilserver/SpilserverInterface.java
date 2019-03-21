package spilserver;

import java.rmi.RemoteException;
import java.util.Map;

public interface SpilserverInterface extends java.rmi.Remote {

    String login(String navn, String password) throws java.rmi.RemoteException, Exception;

    void logud(String navn) throws java.rmi.RemoteException;

    SpilInterface startSpil(String bruger, String id) throws java.rmi.RemoteException;

    Map<String, Integer> gethighscore() throws RemoteException;

    void addnewScore(String navn, int score) throws RemoteException;
}
