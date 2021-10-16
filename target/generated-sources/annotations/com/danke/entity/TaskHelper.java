package com.danke.entity;

import static com.danke.helper.TaskMapping.*;

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
 * TaskHelper: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class TaskHelper implements IEntityHelper {
  @Override
  public Map<String, Object> toColumnMap(IEntity entity, boolean isNoN) {
    return this.toMap((Task)entity, false, isNoN);
  }

  @Override
  public Map<String, Object> toEntityMap(IEntity entity, boolean isNoN) {
    return this.toMap((Task)entity, true, isNoN);
  }

  public Map<String, Object> toMap(Task entity, boolean isProperty, boolean isNoN) {
    return new EntityToMap(isProperty)
    	.put(id, entity.getId(), isNoN)
    	.put(gmtCreate, entity.getGmtCreate(), isNoN)
    	.put(gmtModified, entity.getGmtModified(), isNoN)
    	.put(creatorId, entity.getCreatorId(), isNoN)
    	.put(pOrG, entity.getPOrG(), isNoN)
    	.put(remindDate, entity.getRemindDate(), isNoN)
    	.put(remindStr, entity.getRemindStr(), isNoN)
    	.put(remindTime, entity.getRemindTime(), isNoN)
    	.put(state, entity.getState(), isNoN)
    	.put(type, entity.getType(), isNoN)
    	.getMap();
  }

  @Override
  public <E extends IEntity> E toEntity(Map<String, Object> map) {
    Task entity = new Task();
    if (map.containsKey(id.name)) {
    	entity.setId((Long) map.get(id.name));
    }
    if (map.containsKey(gmtCreate.name)) {
    	entity.setGmtCreate((String) map.get(gmtCreate.name));
    }
    if (map.containsKey(gmtModified.name)) {
    	entity.setGmtModified((String) map.get(gmtModified.name));
    }
    if (map.containsKey(creatorId.name)) {
    	entity.setCreatorId((Long) map.get(creatorId.name));
    }
    if (map.containsKey(pOrG.name)) {
    	entity.setPOrG((Integer) map.get(pOrG.name));
    }
    if (map.containsKey(remindDate.name)) {
    	entity.setRemindDate((String) map.get(remindDate.name));
    }
    if (map.containsKey(remindStr.name)) {
    	entity.setRemindStr((String) map.get(remindStr.name));
    }
    if (map.containsKey(remindTime.name)) {
    	entity.setRemindTime((String) map.get(remindTime.name));
    }
    if (map.containsKey(state.name)) {
    	entity.setState((Integer) map.get(state.name));
    }
    if (map.containsKey(type.name)) {
    	entity.setType((Integer) map.get(type.name));
    }
    return (E)entity;
  }

  @Override
  public Task copy(IEntity iEntity) {
    Task entity = (Task) iEntity;
    Task copy = new Task();
    {
    	copy.setId(entity.getId());
    	copy.setGmtCreate(entity.getGmtCreate());
    	copy.setGmtModified(entity.getGmtModified());
    	copy.setCreatorId(entity.getCreatorId());
    	copy.setPOrG(entity.getPOrG());
    	copy.setRemindDate(entity.getRemindDate());
    	copy.setRemindStr(entity.getRemindStr());
    	copy.setRemindTime(entity.getRemindTime());
    	copy.setState(entity.getState());
    	copy.setType(entity.getType());
    }
    return copy;
  }
}
