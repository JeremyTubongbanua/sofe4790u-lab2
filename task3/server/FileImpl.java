import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class FileImpl extends UnicastRemoteObject implements FileInterface {

    protected FileImpl() throws RemoteException {
        super();
    }

    @Override
    public byte[] downloadFile(String fileName) {
        try {
            String clientHost;
            try {
                clientHost = RemoteServer.getClientHost();
            } catch (ServerNotActiveException e) {
                clientHost = "Unknown";
            }

            System.out.println("Client requested file: " + fileName + " from IP: " + clientHost);

            File file = new File(fileName);
            byte buffer[] = new byte[(int) file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
            input.read(buffer, 0, buffer.length);
            input.close();

            System.out.println("File " + fileName + " has been served successfully.");
            return buffer;
        } catch (Exception e) {
            System.out.println("FileImpl: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
