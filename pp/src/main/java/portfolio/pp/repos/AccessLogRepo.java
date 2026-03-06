package portfolio.pp.repos;

import org.springframework.stereotype.Repository;

import portfolio.pp.models.AccessLog;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccessLogRepo extends JpaRepository<AccessLog, Integer>{
    
}
