package th.com.muangthai.mtlgisxreportingservice.model.repo;

import org.springframework.data.repository.CrudRepository;
import th.com.muangthai.mtlgisxreportingservice.model.entity.MstReportEntity;

import java.util.Optional;

public interface MstReportEntityRepository extends CrudRepository<MstReportEntity, Integer> {
    Optional<MstReportEntity> findByReportCode(String reportCode);
}
