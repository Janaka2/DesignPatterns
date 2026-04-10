package org.example.modern_java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class CommandVariants {
    private CommandVariants() {
    }

    public interface Command {
        String execute();
    }

    public static final class ClassicInvoker {
        private final List<Command> queue = new ArrayList<>();

        public void enqueue(final Command command) {
            queue.add(command);
        }

        public List<String> runAll() {
            return queue.stream().map(Command::execute).toList();
        }
    }

    public static final class FunctionalInvoker {
        private final List<Supplier<String>> queue = new ArrayList<>();

        public void enqueue(final Supplier<String> command) {
            queue.add(command);
        }

        public List<String> runAll() {
            return queue.stream().map(Supplier::get).toList();
        }
    }

    public sealed interface AppCommand permits CreateUser, SuspendUser {
    }

    public record CreateUser(String userId) implements AppCommand {
    }

    public record SuspendUser(String userId) implements AppCommand {
    }

    public static final class ModernInvoker {
        public String run(final AppCommand command) {
            return switch (command) {
                case CreateUser createUser -> "created:" + createUser.userId();
                case SuspendUser suspendUser -> "suspended:" + suspendUser.userId();
            };
        }
    }
}
