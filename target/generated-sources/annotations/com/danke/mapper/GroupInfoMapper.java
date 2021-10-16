package com.danke.mapper;

import static cn.org.atool.fluent.mybatis.mapper.FluentConst.*;

import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IEntityMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IRichMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import com.danke.entity.GroupInfo;
import com.danke.helper.GroupInfoDefaults;
import com.danke.helper.GroupInfoMapping;
import com.danke.helper.GroupInfoSqlProvider;
import com.danke.wrapper.GroupInfoQuery;
import com.danke.wrapper.GroupInfoUpdate;
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
 * GroupInfoMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("groupInfoMapper")
public interface GroupInfoMapper extends IEntityMapper<GroupInfo>, IRichMapper<GroupInfo>, IWrapperMapper<GroupInfo>, IMapper<GroupInfo> {
  String ResultMap = "GroupInfoResultMap";

  /**
   * {@link cn.org.atool.fluent.mybatis.base.crud.BaseSqlProvider#insert(cn.org.atool.fluent.mybatis.base.IEntity)}
   */
  @Override
  @InsertProvider(
      type = GroupInfoSqlProvider.class,
      method = "insert"
  )
  @Options(
      useGeneratedKeys = true,
      keyProperty = "id",
      keyColumn = "id"
  )
  int insert(GroupInfo entity);

  @Override
  @InsertProvider(
      type = GroupInfoSqlProvider.class,
      method = "insertWithPk"
  )
  int insertWithPk(GroupInfo entity);

  @Override
  @InsertProvider(
      type = GroupInfoSqlProvider.class,
      method = "insertBatch"
  )
  @Options(
      useGeneratedKeys = true,
      keyProperty = "id",
      keyColumn = "id"
  )
  int insertBatch(@Param(Param_List) Collection<GroupInfo> entities);

  @Override
  @InsertProvider(
      type = GroupInfoSqlProvider.class,
      method = "insertBatchWithPk"
  )
  int insertBatchWithPk(@Param(Param_List) Collection<GroupInfo> entities);

  /**
   * @see GroupInfoSqlProvider#insertSelect(Map)
   */
  @Override
  @InsertProvider(
      type = GroupInfoSqlProvider.class,
      method = "insertSelect"
  )
  int insertSelect(@Param(Param_Fields) String[] fields, @Param(Param_EW) IQuery ew);

  /**
   * @see GroupInfoSqlProvider#deleteById(Serializable[])
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "deleteById"
  )
  int deleteById(@Param(Param_List) Serializable... ids);

  /**
   * @see GroupInfoSqlProvider#logicDeleteById(Serializable[])
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "logicDeleteById"
  )
  int logicDeleteById(@Param(Param_List) Serializable... ids);

  /**
   * @see GroupInfoSqlProvider#deleteByIds(Map)
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "deleteByIds"
  )
  int deleteByIds(@Param(Param_List) Collection<? extends Serializable> idList);

  /**
   * @see GroupInfoSqlProvider#logicDeleteByIds(Map)
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "logicDeleteByIds"
  )
  int logicDeleteByIds(@Param(Param_List) Collection<? extends Serializable> idList);

  /**
   * @see GroupInfoSqlProvider#deleteByMap(Map)
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "deleteByMap"
  )
  int deleteByMap(@Param(Param_CM) Map<String, Object> cm);

  /**
   * @see GroupInfoSqlProvider#logicDeleteByMap(Map)
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "logicDeleteByMap"
  )
  int logicDeleteByMap(@Param(Param_CM) Map<String, Object> cm);

  /**
   * @see GroupInfoSqlProvider#delete(Map)
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "delete"
  )
  int delete(@Param(Param_EW) IQuery wrapper);

  /**
   * @see GroupInfoSqlProvider#logicDelete(Map)
   */
  @Override
  @DeleteProvider(
      type = GroupInfoSqlProvider.class,
      method = "logicDelete"
  )
  int logicDelete(@Param(Param_EW) IQuery wrapper);

  @Override
  @UpdateProvider(
      type = GroupInfoSqlProvider.class,
      method = "updateById"
  )
  int updateById(@Param(Param_ET) GroupInfo entity);

  /**
   *  {@link GroupInfoSqlProvider#updateBy(Map)}
   */
  @Override
  @UpdateProvider(
      type = GroupInfoSqlProvider.class,
      method = "updateBy"
  )
  int updateBy(@Param(Param_EW) IUpdate... updates);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "findById"
  )
  @Results(
      id = ResultMap,
      value = {
          @Result(column = "id", property = "id", javaType = Long.class, id = true, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "gmt_create", property = "gmtCreate", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "gmt_modified", property = "gmtModified", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "group_name", property = "groupName", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "group_number", property = "groupNumber", javaType = Long.class, jdbcType = JdbcType.UNDEFINED)
          }
  )
  GroupInfo findById(Serializable id);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "findOne"
  )
  @ResultMap(ResultMap)
  GroupInfo findOne(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "listByIds"
  )
  @ResultMap(ResultMap)
  List<GroupInfo> listByIds(@Param(Param_List) Collection ids);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "listByMap"
  )
  @ResultMap(ResultMap)
  List<GroupInfo> listByMap(@Param(Param_CM) Map<String, Object> columnMap);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "listEntity"
  )
  @ResultMap(ResultMap)
  List<GroupInfo> listEntity(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "listMaps"
  )
  @ResultType(Map.class)
  List<Map<String, Object>> listMaps(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "listObjs"
  )
  <O> List<O> listObjs(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "count"
  )
  Integer count(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = GroupInfoSqlProvider.class,
      method = "countNoLimit"
  )
  Integer countNoLimit(@Param(Param_EW) IQuery query);

  default GroupInfoQuery query() {
    return new GroupInfoQuery();
  }

  default GroupInfoUpdate updater() {
    return new GroupInfoUpdate();
  }

  default GroupInfoQuery defaultQuery() {
    return GroupInfoDefaults.INSTANCE.defaultQuery();
  }

  default GroupInfoUpdate defaultUpdater() {
    return GroupInfoDefaults.INSTANCE.defaultUpdater();
  }

  @Override
  default FieldMapping primaryField() {
    return GroupInfoMapping.id;
  }

  @Override
  default Class<GroupInfo> entityClass() {
    return GroupInfo.class;
  }
}
