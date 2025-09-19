package br.com.arthur.api.model.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.arthur.api.model.domain.Task;
import br.com.arthur.api.model.exceptions.DadosInvalidosException;
import br.com.arthur.api.model.exceptions.RecursoNaoEncontradoException;
import br.com.arthur.api.model.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	private final Map<Integer, Task> mapTask = new HashMap<Integer, Task>();
	private final AtomicInteger nextId = new AtomicInteger(1); 
	

	//add POST
	public Task add(Task task) {
		validateTask(task);
		
	    task.setId(nextId.getAndIncrement());
	    mapTask.put(task.getId(), task);

	    return task;
	}

	
	// change PUT
    public Task update(Integer id, Task novaTask) {
        if (!mapTask.containsKey(id)) {
            throw new RecursoNaoEncontradoException("Task with ID " + id + " not found!");
            
        }
        novaTask.setId(id);
        validateTask(novaTask);
        mapTask.put(id, novaTask);
        return novaTask;
    }


	// DELETE
	public void delete(Integer id) {
        if (!mapTask.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID " + id + " not found!");
        }
        mapTask.remove(id);
    }

	
	public List<Task> obtainByDescription(String description) {
		return taskRepository.findByDescriptionContainingIgnoreCase(description);
	}
	
	
	public List<Task> obtainList(){
		
		return new ArrayList<Task>(mapTask.values());
	}

	
	public Optional<Task> obtainById(Integer id) {
		return Optional.ofNullable(mapTask.get(id));
	}

	
	public List<Task> obtainByPending(boolean choice) {
		return taskRepository.findByPending(choice);
	}
	

	private void validateTask(Task task) {
	    if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
	        throw new DadosInvalidosException("Description can't be empty nor null.");
	    }
	
	    if (task.getOwner() == null || task.getOwner().trim().isEmpty()) {
	        throw new DadosInvalidosException("Owner can't be empty nor null.");
	    }
	
	    if (task.getDuration() < 0 || task.getDuration() > 10) {
	        throw new DadosInvalidosException("Duration must be between 0 e 10.");
	    }
	
	    if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
	        throw new DadosInvalidosException("Status can't be empty nor null.");
	    }
	
	    if (task.getUrgency() == null) {
	        throw new DadosInvalidosException("Urgency can't be null.");
	    }
	}
}


