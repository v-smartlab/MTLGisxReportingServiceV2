package th.com.muangthai.mtlgisxreportingservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mst_report")
public class MstReportEntity {
    @Id
    @Column(name = "mst_report_id")
    private Integer mstReportId;

    @Column(name = "report_code", length = Integer.MAX_VALUE)
    private String reportCode;

    @Column(name = "report_name", length = Integer.MAX_VALUE)
    private String reportName;

    @Column(name = "report_file_name", length = Integer.MAX_VALUE)
    private String reportFileName;

    @Column(name = "path_file", length = Integer.MAX_VALUE)
    private String pathFile;

    @Column(name = "report_type")
    private Integer reportType;

    @Column(name = "data_json", length = Integer.MAX_VALUE)
    private String dataJson;

    @Column(name = "report_data_type")
    private Integer reportDataType;

    @Column(name = "dummy_data", length = Integer.MAX_VALUE)
    private String dummyData;

    @Column(name = "req_json_path_file")
    private String reqJsonPathFile;

    public Integer getMstReportId() {
        return mstReportId;
    }

    public void setMstReportId(Integer mstReportId) {
        this.mstReportId = mstReportId;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public Integer getReportDataType() {
        return reportDataType;
    }

    public void setReportDataType(Integer reportDataType) {
        this.reportDataType = reportDataType;
    }

    public String getDummyData() {
        return dummyData;
    }

    public void setDummyData(String dummyData) {
        this.dummyData = dummyData;
    }

    public String getReqJsonPathFile() {
        return reqJsonPathFile;
    }

    public void setReqJsonPathFile(String reqJsonPathFile) {
        this.reqJsonPathFile = reqJsonPathFile;
    }
}