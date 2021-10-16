package cn.org.atool.fluent.mybatis.refs;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.notFluentMybatisException;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.helper.GroupInfoMapping;
import com.danke.helper.TaskMapping;
import com.danke.helper.UserInfoMapping;
import java.lang.Class;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * FieldRef: Entity所有Mapping引用
 *
 * @author powered by FluentMybatis
 */
public class FieldRef {
  public static final GroupInfoMapping GroupInfo = GroupInfoMapping.MAPPING;

  public static final TaskMapping Task = TaskMapping.MAPPING;

  public static final UserInfoMapping UserInfo = UserInfoMapping.MAPPING;

  private static final Map<Class, IMapping> mappings = new HashMap<>();

  static {
    mappings.put(GroupInfo.class, GroupInfoMapping.MAPPING);
    mappings.put(Task.class, TaskMapping.MAPPING);
    mappings.put(UserInfo.class, UserInfoMapping.MAPPING);
  }

  /**
   * 返回clazz属性field对应的数据库字段名称
   */
  public static final String findColumnByField(Class clazz, String field) {
    if (mappings.containsKey(clazz)) {
    	return mappings.get(clazz).findColumnByField(field);
    }
    throw notFluentMybatisException(clazz);
  }

  /**
   * 返回clazz属性对应数据库主键字段名称
   */
  public static final String findPrimaryColumn(Class clazz) {
    if (mappings.containsKey(clazz)) {
    	return mappings.get(clazz).findPrimaryColumn();
    }
    throw notFluentMybatisException(clazz);
  }
}
