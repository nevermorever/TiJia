package com.zyjd.tijia.activity.device_manage;


import android.os.Bundle;
import android.widget.TextView;

import com.zyjd.tijia.R;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.BaseFragment;
import com.zyjd.tijia.entity.Elevator;
import com.zyjd.tijia.entity.User;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ElevatorParamsFragment extends BaseFragment {
    @BindView(R.id.tv_elevator_name)
    TextView tv_elevator_name;
    @BindView(R.id.tv_sn)
    TextView tv_sn;
    @BindView(R.id.tv_elevator_type)
    TextView tv_elevator_type;
    @BindView(R.id.tv_belong_org)
    TextView tv_belong_org;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_maintain_org)
    TextView tv_maintain_org;
    @BindView(R.id.tv_start_using_date)
    TextView tv_start_using_date;
    @BindView(R.id.tv_manufacturer)
    TextView tv_manufacturer;
    @BindView(R.id.tv_product_model)
    TextView tv_product_model;
    @BindView(R.id.tv_floor_staion_num)
    TextView tv_floor_staion_num;
    @BindView(R.id.tv_maintenance_ppl)
    TextView tv_maintenance_ppl;


    private int elevatorId;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_elevator_params;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        elevatorId = bundle.getInt("elevator_id");
    }

    @Override
    protected void initData() {
        super.initData();
        ApiClient
                .getApiService()
                .getElevatorById(elevatorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Elevator>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Elevator elevator) {
                        displayElevatorInfo(elevator);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void displayElevatorInfo(Elevator elevator) {
        tv_sn.setText(elevator.getSn());

        if (null != elevator.getBelong_org_detail()) {
            tv_belong_org.setText(elevator.getBelong_org_detail().getName());
        }

        tv_elevator_name.setText(elevator.getName());

        if (null != elevator.getMaintenance_org_detail()) {
            tv_maintain_org.setText(elevator.getMaintenance_org_detail().getName());
        }

        if (null != elevator.getArea_detail()) {
            tv_area.setText(elevator.getArea_detail().getName());
        }
        tv_elevator_type.setText(elevator.getType());
        tv_start_using_date.setText(elevator.getStart_using_date());
        tv_manufacturer.setText(elevator.getManufacturer());
        tv_product_model.setText(elevator.getProduct_model());
        tv_floor_staion_num.setText(elevator.getFloor() + "/" + elevator.getStation());

        if (null != elevator.getMaintenance_ppl_detail()) {
            List<User> list = elevator.getMaintenance_ppl_detail();
            String str = "";
            for (User user : list) {
                str = str + user.getName() + " ";
            }
            tv_maintenance_ppl.setText(str);
        }
    }
}
