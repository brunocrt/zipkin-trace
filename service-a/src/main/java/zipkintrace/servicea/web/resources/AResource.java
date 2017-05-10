package zipkintrace.servicea.web.resources;

import lombok.Getter;
import lombok.Setter;

public class AResource {
    @Getter
    @Setter
    private String text;

    public AResource(String text) {
        this.text = text;
    }
}
