package portfolio.pp.repos;

import org.springframework.stereotype.Repository;

import portfolio.pp.models.Technology;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TechnologyRepo extends JpaRepository<Technology, Integer> {
    
}
