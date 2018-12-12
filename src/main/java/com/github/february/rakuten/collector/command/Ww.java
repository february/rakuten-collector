package com.github.february.rakuten.collector.command;

public class Ww {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String e = "キッズ 【AD_DAS】 アディダスオリジナルス ss 360 i スーパースター360 インファント S82711 17SP　BLAC_/WHT/WHT";
		String f = e.replaceAll(  "[\\u30A0-\\u30FF]", "");
		System.out.println(f);

	}

}
