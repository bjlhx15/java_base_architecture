package ${package}.controller.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

public abstract class BaseController<M extends IService<T>, T> {

    @Autowired
    protected M service;

    public M getBaseService() {
        return this.service;
    }

    @PostMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody T obj) {
        return this.getBaseService().save(obj);
    }

    @PostMapping("/saveBatch")
    @ResponseBody
    public boolean saveBatch(@RequestBody List<T> objList) {
        return this.getBaseService().saveBatch(objList);
    }

    @PostMapping("/remove")
    @ResponseBody
    public boolean remove(@RequestBody T t) {
        Wrapper<T> wrapper = new QueryWrapper(t);
        return this.getBaseService().remove(wrapper);
    }

    @GetMapping("/removeById")
    @ResponseBody
    public boolean removeById(Long id) {
        return this.getBaseService().removeById(id);
    }

    @GetMapping("/removeByIds")
    @ResponseBody
    public boolean removeByIds(List<Long> id) {
        return this.getBaseService().removeByIds(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody T t) {
        Wrapper<T> wrapper = new QueryWrapper(t);
        return this.getBaseService().update(t, wrapper);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public boolean updateById(@RequestBody T t) {
        return this.getBaseService().updateById(t);
    }

    @GetMapping("/getById")
    @ResponseBody
    public T getById(Long id) {
        return this.getBaseService().getById(id);
    }


    @PostMapping("/getOne")
    @ResponseBody
    public T getOne(@RequestBody T t) {
        Wrapper<T> wrapper = new QueryWrapper(t);
        return this.getBaseService().getOne(wrapper);
    }

    @PostMapping("/list")
    @ResponseBody
    public List<T> list(@RequestBody T t) {
        Wrapper<T> wrapper = new QueryWrapper(t);
        return this.getBaseService().list(wrapper);
    }

    @PostMapping("/listByIds")
    @ResponseBody
    public Collection<T> listByIds(List<Long> ids) {
        return this.getBaseService().listByIds(ids);
    }

    @PostMapping("/page")
    @ResponseBody
    public IPage<T> page(T t,Page<T> page) {
        Wrapper<T> wrapper = new QueryWrapper(t);
        return this.getBaseService().page(page, wrapper);
    }
}