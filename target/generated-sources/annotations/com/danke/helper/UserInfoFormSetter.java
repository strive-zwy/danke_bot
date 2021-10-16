package com.danke.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;

import cn.org.atool.fluent.mybatis.base.crud.FormSetter;
import cn.org.atool.fluent.mybatis.functions.FormApply;
import cn.org.atool.fluent.mybatis.model.Form;
import cn.org.atool.fluent.mybatis.model.IFormApply;
import cn.org.atool.fluent.mybatis.utility.PoJoHelper;
import com.danke.entity.UserInfo;
import com.danke.helper.UserInfoWrapperHelper.ISegment;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * UserInfoFormSetter: Form Column Setter
 *
 * @author powered by FluentMybatis
 */
public final class UserInfoFormSetter extends FormSetter implements ISegment<IFormApply<UserInfo, UserInfoFormSetter>> {
  private UserInfoFormSetter(FormApply apply) {
    super.formApply = apply;
  }

  @Override
  public Class entityClass() {
    return UserInfo.class;
  }

  public static IFormApply<UserInfo, UserInfoFormSetter> by(Object object, Form form) {
    assertNotNull("object", object);
    Map map = PoJoHelper.toMap(object);
    Function<FormApply, FormSetter> apply = UserInfoFormSetter::new;
    return new FormApply<>(apply, map, form);
  }
}
