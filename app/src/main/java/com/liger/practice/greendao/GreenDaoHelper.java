package com.liger.practice.greendao;

import com.liger.practice.BaseApp;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 数据库工具类
 *
 * @author zs
 * @date 2018/7/23 0023.
 */
public class GreenDaoHelper {

    private UserDao mUserDao;

    private static class GreenDaoHelperHolder {
        static final GreenDaoHelper INSTANCE = new GreenDaoHelper();
    }

    private GreenDaoHelper() {
        if (mUserDao == null) {
            getUserDao();
        }
    }

    public static GreenDaoHelper getInstance() {
        return GreenDaoHelperHolder.INSTANCE;
    }

    private void getUserDao() {
        DaoSession daoSession = BaseApp.getInstance().getDaoSession();
        if (daoSession == null) {
            return;
        }
        mUserDao = daoSession.getUserDao();
    }

    public void save(User user) {
        mUserDao.insert(user);
    }

    public void delete(User user) {
        mUserDao.delete(user);
    }

    public void deleteByUserId(long userId) {
        mUserDao.deleteByKey(userId);
    }

    public void update(User user) {
        mUserDao.update(user);
    }

    public List<User> queryAll() {
        return mUserDao.loadAll();
    }

    public User queryById() {
        return mUserDao.loadByRowId(1);
    }

    /**
     * 查询年龄大于 10 的用户
     *
     * @return
     */
    public List<User> query() {
        return mUserDao.queryRaw("where AGE>?", "10");
    }

    /**
     * 查询年龄大于 10 的用户
     *
     * @return
     */
    public List<User> query2() {
        QueryBuilder<User> builder = mUserDao.queryBuilder();
        return builder.where(UserDao.Properties.Age.gt(10)).build().list();
    }


}
