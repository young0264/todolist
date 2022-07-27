package todo.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import todo.domain.Todo;
import todo.domain.TodoRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/basic/todolist")
@RequiredArgsConstructor
public class BasicController {

    private final TodoRepository todoRepository;

    //투두리스트 목록
    @GetMapping
    public String todolist(Model model) {
        List<Todo> todolist = todoRepository.findAll();
        model.addAttribute("todolist", todolist);
        return "basic/todolist";
    }

    @GetMapping("/{todoId}")
    public String todo(@PathVariable long todoId, Model model) {
        Todo todo = todoRepository.findById(todoId);
        model.addAttribute("todo", todo);
        return "basic/todo";
    }

    @GetMapping("/add")
    public String addList() {
        return "basic/addList";
    }

    @PostMapping("/add")
    public String addListV1(Todo todo, RedirectAttributes redirectAttributes) {
        Todo savedTodo = todoRepository.save(todo);
        redirectAttributes.addAttribute("todoId", savedTodo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/todolist/{todoId}";
    }

    @GetMapping("/{todoId}/edit")
    public String editList(@PathVariable Long todoId, Model model) {
        Todo todo = todoRepository.findById(todoId);
        model.addAttribute("todo",todo );
        return "basic/editList";
    }

    @PostMapping("/{todoId}/edit")
    public String edit(@PathVariable Long todoId, @ModelAttribute Todo todo) {
        todoRepository.update(todoId, todo);
        return "redirect:/basic/todolist/{todoId}";
    }

    /**
     * 테스트용
     */
    @PostConstruct
    public void init() {

        todoRepository.save(new Todo("밥먹기", LocalDateTime.now().minusHours(1)));
        todoRepository.save(new Todo("씻기", LocalDateTime.now().minusHours(3)));
    }

}
