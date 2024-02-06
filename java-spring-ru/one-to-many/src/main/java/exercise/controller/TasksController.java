package exercise.controller;

import java.util.List;
import java.util.stream.Collectors;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.mapper.UserMapper;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final  UserMapper userMapper;
    private final UserRepository userRepository;

    @GetMapping(path = "")
    private List<TaskDTO> tasksList() {
        return taskRepository.findAll().stream()
                .map(i -> taskMapper.map(i)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    private TaskDTO taskById(@PathVariable Long id) {
        return taskMapper.map(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("1234")));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    private TaskDTO createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        var task = taskMapper.map(taskCreateDTO);
        task.setAssignee(task.getAssignee());
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping(path = "/{id}")
    private TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        var task = taskRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("124"));
        taskMapper.update(taskUpdateDTO, task);
        task.setAssignee(userRepository.findById(taskUpdateDTO.getAssigneeId()).get());
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
