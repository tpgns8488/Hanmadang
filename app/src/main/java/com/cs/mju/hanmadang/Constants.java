package com.cs.mju.hanmadang;

/**
 * Created on Youthink on 2016-01-04
 */
public class Constants {
    /* NOTICE ACTIVITY */
    /* NOTICE URL */
    public static final String NOTICE_URL = "http://jw4.mju.ac.kr/user/boardList.action?boardId=28510945&page=1";

    /* XML PARSING TAG AND PARAMS */
    public static final String TITLE_ELEMENT = "td[class=title]";
    public static final String NUMBER_ELEMENT = "td[class=no]";
    public static final String TIMESTAMP_ELEMENT = "td:eq(3)";
    public static final String NOTICE_URL_ELEMENT = "td > a[href]";
    public static final String HREF_ELEMENT = "abs:href";
    public static final String NOTICE_DIV_VIEW_P = "div#divView > p";
    public static final String NOTICE_DIV_VIEW_DIV = "div#divView > div";
    public static final String NOTICE_DIV_VIEW_B = "div#divView > b";
    public static final String NOTICE_DIV_VIEW_BR = "div#divView > br";
    public static final String NOTICE_IMG_SELECT= "img[src]";
    public static final String NOTICE_IMG_SELECT_PARAMS= "abs:src";

    public static final String DISCONNECTED_INTERNET = "인터넷 연결 안됨";

    /* NOTICE_CONTENT_PARAMS */
    public static final String NOTICE_TITLE = "제목 : ";
    public static final String NOTICE_TIMESTAMP = "작성일 : ";
    public static final String NOTICE_ATTACH_FILE = "첨부파일 : ";

    public static final int ACTIVITY_REQUEST_CODE = 1;

    public static final String JPEG = ".jpg";

    /* BackKeyHandler */
    public static final String BACK_BTN_CLICK = "\'뒤로\'버튼을 한번 더 누르시면 종료 됩니다.";

    /* Key SETTING */
    public static final String CLUB_AO = "#ao0000";
    public static final String CLUB_INCLUDE = "#in0000";
    public static final String CLUB_HANWOOL = "#ha0000";
}