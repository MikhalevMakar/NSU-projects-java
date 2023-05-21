package ru.nsu.org.mikhalev.universal_utile_class.create_command;

import lombok.Getter;

public class ContextCommand {

    @Getter
    private static final String LOG_IN = "logIn";

    @Getter
    private static final String MESSAGE = "message";

    @Getter
    private static final String LIST_PARTICIPANTS  = "listParticipants";

    @Getter
    private static final String CHAT_HISTORY = "chatHistory";

    @Getter
    private static final String ERROR = "error";
}