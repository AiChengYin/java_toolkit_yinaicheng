package top.yinaicheng.common;
import javax.validation.constraints.Min;
/**
 * 分页查询
 * @author yinaicheng
 */
public class PageQuery
{
  /**
   * 页码
   */
  @Min(value=1, message="当前页码不合法")
  private int pageNumber=1;

  /**
   * 每页展示数量
   */
  @Min(value=1, message="每页展示数量不合法")
  private int pageQuantity=10;
  
  public int getOffset(){
    return (pageNumber-1)*pageQuantity;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageQuantity() {
    return pageQuantity;
  }

  public void setPageQuantity(int pageQuantity) {
    this.pageQuantity = pageQuantity;
  }
}

