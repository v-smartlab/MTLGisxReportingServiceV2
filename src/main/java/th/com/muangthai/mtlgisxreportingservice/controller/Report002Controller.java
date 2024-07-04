package th.com.muangthai.mtlgisxreportingservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import th.com.muangthai.mtlgisxreportingservice.controller.ext.Report001Ext;
import th.com.muangthai.mtlgisxreportingservice.controller.ext.Report002Ext;
import th.com.muangthai.mtlgisxreportingservice.model.entity.MstReportEntity;
import th.com.muangthai.mtlgisxreportingservice.model.form.error.DataForm;
import th.com.muangthai.mtlgisxreportingservice.model.form.error.ErrorForm;
import th.com.muangthai.mtlgisxreportingservice.model.form.report001.Rpt001Req;
import th.com.muangthai.mtlgisxreportingservice.model.form.report002.Rpt002Req;
import th.com.muangthai.mtlgisxreportingservice.model.repo.MstReportEntityRepository;
import th.com.muangthai.mtlgisxreportingservice.util.TextStatusUtil;
import th.com.muangthai.mtlgisxreportingservice.util.VsmUtil;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/api/collector")
public class Report002Controller extends Report002Ext {

    private static final Logger LOGGER = LogManager.getLogger(Report002Controller.class);

    private final MstReportEntityRepository mstReportEntityRepository;

    public Report002Controller(MstReportEntityRepository mstReportEntityRepository) {
        this.mstReportEntityRepository = mstReportEntityRepository;
    }

    @RequestMapping(path = "/call", method = RequestMethod.POST)
    public ErrorForm report001GenReport(@RequestBody Rpt002Req req
    ){
        String logId = new Date().getTime()+"";
        String fncName = "["+logId+"] %s .report002GenReport";
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
                ObjectMapper mapper = new ObjectMapper();
                InputStream is = Files.newInputStream(Path.of(mstReportEntity.getReqJsonPathFile()));
                TypeReference<List<HashMap<String,Object>>> typeRef  = new TypeReference<List<HashMap<String,Object>>>() {};
                List<HashMap<String,Object>> dataCollector = mapper.readValue(is, typeRef);
                callReportJasper(logId,mstReportEntity.getReportFileName(),mstReportEntity.getPathFile(),mapObjRequest,dataCollector);
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
