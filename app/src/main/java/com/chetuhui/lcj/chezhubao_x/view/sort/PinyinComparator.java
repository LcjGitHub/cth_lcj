package com.chetuhui.lcj.chezhubao_x.view.sort;

import com.chetuhui.lcj.chezhubao_x.model.CityBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CityBean.DataBean> {

	public int compare(CityBean.DataBean o1, CityBean.DataBean o2) {
		if (o1.getPinyin().equals("@")
				|| o2.getPinyin().equals("#")) {
			return 1;
		} else if (o1.getPinyin().equals("#")
				|| o2.getPinyin().equals("@")) {
			return -1;
		} else {
			return o1.getPinyin().compareTo(o2.getPinyin());
		}
	}

}
