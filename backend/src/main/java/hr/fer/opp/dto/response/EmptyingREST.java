package hr.fer.opp.dto.response;

import hr.fer.opp.model.Emptying;

import java.util.ArrayList;
import java.util.List;

public class EmptyingREST {

    private long id;

    private long workerId;

    private long containerId;

    private long timestamp;

    public EmptyingREST(Emptying e) {
        this.id = e.getId();
        this.workerId = e.getWorker().getId();
        this.containerId = e.getContainer().getId();
        this.timestamp = e.getTimestamp();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static List<EmptyingREST> convertToREST(List<Emptying> emptyingList) {
        List<EmptyingREST> emptyingRESTList = new ArrayList<>();
        for (Emptying e : emptyingList) {
            emptyingRESTList.add(new EmptyingREST(e));
        }
        return emptyingRESTList;
    }
}
