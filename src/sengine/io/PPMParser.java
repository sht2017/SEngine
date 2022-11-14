package sengine.io;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.util.IllegalFormatFlagsException;

public class PPMParser {
    private static final byte[] MAGIC_NUM_HEADER = {80, 54};
    private static final byte SPACE = 32;
    private static final byte LINE_BREAKER = 10;

    public static BufferedImage parse(byte[] rawFile) {
        int width = 0;
        int height = 0;
        String tmpWidth = "";
        String tmpHeight = "";
        int i = 0;
        for (; i < 2; i++) {
            if (rawFile[i] != MAGIC_NUM_HEADER[i]) {
                throw new IllegalFormatFlagsException("HEADER");
            }
        }
        int delimiterCounter = 0;
        HEADER:
        for (; i < rawFile.length; i++) {
            if (rawFile[i] == SPACE || rawFile[i] == LINE_BREAKER) {
                delimiterCounter++;
            }
            switch (delimiterCounter) {
                case 0:
                case 3:
                    break;
                case 1:
                    tmpWidth += (char) Byte.toUnsignedInt(rawFile[i]);
                    break;
                case 2:
                    tmpHeight += (char) Byte.toUnsignedInt(rawFile[i]);
                    break;
                default:
                    i++;
                    break HEADER;
            }
            if ((i > 1 && delimiterCounter < 1)) {
                throw new IllegalFormatFlagsException("DELIMITER");
            }
            if ((i > 7 && delimiterCounter < 2) || (i > 13 && delimiterCounter < 3)) {
                throw new IllegalFormatFlagsException("IMAGE_SIZE");
            }
        }
        width = Integer.parseInt(tmpWidth.substring(1));
        height = Integer.parseInt(tmpHeight.substring(1));
        int[] data = new int[width * height];
        int dataStart = i;
        for (; i < rawFile.length; i++) {
            final int offset = i - dataStart;
            switch (offset % 3) {
                case 0:
                    data[offset / 3] |= 0xFF000000;
                    data[offset / 3] |= Byte.toUnsignedInt(rawFile[i]) << 16;
                    break;
                case 1:
                    data[offset / 3] |= Byte.toUnsignedInt(rawFile[i]) << 8;
                    break;
                case 2:
                    data[offset / 3] |= Byte.toUnsignedInt(rawFile[i]) << 0;
                    break;
            }
        }
        ColorModel cm = ColorModel.getRGBdefault();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        DataBufferInt buffer = (DataBufferInt) raster.getDataBuffer();
        int[] bufferData = buffer.getData();
        System.arraycopy(data, 0, bufferData, 0, (data.length < bufferData.length ? data.length : bufferData.length));
        return new BufferedImage(cm, raster, cm.isAlphaPremultiplied(), null);
    }

    public static byte[] compose(BufferedImage imageBuffer) {
        DataBufferInt buffer = (DataBufferInt) imageBuffer.getRaster().getDataBuffer();
        int[] data = buffer.getData();
        String widthStr = "" + imageBuffer.getWidth();
        String heightStr = "" + imageBuffer.getHeight();
        int headerLength = ("P6 " + widthStr + " " + heightStr + " " + "255 ").length();
        byte[] rawData = new byte[headerLength + imageBuffer.getWidth() * imageBuffer.getHeight() * 3];
        int i = 0;
        for (; i < MAGIC_NUM_HEADER.length; i++) {
            rawData[i] = MAGIC_NUM_HEADER[i];
        }
        rawData[i] = LINE_BREAKER;
        i++;
        int offset = i;
        for (; i < offset + (widthStr).length(); i++) {
            rawData[i] = (byte) widthStr.charAt(i - offset);
        }
        rawData[i] = SPACE;
        i++;
        offset = i;
        for (; i < offset + (heightStr).length(); i++) {
            rawData[i] = (byte) heightStr.charAt(i - offset);
        }
        rawData[i] = SPACE;
        i++;
        rawData[i] = 0x32;
        i++;
        rawData[i] = 0x35;
        i++;
        rawData[i] = 0x35;
        i++;
        rawData[i] = SPACE;
        i++;
        offset = i;
        for (; i < rawData.length; i++) {
            switch ((i - offset) % 3) {
                case 0:
                    rawData[i] = (byte) ((data[(i - offset) / 3] & 0xFF0000) >>> 16);
                    break;
                case 1:
                    rawData[i] = (byte) ((data[(i - offset) / 3] & 0xFF00) >>> 8);
                    break;
                case 2:
                    rawData[i] = (byte) ((data[(i - offset) / 3] & 0xFF) >>> 0);
                    break;
            }
        }
        return rawData;
    }

//    public Image parse(){
//        byte[] rawFile=readBytes();
//        int width=0;
//        int height=0;
//        String tmpWidth="";
//        String tmpHeight="";
//        int i=0;
//        int delimiterCounter=0;
//        for (;i<2;i++) {
//            if (rawFile[i] != MAGIC_NUM_HEADER[i]) {
//                throw new IllegalFormatFlagsException("HEADER");
//            }
//        }
//        HEADER:
//        for (; i<rawFile.length; i++){
//            if (rawFile[i]==SPACE||rawFile[i]==LINE_BREAKER){
//                delimiterCounter++;
//            }
//            switch (delimiterCounter){
//                case 0:
//                case 3:
//                    break;
//                case 1:
//                    tmpWidth += (char) Byte.toUnsignedInt(rawFile[i]);
//                    break;
//                case 2:
//                    tmpHeight += (char) Byte.toUnsignedInt(rawFile[i]);
//                    break;
//                default:
//                    i++;
//                    break HEADER;
//            }
//            if ((i>1&&delimiterCounter<1)){
//                throw new IllegalFormatFlagsException("DELIMITER");
//            }
//            if ((i>7&&delimiterCounter<2)||(i>13&&delimiterCounter<3)){
//                throw new IllegalFormatFlagsException("IMAGE_SIZE");
//            }
//        }
//        width = Integer.parseInt(tmpWidth.substring(1));
//        height = Integer.parseInt(tmpHeight.substring(1));
//        int[][][] image = new int[width][height][3];
//        int dataStart=i;
//        for (;i<rawFile.length;i++){
//                final int offset=i-dataStart;
//                image[offset/3%width][offset/3/width][offset%3]=Byte.toUnsignedInt(rawFile[i]);
//        }
//        return new Image(image);
//    }
}
