package com.wm.response;

import com.github.pagehelper.Page;

public class PageCommonReturnType extends CommonReturnType{

    private Long total;
    private Integer pageNum;
    private Integer pageSize;
    private Integer draw;

    public static PageCommonReturnType create(Page result, Integer draw_) {
        PageCommonReturnType commonReturnType = new PageCommonReturnType();
        commonReturnType.setStatus("success");
        commonReturnType.setData(result);
        commonReturnType.setTotal(result.getTotal());
        commonReturnType.setPageNum(result.getPageNum());
        commonReturnType.setPageSize(result.getPageSize());
        if (draw_ != null) {
            commonReturnType.setDraw(draw_);
        }
        return commonReturnType;
    }
    public static PageCommonReturnType create(Page result) {
        return create(result, 0);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }
}
