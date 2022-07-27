package todo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TodoRepositoryTest {
    TodoRepository todoRepository = new TodoRepository();

    @AfterEach
    void afterEach() {
        todoRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Todo todo = new Todo("밥먹기", LocalDateTime.now().minusHours(1));

        //when
        Todo savedTodo = todoRepository.save(todo);

        //then
        Todo findItem = todoRepository.findById(todo.getId());

        //저장한 값이랑 조회한 값이랑 같다.
        assertThat(findItem).isEqualTo(savedTodo);
    }

    @Test
    void findAll() {
        //given
        Todo todo1 = new Todo("밥먹기", LocalDateTime.now());
        Todo todo2 = new Todo("밥먹기", LocalDateTime.now().minusHours(3));

        todoRepository.save(todo1);
        todoRepository.save(todo2);

        //when
        List<Todo> result = todoRepository.findAll();

        //then , 크기와 내용물
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(todo1, todo2);
    }

    @Test
    void updateTodo() {
        //given
        Todo todo = new Todo("밥먹기", LocalDateTime.now());

        Todo savedTodo = todoRepository.save(todo);
        Long todoId = todo.getId();

        //when
        Todo updateTodo = new Todo("백준풀기", LocalDateTime.now().minusHours(1));
        todoRepository.update(todoId, updateTodo);

        //then
        Todo findTodo = todoRepository.findById(todoId);

        assertThat(findTodo.getDoList()).isEqualTo(updateTodo.getDoList());
        assertThat(findTodo.getLocalDateTime()).isSameAs(updateTodo.getLocalDateTime());


    }

}