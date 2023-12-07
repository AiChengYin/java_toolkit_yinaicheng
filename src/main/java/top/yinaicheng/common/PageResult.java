package top.yinaicheng.common;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;
/**
 * 分页结果
 * Created By AiChengYin
 */
@Builder
@Getter
@Setter
public class PageResult<T>
{

  /*返回额外数据*/
  private Object extraData;

  /**返回具体数据*/
  private List<T> paginationData;

  /*返回分页数据*/
  private Map<String,Integer> paginationValue;

  /*返回当前页数*/
  private long pageNum;

  /*返回每页条数*/
  private long pageSize;

  /*返回数据总数*/
  private long total;

  public static void main(String[] args) {
    System.out.println("""
               Map<String,Integer> paginationValue= ImmutableMap.<String,Integer>builder().put("totalQuantity",0).put("pageNumber",1).put("pageQuantity",0).build();
                return PageResult.<User>builder().paginationData(Lists.newArrayList()).paginationValue(paginationValue).build();
            """);
  }

}
