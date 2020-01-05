package ua.stesh.springbootrestshelter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.stesh.springbootrestshelter.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
