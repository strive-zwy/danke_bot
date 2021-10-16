package com.danke.dao.base;

import static com.danke.helper.TaskDefaults.INSTANCE;

import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import com.danke.entity.Task;
import com.danke.mapper.TaskMapper;
import com.danke.wrapper.TaskQuery;
import com.danke.wrapper.TaskUpdate;
import java.lang.Override;
import javax.annotation.Resource;

/**
 *
 * TaskBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class TaskBaseDao extends BaseDao<Task> {
  @Resource(
      name = "taskMapper"
  )
  protected TaskMapper mapper;

  @Override
  public TaskMapper mapper() {
    return mapper;
  }

  @Override
  protected TaskQuery query() {
    return new TaskQuery();
  }

  @Override
  protected TaskQuery defaultQuery() {
    return INSTANCE.defaultQuery();
  }

  @Override
  protected TaskUpdate updater() {
    return new TaskUpdate();
  }

  @Override
  protected TaskUpdate defaultUpdater() {
    return INSTANCE.defaultUpdater();
  }
}
