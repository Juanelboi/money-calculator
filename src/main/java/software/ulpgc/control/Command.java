package software.ulpgc.control;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}
