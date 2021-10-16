package com.danke.entity;

import static com.danke.helper.GroupInfoMapping.*;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.entity.IEntityHelper;
import cn.org.atool.fluent.mybatis.base.model.EntityToMap;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;

/**
 *
 * GroupInfoHelper: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class GroupInfoHelper implements IEntityHelper {
  @Override
  public Map<String, Object> toColumnMap(IEntity entity, boolean isNoN) {
    return this.toMap((GroupInfo)entity, false, isNoN);
  }

  @Override
  public Map<String, Object> toEntityMap(IEntity entity, boolean isNoN) {
    return this.toMap((GroupInfo)entity, true, isNoN);
  }

  public Map<String, Object> toMap(GroupInfo entity, boolean isProperty, boolean isNoN) {
    return new EntityToMap(isProperty)
    	.put(id, entity.getId(), isNoN)
    	.put(gmtCreate, entity.getGmtCreate(), isNoN)
    	.put(gmtModified, entity.getGmtModified(), isNoN)
    	.put(groupName, entity.getGroupName(), isNoN)
    	.put(groupNumber, entity.getGroupNumber(), isNoN)
    	.getMap();
  }

  @Override
  public <E extends IEntity> E toEntity(Map<String, Object> map) {
    GroupInfo entity = new GroupInfo();
    if (map.containsKey(id.name)) {
    	entity.setId((Long) map.get(id.name));
    }
    if (map.containsKey(gmtCreate.name)) {
    	entity.setGmtCreate((String) map.get(gmtCreate.name));
    }
    if (map.containsKey(gmtModified.name)) {
    	entity.setGmtModified((String) map.get(gmtModified.name));
    }
    if (map.containsKey(groupName.name)) {
    	entity.setGroupName((String) map.get(groupName.name));
    }
    if (map.containsKey(groupNumber.name)) {
    	entity.setGroupNumber((Long) map.get(groupNumber.name));
    }
    return (E)entity;
  }

  @Override
  public GroupInfo copy(IEntity iEntity) {
    GroupInfo entity = (GroupInfo) iEntity;
    GroupInfo copy = new GroupInfo();
    {
    	copy.setId(entity.getId());
    	copy.setGmtCreate(entity.getGmtCreate());
    	copy.setGmtModified(entity.getGmtModified());
    	copy.setGroupName(entity.getGroupName());
    	copy.setGroupNumber(entity.getGroupNumber());
    }
    return copy;
  }
}
