package ${package};

import java.io.Serializable;

/**
 * BaseQuery
 * Date: ${today}
 * Generate by autoBaseQuery
 * Powered by duxing@Taobao
 */

public class BaseQuery implements Serializable {
    private static final long serialVersionUID = 6695437844594466710L;

    private static final Integer DEFAULT_LIMIT = 20;
    private static final Integer DEFAULT_CURRENT_PAGE = 1;

    private Integer limit;
    private Integer currentPage;


    public void setCurrentPage(Integer currentPage) {
        if(currentPage>0){
            this.currentPage = currentPage;
        }
    }

    public void setLimit(Integer limit) {
        if(limit>0){
            this.limit = limit;
        }
    }

    public Integer getStartRow() {
        return (getCurrentPage()-1)*getLimit();
    }

    public Integer getLimit() {
        if(this.limit==null)limit=DEFAULT_LIMIT;
        return limit;
    }

    public Integer getCurrentPage() {
        if(this.currentPage==null)currentPage=DEFAULT_CURRENT_PAGE;
        return currentPage;
    }
}