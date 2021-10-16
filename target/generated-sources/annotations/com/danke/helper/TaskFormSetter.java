package com.danke.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;

import cn.org.atool.fluent.mybatis.base.crud.FormSetter;
import cn.org.atool.fluent.mybatis.functions.FormApply;
import cn.org.atool.fluent.mybatis.model.Form;
import cn.org.atool.fluent.mybatis.model.IFormApply;
import cn.org.atool.fluent.mybatis.utility.PoJoHelper;
import com.danke.entity.Task;
import com.danke.helper.TaskWrapperHelper.ISegment;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * TaskFormSetter: Form Column Setter
 *
 * @author powered by FluentMybatis
 */
public final class TaskFormSetter extends FormSetter implements ISegment<IFormApply<Task, TaskFormSetter>> {
  private TaskFormSetter(FormApply apply) {
    super.formApply = apply;
  }

  @Override
  public Class entityClass() {
    return Task.class;
  }

  public static IFormApply<Task, TaskFormSetter> by(Object object, Form form) {
    assertNotNull("object", object);
    Map map = PoJoHelper.toMap(object);
    Function<FormApply, FormSetter> apply = TaskFormSetter::new;
    return new FormApply<>(apply, map, form);
  }
}
