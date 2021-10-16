package com.danke.dao.base;

import static com.danke.helper.GroupInfoDefaults.INSTANCE;

import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import com.danke.entity.GroupInfo;
import com.danke.mapper.GroupInfoMapper;
import com.danke.wrapper.GroupInfoQuery;
import com.danke.wrapper.GroupInfoUpdate;
import java.lang.Override;
import javax.annotation.Resource;

/**
 *
 * GroupInfoBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class GroupInfoBaseDao extends BaseDao<GroupInfo> {
  @Resource(
      name = "groupInfoMapper"
  )
  protected GroupInfoMapper mapper;

  @Override
  public GroupInfoMapper mapper() {
    return mapper;
  }

  @Override
  protected GroupInfoQuery query() {
    return new GroupInfoQuery();
  }

  @Override
  protected GroupInfoQuery defaultQuery() {
    return INSTANCE.defaultQuery();
  }

  @Override
  protected GroupInfoUpdate updater() {
    return new GroupInfoUpdate();
  }

  @Override
  protected GroupInfoUpdate defaultUpdater() {
    return INSTANCE.defaultUpdater();
  }
}
