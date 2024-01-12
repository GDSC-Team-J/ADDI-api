package com.addi.stt.presentation;

import com.addi.stt.appication.VoiceService;
import com.addi.stt.appication.dto.VoiceResponse;
import com.addi.user.application.UserLoginService;
import com.addi.user.application.dto.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UplodeVoiceController {

	private final VoiceService voiceService;

	@PostMapping("/api/uploadVoice")
	public ResponseEntity<VoiceResponse> getUserRole(@RequestHeader String macAddress,@RequestParam("files") List<MultipartFile> files) {
		VoiceResponse response = voiceService.convert(macAddress,files);
		return ResponseEntity.ok(response);
	}
}
