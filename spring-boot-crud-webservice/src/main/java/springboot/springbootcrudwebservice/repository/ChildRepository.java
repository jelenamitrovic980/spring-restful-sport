package springboot.springbootcrudwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.springbootcrudwebservice.model.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

}
