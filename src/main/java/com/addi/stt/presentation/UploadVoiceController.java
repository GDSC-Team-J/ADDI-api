package com.addi.stt.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.addi.stt.appication.VoiceService;
import com.addi.stt.appication.dto.VoiceResponse;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UploadVoiceController {

	private final VoiceService voiceService;

	@PostMapping("/api/uploadVoice")
	public ResponseEntity<VoiceResponse> getUserRole(@RequestHeader String macAddress,
		@RequestParam("files") List<MultipartFile> files) {
		VoiceResponse response = voiceService.convert(macAddress, files);
		return ResponseEntity.ok(response);
	}
}
