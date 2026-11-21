package portfolio.pp.controllers;

import portfolio.pp.repos.ProjectRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portfolio.pp.models.Project;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepo projectRepo;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping
    public List<Project> getAllProjectMetaData() {
        return projectRepo.findByIsActiveTrue();
    }
    
    @GetMapping("/test-403")
    public void view403(HttpServletResponse response) throws java.io.IOException {
    // This tells the container "an error happened," 
    // which triggers the automatic search for error page.
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
}

    @GetMapping("/ranked")
    public List<Map<String, Object>> getRankedProjects() {
        return projectRepo.findProjectsByViewCount().stream()
            .map(result -> {
                Project project = result.getProject();
                return Map.<String, Object>of(
                    "id", project.getId(),
                    "projectName", project.getProjectName(),
                    "slug", project.getSlug(),
                    "internalEndpoint", project.getInternalEndpoint(),
                    "viewCount", result.getViewCount()
                );
            })
            .collect(Collectors.toList());
    }
}
