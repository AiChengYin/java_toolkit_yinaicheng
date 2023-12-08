package top.yinaicheng.utils.object;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
/**
 * 通用对象构建器
 * 可以根据需求创建一个对象，并为其设置属性值。通过链式调用的方式，可以方便地添加多个属性值。最终使用 build 方法构建最终的对象并返回
 * @author yinaicheng
 */
public class GenericObjectBuilder<T> {

  /**
   * 用于保存实例化容器
   */
  private final Supplier<T> instantiator;

  /**
   * 属性列表，用于存储添加的属性值
   */
  private final List<Consumer<T>> attributeList=new ArrayList<>();

  /**
   * 构造方法，它接收一个instantiator参数，并将其赋值给实例化容器。
   */
  public GenericObjectBuilder(Supplier<T> instantiator) {
    this.instantiator=instantiator;
  }

  /**
   * 实例初始化操作
   * @param instantiator Supplier<T>:提供T对象，不返回值
   * @return 初始化后的实例化容器
   */
  public static <T> GenericObjectBuilder<T> initial(Supplier<T> instantiator){
    return new GenericObjectBuilder<T>(instantiator);
  }

  /**
   * 实例添加属性
   * @param consumer 对类型为T、U参数应用操作，接收T和U对象，不返回值
   * @return 返回当前类的实例
   */
  public <U> GenericObjectBuilder<T> addAttribute(BiConsumer<T,U> consumer, U value){
    /*Consumer<T>：接收T对象，不返回值*/
    Consumer<T> c=instance->consumer.accept(instance,value);
    /*首先将 BiConsumer 转化为 Consumer<T>，然后将其添加到属性列表中*/
    attributeList.add(c);
    return this;
  }

  /**
   * 用于构建最终的对象
   * @return 最终的对象
   */
  public T build()
  {
    /*获取提供的实例对象*/
    T value=instantiator.get();
    /*遍历属性列表 attributeList，对每个属性值应用到实例对象上*/
    attributeList.forEach(modifier->modifier.accept(value));
    /*清空属性列表，并返回实例对*/
    attributeList.clear();
    /*返回实例对象*/
    return value;
  }

  /**
   * 这是一个示例方法，用于展示如何使用GenericBuilder
   */
  public static void main(String[] args) {
    System.out.println("""
            GenericObjectBuilder.initial(MybatisConfiguration::new).addAttribute(MybatisConfiguration::setCallSettersOnNulls,true).build();
            """);
  }

}