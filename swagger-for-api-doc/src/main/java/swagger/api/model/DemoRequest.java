package swagger.api.model;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by J on 16-10-2017.
 */
//@XmlRootElement
public class DemoRequest {
    protected String message;
    protected String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DemoRequest{" +
                "message='" + this.getMessage() + '\'' +
                ", type='" + this.getType() + '\'' +
                '}';
    }
}
