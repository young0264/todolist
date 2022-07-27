package todo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {
    private static final Map<Long, Todo> store = new HashMap<>();
    private static long sequence = 0L;


    //저장
    public Todo save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo;
    }

    //id로 찾기
    public Todo findById(Long id) {
        return store.get(id); }

    //Todo리턴
    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    //Todo 수정
    public void update(Long id, Todo updateParam) {
        Todo findTodo = findById(id);
        findTodo.setLocalDateTime(updateParam.getLocalDateTime());
        findTodo.setDoList(updateParam.getDoList());
    }

    //store 정리
    public void clearStore() {
        store.clear();
    }






}
