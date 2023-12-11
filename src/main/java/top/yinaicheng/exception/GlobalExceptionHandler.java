package top.yinaicheng.exception;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.yinaicheng.common.ResponseValue;
import top.yinaicheng.common.ReturnCodeEnum;
import top.yinaicheng.utils.string.StringUtils;
import javax.servlet.http.HttpServletRequest;
/**
 * 全局异常处理
 * @author yinaicheng
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler
{
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) /*HttpStatus是一个spring自带的枚举类型，封装了常见的HTTP状态码及描述，这里默认500，内部服务器错误*/
  @ResponseBody
  @ExceptionHandler(value=Throwable.class)
  public ResponseValue<String> errorHandler(HttpServletRequest httpServletRequest, Throwable throwable)
  {
    String errorMessage= Throwables.getRootCause(throwable).toString();
    log.error("获取根异常：{}", Throwables.getRootCause(throwable).toString());
    log.error("获得异常的列表：{}",Throwables.getCausalChain(throwable));
    log.error("获得异常堆栈的字符串：{}",Throwables.getStackTraceAsString(throwable));
    return ResponseValue.failure(StringUtils.IS_EMPTY.test(errorMessage) ?"系统异常，请稍后重试":errorMessage, null , ReturnCodeEnum.convertEnumToMap(ReturnCodeEnum.POST_INTERNAL_SERVER_ERROR));

  }
}


