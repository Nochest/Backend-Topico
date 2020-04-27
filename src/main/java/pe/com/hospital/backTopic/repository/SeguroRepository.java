package pe.com.hospital.backTopic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.hospital.backTopic.model.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Integer> {

}
