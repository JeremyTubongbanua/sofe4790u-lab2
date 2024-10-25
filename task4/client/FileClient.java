import java.io.*;
import java.rmi.Naming;

public class FileClient {

   public static void main(String[] argv) {
      if (argv.length != 4) {
         System.out.println("Usage: java FileClient <hostname> <port> <operation> <fileName>");
         System.exit(0);
      }

      String hostName = argv[0];
      int port = Integer.parseInt(argv[1]);
      String operation = argv[2].toLowerCase();  // "upload" or "download"
      String fileName = argv[3];

      try {
         if (operation.equals("download")) {
            downloadFile(hostName, port, fileName);
         } else if (operation.equals("upload")) {
            uploadFile(hostName, port, fileName);
         } else {
            System.out.println("Invalid operation. Use 'upload' or 'download'.");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void downloadFile(String hostName, int port, String fileName) {
      System.out.println("FileClient: Requesting file " + fileName + " from " + hostName + ":" + port);
      BufferedOutputStream output = null;
      try {
         String registryUrl = "//" + hostName + ":" + port + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(registryUrl);
         byte[] filedata = fi.downloadFile(fileName);

         System.out.println("Successfully downloaded file " + fileName + " from server.");

         File file = new File(fileName);
         output = new BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(filedata, 0, filedata.length);
         output.flush();

         System.out.println("File " + fileName + " successfully written.");
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (output != null) {
               output.close();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   private static void uploadFile(String hostName, int port, String fileName) {
      System.out.println("FileClient: Uploading file " + fileName + " to " + hostName + ":" + port);
      BufferedInputStream input = null;
      try {
         String registryUrl = "//" + hostName + ":" + port + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(registryUrl);

         File file = new File(fileName);
         byte[] buffer = new byte[(int) file.length()];
         input = new BufferedInputStream(new FileInputStream(file));
         input.read(buffer, 0, buffer.length);

         fi.uploadFile(buffer, fileName);
         System.out.println("Successfully uploaded file " + fileName + " to server.");
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (input != null) {
               input.close();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
