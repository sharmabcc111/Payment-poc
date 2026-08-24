package com.service;


import com.dto.PaymentRequest;
import com.entity.Organisation;
import com.entity.PaymentQr;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.repository.OrgRepo;
import com.repository.QrRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrServiceImpl {
    private final OrgRepo orgRepo;
    private final QrRepo qrRepo;

    public byte[] generateQr(String text) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();

        BitMatrix matrix = writer.encode(
                        text,
                        BarcodeFormat.QR_CODE,
                        300,
                        300);
        BufferedImage image = new BufferedImage(
                        300,
                        300,
                        BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                image.setRGB(x, y, matrix.get(x, y)
                                ? 0x000000
                                : 0xFFFFFF
                );
            }
        }

        ByteArrayOutputStream baos =
                new ByteArrayOutputStream();

        ImageIO.write(image, "PNG", baos);

        return baos.toByteArray();
    }

    public byte[] create(PaymentRequest request) throws IOException, WriterException {
       Organisation organisation = orgRepo.findById(request.getOrganisationId()).orElseThrow(()->new RuntimeException("organisation not found"));
        String upiString ="upi://pay"+"?pa="+organisation.getUpiId()+"&pa"+organisation.getAccountName()+"&am="+request.getAmount()+"&cu=INR";

        PaymentQr paymentQr= PaymentQr.builder().
                organisation(organisation)
                .qrData(upiString)
                .transactionReference(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .build();

        qrRepo.save(paymentQr);
        return generateQr(upiString);

    }

    }

