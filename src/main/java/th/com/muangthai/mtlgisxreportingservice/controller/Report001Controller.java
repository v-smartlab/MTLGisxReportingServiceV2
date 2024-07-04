package th.com.muangthai.mtlgisxreportingservice.controller;

import net.sf.jasperreports.engine.JasperPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import th.com.muangthai.mtlgisxreportingservice.controller.ext.Report001Ext;
import th.com.muangthai.mtlgisxreportingservice.model.entity.MstReportEntity;
import th.com.muangthai.mtlgisxreportingservice.model.form.error.DataForm;
import th.com.muangthai.mtlgisxreportingservice.model.form.error.ErrorForm;
import th.com.muangthai.mtlgisxreportingservice.model.form.report001.Rpt001Req;
import th.com.muangthai.mtlgisxreportingservice.model.repo.MstReportEntityRepository;
import th.com.muangthai.mtlgisxreportingservice.util.TextStatusUtil;
import th.com.muangthai.mtlgisxreportingservice.util.VsmUtil;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ds")
public class Report001Controller extends Report001Ext {

    private static final Logger LOGGER = LogManager.getLogger(Report001Controller.class);

    private final MstReportEntityRepository mstReportEntityRepository;

    public Report001Controller(DataSource dataSource, MstReportEntityRepository mstReportEntityRepository) {
        super(dataSource);
        this.mstReportEntityRepository = mstReportEntityRepository;
    }

    @RequestMapping(path = "/call", method = RequestMethod.POST)
    public ErrorForm report001GenReport(@RequestBody Rpt001Req req
    ){
        String logId = new Date().getTime()+"";
        String fncName = "["+logId+"] %s .report001GenReport";
        LOGGER.info(String.format(fncName,"start"));

        ErrorForm errorForm = new ErrorForm();
        DataForm dataForm = new DataForm();
        try{

            dataForm.setReportType(req.getReportFileType());

            MstReportEntity mstReportEntity = null;
            if(VsmUtil.isNotEmpty(req.getDocCode())){
                Optional<MstReportEntity> mstReportEntityOptional = this.mstReportEntityRepository.findByReportCode(req.getDocCode());
                if(mstReportEntityOptional.isPresent()){
                    mstReportEntity = mstReportEntityOptional.get();
                }else{
                    errorForm.setStatus(TextStatusUtil.ST_ERROR);
                    errorForm.setStatusDesc("Error : 'docCode : "+(req.getDocCode())+"' not found.");
                }
            }

            if(null != mstReportEntity){
                Map<String, String> mapObjRequest = new HashMap<>();
                if(VsmUtil.isNotEmpty(req.getPolicyNo())){
                    mapObjRequest.put("policyNo", req.getPolicyNo());
                }
                callReportJasper(logId,mstReportEntity.getReportFileName(),mstReportEntity.getPathFile(),mapObjRequest);
                dataForm = callExportReport(logId,req);
                errorForm.setStatus(TextStatusUtil.ST_SUCCESS);
            }

        }catch (Exception ex){
            errorForm.setStatus(TextStatusUtil.ST_ERROR);
            errorForm.setStatusDesc("Error : "+(ex.getMessage()));
            LOGGER.error(String.format(fncName,"end")+ex.toString(),ex);
        }finally {
            LOGGER.info(String.format(fncName,"end"));

            errorForm.setData(dataForm);
        }
        return errorForm;
    }

}
