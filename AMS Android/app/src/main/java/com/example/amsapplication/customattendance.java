package com.example.amsapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class customattendance extends BaseAdapter{
    private Context context;

    ArrayList<String> a;
    ArrayList<String> b;
    SharedPreferences sh;




    public customattendance(Context applicationContext, ArrayList<String> a, ArrayList<String> b) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        sh= PreferenceManager.getDefaultSharedPreferences(context);



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_customattendance, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView19);
        CheckBox cb=(CheckBox)gridView.findViewById(R.id.checkBox);






        tv1.setText(a.get(position));




        tv1.setTextColor(Color.BLACK);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb.isChecked())
                {
                    String s=sh.getString("att","")+b.get(position)+",";
                    SharedPreferences.Editor ed=sh.edit();
                    ed.putString("att",s);
                    ed.commit();
                }
                else {
                    String s=sh.getString("att","").replace(","+b.get(position)+",",",");
                    SharedPreferences.Editor ed=sh.edit();
                    ed.putString("att",s);
                    ed.commit();
                }
            }
        });










        return gridView;

    }

}
