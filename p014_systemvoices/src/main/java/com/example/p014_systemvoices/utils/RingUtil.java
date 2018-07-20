package com.example.p014_systemvoices.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;

import com.example.p014_systemvoices.R;

import java.io.IOException;
import java.util.List;

/**
 * 系统声音stream_type：
 * 1.设置闹钟音量                  AudioManager.STREAM_ALARM
 * 2.设置媒体（音乐）音量          AudioManager.STREAM_MUSIC
 * 3.设置通知音量                  AudioManager.STREAM_NOTIFICATION
 * 4.设置系统音量                  AudioManager.STREAM_SYSTEM
 * 5.设置手机铃声音量              AudioManager.STREAM_RING
 * 6.设置通话音量                  AudioManager.STREAM_VOICE_CALL
 * 7.设置拨号键音量                AudioManager.STREAM_DTMF
 */
public class RingUtil {
    private static final String TAG = RingUtil.class.getSimpleName();
    //音效值（1～3）
    public static final String CURRENT_SOUNDEFFECT = "sound_effect";     //音效的设置
    //当前系统声音值用于存sp
    public static final String CURRENT_SOUNDALARM = "current_alarm";         //闹钟声音
    public static final String CURRENT_SOUNDMUSIC = "current_music";         //媒体（音乐）声音
    public static final String CURRENT_SOUNDDTMF = "current_dtmf";           //拨号键声音
    public static final String CURRENT_SOUNDNOTICE = "current_notice";      //通知声音
    public static final String CURRENT_SOUNDRING = "current_ring";           //手机铃声声音
    public static final String CURRENT_SOUNDVOICECALL = "current_voicecall"; //通话声音
    public static final String CURRENT_SOUNDSYSTEM = "current_system";       //系统声音
    //静音时用户设置的声音值
    public static final String QUIET_SOUNDTAG = "quiet_tag";             //静音标志位
    public static final String QUIET_SOUNDALARM = "quiet_alarm";         //闹钟声音
    public static final String QUIET_SOUNDMUSIC = "quiet_music";         //媒体（音乐）声音
    public static final String QUIET_SOUNDDTMF = "quiet_dtmf";           //拨号键声音
    public static final String QUIET_SOUNDNOTICE = "quiet_notice";      //通知声音
    public static final String QUIET_SOUNDRING = "quiet_ring";           //手机铃声声音
    public static final String QUIET_SOUNDVOICECALL = "quiet_voicecall"; //通话声音
    public static final String QUIET_SOUNDSYSTEM = "quiet_system";       //系统声音


    private Context context;

    private RingUtil(Context context) {
        this.context = context;
    }

    private static RingUtil instance;

    public static RingUtil getInstance(Context context) {
        if (instance == null) {
            instance = new RingUtil(context);
        }
        return instance;
    }

    /**
     * 使用指定声音类型播放音效（当用户调节声音时，进行音效播放）
     * 播放指定音效 raw_url: 0 默认系统, R.raw.ring1，R.raw.ring2，R.raw.ring3
     *
     * @param stream_type 默认AudioManager.STREAM_SYSTEM
     */
    public void play_which_raw(final int stream_type) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Uri uri = null;
                int raw_url = 0;
                if (RingUtil.getInstance(context).ring_get_sp_sound_effect() == 1) {
                    raw_url = R.raw.ring1;
                }
                if (RingUtil.getInstance(context).ring_get_sp_sound_effect() == 2) {
                    raw_url = R.raw.ring2;
                }
                if (RingUtil.getInstance(context).ring_get_sp_sound_effect() == 3) {
                    raw_url = R.raw.ring3;
                }
                if (raw_url == 0) {
                    uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                } else {
                    uri = Uri.parse("android.resource://" + App.get().getPackageName() + "/" + raw_url);
                }
                final MediaPlayer player = new MediaPlayer();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        player.release();
                    }
                });
                try {
                    player.setDataSource(context, uri);
                    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    if (audioManager.getStreamVolume(stream_type) != 0) {
                        player.setAudioStreamType(stream_type);
                        // player.setLooping(true);
                        player.prepare();
                        player.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 500);
    }

    /**
     * 设置播放音效sp
     *
     * @param i
     */
    public void ring_put_sp_sound_effect(int i) {
        RingSpUtils.getInstance(context).put(CURRENT_SOUNDEFFECT, i);
    }

    /**
     * 获取播放音效sp
     */
    public int ring_get_sp_sound_effect() {
        return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDEFFECT, 1);
    }

    /**
     * 设置指定声音的音量
     *
     * @param audioManager
     * @param stream_type  音量类型
     * @param volume       音量大小
     */
    public void set_zhiding_voices_volume(AudioManager audioManager, int stream_type, int volume) {
        audioManager.setStreamVolume(stream_type, volume, 0);
    }

    /**
     * 获取当前指定声音的音量
     *
     * @param audioManager
     * @param stream_type
     * @return
     */
    public int get_zhiding_voices_volume(AudioManager audioManager, int stream_type) {
        return audioManager.getStreamVolume(stream_type);
    }

    /**
     * 获取当前指定声音的最大声音
     *
     * @param audioManager
     * @param stream_type
     * @return
     */
    public int get_zhiding_voices_MaxVolume(AudioManager audioManager, int stream_type) {
        return audioManager.getStreamMaxVolume(stream_type);
    }

    /**
     * 获取指定类型音量从当前声音SP
     *
     * @param stream_type
     * @return
     */
    public int ring_get_sp_current_vol(int stream_type) {
        if (stream_type == AudioManager.STREAM_ALARM) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDALARM, -1);
        } else if (stream_type == AudioManager.STREAM_MUSIC) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDMUSIC, -1);
        } else if (stream_type == AudioManager.STREAM_DTMF) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDDTMF, -1);
        } else if (stream_type == AudioManager.STREAM_NOTIFICATION) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDNOTICE, -1);
        } else if (stream_type == AudioManager.STREAM_RING) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDRING, -1);
        } else if (stream_type == AudioManager.STREAM_VOICE_CALL) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDVOICECALL, -1);
        } else if (stream_type == AudioManager.STREAM_SYSTEM) {
            return (int) RingSpUtils.getInstance(context).get(CURRENT_SOUNDSYSTEM, -1);
        }
        return 0;
    }

    /**
     * 记录指定类型SeekBar的progress到静音声音SP
     *
     * @param stream_type
     * @param i
     */
    public void ring_put_sp_mute_vol(int stream_type, int i) {
        if (stream_type == AudioManager.STREAM_ALARM) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDALARM, i);
        } else if (stream_type == AudioManager.STREAM_MUSIC) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDMUSIC, i);
        } else if (stream_type == AudioManager.STREAM_DTMF) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDDTMF, i);
        } else if (stream_type == AudioManager.STREAM_NOTIFICATION) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDNOTICE, i);
        } else if (stream_type == AudioManager.STREAM_RING) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDRING, i);
        } else if (stream_type == AudioManager.STREAM_VOICE_CALL) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDVOICECALL, i);
        } else if (stream_type == AudioManager.STREAM_SYSTEM) {
            RingSpUtils.getInstance(context).put(QUIET_SOUNDSYSTEM, i);
        }
    }

    /**
     * 获取指定类型SeekBar的progress到静音声音SP
     *
     * @param stream_type
     * @return
     */
    public int ring_get_sp_mute_vol(int stream_type) {
        if (stream_type == AudioManager.STREAM_ALARM) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDALARM, -1);
        } else if (stream_type == AudioManager.STREAM_MUSIC) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDMUSIC, -1);
        } else if (stream_type == AudioManager.STREAM_DTMF) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDDTMF, -1);
        } else if (stream_type == AudioManager.STREAM_NOTIFICATION) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDNOTICE, -1);
        } else if (stream_type == AudioManager.STREAM_RING) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDRING, -1);
        } else if (stream_type == AudioManager.STREAM_VOICE_CALL) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDVOICECALL, -1);
        } else if (stream_type == AudioManager.STREAM_SYSTEM) {
            return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDSYSTEM, -1);
        }
        return 0;
    }

    /**
     * 获取静音状态从SP文件中
     *
     * @return 1：静音  0：非静音  -1：第一次检测静音状态
     */
    public int ring_get_sp_mute_state() {
        return (int) RingSpUtils.getInstance(context).get(QUIET_SOUNDTAG, -1);
    }

    /**
     * 记录静音状态到SP文件
     *
     * @param i 1：静音  0：非静音
     */
    public void ring_put_sp_mute_state(int i) {
        RingSpUtils.getInstance(context).put(QUIET_SOUNDTAG, i);
    }


    /**
     * 记录指定类型当前音量到当前声音SP  传-1 记录所有类型当前音量到当前声音SP
     *
     * @param stream_type
     */
    public void ring_put_sp_current_vol(AudioManager audioManager, int stream_type) {
        if (stream_type == AudioManager.STREAM_ALARM) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDALARM, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_ALARM));
        } else if (stream_type == AudioManager.STREAM_MUSIC) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDMUSIC, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_MUSIC));
        } else if (stream_type == AudioManager.STREAM_DTMF) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDDTMF, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_DTMF));
        } else if (stream_type == AudioManager.STREAM_NOTIFICATION) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDNOTICE, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_NOTIFICATION));
        } else if (stream_type == AudioManager.STREAM_RING) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDRING, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_RING));
        } else if (stream_type == AudioManager.STREAM_VOICE_CALL) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDVOICECALL, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_VOICE_CALL));
        } else if (stream_type == AudioManager.STREAM_SYSTEM) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDSYSTEM, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_SYSTEM));
        } else if (stream_type == -1) {
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDALARM, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_ALARM));
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDMUSIC, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_MUSIC));
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDDTMF, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_DTMF));
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDNOTICE, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_NOTIFICATION));
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDRING, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_RING));
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDVOICECALL, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_VOICE_CALL));
            RingSpUtils.getInstance(context).put(CURRENT_SOUNDSYSTEM, RingUtil.getInstance(context).get_zhiding_voices_volume(audioManager, AudioManager.STREAM_SYSTEM));
        }
    }

    /**
     * 测试音量
     */
    public  void getSoundVolume(AudioManager audioManager) {
        Log.e("geekSTREAM_ALARM", audioManager.getStreamVolume(AudioManager.STREAM_ALARM) + "");
        Log.e("geekSTREAM_MUSIC", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + "");
        Log.e("geekSTREAM_DTMF", audioManager.getStreamVolume(AudioManager.STREAM_DTMF) + "");
        Log.e("geekSTREAM_NOTIFICATION", audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) + "");
        Log.e("geekSTREAM_RING", audioManager.getStreamVolume(AudioManager.STREAM_RING) + "");
        Log.e("geekSTREAM_VOICE_CALL", audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL) + "");
        Log.e("geekSTREAM_SYSTEM", audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM) + "");
    }

    /**
     * 判断当前实际是否有声音
     * （注：当前的规则认为所有seekbar注册的音量类型的当前音量之和大于0，否则认为是实际是静音情况）
     *
     * @return true：有声音  false：没声音
     */
    public boolean ring_has_sound(List<SeekBar> seekbars) {
        int sum = 0;
        for (SeekBar seekbar : seekbars) {
            int type = (int) seekbar.getTag();
            sum += ring_get_sp_current_vol(type);
            Log.e("---geek-----",type+"   "+sum);

        }
        if (sum > 0) {
            return true;
        }
        return false;
    }

    /**
     * 设置声音调节Seekbar的UI样式
     *
     * @param seekbar
     * @param isMute
     */
    public void ring_set_ui_seekbar(SeekBar seekbar, boolean isMute) {
        if (isMute) {
            seekbar.setProgressDrawable(context.getDrawable(R.drawable.volume_bg_progress_grey));
            seekbar.setThumb(context.getDrawable(R.drawable.volume_thumb_grey));
        } else {
            seekbar.setProgressDrawable(context.getDrawable(R.drawable.volume_bg_progress));
            seekbar.setThumb(context.getDrawable(R.drawable.volume_thumb));
        }
    }

    /**
     * 打开静音事件的操作
     * 1.记录静音状态到SP文件 1
     * 2.记录指定类型SeekBar的progress到静音声音SP
     * 3.设置指定声音的音量为0
     * 4.记录指定类型当前音量到当前声音SP  传-1 记录所有类型当前音量到当前声音SP
     * 5.设置声音调节Seekbar的UI样式为不可用
     *
     * @param seekbars
     */
    public void ring_open_mute(AudioManager audioManager, List<SeekBar> seekbars) {
        ring_put_sp_mute_state(1);      //设置静音标志位：静音
        for (SeekBar seekbar : seekbars) {
            int type = (int) seekbar.getTag();
            int currentVol = seekbar.getProgress();
            ring_put_sp_mute_vol(type, currentVol);// 记录指定类型SeekBar的progress到静音声音SP
            set_zhiding_voices_volume(audioManager, type, 0);// 设置指定声音的音量
            ring_put_sp_current_vol(audioManager, type);// 记录指定类型当前音量到当前声音SP  传-1 记录所有类型当前音量到当前声音SP
            ring_set_ui_seekbar(seekbar, true);// 设置声音调节Seekbar的UI样式为不可用
        }
    }

    /**
     * 关闭静音事件的操作
     * 1.记录静音状态到SP文件 0
     * 2. seekbar.setMax(maxVol);
     * 3.如果实际有声音，seekbar位置根据实际声音SP恢复 获取指定类型音量从当前声音SP
     * 4.如果实际没有声音，seekbar位置基于静音备份SP恢复，将该静音备份设置给当前声音  获取指定类型SeekBar的progress到静音声音SP 设置指定声音的音量
     * 5.设置声音调节Seekbar的UI样式为可用
     *
     * @param audioManager
     * @param seekbars
     */
    public void ring_close_mute(AudioManager audioManager, List<SeekBar> seekbars) {
        ring_put_sp_mute_state(0);      //设置静音标志位：非静音
        for (SeekBar seekbar : seekbars) {
            int type = (int) seekbar.getTag();
            int maxVol = get_zhiding_voices_MaxVolume(audioManager, type);
            seekbar.setMax(maxVol);
            if (ring_has_sound(seekbars)) {  // 如果实际有声音，seekbar位置根据实际声音SP恢复
                int currentVol = ring_get_sp_current_vol(type);// 获取指定类型音量从当前声音SP
                seekbar.setProgress(currentVol);
            } else {                 // 如果实际没有声音，seekbar位置基于静音备份SP恢复，将该静音备份设置给当前声音
                int currentVol = ring_get_sp_mute_vol(type);// 获取指定类型SeekBar的progress到静音声音SP
                seekbar.setProgress(currentVol);
                set_zhiding_voices_volume(audioManager, type, currentVol);// 设置指定声音的音量
            }
            ring_set_ui_seekbar(seekbar, false);// 设置声音调节Seekbar的UI样式为可用
        }
    }





}
