package cn.org.atool.fluent.mybatis.refs;

import cn.org.atool.fluent.mybatis.base.IRefs;
import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.wrapper.GroupInfoQuery;
import com.danke.wrapper.TaskQuery;
import com.danke.wrapper.UserInfoQuery;
import java.util.List;

/**
 *
 * Refs: 
 *  o - 查询器，更新器工厂类单例引用
 *  o - 应用所有Mapper Bean引用
 *  o - Entity关联对象延迟加载查询实现
 *
 * @author powered by FluentMybatis
 */
public final class Refs extends AllRef {
  /**
   * Refs 单例
   */
  public static final Refs instance() {
    return (Refs) IRefs.instance();
  }

  /**
   * {@link GroupInfo#findTaskList}
   */
  public List<Task> findTaskListOfGroupInfo(GroupInfo entity) {
    return mapper().taskMapper.listEntity(new TaskQuery()
    	.where.creatorId().eq(entity.getId())
    	.end());
  }

  /**
   * {@link Task#findUserInfo}
   */
  public UserInfo findUserInfoOfTask(Task entity) {
    return mapper().userInfoMapper.findOne(new UserInfoQuery()
    	.where.id().eq(entity.getCreatorId())
    	.end());
  }

  /**
   * {@link Task#findGroupInfo}
   */
  public GroupInfo findGroupInfoOfTask(Task entity) {
    return mapper().groupInfoMapper.findOne(new GroupInfoQuery()
    	.where.id().eq(entity.getCreatorId())
    	.end());
  }

  /**
   * {@link UserInfo#findTaskList}
   */
  public List<Task> findTaskListOfUserInfo(UserInfo entity) {
    return mapper().taskMapper.listEntity(new TaskQuery()
    	.where.creatorId().eq(entity.getId())
    	.end());
  }
}
