package offery.wizzo.in.offery.webApi;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ankushpatel8 on 28/10/2017.
 */

public class FileDownloader {
    private static final int BUFFER_SIZE = 4096;

   private String saveDir;

    public FileDownloader() {
        File direct = new File(Environment.getExternalStorageDirectory() + "/Spass");
        File direct1 = new File(Environment.getExternalStorageDirectory() + "/Spass/video");
        File direct2 = new File(Environment.getExternalStorageDirectory() + "/Spass/image");

        if (!direct.exists())
            direct.mkdirs();
        if (!direct1.exists()) {
            //  File wallpaperDirectory = new File("/stream_list_screen/Blast/");
            direct1.mkdirs();
        }
        if (!direct2.exists()) {
            //  File wallpaperDirectory = new File("/stream_list_screen/Blast/");
            direct2.mkdirs();
        }
        saveDir = direct.getPath();

    }

    public  String downloadFile(String fileURL)
            throws IOException
    {
        String toReturn = null;
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }



            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath;
            if (fileURL.endsWith(".mp4"))
            saveFilePath = saveDir+"/video" + File.separator + fileName;
            else
                saveFilePath = saveDir+"/image" + File.separator + fileName;

            File videoFile = new File(saveFilePath);


            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            toReturn= saveFilePath;
        } else {
            toReturn= null;
        }
        httpConn.disconnect();

        return toReturn;
    }
}
