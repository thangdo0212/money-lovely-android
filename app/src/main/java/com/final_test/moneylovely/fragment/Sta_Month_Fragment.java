package com.final_test.moneylovely.fragment;

import android.database.Cursor;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.final_test.moneylovely.MainActivity;
import com.final_test.moneylovely.R;
import com.github.mikephil.charting.charts.PieChart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Sta_Month_Fragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    static TextView txtTongChiMonth;
    TextView txtTongThuMonth;
    static TextView txtMonth;
    static int UserID;
    static String thangHientai;
    Spinner spinnerThang;
    static int tongThuVND = 0;
    static int tongChiVND = 0;
    static DecimalFormat formatter = new DecimalFormat("#,###,###");
    String tongThuVNDFormat = "0";
    static String tongChiVNDFormat = "0";
    public static PieChart pieChart;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sta_month, container, false);
        anhxa(view);
        SpinnerThang();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getDataThu() {
        tongThuVND = 0;
        Cursor dataCV = MainActivity.database.GetData("SELECT * FROM DoanhThu WHERE idUser = '" + UserID + "' AND deleteFlag = 0");
        while (dataCV.moveToNext()) {
            String money = dataCV.getString(2);
            String donviThu = dataCV.getString(3);
            String date = dataCV.getString(6);
            UserID = dataCV.getInt(10);
            if (date.contains(thangHientai)) {
                if (donviThu.equals("VND")) {
                    tongThuVND += Integer.parseInt(money);
                }
            }
        }
        tongThuVNDFormat = formatter.format(tongThuVND);
        txtTongThuMonth.setText(tongThuVNDFormat);
        txtMonth.setText("Tháng " + thangHientai);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void getDataChi() {
        tongChiVND = 0;
        Cursor dataCV = MainActivity.database.GetData("SELECT * FROM KhoangChi WHERE idUser = '" + UserID + "' AND deleteFlag = 0");
        while (dataCV.moveToNext()) {
            String money = dataCV.getString(2);
            String donviThu = dataCV.getString(3);
            String date = dataCV.getString(6);
            UserID = dataCV.getInt(10);
            if (date.contains(thangHientai)) {
                if (donviThu.equals("VND")) {
                    tongChiVND += Integer.parseInt(money);
                }
            }
        }
        tongChiVNDFormat = formatter.format(tongChiVND);
        txtTongChiMonth.setText(tongChiVNDFormat);
        com.final_test.moneylovely.view.PieChart.addDataSet(pieChart, tongChiVND, tongThuVND, 2);

        txtMonth.setText("Tháng " + thangHientai);

    }

    private void DayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        thangHientai = (month + 1) + "/" + year;
    }

    private void SpinnerThang() {
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Chọn tháng");
        for (int i = 1; i < 13; i++) {
            String thang = "Tháng " + i;
            arrayList.add(thang);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        spinnerThang.setAdapter(arrayAdapter);
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    thangHientai = position + "/" + 2019;
                    Log.d("date", thangHientai);
                    getDataThu();
                    getDataChi();
                    com.final_test.moneylovely.view.PieChart.addDataSet(pieChart, tongChiVND, tongThuVND, 2);

                } else {
                    DayOfMonth();
                    getDataThu();
                    getDataChi();
                    com.final_test.moneylovely.view.PieChart.addDataSet(pieChart, tongChiVND, tongThuVND, 2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void anhxa(View view) {
        txtMonth = view.findViewById(R.id.txtThang);
        txtTongChiMonth = view.findViewById(R.id.txtChiMonth);
        txtTongThuMonth = view.findViewById(R.id.txtThuMonth);
        spinnerThang = view.findViewById(R.id.spinerThang);
        UserID = MainActivity.UserID;
        pieChart = (PieChart) view.findViewById(R.id.piechartMonth);
    }
}
