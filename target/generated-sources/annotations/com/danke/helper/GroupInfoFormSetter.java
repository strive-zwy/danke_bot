package com.danke.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;

import cn.org.atool.fluent.mybatis.base.crud.FormSetter;
import cn.org.atool.fluent.mybatis.functions.FormApply;
import cn.org.atool.fluent.mybatis.model.Form;
import cn.org.atool.fluent.mybatis.model.IFormApply;
import cn.org.atool.fluent.mybatis.utility.PoJoHelper;
import com.danke.entity.GroupInfo;
import com.danke.helper.GroupInfoWrapperHelper.ISegment;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * GroupInfoFormSetter: Form Column Setter
 *
 * @author powered by FluentMybatis
 */
public final class GroupInfoFormSetter extends FormSetter implements ISegment<IFormApply<GroupInfo, GroupInfoFormSetter>> {
  private GroupInfoFormSetter(FormApply apply) {
    super.formApply = apply;
  }

  @Override
  public Class entityClass() {
    return GroupInfo.class;
  }

  public static IFormApply<GroupInfo, GroupInfoFormSetter> by(Object object, Form form) {
    assertNotNull("object", object);
    Map map = PoJoHelper.toMap(object);
    Function<FormApply, FormSetter> apply = GroupInfoFormSetter::new;
    return new FormApply<>(apply, map, form);
  }
}
