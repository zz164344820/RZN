package com.zyhealth.expertlib.bean;

/**
 * Created by Administrator on 2016-08-02.
 */
public class ResponsePagerBean {
    private String  pageSize;//分页大小
    private String  totalResults;//总结果数
    private String currentPage;//当前页码
    private String totalPages;//总共页数

    public ResponsePagerBean() {
    }

    public ResponsePagerBean(String pageSize, String totalResults, String currentPage, String totalPages) {
        this.pageSize = pageSize;
        this.totalResults = totalResults;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "ResponsePagerBean{" +
                "pageSize='" + pageSize + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPages='" + totalPages + '\'' +
                '}';
    }
}
