package com.zl.tesseract.scanner.tess;


public interface TesseractCallback {

    void succeed(String result);

    void fail();
}
