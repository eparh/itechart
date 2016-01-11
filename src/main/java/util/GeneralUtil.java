package util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GeneralUtil {
    public static Date stringToDate(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(stringDate != null && !stringDate.equals("")) {
            try {
                java.util.Date utilDate = formatter.parse(stringDate);
                date = new Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new RuntimeException();
            }
        }
        return date;
    }

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

    public static  void renameFile(long idContact, File file) {
        String ext = FilenameUtils.getExtension(file.getName());
        System.out.println("ext:"+ext);
        String path =FilenameUtils.getPath(file.getName());
        System.out.println("path:"+path);
        String new_name = path + idContact + ext;
        File new_file = new File(new_name);
        file.renameTo(new_file);
    }
}
