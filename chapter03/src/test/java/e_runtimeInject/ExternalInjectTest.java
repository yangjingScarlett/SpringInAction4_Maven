package e_runtimeInject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/5
 */
public class ExternalInjectTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = EnvironmentConfig.class)
    public static class EnvironmentConfigTest {

        @Autowired
        private BlankDisc blankDisc;

        @Test
        public void environmentConfigTest() {
            System.out.println(blankDisc.getTitle() + " by " + blankDisc.getArtist());
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = EnvironmentConfigWithDefault.class)
    public static class EnvironmentConfigWithDefaultTest {

        @Autowired
        private BlankDisc blankDisc;

        @Test
        public void environmentConfigWithDefaultTest() {
            System.out.println(blankDisc.getTitle() + " by " + blankDisc.getArtist());
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = EnvironmentConfigWithRequiredProperties.class)
    public static class EnvironmentConfigWithRequiredPropertiesTest {

        @Autowired
        private BlankDisc blankDisc;

        @Test
        public void environmentConfigRequiredPropertiesTest() {
            System.out.println(blankDisc.getTitle() + " by " + blankDisc.getArtist());
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:e_runtimeInject/spring.xml")
    public static class EnvironmentConfigTest_XMLConfig {

        @Autowired
        private BlankDisc blankDisc;

        @Test
        public void environmentConfigTest_XMLConfig() {
            System.out.println(blankDisc.getTitle() + " by " + blankDisc.getArtist());
        }

    }

}
