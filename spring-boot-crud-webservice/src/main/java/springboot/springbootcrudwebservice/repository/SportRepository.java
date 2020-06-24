package springboot.springbootcrudwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.springbootcrudwebservice.model.Sport;


@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

}
