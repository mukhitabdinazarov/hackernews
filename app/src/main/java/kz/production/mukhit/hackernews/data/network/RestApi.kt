package kz.production.mukhit.hackernews.data.network

import com.google.gson.GsonBuilder
import kz.production.mukhit.hackernews.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RestApi {
    companion object {

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private var client = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val newRequest = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .build()


                        return chain.proceed(newRequest)
                    }
                }).build()


        public val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder()
                                .setLenient()
                                .create()
                ))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        private val apiInterface: ApiHelper = retrofit.create(ApiHelper::class.java)

        fun getApi(): ApiHelper = apiInterface
    }

}