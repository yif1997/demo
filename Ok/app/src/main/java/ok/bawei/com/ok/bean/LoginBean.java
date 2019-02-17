package ok.bawei.com.ok.bean;

/**
 * @author 王艺霏
 * @fileName LoginBean
 * @package ok.bawei.com.ok.bean
 **/
public class LoginBean {

    private String message;
    private String status;

    public LoginBean(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public LoginBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
