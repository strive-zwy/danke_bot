package com.danke.entity;

import static com.danke.helper.UserInfoMapping.*;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.entity.IEntityHelper;
import cn.org.atool.fluent.mybatis.base.model.EntityToMap;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;

/**
 *
 * UserInfoHelper: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class UserInfoHelper implements IEntityHelper {
  @Override
  public Map<String, Object> toColumnMap(IEntity entity, boolean isNoN) {
    return this.toMap((UserInfo)entity, false, isNoN);
  }

  @Override
  public Map<String, Object> toEntityMap(IEntity entity, boolean isNoN) {
    return this.toMap((UserInfo)entity, true, isNoN);
  }

  public Map<String, Object> toMap(UserInfo entity, boolean isProperty, boolean isNoN) {
    return new EntityToMap(isProperty)
    	.put(id, entity.getId(), isNoN)
    	.put(gmtCreate, entity.getGmtCreate(), isNoN)
    	.put(gmtModified, entity.getGmtModified(), isNoN)
    	.put(nickname, entity.getNickname(), isNoN)
    	.put(qqNumber, entity.getQqNumber(), isNoN)
    	.put(role, entity.getRole(), isNoN)
    	.getMap();
  }

  @Override
  public <E extends IEntity> E toEntity(Map<String, Object> map) {
    UserInfo entity = new UserInfo();
    if (map.containsKey(id.name)) {
    	entity.setId((Long) map.get(id.name));
    }
    if (map.containsKey(gmtCreate.name)) {
    	entity.setGmtCreate((String) map.get(gmtCreate.name));
    }
    if (map.containsKey(gmtModified.name)) {
    	entity.setGmtModified((String) map.get(gmtModified.name));
    }
    if (map.containsKey(nickname.name)) {
    	entity.setNickname((String) map.get(nickname.name));
    }
    if (map.containsKey(qqNumber.name)) {
    	entity.setQqNumber((Long) map.get(qqNumber.name));
    }
    if (map.containsKey(role.name)) {
    	entity.setRole((Integer) map.get(role.name));
    }
    return (E)entity;
  }

  @Override
  public UserInfo copy(IEntity iEntity) {
    UserInfo entity = (UserInfo) iEntity;
    UserInfo copy = new UserInfo();
    {
    	copy.setId(entity.getId());
    	copy.setGmtCreate(entity.getGmtCreate());
    	copy.setGmtModified(entity.getGmtModified());
    	copy.setNickname(entity.getNickname());
    	copy.setQqNumber(entity.getQqNumber());
    	copy.setRole(entity.getRole());
    }
    return copy;
  }
}
