package cn.crazyjoe.sortrecycleview;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.crazyjoe.sortrecycleviewlib.IBaseAdapter;
import cn.crazyjoe.sortrecycleviewlib.SidebarRecycleView;
import cn.crazyjoe.sortrecycleviewlib.SortModel;
import cn.crazyjoe.sortrecycleviewlib.TitleItemDecoration;

public class MainActivity extends AppCompatActivity {


    SidebarRecycleView  recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<UserEntitity>  list=new ArrayList<>();
        list.add(new UserEntitity("裘豆豆"));

        list.add(new UserEntitity("B李莎"));
        list.add(new UserEntitity("动力火车"));
        list.add(new UserEntitity("蔡依林"));
        list.add(new UserEntitity("吴克群"));
        list.add(new UserEntitity("王力宏"));
        list.add(new UserEntitity("夏先生"));
        list.add(new UserEntitity("阿李珊"));
        list.add(new UserEntitity("陈奕迅"));
        list.add(new UserEntitity("周杰伦"));
        list.add(new UserEntitity("阮金天"));



        list.add(new UserEntitity("阮银天"));
        list.add(new UserEntitity("宋伟"));
        list.add(new UserEntitity("苏醒"));

        List<SortModel<UserEntitity>>  sordata=new ArrayList<>();
        for(UserEntitity entitity:list)
        {
            sordata.add(new SortModel<UserEntitity>(entitity,entitity.getName()));
        }

        recycleView=findViewById(R.id.sibar_recycleview);
        TitleItemDecoration titleItemDecoration=new TitleItemDecoration.Builder<UserEntitity>(this,sordata)
                .setTitileTextColor(getResources().getColor(R.color.black))
                .setTitleTextBgColor(getResources().getColor(R.color.gray)).build();
        recycleView.setAdapter(new Myadapter(R.layout.list_item,sordata),sordata,titleItemDecoration);


    }

    class  Myadapter extends BaseQuickAdapter<SortModel<UserEntitity>,BaseViewHolder> implements IBaseAdapter<BaseQuickAdapter>
    {

        public Myadapter(int layoutResId, @Nullable List<SortModel<UserEntitity>> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final SortModel<UserEntitity> item) {

            helper.setText(R.id.user_entity,item.getOrigin_data().getName());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   Toast.makeText(MainActivity.this, item.getOrigin_data().getName(),Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public int getPositionForSection(int sectionCharASCII) {

            for (int i = 0; i < getItemCount(); i++) {
                String sortStr = mData.get(i).getLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == sectionCharASCII) {
                    return i;
                }
            }

            return -1;
        }

        @Override
        public BaseQuickAdapter getMyAdapter() {
            return this;
        }
    }



    public  class  UserEntitity {

        UserEntitity(String name)
        {
            this.name=name;
        }
        String name;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
