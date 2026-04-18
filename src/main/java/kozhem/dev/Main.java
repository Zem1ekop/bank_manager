package kozhem.dev;

import kozhem.dev.services.OperationsConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    static void main() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);
        listener.start();


    }
}
