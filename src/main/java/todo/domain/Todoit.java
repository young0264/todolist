package todo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Todoit {
    private Long id;
    private String doList;
    private LocalDateTime localDateTime;

    public Todoit() {
    }

    public Todoit(String doList, LocalDateTime localDateTime) {
        this.doList = doList;
        this.localDateTime = localDateTime;
    }
}
