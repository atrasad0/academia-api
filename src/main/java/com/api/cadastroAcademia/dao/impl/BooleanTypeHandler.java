package com.api.cadastroAcademia.dao.impl;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanTypeHandler implements TypeHandler<Boolean> {
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private static boolean convert(int value) throws SQLException {
        if (value != TRUE && value != FALSE)
            throw new SQLException("O valor inesperado " + value + " foi encontrado. " + TRUE + ", " + FALSE + " ou null eram esperados.");

        return value == TRUE;
    }

    @Override
    public Boolean getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return convert(cs.getInt(columnIndex));
    }

    @Override
    public Boolean getResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return convert(rs.getInt(columnIndex));
    }

    @Override
    public Boolean getResult(final ResultSet rs, final String columnName) throws SQLException {
        return convert(rs.getInt(columnName));
    }

    @Override
    public void setParameter(final PreparedStatement ps, final int parameterIndex, final Boolean parameter, final JdbcType jdbcType) throws SQLException {

        if (parameter == null || parameter == false)
            ps.setInt(parameterIndex, FALSE);
        else
            ps.setInt(parameterIndex, TRUE);

    }
}
