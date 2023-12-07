package top.yinaicheng.utils.collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * 对List集合的包装，用于空值处理
 */
public final class ListOptional<E>
{

  /**
   * 空List集合常量
   */
  private static final ListOptional<?> EMPTY = new ListOptional<>();

  private final List<E> value;

  /**
   * value为null
   */
  private ListOptional() {
    this.value=null;
  }

  /**
   * value为传入的值
   */
  private ListOptional(List<E> value) {
    this.value = value;
  }

  /**
   * 如果传入的List集合是空List集合，返回包装空List集合，里面的value为null
   */
  public static ListOptional<?> empty() {
    return EMPTY;
  }

  /**
   * 如果传入的List集合是非空List集合，返回包装非空List集合，里面的value为传入的value
   */
  public static <E> ListOptional<E> of(List<E> value) {
    return new ListOptional<>(value);
  }

  /**
   * 判断传入的List集合是否是空List集合，根据结果对其分别处理，返回包装后的List集合
   */
  public static ListOptional<?> ofEmptyable(List<?> listValue) {
    return (Optional.ofNullable(listValue).isEmpty() || listValue.isEmpty())?empty():of(listValue);
  }

  /**
   * 判断value是否是null，如果是null，则用other替换它
   */
  public List<E> orElse(List<E> other) {
    return Optional.ofNullable(value).isPresent()?value:other;
  }

  /**
   * 这是一个示例方法，用于展示如何使用ListOptional
   */
  public static void main(String[] args) {
    List<String> testList=new ArrayList<>();
    testList.add("A");
    System.out.println(ListOptional.ofEmptyable(testList).orElse(new ArrayList<>()));
  }

}
