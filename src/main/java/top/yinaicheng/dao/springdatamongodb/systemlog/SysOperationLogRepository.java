package top.yinaicheng.dao.springdatamongodb.systemlog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import top.yinaicheng.entity.systemlog.SysOperationLog;
import java.util.List;
/**
 * 系统日志数据库访问层
 */
public interface SysOperationLogRepository extends MongoRepository<SysOperationLog,String>
{
  
  @Query("{'createTime':{$gte:?0, $lte:?1}}")
  public List<SysOperationLog> getSysOperationLogByStartTimeAndEndTime(String startTime,String endTime);
  
}
