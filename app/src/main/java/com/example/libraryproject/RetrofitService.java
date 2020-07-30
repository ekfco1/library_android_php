package com.example.libraryproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface RetrofitService {

        @POST("register.php") //회원가입
        Call<MemDTO> getregister(@Query("UserID") String UserID, @Query("UserPW") String UserPW, @Query("UserName") String UserName,
                                 @Query("UserPhone") Integer UserPhone, @Query("UserFlag") String UserFlag, @Query("Token") String Token);// register = api uri 부분(php이름)

//   ??     @GET("login1.php") //로그인
        @GET("tlogin.php")
        Call<MemDTO> getlogin(@Query("UserID") String UserID, @Query("UserPW") String UserPW);

        @GET("retrofit_test.php") //예시
        Call<MemDTO> gettest(@Query("UserID") String UserID);

        @POST("inserth.php") //희망도서 등록
        Call<HBookDTO> inserthbook(@Query("HTitle") String HTitle, @Query("HAuthor") String HAuthor, @Query("HPublish") String HPublish);

        @GET("find1.php") //ID/PW 찾기
        Call<MemDTO> find(@Query("UserName")String UserName, @Query("UserPhone") Integer UserPhone);

        @POST("insertbook.php")// 도서 등록
        Call<BookDTO> insertbook(@Query("BTitle") String BTitle, @Query("BAuthor") String BAuthor, @Query("BPublish") String BPublish,
                                 @Query("BDate") String BDate, @Query("BFlag") String BFlag, @Query("MemNo") String MemNo);
        @GET("htest1.php")// 희망 도서 확인
        Call<List<HBookDTO>> gethbook();

        @GET("getfaq.php")//faq 첫화면
        Call<List<FaqDTO>> getFaq();
//
        @POST("insertfaq.php")//faq 등록
        Call<FaqDTO> insertFaq( @Query("FTitle") String FTitle, @Query("FContent") String FContent,@Query("MemNo") String MemNo);

        @GET("readfaq.php")//FAQ read
        Call<FaqDTO> readFaq(@Query("FNo") String FNo);

        @POST("updatefaq1.php")//FAQ 수정
        Call<FaqDTO> updateFaq(@Query("FNo") String FNo, @Query("FTitle") String Ftitle,@Query("FContent") String FContent);

        @GET("getboard.php")//도서후기 첫화면
        Call<List<BoardDTO>> getboard();

        @POST("deletefaq.php") //faq 삭제
        Call<FaqDTO> deleteFaq(@Query("FNo") String FNo/*, @Query("FFlag") String FFlag*/);

        @GET("readboard.php")//도서후기 읽기
        Call<BoardDTO> readBoard(@Query("BoardNo") String BoardNo);

        @POST("insertboard.php")//도서후기
        Call<BoardDTO> insertboard(@Query("BoardTitle") String BoardTitle,@Query("BoardBTitle") String BoardBTitle, @Query("BoardContent") String BoardContent
                , @Query("MemNo") String MemNo);

        @POST("updateboard.php")//도서후기 수정
        Call<BoardDTO> updateBoard(@Query("BoardNo") String BoardNo,@Query("BoardTitle") String BoardTitle,@Query("BoardBTitle") String BoardBTitle,
                                   @Query("BoardContent") String BoardContent);

        @POST("deleteboard.php") //도서후기 삭제
        Call<BoardDTO> deleteBoard(@Query("BoardNo") String BoardNo);

        @GET("workbook.php")//도서관리
        Call<List<BookDTO>> workBook();

        @GET("mypage.php") //마이페이지
        Call<List<BookDTO>> myPage(@Query("MemNo") String MemNo);

        @POST("delay.php") //도서 연장
        Call<BookDTO> delay(@Query("MemNo") String MemNo,@Query("BNo") String BNo, @Query("BReturn") String BReturn, @Query("EFlag") String EFlag);

        @GET("searchbook.php")
        Call<List<BookDTO>> searchBook(@Query("BTitle") String BTitle);

        @GET("rentbook.php")
        Call<BookDTO> rentBook(@Query("MemNo") String MemNo, @Query("BNo")String BNo, @Query("rMemNo") String rMemNo);

        @GET("returnbook.php")
        Call<BookDTO> returnBook(@Query("BNo")String BNo);
}

