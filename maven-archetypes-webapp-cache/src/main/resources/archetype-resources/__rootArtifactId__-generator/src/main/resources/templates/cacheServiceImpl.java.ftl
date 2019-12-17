package ${package.Service}.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${package.Service}.base.BaseServiceImpl;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@CacheConfig(cacheNames = "${entity}")
public class ${table.serviceImplName} extends BaseServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean insert(${entity} obj) {
        return this.save(obj);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean insertBatch(List<${entity}> objList) {
        return this.saveBatch(objList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean deleteLogic(${entity} obj) {
        Wrapper wrapper = new QueryWrapper<>(obj);
        return this.remove(wrapper);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean deleteLogicById(Serializable id) {
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean deleteLogicByIds(Collection<? extends Serializable> idList) {
        return this.removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean updateWithId(${entity} entity) {
        return this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(value = "${entity}", allEntries = true)
    public boolean updateByWrapper(AccountBalance entity, Wrapper<${entity}> updateWrapper) {
        return this.update(entity, updateWrapper);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public List<${entity}> select() {
        return this.list();
    }

    @Override
    @Cacheable(unless = "#result == null")
    public AccountBalance selectById(Serializable id) {
        return this.getById(id);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public List<${entity}> selectList(AccountBalance entity) {
        Wrapper wrapper = new QueryWrapper<>(entity);
        return this.list(wrapper);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public Collection<${entity}> selectByIds(Collection<? extends Serializable> idList) {
        return this.listByIds(idList);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public IPage<${entity}> selectPage(${entity} entity, Integer pageNo, Integer pageSize) {
        Page<${entity}> page = new Page<${entity}>(pageNo, pageSize);
        Wrapper wrapper = new QueryWrapper<${entity}>(entity);
        return this.page(page, wrapper);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public IPage<${entity}> selectPage(${entity} entity, IPage page) {
        Wrapper wrapper = new QueryWrapper<${entity}>(entity);
        return this.page(page, wrapper);
    }
}


