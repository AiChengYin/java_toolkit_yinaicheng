package top.yinaicheng.utils.string;
import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 * 字符串工具类
 * @author yinaicheng
 */
public interface StringUtils {

    /**
     * 空字符串标记
     */
    String EMPTY_STRING = "";

    /**
     * 逗号标记
     */
    String COMMA_SIGN = ",";

    /**
     * 换两行
     */
    String WRAP_TWO_LINES = "\n\n";

    /**
     * 将字符串形式的数字转化为Long类型
     */
    Function<String, Long> STRING_CONVERT_TO_LONG = str -> {
        if (str == null) {
            return null;
        } else {
            return Long.valueOf(str);
        }
    };

    /**
     * 提供空值
     */
    Supplier<String> EMPTY = () -> EMPTY_STRING;

    /**
     * 判断字符串是否为空
     */
    Predicate<String> IS_EMPTY = str -> str == null || str.trim().isEmpty();

    /**
     * 判断字符串是否不为空
     */
    Predicate<String> IS_NOT_EMPTY = str -> str != null && !str.trim().isEmpty();

    /**
     * 低驼峰 test_data -> testData
     */
    Function<String, String> LOWER_UNDER_SCORE_LOWER_CAMEL_CASE_FORMAT = a -> CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, a);

    /**
     * 高驼峰 test_data -> TestData
     */
    Function<String, String> LOWER_UNDER_SCORE_UPPER_CAMEL_CASE_FORMAT = a -> CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, a);

    /**
     * 低驼峰转下划线
     */
    Function<String, String> LOWER_CAMEL_LOWER_UNDER_SCORE_CASE_FORMAT = a -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, a);

    /**
     * 生成JavaDoc注释
     */
    Function<String, String> GENERATE_JAVA_DOC_FORMAT_COMMENT = str -> "/**\n * ".concat(str).concat("\n */\n");

    /**
     * 生成Java多行注释
     */
    Function<String, String> GENERATE_JAVA_MULTILINES_FORMAT_COMMENT = str -> "/* ".concat(str).concat(" */\n");

    /**
     * 从Java 类的完全限定名称中提取字段类型
     */
    Function<String, String> FROM_COLUMN_CLASS_NAME_EXTRACT_JAVA_TYPE = a -> a.substring(a.lastIndexOf(".") + 1);

    /**
     * 对字符串形式的列表去重
     * 如38,38,310=>38,310
     */
    Function<String, String> STRING_DISTINCT_FUNCTION = s -> Splitter.on(",").splitToList(s).stream().distinct().collect(Collectors.joining(","));

    /**
     * 高驼峰命名
     */
    Function<String, String> HIGH_CASE_FORMAT_FUNCTION = a -> CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, a);

    /**
     * 首字母变大写
     */
    Function<String, String> FIRST_CHAR_TO_UPPER_CASE = str -> {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    };

    /**
     * 对象转字符串
     */
    BiFunction<Object, String, String> CONVERT_OBJECT_TO_STRING = (needConvertedValue, javaType) -> {
        if (!Optional.ofNullable(needConvertedValue).isPresent()) {
            return EMPTY_STRING;
        }
        switch (javaType) {
            case "Boolean":
            case "Long":
                return String.valueOf(needConvertedValue);
            case "Double":
                return String.format("%.2f", needConvertedValue);
            case "LocalDateTime":
                return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format((LocalDateTime) needConvertedValue);
            case "String":
                return String.valueOf(needConvertedValue).trim();
            default:
                return EMPTY_STRING;
        }
    };

    /**
     * 字符串转对象
     */
    BiFunction<String, String, Object> CONVERT_STRING_TO_OBJECT = (needConvertedValue, javaType) -> {
        if (IS_EMPTY.test(needConvertedValue)) {
            return null;
        }
        switch (javaType) {
            case "Boolean":
                return Boolean.parseBoolean(needConvertedValue);
            case "Long":
                return Long.parseLong(needConvertedValue);
            case "Double":
                return Double.parseDouble(needConvertedValue);
            case "LocalDateTime":
                return LocalDateTime.parse(needConvertedValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            case "String":
                return needConvertedValue.trim();
            default:
                return null;
        }
    };

    /**
     * 字符串是否包含中文
     * @param str 待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     */
    Predicate<String> JUDGE_CONTAIN_CHINESE = str -> {
        /*如果字符串为空，说明不包含中文*/
        if (StringUtils.IS_EMPTY.test(str)) {
            return Boolean.FALSE;
        }
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        return m.find();
    };


}
