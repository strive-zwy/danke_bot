package com.danke.mapper;

import static cn.org.atool.fluent.mybatis.mapper.FluentConst.*;

import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IEntityMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IRichMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import com.danke.entity.Task;
import com.danke.helper.TaskDefaults;
import com.danke.helper.TaskMapping;
import com.danke.helper.TaskSqlProvider;
import com.danke.wrapper.TaskQuery;
import com.danke.wrapper.TaskUpdate;
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
 * TaskMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("taskMapper")
public interface TaskMapper extends IEntityMapper<Task>, IRichMapper<Task>, IWrapperMapper<Task>, IMapper<Task> {
  String ResultMap = "TaskResultMap";

  /**
   * {@link cn.org.atool.fluent.mybatis.base.crud.BaseSqlProvider#insert(cn.org.atool.fluent.mybatis.base.IEntity)}
   */
  @Override
  @InsertProvider(
      type = TaskSqlProvider.class,
      method = "insert"
  )
  @Options(
      useGeneratedKeys = true,
      keyProperty = "id",
      keyColumn = "id"
  )
  int insert(Task entity);

  @Override
  @InsertProvider(
      type = TaskSqlProvider.class,
      method = "insertWithPk"
  )
  int insertWithPk(Task entity);

  @Override
  @InsertProvider(
      type = TaskSqlProvider.class,
      method = "insertBatch"
  )
  @Options(
      useGeneratedKeys = true,
      keyProperty = "id",
      keyColumn = "id"
  )
  int insertBatch(@Param(Param_List) Collection<Task> entities);

  @Override
  @InsertProvider(
      type = TaskSqlProvider.class,
      method = "insertBatchWithPk"
  )
  int insertBatchWithPk(@Param(Param_List) Collection<Task> entities);

  /**
   * @see TaskSqlProvider#insertSelect(Map)
   */
  @Override
  @InsertProvider(
      type = TaskSqlProvider.class,
      method = "insertSelect"
  )
  int insertSelect(@Param(Param_Fields) String[] fields, @Param(Param_EW) IQuery ew);

  /**
   * @see TaskSqlProvider#deleteById(Serializable[])
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "deleteById"
  )
  int deleteById(@Param(Param_List) Serializable... ids);

  /**
   * @see TaskSqlProvider#logicDeleteById(Serializable[])
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "logicDeleteById"
  )
  int logicDeleteById(@Param(Param_List) Serializable... ids);

  /**
   * @see TaskSqlProvider#deleteByIds(Map)
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "deleteByIds"
  )
  int deleteByIds(@Param(Param_List) Collection<? extends Serializable> idList);

  /**
   * @see TaskSqlProvider#logicDeleteByIds(Map)
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "logicDeleteByIds"
  )
  int logicDeleteByIds(@Param(Param_List) Collection<? extends Serializable> idList);

  /**
   * @see TaskSqlProvider#deleteByMap(Map)
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "deleteByMap"
  )
  int deleteByMap(@Param(Param_CM) Map<String, Object> cm);

  /**
   * @see TaskSqlProvider#logicDeleteByMap(Map)
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "logicDeleteByMap"
  )
  int logicDeleteByMap(@Param(Param_CM) Map<String, Object> cm);

  /**
   * @see TaskSqlProvider#delete(Map)
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "delete"
  )
  int delete(@Param(Param_EW) IQuery wrapper);

  /**
   * @see TaskSqlProvider#logicDelete(Map)
   */
  @Override
  @DeleteProvider(
      type = TaskSqlProvider.class,
      method = "logicDelete"
  )
  int logicDelete(@Param(Param_EW) IQuery wrapper);

  @Override
  @UpdateProvider(
      type = TaskSqlProvider.class,
      method = "updateById"
  )
  int updateById(@Param(Param_ET) Task entity);

  /**
   *  {@link TaskSqlProvider#updateBy(Map)}
   */
  @Override
  @UpdateProvider(
      type = TaskSqlProvider.class,
      method = "updateBy"
  )
  int updateBy(@Param(Param_EW) IUpdate... updates);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "findById"
  )
  @Results(
      id = ResultMap,
      value = {
          @Result(column = "id", property = "id", javaType = Long.class, id = true, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "gmt_create", property = "gmtCreate", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "gmt_modified", property = "gmtModified", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "creator_id", property = "creatorId", javaType = Long.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "p_or_g", property = "pOrG", javaType = Integer.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "remind_date", property = "remindDate", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "remind_str", property = "remindStr", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "remind_time", property = "remindTime", javaType = String.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "state", property = "state", javaType = Integer.class, jdbcType = JdbcType.UNDEFINED),
          @Result(column = "type", property = "type", javaType = Integer.class, jdbcType = JdbcType.UNDEFINED)
          }
  )
  Task findById(Serializable id);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "findOne"
  )
  @ResultMap(ResultMap)
  Task findOne(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "listByIds"
  )
  @ResultMap(ResultMap)
  List<Task> listByIds(@Param(Param_List) Collection ids);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "listByMap"
  )
  @ResultMap(ResultMap)
  List<Task> listByMap(@Param(Param_CM) Map<String, Object> columnMap);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "listEntity"
  )
  @ResultMap(ResultMap)
  List<Task> listEntity(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "listMaps"
  )
  @ResultType(Map.class)
  List<Map<String, Object>> listMaps(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "listObjs"
  )
  <O> List<O> listObjs(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "count"
  )
  Integer count(@Param(Param_EW) IQuery query);

  @Override
  @SelectProvider(
      type = TaskSqlProvider.class,
      method = "countNoLimit"
  )
  Integer countNoLimit(@Param(Param_EW) IQuery query);

  default TaskQuery query() {
    return new TaskQuery();
  }

  default TaskUpdate updater() {
    return new TaskUpdate();
  }

  default TaskQuery defaultQuery() {
    return TaskDefaults.INSTANCE.defaultQuery();
  }

  default TaskUpdate defaultUpdater() {
    return TaskDefaults.INSTANCE.defaultUpdater();
  }

  @Override
  default FieldMapping primaryField() {
    return TaskMapping.id;
  }

  @Override
  default Class<Task> entityClass() {
    return Task.class;
  }
}
