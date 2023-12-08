package top.yinaicheng.utils.collection;
/**
 * HashMap构建工具类，提供了一些便捷的方法来构建和操作HashMap
 * @author yinaicheng
 */
public class HashMapBuilder<K, V> extends java.util.HashMap<K, V> {

  /**
   * 向HashMap中添加一个键值对，并返回自身，以支持链式调用
   */
  public HashMapBuilder<K, V> builder(K key, V value) {
    put(key, value);
    return this;
  }

  /**
   * 用于创建HashMapBuilder的实例，并立即添加一个键值对
   */
  public static <K, V> HashMapBuilder<K, V> initial(K key, V value) {
    return new HashMapBuilder<K, V>().builder(key, value);
  }

  /**
   * 法用于根据键删除HashMap中的一个键值对，并返回自身，以支持链式调用
   */
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