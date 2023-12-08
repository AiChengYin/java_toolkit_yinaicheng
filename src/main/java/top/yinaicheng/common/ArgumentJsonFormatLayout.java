package top.yinaicheng.common;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.helpers.MessageFormatter;
import java.util.stream.Stream;
/**
 * 参数Json格式化类
 * 主要作用是为日志消息中的参数提供Json格式化，并返回格式化后的消息
 * @author yinaicheng
 */
public class ArgumentJsonFormatLayout extends MessageConverter
{

  private static final Gson gson=new GsonBuilder().create();

  @Override
  public String convert(ILoggingEvent event)
  {
    try
    {
      return MessageFormatter.arrayFormat(event.getMessage(),Stream.of(event.getArgumentArray()).map(gson::toJson).toArray()).getMessage();
    }
    catch(Exception exception)
    {
      return event.getMessage();
    }
  }
}
