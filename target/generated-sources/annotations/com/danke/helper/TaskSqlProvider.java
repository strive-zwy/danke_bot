package com.danke.helper;

import static cn.org.atool.fluent.mybatis.base.model.InsertList.el;
import static cn.org.atool.fluent.mybatis.mapper.FluentConst.*;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.*;
import static cn.org.atool.fluent.mybatis.utility.SqlProviderUtils.*;
import static com.danke.helper.TaskMapping.*;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.BaseSqlProvider;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.model.InsertList;
import cn.org.atool.fluent.mybatis.base.model.UpdateDefault;
import cn.org.atool.fluent.mybatis.base.model.UpdateSet;
import cn.org.atool.fluent.mybatis.mapper.MapperSql;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import com.danke.entity.Task;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * TaskSqlProvider: 动态语句封装
 *
 * @author powered by FluentMybatis
 */
public class TaskSqlProvider extends BaseSqlProvider<Task> {
  /**
   * 默认设置器
   */
  private static final TaskDefaults defaults = TaskDefaults.INSTANCE;

  @Override
  public boolean primaryIsNull(Task entity) {
    return entity.getId() == null;
  }

  @Override
  public boolean primaryNotNull(Task entity) {
    return entity.getId() != null;
  }

  @Override
  protected void insertEntity(InsertList inserts, String prefix, Task entity, boolean withPk) {
    if (withPk) {
    	inserts.add(prefix, id, entity.getId(), null);
    }
    inserts.add(prefix, gmtCreate, entity.getGmtCreate(), "now()");
    inserts.add(prefix, gmtModified, entity.getGmtModified(), "now()");
    inserts.add(prefix, creatorId, entity.getCreatorId(), "");
    inserts.add(prefix, pOrG, entity.getPOrG(), "");
    inserts.add(prefix, remindDate, entity.getRemindDate(), "");
    inserts.add(prefix, remindStr, entity.getRemindStr(), "");
    inserts.add(prefix, remindTime, entity.getRemindTime(), "");
    inserts.add(prefix, state, entity.getState(), "");
    inserts.add(prefix, type, entity.getType(), "");
  }

  @Override
  protected List<String> insertBatchEntity(int index, Task entity, boolean withPk) {
    List<String> values = new ArrayList<>();
    if (withPk) {
    	values.add(el("list[" + index + "].", id, entity.getId(), null));
    }
    values.add(el("list[" + index + "].", gmtCreate, entity.getGmtCreate(), "now()"));
    values.add(el("list[" + index + "].", gmtModified, entity.getGmtModified(), "now()"));
    values.add(el("list[" + index + "].", creatorId, entity.getCreatorId(), ""));
    values.add(el("list[" + index + "].", pOrG, entity.getPOrG(), ""));
    values.add(el("list[" + index + "].", remindDate, entity.getRemindDate(), ""));
    values.add(el("list[" + index + "].", remindStr, entity.getRemindStr(), ""));
    values.add(el("list[" + index + "].", remindTime, entity.getRemindTime(), ""));
    values.add(el("list[" + index + "].", state, entity.getState(), ""));
    values.add(el("list[" + index + "].", type, entity.getType(), ""));
    return values;
  }

  public String updateById(Map<String, Object> map) {
    Task entity = getParas(map, Param_ET);
    assertNotNull(Param_Entity, entity);
    MapperSql sql = new MapperSql();
    sql.UPDATE(this.tableName());
    UpdateSet updates = new UpdateSet()
    	.add(this.dbType(), gmtCreate, entity.getGmtCreate(), "")
    	.add(this.dbType(), gmtModified, entity.getGmtModified(), "now()")
    	.add(this.dbType(), creatorId, entity.getCreatorId(), "")
    	.add(this.dbType(), pOrG, entity.getPOrG(), "")
    	.add(this.dbType(), remindDate, entity.getRemindDate(), "")
    	.add(this.dbType(), remindStr, entity.getRemindStr(), "")
    	.add(this.dbType(), remindTime, entity.getRemindTime(), "")
    	.add(this.dbType(), state, entity.getState(), "")
    	.add(this.dbType(), type, entity.getType(), "");
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
    	return Arrays.asList("id", "gmt_create", "gmt_modified", "creator_id", "p_or_g", "remind_date", "remind_str", "remind_time", "state", "type");
    } else {
    	return Arrays.asList("gmt_create", "gmt_modified", "creator_id", "p_or_g", "remind_date", "remind_str", "remind_time", "state", "type");
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
