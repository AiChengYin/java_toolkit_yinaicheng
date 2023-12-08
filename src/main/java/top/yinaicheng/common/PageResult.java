package top.yinaicheng.common;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;
/**
 * 分页结果
 * @author yinaicheng
 */
@Builder
@Getter
@Setter
public class PageResult<T>
{

  /**
   * 返回当前页数，第几页
   */
  private long currentPageNumber;

  /**
   * 返回每页条数
   */
  private long pageSize;

  /**
   * 当前页的数据量
   */
  private long currentPageSize;

  /**
   * 返回数据总数
   */
  private long total;

  /**
   * 返回分页数据
   */
  private Map<String,Integer> paginationValue;

  /**
   * 返回具体数据
   */
  private List<T> paginationData;

  /**
   * 返回额外数据，用于扩展
   */
  private Object extraData;

  /**
   * 是否有下一页
   */
  private Boolean hasNext;

  /**
   * 配置对外API分页结果
   * @param paginationData 分页后的数据
   * @param total 总数
   * @param currentPageNumber 当前页
   * @param limit 前端需要一页多少条
   * @return 对外API分页结果
   */
  public PageResult<T> configPageResult(List<T> paginationData, Long total, Integer currentPageNumber, Integer limit){
    this.paginationData=paginationData;
    this.total =total;
    this.currentPageNumber =currentPageNumber;
    this.currentPageSize = paginationData.size();
    this.pageSize=limit;
    /*偏移量+当前页数量<总数，则说有有下一页，否则没有下一页*/
    this.hasNext = (this.currentPageNumber - 1) * limit + currentPageSize < total ? Boolean.TRUE : Boolean.FALSE;
    return this;
  }

  public static void main(String[] args) {
    /*
     Map<String,Integer> paginationValue= ImmutableMap.<String,Integer>builder().put("totalQuantity",0).put("pageNumber",1).put("pageQuantity",0).build();
     return PageResult.<User>builder().paginationData(Lists.newArrayList()).paginationValue(paginationValue).build();
     */
  }

}
