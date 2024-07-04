package th.com.muangthai.mtlgisxreportingservice.model.form.report001;

public class Rpt001Req {
    private String docCode;
    private String policyNo;
    private int exportType; // 1=local File,2=base64
    private String exportFileName;
    private String reportFileType; //pdf,excel,csv

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public int getExportType() {
        return exportType;
    }

    public void setExportType(int exportType) {
        this.exportType = exportType;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public String getReportFileType() {
        return reportFileType;
    }

    public void setReportFileType(String reportFileType) {
        this.reportFileType = reportFileType;
    }
}
