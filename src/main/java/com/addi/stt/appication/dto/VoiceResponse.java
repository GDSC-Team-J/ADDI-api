package com.addi.stt.appication.dto;

import com.addi.user.doamin.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VoiceResponse{
	List<String> EncodedFiles;

}
