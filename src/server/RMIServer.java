// Used to start the server before the 'CreateWhiteBoard' and 'JoinWhiteBoard' classes were implemented.
//// Written by Michael Yixiao Wu | 1388097

package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            // This will start the registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);


            // Creating and exporting a remote object
            // Old from demo: RemoteMath remoteMath = new RemoteMath();
            // My implementation class:
            RemoteDrawingSpace remoteDrawingSpace = new RemoteDrawingSpace();
//            java.rmi.Naming.rebind("DrawingSpace", remoteDrawingSpace);

            // Binding the remote object (stub) in the registry
            registry.bind("DrawingSpace", remoteDrawingSpace);

            System.out.println("Drawing space ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}