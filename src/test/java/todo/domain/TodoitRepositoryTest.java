package todo.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TodoitRepositoryTest {
    TodoRepository todoRepository = new TodoRepository();

    @AfterEach
    void afterEach() {
        todoRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Todoit todoit = new Todoit("밥먹기", LocalDateTime.now().minusHours(1));

        //when
        Todoit savedTodoit = todoRepository.save(todoit);

        //then
        Todoit findItem = todoRepository.findById(todoit.getId());

        //저장한 값이랑 조회한 값이랑 같다.
        assertThat(findItem).isEqualTo(savedTodoit);
    }

    @Test
    void findAll() {
        //given
        Todoit todoit1 = new Todoit("밥먹기", LocalDateTime.now());
        Todoit todoit2 = new Todoit("밥먹기", LocalDateTime.now().minusHours(3));

        todoRepository.clearStore();
        todoRepository.save(todoit1);
        todoRepository.save(todoit2);

        //when
        List<Todoit> result = todoRepository.findAll();

        //then , 크기와 내용물
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(todoit1, todoit2);

    }

    @Test
    void updateTodo() {
        //given
        Todoit todoit = new Todoit("밥먹기", LocalDateTime.now());

        Todoit savedTodoit = todoRepository.save(todoit);
        Long todoId = todoit.getId();

        //when
        Todoit updateTodoit = new Todoit("백준풀기", LocalDateTime.now().minusHours(1));
        todoRepository.update(todoId, updateTodoit);

        //then
        Todoit findTodoit = todoRepository.findById(todoId);

        assertThat(findTodoit.getDoList()).isEqualTo(updateTodoit.getDoList());
        assertThat(findTodoit.getLocalDateTime()).isSameAs(updateTodoit.getLocalDateTime());


    }

}