package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatMessage extends Remote {
    void sendMessage(String message) throws RemoteException;
    void showMessage(String message) throws RemoteException;
}
