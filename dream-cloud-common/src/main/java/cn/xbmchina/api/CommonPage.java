//package cn.xbmchina.api;
//
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageInfo;
//
//import java.util.List;
//
///**
// * 分页数据封装类
// * Created by macro on 2019/4/19.
// */
//public class CommonPage<T> {
//    private Integer pageNum;
//    private Integer pageSize;
//    private Long total;
//    private List<T> list;
//
//    /**
//     * 将PageHelper分页后的list转为分页信息
//     */
//    public static <T> CommonPage<T> restPage(List<T> list) {
//        CommonPage<T> result = new CommonPage<T>();
//        PageInfo<T> pageInfo = new PageInfo<T>(list);
//        result.setPageNum(pageInfo.getPageNum());
//        result.setPageSize(pageInfo.getPageSize());
//        result.setTotal(pageInfo.getTotal());
//        result.setList(pageInfo.getList());
//        return result;
//    }
//
//    /**
//     * 将SpringData分页后的list转为分页信息
//     */
//    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
//        CommonPage<T> result = new CommonPage<T>();
//        result.setPageNum(pageInfo.getPageNum());
//        result.setPageSize(pageInfo.getPageSize());
//        result.setTotal(pageInfo.getTotal());
//        result.setList(pageInfo.getResult());
//        return result;
//    }
//
//    public Integer getPageNum() {
//        return pageNum;
//    }
//
//    public void setPageNum(Integer pageNum) {
//        this.pageNum = pageNum;
//    }
//
//    public Integer getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(Integer pageSize) {
//        this.pageSize = pageSize;
//    }
//
//
//    public List<T> getList() {
//        return list;
//    }
//
//    public void setList(List<T> list) {
//        this.list = list;
//    }
//
//    public Long getTotal() {
//        return total;
//    }
//
//    public void setTotal(Long total) {
//        this.total = total;
//    }
//}