package com.danke.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import com.danke.entity.GroupInfo;
import com.danke.helper.GroupInfoDefaults;
import com.danke.helper.GroupInfoMapping;
import com.danke.helper.GroupInfoWrapperHelper.UpdateOrderBy;
import com.danke.helper.GroupInfoWrapperHelper.UpdateSetter;
import com.danke.helper.GroupInfoWrapperHelper.UpdateWhere;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * GroupInfoUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class GroupInfoUpdate extends BaseUpdate<GroupInfo, GroupInfoUpdate, GroupInfoQuery> {
  /**
   * 默认设置器
   */
  private static final GroupInfoDefaults defaults = GroupInfoDefaults.INSTANCE;

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

  public GroupInfoUpdate() {
    this(defaults.table(), null);
  }

  public GroupInfoUpdate(Supplier<String> table, String alias) {
    super(table, alias, GroupInfo.class, GroupInfoQuery.class);
  }

  @Override
  public UpdateWhere where() {
    return this.where;
  }

  @Override
  protected IMapping mapping() {
    return GroupInfoMapping.MAPPING;
  }

  protected List<String> allFields() {
    return GroupInfoMapping.ALL_COLUMNS;
  }

  @Override
  public DbType dbType() {
    return DbType.MYSQL;
  }

  public static GroupInfoUpdate updater() {
    return new GroupInfoUpdate();
  }

  public static GroupInfoUpdate updater(Supplier<String> table) {
    return new GroupInfoUpdate(table, null);
  }

  public static GroupInfoUpdate defaultUpdater() {
    return defaults.defaultUpdater();
  }

  @Override
  protected Map<String, FieldMapping> column2mapping() {
    return GroupInfoMapping.Column2Mapping;
  }
}
