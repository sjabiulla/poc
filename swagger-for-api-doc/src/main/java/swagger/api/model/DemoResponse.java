package swagger.api.model;

/**
 * Created by J on 16-10-2017.
 */
public class DemoResponse {

    protected int response_code;
    protected String error_message;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "DemoResponse{" +
                "response_code=" + this.getResponse_code() +
                ", error_message='" + this.getError_message() + '\'' +
                '}';
    }
}
