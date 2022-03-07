package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    int manghinhbai[] = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.jco, R.drawable.qco, R.drawable.kco,
            R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5,
            R.drawable.b6, R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10,
            R.drawable.jbic, R.drawable.qbic, R.drawable.kbic,
            R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4, R.drawable.r5,
            R.drawable.r6, R.drawable.r7, R.drawable.r8, R.drawable.r9, R.drawable.r10,
            R.drawable.jro, R.drawable.qro, R.drawable.kro,
            R.drawable.ch1, R.drawable.ch2, R.drawable.ch3, R.drawable.ch4, R.drawable.ch5,
            R.drawable.ch6, R.drawable.ch7, R.drawable.ch8, R.drawable.ch9, R.drawable.ch10,
            R.drawable.j, R.drawable.q, R.drawable.k};
    int mang[] = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0
    };
    int soDiemNguoi = 0;
    int soDiemMay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv1 = findViewById(R.id.imgView1);
        ImageView iv2 = findViewById(R.id.imgView2);
        ImageView iv3 = findViewById(R.id.imgView3);
        ImageView iv1m = findViewById(R.id.imgView1may);
        ImageView iv2m = findViewById(R.id.imgView2may);
        ImageView iv3m = findViewById(R.id.imgView3may);
        TextView kqm = findViewById(R.id.txtKetQuamay);
        TextView diemNguoi = findViewById(R.id.diemN);
        TextView diemMay = findViewById(R.id.diemM);
        Button btn_rutbai = findViewById(R.id.btn_rutbai);
        EditText time = findViewById(R.id.edtTime);
        TextView kQ = findViewById(R.id.textView3);
        EditText edtTime = findViewById(R.id.edtTime);
        btn_rutbai.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              int[] value = laySoNgauNhien(0, 51);
                                              iv1.setImageResource(manghinhbai[value[0]]);
                                              iv2.setImageResource(manghinhbai[value[1]]);
                                              iv3.setImageResource(manghinhbai[value[2]]);

                                              iv1m.setImageResource(R.drawable.matlunglabai);
                                              iv2m.setImageResource(R.drawable.matlunglabai);
                                              iv3m.setImageResource(R.drawable.matlunglabai);
                                              kqm.setText("Kết quả");
                                              CountDownTimer Timer = new CountDownTimer(10000, 1000) {
                                                  @Override
                                                  public void onTick(long millisUntilFinished) {
                                                      btn_rutbai.setVisibility(View.GONE);
                                                      time.setText(String.valueOf(millisUntilFinished / 1000));
                                                  }

                                                  @Override
                                                  public void onFinish() {



                                                      iv1m.setImageResource(manghinhbai[value[3]]);
                                                      iv2m.setImageResource(manghinhbai[value[4]]);
                                                      iv3m.setImageResource(manghinhbai[value[5]]);
                                                      kqm.setText(tinhKetQuaMay(value));

                                                      if (ktraKQ(value) == 1) {
                                                          edtTime.setText("Người chơi thua");
                                                          soDiemNguoi++;
                                                          diemNguoi.setText(soDiemNguoi + "");
                                                      }else if (ktraKQ(value) == -1) {
                                                          edtTime.setText("Người chơi thắng");
                                                          soDiemMay++;
                                                          diemMay.setText(soDiemMay + "");
                                                      } else
                                                          edtTime.setText("Bằng điểm");
                                                      btn_rutbai.setVisibility(View.VISIBLE);

                                                  }
                                              }.start();

                                          }
                                      }

        );


    }

    //---------------------------------------------
    private String tinhKetQua(int[] baso) {
        String ketQua = "";
        if (tinhSoTay(baso) == 3)
            ketQua = "Kết quả: 3 tây";
        else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13+1 ;
            if (tong % 10 == 0)
                ketQua = "Kết quả bù";
            else
                ketQua = "Kết quả là " + (tong % 10) + " nút";
        }
        return ketQua;
    }

    //---------------------------------------------
    private String tinhKetQuaMay(int[] baso) {
        String ketQua = "";
        if (tinhSoTay(baso) == 3)
            ketQua = "Kết quả: 3 tây";
        else {
            int tong = 0;
            for (int j = 3; j < baso.length; j++)
                if (baso[j] % 13 < 10)
                    tong += baso[j] % 13 + 1;
            if (tong % 10 == 0)
                ketQua = "Kết quả bù";
            else
                ketQua = "Kết quả là " + (tong % 10) + " nút";
        }
        return ketQua;
    }

    //--------------------------------------------
    private int tinhSoTay(int[] a) {
        int k = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] % 13 >= 10 && a[i] % 13 < 13)
                k++;
        return k;
    }

    //---------------------------------------------
    private boolean kiemTraTrung(int k, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == k)
                return true;
        return false;

    }

    //---------------------------------------------
    private int[] laySoNgauNhien(int min, int max) {
        int[] baso = new int[6];
        int i = 0;
        baso[1] = random();
        do {
            int k = random();
            if (!kiemTraTrung(k, baso))
                baso[i++] = k;
        } while (i < 6);
        return baso;
    }

    //------------------------------------------------
    private int[] laySoNgauNhienMay(int min, int max) {
        int[] baso = new int[6];
        int j = 0;
        baso[1] = random();
        do {
            int k = random();
            if (!kiemTraTrung(k, baso))
                baso[j++] = k;
        } while (j < 6);
        return baso;
    }

    //----------------------------------------------
    private int random() {
        int min = 0;
        int max = 51;
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    //-----------------------------------------------
    private int ktraKQ(int a[]) {
        int May = 0;
        int Nguoi = 0;
        for(int i=0;i<3;i++){
            Nguoi += (mang[a[i]]);
        }
        for(int j=3;j<6;j++){
            May += (mang[a[j]]);
        }
        Nguoi = Nguoi%10;
        May = May%10;

        if (May > Nguoi)
            return 1;
        else if (Nguoi == May)
            return 0;
        else
            return -1;
    }

}