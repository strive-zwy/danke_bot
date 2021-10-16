package com.danke.helper;

import static cn.org.atool.fluent.mybatis.base.model.InsertList.el;
import static cn.org.atool.fluent.mybatis.mapper.FluentConst.*;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.*;
import static cn.org.atool.fluent.mybatis.utility.SqlProviderUtils.*;
import static com.danke.helper.GroupInfoMapping.*;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.BaseSqlProvider;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.model.InsertList;
import cn.org.atool.fluent.mybatis.base.model.UpdateDefault;
import cn.org.atool.fluent.mybatis.base.model.UpdateSet;
import cn.org.atool.fluent.mybatis.mapper.MapperSql;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import com.danke.entity.GroupInfo;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * GroupInfoSqlProvider: 动态语句封装
 *
 * @author powered by FluentMybatis
 */
public class GroupInfoSqlProvider extends BaseSqlProvider<GroupInfo> {
  /**
   * 默认设置器
   */
  private static final GroupInfoDefaults defaults = GroupInfoDefaults.INSTANCE;

  @Override
  public boolean primaryIsNull(GroupInfo entity) {
    return entity.getId() == null;
  }

  @Override
  public boolean primaryNotNull(GroupInfo entity) {
    return entity.getId() != null;
  }

  @Override
  protected void insertEntity(InsertList inserts, String prefix, GroupInfo entity, boolean withPk) {
    if (withPk) {
    	inserts.add(prefix, id, entity.getId(), null);
    }
    inserts.add(prefix, gmtCreate, entity.getGmtCreate(), "now()");
    inserts.add(prefix, gmtModified, entity.getGmtModified(), "now()");
    inserts.add(prefix, groupName, entity.getGroupName(), "");
    inserts.add(prefix, groupNumber, entity.getGroupNumber(), "");
  }

  @Override
  protected List<String> insertBatchEntity(int index, GroupInfo entity, boolean withPk) {
    List<String> values = new ArrayList<>();
    if (withPk) {
    	values.add(el("list[" + index + "].", id, entity.getId(), null));
    }
    values.add(el("list[" + index + "].", gmtCreate, entity.getGmtCreate(), "now()"));
    values.add(el("list[" + index + "].", gmtModified, entity.getGmtModified(), "now()"));
    values.add(el("list[" + index + "].", groupName, entity.getGroupName(), ""));
    values.add(el("list[" + index + "].", groupNumber, entity.getGroupNumber(), ""));
    return values;
  }

  public String updateById(Map<String, Object> map) {
    GroupInfo entity = getParas(map, Param_ET);
    assertNotNull(Param_Entity, entity);
    MapperSql sql = new MapperSql();
    sql.UPDATE(this.tableName());
    UpdateSet updates = new UpdateSet()
    	.add(this.dbType(), gmtCreate, entity.getGmtCreate(), "")
    	.add(this.dbType(), gmtModified, entity.getGmtModified(), "now()")
    	.add(this.dbType(), groupName, entity.getGroupName(), "")
    	.add(this.dbType(), groupNumber, entity.getGroupNumber(), "");
    sql.SET(updates.getUpdates());
    sql.WHERE(id.el(this.dbType(), Param_ET));
    return sql.toString();
  }

  @Override
  public List<String> updateDefaults(Map<String, String> updates, boolean ignoreLockVersion) {
    UpdateDefault defaults = new UpdateDefault(updates);
    defaults.add(dbType(), gmtModified, "now()");
    return defaults.getUpdateDefaults();
  }

  @Override
  public String tableName() {
    return defaults.table().get();
  }

  @Override
  protected IMapping mapping() {
    return MAPPING;
  }

  @Override
  public List<String> allFields(boolean withPk) {
    if (withPk) {
    	return Arrays.asList("id", "gmt_create", "gmt_modified", "group_name", "group_number");
    } else {
    	return Arrays.asList("gmt_create", "gmt_modified", "group_name", "group_number");
    }
  }

  @Override
  protected void setEntityByDefault(IEntity entity) {
    defaults.setEntityByDefault(entity);
  }

  @Override
  public DbType dbType() {
    return DbType.MYSQL;
  }

  @Override
  protected boolean longTypeOfLogicDelete() {
    return false;
  }
}
