package top.yinaicheng.utils.validate;
import com.google.common.base.Preconditions;
import org.springframework.util.CollectionUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;
/**
 * 对接收到的参数进行校验的工具类
 * 该工具类提供了一种简便的方式来对对象和集合中的参数进行验证，并且支持多个参数的验证
 * @author yinaicheng
 */
public class ParamValidator {
  private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private static final Validator validator = validatorFactory.getValidator();

  private ParamValidator() {
  }


  public static <E> Map<String, String> validate(E e, Class<?>... groups) {
    /*首先通过传入的validator对象对e对象进行验证，将验证结果保存在constraintViolations变量中*/
    Set<ConstraintViolation<E>> constraintViolations = validator.validate(e, groups);
    /*如果constraintViolations为空，即没有违反约束，方法将返回一个空的Map，即Collections.emptyMap()*/
    if (constraintViolations.isEmpty()) {
      return Collections.emptyMap();
    }
    else {
      Map<String, String> errors = new LinkedHashMap<>();
      for (ConstraintViolation<E> violation : constraintViolations) {
        /*将每个违反约束的属性路径和对应的错误消息添加到errors中*/
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
      }
      return errors;
    }
  }

  /**
   * 验证集合，方法是对集合进行迭代，然后每个集合进行参数验证
   */
  public static Map<String, String> validateList(Collection<?> collection) {
    Preconditions.checkNotNull(collection);
    Map<String, String> errors;
    for (Object object : collection) {
      errors = validate(object);
      if (!errors.isEmpty()) {
        return errors;
      }
    }
    return Collections.emptyMap();
  }

  /**
   * 验证对象
   */
  public static Map<String, String> validateObject(Object first, Object... objects) {
    /*如果objects不为null且不为空，说明除了first还有object，需要验证多个参数*/
    if (objects != null && objects.length > 0) {
      List<Object> paramList = new ArrayList<>(objects.length + 1);
      paramList.add(first);
      Collections.addAll(paramList, objects);
      return validateList(paramList);
    }
    /*objects为null或者为空，那只有first了，验证一个参数就可以了*/
    else {
      return validate(first);
    }
  }

  public static void check(Object param) {
    Map<String, String> errors = validateObject(param);
    if (!CollectionUtils.isEmpty(errors)) {
      throw new RuntimeException(errors.toString());
    }
  }

  public static void main(String[] args) {
    /*
      ParamValidator.check(userParam);
     */
  }

}