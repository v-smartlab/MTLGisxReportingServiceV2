package th.com.muangthai.mtlgisxreportingservice.service.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SimpleReportFiller {

    private String reportFileName;

    private JasperReport jasperReport;

    private JasperPrint jasperPrint;

    private DataSource dataSource;

    private Map<String, Object> parameters;

    public SimpleReportFiller(DataSource dataSource) {
        parameters = new HashMap<>();
        if(null != dataSource)
        this.dataSource = dataSource;
    }

    public void prepareReport() {
        compileReport();
        fillReport();
    }

    public void compileReport() {
        try {
            InputStream reportStream = Files.newInputStream(Path.of(reportFileName));
            jasperReport = JasperCompileManager.compileReport(reportStream);
            //JRSaver.saveObject(jasperReport, reportFileName.replace(".jrxml", ".jasper"));
        } catch (JRException | IOException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillReport() {
        try {
            if(this.dataSource != null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, this.dataSource.getConnection());
            }else{
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            }
        } catch (JRException | SQLException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillReportWithJrBean(JRBeanCollectionDataSource lstJrBean) {
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, lstJrBean);
        } catch (JRException ex) {
            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

}
