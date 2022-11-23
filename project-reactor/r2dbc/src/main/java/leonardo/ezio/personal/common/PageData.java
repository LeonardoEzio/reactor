package leonardo.ezio.personal.common;

import cn.hutool.core.util.PageUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageData<T> {

    private Integer page;

    private Integer pageSize;

    private Long total;

    private Integer totalPage;

    private List<T> records;

    private PageData() {

    }

    public static <T> PageData<T> of(Integer page,Integer pageSize, Long total, List<T> records){
        PageData pageData = new PageData();
        pageData.setPage(page);
        pageData.setPageSize(pageSize);
        pageData.setTotal(total);
        final int totalPage = PageUtil.totalPage(total.intValue(), pageSize);
        pageData.setTotalPage(totalPage);
        pageData.setRecords(records);
        return pageData;
    }

    public static <T> PageData<T> empty(Integer page,Integer pageSize){
        PageData pageData = new PageData();
        pageData.setPage(page);
        pageData.setPageSize(pageSize);
        pageData.setTotal(0L);
        pageData.setTotalPage(0);
        pageData.setRecords(new ArrayList());
        return pageData;
    }
}
