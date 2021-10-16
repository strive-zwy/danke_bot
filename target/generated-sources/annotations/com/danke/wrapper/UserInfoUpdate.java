package com.danke.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import com.danke.entity.UserInfo;
import com.danke.helper.UserInfoDefaults;
import com.danke.helper.UserInfoMapping;
import com.danke.helper.UserInfoWrapperHelper.UpdateOrderBy;
import com.danke.helper.UserInfoWrapperHelper.UpdateSetter;
import com.danke.helper.UserInfoWrapperHelper.UpdateWhere;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * UserInfoUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class UserInfoUpdate extends BaseUpdate<UserInfo, UserInfoUpdate, UserInfoQuery> {
  /**
   * 默认设置器
   */
  private static final UserInfoDefaults defaults = UserInfoDefaults.INSTANCE;

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

  public UserInfoUpdate() {
    this(defaults.table(), null);
  }

  public UserInfoUpdate(Supplier<String> table, String alias) {
    super(table, alias, UserInfo.class, UserInfoQuery.class);
  }

  @Override
  public UpdateWhere where() {
    return this.where;
  }

  @Override
  protected IMapping mapping() {
    return UserInfoMapping.MAPPING;
  }

  protected List<String> allFields() {
    return UserInfoMapping.ALL_COLUMNS;
  }

  @Override
  public DbType dbType() {
    return DbType.MYSQL;
  }

  public static UserInfoUpdate updater() {
    return new UserInfoUpdate();
  }

  public static UserInfoUpdate updater(Supplier<String> table) {
    return new UserInfoUpdate(table, null);
  }

  public static UserInfoUpdate defaultUpdater() {
    return defaults.defaultUpdater();
  }

  @Override
  protected Map<String, FieldMapping> column2mapping() {
    return UserInfoMapping.Column2Mapping;
  }
}
