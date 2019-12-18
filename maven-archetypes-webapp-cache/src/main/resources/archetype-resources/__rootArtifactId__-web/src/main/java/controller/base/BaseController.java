package com.aaa.test.controller.base;

import com.aaa.test.service.auto.base.IBaseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class BaseController<M extends IBaseService<T>, T> {

    @Autowired
    protected M service;

    public M getBaseService() {
        return this.service;
    }

    @PostMapping("/insert")
    @ResponseBody
    public boolean insert(@RequestBody T entity) {
        return this.getBaseService().insert(entity);
    }

    @PostMapping("/insertBatch")
    @ResponseBody
    public boolean insertBatch(@RequestBody List<T> objList) {
        return this.getBaseService().insertBatch(objList);
    }

    @PostMapping("/deleteLogic")
    @ResponseBody
    public boolean deleteLogic(@RequestBody T entity) {
        return this.getBaseService().deleteLogic(entity);
    }

//    @GetMapping("/deleteLogicById")
//    @ResponseBody
//    public boolean deleteLogicById(Serializable id) {
//        return this.getBaseService().deleteLogicById(id);
//    }

//    @GetMapping("/deleteLogicByIds")
//    @ResponseBody
//    public boolean deleteLogicByIds(Collection<? extends Serializable> idList) {
//        return this.getBaseService().deleteLogicByIds(idList);
//    }

    @PostMapping("/updateByWrapper")
    @ResponseBody
    public boolean updateByWrapper(@RequestBody T entity) {
        Wrapper<T> wrapper = new QueryWrapper(entity);
        return this.getBaseService().updateByWrapper(entity, wrapper);
    }

    @PostMapping("/updateWithId")
    @ResponseBody
    public boolean updateWithId(@RequestBody T entity) {
        return this.getBaseService().updateWithId(entity);
    }

//    @GetMapping("/selectById")
//    @ResponseBody
//    public T selectById(Serializable id) {
//        return this.getBaseService().selectById(id);
//    }


    @PostMapping("/selectOne")
    @ResponseBody
    public T selectOne(@RequestBody T entity) {
        return this.getBaseService().selectOne(entity);
    }

    @PostMapping("/selectList")
    @ResponseBody
    public List<T> selectList(@RequestBody T entity) {
        return this.getBaseService().selectList(entity);
    }

//    @PostMapping("/selectByIds")
//    @ResponseBody
//    public Collection<T> selectByIds(Collection<? extends Serializable> idList) {
//        return this.getBaseService().selectByIds(idList);
//    }

    @PostMapping("/page")
    @ResponseBody
    public IPage<T> page(T entity, Page<T> page) {
        return this.getBaseService().selectPage(entity, page);
    }
}