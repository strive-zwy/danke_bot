package com.danke.helper;

import static com.danke.helper.TaskMapping.Table_Name;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.functions.TableDynamic;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.danke.wrapper.TaskQuery;
import com.danke.wrapper.TaskUpdate;
import java.lang.Override;
import java.lang.String;
import java.util.function.Supplier;

/**
 *
 * TaskDefaults
 *
 * @author powered by FluentMybatis
 */
public class TaskDefaults implements IDefaultSetter, IDefaultGetter {
  public static final TaskDefaults INSTANCE = new TaskDefaults();

  private TableDynamic dynamic;

  private TaskDefaults() {
  }

  @Override
  public void setEntityByDefault(IEntity entity) {
    this.setInsertDefault(entity);
  }

  @Override
  public TaskQuery query() {
    return new TaskQuery();
  }

  @Override
  public TaskQuery defaultQuery() {
    TaskQuery query = new TaskQuery();
    this.setQueryDefault(query);
    return query;
  }

  @Override
  public TaskUpdate updater() {
    return new TaskUpdate();
  }

  @Override
  public TaskUpdate defaultUpdater() {
    TaskUpdate updater = new TaskUpdate();
    this.setUpdateDefault(updater);
    return updater;
  }

  /**
   * 自动分配表别名查询构造器(join查询的时候需要定义表别名)
   * 如果要自定义别名, 使用方法 {@link #aliasQuery(String)}
   */
  @Override
  public TaskQuery aliasQuery() {
    Parameters parameters = new Parameters();
    TaskQuery query = new TaskQuery(parameters.alias(), parameters);
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  @Override
  public TaskQuery aliasQuery(String alias) {
    TaskQuery query = new TaskQuery(alias, new Parameters());
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 关联查询, 根据fromQuery自动设置别名和关联?参数
   * 如果要自定义别名, 使用方法 {@link #aliasWith(String, BaseQuery)}
   */
  @Override
  public TaskQuery aliasWith(BaseQuery fromQuery) {
    Parameters parameters = fromQuery.getWrapperData().getParameters();
    TaskQuery query = new TaskQuery(parameters.alias(), parameters);
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 关联查询, 显式设置别名, 根据fromQuery自动关联?参数
   */
  @Override
  public TaskQuery aliasWith(String alias, BaseQuery fromQuery) {
    TaskQuery query = new TaskQuery(alias, fromQuery.getWrapperData().getParameters());
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 设置表名动态设置
   */
  public TaskDefaults setTableDynamic(TableDynamic dynamic) {
    this.dynamic = dynamic;
    return this;
  }

  /**
   * 获取表名
   */
  public Supplier<String> table() {
    return dynamic == null ? () -> Table_Name : () -> dynamic.get(Table_Name);
  }
}
