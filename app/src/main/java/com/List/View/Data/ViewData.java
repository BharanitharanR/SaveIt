package com.List.View.Data;




import android.widget.ArrayAdapter;

import com.camera.module.CameraInstance;

import java.util.ArrayList;

public class ViewData
{
  private static ViewData singleTonStaticViewData=null;

    private String[] ListElements = new String[] {
            "Android",
            "PHP"
    };
    private ArrayAdapter adapter = null;

    private ArrayList<String> ListData = new ArrayList<>();

    private ViewData()
        {

        }

    public synchronized  void setAdapter(ArrayAdapter adapter)
    {
        this.adapter = adapter;
    }
    public synchronized static ViewData getInstance()
        {
            if(singleTonStaticViewData == null)
            {
                singleTonStaticViewData = new ViewData();
            }
            return singleTonStaticViewData;
        }
    public synchronized ArrayList<String>  getListData()
        {
            return this.ListData;
        }

    public  synchronized void setData(String data) {
        this.ListData.add(data);
        this.adapter.notifyDataSetChanged();

    }

    public synchronized String getImageName(int position)
    {
        return this.ListData.get(position);
    }

}
