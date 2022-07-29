package todo.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import todo.domain.Todoit;
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
        List<Todoit> todolist = todoRepository.findAll();
        model.addAttribute("todolist", todolist);
        return "basic/todolist";
    }

    @GetMapping("/{todoId}")
    public String todo(@PathVariable long todoId, Model model) {
        Todoit todoit = todoRepository.findById(todoId);
        model.addAttribute("todoit", todoit);
        return "basic/todoit";
    }

    @GetMapping("/add")
    public String addList() {
        return "basic/addList";
    }

    //이걸 통으로
    @PostMapping("/add")
    public String addListV1(Todoit todo, RedirectAttributes redirectAttributes) {
        System.out.println("todo.getId() : "+ todo.getId());
        System.out.println("todo.getDoList() : "+ todo.getDoList());
        System.out.println("todo.getLocalDateTime() : "+ todo.getLocalDateTime());

        Todoit savedTodo = todoRepository.save(todo);
        redirectAttributes.addAttribute("todoId", savedTodo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/todolist/{todoId}";
    }

    @GetMapping("/{todoId}/edit")
    public String editList(@PathVariable Long todoId, Model model) {
        Todoit todoit = todoRepository.findById(todoId);
        model.addAttribute("todoit",todoit);
        return "basic/editList";
    }

    @PostMapping("/{todoId}/edit")
    public String edit(@PathVariable Long todoId, @ModelAttribute Todoit todoit) {
        System.out.println("todoId : " + todoId);
        System.out.println("todoitgetId : " + todoit.getId());
        System.out.println("todoitgetDoList : " + todoit.getDoList());
        System.out.println("todoitgetLocalDateTime : " + todoit.getLocalDateTime());

        todoRepository.update(todoId, todoit);
        return "redirect:/basic/todolist/{todoId}";
    }

    /**
     * 테스트용
     */
    @PostConstruct
    public void init() {

        todoRepository.save(new Todoit("밥먹기", LocalDateTime.now().minusHours(1)));
        todoRepository.save(new Todoit("씻기", LocalDateTime.now().minusHours(3)));
    }

}