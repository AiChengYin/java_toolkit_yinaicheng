package top.yinaicheng.entity.systemlog;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.io.Serializable;
/**
 * 系统自动日志Entity
 * @author yinaicheng
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document(collection="system_operation_log") // 表明这是一个文档对象，对应MongoDB里的system_operation_log表
public class SysOperationLog implements Serializable
{
  
  /*主键id，String类型的主键值在插入的时候MongoDB会帮我们自动生成*/
  @Id
  private Long id;
  
  /*类名*/
  private String className;
  
  /*请求方法名*/
  private String methodName;
  
  /*请求参数*/
  private String requestParams;
  
  /*日志注解描述*/
  private String annotationDescription;
  
  /*响应时间*/
  private String responseTime;
  
  /*创建时间*/
  private String createTime;
  
  /*返回结果*/
  private String responseResult;
  
}
