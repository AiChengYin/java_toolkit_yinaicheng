package top.yinaicheng.common;
import top.yinaicheng.utils.collection.HashMapBuilder;
import java.util.Map;
/**
 * 返回码枚举
 * @author yinaicheng
 */
public enum ReturnCodeEnum
{

  GENERAL_OK(200,"请求成功"),
  GET_OK(200,"已在响应中发出"),
  POST_OK(200,"现有资源已被更改"),
  PUT_OK(200,"已存在资源被更改"),
  DELETE_OK(200,"资源已被删除"),

  GENERAL_FAIL_AUTH(401,"未通过身份验证"),
  GET_FAIL_AUTH(401,"未通过身份验证"),
  POST_FAIL_AUTH(401,"未通过身份验证"),
  PUT_FAIL_AUTH(401,"未通过身份验证"),
  DELETE_FAIL_AUTH(401,"未通过身份验证"),

  GENERAL_REQUEST_METHOD_NOT_ALLOWRD(405,"不允许使用该请求方式"),
  GET_REQUEST_METHOD_NOT_ALLOWRD(405,"不允许使用该请求方式"),
  POST_REQUEST_METHOD_NOT_ALLOWRD(405,"不允许使用该请求方式"),
  PUT_REQUEST_METHOD_NOT_ALLOWRD(405,"不允许使用该请求方式"),
  DELETE_REQUEST_METHOD_NOT_ALLOWRD(405,"不允许使用该请求方式"),

  GENERAL_REQUEST_TIME_OUT(408,"请求超时"),
  GET_REQUEST_TIME_OUT(408,"请求超时"),
  POST_REQUEST_TIME_OUT(408,"请求超时"),
  PUT_REQUEST_TIME_OUT(408,"请求超时"),
  DELETE_REQUEST_TIME_OUT(408,"请求超时"),

  GENERAL_REQUEST_BODY_LARGE(413,"请求体过大"),
  GET_REQUEST_BODY_LARGE(413,"请求体过大"),
  POST_REQUEST_BODY_LARGE(413,"请求体过大"),
  PUT_REQUEST_BODY_LARGE(413,"请求体过大"),
  DELETE_REQUEST_BODY_LARGE(413,"请求体过大"),

  GENERAL_REQUEST_ADDRESS_LONG(414,"请求地址过长"),
  GET_REQUEST_ADDRESS_LONG(414,"请求地址过长"),
  POST_REQUEST_ADDRESS_LONG(414,"请求地址过长"),
  PUT_REQUEST_ADDRESS_LONG(414,"请求地址过长"),
  DELETE_REQUEST_ADDRESS_LONG(414,"请求地址过长"),

  GENERAL_Media_TYPE_NOT_SUPPORTED(415,"媒体类型不支持"),
  GET_Media_TYPE_NOT_SUPPORTED(415,"媒体类型不支持"),
  POST_Media_TYPE_NOT_SUPPORTED(415,"媒体类型不支持"),
  PUT_Media_TYPE_NOT_SUPPORTED(415,"媒体类型不支持"),
  DELETE_Media_TYPE_NOT_SUPPORTED(415,"媒体类型不支持"),

  GENERAL_REQUEST_NUMBER_EXCEED_LIIT(429,"请求次数超过限额"),
  GET_REQUEST_NUMBER_EXCEED_LIIT(429,"请求次数超过限额"),
  POST_REQUEST_NUMBER_EXCEED_LIIT(429,"请求次数超过限额"),
  PUT_REQUEST_NUMBER_EXCEED_LIIT(429,"请求次数超过限额"),
  DELETE_REQUEST_NUMBER_EXCEED_LIIT(429,"请求次数超过限额"),

  GENERAL_REQUEST_HEADER_LARGE(431,"请求头过大"),
  GET_REQUEST_HEADER_LARGE(431,"请求头过大"),
  POST_REQUEST_HEADER_LARGE(431,"请求头过大"),
  PUT_REQUEST_HEADER_LARGE(431,"请求头过大"),
  DELETE_REQUEST_HEADER_LARGE(431,"请求头过大"),

  GENERAL_SERVER_In_MAINTENANCE(503,"服务器正在维护"),
  GET_SERVER_In_MAINTENANCE(503,"服务器正在维护"),
  POST_SERVER_In_MAINTENANCE(503,"服务器正在维护"),
  PUT_SERVER_In_MAINTENANCE(503,"服务器正在维护"),
  DELETE_SERVER_In_MAINTENANCE(503,"服务器正在维护"),

  GENERAL_GATEWAY_TIMEOUT(504,"网关超时"),
  GET_GATEWAY_TIMEOUT(504,"网关超时"),
  POST_GATEWAY_TIMEOUT(504,"网关超时"),
  PUT_GATEWAY_TIMEOUT(504,"网关超时"),
  DELETE_GATEWAY_TIMEOUT(504,"网关超时"),

  GENERAL_FORBIDDEN(403,"不具有访问资源的权限"),
  GET_FORBIDDEN(403,"不具有访问资源的权限"),
  POST_FORBIDDEN(403,"不具有访问资源的权限"),
  PUT_FORBIDDEN(403,"不具有访问资源的权限"),
  DELETE_FORBIDDEN(403,"不具有访问资源的权限"),

  GENERAL_NOT_FOUND(404,"请求的资源不存在"),
  GET_NOT_FOUND(404,"请求的资源不存在"),
  POST_NOT_FOUND(404,"请求的资源不存在"),
  PUT_NOT_FOUND(404,"请求的资源不存在"),
  DELETE_NOT_FOUND(404,"请求的资源不存在"),

  GENERAL_INTERNAL_SERVER_ERROR(500,"服务器遇到未知问题"),
  GET_INTERNAL_SERVER_ERROR(500,"服务器遇到未知问题"),
  POST_INTERNAL_SERVER_ERROR(500,"服务器遇到未知问题"),
  PUT_INTERNAL_SERVER_ERROR(500,"服务器遇到未知问题"),
  DELETE_INTERNAL_SERVER_ERROR(500,"服务器遇到未知问题");

  /**返回码*/
  private Integer returnCode;

  /*返回码解释*/
  private String returnCodeExplanation;

  public Integer getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnCodeExplanation() {
    return returnCodeExplanation;
  }

  public void setReturnCodeExplanation(String returnCodeExplanation) {
    this.returnCodeExplanation = returnCodeExplanation;
  }

  ReturnCodeEnum(Integer returnCode, String returnCodeExplanation) {
    this.returnCode = returnCode;
    this.returnCodeExplanation = returnCodeExplanation;
  }

  public static Map<String,Object> convertEnumToMap(ReturnCodeEnum returnCodeEnum){
    return HashMapBuilder.<String,Object>
            initial("code",returnCodeEnum.getReturnCode()).
            builder("returnCodeExplanation",returnCodeEnum.getReturnCodeExplanation());
  }

}
