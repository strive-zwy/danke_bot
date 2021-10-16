package com.danke.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;

import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.functions.IAggregate;
import cn.org.atool.fluent.mybatis.segment.GroupByBase;
import cn.org.atool.fluent.mybatis.segment.HavingBase;
import cn.org.atool.fluent.mybatis.segment.HavingOperator;
import cn.org.atool.fluent.mybatis.segment.OrderByApply;
import cn.org.atool.fluent.mybatis.segment.OrderByBase;
import cn.org.atool.fluent.mybatis.segment.SelectorBase;
import cn.org.atool.fluent.mybatis.segment.UpdateApply;
import cn.org.atool.fluent.mybatis.segment.UpdateBase;
import cn.org.atool.fluent.mybatis.segment.WhereBase;
import cn.org.atool.fluent.mybatis.segment.where.NumericWhere;
import cn.org.atool.fluent.mybatis.segment.where.StringWhere;
import com.danke.wrapper.TaskQuery;
import com.danke.wrapper.TaskUpdate;
import java.lang.Override;
import java.lang.String;

/**
 *
 * TaskWrapperHelper
 *
 * @author powered by FluentMybatis
 */
public class TaskWrapperHelper {
  /**
   * 默认设置器
   */
  private static final TaskDefaults defaults = TaskDefaults.INSTANCE;

  public interface ISegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(TaskMapping.id);
    }

    default R gmtCreate() {
      return this.set(TaskMapping.gmtCreate);
    }

    default R gmtModified() {
      return this.set(TaskMapping.gmtModified);
    }

    default R creatorId() {
      return this.set(TaskMapping.creatorId);
    }

    default R pOrG() {
      return this.set(TaskMapping.pOrG);
    }

    default R remindDate() {
      return this.set(TaskMapping.remindDate);
    }

    default R remindStr() {
      return this.set(TaskMapping.remindStr);
    }

    default R remindTime() {
      return this.set(TaskMapping.remindTime);
    }

    default R state() {
      return this.set(TaskMapping.state);
    }

    default R type() {
      return this.set(TaskMapping.type);
    }
  }

  /**
   * select字段设置
   */
  public static final class Selector extends SelectorBase<Selector, TaskQuery> implements ISegment<Selector> {
    public Selector(TaskQuery query) {
      super(query);
    }

    protected Selector(Selector selector, IAggregate aggregate) {
      super(selector, aggregate);
    }

    @Override
    protected Selector aggregateSegment(IAggregate aggregate) {
      return new Selector(this, aggregate);
    }

    public Selector id(String _alias_) {
      return this.process(TaskMapping.id, _alias_);
    }

    public Selector gmtCreate(String _alias_) {
      return this.process(TaskMapping.gmtCreate, _alias_);
    }

    public Selector gmtModified(String _alias_) {
      return this.process(TaskMapping.gmtModified, _alias_);
    }

    public Selector creatorId(String _alias_) {
      return this.process(TaskMapping.creatorId, _alias_);
    }

    public Selector pOrG(String _alias_) {
      return this.process(TaskMapping.pOrG, _alias_);
    }

    public Selector remindDate(String _alias_) {
      return this.process(TaskMapping.remindDate, _alias_);
    }

    public Selector remindStr(String _alias_) {
      return this.process(TaskMapping.remindStr, _alias_);
    }

    public Selector remindTime(String _alias_) {
      return this.process(TaskMapping.remindTime, _alias_);
    }

    public Selector state(String _alias_) {
      return this.process(TaskMapping.state, _alias_);
    }

    public Selector type(String _alias_) {
      return this.process(TaskMapping.type, _alias_);
    }
  }

  /**
   * query where条件设置
   */
  public static class QueryWhere extends WhereBase<QueryWhere, TaskQuery, TaskQuery> {
    public QueryWhere(TaskQuery query) {
      super(query);
    }

    private QueryWhere(TaskQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((TaskQuery) this.wrapper, and);
    }

    @Override
    public QueryWhere defaults() {
      defaults.setQueryDefault((TaskQuery) super.wrapper);
      return super.and;
    }

    public NumericWhere<QueryWhere, TaskQuery> id() {
      return this.set(TaskMapping.id);
    }

    public StringWhere<QueryWhere, TaskQuery> gmtCreate() {
      return this.set(TaskMapping.gmtCreate);
    }

    public StringWhere<QueryWhere, TaskQuery> gmtModified() {
      return this.set(TaskMapping.gmtModified);
    }

    public NumericWhere<QueryWhere, TaskQuery> creatorId() {
      return this.set(TaskMapping.creatorId);
    }

    public NumericWhere<QueryWhere, TaskQuery> pOrG() {
      return this.set(TaskMapping.pOrG);
    }

    public StringWhere<QueryWhere, TaskQuery> remindDate() {
      return this.set(TaskMapping.remindDate);
    }

    public StringWhere<QueryWhere, TaskQuery> remindStr() {
      return this.set(TaskMapping.remindStr);
    }

    public StringWhere<QueryWhere, TaskQuery> remindTime() {
      return this.set(TaskMapping.remindTime);
    }

    public NumericWhere<QueryWhere, TaskQuery> state() {
      return this.set(TaskMapping.state);
    }

    public NumericWhere<QueryWhere, TaskQuery> type() {
      return this.set(TaskMapping.type);
    }
  }

  /**
   * update where条件设置
   */
  public static class UpdateWhere extends WhereBase<UpdateWhere, TaskUpdate, TaskQuery> {
    public UpdateWhere(TaskUpdate updater) {
      super(updater);
    }

    private UpdateWhere(TaskUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((TaskUpdate) this.wrapper, and);
    }

    @Override
    public UpdateWhere defaults() {
      defaults.setUpdateDefault((TaskUpdate) super.wrapper);
      return super.and;
    }

    public NumericWhere<UpdateWhere, TaskQuery> id() {
      return this.set(TaskMapping.id);
    }

    public StringWhere<UpdateWhere, TaskQuery> gmtCreate() {
      return this.set(TaskMapping.gmtCreate);
    }

    public StringWhere<UpdateWhere, TaskQuery> gmtModified() {
      return this.set(TaskMapping.gmtModified);
    }

    public NumericWhere<UpdateWhere, TaskQuery> creatorId() {
      return this.set(TaskMapping.creatorId);
    }

    public NumericWhere<UpdateWhere, TaskQuery> pOrG() {
      return this.set(TaskMapping.pOrG);
    }

    public StringWhere<UpdateWhere, TaskQuery> remindDate() {
      return this.set(TaskMapping.remindDate);
    }

    public StringWhere<UpdateWhere, TaskQuery> remindStr() {
      return this.set(TaskMapping.remindStr);
    }

    public StringWhere<UpdateWhere, TaskQuery> remindTime() {
      return this.set(TaskMapping.remindTime);
    }

    public NumericWhere<UpdateWhere, TaskQuery> state() {
      return this.set(TaskMapping.state);
    }

    public NumericWhere<UpdateWhere, TaskQuery> type() {
      return this.set(TaskMapping.type);
    }
  }

  /**
   * 分组设置
   */
  public static final class GroupBy extends GroupByBase<GroupBy, TaskQuery> implements ISegment<GroupBy> {
    public GroupBy(TaskQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  public static final class Having extends HavingBase<Having, TaskQuery> implements ISegment<HavingOperator<Having>> {
    public Having(TaskQuery query) {
      super(query);
    }

    protected Having(Having having, IAggregate aggregate) {
      super(having, aggregate);
    }

    @Override
    protected Having aggregateSegment(IAggregate aggregate) {
      return new Having(this, aggregate);
    }
  }

  /**
   * Query OrderBy设置
   */
  public static final class QueryOrderBy extends OrderByBase<QueryOrderBy, TaskQuery> implements ISegment<OrderByApply<QueryOrderBy, TaskQuery>> {
    public QueryOrderBy(TaskQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  public static final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, TaskUpdate> implements ISegment<OrderByApply<UpdateOrderBy, TaskUpdate>> {
    public UpdateOrderBy(TaskUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  public static final class UpdateSetter extends UpdateBase<UpdateSetter, TaskUpdate> implements ISegment<UpdateApply<UpdateSetter, TaskUpdate>> {
    public UpdateSetter(TaskUpdate updater) {
      super(updater);
    }
  }
}
