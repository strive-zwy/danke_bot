package cn.org.atool.fluent.mybatis.refs;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.mapper.IRichMapper;
import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.TaskMapper;
import com.danke.mapper.UserInfoMapper;
import java.lang.Class;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * MapperRef: 应用所有Mapper Bean引用
 *
 * @author powered by FluentMybatis
 */
public class MapperRef {
  private static final Map<Class<? extends IEntity>, IRichMapper> allMappers = new HashMap<>();

  private static final Set<Class<? extends IEntity>> allEntityClass = new HashSet<>();

  private static MapperRef instance;

  public final GroupInfoMapper groupInfoMapper;

  public final TaskMapper taskMapper;

  public final UserInfoMapper userInfoMapper;

  private MapperRef(MapperFactory factory) {
    this.groupInfoMapper = factory.getBean(GroupInfoMapper.class);
    this.taskMapper = factory.getBean(TaskMapper.class);
    this.userInfoMapper = factory.getBean(UserInfoMapper.class);
    allMappers.put(GroupInfo.class, this.groupInfoMapper);
    allMappers.put(Task.class, this.taskMapper);
    allMappers.put(UserInfo.class, this.userInfoMapper);
    allEntityClass.addAll(allMappers.keySet());
  }

  public static final synchronized MapperRef instance(MapperFactory factory) {
    if (instance == null) {
      instance = new MapperRef(factory);
    }
    return instance;
  }

  public static final IRichMapper mapper(Class<? extends IEntity> entityClass) {
    return allMappers.get(entityClass);
  }

  public static final Set<Class<? extends IEntity>> allEntityClass() {
    return allEntityClass;
  }
}
