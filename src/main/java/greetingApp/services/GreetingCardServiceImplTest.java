package greetingApp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GreetingCardServiceImplTest {

    @Autowired
    private GreetingCardService greetingCardService;

    @Test
    public void getCatalogTest(){
        List<GreetingcardTemplate> templates = greetingCardService.getTemplates();
        assert (templates.get(0).getType() == (GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE));
        assert (templates.get(1).getType() == (GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE));
    }
}
