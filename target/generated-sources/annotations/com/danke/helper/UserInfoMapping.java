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
 * UserInfoMapping: Entity类字段和表结构映射
 *
 * @author powered by FluentMybatis
 */
public interface UserInfoMapping extends IMapping {
  UserInfoMapping MAPPING = new UserInfoMapping(){};

  /**
   * 表名称
   */
  String Table_Name = "user_info";

  /**
   * Entity名称
   */
  String Entity_Name = "UserInfo";

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
   *  nickname : nickname
   */
  FieldMapping nickname = new FieldMapping("nickname", "nickname");

  /**
   * 实体属性 : 数据库字段 映射
   *  qqNumber : qq_number
   */
  FieldMapping qqNumber = new FieldMapping("qqNumber", "qq_number");

  /**
   * 实体属性 : 数据库字段 映射
   *  role : role
   */
  FieldMapping role = new FieldMapping("role", "role");

  /**
   * 实例属性和数据库字段对应表
   */
  Map<String, String> Property2Column = new HashMap<String, String>() {
    {
  		this.put(id.name, id.column);
  		this.put(gmtCreate.name, gmtCreate.column);
  		this.put(gmtModified.name, gmtModified.column);
  		this.put(nickname.name, nickname.column);
  		this.put(qqNumber.name, qqNumber.column);
  		this.put(role.name, role.column);
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
  		this.put(nickname.column, nickname);
  		this.put(qqNumber.column, qqNumber);
  		this.put(role.column, role);
    }
  };

  /**
   * 数据库所有字段列表
   */
  List<String> ALL_COLUMNS = Arrays.asList(
  		id.column,
  		gmtCreate.column,
  		gmtModified.column,
  		nickname.column,
  		qqNumber.column,
  		role.column
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
