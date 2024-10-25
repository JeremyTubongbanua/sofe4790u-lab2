import java.io.*;
import java.rmi.Naming;

public class FileClient {

   public static void main(String[] argv) {
      if (argv.length != 2) {
         System.out.println("Usage: java FileClient <fileName> <hostname>");
         System.exit(0);
      }
      BufferedOutputStream output = null;
      try {
         String registryUrl = "//" + argv[1] + ":" + 1099 + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(registryUrl);

         byte[] filedata = fi.downloadFile(argv[0]);
         File file = new File(argv[0]);
         output = new BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(filedata, 0, filedata.length);
         output.flush();

         System.out.println("File " + argv[0] + " downloaded successfully.");
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
}
