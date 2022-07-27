package todo.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import todo.domain.Todo;
import todo.domain.TodoRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/basic/todolist")
@RequiredArgsConstructor
public class BasicController {

    private final TodoRepository todoRepository;

    /**
     * 테스트용
     */
    @PostConstruct
    public void init() {
        todoRepository.save(new Todo("밥먹기",LocalDateTime.now().minusHours(1)));
        todoRepository.save(new Todo("씻기",LocalDateTime.now().minusHours(3)));
    }

}
