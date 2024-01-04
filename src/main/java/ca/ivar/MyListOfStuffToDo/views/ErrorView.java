package ca.ivar.MyListOfStuffToDo.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.servlet.http.HttpServletResponse;

@Route("error")
public class ErrorView extends VerticalLayout implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        add(new Text("The requested resource was not found."));
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
