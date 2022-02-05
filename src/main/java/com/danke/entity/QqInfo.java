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
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * QqInfo: 数据映射实体定义
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
    table = "qq_info",
    suffix = ""
)
public class QqInfo extends RichEntity {
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
   * 添加者的用户id （login_id）
   */
  @TableField("adder")
  private Long adder;

  /**
   * 该QQ号的头像
   */
  @TableField("avatar")
  private String avatar;

  /**
   * QQ描述
   */
  @TableField("description")
  private String description;

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

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return QqInfo.class;
  }

  @Override
  public final QqInfo changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final QqInfo changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("creatorId = id")
  public List<Task> findTaskList() {
    return super.invoke("findTaskList", true);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("id = adder")
  public Login findLogin() {
    return super.invoke("findLogin", true);
  }
}
