package com.danke.helper;

import static com.danke.helper.GroupInfoMapping.Table_Name;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.functions.TableDynamic;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.danke.wrapper.GroupInfoQuery;
import com.danke.wrapper.GroupInfoUpdate;
import java.lang.Override;
import java.lang.String;
import java.util.function.Supplier;

/**
 *
 * GroupInfoDefaults
 *
 * @author powered by FluentMybatis
 */
public class GroupInfoDefaults implements IDefaultSetter, IDefaultGetter {
  public static final GroupInfoDefaults INSTANCE = new GroupInfoDefaults();

  private TableDynamic dynamic;

  private GroupInfoDefaults() {
  }

  @Override
  public void setEntityByDefault(IEntity entity) {
    this.setInsertDefault(entity);
  }

  @Override
  public GroupInfoQuery query() {
    return new GroupInfoQuery();
  }

  @Override
  public GroupInfoQuery defaultQuery() {
    GroupInfoQuery query = new GroupInfoQuery();
    this.setQueryDefault(query);
    return query;
  }

  @Override
  public GroupInfoUpdate updater() {
    return new GroupInfoUpdate();
  }

  @Override
  public GroupInfoUpdate defaultUpdater() {
    GroupInfoUpdate updater = new GroupInfoUpdate();
    this.setUpdateDefault(updater);
    return updater;
  }

  /**
   * 自动分配表别名查询构造器(join查询的时候需要定义表别名)
   * 如果要自定义别名, 使用方法 {@link #aliasQuery(String)}
   */
  @Override
  public GroupInfoQuery aliasQuery() {
    Parameters parameters = new Parameters();
    GroupInfoQuery query = new GroupInfoQuery(parameters.alias(), parameters);
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  @Override
  public GroupInfoQuery aliasQuery(String alias) {
    GroupInfoQuery query = new GroupInfoQuery(alias, new Parameters());
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 关联查询, 根据fromQuery自动设置别名和关联?参数
   * 如果要自定义别名, 使用方法 {@link #aliasWith(String, BaseQuery)}
   */
  @Override
  public GroupInfoQuery aliasWith(BaseQuery fromQuery) {
    Parameters parameters = fromQuery.getWrapperData().getParameters();
    GroupInfoQuery query = new GroupInfoQuery(parameters.alias(), parameters);
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 关联查询, 显式设置别名, 根据fromQuery自动关联?参数
   */
  @Override
  public GroupInfoQuery aliasWith(String alias, BaseQuery fromQuery) {
    GroupInfoQuery query = new GroupInfoQuery(alias, fromQuery.getWrapperData().getParameters());
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 设置表名动态设置
   */
  public GroupInfoDefaults setTableDynamic(TableDynamic dynamic) {
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
