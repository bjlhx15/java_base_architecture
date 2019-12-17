package ${package}.service.auto.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 基本业务类接口
 */
public interface IBaseService<T> {

    boolean insert(T obj);

    boolean insertBatch(List<T> objList);

    boolean deleteLogic(T obj);

    boolean deleteLogicById(Serializable id);

    boolean deleteLogicByIds(Collection<? extends Serializable> idList);

    boolean updateWithId(T entity);

    boolean updateByWrapper(T entity, Wrapper<T> updateWrapper);

    List<T> select();

    T selectById(Serializable id);

    List<T> selectList(T obj);

    Collection<T> selectByIds(Collection<? extends Serializable> idList);

    IPage<T> selectPage(T obj, Integer pageNo, Integer pageSize);

    IPage<T> selectPage(T entity, IPage page);
}