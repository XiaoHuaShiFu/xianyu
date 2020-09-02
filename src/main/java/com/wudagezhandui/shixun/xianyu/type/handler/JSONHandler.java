package com.wudagezhandui.shixun.xianyu.type.handler;

import com.google.gson.Gson;
import com.wudagezhandui.shixun.xianyu.pojo.modules.TheStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JSONHandler extends BaseTypeHandler<TheStatus> {
    Gson gson=new Gson();
    private Class<TheStatus> clazz;
    private String toJSON(TheStatus object) {
        String string = gson.toJson(object);
        return string;
    }

    private TheStatus toObject(String json, Class<TheStatus> clazz) throws IOException {
        return gson.fromJson(json, TheStatus.class);
    }

    @Override
    public TheStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        try {
            return toObject(resultSet.getString(s), TheStatus.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TheStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        // TODO Auto-generated method stub
        try {
            return toObject(resultSet.getString(i), TheStatus.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TheStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            return toObject(callableStatement.getString(i), TheStatus.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, TheStatus t, JdbcType jdbcType) throws SQLException {
        // TODO Auto-generated method stub
        preparedStatement.setString(i,toJSON(t));
    }






}