package com.github.tangyi.common.core.utils.zxing;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * 二维码实体类
 *
 * @author zdz
 * @date 2022/04/10 15:28
 */
public class QRCode implements QRCodeImage {

    BufferedImage bufImg;

    public QRCode(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        return bufImg.getRGB(x, y);
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }

}

