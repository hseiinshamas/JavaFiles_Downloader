import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl {
    public static void main(String[] args) {
        // Make sure that this directory exists
        String dirName = "C:\\downloader";
        try {
            saveFileFromUrlWithJavaIO(
                    dirName + "\\java_tutorial.pdf", "https://www.tutorialspoint.com/java/java_pdf_version.htm");
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Using Java IO
    public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl) throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(fileUrl).openStream());
            fout = new FileOutputStream(fileName);
            byte[] data = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) in.close();
            if (fout != null) fout.close();
        }
    }

    // Using Commons IO library
    // Available at http://commons.apache.org/io/download_io.cgi
    public static void saveFileFromUrlWithCommonsIO(String fileName, String fileUrl) throws MalformedURLException, IOException {
        FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
    }
}
