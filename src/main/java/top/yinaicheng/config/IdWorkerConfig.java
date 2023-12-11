package top.yinaicheng.config;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Spring配置类，分布式系统中，全局唯一ID
 * @author yinaicheng
 */
@Configuration
public class IdWorkerConfig
{
  @Bean
  public Snowflake idWorker() {
    /*创建和配置一个Snowflake对象，用于生成分布式系统中的全局唯一ID*/
    return IdUtil.getSnowflake(1,1);
  }
}
