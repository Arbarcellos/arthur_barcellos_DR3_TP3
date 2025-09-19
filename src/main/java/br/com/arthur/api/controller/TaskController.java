package br.com.arthur.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthur.api.model.domain.Task;
import br.com.arthur.api.model.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<Task>> obtainList() {
		List<Task> tasks = taskService.obtainList();
				
		if(tasks.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(tasks);
	}

	@PostMapping
	public ResponseEntity<Task> add(@RequestBody Task task) {
		Task newTask = taskService.add(task);
		
		//return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Task>> obtainById(@PathVariable Integer id){
		Optional<Task> taskFound = taskService.obtainById(id);

        if (taskFound.isPresent()) {
            return ResponseEntity.ok(taskFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable Integer id,@RequestBody Task updatedTask) {	
		Task task = taskService.update(id, updatedTask);

        if (task.getClass()!=null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/description")
	public ResponseEntity<List<Task>> obtainByDescription(@RequestParam String description) {
		List<Task> list = taskService.obtainByDescription(description);
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(list);
	}
	@GetMapping("/pending")
	public ResponseEntity<List<Task>> obtainByPending() {
		List<Task> list = taskService.obtainByPending(true);
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(list);
	}
	@GetMapping("/notpending")
	public ResponseEntity<List<Task>> obtainByNotPending() {
		List<Task> list = taskService.obtainByPending(false);
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(list);
	}
}
