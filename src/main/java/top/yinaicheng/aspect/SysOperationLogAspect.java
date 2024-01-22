package top.yinaicheng.aspect;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.yinaicheng.annotation.LogAnnotation;
import top.yinaicheng.common.ResponseValue;
import top.yinaicheng.common.ReturnCodeEnum;
import top.yinaicheng.config.IdWorkerConfig;
import top.yinaicheng.constant.DateTimeConstant;
import top.yinaicheng.dao.springdatamongodb.systemlog.SysOperationLogRepository;
import top.yinaicheng.entity.systemlog.SysOperationLog;
import top.yinaicheng.utils.collection.MapOptional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 * 系统自动日志Aspect
 * Created By AiChengYin
 */
@Order(Ordered.HIGHEST_PRECEDENCE) // 可以使用@Order注解修饰Aspect类，值越小，优先级越高
@Aspect
@Component
@Slf4j
public class SysOperationLogAspect
{
  /**
   * 系统自动日志的Dao接口
   */
  private final SysOperationLogRepository sysOperationLogRepository;
  /**
   * 分布式ID生成器
   */
  private final IdWorkerConfig idWorkerConfig;

  @Autowired
  public SysOperationLogAspect(SysOperationLogRepository sysOperationLogRepository, IdWorkerConfig idWorkerConfig) {
    this.idWorkerConfig=idWorkerConfig;
    this.sysOperationLogRepository=sysOperationLogRepository;
  }
  
  /**
   * 控制层切点，声明公共切入点
   */
  @Pointcut("@within(top.yinaicheng.annotation.LogAnnotation)") // @within表示匹配标注有指定注解的类
  public void pointCut(){}
  
  /**
   * 声明环绕通知，记录请求数据
   */
  @Around("pointCut()")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable // ProceedingJoinPoint可以放行，JoinPoint不可以放行
  {
    /*获取当前方法和参数*/
    MethodSignature methodSignature=(MethodSignature)proceedingJoinPoint.getSignature();
    /*如果不存在日志注解，直接返回执行结果，二次校验确定有该注解*/
    LogAnnotation logAnnotation=methodSignature.getMethod().getAnnotation(LogAnnotation.class);
    /*如果没有日志注解*/
    if(!Optional.ofNullable(logAnnotation).isPresent())
      return proceedingJoinPoint.proceed();
    /*结果返回给前端*/
    Object responseResult;
    /*结果落地mongo*/
    Object responseResultDepositMongo;
    /*获取开始时间，从1970-01-01T00:00:00Z至今的毫秒数*/
    Long beginTime=Instant.now().toEpochMilli();
    /*获取类名*/
    String className=getClassName(proceedingJoinPoint).getName();
    /*获取请求方法名*/
    String methodName=getMethodName(proceedingJoinPoint);
    /*获取注解描述*/
    String annotationDescription=logAnnotation.value();
    /*获取创建时间（指定时区）*/
    String createTime= DateTimeConstant.SECOND_DATE_TIME_FORMAT_PARSE.format(LocalDateTime.now().atZone(ZoneId.of(DateTimeConstant.ZONE_ID)));
    Object response=null;
    try {
      /*用于启动目标方法执行，并且返回结果*/
      response=proceedingJoinPoint.proceed();
      responseResultDepositMongo=response;
    }
    catch(Throwable throwable)
    {
      String method= className + "." + methodName;
      log.error("执行目标方法{}失败，原因是{}",method,throwable.getMessage());
      String exceptionCause=Throwables.getStackTraceAsString(throwable);
      responseResultDepositMongo=ResponseValue.failure(method,exceptionCause,ReturnCodeEnum.convertEnumToMap(ReturnCodeEnum.GENERAL_INTERNAL_SERVER_ERROR));
    }
    /*获取结束时间，从1970-01-01T00:00:00Z至今的毫秒数*/
    Long endTime=Instant.now().toEpochMilli();
    /*获取响应时间*/
    Long responseTime=endTime-beginTime;
    /*获取并且组装请求参数*/
    Map<?,?> params = MapOptional.ofEmptyable(getParams(proceedingJoinPoint)).orElse(new HashMap<>());
    SysOperationLog sysOperationLog= SysOperationLog.builder().
        id(idWorkerConfig.idWorker().nextId()). // 主键值，确保唯一
        className(className).
        methodName(methodName).
        annotationDescription(annotationDescription).
        responseTime(String.format("%s ms",responseTime)).
        createTime(createTime).
        requestParams(params.toString()).
        responseResult(Optional.ofNullable(responseResultDepositMongo).orElse(Strings.EMPTY).toString()).build();
    try
    {
      /*异步调用系统自动日志的Dao接口保存到数据库*/
      sysOperationLogRepository.save(sysOperationLog);
    }
    catch(Exception e) {
      log.error("保存到MongoDB数据库失败，原因是{}",e.getMessage());
    }
    return response;
  }
  
  /**
   * 声明异常通知
   */
  @AfterThrowing(value="controllerAspect()",throwing="throwable")
  public void doAfterThrowing(JoinPoint joinPoint,Throwable throwable) {
    log.error("异常方法：" + getMethodName((ProceedingJoinPoint) joinPoint) + " ; " + "异常信息：" + throwable.getMessage());
  }
  
  /**
   * 获取类
   */
  private static Class<?> getClassName(ProceedingJoinPoint proceedingJoinPoint) {
    return proceedingJoinPoint.getTarget().getClass();
  }
  
  /**
   * 获取方法名
   */
  private static String getMethodName(ProceedingJoinPoint proceedingJoinPoint) {
    return proceedingJoinPoint.getSignature().getName();
  }

  /**
   * 获取请求参数
   */
  private Map<String,Object> getParams(ProceedingJoinPoint proceedingJoinPoint) {
    Map<String, Object> requestParams = new HashMap<>();
    MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
    /*获取参数名*/
    String[] parameterNames = signature.getParameterNames();
    /*获取参数值*/
    Object[] parameterValues = proceedingJoinPoint.getArgs();
    for (int i = 0; i < parameterNames.length; i++) {
      Object value = parameterValues[i];
      /*如果对象是文件对象,获取文件名*/
      if (value instanceof MultipartFile) {
        MultipartFile file = (MultipartFile) value;
        value = file.getOriginalFilename();
      }
      requestParams.put(parameterNames[i], value);
    }
    return requestParams;
  }
  
}
