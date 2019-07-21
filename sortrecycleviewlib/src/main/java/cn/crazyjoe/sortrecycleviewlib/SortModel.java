package cn.crazyjoe.sortrecycleviewlib;

import android.text.TextUtils;

public class SortModel<T>{

    private String sort_key;   //用来排序
    private String letters;//显示拼音的首字母
    T  origin_data;

    public T getOrigin_data() {
        return origin_data;
    }

    public void setOrigin_data(T origin_data) {
        this.origin_data = origin_data;
    }

    public String getSort_key() {
        return sort_key;
    }

    public void setSort_key(String sort_key) {
        this.sort_key = sort_key;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public SortModel(T data, String sort_key)
    {
        this.origin_data=data;
        this.sort_key=sort_key;
        init();
    }

    private  void init()
    {

        if(!TextUtils.isEmpty(sort_key)) {
            String pinyin = PinyinUtils.getPingYin(sort_key);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                this.setLetters(sortString.toUpperCase());
            } else {
                this.setLetters("#");
            }
        }
        else
        {
            this.setLetters("#");
        }


    }


}
