package top.yinaicheng.utils.collection;
/**
 * HashMap构建工具类
 */
public class HashMapBuilder<K, V> extends java.util.HashMap<K, V> {

  public HashMapBuilder<K, V> builder(K key, V value) {
    put(key, value);
    return this;
  }
  
  public static <K, V> HashMapBuilder<K, V> initial(K key, V value) {
    return new HashMapBuilder<K, V>().builder(key, value);
  }
  
  public HashMapBuilder<K, V> removeByKey(K key) {
    remove(key);
    return this;
  }

  /**
   * 这是一个示例方法，用于展示如何使用HashMapBuilder
   */
  public static void main(String[] args) {
    System.out.println(HashMapBuilder.initial("userName","yinaicheng").builder("sex","male"));
  }

}