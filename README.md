# SortRecycleView

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.joelan:SortRecycleView:Tag'
	}


# 2.在布局文件加入SortRecycleView

    <cn.crazyjoe.sortrecycleviewlib.SidebarRecycleView
        android:id="@+id/sibar_recycleview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:chooseTextColor="@android:color/white"
        app:textColor="#030303"
        app:backgroundColor="#BE0FA7EC"
        app:letterbackgroundColor="@color/transparent"

        app:letterBgStrokeColor="@color/transparent"
        app:textSize="13sp"
        app:hintTextSize="32sp"
        app:radius="26dp"
        app:circleRadius="30dp"
        >
        <!-- 默认  radius 20   circle 24-->

    </cn.crazyjoe.sortrecycleviewlib.SidebarRecycleView>





# 3.使用例子，必须SortModel作为，adapter里实现IBaseAdapter方法，传递当前同时adapter类型，实现 getPositionForSection方法。参考例子

```java
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
```java

