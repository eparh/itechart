package util;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class MyFileUtil {
    public static void deleteOnPath(String path) {
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }

    public static String  extractFileName (Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    public static String  extractFileExtension(Part part){
        String fileName = extractFileName(part);
        return fileName.substring(fileName.indexOf("."));
    }

    public static  File renameFile(long idContact, File file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getName());
        String path =FilenameUtils.getPath(file.getPath());
        String newName = path + idContact + "." + ext;
        File newFile = new File(newName);
        return newFile;
    }

}
