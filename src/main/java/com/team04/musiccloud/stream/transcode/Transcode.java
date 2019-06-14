package com.team04.musiccloud.stream.transcode;

import be.hogent.tarsos.transcoder.DefaultAttributes;
import be.hogent.tarsos.transcoder.Transcoder;
import be.hogent.tarsos.transcoder.ffmpeg.EncoderException;
import com.team04.musiccloud.audio.Audio;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Sigmoid;

public class Transcode extends Thread {

  private final static Logger logger = Logger.getGlobal();
  private Audio audio;
  private double weight;
  private UnivariateFunction calculator;

  public Transcode(Audio audio) {
    this.audio = audio;
    this.weight = 0.0;
    // 0 ~ 100까지 값을 출력하도록 합니다.
    this.calculator = new Sigmoid(0, 100);
    logger.setLevel(Level.INFO);
  }

  /**
   * Sigmoid 함수에 적절한 값으로 대응되도록 만드는 함수입니다. 기존의 값을 -10 ~ 10으로 downsizing을 해주도록 합니다.
   *
   * 예를 들어, 0 ~ 100까지의 값이 들어온다고하면 10으로 나누어주고, -10을 진행하도록 합니다.
   */
  private double preProcessWeight(double weight) {
    //@TODO: weight에 대한 전처리를 해주도록 합니다. 이 부분은 담당자와 협의가 요구됩니다.
    return calculator.value(weight / 10000);
  }

  protected double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = preProcessWeight(weight);
  }

  protected DefaultAttributes getAudioSetting(double weight) {
    DefaultAttributes setting = DefaultAttributes.MP3_320KBS_STEREO_44KHZ;
    if (weight > 80.0) {
      setting = DefaultAttributes.MP3_128KBS_STEREO_44KHZ;
    } else if (weight > 65.0) {
      setting = DefaultAttributes.MP3_192KBS_STEREO_44KHZ;
    }

    return setting;
  }

  private void overwriteSourceToTarget(String sourceLocation, String targetLocation)
      throws IOException {
    File previousSource = new File(sourceLocation);
    boolean isDeleteSource = previousSource.delete();

    File sourceFile = new File(sourceLocation);
    File targetFile = new File(targetLocation);
    boolean isMove = targetFile.renameTo(sourceFile);

    if (!(isMove && isDeleteSource)) {
      throw new IOException();
    }
  }

  /**
   * 여기서 audio는 수정이 되지 않음에도 반환을 하도록 합니다. 그 이유는 향후에 혹시 모를 확장성을 위해서 가지고 있는 것입니다.
   *
   * @return Audio
   */
  public Audio getAudio() throws IOException {
    if (weight < 25) // weight가 25 이하인 경우 transcode가 불필요하다고 판단.
    {
      return audio;
    }
    String sourceLocation = audio.getFileMeta().getFullPath().toString();
    String sourceDirectory = audio.getFileMeta().getDirectory();
    String sourceName = audio.getFileMeta().getName();
    String sourceExtension = audio.getFileMeta().getExtension();

    String targetLocation = sourceDirectory + File.separator
        + sourceName + ".temp." + sourceExtension;
    DefaultAttributes audioSetting = getAudioSetting(weight);
    try {
      Transcoder.transcode(sourceLocation, targetLocation, audioSetting);
    } catch (EncoderException e) {
      logger.severe(e.toString());
    }
    overwriteSourceToTarget(sourceLocation, targetLocation);
    return audio;
  }

  @Override
  public void run() {
    try {
      getAudio();
    } catch (IOException e) {
      logger.warning(e.toString());
    }
  }
}
