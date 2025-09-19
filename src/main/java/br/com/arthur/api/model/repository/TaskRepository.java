package br.com.arthur.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arthur.api.model.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{
	
	List<Task> findByDescriptionContainingIgnoreCase(String description); //OK
	List<Task> findByPending(boolean pending); //OK
	List<Task> findByStatusContainingIgnoreCase(String status);
	List<Task> findByOwnerContainingIgnoreCase(String owner);
	List<Task> findByDueDateContainingIgnoreCase(String dueDate);
	List<Task> findByDuration(double duration);
	
}
