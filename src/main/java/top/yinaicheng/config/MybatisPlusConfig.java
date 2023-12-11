package top.yinaicheng.config;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * MyBatis-Plus配置类，用于配置MyBatis-Plus框架的相关信息和插件
 * @author yinaicheng
 */
@Configuration
public class MybatisPlusConfig
{
  /*分页插件*/
  @Bean
  public PaginationInterceptor paginationInterceptor(){
    /*PaginationInterceptor是MyBatis-Plus提供的一个分页插件类，用于实现数据库查询结果的分页功能*/
    return new PaginationInterceptor();
  }
}
