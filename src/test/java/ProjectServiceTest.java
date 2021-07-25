import com.mongodb.client.MongoClient;
import configuration.Config;
import dto.ProjectDTO;
import model.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import repository.ProjectRepository;
import service.ProjectService;

import java.util.List;

@SpringBootTest(classes = Config.class)
@DataJpaTest
@AutoConfigureDataMongo
public class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void getAllProjectsTest() {
        List<ProjectDTO> list = projectService.getAllProjects();
        Assert.notEmpty(list,"collection is empty");
    }
}
