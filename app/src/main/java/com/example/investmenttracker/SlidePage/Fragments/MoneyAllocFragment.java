package com.example.investmenttracker.SlidePage.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.investmenttracker.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class MoneyAllocFragment extends Fragment {

    private static MoneyAllocFragment instance = null;
    public PieChart moneyAllocChart;
    public ArrayList<PieEntry> mMoneyAllocValues;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup moneyAllocView = (ViewGroup) inflater.inflate(R.layout.fragment_money_allocated, container, false);
        moneyAllocChart = moneyAllocView.findViewById(R.id.moneyAllocChart);
        mMoneyAllocValues = new ArrayList<>();
        instance = this;
        createMoneyAllocChart(mMoneyAllocValues, "0.0");
        return moneyAllocView;
    }

    public static MoneyAllocFragment getInstance() {
        return instance;
    }

    public void createMoneyAllocChart(ArrayList<PieEntry> moneyAllocValues, String portValue) {
        moneyAllocChart.setExtraOffsets(12f, 12f,12f,12f);
        moneyAllocChart.getDescription().setEnabled(false);
        moneyAllocChart.getLegend().setEnabled(false);

        mMoneyAllocValues = moneyAllocValues;
        moneyAllocChart.setCenterText(portValue + " €");

        PieDataSet dataSet = new PieDataSet(mMoneyAllocValues, null);
        PieData data = new PieData(dataSet);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(100f);
        dataSet.setValueLinePart1Length(0.6f);
        dataSet.setValueLinePart2Length(0.6f);
        moneyAllocChart.setEntryLabelColor(Color.BLACK);

        data.setValueFormatter(new PercentFormatter());
        moneyAllocChart.setData(data);
        moneyAllocChart.setDrawHoleEnabled(true);
        moneyAllocChart.setTransparentCircleRadius(50f);
        moneyAllocChart.setHoleRadius(46f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        moneyAllocChart.animateXY(0, 2000, Easing.EaseInOutCubic);
    }
}
