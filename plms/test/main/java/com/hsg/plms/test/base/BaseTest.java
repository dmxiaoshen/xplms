
package com.hsg.plms.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-common.xml","classpath*:spring-shiro.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{

}
