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
import com.danke.wrapper.GroupInfoQuery;
import com.danke.wrapper.GroupInfoUpdate;
import java.lang.Override;
import java.lang.String;

/**
 *
 * GroupInfoWrapperHelper
 *
 * @author powered by FluentMybatis
 */
public class GroupInfoWrapperHelper {
  /**
   * 默认设置器
   */
  private static final GroupInfoDefaults defaults = GroupInfoDefaults.INSTANCE;

  public interface ISegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(GroupInfoMapping.id);
    }

    default R gmtCreate() {
      return this.set(GroupInfoMapping.gmtCreate);
    }

    default R gmtModified() {
      return this.set(GroupInfoMapping.gmtModified);
    }

    default R groupName() {
      return this.set(GroupInfoMapping.groupName);
    }

    default R groupNumber() {
      return this.set(GroupInfoMapping.groupNumber);
    }
  }

  /**
   * select字段设置
   */
  public static final class Selector extends SelectorBase<Selector, GroupInfoQuery> implements ISegment<Selector> {
    public Selector(GroupInfoQuery query) {
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
      return this.process(GroupInfoMapping.id, _alias_);
    }

    public Selector gmtCreate(String _alias_) {
      return this.process(GroupInfoMapping.gmtCreate, _alias_);
    }

    public Selector gmtModified(String _alias_) {
      return this.process(GroupInfoMapping.gmtModified, _alias_);
    }

    public Selector groupName(String _alias_) {
      return this.process(GroupInfoMapping.groupName, _alias_);
    }

    public Selector groupNumber(String _alias_) {
      return this.process(GroupInfoMapping.groupNumber, _alias_);
    }
  }

  /**
   * query where条件设置
   */
  public static class QueryWhere extends WhereBase<QueryWhere, GroupInfoQuery, GroupInfoQuery> {
    public QueryWhere(GroupInfoQuery query) {
      super(query);
    }

    private QueryWhere(GroupInfoQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((GroupInfoQuery) this.wrapper, and);
    }

    @Override
    public QueryWhere defaults() {
      defaults.setQueryDefault((GroupInfoQuery) super.wrapper);
      return super.and;
    }

    public NumericWhere<QueryWhere, GroupInfoQuery> id() {
      return this.set(GroupInfoMapping.id);
    }

    public StringWhere<QueryWhere, GroupInfoQuery> gmtCreate() {
      return this.set(GroupInfoMapping.gmtCreate);
    }

    public StringWhere<QueryWhere, GroupInfoQuery> gmtModified() {
      return this.set(GroupInfoMapping.gmtModified);
    }

    public StringWhere<QueryWhere, GroupInfoQuery> groupName() {
      return this.set(GroupInfoMapping.groupName);
    }

    public NumericWhere<QueryWhere, GroupInfoQuery> groupNumber() {
      return this.set(GroupInfoMapping.groupNumber);
    }
  }

  /**
   * update where条件设置
   */
  public static class UpdateWhere extends WhereBase<UpdateWhere, GroupInfoUpdate, GroupInfoQuery> {
    public UpdateWhere(GroupInfoUpdate updater) {
      super(updater);
    }

    private UpdateWhere(GroupInfoUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((GroupInfoUpdate) this.wrapper, and);
    }

    @Override
    public UpdateWhere defaults() {
      defaults.setUpdateDefault((GroupInfoUpdate) super.wrapper);
      return super.and;
    }

    public NumericWhere<UpdateWhere, GroupInfoQuery> id() {
      return this.set(GroupInfoMapping.id);
    }

    public StringWhere<UpdateWhere, GroupInfoQuery> gmtCreate() {
      return this.set(GroupInfoMapping.gmtCreate);
    }

    public StringWhere<UpdateWhere, GroupInfoQuery> gmtModified() {
      return this.set(GroupInfoMapping.gmtModified);
    }

    public StringWhere<UpdateWhere, GroupInfoQuery> groupName() {
      return this.set(GroupInfoMapping.groupName);
    }

    public NumericWhere<UpdateWhere, GroupInfoQuery> groupNumber() {
      return this.set(GroupInfoMapping.groupNumber);
    }
  }

  /**
   * 分组设置
   */
  public static final class GroupBy extends GroupByBase<GroupBy, GroupInfoQuery> implements ISegment<GroupBy> {
    public GroupBy(GroupInfoQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  public static final class Having extends HavingBase<Having, GroupInfoQuery> implements ISegment<HavingOperator<Having>> {
    public Having(GroupInfoQuery query) {
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
  public static final class QueryOrderBy extends OrderByBase<QueryOrderBy, GroupInfoQuery> implements ISegment<OrderByApply<QueryOrderBy, GroupInfoQuery>> {
    public QueryOrderBy(GroupInfoQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  public static final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, GroupInfoUpdate> implements ISegment<OrderByApply<UpdateOrderBy, GroupInfoUpdate>> {
    public UpdateOrderBy(GroupInfoUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  public static final class UpdateSetter extends UpdateBase<UpdateSetter, GroupInfoUpdate> implements ISegment<UpdateApply<UpdateSetter, GroupInfoUpdate>> {
    public UpdateSetter(GroupInfoUpdate updater) {
      super(updater);
    }
  }
}
