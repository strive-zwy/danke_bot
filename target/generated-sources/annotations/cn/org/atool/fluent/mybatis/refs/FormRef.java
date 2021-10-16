package cn.org.atool.fluent.mybatis.refs;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.notFluentMybatisException;

import cn.org.atool.fluent.mybatis.functions.FormFunction;
import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.helper.GroupInfoFormSetter;
import com.danke.helper.TaskFormSetter;
import com.danke.helper.UserInfoFormSetter;

/**
 *
 * FormRef: 所有Entity Form Setter引用
 *
 * @author powered by FluentMybatis
 */
public interface FormRef {
  FormFunction<GroupInfo, GroupInfoFormSetter> groupInfo = (obj, form) -> GroupInfoFormSetter.by(obj, form);

  FormFunction<Task, TaskFormSetter> task = (obj, form) -> TaskFormSetter.by(obj, form);

  FormFunction<UserInfo, UserInfoFormSetter> userInfo = (obj, form) -> UserInfoFormSetter.by(obj, form);
}
