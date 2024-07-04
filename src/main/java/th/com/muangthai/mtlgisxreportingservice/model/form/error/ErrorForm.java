package th.com.muangthai.mtlgisxreportingservice.model.form.error;

public class ErrorForm {
    private String status;
    private String statusDesc;
    private DataForm data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public DataForm getData() {
        return data;
    }

    public void setData(DataForm data) {
        this.data = data;
    }
}
