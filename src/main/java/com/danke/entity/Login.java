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
 * Login: 数据映射实体定义
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
    table = "login",
    suffix = ""
)
public class Login extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Long id;

  /**
   * 用户登录后申请的API Key
   */
  @TableField("api_key")
  private String apiKey;

  /**
   * 用户头像
   */
  @TableField("avatar")
  private String avatar;

  /**
   * 当前还能加几个QQ群
   */
  @TableField("can_add_group_num")
  private Integer canAddGroupNum;

  /**
   * 当前还能加几个QQ
   */
  @TableField("can_add_qq_num")
  private Integer canAddQqNum;

  /**
   * 用户登录后获取的用户地址
   */
  @TableField("location")
  private String location;

  /**
   * 用户登录昵称
   */
  @TableField("nickname")
  private String nickname;

  /**
   * 用户权限
   */
  @TableField("role")
  private Integer role;

  /**
   * 用户登录获取的token
   */
  @TableField("token")
  private String token;

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return Login.class;
  }

  @Override
  public final Login changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final Login changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("adder = id")
  public List<QqInfo> findQqInfoList() {
    return super.invoke("findQqInfoList", true);
  }

  /**
   * 实现定义在{@link cn.org.atool.fluent.mybatis.base.IRefs}子类Refs上
   */
  @RefMethod("adder = id")
  public List<GroupInfo> findGroupInfoList() {
    return super.invoke("findGroupInfoList", true);
  }
}
