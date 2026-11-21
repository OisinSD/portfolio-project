package portfolio.pp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import portfolio.pp.repos.ProjectRepo;
import portfolio.pp.repos.AccessLogRepo;
import portfolio.pp.models.AccessLog;
import portfolio.pp.models.Project;
import java.util.List;
import java.util.Map;
import java.time.OffsetDateTime;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class AccesslogController {
    
    private final AccessLogRepo accessLogRepo;
    private final ProjectRepo projectRepo;

    public AccesslogController(AccessLogRepo accessLogRepo, ProjectRepo projectRepo){
        this.accessLogRepo = accessLogRepo;
        this.projectRepo = projectRepo;
    }
    @GetMapping("/api/accessLogs")
    public List<AccessLog> getAllAccessLogsMetaData(){
        return accessLogRepo.findAll();
    }

    @PostMapping("/api/analytics/views")
    public ResponseEntity<Map<String, Object>> recordView(
            @RequestBody Map<String, Object> payload,
            HttpServletRequest request) {
        Object rawProjectId = payload.get("projectId");
        if (rawProjectId == null) {
            rawProjectId = payload.get("project_id");
        }
        if (rawProjectId == null) {
            rawProjectId = payload.get("id");
        }
        if (rawProjectId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "projectId is required");
        }

        final Integer projectId;
        try {
            projectId = Integer.valueOf(rawProjectId.toString());
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "projectId must be an integer");
        }

        Project project = projectRepo.findById(projectId)
            .filter(Project::getIsActive)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        AccessLog accessLog = new AccessLog();
        accessLog.setProject(project);
        accessLog.setAccessedAt(OffsetDateTime.now());
        accessLog.setIpAddress(getClientIp(request));
        accessLog.setUserAgent(resolveUserAgent(request));
        accessLogRepo.save(accessLog);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "projectId", project.getId(),
            "recorded", true
        ));
    }

    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private String resolveUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent == null || userAgent.isBlank() ? "unknown" : userAgent;
    }
}
