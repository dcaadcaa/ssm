package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestMyBatis {
//    测试查询
    @Test
    public void  run1() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession session = factory.openSession();
        //获取到代理对象
        AccountDao dao = session.getMapper(AccountDao.class);
        //查询所有账户信息
        List<Account> list = dao.findALl();
        for (Account account : list){
            System.out.println(account);
        }

        //关闭资源
        session.close();
        in.close();


    }

//    测试保存
        @Test
        public void  run2() throws Exception {
            Account account = new Account();
            account.setName("熊7");
            account.setMoney(400d);

            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //创建SqlSessionFactory对象
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            //创建SqlSession对象
            SqlSession session = factory.openSession();
            //获取到代理对象
            AccountDao dao = session.getMapper(AccountDao.class);
            //保存账户信息
            dao.saveAccount(account);
            //提交事务
            session.commit();

            //关闭资源
            session.close();
            in.close();
            run1();


        }
}
