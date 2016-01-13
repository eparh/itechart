package persistence.model;

public class ViewSettings {
    private long count = 10;
    private long pageNumber = 1;
    private long pages;

    public void setStandardView(){
        count = 10;
        pageNumber = 1;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getStart() {
        return (pageNumber - 1) * count;
    }

    public long getPages() {
        return pages;
    }

    public void countPages(long totalCount) {
        pages = totalCount / count;
        if (totalCount % count != 0) {
            pages++;
        }
    }
}
