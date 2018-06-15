package com.wl.service;

import com.wl.DAO.UserDAO;
import com.wl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    public void saveUsers(List<User> us) {
        for (User u : us) {
            userDao.save(u);
        }
    }

    public List<User> getAllUsernames() {
        return userDao.findAll();
    }

    public User getByNameAndPwd(String user, String pwd) {
        User u =userDao.getByNameAndPwd(user,pwd);
        return u;
    }
}
