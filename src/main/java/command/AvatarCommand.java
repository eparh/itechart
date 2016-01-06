package command;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import service.ContactService;
import service.ServiceFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class AvatarCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String temp = request.getParameter("idContact");
        Long idContact =(long) -1;
        System.out.println("ID"+temp);
        if(! "".equals(temp)) idContact = Long.parseLong(temp);
        else {
            String[] chosen = request.getParameterValues("marked");
            for (String item : chosen) {
                idContact = Long.parseLong(item);
                break;
            }
        }

        System.out.println("ID"+idContact);
        String path = contactService.getPhoto(idContact);
        byte[] imageBytes =  extractBytes(path);

        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);

        response.getOutputStream().write(imageBytes);
        return null;
    }

    private byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
}
