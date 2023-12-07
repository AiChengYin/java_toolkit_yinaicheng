package top.yinaicheng.common;
import java.util.Map;
/**
 * 统一的返回值
 * @author yinaicheng
 */
public class ResponseValue<T>
{
  /**
   * 返回结果：错误or正确
   */
  private Boolean result;
  /**
   * 返回码
   */
  private Map<String,Object> returnCodeEnum;
  /**
   * 返回信息
   */
  private String message;
  /**
   * 返回数据（这里使用泛型，不需要做强制类型转换，如果使用Object类的话，可能在运行时得到类型转换异常）
   */
  private T data;

  public Boolean getResult() {
    return result;
  }

  public void setResult(Boolean result) {
    this.result = result;
  }

  public Map<String, Object> getReturnCodeEnum() {
    return returnCodeEnum;
  }

  public void setReturnCodeEnum(Map<String, Object> returnCodeEnum) {
    this.returnCodeEnum = returnCodeEnum;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public ResponseValue(Boolean result) {
    this.result=result;
  }
  /**执行成功，且返回信息和数据和返回码*/
  public static <T> ResponseValue<T> success(String message, T data, Map<String,Object> returnCodeEnum)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(true);
    responseValue.message=message;
    responseValue.data=data;
    responseValue.returnCodeEnum=returnCodeEnum;
    return responseValue;
  }

  /**执行成功，且返回信息和数据*/
  public static <T> ResponseValue<T> success(String message, T data)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(true);
    responseValue.message=message;
    responseValue.data=data;
    return responseValue;
  }

  /**执行成功，且返回数据*/
  public static <T> ResponseValue<T> success(T data)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(true);
    responseValue.data=data;
    return responseValue;
  }

  /**执行成功，且返回信息*/
  public static <T> ResponseValue<T> success(String message)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(true);
    responseValue.message=message;
    return responseValue;
  }

  /**执行成功，不返回信息和数据和其他数据*/
  public static <T> ResponseValue<T> success()
  {
    return new ResponseValue<>(true);
  }

  /**执行失败，且返回信息和数据和返回码*/
  public static <T> ResponseValue<T> failure(String message, T data, Map<String,Object> returnCodeEnum)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(false);
    responseValue.message=message;
    responseValue.data=data;
    responseValue.returnCodeEnum=returnCodeEnum;
    return responseValue;
  }

  /**执行失败，且返回信息和数据*/
  public static <T> ResponseValue<T> failure(String message, T data)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(false);
    responseValue.message=message;
    responseValue.data=data;
    return responseValue;
  }

  /**执行失败，返回错误信息*/
  public static <T> ResponseValue<T> failure(String message)
  {
    ResponseValue<T> responseValue=new ResponseValue<>(false);
    responseValue.message=message;
    return responseValue;
  }

  /**执行失败，不返回信息和数据*/
  public static <T> ResponseValue<T> failure()
  {
    return new ResponseValue<>(false);
  }
}
