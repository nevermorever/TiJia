package com.zyjd.tijia.activity.monitor;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.ElevatorRealtimeData;
import com.zyjd.tijia.websocket.WsManager;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class RealtimeDataMonitorActivity extends ToolbarActivity implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;


    private Adapter adapter;
    private Handler handler = new Handler();
    private Runnable run;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_realtime_data_monitor;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        toolbar.setOnMenuItemClickListener(this);

        adapter = new Adapter(R.layout.list_item_elevator_realtime_data);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        // 初始化websocket连接
        WsManager.getInstance().initWebsocket(new WsAdapter());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_realtime_monitor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return true;
    }


    private class WsAdapter extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.i("TiJia", "websocket 收到消息：" + text);

            final String txt = text;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Type type = new TypeToken<ArrayList<ElevatorRealtimeData>>() {
                    }.getType();
                    List<ElevatorRealtimeData> list = new Gson().fromJson(txt, type);
                    adapter.replaceData(list);
                }
            });


        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            Log.i("TiJia", "websocket 连接成功");
            JSONArray array = new JSONArray();
            array.put("1");
            final String s = array.toString();
            websocket.sendText(s);
            final WebSocket ws = websocket;
            run = new Runnable() {
                @Override
                public void run() {
                    ws.sendText(s);
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(run, 1000);
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
            Log.i("TiJia", "websocket 连接错误");

        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.i("TiJia", "websocket 连接断开");
        }
    }

    private class Adapter extends BaseQuickAdapter<ElevatorRealtimeData, BaseViewHolder> {
        public Adapter(int layoutResId, @Nullable List<ElevatorRealtimeData> data) {
            super(layoutResId, data);
        }

        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, ElevatorRealtimeData item) {
            helper.setText(R.id.tv_elevator_name, item.getName())
            .setText(R.id.tv_floor, item.getRealtime_data().getFloor())
            .setText(R.id.tv_direction, "1")
            .setText(R.id.tv_door_status, "")
            .setText(R.id.tv_have_ppl, "")
            .setText(R.id.tv_operation_mode, "")
            .setText(R.id.tv_car_tem_hum,"")
            .setText(R.id.tv_safety_circuit, "")
            .setText(R.id.tv_run_status, "")
            .setText(R.id.tv_door_lock,"")
            .setText(R.id.tv_elevator_lock, "")
            .setText(R.id.tv_is_overload, "")
            .setText(R.id.tv_system_power, "1")
            .setText(R.id.tv_flat_floor_status, "1")
            .setText(R.id.tv_brake_status, "1");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(run, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(run);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 断开websocket连接
        WsManager.getInstance().disconnect();
    }
}
