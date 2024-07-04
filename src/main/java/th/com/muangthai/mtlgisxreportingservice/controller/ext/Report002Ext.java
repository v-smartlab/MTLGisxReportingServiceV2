package th.com.muangthai.mtlgisxreportingservice.controller.ext;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.com.muangthai.mtlgisxreportingservice.model.form.error.DataForm;
import th.com.muangthai.mtlgisxreportingservice.model.form.report001.Rpt001Req;
import th.com.muangthai.mtlgisxreportingservice.model.form.report002.Rpt002Req;
import th.com.muangthai.mtlgisxreportingservice.service.jasper.SimpleReportExporter;
import th.com.muangthai.mtlgisxreportingservice.service.jasper.SimpleReportFiller;
import th.com.muangthai.mtlgisxreportingservice.util.VsmUtil;

import javax.sql.DataSource;
import java.util.*;

public class Report002Ext {

    private final DataSource dataSource;
    private SimpleReportFiller simpleReportFiller;

    public Report002Ext() {
        this.dataSource = null;
    }

    public void callReportJasper(String logId
            , String fileName
            , String pathFile
            , Map<String, String> mapObjRequest
            , List<HashMap<String,Object>> listDataJson
    ){
        this.simpleReportFiller = new SimpleReportFiller(this.dataSource);
        try {
//            final String pathJrxml = "D:\\data\\mtl\\gisx\\poc\\jasper-file\\";
            final String pathJrxml = pathFile;

            this.simpleReportFiller.setReportFileName(pathJrxml.concat(fileName));
//            this.simpleReportFiller.setReportFileName(pathFile.concat(fileName));
            this.simpleReportFiller.compileReport();

            Map<String, Object> parameters = new HashMap<>();
//            for (Map.Entry<String,String> entry : mapObjRequest.entrySet()){
//                parameters.put(entry.getKey(),entry.getValue());
//            }
            Collection dataSource = (Collection<HashMap<String,Object>>) listDataJson;
            JRBeanCollectionDataSource lstJrBean = new JRBeanCollectionDataSource(dataSource);
            this.simpleReportFiller.setParameters(parameters);
            this.simpleReportFiller.fillReportWithJrBean(lstJrBean);
        }catch (Exception ex){
            throw ex;
        }
    }

    public DataForm callExportReport(String logId
            , Rpt002Req rpt002Req
    ){
        SimpleReportExporter simpleExporter = new SimpleReportExporter(this.simpleReportFiller.getJasperPrint());

//        final String pathExportFile = "\\MTLGisxReportingService\\data\\mtl\\gisx\\poc\\export-file\\";
        final String pathExportFile = "/MTLGisxReportingService/data/export-file/";

        DataForm dataForm = null;
        String newReportFileName = null;
        try {

            dataForm= new DataForm();
            if(VsmUtil.isNotEmpty(rpt002Req.getExportType()) && rpt002Req.getExportType() == 1){
                newReportFileName = pathExportFile.concat(rpt002Req.getExportFileName().concat("_".concat(logId)));
                final String author = "MTL-GISX POC";
                if("pdf".equals(rpt002Req.getReportFileType())){
                    newReportFileName = newReportFileName.concat(".pdf");
                    simpleExporter.exportToPdf(newReportFileName, author);
                }else if("xlsx".equals(rpt002Req.getReportFileType())){
                    newReportFileName = newReportFileName.concat(".xlsx");
                    simpleExporter.exportToXlsx(newReportFileName, author);
                }else if("csv".equals(rpt002Req.getReportFileType())){
                    newReportFileName = newReportFileName.concat(".csv");
                    simpleExporter.exportToCsv(newReportFileName);
                }
            }else if(VsmUtil.isNotEmpty(rpt002Req.getExportType()) && rpt002Req.getExportType() == 2){
                newReportFileName = rpt002Req.getExportFileName();
                byte[] dataByte = null;
                if("pdf".equals(rpt002Req.getReportFileType())) {
                    dataByte = simpleExporter.exportToPdfBase64();
                }else if("xlsx".equals(rpt002Req.getReportFileType())){
                    dataByte = simpleExporter.exportToXlsxBase64();
                }else if("csv".equals(rpt002Req.getReportFileType())){
                    dataByte = simpleExporter.exportToCsvBase64();
                }
                if(null != dataByte && dataByte.length > 0){
                    dataForm.setDataBase64(Base64.getEncoder().encodeToString(dataByte));

//                    byte[] xlsxBytes = Base64.getDecoder().decode(xlsxBase64String);
//                    try (FileOutputStream xlsxOut = new FileOutputStream("output.xlsx")) {
//                        xlsxOut.write(xlsxBytes);
//                        System.out.println("XLSX file created successfully!");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    // แปลง Base64 string กลับเป็นไฟล์ CSV
//                    byte[] csvBytes = Base64.getDecoder().decode(dataForm.getDataBase64());
//                    try (FileOutputStream csvOut = new FileOutputStream(pathExportFile+"output_"+(new Date().getTime())+".csv")) {
//                        csvOut.write(csvBytes);
//                        System.out.println("CSV file created successfully!");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }

            dataForm.setReportPathFile(newReportFileName);
            dataForm.setReportType(rpt002Req.getExportFileName());

        }catch (Exception ex){
            throw ex;
        }
        return dataForm;
    }
}
