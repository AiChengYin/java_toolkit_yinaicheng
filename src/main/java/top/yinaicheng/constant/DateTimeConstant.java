package top.yinaicheng.constant;
import java.time.format.DateTimeFormatter;
/**
 * 日期时间常量
 * @author yinaicheng
 */
public interface DateTimeConstant
{

  /**
   * 上海时区
   */
  String ZONE_ID="Asia/Shanghai";

  /**
   * 展示日期时间格式，分钟级别
   */
  String MINUTE_DATE_TIME_FORMAT ="yyyy-MM-dd HH:mm";

  /**
   * 展示日期时间格式，秒级别
   */
  String SECOND_DATE_TIME_FORMAT ="yyyy-MM-dd HH:mm:ss";

  /**
   * 展示日期时间格式，小时级别
   */
  String DEFAULT_HOUR_FORMAT="yyyy-MM-dd HH";

  /**
   * 默认日期格式
   */
  String DEFAULT_DATE_FORMAT="yyyy-MM-dd";

  /**
   * 默认时间格式
   */
  String DEFAULT_TIME_FORMAT="HH:mm:ss";

  /**
   * yyyy-MM-dd HH:mm
   */
  DateTimeFormatter MINUTE_DATE_TIME_FORMAT_PARSE =DateTimeFormatter.ofPattern(DateTimeConstant.MINUTE_DATE_TIME_FORMAT);

  /**
   * yyyy-MM-dd HH:mm:ss
   */
  DateTimeFormatter SECOND_DATE_TIME_FORMAT_PARSE =DateTimeFormatter.ofPattern(DateTimeConstant.SECOND_DATE_TIME_FORMAT);

  /**
   * yyyy-MM-dd HH
   */
  DateTimeFormatter DEFAULT_HOUR_FORMAT_PARSE=DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_HOUR_FORMAT);

  /**
   * yyyy-MM-dd
   */
  DateTimeFormatter DEFAULT_DATE_FORMAT_PARSE=DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE_FORMAT);

  /**
   * HH:mm:ss
   */
  DateTimeFormatter DEFAULT_TIME_FORMAT_PARSE=DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_TIME_FORMAT);

}
