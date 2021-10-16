package com.danke.helper;

import static com.danke.helper.UserInfoMapping.Table_Name;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.functions.TableDynamic;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.danke.wrapper.UserInfoQuery;
import com.danke.wrapper.UserInfoUpdate;
import java.lang.Override;
import java.lang.String;
import java.util.function.Supplier;

/**
 *
 * UserInfoDefaults
 *
 * @author powered by FluentMybatis
 */
public class UserInfoDefaults implements IDefaultSetter, IDefaultGetter {
  public static final UserInfoDefaults INSTANCE = new UserInfoDefaults();

  private TableDynamic dynamic;

  private UserInfoDefaults() {
  }

  @Override
  public void setEntityByDefault(IEntity entity) {
    this.setInsertDefault(entity);
  }

  @Override
  public UserInfoQuery query() {
    return new UserInfoQuery();
  }

  @Override
  public UserInfoQuery defaultQuery() {
    UserInfoQuery query = new UserInfoQuery();
    this.setQueryDefault(query);
    return query;
  }

  @Override
  public UserInfoUpdate updater() {
    return new UserInfoUpdate();
  }

  @Override
  public UserInfoUpdate defaultUpdater() {
    UserInfoUpdate updater = new UserInfoUpdate();
    this.setUpdateDefault(updater);
    return updater;
  }

  /**
   * 自动分配表别名查询构造器(join查询的时候需要定义表别名)
   * 如果要自定义别名, 使用方法 {@link #aliasQuery(String)}
   */
  @Override
  public UserInfoQuery aliasQuery() {
    Parameters parameters = new Parameters();
    UserInfoQuery query = new UserInfoQuery(parameters.alias(), parameters);
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  @Override
  public UserInfoQuery aliasQuery(String alias) {
    UserInfoQuery query = new UserInfoQuery(alias, new Parameters());
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 关联查询, 根据fromQuery自动设置别名和关联?参数
   * 如果要自定义别名, 使用方法 {@link #aliasWith(String, BaseQuery)}
   */
  @Override
  public UserInfoQuery aliasWith(BaseQuery fromQuery) {
    Parameters parameters = fromQuery.getWrapperData().getParameters();
    UserInfoQuery query = new UserInfoQuery(parameters.alias(), parameters);
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 关联查询, 显式设置别名, 根据fromQuery自动关联?参数
   */
  @Override
  public UserInfoQuery aliasWith(String alias, BaseQuery fromQuery) {
    UserInfoQuery query = new UserInfoQuery(alias, fromQuery.getWrapperData().getParameters());
    this.setQueryDefault(query);
    return query;
  }

  /**
   * 设置表名动态设置
   */
  public UserInfoDefaults setTableDynamic(TableDynamic dynamic) {
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
