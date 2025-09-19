package br.com.arthur.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.arthur.api.model.domain.Task;
import br.com.arthur.api.model.domain.Urgency;
import br.com.arthur.api.model.service.TaskService;

@Component
public class TaskLoader implements ApplicationRunner{
	
	@Autowired
	private final TaskService taskService = new TaskService();
	
	
	@Override
	public void run(ApplicationArguments args) {

		String arquivo = "tasks.txt";
		
		FileReader fileReader;
		
		System.out.println(">>>>>>>>>>>>>>>>INITIALIZING TASK UPLOAD.");
		try {
			fileReader = new FileReader(arquivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			
			String[] fields = null;
			
			while(line != null) {
			
				fields = line.split(";");
				
				Task task = new Task();
				task.setDescription(fields[1]);
				task.setOwner(fields[2]);
				task.setDueDate(fields[3]);
				task.setDuration(Double.valueOf(fields[4]));
				task.setStatus(fields[5]);
				task.setPending(Boolean.parseBoolean(fields[6]));
				task.setComment(fields[7]);
				task.setUrgency(Urgency.valueOf(fields[8]));
				
				taskService.add(task);
				
				line = bufferedReader.readLine();
			}
			int contador = 0;
			for(Task task: taskService.obtainList()) {
				System.out.println("# " + task);
				contador +=1;
			}
			
			System.out.println(">>>>>>>>>>>>>>>>UPLOAD FINISHED. " + contador + " TASKS UPLOADED.");
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Problemas na abertura/fechamento do arquivo: " + e.getMessage());
		}	
	}	
}
