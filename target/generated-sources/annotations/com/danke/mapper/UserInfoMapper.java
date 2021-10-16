package com.danke.mapper;

import static cn.org.atool.fluent.mybatis.mapper.FluentConst.*;

import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IEntityMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IRichMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import com.danke.entity.UserInfo;
import com.danke.helper.UserInfoDefaults;
import com.danke.helper.UserInfoMapping;
import com.danke.helper.UserInfoSqlProvider;
import com.danke.wrapper.UserInfoQuery;
import com.danke.wrapper.UserInfoUpdate;
import java.io.Serializable;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

/**
 *
 * UserInfoMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("userInfoMapper")
public interface UserInfoMapper extends IEntityMapper<UserInfo>, IRichMapper<UserInfo>, IWrapperMapper<UserInfo>, IMapper<UserInfo> {
  String ResultMap = "UserInfoResultMap";

  /**
   * {@link cn.org.atool.fluent.mybatis.base.crud.BaseSqlProvider#insert(cn.org.atool.fluent.mybatis.base.IEntity)}
   */
  @Override
  @InsertProvider(
      type = UserInfoSqlProvider.class,
      method = "insert"
  )
  @Options(
      useGeneratedKeys = true,
      keyProperty = "id",
      keyColumn = "id"
  )
  int insert(UserInfo entity);

  @Override
  @InsertProvider(
      type = UserInfoSqlProvider.class,
      method = "insertWithPk"
  )
  int insertWithPk(UserInfo entity);

  @Override
  @InsertProvider(
      type = UserInfoSqlProvider.class,
      method = "insertBatch"
  )
  @Options(
      useGeneratedKeys = true,
      keyProperty = "id",
      keyColumn = "id"
  )
  int insertBatch(@Param(Param_List) Collection<UserInfo> entities);

  @Override
  @InsertProvider(
      type = UserInfoSqlProvider.class,
      method = "insertBatchWithPk"
  )
  int insertBatchWithPk(@Param(Param_List) Collection<UserInfo> entities);

  /**
   * @see UserInfoSqlProvider#insertSelect(Map)
   */
  @Override
  @InsertProvider(
      type = UserInfoSqlProvider.class,
      method = "insertSelect"
  )
  int insertSelect(@Param(Param_Fields) String[] fields, @Param(Param_EW) IQuery ew);

  /**
   * @see UserInfoSqlProvider#deleteById(Serializable[])
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "deleteById"
  )
  int deleteById(@Param(Param_List) Serializable... ids);

  /**
   * @see UserInfoSqlProvider#logicDeleteById(Serializable[])
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "logicDeleteById"
  )
  int logicDeleteById(@Param(Param_List) Serializable... ids);

  /**
   * @see UserInfoSqlProvider#deleteByIds(Map)
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "deleteByIds"
  )
  int deleteByIds(@Param(Param_List) Collection<? extends Serializable> idList);

  /**
   * @see UserInfoSqlProvider#logicDeleteByIds(Map)
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "logicDeleteByIds"
  )
  int logicDeleteByIds(@Param(Param_List) Collection<? extends Serializable> idList);

  /**
   * @see UserInfoSqlProvider#deleteByMap(Map)
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "deleteByMap"
  )
  int deleteByMap(@Param(Param_CM) Map<String, Object> cm);

  /**
   * @see UserInfoSqlProvider#logicDeleteByMap(Map)
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "logicDeleteByMap"
  )
  int logicDeleteByMap(@Param(Param_CM) Map<String, Object> cm);

  /**
   * @see UserInfoSqlProvider#delete(Map)
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "delete"
  )
  int delete(@Param(Param_EW) IQuery wrapper);

  /**
   * @see UserInfoSqlProvider#logicDelete(Map)
   */
  @Override
  @DeleteProvider(
      type = UserInfoSqlProvider.class,
      method = "logicDelete"
  )
  int logicDelete(@Param(Param_EW) IQuery wrapper);

  @Override
  @UpdateProvider(
      type = UserInfoSqlProvider.class,
      method = "updateById"
  )
  int updateById(@Param(Param_ET) UserInfo entity);

  /**
   *  {@link UserInfoSqlProvider#updateBy(Map)}
   */
  @Override
  @UpdateProvider(
      type = UserInfoSqlProvider.class,
      method = "updateBy"
  )
  int updateBy(@Param(Param_EW) IUpdate... updates);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "findById"
  )
  @Results(
      id = ResultMap,
      value = {
          @Result(column = "id", property = "id", javaType = Long.class, id = true, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "gmt_create", property = "gmtCreate", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "gmt_modified", property = "gmtModified", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "nickname", property = "nickname", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "qq_number", property = "qqNumber", javaType = Long.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "role", property = "role", javaType = Integer.class, jdbcType = JdbcType.UNDEFINED)
          }
  )
  UserInfo findById(Serializable id);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "findOne"
  )
  @ResultMap(ResultMap)
  UserInfo findOne(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "listByIds"
  )
  @ResultMap(ResultMap)
  List<UserInfo> listByIds(@Param(Param_List) Collection ids);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "listByMap"
  )
  @ResultMap(ResultMap)
  List<UserInfo> listByMap(@Param(Param_CM) Map<String, Object> columnMap);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "listEntity"
  )
  @ResultMap(ResultMap)
  List<UserInfo> listEntity(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "listMaps"
  )
  @ResultType(Map.class)
  List<Map<String, Object>> listMaps(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "listObjs"
  )
  <O> List<O> listObjs(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "count"
  )
  Integer count(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = UserInfoSqlProvider.class,
      method = "countNoLimit"
  )
  Integer countNoLimit(@Param(Param_EW) IQuery query);

  default UserInfoQuery query() {
    return new UserInfoQuery();
  }

  default UserInfoUpdate updater() {
    return new UserInfoUpdate();
  }

  default UserInfoQuery defaultQuery() {
    return UserInfoDefaults.INSTANCE.defaultQuery();
  }

  default UserInfoUpdate defaultUpdater() {
    return UserInfoDefaults.INSTANCE.defaultUpdater();
  }

  @Override
  default FieldMapping primaryField() {
    return UserInfoMapping.id;
  }

  @Override
  default Class<UserInfo> entityClass() {
    return UserInfo.class;
  }
}
