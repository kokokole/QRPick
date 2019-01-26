package com.ldcc.eleven.qrpick;

public class Notice {

    /**
     *
     * 45 files committed: 19/01/26 15:13 Commit  한 곳에 모아 놓고 보려고 Activity를 하나의 패키지(activities)로 묶음.
     * 일단 3개의 Activity를 만들어 둠(내용은 없음)
     * 로그인화면(LoginActivity)
     * 고객화면(CustomerActivity)
     * 관리자화면(ManagerActivity)
     * 고객과 관리자화면은 패키지를 만들어서 각 화면에 필요한 클래스들은 해당 패키지에 저장하면 될것 같음.
     * 공통된 기능은 기능에 맞게 QR이면 qr패키지에, Adapter면 adapter패키지에 저장
     * 각각의 기능이라면 각 패키지에 저장하는게 어디에 쓰이는 기능인지 쉽게 알 수 있지 않을까 생각됨.
     *
     * 관리자화면은 태블릿용 large사이즈 레이아웃도 하나 만들어 둠
     *
     * util패키지에는 vo랑 dao 패키지를 일단 만들어 둠.
     *
     * 필요한게 생기면 만들어가면서 추가하면 될 것 같습니다.
     *
     * RecyclerView, Retrofit, okhttp, gson 등 일단 추가해둠 (다른거 쓰셔도 됨)
     */


    /**
     * 19/01/27  01:30
     *  두 가지 QR 라이브러리를 추가하고 확인함
     *  CustomerActivity에서는 firebase QR인식을 이용
     *  ManagerActivity에서는 다른 라이브러리를 이용
     *  firebase QR인식이 더 좋은거 같음
     *
     */
}
