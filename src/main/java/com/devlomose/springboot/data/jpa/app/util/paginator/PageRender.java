package com.devlomose.springboot.data.jpa.app.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * PageRender at: src/main/java/com/devlomose/springboot/data/jpa/app/util/paginator
 * Created by @DevLomoSE at 21/9/21 13:24.
 */
public class PageRender<T> {

    private String url;
    private Page<T> page;

    private int totalPages;
    private int itemsPerPage;
    private int currentPage;

    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        this.itemsPerPage = page.getSize();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber()+1;

        int from, to;
        if( this.totalPages <= this.itemsPerPage ){
            from = 1;
            to = this.totalPages;
        }else{
            if(this.currentPage <= this.itemsPerPage/2){
                from = 1;
                to = this.itemsPerPage;
            }else if(this.currentPage >= this.totalPages - this.itemsPerPage/2){
                from = this.totalPages - this.itemsPerPage + 1;
                to = this.itemsPerPage;
            } else {
                from = currentPage - itemsPerPage/2;
                to = totalPages;
            }
        }

        for (int i=0; i<to; i++){
            this.pages.add(new PageItem(from+i, currentPage == from+i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }

    @Override
    public String toString() {
        return "PageRender{" +
                "url='" + url + '\'' +
                ", page=" + page +
                '}';
    }
}
