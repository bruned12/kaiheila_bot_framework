package top.bruned.kaiheila.framework.wsclient;


import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import top.bruned.kaiheila.framework.plugin.loader.PluginManger;
import top.bruned.kaiheila.sdk.util.Log;
import top.bruned.kaiheila.sdk.wsclient.PING;
import top.bruned.kaiheila.sdk.wsclient.base.EVENT;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class Client extends WebSocketClient {
    public int sn = 0;
    private Thread daemon;
    private final Log log;
    private final PluginManger manger;

    public Client(URI serverURI, Log log, PluginManger manger) {
        super(serverURI);
        this.manger = manger;
        this.log = log;
    }

    public void sendPing() {
        while (true) {
            try {
                Thread.sleep(25000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("[WSS][心跳]PING SN=" + this.sn);
            send(PING.PING(this.sn));
        }
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        this.daemon = new Thread(this::sendPing);
        this.daemon.start();
    }

    @Override
    public void onMessage(String message) {
        log.debug(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("[WSS]断开" + code);
        this.daemon.interrupt();
        this.sn = 0;
    }

    public void message(String message) {
        EVENT event = JSONObject.parseObject(message).toJavaObject(EVENT.class);
        switch (event.getS()) {
            case 0: {
                log.debug(message);
                this.sn = event.getSn();
                new Thread(() -> manger.eventChannel.start(event)).start();
                break;
            }
            case 1: {
                log.info("[WSS][连接]" + message);
                break;
            }
            case 3: {
                log.info("[WSS][心跳]PONG");
                break;
            }
            case 5: {
                log.info("[WSS][请求重新连接]" + message);

                break;
            }
            case 6: {
                log.info("[WSS][RESUME]" + message);

                break;
            }
        }
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        message(new String(decompress(bytes.array())));
    }

    @Override
    public void onError(Exception ex) {
        log.warning("[WSS]" + ex);
        this.sn = 0;
    }

    public void stopAll() {
        this.daemon.stop();
        this.sn = 0;
        close();
    }
    public static byte[] decompress(byte[] data) {
        byte[] output;

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        decompresser.end();
        return output;
    }
}
