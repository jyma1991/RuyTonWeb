package com.ryt.web.service.auth;

import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.subexpression.ValueExpression;

import com.ryt.web.entity.auth.AuthDataPermission;


public class ExpressionFactory {
    private static final byte TYPE_VALUE_EXPRESSION = 1;
    private static final byte TYPE_LIST_EXPRESSION = 2;

    public static Expression build(AuthDataPermission dataPermission) {
        if (dataPermission == null) {
            return null;
        }
        byte expressionType = dataPermission.getExpressionType();
        String column = dataPermission.getDataColumn();
        String equal = dataPermission.getEqual();
        String value = dataPermission.getDataValue();

        if (TYPE_VALUE_EXPRESSION == expressionType) {
            return new ValueExpression(column, equal, value);
        }
        if (TYPE_LIST_EXPRESSION == expressionType) {
            // TODO:....
        }

        return null;
    }

}
