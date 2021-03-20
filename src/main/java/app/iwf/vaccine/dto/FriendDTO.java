package app.iwf.vaccine.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import app.iwf.vaccine.entity.Friend;
import lombok.Data;

@Data
public class FriendDTO {
	
	public FriendDTO() {
	}
	
	public FriendDTO(Friend friend) {
		this.code = friend.getCode();
		this.name = friend.getName();
		this.firstDose = friend.isFirstDosed();
		this.secondDose = friend.isSecondDosed();
	}
	
	private UUID code;
	@NotEmpty(message = "Who are you?")
	private String name;
	@NotNull(message = "Are ya vaccinated yet, son?")
	private Boolean firstDose;
	@NotNull(message = "Are ya double-vaccinated yet, son?")
	private Boolean secondDose;

}
