package com.sonar.vishal.ui.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sonar.vishal.medico.common.util.LoggerApi;
import com.sonar.vishal.medico.common.util.LoggerMessage;

public class BarcodeUtil {

	public static final String PNG = "png";
	public static final String IMAGE_INDEX_URL = "data:image/png;base64,";

	public static String generateBarcode(String message, int width, int height) {
		String base64 = null;
		try {
			Map<EncodeHintType, Integer> hints = new HashMap<>();
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bitMatrix = new QRCodeWriter().encode(message, BarcodeFormat.QR_CODE, width, height, hints);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, PNG, outputStream);
			base64 = new String(Base64.getEncoder().encode(outputStream.toByteArray()));
		} catch (WriterException e) {
			LoggerApi.error(LoggerMessage.BARCODE_UTIL_CLASS_NAME, e.getLocalizedMessage());
		} catch (IOException e) {
			LoggerApi.error(LoggerMessage.BARCODE_UTIL_CLASS_NAME, e.getLocalizedMessage());
		}
		return base64;
	}
}
