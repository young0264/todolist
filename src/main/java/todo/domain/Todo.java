package todo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class Todo {
    private Long id;
    private String doList;
    private LocalDateTime localDateTime;

    public Todo() {
    }

    public Todo(String doList, LocalDateTime localDateTime) {
        this.doList = doList;
        this.localDateTime = localDateTime;
    }
}
