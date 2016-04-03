package polyu.comp.funing.service;

/**
 * Created by liushanchen on 16/4/3.
 */
public class ScDetailR {
    /**
     * "error": 0,
     "message": "Shoppingcart detail created successfully",
     "sdid": 4
     */
    private int error;
    private String message;
    private int sdid;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSdid() {
        return sdid;
    }

    public void setSdid(int sdid) {
        this.sdid = sdid;
    }

    @Override
    public String toString() {
        return "ScDetailR{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", sdid=" + sdid +
                '}';
    }
}
