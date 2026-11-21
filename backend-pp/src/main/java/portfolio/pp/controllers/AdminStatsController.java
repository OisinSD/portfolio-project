package portfolio.pp.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portfolio.pp.models.Project;
import portfolio.pp.repos.ProjectRepo;

@RestController
@RequestMapping("/api/admin")
public class AdminStatsController {

    private final ProjectRepo projectRepo;

    public AdminStatsController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping("/stats")
    public List<Map<String, Object>> getStats() {
        return projectRepo.findProjectsByViewCount().stream()
            .map(result -> {
                Project project = result.getProject();
                return Map.<String, Object>of(
                    "projectId", project.getId(),
                    "projectName", project.getProjectName(),
                    "slug", project.getSlug(),
                    "viewCount", result.getViewCount()
                );
            })
            .collect(Collectors.toList());
    }
}
