import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.util.ResetUtils;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadBean {
    private RichInputText pathBind;
    private RichOutputText pathBindReview;
    private RichInputText pathBindReply;

    public UploadBean() {
    }

    public void uploadAction(ValueChangeEvent vce){
               if (vce.getNewValue() != null) 
               {
               UploadedFile fileVal = (UploadedFile) vce.getNewValue();
               String path = uploadFile(fileVal);
               OperationBinding ob = executeOperation("setFileData");
               ob.getParamsMap().put("name", fileVal.getFilename());
               ob.getParamsMap().put("path", path);
               ob.getParamsMap().put("contTyp", fileVal.getContentType());
               ob.execute();
               ResetUtils.reset(vce.getComponent());
               }
            }

             public BindingContainer getBindingsCont() {
            return BindingContext.getCurrent().getCurrentBindingsEntry();
        }

        public OperationBinding executeOperation(String operation) {
    OperationBinding createParam = getBindingsCont().getOperationBinding(operation);
            return createParam;
       }
        
    private String uploadFile(UploadedFile file)
               {
               UploadedFile myfile = file;
               String path = null;
               if (myfile == null) {
                  } 
         else 
        {
                path = "C://Oracle//Middleware//Oracle_Home//jdeveloper//jdev//mywork//Online//images//" + myfile.getFilename();
                InputStream inputStream = null;
                try {
                FileOutputStream out = new FileOutputStream(path);
                inputStream = myfile.getInputStream();
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
               }
                out.flush();
                out.close();
                 } catch (Exception ex) {
                     // handle exception
               ex.printStackTrace();
                } finally {
                try {
                inputStream.close();
                 } catch (Exception e) {}
                   }
                   }
                     //Returns the path where file is stored
                  return path;
               }

    public void downloadAction(FacesContext fc, OutputStream os)throws Exception {
               File filed = new File(pathBind.getValue().toString());
               FileInputStream fis;
               byte[] b;
               try {
               fis = new FileInputStream(filed);
               int n;
               while ((n = fis.available()) > 0) {
               b = new byte[n];
               int result = fis.read(b);
               os.write(b, 0, b.length);
               if (result == -1)
               break;
                }
                } catch (Exception e) {
               e.printStackTrace();
                 }
               os.flush();
                 }

    public void setPathBind(RichInputText pathBind) {
        this.pathBind = pathBind;
    }

    public RichInputText getPathBind() {
        return pathBind;
    }

    public void setPathBindReview(RichOutputText pathBindReview) {
        this.pathBindReview = pathBindReview;
    }

    public RichOutputText getPathBindReview() {
        return pathBindReview;
    }

    public void downloadActionReview(FacesContext fc, OutputStream os)throws Exception {
               File filed = new File(pathBindReview.getValue().toString());
               FileInputStream fis;
               byte[] b;
               try {
               fis = new FileInputStream(filed);
               int n;
               while ((n = fis.available()) > 0) {
               b = new byte[n];
               int result = fis.read(b);
               os.write(b, 0, b.length);
               if (result == -1)
               break;
                }
                } catch (Exception e) {
               e.printStackTrace();
                 }
               os.flush();
                 }

    public void downloadActionReply(FacesContext fc, OutputStream os)throws Exception {
               File filed = new File(pathBindReply.getValue().toString());
               FileInputStream fis;
               byte[] b;
               try {
               fis = new FileInputStream(filed);
               int n;
               while ((n = fis.available()) > 0) {
               b = new byte[n];
               int result = fis.read(b);
               os.write(b, 0, b.length);
               if (result == -1)
               break;
                }
                } catch (Exception e) {
               e.printStackTrace();
                 }
               os.flush();
                 }

    public void setPathBindReply(RichInputText pathBindReply) {
        this.pathBindReply = pathBindReply;
    }

    public RichInputText getPathBindReply() {
        return pathBindReply;
    }
}
