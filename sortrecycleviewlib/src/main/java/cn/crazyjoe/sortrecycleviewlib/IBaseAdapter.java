package cn.crazyjoe.sortrecycleviewlib;

import android.support.v7.widget.RecyclerView;

public  interface    IBaseAdapter<T extends RecyclerView.Adapter>
{

    public   int getPositionForSection(int sectionCharASCII);

    public   T  getMyAdapter();


}
