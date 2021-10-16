package com.danke.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import com.danke.entity.Task;
import com.danke.helper.TaskDefaults;
import com.danke.helper.TaskMapping;
import com.danke.helper.TaskWrapperHelper.UpdateOrderBy;
import com.danke.helper.TaskWrapperHelper.UpdateSetter;
import com.danke.helper.TaskWrapperHelper.UpdateWhere;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * TaskUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class TaskUpdate extends BaseUpdate<Task, TaskUpdate, TaskQuery> {
  /**
   * 默认设置器
   */
  private static final TaskDefaults defaults = TaskDefaults.INSTANCE;

  /**
   * same as {@link #update}
   */
  public final UpdateSetter set = new UpdateSetter(this);

  /**
   * replaced by {@link #set}
   */
  @Deprecated
  public final UpdateSetter update = set;

  public final UpdateWhere where = new UpdateWhere(this);

  public final UpdateOrderBy orderBy = new UpdateOrderBy(this);

  public TaskUpdate() {
    this(defaults.table(), null);
  }

  public TaskUpdate(Supplier<String> table, String alias) {
    super(table, alias, Task.class, TaskQuery.class);
  }

  @Override
  public UpdateWhere where() {
    return this.where;
  }

  @Override
  protected IMapping mapping() {
    return TaskMapping.MAPPING;
  }

  protected List<String> allFields() {
    return TaskMapping.ALL_COLUMNS;
  }

  @Override
  public DbType dbType() {
    return DbType.MYSQL;
  }

  public static TaskUpdate updater() {
    return new TaskUpdate();
  }

  public static TaskUpdate updater(Supplier<String> table) {
    return new TaskUpdate(table, null);
  }

  public static TaskUpdate defaultUpdater() {
    return defaults.defaultUpdater();
  }

  @Override
  protected Map<String, FieldMapping> column2mapping() {
    return TaskMapping.Column2Mapping;
  }
}
