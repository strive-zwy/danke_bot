package com.danke.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.RefMethod;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.functions.TableSupplier;
import java.io.Serializable;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * UserInfo: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "user_info",
    suffix = ""
)
public class UserInfo extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Long id;

  /**
   */
  @TableField(
      value = "gmt_create",
      insert = "now()"
  )
  private String gmtCreate;

  /**
   */
  @TableField(
      value = "gmt_modified",
      insert = "now()",
      update = "now()"
  )
  private String gmtModified;

  /**
   * 希望被机器人喊得昵称，默认是小主
   */
  @TableField("nickname")
  private String nickname;

  /**
   * qq号
   */
  @TableField("qq_number")
  private Long qqNumber;

  /**
   * 权限
   */
  @TableField("role")
  private Integer role;

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return UserInfo.class;
  }

  @Override
  public final UserInfo changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final UserInfo changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("creatorId = id")
  public List<Task> findTaskList() {
    return super.invoke("findTaskList", true);
  }
}
