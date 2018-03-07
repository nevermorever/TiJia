package com.zyjd.tijia.activity.maintenance;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;
import com.zyjd.tijia.R;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.MaintenancePlan;
import com.zyjd.tijia.widget.calendar.CustomDayView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MaintenancePlanActivity extends ToolbarActivity {
    @BindView(R.id.tv_current_year)
    TextView tv_current_year;
    @BindView(R.id.tv_current_month)
    TextView tv_current_month;
    @BindView(R.id.month_pager)
    MonthPager monthPager;
    @BindView(R.id.content)
    CoordinatorLayout content;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private List<MaintenancePlan> maintenancePlanList;
    private List<MaintenancePlan> planDateList;
    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private CalendarDate currentDate;
    private CalendarViewAdapter calendarViewAdapter;
    private int currentPage = MonthPager.CURRENT_DAY_INDEX;
    private MaintenancePlanAdapter maintenancePlanAdapter;
    private boolean initiated = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !initiated) {
            refreshMonthPager();
            initiated = true;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_maintenance_plan;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        monthPager.setViewHeight(Utils.dpi2px(this, 270));

        maintenancePlanAdapter = new MaintenancePlanAdapter(R.layout.list_item_maintenance_plan);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(maintenancePlanAdapter);
        recycler.setHasFixedSize(true);
        initCurrentDate();
        initCalendarView();

        getPlansDate();

    }

    // 获取15天之内的维保计划
    private void getPlansDate() {
        ApiClient.getApiService(this)
                .getMaintenancePlanDateList(new HashMap<String, Object>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<MaintenancePlan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MaintenancePlan> maintenancePlans) {
                        planDateList = maintenancePlans;
                        initMarkData();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initCalendarView() {
        CustomDayView customDayView = new CustomDayView(this, R.layout.custom_day);
        calendarViewAdapter = new CalendarViewAdapter(
                this,
                new OnSelectDateListener() {
                    @Override
                    public void onSelectDate(CalendarDate calendarDate) {
                        currentDate = calendarDate;
                        tv_current_year.setText(currentDate.getYear() + getString(R.string.year));
                        tv_current_month.setText(currentDate.getMonth() + "");

                        DateFormat fmt = new SimpleDateFormat("YYYY-mm-ddd");
//                        Date date = fmt.parse(currentDate.toString())

                        // 选中日期，请求当天数据渲染
                        ApiClient.getApiService(MaintenancePlanActivity.this)
                                .getMaintenancePlanList(new HashMap<String, Object>())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<List<MaintenancePlan>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(List<MaintenancePlan> maintenancePlans) {
                                        maintenancePlanAdapter.replaceData(maintenancePlans);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onSelectOtherMonth(int offset) {
                        //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                        monthPager.selectOtherMonth(offset);
                    }
                },
                CalendarAttr.CalendarType.MONTH,
                CalendarAttr.WeekArrayType.Sunday,
                customDayView
        );
        calendarViewAdapter.setOnCalendarTypeChangedListener(new CalendarViewAdapter.OnCalendarTypeChanged() {
            @Override
            public void onCalendarTypeChanged(CalendarAttr.CalendarType type) {
            }
        });
        initMonthPager();
    }

    private void initCurrentDate() {
        currentDate = new CalendarDate();
        tv_current_year.setText(currentDate.getYear() + getString(R.string.year));
        tv_current_month.setText(currentDate.getMonth() + "");
    }

    /**
     * 初始化标记数据，HashMap的形式，可自定义
     * 如果存在异步的话，在使用setMarkData之后调用 calendarAdapter.notifyDataChanged();
     */
    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();

        DateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat fmt2 = new SimpleDateFormat("yyyy-M-d");
        for (MaintenancePlan plan : planDateList) {
            try {
                Date date1 = fmt1.parse(plan.getStart());
                String str = fmt2.format(date1);
                markData.put(str, "1");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        calendarViewAdapter.setMarkData(markData);
        calendarViewAdapter.notifyDataChanged();
    }

    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarViewAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                currentCalendars = calendarViewAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    tv_current_year.setText(currentDate.getYear() + getString(R.string.year));
                    tv_current_month.setText(currentDate.getMonth() + "");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void refreshMonthPager() {
        CalendarDate today = new CalendarDate();
        calendarViewAdapter.notifyDataChanged(today);
        tv_current_year.setText(currentDate.getYear() + getString(R.string.year));
        tv_current_month.setText(currentDate.getMonth() + "");
    }


    @OnClick(R.id.iv_previous_month)
    void onPreviousMonthClick() {
        monthPager.setCurrentItem(monthPager.getCurrentPosition() - 1);
    }

    @OnClick(R.id.iv_next_month)
    void onNextMonthClick() {
        monthPager.setCurrentItem(monthPager.getCurrentPosition() + 1);
    }

    private class MaintenancePlanAdapter extends BaseQuickAdapter<MaintenancePlan, BaseViewHolder> {
        public MaintenancePlanAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, MaintenancePlan item) {

        }
    }
}