package com.zq.mvvm.api


import com.pcl.mvvm.network.entity.ArticlesBean
import com.zq.base.network.BaseResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *   @auther : Aleyn
 *   time   : 2019/10/29
 */
interface HomeService {

    /**
     * 玩安卓轮播图
     */
    @GET("/vitalSigns/main")
    suspend fun getMain(): BaseResult<String>


    /**
     * 导航数据
     */
    @GET("project/tree/json")
    suspend fun naviJson(): BaseResult<List<ArticlesBean>>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("article/listproject/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): BaseResult<ArticlesBean>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseResult<ArticlesBean>


    /**
     * 常用网站
     */
    @GET("friend/json")
    suspend fun getPopularWeb(): BaseResult<MutableList<ArticlesBean>>
}