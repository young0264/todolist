package todo.domain;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {
    private static final Map<Long, Todoit> store = new HashMap<>();
    private static long sequence = 0L;


    //저장
    public Todoit save(Todoit todoit) {
        todoit.setId(++sequence);
        store.put(todoit.getId(), todoit);
        return todoit;
    }

    //id로 찾기
    public Todoit findById(Long id) {
        return store.get(id); }

    //Todo리턴
    public List<Todoit> findAll() {
        return new ArrayList<>(store.values());
    }

    //Todo수정
    public void update(Long id, Todoit updateParam) {
        Todoit findTodoit = findById(id);
        findTodoit.setLocalDateTime(updateParam.getLocalDateTime());
        findTodoit.setDoList(updateParam.getDoList());
    }

    //store 정리
    public void clearStore() {
        store.clear();
    }

}
