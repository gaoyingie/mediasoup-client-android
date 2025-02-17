package org.webrtc;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

public class RTCUtils {

  public static long getNativeMediaStreamTrack(@NonNull MediaStreamTrack track) {
    return track.getNativeMediaStreamTrack();
  }

  public static long getNativeRtpSender(RtpSender sender) {
    return sender.getNativeRtpSender();
  }

  // use Reflection to get RtpReceiver#nativeRtpReceiver
  public static long getNativeRtpReceiver(RtpReceiver receiver) {
    long nativeRtpReceiver = 0L;
    try {
      Class<?> clazz = receiver.getClass();
      Field f = clazz.getDeclaredField("nativeRtpReceiver");
      f.setAccessible(true);
      nativeRtpReceiver = (long) f.get(receiver);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return nativeRtpReceiver;
  }

  public static RtpParameters.Encoding genRtpEncodingParameters(
      boolean active,
      Integer maxBitrateBps,
      Integer minBitrateBps,
      Integer maxFramerate,
      Integer numTemporalLayers,
      Double scaleResolutionDownBy,
      Long ssrc) {
    return new RtpParameters.Encoding(
        active,
        maxBitrateBps,
        minBitrateBps,
        maxFramerate,
        numTemporalLayers,
        scaleResolutionDownBy,
        ssrc);
  }
}
