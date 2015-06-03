package core.dao;

import com.anmi.mailclient.core.dao.AccountRoleDao;
import com.anmi.mailclient.core.dao.entity.AccountRole;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import java.util.Set;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class AccountRoleDaoTest {

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Test
    public void getAccountRolesByAccountId(){
        Set<AccountRole> accountRoleList = accountRoleDao.findByAccountId(1L);
        assertEquals(3, accountRoleList.size());
    }
}
