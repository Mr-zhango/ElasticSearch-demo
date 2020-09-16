package com.iistd.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * <b> 分页通用类 </b>
 *
 * @author kangxu
 * @param <T>
 *
 */
public class Pager<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4476276522269472300L;

    /**
     * currentPage 当前页
     */
    private int currentPage = 1;
    /**
     * pageSize 每页大小
     */
    private int pageSize = 20;
    /**
     * pageTotal 总页数
     */
    private int pageTotal;
    /**
     * recordTotal 总条数
     */
    private int recordTotal = 0;
    /**
     * firstPage 第一页
     */
    private int firstPage = 1;

    /**
     * content 每页的内容
     */
    private List<T> content;

    /**
     * pager构造器
     * @param content
     */
    public Pager(List<T> content, int currentPage, int pageSize) {
        super();
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.otherAttr();
    }

    public Pager(List<T> content, int currentPage) {
        super();
        this.content = content;
        this.currentPage = currentPage;
        this.otherAttr();
    }

    public Pager(List<T> content) {
        super();
        this.content = content;
        this.otherAttr();
    }
    /**
     * pager获取分好页的数据记录
     * @return
     */
    public List<T> getPageContent() {
        if(this.content == null || this.content.size() < 1)
            return null;

        List<T> pageContent = new ArrayList<T>();
        //当前页的第一行为：(页码-1)x每页行数
        int firstLine = (this.currentPage - 1) * this.pageSize;
        //当前页的第最后行为：页码-x每页行数-1（如果最后一页为最大行数）
        int lastLine = this.currentPage == this.pageTotal ? this.recordTotal : this.currentPage * this.pageSize;
        for(int i = firstLine; i < lastLine ; i++) {
            pageContent.add(this.content.get(i));
        }
        return pageContent;
    }

    // 以下set方式是需要赋值的

    /**
     * 设置当前页 <br>
     *
     * @author kangxu
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 设置每页大小,也可以不用赋值,默认大小为10条 <br>
     *
     * @author kangxu
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置总条数,默认为0 <br>
     *
     * @author kangxu
     *
     * @param recordTotal
     */
    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
        otherAttr();
    }

    /**
     * 设置分页内容 <br>
     *
     * @author kangxu
     *
     * @param content
     */
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * 设置其他参数
     *
     * @author kangxu
     *
     */
    public void otherAttr() {
        if(this.content != null) {
            // 总条数
            this.recordTotal = this.content.size();
            // 总页数
            this.pageTotal = this.recordTotal % this.pageSize > 0 ? this.recordTotal / this.pageSize + 1 : this.recordTotal / this.pageSize;

            // 设置并调整当前页
            if(this.currentPage < 1)
                this.currentPage = 1;
            else if(this.currentPage > this.pageTotal)
                this.currentPage = this.pageTotal;
        }
    }

    /**
     * @return the pageTotal
     */
    public int getPageTotal() {
        return pageTotal;
    }

    /**
     * @param pageTotal the pageTotal to set
     */
    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }


    /**
     * @return the firstPage
     */
    public int getFirstPage() {
        return firstPage;
    }

    /**
     * @param firstPage the firstPage to set
     */
    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }


    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return the recordTotal
     */
    public int getRecordTotal() {
        return recordTotal;
    }

    /**
     * @return the content
     */
    public List<T> getContent() {
        return content;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pager :{currentPage=");
        builder.append(currentPage);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(", pageTotal=");
        builder.append(pageTotal);
        builder.append(", recordTotal=");
        builder.append(recordTotal);
        builder.append(", firstPage=");
        builder.append(firstPage);
        builder.append(", content=");
        builder.append(content);
        builder.append("}");
        return builder.toString();
    }

}
