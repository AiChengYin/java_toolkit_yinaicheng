package top.yinaicheng.config;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static top.yinaicheng.constant.DateTimeConstant.*;

/**
 * JackSon配置类
 * 添加对Java 8时间类（如LocalDateTime、LocalDate、LocalTime）的序列化和反序列化支持
 * @author yinaicheng
 */
@Configuration
public class JacksonConfig
{
  /**
   * 添加LocalDateTime等java8时间类序列化和反序列化的支持，只需要在实体类中对应的时间类型上使用@DateTimeFormat和@JsonFormat即可
   */
  @Bean
  public ObjectMapper objectMapper()
  {
    /*创建并配置一个ObjectMapper对象，ObjectMapper是Jackson库中最核心的类，它负责将Java对象转换为JSON或者将JSON转换为Java对象*/
    ObjectMapper objectMapper=new ObjectMapper();
    /*该配置意味着在序列化Java对象时，时间属性将不会以时间戳的方式输出，而是按照指定的时间格式输出*/
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    /*该配置意味着在反序列化JSON时，时间属性将不会根据上下文的时区进行调整*/
    objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    /*创建一个JavaTimeModule对象，该对象是Jackson库中用于支持Java 8时间类序列化和反序列化的模块*/
    JavaTimeModule javaTimeModule=new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(MINUTE_DATE_TIME_FORMAT_PARSE));
    javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DEFAULT_DATE_FORMAT_PARSE));
    javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DEFAULT_TIME_FORMAT_PARSE));
    javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(MINUTE_DATE_TIME_FORMAT_PARSE));
    javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DEFAULT_DATE_FORMAT_PARSE));
    javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DEFAULT_TIME_FORMAT_PARSE));
    /*将JavaTimeModule对象和ParameterNamesModule对象注册到ObjectMapper中，ParameterNamesModule是Jackson库中的模块，可以用于处理参数名的序列化和反序列化*/
    objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
    return objectMapper;
  }
}
