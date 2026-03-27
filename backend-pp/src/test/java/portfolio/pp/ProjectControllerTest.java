package portfolio.pp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import portfolio.pp.models.Project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import portfolio.pp.repos.ProjectRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepo projectRepo;

    @Test
    void shouldReturnProjectsArrayWithCorrectFields() throws Exception {

        // 1. Arrange: Save a dummy project to the database FIRST
    Project dummyProject = new Project();
            dummyProject.setProjectName("TestProject");
            dummyProject.setSlug("thisIsATestSlugTEST");
            dummyProject.setInternalEndpoint("/api/projects");
            projectRepo.save(dummyProject);

        // 2. Act & Assert: Perform GET request and verify response
            mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Verify it's a JSON array
                .andExpect(jsonPath("$", instanceOf(java.util.List.class)))
                // Check fields of the first project in the list
                .andExpect(jsonPath("$[0].projectName").exists())
                .andExpect(jsonPath("$[0].slug").exists())
                .andExpect(jsonPath("$[0].internalEndpoint").exists())
                // Verify nested arrays exist
                .andExpect(jsonPath("$[0].projectSkills", is(notNullValue())))
                .andExpect(jsonPath("$[0].projectTechs", is(notNullValue())));
    }
}