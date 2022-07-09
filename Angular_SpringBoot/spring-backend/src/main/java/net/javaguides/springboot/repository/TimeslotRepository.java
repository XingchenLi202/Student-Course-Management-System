package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.TimeSlot;

@Repository
public interface TimeslotRepository extends JpaRepository<TimeSlot, Long>{

}
