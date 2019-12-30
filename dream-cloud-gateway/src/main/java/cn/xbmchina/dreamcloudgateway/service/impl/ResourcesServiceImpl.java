package cn.xbmchina.dreamcloudgateway.service.impl;

import cn.xbmchina.bo.RResourcesBo;
import cn.xbmchina.domain.Resources;
import cn.xbmchina.domain.ResourcesExample;
import cn.xbmchina.dreamcloudgateway.repository.ResourcesMapper;
import cn.xbmchina.dreamcloudgateway.service.ResourcesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;


    @Override
    public List<RResourcesBo> resourcesListWithRole(Integer rid) {
        return resourcesMapper.resourcesListWithRole(rid);
    }

    @Override
    public PageInfo<Resources> selectByPage(Resources resources, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ResourcesExample example = new ResourcesExample();
        if (resources!=null) {
            ResourcesExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(resources.getId());
            criteria.andNameEqualTo(resources.getName());
            criteria.andParentIdEqualTo(resources.getParentId());
            criteria.andResKeyEqualTo(resources.getResKey());
            criteria.andTypeEqualTo(resources.getType());
        }
        List<Resources> resourcesList = resourcesMapper.selectByExample(example);
        return new PageInfo<>(resourcesList);
    }

    @Override
    public void addResources(Resources resources) {
        resourcesMapper.insert(resources);
    }

    @Override
    public void delResources(Integer id) {
        resourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Resources> loadMenu(Resources resources) {
        return resourcesMapper.loadMenu(resources);
    }

    @Override
    public List<Resources> queryAll() {
        return resourcesMapper.selectByExample(new ResourcesExample());
    }




}
