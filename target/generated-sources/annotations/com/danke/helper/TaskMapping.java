package com.danke.helper;

import static java.util.Optional.ofNullable;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.base.model.FieldType;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * TaskMapping: Entity类字段和表结构映射
 *
 * @author powered by FluentMybatis
 */
public interface TaskMapping extends IMapping {
  TaskMapping MAPPING = new TaskMapping(){};

  /**
   * 表名称
   */
  String Table_Name = "task";

  /**
   * Entity名称
   */
  String Entity_Name = "Task";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  FieldMapping id = new FieldMapping("id", "id");

  /**
   * 实体属性 : 数据库字段 映射
   *  gmtCreate : gmt_create
   */
  FieldMapping gmtCreate = new FieldMapping("gmtCreate", "gmt_create");

  /**
   * 实体属性 : 数据库字段 映射
   *  gmtModified : gmt_modified
   */
  FieldMapping gmtModified = new FieldMapping("gmtModified", "gmt_modified");

  /**
   * 实体属性 : 数据库字段 映射
   *  creatorId : creator_id
   */
  FieldMapping creatorId = new FieldMapping("creatorId", "creator_id");

  /**
   * 实体属性 : 数据库字段 映射
   *  pOrG : p_or_g
   */
  FieldMapping pOrG = new FieldMapping("pOrG", "p_or_g");

  /**
   * 实体属性 : 数据库字段 映射
   *  remindDate : remind_date
   */
  FieldMapping remindDate = new FieldMapping("remindDate", "remind_date");

  /**
   * 实体属性 : 数据库字段 映射
   *  remindStr : remind_str
   */
  FieldMapping remindStr = new FieldMapping("remindStr", "remind_str");

  /**
   * 实体属性 : 数据库字段 映射
   *  remindTime : remind_time
   */
  FieldMapping remindTime = new FieldMapping("remindTime", "remind_time");

  /**
   * 实体属性 : 数据库字段 映射
   *  state : state
   */
  FieldMapping state = new FieldMapping("state", "state");

  /**
   * 实体属性 : 数据库字段 映射
   *  type : type
   */
  FieldMapping type = new FieldMapping("type", "type");

  /**
   * 实例属性和数据库字段对应表
   */
  Map<String, String> Property2Column = new HashMap<String, String>() {
    {
  		this.put(id.name, id.column);
  		this.put(gmtCreate.name, gmtCreate.column);
  		this.put(gmtModified.name, gmtModified.column);
  		this.put(creatorId.name, creatorId.column);
  		this.put(pOrG.name, pOrG.column);
  		this.put(remindDate.name, remindDate.column);
  		this.put(remindStr.name, remindStr.column);
  		this.put(remindTime.name, remindTime.column);
  		this.put(state.name, state.column);
  		this.put(type.name, type.column);
    }
  };

  /**
   * 数据库字段对应的FieldMapping
   */
  Map<String, FieldMapping> Column2Mapping = new HashMap<String, FieldMapping>() {
    {
  		this.put(id.column, id);
  		this.put(gmtCreate.column, gmtCreate);
  		this.put(gmtModified.column, gmtModified);
  		this.put(creatorId.column, creatorId);
  		this.put(pOrG.column, pOrG);
  		this.put(remindDate.column, remindDate);
  		this.put(remindStr.column, remindStr);
  		this.put(remindTime.column, remindTime);
  		this.put(state.column, state);
  		this.put(type.column, type);
    }
  };

  /**
   * 数据库所有字段列表
   */
  List<String> ALL_COLUMNS = Arrays.asList(
  		id.column,
  		gmtCreate.column,
  		gmtModified.column,
  		creatorId.column,
  		pOrG.column,
  		remindDate.column,
  		remindStr.column,
  		remindTime.column,
  		state.column,
  		type.column
  );

  /**
   * 数据库所有字段列表用逗号分隔
   */
  String ALL_JOIN_COLUMNS = String.join(", ", ALL_COLUMNS);

  @Override
  default String findColumnByField(String field) {
    return Property2Column.get(field);
  }

  @Override
  default Optional<FieldMapping> findField(FieldType type) {
    switch (type) {
    	case PRIMARY_ID:
    		return ofNullable(id);
    	default:
    		return ofNullable(null);
    }
  }
}
