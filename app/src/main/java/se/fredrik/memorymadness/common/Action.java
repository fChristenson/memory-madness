package se.fredrik.memorymadness.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fredrik on 2016-11-03.
 */

public class Action {
    private final Actions action;
    private final Map<Payloads, Object> payload;

    public Action(Actions action) {
        this(action, new HashMap<Payloads, Object>());
    }

    public Action(Actions action, Map<Payloads, Object> payload) {
        this.action = action;
        this.payload = payload;
    }

    public Actions getAction() {
        return action;
    }

    public Map<Payloads, Object> getPayload() {
        return payload;
    }
}
