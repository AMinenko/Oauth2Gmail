package core.dao;

import com.anmi.mailclient.core.dao.MailDao;
import com.anmi.mailclient.core.dao.entity.Mail;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class MailDaoTest {

    @Autowired
    private MailDao mailDao;

    @Test
    public void testMailDao() {
        Mail mail = new Mail();

        mail = mailDao.save(mail);
        assertNotNull(mail.getId());

        Long mailId = mail.getId();
        mail = mailDao.findOne(mailId);
        assertNotNull(mail);

        mailDao.delete(mail);
//        assertNull(mail);
    }

}
