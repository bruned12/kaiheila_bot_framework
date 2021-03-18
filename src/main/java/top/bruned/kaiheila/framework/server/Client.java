package top.bruned.kaiheila.framework.server;


import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import top.bruned.kaiheila.framework.plugin.loader.PluginManger;
import top.bruned.kaiheila.sdk.util.Log;
import top.bruned.kaiheila.sdk.wsclient.PING;
import top.bruned.kaiheila.sdk.wsclient.base.EVENT;

import java.net.URI;

public class Client extends WebSocketClient {
    public int sn = 0;
    private Thread daemon;
    private final Log log;
    private boolean wscs = false;
    private final PluginManger manger;

    public Client(URI serverURI, Log log, PluginManger manger) {
        super(serverURI);
        this.manger = manger;
        this.log = log;
    }

    public void sendPing() {
        while (wscs) {
            try {
                Thread.sleep(28000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("[WSS][心跳]PING SN=" + this.sn);
            send(PING.PING(this.sn));
        }
        log.info("[WSS][心跳PING]结束");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        this.wscs = true;
        this.daemon = new Thread(this::sendPing);
        this.daemon.start();

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        this.wscs = false;
        log.info("[WSS]断开" + code);
        this.daemon.interrupt();
        connect();
    }

    @Override
    public void onMessage(String message) {
        EVENT event = JSONObject.parseObject(message).toJavaObject(EVENT.class);

        switch (event.getS()) {
            case 0: {
                log.debug("[WSS][事件][源]" + message);
                this.sn = event.getSn();
                manger.eventParse.start(event);
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
    public void onError(Exception ex) {
        this.wscs = false;
        log.warning("[WSS]" + ex);
    }

    public void stopAll() {
        this.daemon.stop();
        close();
    }
}
