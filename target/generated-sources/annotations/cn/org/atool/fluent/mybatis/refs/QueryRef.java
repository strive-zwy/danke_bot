package cn.org.atool.fluent.mybatis.refs;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.notFluentMybatisException;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.helper.GroupInfoDefaults;
import com.danke.helper.TaskDefaults;
import com.danke.helper.UserInfoDefaults;
import java.lang.Class;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * QueryRef: 构造Entity对应的default query
 * 更新器工厂类单例引用
 *
 * @author powered by FluentMybatis
 */
public class QueryRef {
  public static final GroupInfoDefaults groupInfo = GroupInfoDefaults.INSTANCE;

  public static final TaskDefaults task = TaskDefaults.INSTANCE;

  public static final UserInfoDefaults userInfo = UserInfoDefaults.INSTANCE;

  private static final Map<Class, IDefaultGetter> Supplier = new HashMap<>();

  static {
    Supplier.put(GroupInfo.class, groupInfo);
    Supplier.put(Task.class, task);
    Supplier.put(UserInfo.class, userInfo);
  }

  /**
   * 返回clazz实体对应的默认Query实例
   */
  public static final IQuery defaultQuery(Class clazz) {
    	return findDefault(clazz).defaultQuery();
  }

  /**
   * 返回clazz实体对应的空Query实例
   */
  public static final IQuery emptyQuery(Class clazz) {
    	return findDefault(clazz).query();
  }

  /**
   * 返回clazz实体对应的默认Updater实例
   */
  public static final IUpdate defaultUpdater(Class clazz) {
    	return findDefault(clazz).defaultUpdater();
  }

  /**
   * 返回clazz实体对应的空Updater实例
   */
  public static final IUpdate emptyUpdater(Class clazz) {
    	return findDefault(clazz).updater();
  }

  public static final IDefaultGetter findDefault(Class clazz) {
    if (Supplier.containsKey(clazz)) {
    	return Supplier.get(clazz);
    }
    throw notFluentMybatisException(clazz);
  }
}
