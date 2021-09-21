package com.devlomose.springboot.data.jpa.app.util.paginator;

/**
 * PageItem at: src/main/java/com/devlomose/springboot/data/jpa/app/util/paginator
 * Created by @DevLomoSE at 21/9/21 13:33.
 */
public class PageItem {

    private int number;
    private boolean current;

    public PageItem(int number, boolean current) {
        this.number = number;
        this.current = current;
    }

    public int getNumber() {
        return number;
    }

    public boolean isCurrent() {
        return current;
    }
}
