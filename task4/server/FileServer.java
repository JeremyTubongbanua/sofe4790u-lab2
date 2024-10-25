import java.rmi.Naming;

public class FileServer {
    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.out.println("Usage: java FileServer <hostname> <port>");
            System.exit(0);
        }
        try {
            String hostName = argv[0];
            int port = Integer.parseInt(argv[1]);
            FileInterface fi = new FileImpl();
            String registryUrl = "//" + hostName + ":" + port + "/FileServer";
            Naming.rebind(registryUrl, fi);
            System.out.println("File Server is ready at " + registryUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
