package com.lk.blog.interceptor;

import com.lk.blog.dto.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static org.apache.ibatis.reflection.SystemMetaObject.*;

@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = Connection.class)})
public class PaginationInterceptor implements Interceptor {
    public static final Logger logger = Logger.getLogger(PaginationInterceptor.class);
    public static final String DEFAULT_PAGE_SQL_ID = ".*Page$";

    private String pageSqlId;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStateHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        while (metaStateHandler.hasGetter("h")) {
            Object object = metaStateHandler.getValue("h");
            metaStateHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        while (metaStateHandler.hasGetter("target")) {
            Object object = metaStateHandler.getValue("target");
            metaStateHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        Configuration configuration = (Configuration) metaStateHandler.getValue("delegate.configuration");
        if (configuration.getVariables() != null) {
            pageSqlId = configuration.getVariables().getProperty("pageSqlId");
        }
        if (pageSqlId == null || pageSqlId.trim() == "") {
            logger.warn("pageSqlId is not setted");
            pageSqlId = DEFAULT_PAGE_SQL_ID;
        }
        MappedStatement mappedStatement = (MappedStatement) metaStateHandler.getValue("delegate.mappedStatement");
        if (mappedStatement.getId().matches(pageSqlId)) {
            BoundSql boundSql = (BoundSql) metaStateHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null");
            }
            Page page = (Page) metaStateHandler.getValue("delegate.boundSql.parameterObject.page");
            String sql = boundSql.getSql();
            String paginationSql = this.buildPaginationSql(sql, page);
            metaStateHandler.setValue("delegate.boundSql.sql", paginationSql);

            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
            metaStateHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStateHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

            Connection connection = (Connection) invocation.getArgs()[0];
            this.setPageParameter(sql, connection, mappedStatement, boundSql, page);
        }
        return invocation.proceed();
    }

    private String buildPaginationSql(String sql, Page page) {
        StringBuilder builder = new StringBuilder(sql);
        builder.append(" limit ")
                .append(page.getPageSize() * page.getPageNow())
                .append(" , ")
                .append(page.getPageSize());
        return builder.toString();
    }

    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, Page page) {
        String countSql = "SELECT count(0) FROM (" + sql + ") as total";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            // 设置参数(?)
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBS);
            parameterHandler.setParameters(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            int totalCount = 0;
            if (resultSet.next()) {
                totalCount = resultSet.getInt(1);
            }
            page.setTotalNumber(totalCount);
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
