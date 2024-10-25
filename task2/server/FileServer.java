import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;

public class FileServer {
    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.out.println("Usage: java FileServer <hostname> <port>");
            System.exit(0);
        }
        try {
            String hostname = argv[0];
            int port = Integer.parseInt(argv[1]);
            String registryUrl = "//" + hostname + ":" + port + "/FileServer";

            FileInterface fileImpl = new FileImpl();
            Remote stub = UnicastRemoteObject.exportObject(fileImpl, 0);

            Naming.rebind(registryUrl, stub);
            System.out.println("File Server: " + registryUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
