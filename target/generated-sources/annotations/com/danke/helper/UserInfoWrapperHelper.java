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
import com.danke.wrapper.UserInfoQuery;
import com.danke.wrapper.UserInfoUpdate;
import java.lang.Override;
import java.lang.String;

/**
 *
 * UserInfoWrapperHelper
 *
 * @author powered by FluentMybatis
 */
public class UserInfoWrapperHelper {
  /**
   * 默认设置器
   */
  private static final UserInfoDefaults defaults = UserInfoDefaults.INSTANCE;

  public interface ISegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(UserInfoMapping.id);
    }

    default R gmtCreate() {
      return this.set(UserInfoMapping.gmtCreate);
    }

    default R gmtModified() {
      return this.set(UserInfoMapping.gmtModified);
    }

    default R nickname() {
      return this.set(UserInfoMapping.nickname);
    }

    default R qqNumber() {
      return this.set(UserInfoMapping.qqNumber);
    }

    default R role() {
      return this.set(UserInfoMapping.role);
    }
  }

  /**
   * select字段设置
   */
  public static final class Selector extends SelectorBase<Selector, UserInfoQuery> implements ISegment<Selector> {
    public Selector(UserInfoQuery query) {
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
      return this.process(UserInfoMapping.id, _alias_);
    }

    public Selector gmtCreate(String _alias_) {
      return this.process(UserInfoMapping.gmtCreate, _alias_);
    }

    public Selector gmtModified(String _alias_) {
      return this.process(UserInfoMapping.gmtModified, _alias_);
    }

    public Selector nickname(String _alias_) {
      return this.process(UserInfoMapping.nickname, _alias_);
    }

    public Selector qqNumber(String _alias_) {
      return this.process(UserInfoMapping.qqNumber, _alias_);
    }

    public Selector role(String _alias_) {
      return this.process(UserInfoMapping.role, _alias_);
    }
  }

  /**
   * query where条件设置
   */
  public static class QueryWhere extends WhereBase<QueryWhere, UserInfoQuery, UserInfoQuery> {
    public QueryWhere(UserInfoQuery query) {
      super(query);
    }

    private QueryWhere(UserInfoQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((UserInfoQuery) this.wrapper, and);
    }

    @Override
    public QueryWhere defaults() {
      defaults.setQueryDefault((UserInfoQuery) super.wrapper);
      return super.and;
    }

    public NumericWhere<QueryWhere, UserInfoQuery> id() {
      return this.set(UserInfoMapping.id);
    }

    public StringWhere<QueryWhere, UserInfoQuery> gmtCreate() {
      return this.set(UserInfoMapping.gmtCreate);
    }

    public StringWhere<QueryWhere, UserInfoQuery> gmtModified() {
      return this.set(UserInfoMapping.gmtModified);
    }

    public StringWhere<QueryWhere, UserInfoQuery> nickname() {
      return this.set(UserInfoMapping.nickname);
    }

    public NumericWhere<QueryWhere, UserInfoQuery> qqNumber() {
      return this.set(UserInfoMapping.qqNumber);
    }

    public NumericWhere<QueryWhere, UserInfoQuery> role() {
      return this.set(UserInfoMapping.role);
    }
  }

  /**
   * update where条件设置
   */
  public static class UpdateWhere extends WhereBase<UpdateWhere, UserInfoUpdate, UserInfoQuery> {
    public UpdateWhere(UserInfoUpdate updater) {
      super(updater);
    }

    private UpdateWhere(UserInfoUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((UserInfoUpdate) this.wrapper, and);
    }

    @Override
    public UpdateWhere defaults() {
      defaults.setUpdateDefault((UserInfoUpdate) super.wrapper);
      return super.and;
    }

    public NumericWhere<UpdateWhere, UserInfoQuery> id() {
      return this.set(UserInfoMapping.id);
    }

    public StringWhere<UpdateWhere, UserInfoQuery> gmtCreate() {
      return this.set(UserInfoMapping.gmtCreate);
    }

    public StringWhere<UpdateWhere, UserInfoQuery> gmtModified() {
      return this.set(UserInfoMapping.gmtModified);
    }

    public StringWhere<UpdateWhere, UserInfoQuery> nickname() {
      return this.set(UserInfoMapping.nickname);
    }

    public NumericWhere<UpdateWhere, UserInfoQuery> qqNumber() {
      return this.set(UserInfoMapping.qqNumber);
    }

    public NumericWhere<UpdateWhere, UserInfoQuery> role() {
      return this.set(UserInfoMapping.role);
    }
  }

  /**
   * 分组设置
   */
  public static final class GroupBy extends GroupByBase<GroupBy, UserInfoQuery> implements ISegment<GroupBy> {
    public GroupBy(UserInfoQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  public static final class Having extends HavingBase<Having, UserInfoQuery> implements ISegment<HavingOperator<Having>> {
    public Having(UserInfoQuery query) {
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
  public static final class QueryOrderBy extends OrderByBase<QueryOrderBy, UserInfoQuery> implements ISegment<OrderByApply<QueryOrderBy, UserInfoQuery>> {
    public QueryOrderBy(UserInfoQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  public static final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, UserInfoUpdate> implements ISegment<OrderByApply<UpdateOrderBy, UserInfoUpdate>> {
    public UpdateOrderBy(UserInfoUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  public static final class UpdateSetter extends UpdateBase<UpdateSetter, UserInfoUpdate> implements ISegment<UpdateApply<UpdateSetter, UserInfoUpdate>> {
    public UpdateSetter(UserInfoUpdate updater) {
      super(updater);
    }
  }
}
