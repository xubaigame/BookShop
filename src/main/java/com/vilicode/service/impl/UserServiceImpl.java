package com.vilicode.service.impl;

import com.vilicode.Utils.MyCipher;
import com.vilicode.bean.Page;
import com.vilicode.bean.User;
import com.vilicode.mapper.UserMapper;
import com.vilicode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean register(User user) {
        boolean flag=false;
        //密码加密
        MyCipher mc=new MyCipher();
        user.setUpwd(mc.encrypt(user.getUpwd(), "*%#@()^&"));
        try {
            int result=userMapper.identify(user.getUname());
            if(result>=1)
                return false;
            userMapper.addUser(user);
            flag=true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public User login(String uname) {
        String pwd="";
        //密码解密
        MyCipher  mc=new MyCipher();
        User user=userMapper.login(uname);
        if(user!=null)
            user.setUpwd(mc.decrypt(user.getUpwd(),"*%#@()^&"));
        return user;
    }

    public boolean UpdatePhoneAndAddress(int uid,String uphone,String uaddress)
    {
        try {
            userMapper.UpdatePhoneAndAddress(uid,uphone,uaddress);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean UpdatePassword(int uid, String upwd,String oldupwd) {
        MyCipher myCipher=new MyCipher();
        try {
            String temp=userMapper.queryUpwd(uid);
            if(temp.equals(myCipher.encrypt(oldupwd,"!")))
            {
                userMapper.UpdatePassword(uid,myCipher.encrypt(upwd,"!"));
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean UpdatePassword(int uid, String upwd) {
        MyCipher myCipher=new MyCipher();
        try {
            userMapper.UpdatePassword(uid,myCipher.encrypt(upwd,"!"));
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Page queryUser(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = 0;
        try {
            totalCount = userMapper.queryCountOfUser();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = userMapper.queryUser( (pageNumber-1)*pageSize, pageSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }

    @Override
    public boolean deleteUser(int uid) {
        try {
            userMapper.deleteUser(uid);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public User queryUserByUid(int uid) {
        return  userMapper.queryUserByUid(uid);
    }

}
