package portfolio.pp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import portfolio.pp.models.Project;
import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer>{
    List<Project> findByIsActiveTrue();

    @Query("""
        SELECT p AS project, COUNT(a) AS viewCount
        FROM Project p
        LEFT JOIN p.accessLogs a
        WHERE p.isActive = true
        GROUP BY p
        ORDER BY COUNT(a) DESC, p.projectName ASC
        """)
    List<ProjectViewCount> findProjectsByViewCount();

    interface ProjectViewCount {
        Project getProject();
        Long getViewCount();
    }
}
