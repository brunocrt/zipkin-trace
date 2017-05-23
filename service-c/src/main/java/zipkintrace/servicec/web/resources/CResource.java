package zipkintrace.servicec.web.resources;


import lombok.Getter;
import lombok.Setter;

public class CResource {
    @Getter
    @Setter
    private String text;

    public CResource(String text) {
        this.text = text;
    }
}
