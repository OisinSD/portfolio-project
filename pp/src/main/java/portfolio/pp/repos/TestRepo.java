package portfolio.pp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import portfolio.pp.models.TestTable;

public interface TestRepo extends JpaRepository<TestTable, Long>{
    
}
