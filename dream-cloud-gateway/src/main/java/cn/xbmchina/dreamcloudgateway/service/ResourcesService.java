package cn.xbmchina.dreamcloudgateway.service;

import cn.xbmchina.bo.RResourcesBo;
import cn.xbmchina.domain.Resources;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ResourcesService {


    public List<RResourcesBo> resourcesListWithRole(Integer rid);

    public PageInfo<Resources> selectByPage(Resources resources, int pageNum, int pageSize);

    public void addResources(Resources resources);

    public void delResources(Integer id);

    public List<Resources> loadMenu(Resources resources);

    public List<Resources>queryAll();
}
