package com.danke.dao.base;

import static com.danke.helper.UserInfoDefaults.INSTANCE;

import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import com.danke.entity.UserInfo;
import com.danke.mapper.UserInfoMapper;
import com.danke.wrapper.UserInfoQuery;
import com.danke.wrapper.UserInfoUpdate;
import java.lang.Override;
import javax.annotation.Resource;

/**
 *
 * UserInfoBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class UserInfoBaseDao extends BaseDao<UserInfo> {
  @Resource(
      name = "userInfoMapper"
  )
  protected UserInfoMapper mapper;

  @Override
  public UserInfoMapper mapper() {
    return mapper;
  }

  @Override
  protected UserInfoQuery query() {
    return new UserInfoQuery();
  }

  @Override
  protected UserInfoQuery defaultQuery() {
    return INSTANCE.defaultQuery();
  }

  @Override
  protected UserInfoUpdate updater() {
    return new UserInfoUpdate();
  }

  @Override
  protected UserInfoUpdate defaultUpdater() {
    return INSTANCE.defaultUpdater();
  }
}
