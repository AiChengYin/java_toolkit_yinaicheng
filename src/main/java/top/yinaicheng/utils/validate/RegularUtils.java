package top.yinaicheng.utils.validate;
/**
 * 正则表达式工具类
 */
public class RegularUtils {

    /**
     * 判断一个字符串是否符合{yyyy}-{mm}-{dd}的格式
     */
    public static boolean isValidDateFormat(String dateStr){
        return dateStr.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    /**
     * 这是一个示例方法
     */
    public static void main(String[] args) {
        System.out.println(isValidDateFormat("2023-03-06"));
    }

}
