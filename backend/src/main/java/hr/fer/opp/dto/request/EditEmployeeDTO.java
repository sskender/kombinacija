package hr.fer.opp.dto.request;

public class EditEmployeeDTO {
    private Long newHoodId;

    public Long getNewHoodId(){
        return newHoodId;
    }

    public void setNewHoodId(Long newHoodId){
        this.newHoodId = newHoodId;
    }
}
