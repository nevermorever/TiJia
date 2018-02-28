package com.zyjd.tijia.websocket;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.zyjd.tijia.Constant;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WsManager {

    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;

    private static WsManager instance;
    private WsListener listener;
    private WebSocket ws;
    private WsStatus status;

    public WsManager() {
    }

    public static WsManager getInstance() {
        if (instance == null) {
            synchronized (WsManager.class) {
                if (instance == null) {
                    instance = new WsManager();
                }
            }
        }
        return instance;
    }

    // 初始化websocket
    public void initWebsocket(WebSocketAdapter webSocketAdapter) {
        try {
            ws = new WebSocketFactory()
                    .createSocket(Constant.WS_REALTIME_DATA_URL)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)            // 设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)             // 设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(webSocketAdapter)       // 添加回调监听
                    .connectAsynchronously();                       // 异步连接

            setStatus(WsStatus.CONNECTING);
            Log.i("TiJia", "websocket 正在连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 监听
    public class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.i("TiJia", "websocket 收到消息：" + text);
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            setStatus(WsStatus.CONNECTED);
            Log.i("TiJia", "websocket 连接成功");

        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
            setStatus(WsStatus.CONNECT_ERROR);
            Log.i("TiJia", "websocket 连接错误");

        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            setStatus(WsStatus.CONNECT_ERROR);
            Log.i("TiJia", "websocket 连接断开");
        }
    }

    public void setStatus(WsStatus status) {
        this.status = status;
    }

    public WsStatus getStatus() {
        return status;
    }

    public void disconnect() {
        if (ws != null)
            ws.disconnect();
    }

    // 连接状态
    public enum WsStatus {
        CONNECTED,          // 连接成功
        CONNECT_ERROR,      // 连接失败
        CONNECTING;         // 正在连接
    }

    // TODO: 重连、心跳等机制
}
