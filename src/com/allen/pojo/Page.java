package com.allen.pojo;

import java.util.List;

/**
 * @author Allen
 * @date 2020/9/20 21:21
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    private Integer pageNo;
    private Integer pageTotal;
    private Integer pageSize = PAGE_SIZE;
    private Integer itemTotal;
    private List<T> items;
    // 分页条的请求地址
    private String url;

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo<1){
            pageNo = 1;
        }else if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setItemTotal(Integer itemTotal) {
        this.itemTotal = itemTotal;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getItemTotal() {
        return itemTotal;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", itemTotal=" + itemTotal +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
