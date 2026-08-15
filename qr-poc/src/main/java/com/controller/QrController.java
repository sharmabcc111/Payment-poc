package com.controller;

import com.dto.PaymentRequest;
import com.google.zxing.WriterException;
import com.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrController {
    private final OrgService orgService;

    @PostMapping(value = "/generate",
    produces = MediaType.IMAGE_PNG_VALUE
)
    public byte[] create(@RequestBody PaymentRequest request) throws IOException, WriterException {
      return orgService.create(request);
    }

}
