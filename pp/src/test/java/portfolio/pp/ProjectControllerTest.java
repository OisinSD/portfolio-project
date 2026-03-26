package portfolio.pp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnProjectsArrayWithCorrectFields() throws Exception {
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