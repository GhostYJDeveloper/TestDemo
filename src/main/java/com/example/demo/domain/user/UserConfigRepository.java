package com.example.demo.domain.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserConfigRepository implements IUserConfigRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserConfig selectByChineseName(String chineseName) {
        String sql = "select * from t_user where u_chineseName=?";
        Map map = jdbcTemplate.queryForMap(sql, chineseName);
        try {
            return new UserConfig((Long) map.get("u_id"), (String) map.get("u_chineseName"), (String) map.get("u_userName")
                    , (String) map.get("u_passWord"), (Date) map.get("u_createTime"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public User selectByChineseName1(String chineseName) {
        SqlRowSet sqlRowSet=jdbcTemplate.queryForRowSet("select * from t_user where u_chineseName=?",chineseName);
        while (sqlRowSet.next()){
            return convert(sqlRowSet);
        }
        return null;
    }

    @Override
    public int insert(User user) {
        String sql = "insert into t_user(u_id,u_chineseName,u_username,u_password,u_createTime) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getId(), user.getChineseName(), user.getUserName(), user.getPassWord()
                , user.getCreateTime());
    }

    /**
     * 转换查询条件
     *
     * @param criteria 查询条件字典
     * @param args     查询参数集合
     * @return sql查询语句
     */
    private String convertCriteria(HashMap<String, Object> criteria, List<Object> args) {
        if (criteria == null)
            return "";

        List<String> sqls = new ArrayList<>();
        if (criteria.containsKey("chineseName")) {
            sqls.add(" u_chineseName like ? ");
            args.add("%" + criteria.get("chineseName") + "%");
        }
        if (criteria.containsKey("userName")) {
            sqls.add(" u_userName like ? ");
            args.add("%" + criteria.get("userName") + "%");
        }
        return String.join(" and ", sqls);
    }

    /**
     * 查询用户
     */
    @Override
    public List<User> selectByPage(int page, int limit, HashMap<String, Object> criteria) {
        List<Object> args = new ArrayList<>();
        String criteriaSql = convertCriteria(criteria, args);
        if (!StringUtils.isBlank(criteriaSql))
            criteriaSql = " where " + criteriaSql;

        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_user");
        sql.append(criteriaSql);
        sql.append(" order by u_id");
        sql.append(" limit ?, ?;");
        args.add((page - 1) * limit);
        args.add(limit);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql.toString(), args.toArray());
        List<User> list = new ArrayList<>();
        while (sqlRowSet.next()) {
            list.add(convert(sqlRowSet));
        }
        return list;
    }

    @Override
    public int selectCount(HashMap<String, Object> criteria) {
        List<Object> args = new ArrayList<>();
        String criteriaSql = convertCriteria(criteria, args);
        if (!StringUtils.isBlank(criteriaSql))
            criteriaSql = " where " + criteriaSql;

        return jdbcTemplate.queryForObject("select count(*) from t_user" + criteriaSql,
                args.toArray(), int.class);
    }

    /**
     * 转换用户
     */
    public User convert(SqlRowSet sqlRowSet) {
        return new User(
                sqlRowSet.getLong("u_id"),
                sqlRowSet.getString("u_userName"),
                sqlRowSet.getString("u_chineseName"),
                sqlRowSet.getString("u_passWord"),
                sqlRowSet.getTimestamp("u_createTime"));
    }

    /**
     * 根据Id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User selectUserById(Long id) {
        Map map = jdbcTemplate.queryForMap("select * from t_user where u_id=? order by u_id limit 1;", id);
        try {
            return new User((Long) map.get("u_id"), (String) map.get("u_chineseName"), (String) map.get("u_userName")
                    , (String) map.get("u_passWord"), (Date) map.get("u_createTime"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        jdbcTemplate.update("DELETE from t_user where u_id=?", id);
    }

    /**
     * 根据Id批量删除
     * @param idList
     */
    public void deleteUserByIdList(List<Long> idList) {
        jdbcTemplate.batchUpdate("delete  from t_user where u_id=?",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, idList.get(i));
                    }
                    @Override
                    public int getBatchSize() {
                        return idList.size();
                    }
                }
        );
    }
}
