package hr.fer.opp.dto.response;

import hr.fer.opp.model.Ping;
import hr.fer.opp.model.enums.PingLevel;

import java.util.ArrayList;
import java.util.List;

public class PingREST {

    private long id;

    private long creatorId;

    private long containerId;

    private PingLevel level;

    private long timestamp;

    public PingREST(Ping p) {
        this.id = p.getId();
        this.creatorId = p.getCreator().getId();
        this.containerId = p.getContainer().getId();
        this.level = p.getLevel();
        this.timestamp = p.getTimestamp();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public PingLevel getLevel() {
        return level;
    }

    public void setLevel(PingLevel level) {
        this.level = level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static List<PingREST> convertToREST(List<Ping> pings) {
        List<PingREST> pingREST = new ArrayList<>();
        for (Ping p : pings) {
            pingREST.add(new PingREST(p));
        }
        return pingREST;
    }
}
