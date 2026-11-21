package portfolio.pp.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.OffsetDateTime;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import portfolio.pp.models.AccessLog;
import portfolio.pp.models.Project;
import portfolio.pp.repos.AccessLogRepo;
import portfolio.pp.repos.ProjectRepo;

@RestController
@RequestMapping("/api")
public class ProxyController {

    private final ProjectRepo projectRepo;
    private final AccessLogRepo accessLogRepo;
    private final HttpClient httpClient;

    public ProxyController(ProjectRepo projectRepo, AccessLogRepo accessLogRepo) {
        this.projectRepo = projectRepo;
        this.accessLogRepo = accessLogRepo;
        this.httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }

    @GetMapping({"/proxy/{identifier}", "/projects/{identifier}/proxy"})
    public ResponseEntity<byte[]> proxyProject(
            @PathVariable String identifier,
            HttpServletRequest request) throws Exception {
        Project project = findProject(identifier);
        if (!Boolean.TRUE.equals(project.getIsActive())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }

        URI target;
        try {
            target = URI.create(project.getInternalEndpoint());
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Project URL is invalid");
        }
        if (!target.isAbsolute()
                || !("http".equalsIgnoreCase(target.getScheme()) || "https".equalsIgnoreCase(target.getScheme()))) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Project URL is invalid");
        }

        HttpRequest upstreamRequest = HttpRequest.newBuilder(target)
            .timeout(Duration.ofSeconds(20))
            .GET()
            .build();

        HttpResponse<byte[]> upstreamResponse;
        try {
            upstreamResponse = httpClient.send(upstreamRequest, HttpResponse.BodyHandlers.ofByteArray());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Project server did not respond");
        } catch (java.io.IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Project server did not respond");
        }

        if (upstreamResponse.statusCode() >= 400) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Project server returned an error");
        }

        recordAccess(project, request);

        HttpHeaders headers = new HttpHeaders();
        String contentType = upstreamResponse.headers().firstValue("Content-Type").orElse("text/html");
        try {
            headers.setContentType(MediaType.parseMediaType(contentType));
        } catch (RuntimeException ex) {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        headers.set("X-Frame-Options", "SAMEORIGIN");

        return new ResponseEntity<>(upstreamResponse.body(), headers, HttpStatus.OK);
    }

    private Project findProject(String identifier) {
        try {
            Integer id = Integer.valueOf(identifier);
            return projectRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        } catch (NumberFormatException ex) {
            return projectRepo.findAll().stream()
                .filter(project -> identifier.equals(project.getSlug()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        }
    }

    private void recordAccess(Project project, HttpServletRequest request) {
        AccessLog accessLog = new AccessLog();
        accessLog.setProject(project);
        accessLog.setAccessedAt(OffsetDateTime.now());

        String forwarded = request.getHeader("X-Forwarded-For");
        accessLog.setIpAddress(forwarded == null || forwarded.isBlank()
            ? request.getRemoteAddr()
            : forwarded.split(",")[0].trim());

        String userAgent = request.getHeader("User-Agent");
        accessLog.setUserAgent(userAgent == null || userAgent.isBlank() ? "unknown" : userAgent);
        accessLogRepo.save(accessLog);
    }
}
