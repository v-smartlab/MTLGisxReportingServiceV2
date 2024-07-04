package th.com.muangthai.mtlgisxreportingservice.model.form.error;

public class DataForm {
    private String dataBase64;
    private String reportType;
    private String reportPathFile;


    public String getDataBase64() {
        return dataBase64;
    }

    public void setDataBase64(String dataBase64) {
        this.dataBase64 = dataBase64;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportPathFile() {
        return reportPathFile;
    }

    public void setReportPathFile(String reportPathFile) {
        this.reportPathFile = reportPathFile;
    }
}
