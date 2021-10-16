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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Task: 数据映射实体定义
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
    table = "task",
    suffix = ""
)
public class Task extends RichEntity {
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
   * 创建者id，个人任务是user_id,群任务是group_id
   */
  @TableField("creator_id")
  private Long creatorId;

  /**
   * 个人任务还是群任务：0-个人任务、1-群任务
   */
  @TableField("p_or_g")
  private Integer pOrG;

  /**
   * 任务运行日期
   */
  @TableField("remind_date")
  private String remindDate;

  /**
   * 提醒内容
   */
  @TableField("remind_str")
  private String remindStr;

  /**
   * 任务运行时间
   */
  @TableField("remind_time")
  private String remindTime;

  /**
   * 任务状态：0-正常执行、1-已取消
   */
  @TableField("state")
  private Integer state;

  /**
   * 任务类型：0-一次性任务、1-每日任务、2-工作日任务、3-周末任务、4-每周自定义任务
   */
  @TableField("type")
  private Integer type;

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return Task.class;
  }

  @Override
  public final Task changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final Task changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("id = creatorId")
  public UserInfo findUserInfo() {
    return super.invoke("findUserInfo", true);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("id = creatorId")
  public GroupInfo findGroupInfo() {
    return super.invoke("findGroupInfo", true);
  }
}
