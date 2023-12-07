package top.yinaicheng.utils.collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 对Map集合的包装，，用于空值处理
 * @author yinaicheng
 */
public final class MapOptional<K,V>
{

  /**
   * 空Map集合常量
   */
  private static final MapOptional<?,?> EMPTY = new MapOptional<>();
  
  private final Map<K,V> value;

  /**
   * value为null
   */
  private MapOptional() {
    this.value=null;
  }

  /**
   * value为传入的值
   */
  private MapOptional(Map<K,V> value) {
    this.value = value;
  }

  /**
   * 如果传入的Map集合是空Map集合，返回包装空Map集合，里面的value为null
   */
  public static MapOptional<?,?> empty() {
    return EMPTY;
  }

  /**
   * 如果传入的Map集合是非空Map集合，返回包装非空Map集合，里面的value为传入的value
   */
  public static <K,V> MapOptional<K,V> of(Map<K,V> value) {
    return new MapOptional<>(value);
  }

  /**
   * 判断传入的Map集合是否是空Map集合，根据结果对其分别处理，返回包装后的Map集合
   */
  public static MapOptional<?,?> ofEmptyable(Map<?,?> mapValue) {
    return (mapValue==null || mapValue.isEmpty())?empty():of(mapValue);
  }

  /**
   * 判断value是否是null，如果是null，则用other替换它
   */
  public Map<K,V> orElse(Map<K,V> other) {
    return value!=null?value:other;
  }

  /**
   * 这是一个示例方法，用于展示如何使用ListOptional
   */
  public static void main(String[] args) {
    Map<String,Object> map=new HashMap<>();
    map.put("address","shanghai");
    System.out.println(MapOptional.ofEmptyable(map).orElse(new HashMap<>()));
  }

}
